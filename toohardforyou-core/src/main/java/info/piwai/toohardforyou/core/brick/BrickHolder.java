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
package info.piwai.toohardforyou.core.brick;

import java.util.ArrayList;
import java.util.List;

public class BrickHolder {

    private final Brick brick;
    private final List<BrickTransformation> transformations = new ArrayList<BrickTransformation>();
    private int nextTransformationIndex = 0;
    private int x;
    private int y;

    public BrickHolder(Brick brick, int x, int y) {
        this.brick = brick;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNextX() {
        return x + transformations.get(nextTransformationIndex).getDeltaX();
    }

    public int getNextY() {
        return y + transformations.get(nextTransformationIndex).getDeltaY();
    }

    public Brick getBrick() {
        return brick;
    }
    
    public void addTransformation(int deltaX, int deltaY) {
        transformations.add(new BrickTransformation(deltaX, deltaY));   
    }

    public boolean hasTransformations() {
        return transformations.size() > 0;
    }

    public void transform() {
        if (hasTransformations()) {

            BrickTransformation transformation = transformations.get(nextTransformationIndex);

            x += transformation.getDeltaX();
            y += transformation.getDeltaY();

            brick.transformPos(transformation.getDeltaX(), transformation.getDeltaY());

            nextTransformationIndex++;
            if (nextTransformationIndex == transformations.size()) {
                nextTransformationIndex = 0;
            }
        }

    }

}