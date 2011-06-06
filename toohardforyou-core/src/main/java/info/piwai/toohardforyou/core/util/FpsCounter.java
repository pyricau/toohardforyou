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
package info.piwai.toohardforyou.core.util;

import static forplay.core.ForPlay.currentTime;
import info.piwai.toohardforyou.core.UiTexts;

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
