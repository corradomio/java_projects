package jext.optim.heuristics.sa;

public interface TemperatureScheduler {

    double temperature(double maxTemperature, int generationCount);
}
