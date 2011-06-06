package info.piwai.toohardforyou.core;

public class PieceFactory {
    
    private final TooHardForYouEngine engine;
    private final Wall wall;

    public PieceFactory(TooHardForYouEngine engine, Wall wall) {
        this.engine = engine;
        this.wall = wall;
    }
    
    public Piece newRandomPiece() {
        return newSquare();
    }
    
    private Piece newSquare() {
        Piece piece = new Piece(engine, wall);
        piece.add(new BrickHolder(newRandomBrick(), 0, 0));
        piece.add(new BrickHolder(newRandomBrick(), 1, 0));
        piece.add(new BrickHolder(newRandomBrick(), 0, 1));
        piece.add(new BrickHolder(newRandomBrick(), 1, 1));
        return piece;
    }

    private Brick newRandomBrick() {
        return new Brick(engine, BrickType.CLASSIC, 0, 0);
    }

}
