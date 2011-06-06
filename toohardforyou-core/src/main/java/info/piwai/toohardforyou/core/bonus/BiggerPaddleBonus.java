package info.piwai.toohardforyou.core.bonus;

import info.piwai.toohardforyou.core.Resources;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.paddle.Paddle;

public class BiggerPaddleBonus extends AbstractBonus {
    
    public final static String IMAGE = Resources.BONUS_PATH + "8.png";
    
    private final Paddle paddle;

    public BiggerPaddleBonus(TooHardForYouEngine engine, Paddle paddle, float x, float y) {
        super(engine, IMAGE, x, y);
        this.paddle = paddle;
    }

    @Override
    protected void activate() {
    }

}
