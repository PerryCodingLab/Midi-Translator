// 322877754 Omer Perry
package collision;

import assests.Ball;
import game.Game;
import shapes.Point;
import shapes.Rectangle;

/**
 * The Collidable interface represents objects that can participate in
 * collisions.
 * It defines methods for obtaining the collision shape of the object,
 * handling collisions, and adding the object to a game.
 *
 * @author 322877754 Omer Perry
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return The collision rectangle representing the shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it at the specified
     * collision point
     * with a given velocity.
     * The return is the new velocity expected after the hit, based on the force the
     * object
     * inflicted on the colliding object.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @param hitter the ball hitting the block
     * @return The new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Notifies the object that a collision occurred with it at the specified
     * collision point
     * with a given velocity.
     * The return is the new velocity expected after the hit, based on the force the
     * object
     * inflicted on the colliding object.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity expected after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

    /**
     * Adds the collidable object to the specified game.
     *
     * @param game The game to which the collidable object will be added.
     */
    void addToGame(Game game);
}
