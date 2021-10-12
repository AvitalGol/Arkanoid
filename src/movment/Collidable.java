//ID: 318960168

package movment;

import geometry.Point;
import geometry.Rectangle;

/**
 * movment.Collidable interface.
 * @author Avital Gololobov
 * @since 19.4.20
 */
public interface Collidable {

    /**
     * gets the collidable the ball hits.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * checks the new velocity the ball should have after hitting something.
     * @param collisionPoint - the collision point
     * @param currentVelocity - the ball's current velocity.
     * @param hitter - the ball that hits the collidable
     * @return new velocity after collision.
     */
    Velocity hit(shapes.Ball hitter, Point collisionPoint, Velocity currentVelocity);
}