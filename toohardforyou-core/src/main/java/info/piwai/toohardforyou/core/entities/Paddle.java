/**
 * Copyright 2011 The ForPlay Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package info.piwai.toohardforyou.core.entities;

import info.piwai.toohardforyou.core.EntityEngine;
import info.piwai.toohardforyou.core.Resources;
import info.piwai.toohardforyou.core.TooHardForYouEngine;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Paddle extends DynamicPhysicsEntity {

    public final static String IMAGE = Resources.GAME_PATH + "paddle.png";

    private boolean moveLeft;

    private boolean moveRight;

    /**
     * in px/s
     */
    private float speed = 20;

    private float maxX;

    private float top;

    private float left;

    private float minX;

    public Paddle(EntityEngine peaWorld, World world, float x, float y, float angle) {
        super(peaWorld, world, x, y, angle);
        top = TooHardForYouEngine.HEIGHT - (getHeight() / 2);
        left = TooHardForYouEngine.WIDTH / 2;
        maxX = TooHardForYouEngine.WIDTH - getWidth() / 2;
        minX = getWidth() / 2;
        setPos(left, top);
    }

    @Override
    Body initPhysicsBody(World world, float x, float y, float angle) {
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.position = new Vec2(0, 0);
        Body body = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        Vec2[] polygon = new Vec2[4];
        polygon[0] = new Vec2(-getWidth() / 2f, -getHeight() / 2f);
        polygon[1] = new Vec2(getWidth() / 2f, -getHeight() / 2f);
        polygon[2] = new Vec2(getWidth() / 2f, getHeight() / 2f);
        polygon[3] = new Vec2(-getWidth() / 2f, getHeight() / 2f);
        polygonShape.set(polygon, polygon.length);
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 1.2f;
        body.createFixture(fixtureDef);
        body.setTransform(new Vec2(x, y), angle);
        return body;
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        if (moveLeft) {
            moveTo(left - (speed * delta) / 1000);
        } else if (moveRight) {
            moveTo(left + (speed * delta) / 1000);
        }

    }
    
    public void moveTo(float x) {
        if (x < minX) {
            x = minX;
        } else if (x > maxX) {
            x = maxX;
        }

        left = x;
        
        setPos(x, top);

    }

    @Override
    public float getWidth() {
        return 100 * TooHardForYouEngine.physUnitPerScreenUnit;
    }

    @Override
    public float getHeight() {
        return 20 * TooHardForYouEngine.physUnitPerScreenUnit;
    }

    @Override
    public String getImagePath() {
        return IMAGE;
    }

    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
}
