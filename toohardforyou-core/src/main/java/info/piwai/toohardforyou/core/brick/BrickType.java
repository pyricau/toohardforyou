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

import info.piwai.toohardforyou.core.Resources;

public enum BrickType {

    MALUS("b00"), CLASSIC("b10"), UNBREAKABLE("b20"), THICK("b30"), BONUS("b40"), BOMB("b50"), THICKER("b60"), BROKEN("bgrey.png");

    private final String imagePath;

    BrickType(String image) {
        imagePath = Resources.BRICKS_PATH + image + ".png";
    }

    public String getImagePath() {
        return imagePath;
    }
    

}