package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.EntityEngine;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entities.BrokenBrickEntity;

public class BrokenBrick extends Brick {

    public BrokenBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        super(engine, entityEngine, BrickType.BONUS, x, y);
        
        entityEngine.remove(entity);
        float savedX = entity.getPosX();
        float savedY = entity.getPosY();
        
        entity = new BrokenBrickEntity(entityEngine);
        entityEngine.add(entity);
        entity.setPos(savedX, savedY);
    }

    public void hit() {
    };

}
