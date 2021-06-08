package jext.math.random;

/**
 * https://en.wikipedia.org/wiki/Linear_congruential_generator
 * https://en.wikipedia.org/wiki/List_of_random_number_generators
 */
public class LinearCongruentialGenerator implements RandomGenerator {

    private static long pow2(int n) { return 1L << n ; }

    public static LinearCongruentialGenerator numericalrecipes(int seed) {
        return new LinearCongruentialGenerator(seed, pow2(32), 1664525, 1013904223);
    }
    public static LinearCongruentialGenerator borland(int seed) {
        return new LinearCongruentialGenerator(seed, pow2(32), 22695477, 1)
            .withBitsMask(0, 30);
    }
    public static LinearCongruentialGenerator gcc(int seed) {
        return new LinearCongruentialGenerator(seed, pow2(31), 1103515245, 1234)
            .withBitsMask(0, 30);
    }

    private final long multiplier;
    private final long addend;
    private final long modulus;
    private final double toDouble;
    private long value;

    private int offsetBit;
    private long maskBits;

    public LinearCongruentialGenerator() {
        this(System.currentTimeMillis());
    }

    public LinearCongruentialGenerator(long seed) {
        this(seed, (1L << 32), 1664525, 1013904223);
    }

    public LinearCongruentialGenerator(long seed, long modulus, long multiplier, long addend) {
        this.value = seed;
        this.multiplier = multiplier;
        this.addend = addend;
        this.modulus = modulus;
        this.toDouble = 1./modulus;
    }

    public LinearCongruentialGenerator withBitsMask(int offsetBit, int nBits) {
        this.offsetBit = offsetBit;
        this.maskBits = (1L << nBits) - 1;
        return this;
    }

    public long nextLong() {
        return value = (value*multiplier + addend) % modulus;
    }

    public double nextDouble() {
        return nextLong()*toDouble;
    }

}
