package info.piwai.toohardforyou.core;

public class Level {

    private int bricksToNextLevel;

    private int linesToNextLevel;

    private int level;

    private final TooHardForYouEngine engine;

    private final UiTexts uiTexts;

    private int piecePauseInMs;

    private float ballSpeed;

    public Level(TooHardForYouEngine engine, UiTexts uiTexts) {
        this.engine = engine;
        this.uiTexts = uiTexts;
    }

    public void newGame() {
        level = 0;
        computeNextLevel();

    }

    public void onBrickBroken() {
        if (bricksToNextLevel == 0) {
            return;
        }
        bricksToNextLevel--;
        uiTexts.updateBricksToNextLevel(bricksToNextLevel);
        checkNextLevel();
    }

    public void onFullLines(int fullLines) {
        if (linesToNextLevel == 0) {
            return;
        }
        linesToNextLevel-=fullLines;
        if (linesToNextLevel < 0) {
            linesToNextLevel = 0;
        }
        uiTexts.updateLinesToNextLevel(linesToNextLevel);
        checkNextLevel();
    }

    private void checkNextLevel() {
        if (linesToNextLevel == 0 && bricksToNextLevel == 0) {
            computeNextLevel();
            engine.nextLevel();
        }
    }

    private void computeNextLevel() {
        level++;
        linesToNextLevel = 10;
        bricksToNextLevel = 50;

        piecePauseInMs = (int) (200 + 750f / level);
        ballSpeed = 4 + 0.2f * level;

        uiTexts.updateBricksToNextLevel(bricksToNextLevel);
        uiTexts.updateLinesToNextLevel(linesToNextLevel);
        uiTexts.updateLevel(level);
    }

    public int getPiecePauseInMs() {
        return piecePauseInMs;
    }

    public float getBallSpeed() {
        return ballSpeed;
    }

}
