// 322877754 Omer Perry
package events;

import assests.Ball;
import assests.Block;
import game.Counter;
import game.Game;

/**
 * The BallRemover class implements the HitListener interface
 * to remove balls from the game upon collision with blocks.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover with the specified game and ball counter.
     *
     * @param game        the game from which to remove balls
     * @param ballCounter the counter representing the number of remaining balls
     */
    public BallRemover(Game game, Counter ballCounter) {
        this.remainingBalls = ballCounter;
        this.game = game;
    }

    /**
     * Handles the hit event by removing the ball from the game
     * and decreasing the remaining balls count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        remainingBalls.decrease(1);
    }
}
