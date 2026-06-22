package entity;

import map.Coordinates;
import map.WorldMap;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Predator extends Creature{
    private int attackPower;

    public Predator(int speed, int attackPower) {
        super(speed);
        this.attackPower = attackPower;
        super.setTarget(EntityType.HERBIVORE);
        Set<EntityType> availableSet = new HashSet<>();
        availableSet.add(EntityType.EMPTINESS);
        availableSet.add(EntityType.GRASS);
        availableSet.add(EntityType.HERBIVORE);
        super.setAvailableEntityTypes(availableSet);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        int i = 1;
        Queue<Coordinates> pathCoordinates = this.getPath().getPathQueue();
        Coordinates coordinate = pathCoordinates.poll();
        Coordinates startCoordinate = coordinate;
        Coordinates previousCoordinate = null;
        while (i <= getSpeed() && coordinate != null){
            previousCoordinate = coordinate;
            BaseEntity entity = worldMap.getEntity(coordinate);
            if (entity.getType().equals(EntityType.HERBIVORE)) {
                Herbivore herbivore = (Herbivore) entity;
                if (herbivore.getHp() <= this.attackPower) {
                    worldMap.addEntity(new EmptyEntity(), startCoordinate);
                    worldMap.addEntity(this, coordinate);
                } else {
                    worldMap.addEntity(new EmptyEntity(), startCoordinate);
                    worldMap.addEntity(this, previousCoordinate);
                }
                return;
            }
            coordinate = pathCoordinates.poll();
            i++;
        }

        worldMap.addEntity(new EmptyEntity(), startCoordinate);
        worldMap.addEntity(this, previousCoordinate);
    }

    @Override
    public EntityType returnType() {
        return EntityType.PREDATOR;
    }

}

