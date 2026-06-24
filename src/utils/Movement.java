package utils;

import entity.BaseEntity;
import entity.Creature;
import entity.EntityType;
import map.Coordinates;
import map.WorldMap;

import java.util.HashSet;
import java.util.Set;

public final class Movement {
    public static boolean isTarget(Coordinates coordinateTarget, WorldMap worldMap, Coordinates coordinateStart) {
        BaseEntity potentialTarget = worldMap.getEntity(coordinateTarget);
        Creature moveEntity = (Creature) worldMap.getEntity(coordinateStart);
        return moveEntity.getTarget().equals(potentialTarget.getType());
    }

    public static boolean isAvailableMove (Coordinates coordinateTarget, WorldMap worldMap, Coordinates coordinateStart) {
        BaseEntity potentialMovement = worldMap.getEntity(coordinateTarget);
        Creature moveEntity = (Creature) worldMap.getEntity(coordinateStart);
        for (EntityType entityType : moveEntity.getAvailableEntityTypes()) {
            if (entityType.equals(potentialMovement.getType())) {
                return true;
            }
        }
        return false;
    }

    public static Set<Coordinates> getSetPotentialCoordinates (WorldMap worldMap, Coordinates coordinateStart, Coordinates coordinateEntity) {

        Set<Coordinates> coordinatesSet = new HashSet<>();
        int x = coordinateStart.x();
        int y = coordinateStart.y();

        Coordinates potentialCoordinateXMinus = new Coordinates(x-1, y);
        if (x != 0 && isAvailableMove(potentialCoordinateXMinus, worldMap, coordinateEntity)) coordinatesSet.add(potentialCoordinateXMinus);

        Coordinates potentialCoordinateYMinus= new Coordinates(x, y-1);
        if (y != 0 && isAvailableMove(potentialCoordinateYMinus, worldMap, coordinateEntity)) coordinatesSet.add(potentialCoordinateYMinus);

        Coordinates potentialCoordinateXPlus= new Coordinates(x+1, y);
        if (x != worldMap.getSizeM()-1 && isAvailableMove(potentialCoordinateXPlus, worldMap, coordinateEntity)) coordinatesSet.add(potentialCoordinateXPlus);

        Coordinates potentialCoordinateYPlus= new Coordinates(x, y+1);
        if (y != worldMap.getSizeN()-1 && isAvailableMove(potentialCoordinateYPlus, worldMap, coordinateEntity)) coordinatesSet.add(potentialCoordinateYPlus);

        return coordinatesSet;
    }
}
