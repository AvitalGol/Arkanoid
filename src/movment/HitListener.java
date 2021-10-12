package movment;

import shapes.Ball;
import shapes.Block;

/**
 * hit Listener class.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that being hit
     * @param hitter - the ball that hits the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
