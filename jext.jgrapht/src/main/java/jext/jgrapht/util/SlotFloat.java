package jext.jgrapht.util;

public abstract class SlotFloat extends Number implements java.io.Serializable  {
    protected volatile float value;

    public SlotFloat() { }

    public SlotFloat(float initialValue) {
        value = initialValue;
    }

    public synchronized float get() {
        return value;
    }

    public synchronized void set(float newValue) {
        value = newValue;
    }

    public abstract void accumulate(float value);

    @Override
    public String toString() {
        return Float.toString(get());
    }

    @Override
    public int intValue() {
        return (int)get();
    }

    @Override
    public long longValue() {
        return (long)get();
    }

    @Override
    public float floatValue() {
        return get();
    }

    @Override
    public double doubleValue() {
        return get();
    }
}
