// 322877754 Omer Perry
package events;

import assests.Ball;
import assests.Block;
import game.Counter;
import game.Game;

/**
 * The BlockRemover class implements the HitListener interface
 * to remove blocks from the game upon collision with balls.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover with the specified game and remaining blocks
     * counter.
     *
     * @param game            the game from which to remove blocks
     * @param remainingBlocks the counter representing the number of remaining
     *                        blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
        this.game = game;
    }

    /**
     * Handles the hit event by removing the block from the game,
     * removing this listener from the block, updating the hitter's color,
     * and decreasing the remaining blocks count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        hitter.setColor(beingHit.getColor());
        this.remainingBlocks.decrease(1);
    }

    /**
     * Updates the counter for the remaining blocks.
     *
     * @param a the counter to update with
     */
    public void updateCounter(Counter a) {
        this.remainingBlocks = a;
    }
}
