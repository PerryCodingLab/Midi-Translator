// 322877754 Omer Perry
package shapes;

import java.util.Random;
import java.awt.Color;

import biuoop.DrawSurface;
import game.Game;

import java.util.ArrayList;

import assests.Sprite;

/**
 * The Line class represents a line segment in a 2D coordinate system.
 * It includes methods for calculating the length, middle point, start and end
 * points of the line,
 * checking intersection with other lines, and generating random lines.
 *
 * @author 322877754 Omer Perry
 */
public class Line implements Sprite{
    private Point start;
    private Point end;
    private Point first;
    private Point second;
    private double gradient;
    private double offset;
    private boolean vertical;

    /**
     * Constructs a new Line object with the specified start and end points.
     * Uses the points to calculate the parameters of a line equation: y = mx + b,
     * and saves m in the gradient variable and b in offset.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.getX() < end.getX()) {
            this.first = start;
            this.second = end;
        } else {
            this.first = end;
            this.second = start;
        }
        // m = (y2-y1)/(x2-x1)
        if (Math.abs(start.getX() - end.getX()) <= 0.001) {
            this.vertical = true;
            this.gradient = 0;
            this.offset = start.getX();
        } else {
            this.gradient = ((this.second.getY() - this.first.getY()) / (this.second.getX() - this.first.getX()));
            this.offset = ((-1) * this.first.getX() * this.gradient) + this.first.getY();
        }
        // b = -m*x1+y1
    }

    /**
     * Constructs a new Line object with the specified coordinates.
     * Uses the points to calculate the parameters of a line equation: y = mx + b,
     * and saves m in the gradient variable and b in offset.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        if (x1 > x2) {
            this.first = new Point(x2, y2);
            this.second = new Point(x1, y1);
        } else {
            this.first = new Point(x1, y1);
            this.second = new Point(x2, y2);
        }
        if (Math.abs(this.second.getX() - this.first.getX()) <= 0.0001) {
            this.vertical = true;
            this.gradient = 0;
            this.offset = x1;
        } else {
            this.gradient = ((this.second.getY() - this.first.getY()) / (this.second.getX() - this.first.getX()));
            this.offset = ((-1) * this.first.getX() * this.gradient) + this.first.getY();
        }
        // m = (y2-y1)/(x2-x1)
        // b = m*x1+y1
        // y = mx - mx1 + y1
    }

    /**
     * Sets the line as vertical.
     */
    public void setVertical() {
        this.vertical = true;
    }

    /**
     * Calculates and returns the length of the line segment.
     *
     * @return The length of the line.
     */
    public double length() {
        return this.first.distance(this.second);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        double x = (this.first.getX() + this.second.getX()) / 2;
        double y = (this.first.getY() + this.second.getY()) / 2;
        Point middle = new Point(x, y);
        return middle;
    }

    /**
     * Returns the starting point of the line.
     * starting point is the point with lower x value.
     *
     * @return The starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     * ending point is the point with higher x value.
     *
     * @return The ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the first point of the line (lower x value).
     *
     * @return The first point of the line.
     */
    public Point first() {
        return this.first;
    }

    /**
     * Returns the second point of the line (higher x value).
     *
     * @return The second point of the line.
     */
    public Point second() {
        return this.second;
    }

    /**
     * Checks if the line is vertical.
     *
     * @return true if the line is vertical, false otherwise.
     */
    public boolean isVertical() {
        return this.vertical;
    }

    /**
     * Checks if the line intersects with another line.
     *
     * @param other The other Line object to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.gradient == other.getGradient()) {
            return false;
        }
        if (this.vertical && other.isVertical()) {
            return false;
        }
        Point testing = intersectionWith(other);
        if (testing == null) {
            return false;
        }
        if (pointInSegment(other.first(), other.second(), testing)
                && pointInSegment(this.first, this.second, testing)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified point is possible within the line segment defined by
     * the start and end points.
     *
     * @param start   The starting point of the line segment.
     * @param end     The ending point of the line segment.
     * @param testing The point to check for inclusion in the segment.
     * @return true if the testing point is within the line segment, false
     *         otherwise.
     */
    public boolean pointInSegment(Point start, Point end, Point testing) {
        if ((testing.getX() <= end.getX()) && (testing.getX() >= (start.getX()))) {
            if ((testing.getY() <= Math.max(start.getY(), end.getY()))
                    && (testing.getY() >= Math.min(start.getY(), end.getY()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 The first Line object to check for intersection.
     * @param other2 The second Line object to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (other1.isIntersecting(other2) && this.isIntersecting(other1)
            && other2.isIntersecting(this)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * calculates by assuming a common Y and solving for X. then checking
     * if the resulting point is possible
     *
     * @param other The other Line object to find intersection with.
     * @return The intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        double commonY = -1;
        double commonX;
        if (this.gradient == other.getGradient()) {
            return null;
        }
        if (this.vertical) {
            commonX = this.start().getX();
            if (other.getGradient() == 0) {
                commonY = other.offset;
            } else {
                commonY = other.getGradient() * commonX + other.getOffset();
            }
        } else if (other.isVertical()) {
            commonX = other.start().getX();
            if (this.gradient == 0) {
                commonY = this.offset;
            } else {
                commonY = this.gradient * commonX + this.offset;
            }
        } else if (this.gradient == 0) {
            commonY = this.offset;
            commonX = (other.getOffset() - this.offset) / (this.gradient - other.getGradient());
        } else if (other.getGradient() == 0) {
            commonY = other.getOffset();
            commonX = (other.getOffset() - this.offset) / (this.gradient - other.getGradient());
        } else {
            commonX = (other.getOffset() - this.offset) / (this.gradient - other.getGradient());
            commonY = (this.gradient * commonX) + this.offset;
        }
        // uses y-y1 = m(x-x1) assuming y1 == y2
        Point intersection = new Point(commonX, commonY);
        if (pointInSegment(other.first(), other.second(), intersection)
                && pointInSegment(this.first(), this.second(), intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * Checks if two lines are equal.
     *
     * @param other The other Line object to check for equality.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.first.equals(other.start()) || this.first.equals(other.end())) {
            if (this.second.equals(other.start()) || this.second.equals(other.end())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a random line with endpoints within the specified range.
     *
     * @param x The maximum x-coordinate for the random line.
     * @param y The maximum y-coordinate for the random line.
     * @return A randomly generated Line object.
     */
    public static Line generateRandomLine(int x, int y) {
        Random rand = new Random();
        Line line = new Line(rand.nextInt(x), rand.nextInt(y), rand.nextInt(x), rand.nextInt(y));
        return line;
    }

    /**
     * Draws the line on the specified DrawSurface.
     *
     * @param l The Line object to be drawn.
     * @param d The DrawSurface on which to draw the line.
     */
    public static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * Gets the gradient of the line.
     *
     * @return The gradient of the line.
     */
    public double getGradient() {
        return this.gradient;
    }

    /**
     * Gets the offset of the line.
     *
     * @return The offset of the line.
     */
    public double getOffset() {
        return this.offset;
    }

    /**
     * Finds the closest intersection point of the line with a rectangle.
     *
     * @param rect The rectangle to check for intersection.
     * @param line The line to find the closest intersection point.
     * @return The closest intersection point of the rectangle to the start of the line, or null if
     *         no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect, Line line) {
        java.util.List<Point> list = new ArrayList<Point>();
        list = rect.intersectionPoints(line);
        double distance;

        if (list.isEmpty()) {
            return null; // No intersections with the rectangle
        }
        Point closestIntersection = list.get(0);
        int j = 1;
        while (closestIntersection == null) {
            closestIntersection = list.get(j);
            j++;
        }

        double minDistance = this.start().distance(closestIntersection);
        Point intersection;
        for (int i = 1; i < list.size(); i++) {
            intersection = list.get(i);
            if (intersection != null) {
                distance = this.start().distance(intersection);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestIntersection = intersection;
                }
            }
        }

        return closestIntersection;
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }

    public void drawOn(DrawSurface surface, double xOff, double yOff) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawLine((int)(this.start.getX() + xOff),(int) (this.start.getY() + yOff),(int) (this.end.getX() + xOff),(int) (this.end.getY() + yOff));
        
}
    public void timePassed() {
        
    }
}