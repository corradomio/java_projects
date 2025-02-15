package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.Population;
import jext.optim.heuristics.genetics.StoppingCondition;
import jext.util.TPrint;

public class LogGeneration implements StoppingCondition {

    private int g = 0;

    @Override
    public boolean isSatisfied(Population population) {
        ++g;
        TPrint.printf("[%7d]: %.6f\n", g, population.getFittestChromosome().fitness());
        return false;
    }
}
