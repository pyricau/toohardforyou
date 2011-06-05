package info.piwai.toohardforyou.core;

public enum BrickType {

    RED("b00"), BLUE("b10"), ORANGE("b20"), LIGHT_GREEN("b30"), YELLOW("b40"), PURPLE("b50"), DARK_GREEN("b60");

    private final String imagePath;

    BrickType(String image) {
        imagePath = Resources.BRICKS_PATH + image + ".png";
    }
    
    public String getImagePath() {
        return imagePath;
    }

}