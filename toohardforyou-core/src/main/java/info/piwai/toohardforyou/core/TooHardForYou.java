package info.piwai.toohardforyou.core;


public class TooHardForYou extends GameDelegate  {

    public TooHardForYou() {
        Splashscreen splashscreen = new Splashscreen(this);
        setDelegate(splashscreen);
    }

    public void splashscreenDone() {
        
    }

    


}
