package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * YouWin class.
 */
public class YouWin implements Animation {

    private KeyboardSensor keyboard;
    private int score;
    private boolean stop;

    /**
     * constructor.
     * @param k - the KeyboardSensor
     * @param score - the score the player got
     */
    public YouWin(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    /**
     * draws the "you win" message.
     * @param d - the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.drawLine(0, 250, 800, 250);
        d.setColor(Color.gray);
        d.drawText(257, 253, "You Win!", 60);
        d.drawText(157, 353, "Your score is " + this.score, 60);
        d.setColor(Color.white);
        d.drawText(260, 250, "You Win!", 60);
        d.drawText(160, 350, "Your score is " + this.score, 60);
        d.drawLine(0, 150, 800, 150);
        d.drawLine(0, 400, 800, 400);
    }

    /**
     * returns true when the animation should stop.
     * @return tue/false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}


