package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class BombBrick extends Brick {

    public BombBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.BOMB, x, y);
    }

    public void hit() {
        super.hit();
        engine.explodeLine(wallY);
    };

}
