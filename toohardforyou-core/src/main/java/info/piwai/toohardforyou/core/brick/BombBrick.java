package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class BombBrick extends Brick {

    public BombBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        super(engine, entityEngine, BrickType.BOMB, x, y);
    }

    public void hit() {
        super.hit();
        engine.explodeLine(wallY);
    };

}
