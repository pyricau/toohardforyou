package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;

public class Paddle {

    private final Engine engine;

    private final ImageLayer rightLayer;

    private final ImageLayer leftLayer;

    private GroupLayer paddleLayer;

    private float left;
    private final int top;

    private Image centerImage;

    private int paddleHeight;

    private int paddleWidth;

    private boolean moveLeft;

    private boolean moveRight;

    /**
     * in px/s
     */
    private float speed = 200;

    private int maxX;

    public Paddle(Engine engine, int gameWidth, int gameHeight) {
        this.engine = engine;

        paddleLayer = graphics().createGroupLayer();

        Image leftImage = assetManager().getImage(Resources.PADDLE_LEFT_IMG);
        leftLayer = graphics().createImageLayer(leftImage);
        paddleLayer.add(leftLayer);

        Image rightImage = assetManager().getImage(Resources.PADDLE_RIGHT_IMG);
        rightLayer = graphics().createImageLayer(rightImage);
        paddleLayer.add(rightLayer);

        centerImage = assetManager().getImage(Resources.PADDLE_CENTER_IMG);

        engine.getEngineLayer().add(paddleLayer);

        int initialWidth = 3;

        paddleHeight = leftImage.height();

        paddleWidth = leftImage.width() + initialWidth * centerImage.width() + rightImage.width();

        maxX = gameWidth - paddleWidth;

        top = gameHeight - paddleHeight;

        left = (float) ((gameWidth / 2.) - ((leftImage.width() + initialWidth * centerImage.width() + rightImage.width()) / 2.));

        paddleLayer.setTranslation(left, top);

        for (int i = 0; i < initialWidth; i++) {
            ImageLayer centerLayer = graphics().createImageLayer(centerImage);
            paddleLayer.add(centerLayer);
            centerLayer.setTranslation(leftImage.width() + i * centerImage.width(), 0);
        }

        rightLayer.setTranslation(leftImage.width() + initialWidth * centerImage.width(), 0);
    }

    public void update(float delta) {

        if (moveLeft) {
            moveTo(left - (speed * delta) / 1000);
        } else if (moveRight) {
            moveTo(left + (speed * delta) / 1000);
        }

    }

    public void moveTo(float x) {

        if (x < 0) {
            x = 0;
        } else if (x > maxX) {
            x = maxX;
        }

        left = x;

        paddleLayer.setTranslation(left, top);
    }

    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

}
