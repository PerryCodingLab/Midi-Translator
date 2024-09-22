// 322877754 Omer Perry
package assests;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.Velocity;
import game.Game;
import game.GameEnvironment;
import shapes.Line;
import shapes.Point;

import java.awt.Color;

/**
 * The Ball class represents a simple ball with a position, size, color, and
 * velocity.
 * It includes methods for drawing the ball on a DrawSurface and updating its
 * position.
 *
 * @author 322877754 Omer Perry
 */
//
public class Ball implements Sprite {
    private Point p;
    private int r;
    private Color c;
    private Velocity v;
    private GameEnvironment game;
    private Line innerFrame;

    /**
     * Constructs a new Ball with a specified center, radius, and color.
     *
     * @param center The center point of the ball.
     * @param r      The radius of the ball.
     * @param color  The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.p = center;
        this.r = r;
        this.c = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * Constructs a new Ball with specified coordinates, radius, and color.
     *
     * @param x     The x-coordinate of the center of the ball.
     * @param y     The y-coordinate of the center of the ball.
     * @param r     The radius of the ball.
     * @param color The color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.p = new Point(x, y);
        this.r = r;
        this.c = color;
    }

    /**
     * Sets the inner frame of the ball to be confined within two specified points.
     *
     * @param one The starting point of the inner frame.
     * @param two The ending point of the inner frame.
     */
    public void setInner(Point one, Point two) {
        this.innerFrame = new Line(one, two);
    }

    // Accessors

    /**
     * Returns the x-coordinate of the center of the ball.
     *
     * @return The x-coordinate of the center.
     */
    public int getX() {
        return (int) this.p.getX();
    }

    /**
     * Returns the y-coordinate of the center of the ball.
     *
     * @return The y-coordinate of the center.
     */
    public int getY() {
        return (int) this.p.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return The radius of the ball.
     */
    public double getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.c;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity to be set.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball using the given dx and dy values.
     *
     * @param dx The change in x-coordinate per step.
     * @param dy The change in y-coordinate per step.
     */
    public void setVelocity(double dx, double dy) {
        Velocity tmp = new Velocity(dx, dy);
        this.v = tmp;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the ball.
     */
    public void drawOn(DrawSurface surface, double xOff, double yOff) {
        // if (this.innerFrame == null) {
        //     this.innerFrame = new Line(new Point(0, 0), new Point(surface.getWidth(), surface.getHeight()));
        // }
        surface.setColor(this.c);
        surface.fillCircle((int) (this.p.getX() + xOff), (int) (this.p.getY() + yOff), (int)this.r);
    }

    /**
     * Ensures that the ball stays within the inner frame.
     */
    public void stayIn() {
        if (((this.p.getX() + this.v.getDx()) > this.innerFrame.end().getX()) && (this.v.getDx() > 0)) {
            this.setVelocity(this.v.getDx() * (-1), this.v.getDy());
        }
        if ((this.p.getX() + this.v.getDx() < this.innerFrame.start().getX()) && (this.v.getDx() < 0)) {
            this.setVelocity(this.v.getDx() * (-1), this.v.getDy());
        }
        if (((this.p.getY() + this.v.getDy()) > this.innerFrame.end().getY()) && (this.v.getDy() > 0)) {
            this.setVelocity(this.v.getDx(), this.v.getDy() * (-1));
        }
        if (((this.p.getY() + this.v.getDy()) < this.innerFrame.start().getY()) && (this.v.getDy() < 0)) {
            this.setVelocity(this.v.getDx(), this.v.getDy() * (-1));
        }
    }

    /**
     * Updates the position of the ball for one time step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Moves the ball to its next position based on its velocity.
     * Handles collisions with collidables in the game environment.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.p, new Point(this.p.getX() + this.v.getDx(), this.p.getY() + this.v.getDy()));
        CollisionInfo collide = this.game.getClosestCollision(trajectory);
        // if (this.innerFrame != null) {
        //     this.stayIn();
        // }
        if (collide != null) {
            this.setVelocity(collide.collisionObject().hit(this, collide.collisionPoint(), this.v));
            this.p = this.moveNearPoint(collide.collisionPoint());
        }
        this.p = new Point(this.p.getX() + this.v.getDx(), this.p.getY() + this.v.getDy());
    }

    /**
     * Moves the ball to a point near the collision point.
     *
     * @param point The collision point.
     * @return A new point near the collision point.
     */
    public Point moveNearPoint(Point point) {
        double x = this.p.getX() + point.getX();
        double y = this.p.getY() + point.getY();
        return new Point(x / 2, y / 2);
    }

    /**
     * Sets the game environment for the ball.
     *
     * @param g The game environment to set.
     */
    public void setGame(GameEnvironment g) {
        this.game = g;
    }

    /**
     * Adds the ball to the given game.
     *
     * @param g The game to which the ball should be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes the sprite from the specified game.
     *
     * @param g the game from which the sprite will be removed
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * Sets the color of this sprite to the specified color.
     *
     * @param next the color to be set for this sprite
     */
    public void setColor(Color next) {
        this.c = next;
    }
}
