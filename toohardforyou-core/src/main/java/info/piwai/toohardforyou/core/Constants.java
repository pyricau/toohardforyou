package info.piwai.toohardforyou.core;

public abstract class Constants {
    
    // scale difference between screen space (pixels) and world space (physics).
    public static float PHYS_UNIT_PER_SCREEN_UNIT = 1 / 26.666667f;

    // size of world
    public static final float GAME_WIDTH = 520 * PHYS_UNIT_PER_SCREEN_UNIT;
    public static final float GAME_HEIGHT = 600 * PHYS_UNIT_PER_SCREEN_UNIT;
    
    private Constants() {
        throw new UnsupportedOperationException();
    }

}
