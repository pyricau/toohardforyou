package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class BrokenBrick extends Brick {

    public BrokenBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.BONUS, x, y);
        
        EntityEngine entityEngine = engine.getEntityEngine();
        
        entityEngine.remove(entity);
        float savedX = entity.getPosX();
        float savedY = entity.getPosY();
        
        entity = new BrokenBrickEntity(entityEngine);
        entity.setPos(savedX, savedY);
    }

    public void hit() {
    };

}
