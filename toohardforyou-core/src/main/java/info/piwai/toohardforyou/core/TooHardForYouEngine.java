package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import static forplay.core.ForPlay.keyboard;
import static forplay.core.ForPlay.pointer;
import static forplay.core.ForPlay.random;
import info.piwai.toohardforyou.core.entities.Ball;
import info.piwai.toohardforyou.core.entities.Paddle;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.Keyboard;
import forplay.core.Keyboard.Listener;
import forplay.core.Pointer;

public class TooHardForYouEngine extends EntityEngine implements Pointer.Listener, Listener {

    private final Paddle paddle;

    public TooHardForYouEngine(TooHardForYouGame game) {
        super(buildWorldLayer());

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

        paddle = new Paddle(this, world, 0, 0, 0);
        add(paddle);
        
        new Timer() {
            @Override
            public void run() {
                Ball ball = new Ball(TooHardForYouEngine.this, world, random() * Constants.GAME_WIDTH, random() * Constants.GAME_HEIGHT, 0);
                ball.getBody().setLinearVelocity(new Vec2((-1 + random()) * 3, (-1 + random()) * 3));
                add(ball);
            }
        }.scheduleRepeating(500);
        
        // hook up our pointer listener
        pointer().setListener(this);
        keyboard().setListener(this);
    }
    
    

    // create our world layer (scaled to "world space")
    // main layer that holds the world. note: this gets scaled to world space
    private static GroupLayer buildWorldLayer() {
        Image backgroundImage = assetManager().getImage(Resources.BACKGROUND_IMG);
        graphics().rootLayer().add(graphics().createImageLayer(backgroundImage));

        GroupLayer worldLayer = graphics().createGroupLayer();
        worldLayer.setTranslation(2, 0);
        worldLayer.setScale(1f / Constants.PHYS_UNIT_PER_SCREEN_UNIT);
        graphics().rootLayer().add(worldLayer);
        return worldLayer;
    }

    @Override
    protected Vec2 getGravity() {
        return new Vec2(0.0f, 0.0f);
    }

    @Override
    protected float getWidth() {
        return Constants.GAME_WIDTH;
    }

    @Override
    protected float getHeight() {
        return Constants.GAME_HEIGHT;
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
    public void update(float delta) {
        super.update(delta);
        Timer.update();
    }

    @Override
    public void onKeyDown(int keyCode) {
        switch (keyCode) {
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
        case Keyboard.KEY_LEFT:
            paddle.moveLeft(false);
            break;
        case Keyboard.KEY_RIGHT:
            paddle.moveRight(false);
            break;
        }
    }



    @Override
    protected float getPhysicalUnitPerScreenUnit() {
        return Constants.PHYS_UNIT_PER_SCREEN_UNIT;
    }

}
