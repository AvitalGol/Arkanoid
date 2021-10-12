//ID: 318960168

package shapes;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Rectangle;
import movment.Collidable;
import movment.Sprite;
import movment.Velocity;
import geometry.Point;

import java.awt.Color;

/**
 * Shapes.Paddle class creates the paddle for the game.
 *  * @author Avital Gololobov
 *  * @since 19.4.20
 */
public class Paddle implements Sprite, Collidable {
    private static final int PADDLES_SPEED =  5;
    private static final int RIGHT_BORDER =  780;
    private static final int LEFT_BORDER =  20;
    private static final double EPSILON =  Math.pow(10, -10);

    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    private Rectangle paddle;
    private Color paddleColor;
    private int paddleSpeed;


    /**
     * constructor.
     * @param gui - the gui the paddle should be shown in.
     * @param rectangle - the shape pf the paddle.
     * @param color - the paddle's color.
     */
    public Paddle(GUI gui, Rectangle rectangle, Color color, int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
        this.gui = gui;
        this.paddle = rectangle;
        this.paddleColor = color;
    }

    /**
     * move left moves the paddle to the left side.
     */
    public void moveLeft() {
        // checks if there is more space in the left side
        if (this.paddle.getUpperLeft().getX() > LEFT_BORDER) {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - PADDLES_SPEED,
                    this.paddle.getUpperLeft().getY()), this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * move right moves the paddle to the right side.
     */
    public void moveRight() {
        // checks if there is more space in the right side
        if (this.paddle.getUpperRight().getX() < RIGHT_BORDER) {
            this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + PADDLES_SPEED,
                    this.paddle.getUpperLeft().getY()), this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * check if the left key or right key is pressed.
     */
    public void timePassed() {
        biuoop.KeyboardSensor clickedKeyboard = gui.getKeyboardSensor();
        if (clickedKeyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
           moveLeft();
        }
        if (clickedKeyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draws the paddle on the surface.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleColor);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    // implementation of the movment.Collidable interface:
    /**
     * returns the paddle.
     * @return the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * checks the new velocity the ball should have after hitting something.
     *
     * @param collisionPoint  - the collision point
     * @param currentVelocity - the ball's current velocity.
     * @return new velocity after collision.
     */
    @Override
    public Velocity hit(shapes.Ball hitter, geometry.Point collisionPoint, Velocity currentVelocity) {
        // if the collision point is with the left or right borders the velocity need to change horizontally.
        if (Math.abs(this.paddle.getUpperLeft().getX() - collisionPoint.getX()) <= EPSILON
                || Math.abs(this.paddle.getUpperRight().getX() - collisionPoint.getX()) <= EPSILON) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            // if the collision point is not on the left or right lines of the rectangle it must collide
            // with the top or bottom lines and the velocity needs to change vertically.
        } else {
            // checks the part the ball hit.
            int checkRegion = (int) ((collisionPoint.getX() - paddle.getUpperLeft().getX())
                    / (this.paddle.getWidth() / 5));
            // changes the vertical velocity.
            Velocity newVelocity  = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            // changes the angel according to the part the ball hit.
            switch (checkRegion) {
                case 0: return Velocity.fromAngleAndSpeed(300, newVelocity.getSpeed());
                case 1: return Velocity.fromAngleAndSpeed(330, newVelocity.getSpeed());
                case 2: return newVelocity;
                case 3: return Velocity.fromAngleAndSpeed(30, newVelocity.getSpeed());
                default: return Velocity.fromAngleAndSpeed(60, newVelocity.getSpeed());
            }
        }
    }

//    /**
//     * checks the movment.Velocity the ball should have after hitting the paddle.
//     * @param collisionPoint - the collision point
//     * @param currentVelocity - the ball's velocity before the hit.
//     * @return the new velocity.
//     */
//    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
//        // if the collision point is with the left or right borders the velocity need to change horizontally.
//        if (Math.abs(this.paddle.getUpperLeft().getX() - collisionPoint.getX()) <= EPSILON
//                || Math.abs(this.paddle.getUpperRight().getX() - collisionPoint.getX()) <= EPSILON) {
//            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
//            // if the collision point is not on the left or right lines of the rectangle it must collide
//            // with the top or bottom lines and the velocity needs to change vertically.
//        } else {
//            // checks the part the ball hit.
//            int checkRegion = (int) ((collisionPoint.getX() - paddle.getUpperLeft().getX())
//                    / (this.paddle.getWidth() / 5));
//            // changes the vertical velocity.
//            Velocity newVelocity  = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
//            // changes the angel according to the part the ball hit.
//            switch (checkRegion) {
//                case 0: return Velocity.fromAngleAndSpeed(300, newVelocity.getSpeed());
//                case 1: return Velocity.fromAngleAndSpeed(330, newVelocity.getSpeed());
//                case 2: return newVelocity;
//                case 3: return Velocity.fromAngleAndSpeed(30, newVelocity.getSpeed());
//                default: return Velocity.fromAngleAndSpeed(60, newVelocity.getSpeed());
//            }
//        }
//    }


    /**
     * Add this paddle to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}