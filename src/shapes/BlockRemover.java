// ID: 318960168

package shapes;

import game.Counter;
import game.GameLevel;
import movment.HitListener;


// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

/**
 * block remover class - in charge of removing blocks from the game and count the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game - the game
     * @param removedBlocks - the counter which represent the number of block thar remained.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * called when there is hit.
     * @param beingHit - the block that being hit
     * @param hitter - the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // decrease the number of blocks
        remainingBlocks.decrease(1);
        // removes the block from the game
        beingHit.removeFromGame(this.game);
        // removes the blockRemover instance from the block's listeners
        beingHit.removeHitListener(this);
    }
}
