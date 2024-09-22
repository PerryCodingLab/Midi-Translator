package events;
import assests.Ball;
import assests.Block;
import game.Counter;

/**
 * The ScoreTrackingListener class implements the HitListener interface
 * to track the score when a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Increases the score by 5 when a block is hit.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * Returns the current score.
     *
     * @return the current score
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
}
