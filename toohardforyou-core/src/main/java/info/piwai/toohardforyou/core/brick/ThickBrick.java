package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class ThickBrick extends Brick {

    private boolean thick = true;
    
    public ThickBrick(TooHardForYouEngine engine, BrickType initialBrickType, int x, int y) {
        super(engine, initialBrickType, x, y);
    }

    public ThickBrick(TooHardForYouEngine engine,int x, int y) {
        this(engine, BrickType.THICK, x, y);
    }

    public void hit() {
        if (thick) {
            thick = false;
            EntityEngine entityEngine = engine.getEntityEngine();
            entityEngine.remove(entity);
            float savedX = entity.getPosX();
            float savedY = entity.getPosY();

            entity = new SolidBrickEntity(entityEngine, BrickType.CLASSIC, this);
            entity.setPos(savedX, savedY);
        } else {
            super.hit();
        }
    }

}
