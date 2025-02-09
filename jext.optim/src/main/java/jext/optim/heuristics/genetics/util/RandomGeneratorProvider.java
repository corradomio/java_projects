package jext.optim.heuristics.genetics.util;

import org.apache.commons.rng.UniformRandomProvider;

import java.util.random.RandomGenerator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Adapter Commons UniformRandomProvider -> Java RandomGenerator
 */
public class RandomGeneratorProvider implements RandomGenerator {

    private UniformRandomProvider provider;

    public RandomGeneratorProvider(UniformRandomProvider provider) {
        this.provider = provider;
    }

    @Override
    public void nextBytes(byte[] bytes) {
        provider.nextBytes(bytes);
    }

    public void nextBytes(byte[] bytes, int start, int len) {
        provider.nextBytes(bytes, start, len);
    }

    @Override
    public int nextInt() {
        return provider.nextInt();
    }

    @Override
    public int nextInt(int n) {
        return provider.nextInt(n);
    }

    @Override
    public int nextInt(int origin, int bound) {
        return provider.nextInt(origin, bound);
    }

    @Override
    public long nextLong() {
        return provider.nextLong();
    }

    @Override
    public long nextLong(long n) {
        return provider.nextLong(n);
    }

    @Override
    public long nextLong(long origin, long bound) {
        return provider.nextLong(origin, bound);
    }

    @Override
    public boolean nextBoolean() {
        return provider.nextBoolean();
    }

    @Override
    public float nextFloat() {
        return provider.nextFloat();
    }

    @Override
    public float nextFloat(float bound) {
        return provider.nextFloat(bound);
    }

    @Override
    public float nextFloat(float origin, float bound) {
        return provider.nextFloat(origin, bound);
    }

    @Override
    public double nextDouble() {
        return provider.nextDouble();
    }

    @Override
    public double nextDouble(double bound) {
        return provider.nextDouble(bound);
    }

    @Override
    public double nextDouble(double origin, double bound) {
        return provider.nextDouble(origin, bound);
    }

    @Override
    public IntStream ints() {
        return provider.ints();
    }

    @Override
    public IntStream ints(int origin, int bound) {
        return provider.ints(origin, bound);
    }

    @Override
    public IntStream ints(long streamSize) {
        return provider.ints(streamSize);
    }

    @Override
    public IntStream ints(long streamSize, int origin, int bound) {
        return provider.ints(streamSize, origin, bound);
    }

    @Override
    public LongStream longs() {
        return provider.longs();
    }

    @Override
    public LongStream longs(long origin, long bound) {
        return provider.longs(origin, bound);
    }

    @Override
    public LongStream longs(long streamSize) {
        return provider.longs(streamSize);
    }

    @Override
    public LongStream longs(long streamSize, long origin, long bound) {
        return provider.longs(streamSize, origin, bound);
    }

    @Override
    public DoubleStream doubles() {
        return provider.doubles();
    }

    @Override
    public DoubleStream doubles(double origin, double bound) {
        return provider.doubles(origin, bound);
    }

    @Override
    public DoubleStream doubles(long streamSize) {
        return provider.doubles(streamSize);
    }

    @Override
    public DoubleStream doubles(long streamSize, double origin, double bound) {
        return provider.doubles(streamSize, origin, bound);
    }
}
