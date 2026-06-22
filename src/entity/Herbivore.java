package entity;

import map.Coordinates;
import map.WorldMap;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Herbivore extends Creature{
    private int Hp;


    public Herbivore(int speed, int hp) {
        super(speed);
        Hp = hp;
        Set<EntityType> availableSet = new HashSet<>();
        availableSet.add(EntityType.EMPTINESS);
        availableSet.add(EntityType.GRASS);
        super.setAvailableEntityTypes(availableSet);
        super.setTarget(EntityType.GRASS);
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
            if (entity.getType().equals(EntityType.PREDATOR)) {
                break;
            }
            coordinate = pathCoordinates.poll();
            i++;
        }
        worldMap.addEntity(new EmptyEntity(), startCoordinate);
        worldMap.addEntity(this, previousCoordinate);

    }

    @Override
    public EntityType returnType() {
        return EntityType.HERBIVORE;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }


}
