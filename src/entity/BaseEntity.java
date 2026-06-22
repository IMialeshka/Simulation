package entity;

public abstract class BaseEntity {

    public EntityType getType() {
        return returnType();
    };

    public abstract EntityType returnType();
}
