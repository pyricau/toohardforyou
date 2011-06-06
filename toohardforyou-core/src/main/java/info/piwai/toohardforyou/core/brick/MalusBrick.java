package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class MalusBrick extends Brick {

    public MalusBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.MALUS, x, y);
    }

    public void hit() {
        super.hit();

        engine.newMalus(entity.getPosX(), entity.getPosY() - entity.getHeight() / 2);
    };

}
