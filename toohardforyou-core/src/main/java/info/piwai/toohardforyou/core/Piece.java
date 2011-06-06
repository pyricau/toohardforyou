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

import static forplay.core.ForPlay.currentTime;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private final Wall wall;

    private int x;
    private int y;

    private List<BrickHolder> bricks = new ArrayList<BrickHolder>();

    private boolean moveLeft;

    private boolean moveRight;

    private boolean moveDown;

    private double lastUserMove;

    private double lastMoveDown;

    private final TooHardForYouEngine engine;

    public Piece(TooHardForYouEngine engine, Wall wall) {
        this.engine = engine;
        this.wall = wall;
        x = Constants.WALL_WIDTH / 2;
        y = 0;
        lastUserMove = 0;
        lastMoveDown = currentTime();
    }

    public void update(float delta) {

        double now = currentTime();

        if (now - lastUserMove > 75) {
            int deltaX = 0;
            int deltaY = 0;
            deltaX += moveLeft ? -1 : 0;
            deltaX += moveRight ? 1 : 0;
            deltaY += moveDown ? 1 : 0;
            if (deltaX != 0 || deltaY != 0) {
                int newX = x + deltaX;
                int newY = y + deltaY;
                boolean free = true;
                for (BrickHolder brickHolder : bricks) {
                    if (!wall.isFree(newX + brickHolder.x, newY + brickHolder.y)) {
                        free = false;
                        break;
                    }
                }
                if (free) {
                    lastUserMove = now;
                    moveTo(newX, newY);
                }
            }
        }

        if (now - lastMoveDown > 1000) {
            int newY = y + 1;
            boolean free = true;
            for (BrickHolder brickHolder : bricks) {
                if (!wall.isFree(x + brickHolder.x, newY + brickHolder.y)) {
                    free = false;
                    break;
                }
            }
            if (free) {
                lastMoveDown = now;
                moveTo(x, newY);
            } else {
                freeze();
            }
        }
    }

    private void freeze() {
        for (BrickHolder brickHolder : bricks) {
            wall.addBrick(brickHolder.brick, x + brickHolder.x, y + brickHolder.y);
        }
        bricks.clear();
        engine.pieceFrozen();
    }

    private void moveTo(int newX, int newY) {
        x = newX;
        y = newY;
        for (BrickHolder brickHolder : bricks) {
            brickHolder.brick.setPos(x + brickHolder.x, y + brickHolder.y);
        }
    }

    public void add(BrickHolder brickHolder) {
        brickHolder.brick.setPos(x + brickHolder.x, y + brickHolder.y);
        bricks.add(brickHolder);
    }

    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void moveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void destroy() {
        for (BrickHolder brickHolder : bricks) {
            engine.remove(brickHolder.brick.getEntity());
        }
    }

    public void rotate() {

    }

}
