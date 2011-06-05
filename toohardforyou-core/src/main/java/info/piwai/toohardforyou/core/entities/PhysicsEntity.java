package info.piwai.toohardforyou.core.entities;

import org.jbox2d.dynamics.Body;

/**
 * Based on the forplay-peaphysics example (Copyright 2011 The ForPlay Authors),
 * which is licensed under the Apache License, Version 2.0.
 */
public interface PhysicsEntity {
  
  public Body getBody();
  
  public interface HasContactListener {
    public void contact(PhysicsEntity other);
  }
}