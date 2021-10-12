package shapes;

import game.Counter;
import game.GameLevel;
import movment.HitListener;

/**
 * BallRemover class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game - the game
     * @param remainingBalls - the counter witch checks the number of blocks reminded.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
