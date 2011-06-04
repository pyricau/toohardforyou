package info.piwai.toohardforyou.java;

import info.piwai.toohardforyou.core.TooHardForYou;
import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;

public class Main {

    public static void main(String[] args) {
        JavaAssetManager assets = JavaPlatform.register().assetManager();
        assets.setPathPrefix("src/main/java/info/piwai/toohardforyou/resources");
        ForPlay.run(new TooHardForYou());
      }
    
}
