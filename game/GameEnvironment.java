package game;

// 322877754 Omer Perry
import java.util.ArrayList;

import collision.Collidable;
import collision.CollisionInfo;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

/**
 * The GameEnvironment class represents the environment of a game, including a
 * collection of collidable objects.
 * It provides methods to add collidables to the environment, check for
 * collisions, and retrieve information about collisions.
 *
 * @author 322877754 Omer Perry
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable to be added to the environment.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, returns null. Else, returns the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory The line representing the movement of the object.
     * @return CollisionInfo about the closest collision, or null if no collision is
     *         detected.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<Point> collisions = new ArrayList<Point>();
        double d = 0;
        double newD;
        int index = -1; // Initialize to -1 to check if any collisions were found
        int count = -1;
        java.util.List<Point> intersectionPoints = new ArrayList<Point>();
        for (int i = 0; i < this.collidables.size(); i++) {
            intersectionPoints = this.collidables.get(i).getCollisionRectangle().intersectionPoints(trajectory);
            if (!intersectionPoints.isEmpty()) {
                Point closestIntersection = trajectory
                        .closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle(), trajectory);
                if (closestIntersection != null) {
                    collisions.add(closestIntersection);
                    count++;
                    newD = trajectory.start().distance(closestIntersection);
                    if (d == 0 || newD < d) {
                        d = newD;
                        index = i;
                    }
                }
            }
        }

        if (collisions.isEmpty() || index == -1) {
            return null;
        }
        CollisionInfo info = new CollisionInfo(collisions.get(count), collidables.get(index));
        return info;
    }

    /**
     * Returns the collision rectangle of the collidable at the specified index.
     *
     * @param index The index of the collidable in the list.
     * @return The collision rectangle of the specified collidable.
     */
    public Rectangle getRect(int index) {
        return this.collidables.get(index).getCollisionRectangle();
    }

    /**
     * Returns the list of collidables in the environment.
     *
     * @return The list of collidables in the environment.
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Removes the specified collidable from the game environment.
     *
     * @param c the collidable to remove
     */
    public void collidableRemove(Collidable c) {
        this.collidables.remove(c);
    }

}
