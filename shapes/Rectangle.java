// 322877754 Omer Perry
package shapes;
import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle in a 2D coordinate system.
 * It includes methods for creating a rectangle, getting its properties,
 * and finding intersection points with a line.
 *
 * @author 322877754 Omer Perry
 */
public class Rectangle {
    private Point p;
    private double w;
    private double h;
    private Line[] lines;

    /**
     * Creates a new rectangle with the specified upper-left corner,
     * width, and height.
     *
     * @param upperLeft The upper-left corner of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.p = upperLeft;
        this.w = width;
        this.h = height;
        this.lines = new Line[4];
        this.lines[0] = new Line(this.p, new Point(this.p.getX() + width, this.p.getY()));
        this.lines[1] = new Line(this.p, new Point(this.p.getX(), this.p.getY() + height));
        this.lines[1].setVertical();
        this.lines[2] = new Line(new Point(this.p.getX() + width, this.p.getY() + height),
                new Point(this.p.getX() + width, this.p.getY()));
        this.lines[2].setVertical();
        this.lines[3] = new Line(new Point(this.p.getX() + width, this.p.getY() + height),
                new Point(this.p.getX(), this.p.getY() + height));
    }

    /**
     * Gets an array of lines representing the sides of the rectangle.
     *
     * @return An array of lines representing the sides of the rectangle.
     */
    public Line[] getLines() {
        return this.lines;
    }

    /**
     * Returns a list of intersection points between the rectangle and a line.
     *
     * @param line The line to check for intersection with the rectangle.
     * @return A list of intersection points between the rectangle and the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new ArrayList<Point>();
        if (line.isIntersecting(lines[0])) {
            intersections.add(line.intersectionWith(lines[0]));
        }
        if (line.isIntersecting(lines[1])) {
            intersections.add(line.intersectionWith(lines[1]));
        }
        if (line.isIntersecting(lines[2])) {
            intersections.add(line.intersectionWith(lines[2]));
        }
        if (line.isIntersecting(lines[3])) {
            intersections.add(line.intersectionWith(lines[3]));
        }

        return intersections;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.w;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.h;
    }

    /**
     * Gets the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.p;
    }

    /**
     * Gets the top side of the rectangle.
     *
     * @return The top side of the rectangle.
     */
    public Line getTop() {
        return this.lines[0];
    }

    /**
     * Gets the left side of the rectangle.
     *
     * @return The left side of the rectangle.
     */
    public Line getLeft() {
        return this.lines[1];
    }

    /**
     * Gets the right side of the rectangle.
     *
     * @return The right side of the rectangle.
     */
    public Line getRight() {
        return this.lines[2];
    }
}
