//ID: 318960168

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameFlow;
import game.GameLevel;
import levels.*;

import java.util.ArrayList;
import java.util.List;

/**
 * inclused the main method to start the game.
 * @author Avital Gololobov
 * @since 19.4.20
 */
public class Ass6Game {
    /**
     * the main method.
     * @param args - the arguments the main gets.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("game.Game", 800, 600);
        boolean checknums = false;
        List<LevelInformation> levels = new ArrayList<>();
        for (String i : args) {
            switch (i) {
                case "1" -> {
                    checknums = true;
                    levels.add(new DirectHit());
                }
                case "2" -> {
                    checknums = true;
                    levels.add(new WideEasy());
                }
                case "3" -> {
                    checknums = true;
                    levels.add(new Green3());
                }
                case "4" -> {
                    checknums = true;
                    levels.add(new FinalFour());
                }
            }
        }
        if (args.length == 0 || !checknums) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor kr = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, kr);
        gameFlow.runLevels(levels);
    }
}
