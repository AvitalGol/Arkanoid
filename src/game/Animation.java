package game;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * what to do in each "frame" of the game.
     * @param d - the surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * return true when the animation should stop.
     * @return - true or false accordingly
     */
    boolean shouldStop();
}