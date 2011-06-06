/**
 * Copyright (C) 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
        
        int firstLine = Constants.WALL_HEIGHT - numberOfLines;
        for (int y = firstLine; y < Constants.WALL_HEIGHT; y++) {
            fillLineRandomly(y);
        }
    }

    private void fillLineRandomly(int y) {
        BrickType[] brickTypes = BrickType.values();
        for (int x = 0; x < Constants.WALL_WIDTH; x++) {
            if (random() > 0.5) {
                BrickType brickType = brickTypes[(int) Math.floor(random() * brickTypes.length)];
                addBrick(new Brick(entityEngine, brickType, x, y), x, y);
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

    public boolean isFreeOrUp(int x, int y) {

        if (x < 0 || x >= Constants.WALL_WIDTH || y > Constants.WALL_HEIGHT) {
            return false;
        }

        if (y < 0) {
            return true;
        }

        return isFreeUnchecked(x, y);
    }
    
    public boolean isFreeUnchecked(int x, int y) {
        return bricks[x][y] == null;
    }

    private boolean isFree(int x, int y) {

        if (x < 0 || x >= Constants.WALL_WIDTH || y < 0 || y > Constants.WALL_HEIGHT) {
            return false;
        }

        return isFreeUnchecked(x, y);
    }

    public int checkFullLines() {
        int halfX = Constants.WALL_WIDTH / 2;
        int fullCount = checkPartFullLines(0, halfX);
        fullCount += checkPartFullLines(halfX, Constants.WALL_WIDTH);
        return fullCount;
    }

    public int checkPartFullLines(int fromX, int maxX) {
        int fullCount = 0;

        for (int y = 0; y < Constants.WALL_HEIGHT; y++) {
            if (isFull(fromX, maxX, y)) {
                fullCount++;
                deleteLine(fromX, maxX, y);
            }
        }

        return fullCount;
    }

    private void deleteLine(int fromX, int maxX, int removedY) {
        for (int x = fromX; x < maxX; x++) {
            remove(x, removedY);
        }

        for (int y = removedY; y > 0; y--) {
            int previousY = y - 1;
            for (int x = fromX; x < maxX; x++) {
                Brick brick = bricks[x][previousY];
                bricks[x][y] = brick;
                if (brick != null) {
                    brick.setPos(x, y);
                    bricks[x][previousY] = null;
                }
            }
        }

    }

    private boolean isFull(int fromX, int maxX, int y) {
        for (int x = fromX; x < maxX; x++) {
            if (isFreeUnchecked(x, y)) {
                return false;
            }
        }
        return true;
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

    public void addRandomBottomLine() {
        if (!isFirstLineFree()) {
            full = true;
            return;
        }
        
        int maxY = Constants.WALL_HEIGHT - 1;
        moveLinesUp(maxY);
        
        fillLineRandomly(maxY);
        
    }

    private void moveLinesUp(int maxY) {
        for (int y = 0; y < maxY; y++) {
            int nextY = y + 1;
            for (int x = 0; x < Constants.WALL_WIDTH; x++) {
                Brick brick = bricks[x][nextY];
                bricks[x][y] = brick;
                if (brick != null) {
                    brick.setPos(x, y);
                    bricks[x][nextY] = null;
                }
            }
        }
    }
    
    private boolean isFirstLineFree() {
        for (int x = 0; x < Constants.WALL_WIDTH; x++) {
            if (!isFreeUnchecked(x, 0)) {
                return false;
            }
        }
        return true;
    }

}
