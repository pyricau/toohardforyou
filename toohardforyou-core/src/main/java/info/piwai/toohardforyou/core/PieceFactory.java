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
