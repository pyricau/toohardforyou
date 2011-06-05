package info.piwai.toohardforyou.core;

public enum BrickType {

    BAD_BONUS("b00"), CLASSIC("b10"), UNBREAKABLE("b20"), THICK("b30"), GOOD_BONUS("b40"), BOMB("b50"), THICKER("b60");

    private final String imagePath;

    BrickType(String image) {
        imagePath = Resources.BRICKS_PATH + image + ".png";
    }
    
    public String getImagePath() {
        return imagePath;
    }

}