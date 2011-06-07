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
package info.piwai.toohardforyou.core.brick;

import static forplay.core.ForPlay.*;
import info.piwai.toohardforyou.core.TooHardForYouEngine;

public class BrickFactory {

    private final TooHardForYouEngine engine;

    public BrickFactory(TooHardForYouEngine engine) {
        this.engine = engine;
    }

    public Brick newRandomBrick() {
        return newRandomBrick(0, 0);
    }

    public Brick newRandomBrick(int x, int y) {
        BrickType[] brickTypes = BrickType.values();

        float random = random();

        BrickType brickType;
        if (random < 0.9) {
            brickType = BrickType.CLASSIC;
        } else if (random<0.91){
            brickType = BrickType.UNBREAKABLE;
        } else if (random<0.93){
            brickType = BrickType.THICK;
        } else if (random<0.95){
            brickType = BrickType.THICKER;
        } else if (random<0.97){
            brickType = BrickType.BONUS;
        } else if (random<0.99){
            brickType = BrickType.MALUS;
        } else {
            brickType = BrickType.BOMB;
        }

        switch (brickType) {
        case UNBREAKABLE:
            return new UnbreakableBrick(engine, x, y);
        case THICK:
            return new ThickBrick(engine, x, y);
        case THICKER:
            return new ThickerBrick(engine, x, y);
        case BONUS:
            return new BonusBrick(engine, x, y);
        case MALUS:
            return new MalusBrick(engine, x, y);
        case BROKEN:
            return new BrokenBrick(engine, x, y);
        case BOMB:
            return new BombBrick(engine, x, y);
        default:
            return new Brick(engine, BrickType.CLASSIC, x, y);
        }

    }

}
