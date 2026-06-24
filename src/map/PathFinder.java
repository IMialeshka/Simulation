package map;

import entity.Creature;
import entity.EntityType;
import entity.Herbivore;
import utils.Movement;

import java.util.*;

public class PathFinder {
    private static Set<Coordinates> checked;

    public static  Path find(Coordinates start, WorldMap worldMap) {

        checked = new HashSet<>();
        checked.add(start);
        Path startPath = new Path(start, start);

        return getPaths(startPath, worldMap).stream().min(((o1, o2) -> {
            Creature startCreature = (Creature) worldMap.getEntity(o1.getStartCoordinate());

            if (startCreature.returnType().equals(EntityType.HERBIVORE)) {
                return Integer.compare(o1.length, o2.length);
            } else {

                int resultCompareLength = Integer.compare(o1.length, o2.length);

                if (resultCompareLength == 0) {
                    Herbivore endCreatureO1 = (Herbivore) worldMap.getEntity(o1.getEndCoordinate());
                    Herbivore endCreatureO2 = (Herbivore) worldMap.getEntity(o2.getEndCoordinate());
                    return Integer.compare(endCreatureO1.getHp(), endCreatureO2.getHp());
                }

                return resultCompareLength;

            }
        })).orElse(startPath);
    }


    private static List<Path> getPaths(Path path, WorldMap worldMap) {
        List<Path> paths = new ArrayList<>();
            Coordinates current = path.getEndCoordinate();
            if (Movement.isTarget(current, worldMap, path.getStartCoordinate())) {
                path.addStepPath(current);
                paths.add(path);
                checked.add(current);
            } else {

                checked.add(current);
                for (Coordinates coordinates : Movement.getSetPotentialCoordinates(worldMap, current, path.getStartCoordinate())) {
                    if (!checked.contains(coordinates)) {
                        Path newPath = new Path(path, coordinates);
                        paths.addAll(getPaths(newPath, worldMap));
                    }
                }
            }
        return paths;
    }
}
