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
package info.piwai.toohardforyou.core.entities;

import info.piwai.toohardforyou.core.Constants;
import info.piwai.toohardforyou.core.EntityEngine;
import info.piwai.toohardforyou.core.Resources;
import info.piwai.toohardforyou.core.brick.BrickType;

public class BrokenBrickEntity extends Entity {
    
    public BrokenBrickEntity(EntityEngine entityEngine) {
        super(entityEngine, BrickType.BROKEN.getImagePath(), 0, 0, 0);
    }

    @Override
    public float getWidth() {
        return Constants.BRICK_WIDTH;
    }

    @Override
    public float getHeight() {
        return Constants.BRICK_HEIGHT;
    }

    @Override
    public void initPreLoad(EntityEngine entityEngine) {
        entityEngine.staticLayerBack.add(layer);
    }

    @Override
    public void initPostLoad(EntityEngine entityEngine) {
        
    }

}
