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

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Ball extends DynamicPhysicsEntity {

    private static final float BALL_RADIUS = 10 * Constants.PHYS_UNIT_PER_SCREEN_UNIT;

    private static final float BALL_DIAMETER = 2 * BALL_RADIUS;

    public final static String IMAGE = Resources.GAME_PATH + "ball.png";

    public Ball(EntityEngine peaWorld, World world, float x, float y, float angle) {
        super(peaWorld, world, x, y, angle);
    }

    @Override
    Body initPhysicsBody(World world, float x, float y, float angle) {
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
    float getWidth() {
        return BALL_DIAMETER;
    }

    @Override
    float getHeight() {
        return BALL_DIAMETER;
    }

    float getRadius() {
        return BALL_RADIUS;
    }

    @Override
    public String getImagePath() {
        return IMAGE;
    }
}
