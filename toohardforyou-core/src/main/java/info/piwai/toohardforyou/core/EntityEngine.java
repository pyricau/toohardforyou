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

import static forplay.core.ForPlay.graphics;
import info.piwai.toohardforyou.core.entities.Entity;
import info.piwai.toohardforyou.core.entities.PhysicsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import forplay.core.CanvasLayer;
import forplay.core.DebugDrawBox2D;
import forplay.core.GroupLayer;

/**
 * Based on the forplay-peaphysics example (Copyright 2011 The ForPlay Authors),
 * which is licensed under the Apache License, Version 2.0.
 */
public abstract class EntityEngine implements GameScreen, ContactListener {
    public GroupLayer staticLayerBack;
    public GroupLayer dynamicLayer;
    public GroupLayer staticLayerFront;

    // box2d object containing physics world
    protected World world;

    private List<Entity> entities = new ArrayList<Entity>(0);
    private HashMap<Body, PhysicsEntity> bodyEntityLUT = new HashMap<Body, PhysicsEntity>();
    private Stack<Contact> contacts = new Stack<Contact>();

    protected boolean showDebugDraw = false;
    private DebugDrawBox2D debugDraw;

    public EntityEngine(GroupLayer scaledLayer) {
        staticLayerBack = graphics().createGroupLayer();
        scaledLayer.add(staticLayerBack);
        dynamicLayer = graphics().createGroupLayer();
        scaledLayer.add(dynamicLayer);
        staticLayerFront = graphics().createGroupLayer();
        scaledLayer.add(staticLayerFront);

        // create the physics world
        world = new World(getGravity(), true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        world.setContactListener(this);

        if (showDebugDraw) {
            CanvasLayer canvasLayer = graphics().createCanvasLayer((int) (getWidth() / getPhysicalUnitPerScreenUnit()), (int) (getHeight() / getPhysicalUnitPerScreenUnit()));
            graphics().rootLayer().add(canvasLayer);
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(canvasLayer);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit | DebugDraw.e_jointBit | DebugDraw.e_aabbBit);
            debugDraw.setCamera(0, 0, 1f / getPhysicalUnitPerScreenUnit());
            world.setDebugDraw(debugDraw);
        }

    }

    protected abstract float getPhysicalUnitPerScreenUnit();

    protected abstract float getWidth();

    protected abstract float getHeight();

    protected abstract Vec2 getGravity();

    @Override
    public void update(float delta) {
        for (Entity e : entities) {
            e.update(delta);
        }
        // the step delta is fixed so box2d isn't affected by framerate
        world.step(0.033f, 10, 10);
        processContacts();
    }

    @Override
    public void paint(float delta) {
        if (showDebugDraw) {
            debugDraw.getCanvas().canvas().clear();
            world.drawDebugData();
        }
        for (Entity e : entities) {
            e.paint(delta);
        }
    }

    public void add(Entity entity) {
        entities.add(entity);
        if (entity instanceof PhysicsEntity) {
            PhysicsEntity physicsEntity = (PhysicsEntity) entity;
            bodyEntityLUT.put(physicsEntity.getBody(), physicsEntity);
        }
    }

    // handle contacts out of physics loop
    public void processContacts() {
        while (!contacts.isEmpty()) {
            Contact contact = contacts.pop();

            // handle collision
            PhysicsEntity entityA = bodyEntityLUT.get(contact.m_fixtureA.m_body);
            PhysicsEntity entityB = bodyEntityLUT.get(contact.m_fixtureB.m_body);

            if (entityA != null && entityB != null) {
                if (entityA instanceof PhysicsEntity.HasContactListener) {
                    ((PhysicsEntity.HasContactListener) entityA).contact(entityB);
                }
                if (entityB instanceof PhysicsEntity.HasContactListener) {
                    ((PhysicsEntity.HasContactListener) entityB).contact(entityA);
                }
            }
        }
    }

    // Box2d's begin contact
    @Override
    public void beginContact(Contact contact) {
        contacts.push(contact);
    }

    // Box2d's end contact
    @Override
    public void endContact(Contact contact) {
    }

    // Box2d's pre solve
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    // Box2d's post solve
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
