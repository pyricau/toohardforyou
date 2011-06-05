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
