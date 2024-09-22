// 322877754 Omer Perry
package shapes;
/**
 * The Point class represents a point in a 2D coordinate system.
 * It includes methods for calculating distance between points, checking
 * equality,
 * and accessing/modifying the x and y coordinates of the point.
 *
 * @author 322877754 Omer Perry
 */
public class Point {

    private double cordX;
    private double cordY;

    /**
     * Constructs a new Point object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.cordX = x;
        this.cordY = y;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other The other Point object.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        double a = (other.getX() - this.cordX);
        a = a * a;
        double b = (other.getY() - this.cordY);
        b = b * b;
        return Math.sqrt(a + b);
    }

    /**
     * Checks if two points are equal within a small tolerance.
     *
     * @param other The other Point object.
     * @return true if the points are equal within the tolerance, false otherwise.
     */
    public boolean equals(Point other) {
        if ((Math.abs(this.cordX - other.getX()) <= 0.0001) && (Math.abs(this.cordY - other.getY()) <= 0.0001)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the x value of this point.
     *
     * @return The cordX of this point.
     */
    public double getX() {
        return this.cordX;
    }

    /**
     * Gets the parameter cordY.
     *
     * @return The cordY of this point.
     */
    public double getY() {
        return this.cordY;
    }

    /**
     * Sets a new x value for this point.
     *
     * @param x The new cordX of this point.
     */
    public void setX(double x) {
        this.cordX = x;
    }

    /**
     * Sets a new y value for this point.
     *
     * @param y The new cordY of this point.
     */
    public void setY(double y) {
        this.cordY = y;
    }
}
