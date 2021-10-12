//ID: 318960168

package game;

import movment.HitListener;
import shapes.Ball;
import shapes.Block;

/**
 * ScoreTrackingListener calss.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * returns the current score.
     * @return the score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * updates the current score.
     * @param scoreCounter - the counter the field should be updated to.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * called when there is a hot and increases the score accordingly.
     * @param beingHit - the block that being hit
     * @param hitter - the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }


}
