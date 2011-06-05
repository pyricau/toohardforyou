package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.random;

public class Wall {

    private static final int WALL_WIDTH = 26;

    private static final int WALL_HIDDEN_HEIGHT = 3;

    private static final int WALL_HEIGHT = 16 + WALL_HIDDEN_HEIGHT;

    private final Brick[][] bricks = new Brick[WALL_WIDTH][WALL_HEIGHT];

    private final EntityEngine entityEngine;

    public Wall(EntityEngine entityEngine) {
        this.entityEngine = entityEngine;

    }

    public void fillRandomly(int numberOfLines) {
        clear();
        BrickType[] brickTypes = BrickType.values();
        int firstLine = WALL_HEIGHT - numberOfLines;
        for (int y = firstLine; y < WALL_HEIGHT; y++) {
            for (int x = 0; x < WALL_WIDTH; x++) {
                if (random() > 0.5) {
                    BrickType brickType = brickTypes[(int) Math.floor(random() * brickTypes.length)];
                    bricks[x][y] = new Brick(entityEngine, brickType, x, y - WALL_HIDDEN_HEIGHT);
                }
            }
        }
    }

    private void clear() {
        for (int x = 0; x < WALL_WIDTH; x++) {
            for (int y = 0; y < WALL_HEIGHT; y++) {
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

}
