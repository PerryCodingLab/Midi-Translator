// 322877754 Omer Perry
package assests;
import biuoop.DrawSurface;
import game.Game;

/**
 * The Sprite interface represents objects that can be drawn on a DrawSurface.
 * It includes methods for drawing the sprite, updating it with the passage of
 * time,
 * and adding it to a game.
 *
 * @author 322877754 Omer Perry
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the sprite.
     */
    void drawOn(DrawSurface d, double xOff, double yOff);

    /**
     * Notifies the sprite that time has passed.
     * This method is called once per frame to update the sprite's behavior.
     */
    void timePassed();

    /**
     * Adds the sprite to the specified game.
     *
     * @param g The game to which the sprite will be added.
     */
    void addToGame(Game g);
}
