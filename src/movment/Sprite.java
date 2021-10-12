//ID: 318960168

package movment;

import biuoop.DrawSurface;

/**
 * movment.Sprite interface.
 * @author Avital Gololobov
 * @since 19.4.20
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}