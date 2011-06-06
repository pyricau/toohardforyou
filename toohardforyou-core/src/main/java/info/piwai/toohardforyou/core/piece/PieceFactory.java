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
package info.piwai.toohardforyou.core.piece;

import static forplay.core.ForPlay.*;
import info.piwai.toohardforyou.core.TooHardForYouEngine;
import info.piwai.toohardforyou.core.brick.BrickFactory;
import info.piwai.toohardforyou.core.brick.BrickHolder;
import info.piwai.toohardforyou.core.wall.Wall;

public class PieceFactory {

    private final TooHardForYouEngine engine;
    private final BrickFactory brickFactory;
    private final Wall wall;

    public PieceFactory(TooHardForYouEngine engine, BrickFactory brickFactory, Wall wall) {
        this.engine = engine;
        this.brickFactory = brickFactory;
        this.wall = wall;
    }

    public Piece newRandomPiece() {
        int type = (int) Math.floor(random() * 7);

        switch (type) {
        case 0:
            return newGamma();
        case 1:
            return newGun();
        case 2:
            return newIceT();
        case 3:
            return newS();
        case 4:
            return newSquare();
        case 5:
            return newStick();
        default:
            return newZ();
        }
    }

    private Piece newSquare() {
        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        brickHolder.addTransformation(1, 0);
        brickHolder.addTransformation(0, 1);
        brickHolder.addTransformation(-1, 0);
        brickHolder.addTransformation(0, -1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 1, -2);
        brickHolder.addTransformation(0, 1);
        brickHolder.addTransformation(-1, 0);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(1, 0);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 1, -1);
        brickHolder.addTransformation(-1, 0);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(1, 0);
        brickHolder.addTransformation(0, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(1, 0);
        brickHolder.addTransformation(0, 1);
        brickHolder.addTransformation(-1, 0);
        piece.add(brickHolder);

        return piece;
    }

    /**
     * |
     * 
     * Stick starts vertically
     */
    private Piece newStick() {

        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -4);
        brickHolder.addTransformation(2, 2);
        brickHolder.addTransformation(-2, 1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -2);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -3);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 0);
        brickHolder.addTransformation(0, 0);
        brickHolder.addTransformation(0, -1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        brickHolder.addTransformation(0, 0);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 0);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -2);
        brickHolder.addTransformation(2, 2);
        brickHolder.addTransformation(-2, 1);
        piece.add(brickHolder);

        return piece;
    }

    /**
     * L
     */
    private Piece newGun() {
        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -3);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 1, -1);
        brickHolder.addTransformation(-2, 0);
        brickHolder.addTransformation(0, -2);
        brickHolder.addTransformation(2, 0);
        brickHolder.addTransformation(0, 2);
        piece.add(brickHolder);

        return piece;
    }

    /**
     * _|
     */
    private Piece newGamma() {
        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -3);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), -1, -1);
        brickHolder.addTransformation(0, -2);
        brickHolder.addTransformation(2, 0);
        brickHolder.addTransformation(0, 2);
        brickHolder.addTransformation(-2, 0);
        piece.add(brickHolder);

        return piece;
    }

    /**
     * S
     */
    private Piece newS() {
        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 1, -2);
        brickHolder.addTransformation(0, 1);
        brickHolder.addTransformation(-2, 0);
        brickHolder.addTransformation(1, -2);
        brickHolder.addTransformation(1, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        brickHolder.addTransformation(1, 0);
        brickHolder.addTransformation(-1, 1);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(0, 0);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(0, 0);
        brickHolder.addTransformation(1, 0);
        brickHolder.addTransformation(-1, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), -1, -1);
        brickHolder.addTransformation(1, -2);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(0, 1);
        brickHolder.addTransformation(-2, 0);
        piece.add(brickHolder);

        return piece;
    }

    /**
     * Z
     */
    private Piece newZ() {
        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), -1, -2);
        brickHolder.addTransformation(1, -1);
        brickHolder.addTransformation(1, 2);
        brickHolder.addTransformation(-2, 0);
        brickHolder.addTransformation(0, -1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        brickHolder.addTransformation(0, 0);
        brickHolder.addTransformation(0, 1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, 0);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, 0);
        brickHolder.addTransformation(0, 0);
        brickHolder.addTransformation(0, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 1, -1);
        brickHolder.addTransformation(-2, 0);
        brickHolder.addTransformation(0, -1);
        brickHolder.addTransformation(1, -1);
        brickHolder.addTransformation(1, 2);
        piece.add(brickHolder);

        return piece;
    }

    /**
     * T
     */
    private Piece newIceT() {
        Piece piece = new Piece(engine, wall);

        BrickHolder brickHolder;

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -3);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -2);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 0, -1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        brickHolder.addTransformation(1, 1);
        brickHolder.addTransformation(-1, 1);
        piece.add(brickHolder);

        brickHolder = new BrickHolder(brickFactory.newRandomBrick(), 1, -2);
        brickHolder.addTransformation(-1, 1);
        brickHolder.addTransformation(-1, -1);
        brickHolder.addTransformation(1, -1);
        brickHolder.addTransformation(1, 1);
        piece.add(brickHolder);

        return piece;
    }

}
