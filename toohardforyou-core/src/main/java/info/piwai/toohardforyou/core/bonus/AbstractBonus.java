package info.piwai.toohardforyou.core.bonus;

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.paddle.TouchPaddleEntity;

public abstract class AbstractBonus extends TouchPaddleEntity {

    public AbstractBonus(TooHardForYouEngine engine, String image, float x, float y) {
        super(engine, image, x, y);
    }

    @Override
    protected final void outOfGame() {
        
    }

    @Override
    protected final void touchedPaddle() {
        activate();
    }
    
    protected abstract void activate();

}
