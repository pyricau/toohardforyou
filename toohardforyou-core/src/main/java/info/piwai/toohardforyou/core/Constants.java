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

    // size of world
    public static final float GAME_WIDTH = 520 * PHYS_UNIT_PER_SCREEN_UNIT;
    public static final float GAME_HEIGHT = 600 * PHYS_UNIT_PER_SCREEN_UNIT;
    
    private Constants() {
        throw new UnsupportedOperationException();
    }

}
