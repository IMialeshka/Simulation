package entity.utils;

import entity.*;

public class BaseEntityFactory {
    public BaseEntity generateBaseEntity(EntityType entityType) {
        BaseEntity entity = null;
        int speed;
        switch (entityType) {
            case EntityType.TREE:
                entity = new Tree();
                break;
            case EntityType.GRASS:
                entity = new Grass();
                break;
            case EntityType.ROCK:
                entity = new Rock();
                break;
            case EntityType.PREDATOR:
                speed = (int) (Math.random()*150);
                int attackPower = (int) (Math.random()*230);
                entity = new Predator(speed, attackPower);
                break;
            case EntityType.HERBIVORE:
                speed = (int) (Math.random()*101);
                int hp = (int) (Math.random()*350);
                entity = new Herbivore(speed, hp);
                break;
        }
        return entity;
    }
}
