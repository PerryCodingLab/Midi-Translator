// 322877754 Omer Perry
package assests;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.Velocity;
import game.Game;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

/**
 * The Paddle class represents a game paddle. It can be controlled using the
 * keyboard.
 * The paddle is both a Sprite and a Collidable in the game.
 * It includes methods for moving, drawing, and handling collisions with the
 * ball.
 *
 * @author 322877754 Omer Perry
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private int speed;
    private int size;

    /**
     * Constructs a new Paddle with a specified keyboard sensor.
     *
     * @param keyboard The keyboard sensor for controlling the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.color = java.awt.Color.GREEN;
        this.size = 130;
        this.rect = new Rectangle(new Point(400, 550), this.size, 30);
        this.speed = 6;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double x = this.rect.getUpperLeft().getX();
        double y = (int) this.rect.getUpperLeft().getY();
        if (x < -this.rect.getWidth() / 2) {
            x = 800 - this.rect.getWidth() / 2;
        }
        this.rect = new Rectangle(new Point((int) (x - speed), y), this.size, 30);
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double x = this.rect.getUpperLeft().getX();
        double y = (int) this.rect.getUpperLeft().getY();
        if (x > 800 - this.rect.getWidth() / 2) {
            x = -this.rect.getWidth() / 2;
        }
        this.rect = new Rectangle(new Point((int) (x + speed), y), this.size, 30);
    }

    /**
     * Handles the paddle's behavior during each game tick.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the paddle.
     */
    public void drawOn(DrawSurface surface,double xOff, double yOff) {
        surface.setColor(this.color);
        surface.fillRectangle((int) (this.rect.getUpperLeft().getX() + xOff), (int) (this.rect.getUpperLeft().getY() + yOff),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(java.awt.Color.BLACK);
        this.drawLines(this.rect.getLines(), surface, xOff, yOff);
    }

    /**
     * Draws the lines of the paddle on the given DrawSurface.
     *
     * @param lines   The array of lines representing the paddle.
     * @param surface The DrawSurface on which to draw the lines.
     */
    public void drawLines(Line[] lines, DrawSurface surface, double xOff, double yOff) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawLine((int) (lines[0].start().getX() + xOff), (int) (lines[0].start().getY() + yOff),
                (int) (lines[0].end().getX() + xOff), (int) (lines[0].end().getY()+ yOff));
        surface.drawLine((int) (lines[1].start().getX() + xOff), (int) (lines[1].start().getY() + yOff),
                (int) (lines[1].end().getX() + xOff), (int) (lines[1].end().getY()+ yOff));
        surface.drawLine((int) (lines[2].start().getX() + xOff), (int) (lines[2].start().getY() + yOff),
                (int) (lines[2].end().getX() + xOff), (int) (lines[2].end().getY() + yOff));
        surface.drawLine((int) (lines[3].start().getX() + xOff), (int) (lines[3].start().getY() + yOff),
                (int) (lines[3].end().getX() + xOff), (int) (lines[3].end().getY() + yOff));
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handles the paddle's reaction to a collision with a ball.
     *
     * @param collisionPoint  The point of collision with the ball.
     * @param currentVelocity The current velocity of the ball.
     * @param hitter the ball thats hitting the paddle
     * @return The new velocity of the ball after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (pointOnSide(collisionPoint, this.rect.getLeft())) {
            if (currentVelocity.getDx() > 0) {
                currentVelocity.setDx((-1) * currentVelocity.getDx());
            }
        } else if (pointOnSide(collisionPoint, this.rect.getRight())) {
            if (currentVelocity.getDx() < 0) {
                currentVelocity.setDx((-1) * currentVelocity.getDx());
            }
        } else if (pointOnTop(collisionPoint, this.rect.getTop()) && (currentVelocity.getDy() > 0)) {
            currentVelocity = makePaddleFun(collisionPoint, currentVelocity);
        }
        return currentVelocity;
    }

        /**
     * Handles the paddle's reaction to a collision with a ball.
     *
     * @param collisionPoint  The point of collision with the ball.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity of the ball after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (pointOnSide(collisionPoint, this.rect.getLeft())) {
            if (currentVelocity.getDx() > 0) {
                currentVelocity.setDx((-1) * currentVelocity.getDx());
            }
        } else if (pointOnSide(collisionPoint, this.rect.getRight())) {
            if (currentVelocity.getDx() < 0) {
                currentVelocity.setDx((-1) * currentVelocity.getDx());
            }
        } else if (pointOnTop(collisionPoint, this.rect.getTop()) && (currentVelocity.getDy() > 0)) {
            currentVelocity = makePaddleFun(collisionPoint, currentVelocity);
        }
        return currentVelocity;
    }

    /**
     * Checks if a point is on the top side of a line.
     *
     * @param collision The point to check.
     * @param line      The line representing the side.
     * @return True if the point is on the top side, false otherwise.
     */
    public boolean pointOnTop(Point collision, Line line) {
        return collision.getX() <= line.second().getX() && collision.getX() >= line.first().getX();
    }

    /**
     * Checks if a point is on the side of a line.
     *
     * @param collision The point to check.
     * @param line      The line representing the side.
     * @return True if the point is on the side, false otherwise.
     */
    public boolean pointOnSide(Point collision, Line line) {
        return Math.abs(line.first().getX() - collision.getX()) <= 0.00001;
    }

    /**
     * Adjusts the ball's velocity based on where it hits the paddle.
     *
     * @param collision The point of collision with the ball.
     * @param current   The current velocity of the ball.
     * @return The adjusted velocity of the ball.
     */
    public Velocity makePaddleFun(Point collision, Velocity current) {
        Line top = this.rect.getTop();
        for (int i = 1; i <= 5; i++) {
            if (collision.getX() - top.first().getX() < (this.size / 5) * i) {
                if (i == 3) {
                    return new Velocity(current.getDx(), -current.getDy());
                }
                return Velocity.fromAngleAndSpeed(300 + (i - 1) * 30 + 1, current.getSpeed());
            }
        }
        return current;
    }

    /**
     * Adds the paddle to the given game.
     *
     * @param g The game to which the paddle is added.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}