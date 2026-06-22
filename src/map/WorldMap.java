package map;

import entity.BaseEntity;
import entity.Creature;
import entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
  private final Map<Coordinates, BaseEntity> worldEntities = new HashMap<Coordinates, BaseEntity>();

  private final int  sizeN;
  private final int  sizeM;


    public WorldMap(int sizeN, int sizeM) {
        this.sizeN = sizeN;
        this.sizeM = sizeM;
    }

    public boolean isCrises() {
        int counterHerbivore = (int)(sizeN*sizeM*EntityType.HERBIVORE.getCoefficient());
        int minCountEntityHerbivore  = (int) (counterHerbivore*EntityType.HERBIVORE.getCoefficientCrises());
        int countEntityWorldHerbivore  = getCountEntities(EntityType.HERBIVORE);

        int counterGrass = (int)(sizeN*sizeM*EntityType.GRASS.getCoefficient());
        int minCountEntityGrass = (int) (counterGrass*EntityType.GRASS.getCoefficientCrises());
        int countEntityWorldGrass = getCountEntities(EntityType.GRASS);

        return countEntityWorldHerbivore <= minCountEntityHerbivore || countEntityWorldGrass <= minCountEntityGrass;
    }

    public int getCountEntities(EntityType entityType) {
        int result = 0;
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldEntities.get(coordinates).getType().equals(entityType)) {
                    result++;
                }
            }
        }
        return result;
    }

    public int getSizeN() {
        return sizeN;
    }

    public int getSizeM() {
        return sizeM;
    }


  public void addEntity(BaseEntity entity, Coordinates coordinates) {
      worldEntities.put(coordinates, entity);
  }

  public BaseEntity getEntity(Coordinates coordinates) {
        return worldEntities.get(coordinates);
  }
}
