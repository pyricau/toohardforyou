package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.currentTime;
import static forplay.core.ForPlay.graphics;
import static forplay.core.ForPlay.keyboard;
import static forplay.core.ForPlay.pointer;
import info.piwai.toohardforyou.core.entities.Ball;
import info.piwai.toohardforyou.core.entities.Paddle;
import forplay.core.AssetWatcher;
import forplay.core.Image;
import forplay.core.Keyboard;
import forplay.core.Pointer;

public class Splashscreen implements GameScreen, Pointer.Listener, Keyboard.Listener {

    private static final int SPLASHSCREEN_DURATION = 2000;

    private final TooHardForYouGame tooHardForYou;

    private boolean splashscreenLoaded;

    private boolean gameResourcesLoaded;

    private boolean animationDone;

    /*
     * TODO uncomment when able to set an alpha value for a layer. Used for fade
     * in / fade out
     */
    // private ImageLayer blackLayer;

    private double animationStart;

    public Splashscreen(TooHardForYouGame tooHardForYou) {
        this.tooHardForYou = tooHardForYou;

        AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
            @Override
            public void done() {
                initAfterResourcesLoaded();

                preloadGameImages();
            }

            @Override
            public void error(Throwable e) {
                // TODO Show an error message
            }
        });

        addAll(assetWatcher, Resources.BLACK_IMG, Resources.SPLASHSCREEN_IMG);

        assetWatcher.start();
        
        pointer().setListener(this);
        keyboard().setListener(this);
    }

    @Override
    public void update(float delta) {
        if (!splashscreenLoaded || animationDone) {
            return;
        }

        if (currentTime() - animationStart > SPLASHSCREEN_DURATION) {
            animationDone = true;
            mayStartGame();
        }

        // This place begs for being able to set alpha value for a layer
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
        AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {
            @Override
            public void done() {
                gameResourcesLoaded = true;
                mayStartGame();
            }

            @Override
            public void error(Throwable e) {
                // TODO Show an error message
            }
        });

        addAll(assetWatcher, Resources.BACKGROUND_IMG, Paddle.IMAGE, Ball.IMAGE);
        assetWatcher.start();
    }

    private void addAll(AssetWatcher assetWatcher, String... imagePaths) {
        for (String imagePath : imagePaths) {
            assetWatcher.add(assetManager().getImage(imagePath));
        }
    }

    private void mayStartGame() {
        if (gameResourcesLoaded && animationDone) {
            graphics().rootLayer().clear();
            tooHardForYou.splashscreenDone();
        }
    }

    @Override
    public void paint(float alpha) {

    }

    @Override
    public void onKeyDown(int keyCode) {
        animationDone = true;
        mayStartGame();        
    }

    @Override
    public void onKeyUp(int keyCode) {
        
    }

    @Override
    public void onPointerStart(float x, float y) {
        animationDone = true;
        mayStartGame();        
    }

    @Override
    public void onPointerEnd(float x, float y) {
        
    }

    @Override
    public void onPointerDrag(float x, float y) {
        
    }

}
