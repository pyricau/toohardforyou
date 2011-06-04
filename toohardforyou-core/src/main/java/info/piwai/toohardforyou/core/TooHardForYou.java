package info.piwai.toohardforyou.core;


public class TooHardForYou extends GameDelegate  {

    private Splashscreen splashscreen;
    private Engine engine;

    @Override
    public void init() {
        splashscreen = new Splashscreen(this);
        setDelegate(splashscreen);
    }
    
    public void splashscreenDone() {
        splashscreen = null;
        engine = new Engine(this);
        setDelegate(engine);
    }


    


}
