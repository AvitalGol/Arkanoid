//ID: 318960168

package game;

/**
 * counter class.
 */
public class Counter {

    private int remainedBlocks = 0;

    /**
     * add number to current count.
     * @param number - the number to add
     */
    public void increase(int number) {
        remainedBlocks += number;
    }

    /**
     * subtract number from current count.
     * @param number - the number to decrease.
     */
    public void decrease(int number) {
        remainedBlocks -= number;
    }

    /**
     * get the current count.
     * @return the current count
     */
    public int getValue() {
        return remainedBlocks;
    }
}
