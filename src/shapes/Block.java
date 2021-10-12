//ID: 318960168

package shapes;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import movment.Sprite;
import movment.Velocity;
import movment.Collidable;
import movment.HitListener;
import movment.HitNotifier;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Shapes.Block class- creates the blocks.
 * @author Avital Gololobov
 * @since 22.4.20
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON =  Math.pow(10, -10);

    private Rectangle block;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * first constructor.
     * @param block - creates the block by rectangle.
     */
    public Block(Rectangle block) {
        this.block = block;
    }

    /**
     * second constructor.
     * @param block - creates the block by rectangle.
     * @param color - the block color.
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
    }


    /**
     * gets the block.
     * @return the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * return the velocity the ball should have after hitting collidable.
     * @param collisionPoint - the closest collision point to the ball.
     * @param currentVelocity - the current velocity of the ball.
     * @return the velocity it should have after the hit.
     */
    @Override
    public Velocity hit(shapes.Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if the collision point is with the left or right borders the velocity need to change horizontally.
        if ((Math.abs(this.block.getUpperLeft().getX() - collisionPoint.getX()) <= EPSILON
                && currentVelocity.getDx() > 0)
                || (Math.abs(this.block.getUpperRight().getX() - collisionPoint.getX()) <= EPSILON
                && currentVelocity.getDx() < 0)) {
            dx = -dx;
        }
        // if the collision point is with the top or bottom borders the velocity need to change vertically.
        if ((Math.abs(this.block.getUpperLeft().getY() - collisionPoint.getY()) <= EPSILON)
                || (Math.abs(this.block.getLowerRight().getY() - collisionPoint.getY()) <= EPSILON)) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * gets the current block.
     * @return the block.
     */
    public Rectangle getBlock() {
        return this.block;
    }

    /**
     * draws the block on the surface.
     * @param surface - the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * time passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds thr block to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the block from the game.
     * @param game - the game to remove from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify that there was a hit to all blocks listeners.
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
