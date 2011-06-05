package info.piwai.toohardforyou.core;

import java.util.List;

public class Piece {
    
    private final Wall wall;
    
    private int top;
    private int left;
    
    public Piece(Wall wall) {
        this.wall = wall;
    }
    
    private static class BrickHolder {
        int x;
        int y;
        Brick brick;
        List<Transformation> transformations;
    }
    
    private static class Transformation {
        int deltaX;
        int deltaY;
    }

}
