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

import info.piwai.toohardforyou.core.entities.BrokenBrick;
import info.piwai.toohardforyou.core.entities.Entity;
import info.piwai.toohardforyou.core.entities.SolidBrick;
import info.piwai.toohardforyou.core.entities.SolidBrick.BrokenListener;

public class Brick implements BrokenListener {

    private Entity entity;
    private final EntityEngine entityEngine;

    public Brick(EntityEngine entityEngine, BrickType brickType, int x, int y) {
        this.entityEngine = entityEngine;
        entity = new SolidBrick(entityEngine, brickType, this, convertX(x), convertY(y));
        entityEngine.add(entity);
    }
    
    private float convertX(int x) {
        return Constants.BRICK_WIDTH / 2 + x * Constants.BRICK_WIDTH;
    }
    
    private float convertY(int y) {
        return Constants.BRICK_HEIGHT / 2 +y * Constants.BRICK_HEIGHT;
    }
    
    public void setPos(int x, int y) {
        entity.setPos(convertX(x), convertY(y));
    }
    
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void broken() {
        entityEngine.remove(entity);
        float posX = entity.getPosX();
        float posY = entity.getPosY();
        entity = new BrokenBrick(entityEngine, posX, posY);
        entityEngine.add(entity);
    }

}
