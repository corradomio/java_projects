package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.StoppingCondition;
import org.apache.commons.math4.legacy.exception.NumberIsTooSmallException;
import org.apache.commons.math4.legacy.genetics.FixedGenerationCount;

public class GenerationCount extends FixedGenerationCount implements StoppingCondition {

    public GenerationCount(int maxGenerations) throws NumberIsTooSmallException {
        super(maxGenerations);
    }

}
