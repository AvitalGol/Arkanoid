package game;

import biuoop.KeyboardSensor;
import levels.LevelInformation;
import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * constructor.
     * @param ar - the animationRunner that should run.
     * @param ks - KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * runLevels - iterates threw all levels.
     * changes the levels when the player wins and close the gui when the the player won/lost.
     * @param levels - list pf levels that should be in the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        //variable that checks if the payer win/lost
        Boolean win = true;
        for (LevelInformation levelInfo : levels) {
            // makes instance of the level
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getBallsCounter().getValue() == 0) {
                win = false;
                break;
            }
        }

        // in charge of the message that should appear
        if (win) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new YouWin(this.keyboardSensor, this.score.getValue())));
        } else {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new GameOver(this.keyboardSensor, this.score.getValue())));
        }

        // closes the gui when the game ends
        animationRunner.getGui().close();
    }
}
