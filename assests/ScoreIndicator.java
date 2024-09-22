package assests;

import biuoop.DrawSurface;
import game.Counter;
import game.Game;

/**
 * The ScoreIndicator class represents a visual indicator for displaying the score on the game screen.
 * It implements the Sprite interface.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a new ScoreIndicator with the specified Counter.
     *
     * @param s the Counter representing the score
     */
    public ScoreIndicator(Counter s) {
        this.score = s;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the score indicator
     */
    public void drawOn(DrawSurface d,double xOff, double yOff) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(d.getWidth() / 2, 15, "Score: " + score.getValue(), 20);
    }

    /**
     * Performs no action for time passed.
     */
    public void timePassed() {
        // This method currently performs no action for ScoreIndicator.
    }

    /**
     * Adds the ScoreIndicator to the specified game.
     *
     * @param g the Game to which to add the ScoreIndicator
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
