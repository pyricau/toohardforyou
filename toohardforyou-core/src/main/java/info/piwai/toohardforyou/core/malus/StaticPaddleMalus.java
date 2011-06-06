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
package info.piwai.toohardforyou.core.malus;

import info.piwai.toohardforyou.core.Resources;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.paddle.Paddle;

public class StaticPaddleMalus extends AbstractMalus {
    
    public final static String IMAGE = Resources.BONUS_PATH + "10.png";
    
    private final Paddle paddle;

    public StaticPaddleMalus(TooHardForYouEngine engine, Paddle paddle, float x, float y) {
        super(engine, IMAGE, x, y);
        this.paddle = paddle;
    }

    @Override
    protected void activate() {
        paddle.freeze();
    }

}