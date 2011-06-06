package info.piwai.toohardforyou.core.malus;

import info.piwai.toohardforyou.core.Resources;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.paddle.Paddle;

public class StaticPaddleMalus extends AbstractMalus {
    
    public final static String IMAGE = Resources.BONUS_PATH + "10.png";
    
    private final Paddle paddle;

    public StaticPaddleMalus(TooHardForYouEngine engine, Paddle paddle, float x, float y) {
        super(engine, IMAGE, x, y);
        this.paddle = paddle;
    }

    @Override
    protected void activate() {
        paddle.freeze();
    }

}
