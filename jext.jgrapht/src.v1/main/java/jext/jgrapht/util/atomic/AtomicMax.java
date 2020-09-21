package jext.jgrapht.util.atomic;

import jext.jgrapht.util.SlotFloat;

import static java.lang.Math.max;

public class AtomicMax extends SlotFloat {

    private static final long serialVersionUID = 2566830395670370687L;

    public AtomicMax() {
        value = -Float.MAX_VALUE;
    }

    public AtomicMax(float initialValue) {
        value = initialValue;
    }

    public synchronized void accumulate(float newValue) {
        value = max(value, newValue);
    }

    // public synchronized final float accumulateAndGet(float newValue) {
    //     value = max(value, newValue);
    //     return value;
    // }
    //
    // public synchronized final float getAndAccumulate(float newValue) {
    //     float oldValue = value;
    //     value = max(value, newValue);
    //     return oldValue;
    // }

}
