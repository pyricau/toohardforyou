package info.piwai.toohardforyou.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.log;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class ImagePreloader {

    private final String[] imagePaths;

    public ImagePreloader(String... imagePaths) {
        this.imagePaths = imagePaths;
    }

    public interface PreloadCallback {
        void resourcesLoaded();

        void resourceLoadingError();
    }

    private class ImageCallback implements ResourceCallback<Image> {

        private int preloadedImages;

        private final PreloadCallback callback;

        private boolean error;

        public ImageCallback(PreloadCallback callback) {
            this.callback = callback;
        }

        @Override
        public void done(Image resource) {
            if (!error) {
                preloadedImages++;
                imageLoaded(preloadedImages, callback);
            }
        }

        @Override
        public void error(Throwable err) {
            log().error("Error loading image!", err);
            error = true;
            loadingError(callback);
        }

    }

    public void preload(PreloadCallback callback) {
        ImageCallback imageCallback = new ImageCallback(callback);
        for (String imagePath : imagePaths) {
            Image image = assetManager().getImage(imagePath);
            image.addCallback(imageCallback);
        }
    }

    public void imageLoaded(int preloadedImages, PreloadCallback callback) {
        if (preloadedImages == imagePaths.length) {
            callback.resourcesLoaded();
        }
    }

    public void loadingError(PreloadCallback callback) {
        callback.resourceLoadingError();
    }

}
