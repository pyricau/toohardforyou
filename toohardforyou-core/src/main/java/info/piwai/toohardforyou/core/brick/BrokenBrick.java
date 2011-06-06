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

import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.EntityEngine;

public class BrokenBrick extends Brick {

    public BrokenBrick(TooHardForYouEngine engine, int x, int y) {
        super(engine, BrickType.BONUS, x, y);
        
        EntityEngine entityEngine = engine.getEntityEngine();
        
        entityEngine.remove(entity);
        float savedX = entity.getPosX();
        float savedY = entity.getPosY();
        
        entity = new BrokenBrickEntity(entityEngine);
        entity.setPos(savedX, savedY);
    }

    public void hit() {
    };

}
