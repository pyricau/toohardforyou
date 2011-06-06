package info.piwai.toohardforyou.core.malus;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.paddle.TouchPaddleEntity;

public abstract class AbstractMalus extends TouchPaddleEntity {

    public AbstractMalus(TooHardForYouEngine engine, String image, float x, float y) {
        super(engine, image, x, y);
    }

    @Override
    protected final void outOfGame() {
        activate();
    }

    @Override
    protected final void touchedPaddle() {
    }
    
    protected abstract void activate();

}
