package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor - KeyboardSensor.
     * @param key - the key we wait for
     * @param animation - the animation that should be shown
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * changes the stop field to true if the key was pressed.
     * @param d - the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed && this.keyboard.isPressed(this.key)) {
            this.stop = true;
        }
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