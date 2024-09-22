// 322877754 Omer Perry
package collision;

import shapes.Point;

/**
 * The Velocity class represents a 2D vector specifying the change in position
 * on the x and y axes.
 * It includes methods for creating a velocity from an angle and speed, applying
 * a velocity to a point,
 * and accessing/modifying the velocity components.
 *
 * @author 322877754 Omer Perry
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a new Velocity with specified components.
     *
     * @param dx The change in x-coordinate.
     * @param dy The change in y-coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a new Velocity from a given angle and speed.
     *
     * @param angle The angle in degrees.
     * @param speed The speed of the velocity.
     * @return A new Velocity calculated from the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = ((-1) * Math.cos(Math.toRadians(angle))) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Applies the velocity to a given point, returning a new point.
     *
     * @param p The point to which the velocity is applied.
     * @return A new point with the updated position after applying the velocity.
     */
    public Point applyToPoint(Point p) {
        Point dp = new Point(p.getX() + dx, p.getY() + dy);
        return dp;
    }

    /**
     * Gets the change in x-coordinate of the velocity.
     *
     * @return The change in x-coordinate.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in y-coordinate of the velocity.
     *
     * @return The change in y-coordinate.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the change in x-coordinate of the velocity.
     *
     * @param d The new value for the change in x-coordinate.
     */
    public void setDx(double d) {
        this.dx = d;
    }

    /**
     * Sets the change in y-coordinate of the velocity.
     *
     * @param d The new value for the change in y-coordinate.
     */
    public void setDy(double d) {
        this.dy = d;
    }

    /**
     * Returns the speed of the ball.
     *
     * @return the speed of the objecball
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    public void setSpeed(double newSpeed){
        double ratio = newSpeed / this.getSpeed();
        this.dx = this.dx * ratio;
        this.dy = this.dy * ratio;
    }
}