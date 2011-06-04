package info.piwai.toohardforyou.core;


public class TooHardForYou extends GameDelegate  {

    private Splashscreen splashscreen;
    private TooHardForYouEngine engine;

    @Override
    public void init() {
        splashscreen = new Splashscreen(this);
        setDelegate(splashscreen);
    }
    
    public void splashscreenDone() {
        splashscreen = null;
        engine = new TooHardForYouEngine(this);
        setDelegate(engine);
    }


}
