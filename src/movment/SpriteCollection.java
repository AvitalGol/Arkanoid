//ID: 318960168

package movment;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * movment.Sprite collection.
 * @author Avital Gololobov
 * @since 19.4.20
 */
public class SpriteCollection {

    private List<Sprite> spriteCollectionArray = new ArrayList<>();

    /**
     * adds sprite to the collection.
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        spriteCollectionArray.add(s);
    }

    /**
     * removes sprite to the collection.
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        spriteCollectionArray.remove(s);
    }


    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteCollectionArrayCopy = new ArrayList<Sprite>(this.spriteCollectionArray);
        for (Object sprite : spriteCollectionArrayCopy) {
            ((Sprite) sprite).timePassed();
        }
    }


    /**
     * call drawOn(d) on all sprites.
     * @param d - the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Object sprite : spriteCollectionArray) {
            ((Sprite) sprite).drawOn(d);
        }
    }
}