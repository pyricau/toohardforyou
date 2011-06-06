package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class ThickerBrick extends ThickBrick {

    private boolean thicker = true;
    
    public ThickerBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.THICKER, x, y);
    }

    public void hit() {
        if (thicker) {
            thicker = false;
            EntityEngine entityEngine = engine.getEntityEngine();
            entityEngine.remove(entity);
            float savedX = entity.getPosX();
            float savedY = entity.getPosY();

            entity = new SolidBrickEntity(entityEngine, BrickType.THICK, this);
            entity.setPos(savedX, savedY);
        } else {
            super.hit();
        }
    }

}
