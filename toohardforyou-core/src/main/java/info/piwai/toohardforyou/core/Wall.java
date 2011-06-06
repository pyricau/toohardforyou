package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.random;

public class Wall {

    private final Brick[][] bricks = new Brick[Constants.WALL_WIDTH][Constants.WALL_HEIGHT];

    private final EntityEngine entityEngine;
    
    private boolean full;

    public Wall(EntityEngine entityEngine) {
        this.entityEngine = entityEngine;

    }

    public void fillRandomly(int numberOfLines) {
        full = false;
        clear();
        BrickType[] brickTypes = BrickType.values();
        int firstLine = Constants.WALL_HEIGHT - numberOfLines;
        for (int y = firstLine; y < Constants.WALL_HEIGHT; y++) {
            for (int x = 0; x < Constants.WALL_WIDTH; x++) {
                if (random() > 0.5) {
                    BrickType brickType = brickTypes[(int) Math.floor(random() * brickTypes.length)];
                    addBrick(new Brick(entityEngine, brickType, x, y), x, y);
                }
            }
        }
    }

    private void clear() {
        for (int x = 0; x < Constants.WALL_WIDTH; x++) {
            for (int y = 0; y < Constants.WALL_HEIGHT; y++) {
                remove(x, y);
            }
        }
    }

    private void remove(int x, int y) {
        Brick brick = bricks[x][y];
        if (brick != null) {
            bricks[x][y] = null;
            entityEngine.remove(brick.getEntity());
        }
    }

    public boolean isFree(int x, int y) {
        if (x < 0 || x >= Constants.WALL_WIDTH || y < 0 || y > Constants.WALL_HEIGHT) {
            return false;
        }
        return bricks[x][y] == null;
    }

    public void addBrick(Brick brick, int x, int y) {
        if (isFree(x, y)) {
            bricks[x][y] = brick;
        } else {
            entityEngine.remove(brick.getEntity());
            full = true;
        }
    }
    
    public boolean isFull() {
        return full;
    }

}
