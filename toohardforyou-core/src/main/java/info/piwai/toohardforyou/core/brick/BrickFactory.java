package info.piwai.toohardforyou.core.brick;

import static forplay.core.ForPlay.*;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class BrickFactory {

    private final TooHardForYouEngine engine;
    private final EntityEngine entityEngine;

    public BrickFactory(TooHardForYouEngine engine, EntityEngine entityEngine) {
        this.engine = engine;
        this.entityEngine = entityEngine;
    }

    public Brick newRandomBrick() {
        return newRandomBrick(0, 0);
    }

    public Brick newRandomBrick(int x, int y) {
        BrickType[] brickTypes = BrickType.values();
        BrickType brickType = brickTypes[(int) Math.floor(random() * brickTypes.length)];

        switch (brickType) {
        case UNBREAKABLE:
            return new UnbreakableBrick(engine, entityEngine, x, y);
        case THICK:
            return new ThickBrick(engine, entityEngine, x, y);
        case THICKER:
            return new ThickerBrick(engine, entityEngine, x, y);
        case BONUS:
            return new BonusBrick(engine, entityEngine, x, y);
        case MALUS:
            return new MalusBrick(engine, entityEngine, x, y);
        case BROKEN:
            return new BrokenBrick(engine, entityEngine, x, y);
        case BOMB:
            return new BombBrick(engine, entityEngine, x, y);
        default:
            return new Brick(engine, entityEngine, BrickType.CLASSIC, x, y);
        }

    }

}
