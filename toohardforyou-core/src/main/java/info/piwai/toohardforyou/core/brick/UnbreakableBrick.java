package info.piwai.toohardforyou.core.brick;

import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class UnbreakableBrick extends Brick {

    public UnbreakableBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.UNBREAKABLE, x, y);
    }

    public void hit() {
    };

}
