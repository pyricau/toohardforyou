package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class BonusBrick extends Brick {

    public BonusBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.BONUS, x, y);
    }

    public void hit() {
        super.hit();
        
        engine.newBonus(entity.getPosX(), entity.getPosY() - entity.getHeight() / 2);
    };

}
