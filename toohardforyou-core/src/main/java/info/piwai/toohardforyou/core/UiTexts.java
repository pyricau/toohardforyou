package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import forplay.core.Canvas;
import forplay.core.CanvasLayer;
import forplay.core.Image;

public class UiTexts {

    private boolean textDataChanged = true;
    private CanvasLayer textLayer;
    
    private int numberOfBalls;
    private int frameRate;

    public UiTexts() {
        Image backgroundImage = assetManager().getImage(Resources.BACKGROUND_IMG);
        textLayer = graphics().createCanvasLayer(backgroundImage.width(), backgroundImage.height());

        graphics().rootLayer().add(textLayer);
    }

    public void mayRedrawTexts() {
        if (textDataChanged) {
            textDataChanged = false;
            Canvas canvas = textLayer.canvas();
            canvas.clear();
            canvas.drawText("Balls: " + numberOfBalls, 550, 50);
            canvas.drawText("FPS: " + frameRate, 550, 70);
        }
    }

    public void updateNumberOfBalls(int numberOfBalls) {
        if (this.numberOfBalls != numberOfBalls) {
            textDataChanged = true;
            this.numberOfBalls = numberOfBalls;
        }
    }

    public void updateFrameRate(int frameRate) {
        if (this.frameRate != frameRate) {
            textDataChanged = true;
            this.frameRate = frameRate;
        }
    }

}
