package info.piwai.toohardforyou.core;

import forplay.core.Game;

/**
 * Convenient class to avoid having to implement all {@link Game} methods.
 * The updateRate has a default value of 60.
 */
public abstract class AbstractGame implements Game{

    @Override
    public void init() {
        
    }

    @Override
    public void update(float delta) {
        
    }

    @Override
    public void paint(float alpha) {
        
    }

    @Override
    public int updateRate() {
        return 60;
    }

}
