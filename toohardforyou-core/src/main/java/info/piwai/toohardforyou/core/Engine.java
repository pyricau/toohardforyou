package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;

import java.util.ArrayList;
import java.util.List;

import forplay.core.ForPlay;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.Keyboard;

public class Engine extends AbstractGame implements Keyboard.Listener {

    @SuppressWarnings("unused")
    private final TooHardForYou tooHardForYou;

    private final Paddle paddle;

    private final List<Ball> balls = new ArrayList<Ball>();

    private final Bricks bricks;

    private final GroupLayer engineLayer;

    public Engine(TooHardForYou tooHardForYou) {
        this.tooHardForYou = tooHardForYou;

        Image backgroundImage = assetManager().getImage(Resources.BACKGROUND_IMG);
        graphics().rootLayer().add(graphics().createImageLayer(backgroundImage));

        engineLayer = graphics().createGroupLayer();
        graphics().rootLayer().add(engineLayer);
        engineLayer.setTranslation(2, 0);

        paddle = new Paddle(this, 520, 600);
        bricks = new Bricks(this);

        ForPlay.keyboard().setListener(this);
    }

    public GroupLayer getEngineLayer() {
        return engineLayer;
    }

    @Override
    public void update(float delta) {
        paddle.update(delta);
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

}
