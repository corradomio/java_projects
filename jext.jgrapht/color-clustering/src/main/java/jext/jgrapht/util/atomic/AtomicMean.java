package jext.jgrapht.util.atomic;

import jext.jgrapht.util.SlotFloat;

public class AtomicMean extends SlotFloat {

    private static final long serialVersionUID = 7802725506572935856L;

    private int count;

    public AtomicMean() { }

    public synchronized float get() {
        return count > 0 ? value/count : value;
    }

    public synchronized void set(float newValue) {
        value = newValue;
        count = 0;
    }

    public synchronized final void accumulate(float increment) {
        value += increment;
        count += 1;
    }

    // public synchronized final float accumulateAndGet(float increment) {
    //     value += increment;
    //     count += 1;
    //     return value/count;
    // }
    //
    // public synchronized final float getAndAccumulate(float increment) {
    //     float oldValue = value;
    //     value += increment;
    //     count += 1;
    //     return count == 1 ? oldValue : oldValue/(count-1);
    // }

}
