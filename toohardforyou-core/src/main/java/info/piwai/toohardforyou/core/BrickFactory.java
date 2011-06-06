package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.*;

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
        return new Brick(engine, entityEngine, brickType, x, y);
    }


}
