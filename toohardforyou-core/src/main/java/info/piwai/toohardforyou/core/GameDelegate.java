package info.piwai.toohardforyou.core;

import forplay.core.Game;

/**
 * Delegates game handling stuff to a delegate, except for updateRate which
 * returns 60.
 */
public abstract class GameDelegate implements Game {

    public Game delegate;

    @Override
    public final void init() {
        delegate.init();
    }

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
        return 60;
    }

    public void setDelegate(Game delegate) {
        this.delegate = delegate;
    }

}
