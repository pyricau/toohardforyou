package info.piwai.toohardforyou.core;

import info.piwai.toohardforyou.core.entities.BrokenBrick;
import info.piwai.toohardforyou.core.entities.Entity;
import info.piwai.toohardforyou.core.entities.SolidBrick;
import info.piwai.toohardforyou.core.entities.SolidBrick.BrokenListener;

public class Brick implements BrokenListener {

    private Entity entity;
    private final EntityEngine entityEngine;

    public Brick(EntityEngine entityEngine, BrickType brickType, float x, float y) {
        this.entityEngine = entityEngine;
        entity = new SolidBrick(entityEngine, brickType, this, Constants.BRICK_WIDTH / 2 + x * Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT / 2 +y * Constants.BRICK_HEIGHT);
        entityEngine.add(entity);
        
    }
    
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void broken() {
        entityEngine.remove(entity);
        float posX = entity.getPosX();
        float posY = entity.getPosY();
        entity = new BrokenBrick(entityEngine, posX, posY);
        entityEngine.add(entity);
    }

}
