package info.piwai.toohardforyou.java;

import info.piwai.toohardforyou.core.TooHardForYou;

import java.lang.reflect.Field;

import javax.swing.JFrame;

import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;

public class Main {

    public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        JavaPlatform platform = JavaPlatform.register();
        JavaAssetManager assets = platform.assetManager();
        assets.setPathPrefix("src/main/java/info/piwai/toohardforyou/resources");
        ForPlay.run(new TooHardForYou());
        
        Field frameField = platform.getClass().getDeclaredField("frame");
        frameField.setAccessible(true);
        
        JFrame jframe = (JFrame) frameField.get(platform);
        
        jframe.setTitle("2H4U : Too Hard For You");
      }
    
}
