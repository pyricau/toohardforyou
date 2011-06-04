package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.currentTime;
import static forplay.core.ForPlay.graphics;
import forplay.core.Image;
import info.piwai.toohardforyou.core.ImagePreloader.PreloadCallback;

/**
 * TODO allow for skipping splashscreen via touch / click / enter
 */
public class Splashscreen extends AbstractGame implements PreloadCallback {

    private static final int SPLASHSCREEN_DURATION = 2000;

    private final TooHardForYou tooHardForYou;

    private boolean splashscreenLoaded;

    private boolean gameResourcesLoaded;

    private boolean animationDone;

    /*
     * TODO uncomment when able to set an alpha value for a layer. Used for fade
     * in / fade out
     */
    // private ImageLayer blackLayer;

    private double animationStart;

    public Splashscreen(TooHardForYou tooHardForYou) {
        this.tooHardForYou = tooHardForYou;
        ImagePreloader splashscreenPreloader = new ImagePreloader(Resources.BLACK_IMG, Resources.SPLASHSCREEN_IMG);
        splashscreenPreloader.preload(this);
    }

    @Override
    public void update(float delta) {
        if (!splashscreenLoaded || animationDone) {
            return;
        }

        if (currentTime() - animationStart > SPLASHSCREEN_DURATION) {
            animationDone = true;
            startGame();
        }

        // This place begs for being able to set alpha value for a layer
    }

    @Override
    public void resourcesLoaded() {
        initAfterResourcesLoaded();

        preloadGameImages();
    }

    private void initAfterResourcesLoaded() {
        Image backgroundImage = assetManager().getImage(Resources.SPLASHSCREEN_IMG);
        graphics().rootLayer().add(graphics().createImageLayer(backgroundImage));

        graphics().setSize(backgroundImage.width(), backgroundImage.height());

        /*
         * TODO uncomment when able to set an alpha value for a layer. Used for
         * fade in / fade out
         */
        // Image blackImage = assetManager().getImage(Resources.BLACK_IMG);
        //
        // blackLayer = graphics().createImageLayer(blackImage);
        // graphics().rootLayer().add(blackLayer);

        animationStart = currentTime();

        splashscreenLoaded = true;
    }

    private void preloadGameImages() {
        ImagePreloader imagePreloader = new ImagePreloader(Resources.BACKGROUND_IMG);
        imagePreloader.preload(new PreloadCallback() {
            @Override
            public void resourcesLoaded() {
                gameResourcesLoaded = true;
                startGame();
            }

            @Override
            public void resourceLoadingError() {
                // TODO Show an error message
            }
        });
    }

    private void startGame() {
        if (gameResourcesLoaded && animationDone) {
            graphics().rootLayer().clear();
            tooHardForYou.splashscreenDone();
        }
    }

    @Override
    public void resourceLoadingError() {
        // TODO Show an error message
    }

}
