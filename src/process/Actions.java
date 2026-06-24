package process;

import entity.*;
import utils.BaseEntityFactory;
import map.Path;
import map.PathFinder;
import map.WorldMap;
import map.Coordinates;


public final class Actions {

    public static WorldMap initActions(int sizeN, int sizeM) {
        WorldMap worldMap = createEmptyWorld(sizeN, sizeM);
        fillOutWorldMap(worldMap, EntityType.GRASS);
        fillOutWorldMap(worldMap, EntityType.TREE);
        fillOutWorldMap(worldMap, EntityType.ROCK);
        fillOutWorldMap(worldMap, EntityType.HERBIVORE);
        fillOutWorldMap(worldMap, EntityType.PREDATOR);
        return worldMap;
    }

    public static void topActions(WorldMap worldMap) {
        fillOutWorldMap(worldMap, EntityType.GRASS);
        fillOutWorldMap(worldMap, EntityType.HERBIVORE);
    }


    private static WorldMap createEmptyWorld(int sizeN, int sizeM) {
        WorldMap worldMap = new WorldMap(sizeN, sizeM);
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                EmptyEntity entity = new EmptyEntity();
                worldMap.addEntity(entity, coordinates);
            }
        }
        return worldMap;
    }

    private static void fillOutWorldMap(WorldMap worldMap, EntityType entityType) {
        int counter = (int)(worldMap.getSizeN()*worldMap.getSizeM()*entityType.getCoefficient());
        int minCountEntity = (int) (counter*entityType.getCoefficientCrises());
        int countEntityWorld = worldMap.getCountEntities(entityType);

        if (countEntityWorld > minCountEntity) {
            return;
        }
        int i = Math.min(countEntityWorld, minCountEntity);
        int x = -1;
        int y = -1;
        int sizeN = worldMap.getSizeN();
        int sizeM = worldMap.getSizeM();
        BaseEntityFactory baseEntityFactory = new BaseEntityFactory();
        while(i <=  counter) {
            x = (int) (Math.random()*sizeM);
            y = (int) (Math.random()*sizeN);
            Coordinates coordinates = new Coordinates(x, y);
            BaseEntity baseEntity = worldMap.getEntity(coordinates);
            if (baseEntity.getType() == EntityType.EMPTINESS) {
                worldMap.addEntity(baseEntityFactory.generateBaseEntity(entityType), coordinates);
                i++;
            }
        }
    }

    public static void moveAll(WorldMap worldMap) {
        for (int i = 0; i < worldMap.getSizeM(); i++) {
            for (int j = 0; j < worldMap.getSizeN(); j++) {
                moveCoordinates(worldMap, i, j);
            }
        }
    }

    public static void moveCoordinates(WorldMap worldMap, int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);
        BaseEntity entity = worldMap.getEntity(coordinates);
        if (entity.getType().equals(EntityType.PREDATOR) || entity.getType().equals(EntityType.HERBIVORE)) {
            Creature creature = (Creature) entity;
            Path path = PathFinder.find(coordinates, worldMap);
            creature.setPath(path);
            creature.makeMove(worldMap);
        }
    }
}
