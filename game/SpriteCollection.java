package game;

// 322877754 Omer Perry
import biuoop.DrawSurface;
import java.util.ArrayList;

import assests.Sprite;

/**
 * The SpriteCollection class represents a collection of sprites in a game.
 * It includes methods for adding sprites, notifying all sprites about the
 * passage of time,
 * and drawing all sprites on a DrawSurface.
 *
 * @author 322877754 Omer Perry
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Returns the list of sprites in the game.
     *
     * @return the list of sprites
     */
    public java.util.List<Sprite> getSpriteCollection() {
        return sprites;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes the specified sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void spriteRemove(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This method is typically called once per frame to update the sprites'
     * behavior.
     */
    public void notifyAllTimePassed() {
        for (int i = sprites.size() - 1; i >= 0; i--) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d, double xOff, double yOff) {
        for (int i = 0; i < sprites.size(); i++) {
            this.sprites.get(i).drawOn(d, xOff, yOff);
        }
    }
}