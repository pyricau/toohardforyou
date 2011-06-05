package info.piwai.toohardforyou.core.entities;

import info.piwai.toohardforyou.core.EntityEngine;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Based on the forplay-peaphysics example (Copyright 2011 The ForPlay Authors),
 * which is licensed under the Apache License, Version 2.0.
 */
public abstract class StaticPhysicsEntity extends Entity implements PhysicsEntity {
  private Body body;
  
  public StaticPhysicsEntity(final EntityEngine peaWorld, World world, float x, float y, float angle) {
    super(peaWorld, x, y, angle);
    body = initPhysicsBody(world, x, y, angle);
  }

  abstract Body initPhysicsBody(World world, float x, float y, float angle);

  @Override
  public void paint(float alpha) {
  }

  @Override
  public void update(float delta) {
  }

  public void initPreLoad(final EntityEngine peaWorld) {
  }

  public void initPostLoad(final EntityEngine peaWorld) {
    peaWorld.staticLayerBack.add(layer);
  }

  @Override
  public void setPos(float x, float y) {
    throw new RuntimeException("Error setting position on static entity");
  }

  @Override
  public void setAngle(float a) {
    throw new RuntimeException("Error setting angle on static entity");
  }

  @Override
  public Body getBody() {
    return body;
  }
}
