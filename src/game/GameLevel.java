//ID: 318960168

package game;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import movment.Collidable;
import movment.Sprite;
import movment.SpriteCollection;
import shapes.Ball;
import shapes.BallRemover;
import shapes.Block;
import shapes.BlockRemover;
import shapes.Paddle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * creates the game.
 */
public class GameLevel implements Animation {
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;
    private static final int BORDERS_WIDTH = 20;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BALLS_RADIUS = 6;


    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private int numOfBlocks = 0;

    /**
     * constructor.
     * @param levelInfo - the information of the specific level
     * @param keyboardSensor - the keyboardSensor
     * @param animationRunner - the animation that should run.
     * @param score - the counter that saves the score.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.levelInformation = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.running = true;
        this.gui = runner.getGui();
        this.score = score;
    }

    /**
     * adds collidable to the game.
     * @param c - the collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite for the game.
     * @param s - sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes the collidable from the game environment.
     * @param c - the collidable that should be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes the sprite from the sprites array list.
     * @param s - the sprite that should be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * creates the frame for the game with block.
     * @param ballRemover - the instance of ball remover witch should be
     *                    announced when the ball hits the bottom block of the frame.
     */
    public void createFrame(BallRemover ballRemover) {
        List<Rectangle> frame = new ArrayList<>();

        //top
        frame.add(new Rectangle(new Point(0, 20), BOARD_WIDTH, BORDERS_WIDTH));
        //right
        frame.add(new Rectangle(new Point(0, 20), BORDERS_WIDTH, BOARD_WIDTH));
        //left
        frame.add(new Rectangle(new Point(BOARD_WIDTH - BORDERS_WIDTH, 20), BORDERS_WIDTH, BOARD_WIDTH));
        //bottom
        frame.add(new Rectangle(new Point(0, BOARD_HEIGHT - BORDERS_WIDTH), BOARD_WIDTH, BORDERS_WIDTH));

        int i = 0;
        for (Object rectangle : frame) {
            Block block = new Block((Rectangle) rectangle, Color.gray);
            block.addToGame(this);
            if (i == 3) {
                block.addHitListener(ballRemover);
            }
            i++;
        }

    }

    /**
     * creates the blocks for the game according to the specific level.
     * @param blockRemover - instance of block remover witch will be announced when block was hit
     * @param scoreCounter - instance of score counter (will update the score according to the hits)
     */
    public void createBlocks(BlockRemover blockRemover, ScoreTrackingListener scoreCounter) {
        for (Block block : this.levelInformation.blocks()) {
            // adds listeners to the listeners array of the block (updates them in hit)
            block.addHitListener(blockRemover);
            block.addHitListener(scoreCounter);
            // adds the clock to the game:
            block.addToGame(this);
            // increases the amount of block in the game:
            blockCounter.increase(1);
            numOfBlocks += 1;
        }
    }

    /**
     * creates balls for the game according to the level information.
     */
    public void createBalls() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(this.levelInformation.initialBallsStartPoint().get(i),
                    BALLS_RADIUS, Color.WHITE);
            ball.addToGame(this);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ballsCounter.increase(1);
        }
    }

    /**
     * creates the paddle according to the level information.
     */
    public void createPaddle() {
        Paddle paddle = new Paddle(gui, new Rectangle(this.levelInformation.paddleStartPoint(),
                this.levelInformation.paddleWidth(),
                PADDLE_HEIGHT), new Color(247, 220, 111), this.levelInformation.paddleSpeed());
        // adds the paddle to the environment and to the game.
        environment.setPaddle(paddle);
        paddle.addToGame(this);
    }


    /**
     * Initialize a new game: create the gui, blocks, balls, frame and paddle by calling other methods.
     */
    public void initialize() {
        // creates instances for blockCounter, BallsCounter, score, ScoreTrackingListener, ScoreIndicator,
        // block remover and balls remover.
        this.blockCounter = new Counter();
        this.ballsCounter = new Counter();
        ScoreTrackingListener scoreCounter = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter.getCurrentScore());
        scoreIndicator.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        this.addSprite(this.levelInformation.getBackground());

        // crates all other objects for the game
        createPaddle();
        createBlocks(blockRemover, scoreCounter);
        createFrame(ballRemover);
    }

    /**
     * returns the Counter that counts the balls.
     * @return - the instance of ballsCounter
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * draw all sprites on the screen, checks if the blocks/balls ended and when "p" is pressed.
     * @param d - the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.numOfBlocks - blockCounter.getValue() == this.levelInformation.numberOfBlocksToRemove()) {
            score.increase(100);
            this.running = false;
        }
        if (ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    /**
     * returns true if the animation should stop.
     * @return - true/false
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * runs each level.
     */
    public void run() {
        // initialize the balls
        this.createBalls();
        // show the count down animation before the level starts
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        // start the level
        this.running = true;
        this.runner.run(this);
    }
}