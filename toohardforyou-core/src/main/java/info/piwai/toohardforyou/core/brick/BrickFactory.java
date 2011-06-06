package info.piwai.toohardforyou.core.brick;

import static forplay.core.ForPlay.*;
import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class BrickFactory {

    private final TooHardForYouEngine engine;

    public BrickFactory(TooHardForYouEngine engine) {
        this.engine = engine;
    }

    public Brick newRandomBrick() {
        return newRandomBrick(0, 0);
    }

    public Brick newRandomBrick(int x, int y) {
        BrickType[] brickTypes = BrickType.values();
        BrickType brickType = brickTypes[(int) Math.floor(random() * brickTypes.length)];
        

        switch (brickType) {
        case UNBREAKABLE:
            return new UnbreakableBrick(engine, x, y);
        case THICK:
            return new ThickBrick(engine, x, y);
        case THICKER:
            return new ThickerBrick(engine, x, y);
        case BONUS:
            return new BonusBrick(engine, x, y);
        case MALUS:
            return new MalusBrick(engine, x, y);
        case BROKEN:
            return new BrokenBrick(engine, x, y);
        case BOMB:
            return new BombBrick(engine, x, y);
        default:
            return new Brick(engine, BrickType.CLASSIC, x, y);
        }

    }

}
