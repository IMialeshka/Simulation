package entity;

public class Rock extends BaseEntity {
    @Override
    public EntityType returnType() {
        return EntityType.ROCK;
    }
}
