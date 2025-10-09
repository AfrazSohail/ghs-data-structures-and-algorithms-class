package Classwork.WeBareBears;

import java.util.Arrays;
import java.util.Iterator;

public class Den implements Iterable<Bear> {
    Bear[] bears;

    int count = 0;

    double totalWeight = 0;

    public Den() {
        bears = new Bear[3];
    }

    /**
     *
     * @param bear the bear to push
     * @return the pushed bear or null if not successful
     */

    Bear PushABear(Bear bear) { // pushing a bear
        if (count > 0 && bears[count - 1].weight < bear.weight) {
            return null;
        }

        // We have to check if capacity isn't reached
        // We have to check if the last bear is heavier

        totalWeight += bear.weight;

        if (count < bears.length) {
            bears[count++] = bear;
            return bear;
        }

        Bear[] tempBear = new Bear[bears.length * 2];
        for (int i = 0; i < bears.length; i++) {
            tempBear[i] = bears[i];
        }
        tempBear[count++] = bear;
        bears = tempBear;
        return bear;

    }

    @Override
    public String toString() {
        return "Den [bears=" + Arrays.toString(bears) + ", count=" + count + "]";
    }

    // REMOVE
    public Bear HuntABear() {
        if (count > 0) {
            Bear tempBear = bears[count - 1];
            bears[--count] = null;
            if (1.0 * count / bears.length < 0.1) {
                // Trim();
            }
            totalWeight -= tempBear.weight;
            return tempBear;
        }
        return null;
    }

    // get
    public Bear PeakABear() {
        return (count == 0) ? null : bears[count - 1];
    }
    // , size is public,

    // isEmpty()
    public boolean hasBears() {
        return count > 0;
    }

    @Override
    public Iterator<Bear> iterator() {
        return new BearHunter();
    }

    private class BearHunter implements Iterator<Bear> {
        int fingy = count - 1;

        @Override
        public boolean hasNext() {
            return fingy >= 0;
        }

        @Override
        public Bear next() {
            return hasNext() ? bears[fingy--] : null;
        }
    }
}
