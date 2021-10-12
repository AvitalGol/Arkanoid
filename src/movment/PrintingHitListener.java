package movment;

import shapes.Ball;
import shapes.Block;

public class PrintingHitListener implements HitListener {

    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
