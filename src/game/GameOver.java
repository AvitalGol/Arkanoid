package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * GameOver class.
 */
public class GameOver implements Animation {

    private KeyboardSensor keyboard;
    private int score;
    private boolean stop;

    /**
     * constructor.
     * @param k - the KeyboardSensor
     * @param score - the current score the player got
     */
    public GameOver(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    /**
     * shows game over message.
     * @param d - the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.GRAY);
        d.drawText(217, 253, "Game Over.", 60);
        d.drawText(157, 353, "Your score is " + this.score, 60);
        d.setColor(Color.white);
        d.drawText(220, 250, "Game Over.", 60);
        d.drawText(160, 350, "Your score is " + this.score, 60);
        d.drawLine(0, 150, 800, 150);
        d.drawLine(0, 400, 800, 400);
    }

    /**
     * returns true if the animation should stop.
     * @return - true/false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
