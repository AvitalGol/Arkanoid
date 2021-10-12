package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * PauseScreen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param k - KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * draw the the "pause" message.
     * @param d - the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.GRAY);
        d.drawText(277, 253, "paused", 60);
        d.drawText(167, 353, "press space to continue", 40);
        d.setColor(Color.white);
        d.drawText(280, 250, "paused", 60);
        d.drawText(170, 350, "press space to continue", 40);
        d.drawLine(0, 150, 800, 150);
        d.drawLine(0, 400, 800, 400);
    }

    /**
     * returns true if the animation should stop.
     * @return - true/false
     */
    public boolean shouldStop() {
        return this.stop;
    }
}