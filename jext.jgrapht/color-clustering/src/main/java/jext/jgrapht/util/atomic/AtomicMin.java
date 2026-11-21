package jext.jgrapht.util.atomic;

import jext.jgrapht.util.SlotFloat;

import static java.lang.Math.min;

public class AtomicMin extends SlotFloat {

    private static final long serialVersionUID = 8972988463400548736L;

    public AtomicMin() {
        value = +Float.MAX_VALUE;
    }

    public AtomicMin(float initialValue) {
        value = initialValue;
    }

    public synchronized void accumulate(float newValue) {
        value = min(value, newValue);
    }

    // public synchronized final float accumulateAndGet(float newValue) {
    //     value = min(value, newValue);
    //     return value;
    // }
    //
    // public synchronized final float getAndAccumulate(float newValue) {
    //     float oldValue = value;
    //     value = min(value, newValue);
    //     return oldValue;
    // }

}
