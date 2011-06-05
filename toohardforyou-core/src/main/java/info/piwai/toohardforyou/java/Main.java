package info.piwai.toohardforyou.java;

import info.piwai.toohardforyou.core.TooHardForYouGame;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;

public class Main {

    public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JavaPlatform platform = JavaPlatform.register();
        JavaAssetManager assets = platform.assetManager();
        assets.setPathPrefix("src/main/java/info/piwai/toohardforyou/resources");
        ForPlay.run(new TooHardForYouGame());
        
        Field frameField = platform.getClass().getDeclaredField("frame");
        frameField.setAccessible(true);
        
        Object jframe = frameField.get(platform);
        
        Method method = jframe.getClass().getMethod("setTitle", String.class);
        
        method.invoke(jframe, "2H4U : Too Hard For You");
      }
    
}
