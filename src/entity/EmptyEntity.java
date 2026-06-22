package entity;

public class EmptyEntity extends BaseEntity{
    @Override
    public EntityType returnType() {
        return EntityType.EMPTINESS;
    }
}
