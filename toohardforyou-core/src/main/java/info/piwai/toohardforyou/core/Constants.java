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

public abstract class Constants {
    
    // scale difference between screen space (pixels) and world space (physics).
    public static float PHYS_UNIT_PER_SCREEN_UNIT = 1 / 26.666667f;
    
    public static final int PADDLE_FREEZE_TIME = 1000;

    // size of world
    public static final float GAME_WIDTH = 520 * PHYS_UNIT_PER_SCREEN_UNIT;
    public static final float GAME_HEIGHT = 600 * PHYS_UNIT_PER_SCREEN_UNIT;
    
    public static final float BOARD_OFFSET_X = 2;
    public static final float BOARD_OFFSET_Y = 60;
    
    public static final int BOARD_PIXEL_WIDTH = 800;
    public static final int BOARD_PIXEL_HEIGHT = 660;
    
    public static final float BOARD_WIDTH = BOARD_PIXEL_WIDTH * PHYS_UNIT_PER_SCREEN_UNIT;
    public static final float BOARD_HEIGHT = BOARD_PIXEL_HEIGHT * PHYS_UNIT_PER_SCREEN_UNIT;
    
    public static final float BRICK_HEIGHT = 20 * Constants.PHYS_UNIT_PER_SCREEN_UNIT;

    public static final float BRICK_WIDTH = 20 * Constants.PHYS_UNIT_PER_SCREEN_UNIT;
    
    public static final boolean DEBUG_DRAW = false;
    
    public static final int WALL_WIDTH = 26;

    public static final int WALL_HEIGHT = 16;
    
    /**
     * TODO Should become dynamic, linked to levels
     */
    public static final int LINE_SCORE_BASE = 400;
    
    /**
     * TODO Should become dynamic, linked to levels
     */
    public static final int BRICK_SCORE_BASE = 400;
    
    public static final int LINE_POWER = 2;
    
    public static final int MAX_BALLS = 10;
    
    public static final int KEY_A = 65;
    public static final int KEY_Q = 81;
    public static final int KEY_D = 68;
    public static final int KEY_Z = 90;
    public static final int KEY_W = 87;
    public static final int KEY_S = 83;

    public static final int KEY_F2 = 113;

    public static final int KEY_P = 80;
    
    private Constants() {
        throw new UnsupportedOperationException();
    }

}
