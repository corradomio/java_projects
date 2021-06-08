package jext.math.random;

import java.util.Random;

public class JavaRandomGenerator implements RandomGenerator {

    private Random random;

    public JavaRandomGenerator() {
        this(0);
    }

    public JavaRandomGenerator(long seed) {
        this.random = new Random(seed);
    }

    @Override
    public long nextLong() {
        return random.nextLong();
    }

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }
}
