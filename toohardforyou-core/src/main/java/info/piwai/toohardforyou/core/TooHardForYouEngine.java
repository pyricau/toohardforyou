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

import static forplay.core.ForPlay.*;
import info.piwai.toohardforyou.core.ball.Ball;
import info.piwai.toohardforyou.core.bonus.BiggerPaddleBonus;
import info.piwai.toohardforyou.core.brick.BrickFactory;
import info.piwai.toohardforyou.core.entity.EntityEngine;
import info.piwai.toohardforyou.core.malus.StaticPaddleMalus;
import info.piwai.toohardforyou.core.paddle.Paddle;
import info.piwai.toohardforyou.core.piece.Piece;
import info.piwai.toohardforyou.core.piece.PieceFactory;
import info.piwai.toohardforyou.core.util.FpsCounter;
import info.piwai.toohardforyou.core.util.Timer;
import info.piwai.toohardforyou.core.wall.Wall;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.Keyboard;
import forplay.core.Keyboard.Listener;
import forplay.core.Pointer;

public class TooHardForYouEngine implements GameScreen, Pointer.Listener, Listener {

    private final Paddle paddle;

    private final UiTexts uiTexts;

    private final FpsCounter fpsCounter;

    private final List<Ball> balls = new ArrayList<Ball>();
    
    private final List<NewGameListener> newGamelisteners = new ArrayList<NewGameListener>();

    private final Wall wall;

    private final PieceFactory pieceFactory;

    private Piece piece;

    private int score;

    private final EntityEngine entityEngine;

    public TooHardForYouEngine(TooHardForYouGame game) {

        GroupLayer worldlayer = buildWorldLayer();
        Vec2 gravity = new Vec2(0.0f, 0.1f);
        entityEngine = new EntityEngine(worldlayer, gravity, Constants.GAME_WIDTH, Constants.GAME_HEIGHT, Constants.PHYS_UNIT_PER_SCREEN_UNIT, Constants.DEBUG_DRAW);

        uiTexts = new UiTexts();
        fpsCounter = new FpsCounter(uiTexts);

        createBoundaries();

        paddle = new Paddle(entityEngine);

        BrickFactory brickFactory = new BrickFactory(this);

        wall = new Wall(brickFactory);

        pieceFactory = new PieceFactory(this, brickFactory, wall);

        // hook up our pointer listener
        pointer().setListener(this);
        keyboard().setListener(this);

        newGame();
    }

    private void createBoundaries() {
        World world = entityEngine.getWorld();
        // create the ceil
        Body ceil = world.createBody(new BodyDef());
        PolygonShape ceilShape = new PolygonShape();
        ceilShape.setAsEdge(new Vec2(0, 0), new Vec2(Constants.GAME_WIDTH, 0));
        ceil.createFixture(ceilShape, 0.0f);
        // create the walls
        Body wallLeft = world.createBody(new BodyDef());
        PolygonShape wallLeftShape = new PolygonShape();
        wallLeftShape.setAsEdge(new Vec2(0, 0), new Vec2(0, Constants.GAME_HEIGHT));
        wallLeft.createFixture(wallLeftShape, 0.0f);
        Body wallRight = world.createBody(new BodyDef());
        PolygonShape wallRightShape = new PolygonShape();
        wallRightShape.setAsEdge(new Vec2(Constants.GAME_WIDTH, 0), new Vec2(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        wallRight.createFixture(wallRightShape, 0f);
    }

    private void newGame() {
        
        ArrayList<NewGameListener> listenersToRemove = new ArrayList<NewGameListener>();
        for(NewGameListener listener : new ArrayList<NewGameListener>(newGamelisteners)) {
            boolean remove = listener.onNewGame();
            if (remove) {
                listenersToRemove.add(listener);
            }
        }
        
        newGamelisteners.removeAll(listenersToRemove);
        listenersToRemove.clear();
        
        paddle.resetPosition();

        score = 0;

        uiTexts.resetAll();

        if (piece != null) {
            piece.destroy();
        }
        piece = pieceFactory.newRandomPiece();

        for (Ball ball : balls) {
            entityEngine.remove(ball);
        }
        balls.clear();
        uiTexts.updateNumberOfBalls(balls.size());

        wall.fillRandomly(5);

        createBallOnPaddle();
    }

    private void createBallsOnPaddle(int numberOfBalls) {
        for (int i = 0; i < numberOfBalls; i++) {
            createBallOnPaddle();
        }
    }

    private void createBallOnPaddle() {
        if (balls.size() < Constants.MAX_BALLS) {
            Ball ball = new Ball(this, paddle.getPosX(), paddle.getPosY() - paddle.getHeight());
            Vec2 velocity = new Vec2(random() - 0.5f, random() - 1);
            velocity.normalize();
            velocity.mulLocal(5);
            ball.getBody().setLinearVelocity(velocity);
            balls.add(ball);
            uiTexts.updateNumberOfBalls(balls.size());
        }
    }

    // create our world layer (scaled to "world space")
    // main layer that holds the world. note: this gets scaled to world space
    private GroupLayer buildWorldLayer() {
        Image backgroundImage = assetManager().getImage(Resources.BACKGROUND_IMG);
        graphics().rootLayer().add(graphics().createImageLayer(backgroundImage));

        GroupLayer worldLayer = graphics().createGroupLayer();
        worldLayer.setTranslation(2, 0);
        worldLayer.setScale(1f / Constants.PHYS_UNIT_PER_SCREEN_UNIT);
        graphics().rootLayer().add(worldLayer);
        return worldLayer;
    }

    @Override
    public void update(float delta) {
        entityEngine.update(delta);
        Timer.update();
        piece.update(delta);
    }

    @Override
    public void paint(float delta) {
        entityEngine.paint(delta);
        fpsCounter.update();
        uiTexts.mayRedrawTexts();
    }

    @Override
    public void onPointerStart(float x, float y) {
    }

    @Override
    public void onPointerEnd(float x, float y) {

    }

    @Override
    public void onPointerDrag(float x, float y) {

    }

    @Override
    public void onKeyDown(int keyCode) {
        switch (keyCode) {
        case Constants.KEY_A:
        case Constants.KEY_Q:
            piece.moveLeft(true);
            break;
        case Constants.KEY_D:
            piece.moveRight(true);
            break;
        case Constants.KEY_Z:
        case Constants.KEY_W:
            piece.rotate();
            break;
        case Constants.KEY_S:
            piece.moveDown(true);
            break;
        case Keyboard.KEY_SPACE:
            piece.dropDown();
            break;
        case Keyboard.KEY_LEFT:
            paddle.moveLeft(true);
            break;
        case Keyboard.KEY_RIGHT:
            paddle.moveRight(true);
            break;
        }
    }

    @Override
    public void onKeyUp(int keyCode) {
        switch (keyCode) {
        case Constants.KEY_A:
        case Constants.KEY_Q:
            piece.moveLeft(false);
            break;
        case Constants.KEY_D:
            piece.moveRight(false);
            break;
        case Constants.KEY_S:
            piece.moveDown(false);
            break;
        case Keyboard.KEY_LEFT:
            paddle.moveLeft(false);
            break;
        case Keyboard.KEY_RIGHT:
            paddle.moveRight(false);
            break;
        }
    }

    private void incrementScore(int increment) {
        score += increment;
        uiTexts.updateScore(score);
    }

    public void ballOut(Ball ball) {
        balls.remove(ball);
        uiTexts.updateNumberOfBalls(balls.size());
        entityEngine.remove(ball);

        if (balls.size() == 0) {
            wall.addRandomBottomLine();

            if (wall.isFull()) {
                gameOver();
            } else {
                piece.moveUpIfContact();
                createBallOnPaddle();
            }
        }
    }

    public void pieceFrozen() {
        if (wall.isFull()) {
            gameOver();
        } else {
            int fullLines = wall.checkFullLines();

            if (fullLines > 0) {
                createBallsOnPaddle(fullLines - 1);

                incrementScore((int) (Math.pow(fullLines, Constants.LINE_POWER) * Constants.LINE_SCORE_BASE));
            }

            piece = pieceFactory.newRandomPiece();
        }
    }

    private void gameOver() {
        newGame();
    }

    public void brickBroken() {
        incrementScore(Constants.BRICK_SCORE_BASE);
    }

    public void newMalus(float x, float y) {
        StaticPaddleMalus malus = new StaticPaddleMalus(this, paddle, x, y);
        
        Vec2 velocity = new Vec2(0, 3);
        malus.getBody().setLinearVelocity(velocity);
    }

    public void newBonus(float x, float y) {
        BiggerPaddleBonus bonus = new BiggerPaddleBonus(this, paddle, x, y);
        
        Vec2 velocity = new Vec2(0, 3);
        bonus.getBody().setLinearVelocity(velocity);
    }
    
    public void addNewGameListener(NewGameListener newGamelistener) {
        newGamelisteners.add(newGamelistener);
    }
    
    public void removeNewGameListener(NewGameListener newGamelistener) {
        newGamelisteners.remove(newGamelistener);
    }

    public void explodeLine(int lineY) {
        /*
         * Should the whole line (two halfs) be destroyed ? Or maybe only a part
         * around the bomb ? But then we should recompute to check if no
         * complete line appeared.
         */
    }

    public EntityEngine getEntityEngine() {
        return entityEngine;
    }
    
    

}
