package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.EntityEngine;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entities.SolidBrickEntity;

public class ThickerBrick extends ThickBrick {

    private boolean thicker = true;
    
    public ThickerBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        super(engine, entityEngine, BrickType.THICKER, x, y);
    }

    public void hit() {
        if (thicker) {
            thicker = false;
            entityEngine.remove(entity);
            float savedX = entity.getPosX();
            float savedY = entity.getPosY();

            entity = new SolidBrickEntity(entityEngine, BrickType.THICK, this);
            entityEngine.add(entity);
            entity.setPos(savedX, savedY);
        } else {
            super.hit();
        }
    }

}
