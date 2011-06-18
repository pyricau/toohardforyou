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

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import static forplay.core.ForPlay.keyboard;
import static forplay.core.ForPlay.pointer;
import info.piwai.toohardforyou.core.ball.Ball;
import info.piwai.toohardforyou.core.brick.BrickType;
import info.piwai.toohardforyou.core.paddle.Paddle;
import forplay.core.AssetWatcher;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.Keyboard;
import forplay.core.Pointer;

public class Splashscreen implements GameScreen, Pointer.Listener, Keyboard.Listener {

    private static final int SPLASHSCREEN_DURATION = 3000;

    private final TooHardForYouGame tooHardForYou;

    private boolean splashscreenLoaded;

    private boolean gameResourcesLoaded;

    private boolean animationDone;

    private ImageLayer blackLayer;

    private float animationDuration;

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
        
        animationDuration += delta;

        if (animationDuration > SPLASHSCREEN_DURATION) {
            animationDone = true;
            blackLayer.setAlpha(0);
            mayStartGame();
        } else {
            blackLayer.setAlpha(1 - animationDuration / SPLASHSCREEN_DURATION);
        }
    }

    private void initAfterResourcesLoaded() {
        Image backgroundImage = assetManager().getImage(Resources.SPLASHSCREEN_IMG);
        graphics().rootLayer().add(graphics().createImageLayer(backgroundImage));

        graphics().setSize(backgroundImage.width(), backgroundImage.height());

        Image blackImage = assetManager().getImage(Resources.BLACK_IMG);
        blackLayer = graphics().createImageLayer(blackImage);
        graphics().rootLayer().add(blackLayer);

        animationDuration = 0;

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

        for (BrickType brickType : BrickType.values()) {
            add(assetWatcher, brickType.getImagePath());
        }

        assetWatcher.start();
    }

    private void addAll(AssetWatcher assetWatcher, String... imagePaths) {
        for (String imagePath : imagePaths) {
            add(assetWatcher, imagePath);
        }
    }

    private void add(AssetWatcher assetWatcher, String imagePath) {
        assetWatcher.add(assetManager().getImage(imagePath));
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
