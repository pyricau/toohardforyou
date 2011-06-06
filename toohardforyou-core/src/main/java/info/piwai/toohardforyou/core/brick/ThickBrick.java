package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.EntityEngine;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entities.SolidBrickEntity;

public class ThickBrick extends Brick {

    private boolean thick = true;
    
    public ThickBrick(TooHardForYouEngine engine, EntityEngine entityEngine, BrickType initialBrickType, int x, int y) {
        super(engine, entityEngine, initialBrickType, x, y);
    }

    public ThickBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        this(engine, entityEngine, BrickType.THICK, x, y);
    }

    public void hit() {
        if (thick) {
            thick = false;
            entityEngine.remove(entity);
            float savedX = entity.getPosX();
            float savedY = entity.getPosY();

            entity = new SolidBrickEntity(entityEngine, BrickType.CLASSIC, this);
            entityEngine.add(entity);
            entity.setPos(savedX, savedY);
        } else {
            super.hit();
        }
    }

}
