package info.piwai.toohardforyou.core.entities;

import info.piwai.toohardforyou.core.EntityEngine;

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

  public DynamicPhysicsEntity(EntityEngine peaWorld, World world, float x, float y, float angle) {
    super(peaWorld, x, y, angle);
    body = initPhysicsBody(world, x, y, angle);
    setPos(x, y);
    setAngle(angle);
  }

  abstract Body initPhysicsBody(World world, float x, float y, float angle);

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

  public void initPreLoad(final EntityEngine peaWorld) {
    // attach our layer to the dynamic layer
    peaWorld.dynamicLayer.add(layer);
  }

  public void initPostLoad(final EntityEngine peaWorld) {
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
    getBody().setTransform(new Vec2(x, y), getBody().getAngle());
    prevX = x;
    prevY = y;
  }

  @Override
  public void setAngle(float a) {
    super.setAngle(a);
    getBody().setTransform(getBody().getPosition(), a);
    prevA = a;
  }

  @Override
  public Body getBody() {
    return body;
  }
}
