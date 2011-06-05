package info.piwai.toohardforyou.core;

import forplay.core.Game;

public class TooHardForYouGame implements Game {

    private Splashscreen splashscreen;

    private TooHardForYouEngine engine;

    public GameScreen delegate;

    @Override
    public void init() {
        splashscreen = new Splashscreen(this);
        delegate = splashscreen;
    }

    public void splashscreenDone() {
        splashscreen = null;
        engine = new TooHardForYouEngine(this);
        delegate = engine;
    }

    @Override
    public final void update(float delta) {
        delegate.update(delta);
    }

    @Override
    public final void paint(float alpha) {
        delegate.paint(alpha);
    }

    /**
     * 25ms per update => 40 updates per second
     */
    @Override
    public final int updateRate() {
        return 25;
    }

}
