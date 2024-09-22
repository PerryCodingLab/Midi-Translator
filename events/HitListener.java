// 322877754 Omer Perry
package events;

import assests.Ball;
import assests.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
