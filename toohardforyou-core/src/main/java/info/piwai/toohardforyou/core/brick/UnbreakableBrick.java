package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.EntityEngine;
import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class UnbreakableBrick extends Brick {

    public UnbreakableBrick(TooHardForYouEngine engine, EntityEngine entityEngine, int x, int y) {
        super(engine, entityEngine, BrickType.UNBREAKABLE, x, y);
    }

    public void hit() {
    };

}
