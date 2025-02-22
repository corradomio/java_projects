package jext.math.random;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

public class ThreadLocalRandomGeneratorFactory implements RandomGeneratorFactory {
    @Override
    public RandomGenerator newGenerator() {
        return ThreadLocalRandom.current();
    }
}
