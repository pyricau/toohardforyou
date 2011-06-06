package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class BonusBrick extends Brick {

    public BonusBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        super(engine, entityEngine, BrickType.BONUS, x, y);
    }

    public void hit() {
        super.hit();
        
        engine.newBonus(entity.getPosX(), entity.getPosY());
    };

}
