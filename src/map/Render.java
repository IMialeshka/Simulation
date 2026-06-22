package map;

import entity.BaseEntity;

public final class Render {
    private  WorldMap worldMap;

    public Render(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void print() {
        int sizeN = worldMap.getSizeN();
        int sizeM = worldMap.getSizeM();
       for (int i = 0; i < sizeM; i++) {
           for (int j = 0; j < sizeN; j++) {
               Coordinates coordinates = new Coordinates(i, j);
               BaseEntity element = worldMap.getEntity(coordinates);
               System.out.print(element.getType().getValue());
           }
           System.out.println();
       }
    }

}
