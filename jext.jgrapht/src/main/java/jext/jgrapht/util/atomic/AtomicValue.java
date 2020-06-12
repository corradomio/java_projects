package jext.jgrapht.util.atomic;

import jext.jgrapht.util.SlotFloat;

public class AtomicValue extends SlotFloat {

    public synchronized void accumulate(float value) {
        this.value = value;
    }

}
