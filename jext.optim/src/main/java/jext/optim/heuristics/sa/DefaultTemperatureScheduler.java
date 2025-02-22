package jext.optim.heuristics.sa;

public class DefaultTemperatureScheduler implements TemperatureScheduler {

    private final int maxGenerations;

    public DefaultTemperatureScheduler(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    @Override
    public double temperature(double maxTemperature, int generationCount) {
        return maxTemperature - (maxTemperature * generationCount) / maxGenerations;
    }
}
