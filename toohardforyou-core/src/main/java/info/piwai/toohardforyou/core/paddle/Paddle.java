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
import info.piwai.toohardforyou.core.Resources;
import info.piwai.toohardforyou.core.entity.DynamicPhysicsEntity;
import info.piwai.toohardforyou.core.entity.EntityEngine;
import info.piwai.toohardforyou.core.util.GameTimer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Paddle extends DynamicPhysicsEntity {

    private final GameTimer frozenTimer = new GameTimer() {
        @Override
        public void run() {
            frozen = false;
        }
    };

    private static final float PADDLE_HEIGHT = 20 * Constants.PHYS_UNIT_PER_SCREEN_UNIT;

    private static final float PADDLE_WIDTH = 100 * Constants.PHYS_UNIT_PER_SCREEN_UNIT;

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

    private boolean frozen = false;

    public Paddle(EntityEngine entityEngine) {
        super(entityEngine, IMAGE, 0, 0, 0);
        maxX = Constants.BOARD_RIGHT - getWidth() / 2;
        minX = Constants.BOARD_LEFT + getWidth() / 2;
        resetPosition();
    }

    public void resetPosition() {
        top = Constants.BOARD_BOTTOM - (getHeight() / 2);
        left = Constants.BOARD_LEFT + Constants.GAME_WIDTH / 2;
        setPos(left, top);
    }

    @Override
    protected Body initPhysicsBody(World world, float x, float y, float angle) {
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
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1.05f;
        body.createFixture(fixtureDef);
        body.setTransform(new Vec2(x, y), angle);
        return body;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!frozen) {
            if (moveLeft) {
                moveTo(left - (speed * delta) / 1000);
            } else if (moveRight) {
                moveTo(left + (speed * delta) / 1000);
            }
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
        return PADDLE_WIDTH;
    }

    @Override
    public float getHeight() {
        return PADDLE_HEIGHT;
    }

    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void freeze() {
        frozen = true;
        frozenTimer.schedule(Constants.PADDLE_FREEZE_TIME);
    }
}
