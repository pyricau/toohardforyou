package info.piwai.toohardforyou.core;

import java.util.ArrayList;
import java.util.List;

public class BrickHolder {

    final Brick brick;
    final List<BrickTransformation> transformations = new ArrayList<BrickTransformation>();
    final int currentTransformationIndex = 0;
    final int x;
    final int y;
    
    public BrickHolder(Brick brick, int x, int y) {
        this.brick = brick;
        this.x = x;
        this.y = y;
    }
    
}