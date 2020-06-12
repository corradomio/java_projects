package jext.embedding;

public class Embedding {
    
    private final float[] values;

    static Embedding of(int size) {
        return new Embedding(size);
    }

    public static Embedding of(String[] parts, int start) {
        int i=0, n = parts.length;
        float[] values = new float[n-start];
        for(int j=start; j<n; ++j) {
            values[i++] = Float.parseFloat(parts[j]);
        }
        return new Embedding(values);
    }

    private Embedding(int size) {
        this.values = new float[size];
    }

    private Embedding(float[] values) {
        this.values = values;
    }

    public float[] get() { return values; }

    public int size() { return values.length; }
}
