package game;
/**
 * The Counter class represents a simple numbers counter.
 */
public class Counter {
    private int count;

    /**
     * Constructs a new Counter with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the current count by the specified number.
     *
     * @param number the number to add to the current count
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Decreases the current count by the specified number.
     *
     * @param number the number to subtract from the current count
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}
