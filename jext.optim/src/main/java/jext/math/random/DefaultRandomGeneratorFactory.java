package jext.math.random;

import java.util.Random;
import java.util.random.RandomGenerator;

public class DefaultRandomGeneratorFactory implements RandomGeneratorFactory {

    @Override
    public RandomGenerator newGenerator() {
        return new Random();
    }
}
