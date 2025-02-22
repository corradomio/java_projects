package jext.math.random;

import java.util.HashMap;
import java.util.Map;
import java.util.random.RandomGenerator;

public class UniformRandomGenerator {

    private static RandomGeneratorFactory randomGeneratorFactory = new DefaultRandomGeneratorFactory();

    private static Map<Long, RandomGenerator> rngMap = new HashMap<Long, RandomGenerator>();

    public static synchronized RandomGenerator getRandomGenerator() {
        long threadId = Thread.currentThread().getId();
        synchronized (UniformRandomGenerator.class) {
            if (!rngMap.containsKey(threadId))
                rngMap.put(threadId, randomGeneratorFactory.newGenerator());
            return rngMap.get(threadId);
        }
    }

    public static void  setRandomGeneratorFactory(RandomGeneratorFactory randomGeneratorFactory) {
        UniformRandomGenerator.randomGeneratorFactory = randomGeneratorFactory;
    }
}
