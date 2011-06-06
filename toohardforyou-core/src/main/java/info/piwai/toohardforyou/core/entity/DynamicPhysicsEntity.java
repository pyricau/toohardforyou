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
package info.piwai.toohardforyou.core.entity;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Based on the forplay-peaphysics example (Copyright 2011 The ForPlay Authors),
 * which is licensed under the Apache License, Version 2.0.
 */
public abstract class DynamicPhysicsEntity extends Entity implements PhysicsEntity {
  // for calculating interpolation
  private float prevX, prevY, prevA;
  private Body body;
  
  public DynamicPhysicsEntity(EntityEngine entityEngine, String imagePath, float x, float y, float angle) {
    super(entityEngine, imagePath, x, y, angle);
    body = initPhysicsBody(entityEngine.getWorld(), x, y, angle);
    setPos(x, y);
    setAngle(angle);
  }

  protected abstract Body initPhysicsBody(World world, float x, float y, float angle);

  @Override
  public void paint(float alpha) {
    // interpolate based on previous state
    float x = (body.getPosition().x * alpha) + (prevX * (1f - alpha));
    float y = (body.getPosition().y * alpha) + (prevY * (1f - alpha));
    float a = (body.getAngle() * alpha) + (prevA * (1f - alpha));
    layer.setTranslation(x, y);
    layer.setRotation(a);
  }

  @Override
  public void update(float delta) {
    // store state for interpolation in paint()
    prevX = body.getPosition().x;
    prevY = body.getPosition().y;
    prevA = body.getAngle();
  }

  public void initPreLoad(final EntityEngine entityEngine) {
    entityEngine.dynamicLayer.add(layer);
  }

  public void initPostLoad(final EntityEngine entityEngine) {
  }

  public void setLinearVelocity(float x, float y) {
    body.setLinearVelocity(new Vec2(x, y));
  }

  public void setAngularVelocity(float w) {
    body.setAngularVelocity(w);
  }

  @Override
  public void setPos(float x, float y) {
    super.setPos(x, y);
    body.setTransform(new Vec2(x, y), body.getAngle());
    prevX = x;
    prevY = y;
  }

  @Override
  public void setAngle(float a) {
    super.setAngle(a);
    body.setTransform(body.getPosition(), a);
    prevA = a;
  }

  @Override
  public Body getBody() {
    return body;
  }
}
