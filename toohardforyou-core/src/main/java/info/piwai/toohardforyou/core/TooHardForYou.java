package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import info.piwai.toohardforyou.core.ImagePreloader.PreloadCallback;
import forplay.core.Game;
import forplay.core.Image;
import forplay.core.ImageLayer;

public class TooHardForYou implements Game, PreloadCallback {

    @Override
    public void init() {
        ImagePreloader imagePreloader = new ImagePreloader(Resources.BACKGROUND_IMG);
        imagePreloader.preload(this);
    }

    @Override
    public void update(float delta) {
        
    }

    @Override
    public void paint(float alpha) {
        
    }

    @Override
    public int updateRate() {
        return 25;
    }

    @Override
    public void resourcesLoaded() {
        Image bgImage = assetManager().getImage(Resources.BACKGROUND_IMG);
        graphics().setSize(bgImage.width(), bgImage.height());
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);
    }

    @Override
    public void error() {
        // Show an error message
    }

}
