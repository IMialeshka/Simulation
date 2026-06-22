package entity;

import map.Path;
import map.WorldMap;

import java.util.HashSet;
import java.util.Set;

public abstract class Creature extends BaseEntity {
    private int speed;
    private Path path;

    private EntityType target = EntityType.EMPTINESS;

    private Set<EntityType> availableEntityTypes = new HashSet<>();

    public Creature(int speed) {
        this.speed = speed;
    }

    public void setTarget(EntityType target) {
        this.target = target;
    }

    public EntityType getTarget() {
        return target;
    }

    public Set<EntityType> getAvailableEntityTypes() {
        return availableEntityTypes;
    }

    public void setAvailableEntityTypes(Set<EntityType> availableEntityTypes) {
        this.availableEntityTypes = availableEntityTypes;
    }

    public int getSpeed() {
        return speed;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void makeMove(WorldMap worldMap) {
    }

}
