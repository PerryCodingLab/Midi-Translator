// 322877754 Omer Perry
package events;

/**
 * The HitNotifier interface represents an object that can notify
 * listeners about hit events.
 */
public interface HitNotifier {
    /**
     * Adds the specified HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the specified HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}