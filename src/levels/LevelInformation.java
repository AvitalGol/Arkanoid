package levels;

import geometry.Point;
import movment.Sprite;
import movment.Velocity;
import shapes.Block;

import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * returns the number of balls in the level.
     * @return the number.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each balls.
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * The initial place of each balls.
     * @return list of points
     */
    List<Point> initialBallsStartPoint();

    /**
     * the paddle's speed.
     * @return the speed
     */
    int paddleSpeed();

    /**
     * initialize paddle width.
     * @return the width
     */
    int paddleWidth();

    /**
     * initialize paddle's start point.
     * @return the point
     */
    Point paddleStartPoint();

    /**
     * returns the level name.
     * @return the name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the sprite
     */
    Sprite getBackground();

    /**
     * makes list of blocks that should be in the level.
     * @return the list
     */
    List<Block> blocks();

    /**
     * returns the number of the blocks that should be removed in the level.
     * @return the number
     */
    int numberOfBlocksToRemove();
}
