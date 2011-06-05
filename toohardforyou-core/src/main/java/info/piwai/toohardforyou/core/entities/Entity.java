package info.piwai.toohardforyou.core.entities;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import info.piwai.toohardforyou.core.EntityEngine;
import forplay.core.ForPlay;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.ResourceCallback;

/**
 * Based on the forplay-peaphysics example (Copyright 2011 The ForPlay Authors),
 * which is licensed under the Apache License, Version 2.0.
 */
public abstract class Entity {
  ImageLayer layer;
  Image image;
  float x, y, angle;

  public Entity(final EntityEngine peaWorld, float px, float py, float pangle) {
    this.x = px;
    this.y = py;
    this.angle = pangle;
    image = assetManager().getImage(getImagePath());
    layer = graphics().createImageLayer(image);
    initPreLoad(peaWorld);
    image.addCallback(new ResourceCallback<Image>() {
      @Override
      public void done(Image image) {
        // since the image is loaded, we can use its width and height
        layer.setWidth(image.width());
        layer.setHeight(image.height());
        layer.setOrigin(image.width() / 2f, image.height() / 2f);
        layer.setScale(getWidth() / image.width(), getHeight() / image.height());
        layer.setTranslation(x, y);
        layer.setRotation(angle);
        initPostLoad(peaWorld);
      }

      @Override
      public void error(Throwable err) {
        ForPlay.log().error("Error loading image: " + err.getMessage());
      }
    });
  }

  /**
   * Perform pre-image load initialization (e.g., attaching to PeaWorld layers).
   * 
   * @param peaWorld
   */
  public abstract void initPreLoad(final EntityEngine peaWorld);

  /**
   * Perform post-image load initialization (e.g., attaching to PeaWorld layers).
   * 
   * @param peaWorld
   */
  public abstract void initPostLoad(final EntityEngine peaWorld);

  public void paint(float alpha) {
  }

  public void update(float delta) {
  }

  public void setPos(float x, float y) {
    layer.setTranslation(x, y);
  }

  public void setAngle(float a) {
    layer.setRotation(a);
  }

  abstract float getWidth();

  abstract float getHeight();

  abstract String getImagePath();
  
  public Image getImage() {
    return image;
  }
}
