package jext.optim.heuristics.genetics.stopping;

import jext.optim.heuristics.genetics.StoppingCondition;
import org.apache.commons.math4.legacy.exception.NumberIsTooSmallException;
import org.apache.commons.math4.legacy.genetics.FixedElapsedTime;

public class ElapsedTime extends FixedElapsedTime implements StoppingCondition {

    public ElapsedTime(long maxTime) throws NumberIsTooSmallException {
        super(maxTime);
    }

}
