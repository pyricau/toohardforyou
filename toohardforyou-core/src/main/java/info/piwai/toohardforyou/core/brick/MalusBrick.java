package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class MalusBrick extends Brick {

    public MalusBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        super(engine, entityEngine, BrickType.MALUS, x, y);
    }

    public void hit() {
        super.hit();
        
        engine.newMalus(entity.getPosX(), entity.getPosY());
    };

}
