import map.Render;
import map.WorldMap;
import process.Actions;

import java.util.Scanner;

public class Simulation {
    static WorldMap worldMap;
    static Render render;
    static int counterStep = 1;
    private static final String END_SIMULATION = "E";
    private static final String GO_SIMULATION = "Y";
    private static final String GO_COORDINATES = "C";
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        int intM = 0;
        while(true) {
            System.out.println("Введите ширину поля для симуляции:");
            String strM = inputScanner.nextLine();
            try {
                intM = Integer.parseInt(strM);

                if (intM > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введите целое число");
            }

        }

        int intN = 0;
        while(true) {
            System.out.println("Введите высоту поля для симуляции:");
            String strN = inputScanner.nextLine();
            try {
                intN = Integer.parseInt(strN);

                if (intN > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введите целое число");
            }
        }

        System.out.println("Запускаем симуляцию.");

        worldMap = Actions.initActions(intM, intN);
        render = new Render(worldMap);


        loop:
        while (true) {
            System.out.println("Симуляция на шаге " + counterStep);
            render.print();

            if (worldMap.isCrises()) {
                Actions.topActions(worldMap);
            }

            String choseNextStep = "";
            if ((counterStep%5) == 0) {
                do {
                    System.out.println("Для прекращения симуляции введите " + END_SIMULATION);
                    System.out.println("Для продолжения симуляции введите " + GO_SIMULATION);
                    System.out.println("Для движения конкретной сущностью введите " + GO_COORDINATES);

                    choseNextStep = inputScanner.nextLine();

                } while (!choseNextStep.equals(END_SIMULATION) && !choseNextStep.equals(GO_SIMULATION) && !choseNextStep.equals(GO_COORDINATES));



            } else {
                Actions.moveAll(worldMap);
            }

            switch (choseNextStep) {
                case END_SIMULATION: break loop;
                case GO_SIMULATION:
                    Actions.moveAll(worldMap);
                    break;
                case GO_COORDINATES:
                    int x = -1;
                    int y = -1;
                    do {
                        System.out.println("Введите координату по горизонтали:");
                        x = Integer.parseInt(inputScanner.nextLine());
                    } while (x < 1 || x > worldMap.getSizeM());

                    do {
                        System.out.println("Введите координату по вертикали:");
                        y = Integer.parseInt(inputScanner.nextLine());
                    } while (y < 1 || y > worldMap.getSizeN());

                    Actions.moveCoordinates(worldMap, x, y);
                    break;
            }
            counterStep++;
        }



    }
}