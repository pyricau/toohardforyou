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

public class Level {

    private int bricksToNextLevel;

    private int linesToNextLevel;

    private int level;

    private final TooHardForYouEngine engine;

    private final UiTexts uiTexts;

    private int piecePauseInMs;

    private float ballSpeed;

    public Level(TooHardForYouEngine engine, UiTexts uiTexts) {
        this.engine = engine;
        this.uiTexts = uiTexts;
    }

    public void newGame() {
        level = 0;
        computeNextLevel();

    }

    public void onBrickBroken() {
        if (bricksToNextLevel == 0) {
            return;
        }
        bricksToNextLevel--;
        uiTexts.updateBricksToNextLevel(bricksToNextLevel);
        checkNextLevel();
    }

    public void onFullLines(int fullLines) {
        if (linesToNextLevel == 0) {
            return;
        }
        linesToNextLevel-=fullLines;
        if (linesToNextLevel < 0) {
            linesToNextLevel = 0;
        }
        uiTexts.updateLinesToNextLevel(linesToNextLevel);
        checkNextLevel();
    }

    private void checkNextLevel() {
        if (linesToNextLevel == 0 && bricksToNextLevel == 0) {
            computeNextLevel();
            engine.nextLevel();
        }
    }

    private void computeNextLevel() {
        level++;
        linesToNextLevel = 10;
        bricksToNextLevel = 50;

        piecePauseInMs = (int) (200 + 750f / level);
        ballSpeed = 2 + 0.2f * level;

        uiTexts.updateBricksToNextLevel(bricksToNextLevel);
        uiTexts.updateLinesToNextLevel(linesToNextLevel);
        uiTexts.updateLevel(level);
    }

    public int getPiecePauseInMs() {
        return piecePauseInMs;
    }

    public float getBallSpeed() {
        return ballSpeed;
    }

}
