// 322877754 Omer Perry
package assests;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.Velocity;
import events.HitListener;
import events.HitNotifier;
import game.Game;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;

/**
 * The Block class represents a block in a game. It implements the Collidable
 * and Sprite interfaces.
 * It includes methods for handling collisions, getting the collision shape,
 * adding the block to a game,
 * and drawing the block on a DrawSurface.
 *
 * @author 322877754 Omer Perry
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rect;
    private Color c;

    /**
     * Constructs a new Block with the specified rectangle and color.
     *
     * @param r     The rectangle defining the block's shape and position.
     * @param color The color of the block.
     */
    public Block(Rectangle r, java.awt.Color color) {
        this.rect = r;
        this.c = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Adds the block to the specified game as both a collidable and a sprite.
     *
     * @param g The game to which the block will be added.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Handles a collision with the block, changing the velocity of the colliding
     * object if necessary.
     * The return is the new velocity expected after the hit, based on the force the
     * object inflicted on the colliding object.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @param hitter          the ball hitting the block
     * @return The new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!this.ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        if (changeDirectionX(collisionPoint, currentVelocity)) {
            if (!isInBox(collisionPoint, new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()))) {
                currentVelocity.setDx((-1) * currentVelocity.getDx());
            }
        }
        if (changeDirectionY(collisionPoint, currentVelocity)) {
            if (!isInBox(collisionPoint, new Velocity(currentVelocity.getDx(), -currentVelocity.getDy()))) {
                currentVelocity.setDy((-1) * currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }

    /**
     * Handles a collision with the block, changing the velocity of the colliding
     * object if necessary.
     * The return is the new velocity expected after the hit, based on the force the
     * object inflicted on the colliding object.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity expected after the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (changeDirectionX(collisionPoint, currentVelocity)) {
            if (!isInBox(collisionPoint, new Velocity(-currentVelocity.getDx(), currentVelocity.getDy()))) {
                currentVelocity.setDx((-1) * currentVelocity.getDx());
            }
        }
        if (changeDirectionY(collisionPoint, currentVelocity)) {
            if (!isInBox(collisionPoint, new Velocity(currentVelocity.getDx(), -currentVelocity.getDy()))) {
                currentVelocity.setDy((-1) * currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }

    /**
     * Returns the collision rectangle representing the shape of the block.
     *
     * @return The collision rectangle representing the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Checks if a collision occurred on the X-axis.
     *
     * @param collision       The point where the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return True if a collision occurred on the X-axis, false otherwise.
     */
    public boolean changeDirectionX(Point collision, Velocity currentVelocity) {
        if ((Math.abs(this.rect.getUpperLeft().getX() - collision.getX()) <= 0.00001)) {
            return true;
        }
        if ((Math.abs(this.rect.getUpperLeft().getX() + this.rect.getWidth() - collision.getX()) <= 0.00001)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a collision occurred on the Y-axis.
     *
     * @param collision       The point where the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return True if a collision occurred on the Y-axis, false otherwise.
     */
    public boolean changeDirectionY(Point collision, Velocity currentVelocity) {
        if ((Math.abs(this.rect.getUpperLeft().getY() - collision.getY()) <= 0.00001)) {
            return true;
        }
        if ((Math.abs(this.rect.getUpperLeft().getY() + this.rect.getHeight() - collision.getY()) <= 0.00001)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a point is inside the block.
     *
     * @param collision       The point to check.
     * @param currentVelocity The current velocity of the colliding object.
     * @return True if the point is inside the block, false otherwise.
     */
    public boolean isInBox(Point collision, Velocity currentVelocity) {
        if (((collision.getX() + currentVelocity.getDx()) < this.rect.getUpperLeft().getX() + this.rect.getWidth())
                && ((collision.getX() + currentVelocity.getDx()) > this.rect.getUpperLeft().getX())
                && ((collision.getY() + currentVelocity.getDy()) < this.rect.getUpperLeft().getY()
                        + this.rect.getHeight())
                && ((collision.getY() + currentVelocity.getDy()) > this.rect.getUpperLeft().getY())) {
            return true;
        }
        return false;
    }

    /**
     * Draws the block on the specified DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the block.
     */
    public void drawOn(DrawSurface surface, double xOff, double yOff) {
        surface.setColor(this.c);
        surface.fillRectangle((int) (this.rect.getUpperLeft().getX() + xOff), (int) (this.rect.getUpperLeft().getY() + yOff),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(java.awt.Color.BLACK);
        this.drawLines(this.rect.getLines(), surface, xOff, yOff);
    }

    /**
     * Draws the lines of the block on the specified DrawSurface.
     *
     * @param lines   The lines representing the sides of the block.
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
     * Sets the color of the block.
     *
     * @param color The new color for the block.
     */
    public void setColor(java.awt.Color color) {
        this.c = color;
    }

    /**
     * gets the color of the block.
     *
     * @return The color of the block.
     */
    public java.awt.Color getColor() {
        return this.c;
    }

    /**
     * Indicates that time has passed, updating the state of the block if necessary.
     */
    public void timePassed() {
    }

    /**
     * Checks if the color of the ball matches the color of this object.
     *
     * @param ball the ball to compare colors with
     * @return true if the colors match, false otherwise
     */
    public Boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.getColor());
    }

    /**
     * Removes this object from the specified game by removing it as both a
     * collidable and a sprite.
     *
     * @param game the game from which to remove this object
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a HitListener to this object.
     *
     * @param hl the HitListener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes the specified HitListener from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a hit event with the given ball.
     *
     * @param hitter the ball that caused the hit event
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
