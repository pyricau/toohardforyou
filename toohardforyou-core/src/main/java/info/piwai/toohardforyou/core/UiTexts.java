/**
 * Copyright (C) 2011 Pierre-Yves Ricau (py.ricau at gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
    private int score;
    private int newGameListeners;

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
            canvas.drawText("Score: " + score, 550, 60);
            canvas.drawText("Balls: " + numberOfBalls, 550, 80);
            canvas.drawText("FPS: " + frameRate, 550, 100);
            canvas.drawText("NGL: " + newGameListeners, 550, 120);
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
    
    public void updateScore(int score) {
        if (this.score != score) {
            textDataChanged = true;
            this.score = score;
        }
    }

    public void resetAll() {
        numberOfBalls = 0;
        score = 0;
        textDataChanged = true;
    }

    public void updateNewGameListeners(int newGameListeners) {
        if (this.newGameListeners != newGameListeners) {
            textDataChanged = true;
            this.newGameListeners = newGameListeners;
        }
    }

}
