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
package info.piwai.toohardforyou.core.paddle;

import info.piwai.toohardforyou.core.Constants;
import info.piwai.toohardforyou.core.NewGameListener;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.entity.DynamicPhysicsEntity;
import info.piwai.toohardforyou.core.entity.PhysicsEntity;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public abstract class TouchPaddleEntity extends DynamicPhysicsEntity implements PhysicsEntity.HasContactListener, NewGameListener{

    private static final float RADIUS = 10 * Constants.PHYS_UNIT_PER_SCREEN_UNIT;

    private static final float MAX_POS_Y = Constants.GAME_HEIGHT + RADIUS;

    private static final float DIAMETER = 2 * RADIUS;

    private final TooHardForYouEngine engine;

    public TouchPaddleEntity(TooHardForYouEngine engine, String image, float x, float y) {
        super(engine.getEntityEngine(), image, x, y - RADIUS, 0);
        this.engine = engine;
        engine.addNewGameListener(this);
    }

    @Override
    protected Body initPhysicsBody(World world, float x, float y, float angle) {
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0, 0);
        Body body = world.createBody(bodyDef);
        
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = getRadius();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;
        circleShape.m_p.set(0, 0);
        body.createFixture(fixtureDef);
        body.setLinearDamping(0f);
        body.setTransform(new Vec2(x, y), angle);
        return body;
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        if (getPosY() > MAX_POS_Y) {
            destroy();
            outOfGame();
        }
    }
    
    public void destroy() {
        engine.removeNewGameListener(this);
        engine.getEntityEngine().remove(this);
    }
    
    protected abstract void outOfGame();
    
    @Override
    public void contact(PhysicsEntity other) {
        if (other instanceof Paddle) {
            destroy();
            touchedPaddle();
        }
    }

    protected abstract void touchedPaddle();

    @Override
    public float getWidth() {
        return DIAMETER;
    }

    @Override
    public float getHeight() {
        return DIAMETER;
    }

    private float getRadius() {
        return RADIUS;
    }
    
    @Override
    public boolean onNewGame() {
        destroy();
        return true;
    }

}
