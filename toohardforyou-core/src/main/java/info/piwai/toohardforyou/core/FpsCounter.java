package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.currentTime;

public class FpsCounter {
    
    private static final int FPS_COUNTER_MAX = 300;

    private int frameCounter = 0;
    private double frameCounterStart = 0;
    
    private final UiTexts uiTexts;

    public FpsCounter(UiTexts uiTexts) {
        this.uiTexts = uiTexts;
    }

    public void update() {
        if (frameCounter == 0) {
            frameCounterStart = currentTime();
        }

        frameCounter++;
        if (frameCounter == FPS_COUNTER_MAX) {
            int frameRate = (int) (frameCounter / ((currentTime() - frameCounterStart) / 1000.0));
            uiTexts.updateFrameRate(frameRate);
            frameCounter = 0;
        }
    }
    
}
