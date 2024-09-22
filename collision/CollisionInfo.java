// 322877754 Omer Perry
package collision;

import shapes.Point;

/**
 * The CollisionInfo class represents information about a collision between two
 * objects.
 * It includes the collision point and the collidable object involved in the
 * collision.
 *
 * @author 322877754 Omer Perry
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a new CollisionInfo with the specified collision point and
     * collidable object.
     *
     * @param p The point at which the collision occurs.
     * @param c The collidable object involved in the collision.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
