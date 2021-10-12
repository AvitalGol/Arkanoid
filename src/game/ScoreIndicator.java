//ID: 318960168

package game;

import biuoop.DrawSurface;
import movment.Sprite;

/**
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;

    /**
     * constructor.
     * @param scoreCounter - update the field.
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * draw the sprite to the screen.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(380, 15, "score: " + this.scoreCounter.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the instance to the game.
     * @param g - the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
