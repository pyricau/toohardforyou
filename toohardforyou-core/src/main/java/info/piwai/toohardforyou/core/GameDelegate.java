package info.piwai.toohardforyou.core;

import forplay.core.Game;

public abstract class GameDelegate implements Game {

    public GameScreen delegate;

    @Override
    public final void update(float delta) {
        delegate.update(delta);
    }

    @Override
    public final void paint(float alpha) {
        delegate.paint(alpha);
    }

    @Override
    public final int updateRate() {
        return 25;
    }

    public void setDelegate(GameScreen delegate) {
        this.delegate = delegate;
    }

}
