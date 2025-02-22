package jext.optim.heuristics.ga.stopping;

import jext.math.exception.NumberIsTooSmallException;
import jext.optim.heuristics.ga.Population;
import jext.optim.heuristics.ga.StoppingCondition;

import java.util.concurrent.TimeUnit;

public class FixedElapsedTime implements StoppingCondition {

    /** Maximum allowed time period (in nanoseconds). */
    private final long maxTimePeriod;

    /** The predetermined termination time (stopping condition). */
    private long endTime = -1;

    /**
     * Create a new {@link org.apache.commons.math4.legacy.genetics.FixedElapsedTime} instance.
     *
     * @param maxTime maximum number of seconds generations are allowed to evolve
     * @throws NumberIsTooSmallException if the provided time is &lt; 0
     */
    public FixedElapsedTime(final long maxTime) throws NumberIsTooSmallException {
        this(maxTime, TimeUnit.SECONDS);
    }

    /**
     * Create a new {@link org.apache.commons.math4.legacy.genetics.FixedElapsedTime} instance.
     *
     * @param maxTime maximum time generations are allowed to evolve
     * @param unit {@link TimeUnit} of the maxTime argument
     * @throws NumberIsTooSmallException if the provided time is &lt; 0
     */
    public FixedElapsedTime(final long maxTime, final TimeUnit unit) throws NumberIsTooSmallException {
        if (maxTime < 0) {
            throw new NumberIsTooSmallException(maxTime, 0, true);
        }
        maxTimePeriod = unit.toNanos(maxTime);
    }

    /**
     * Determine whether or not the maximum allowed time has passed.
     * The termination time is determined after the first generation.
     *
     * @param population ignored (no impact on result)
     * @return <code>true</code> IFF the maximum allowed time period has elapsed
     */
    @Override
    public boolean isSatisfied(final Population population) {
        if (endTime < 0) {
            endTime = System.nanoTime() + maxTimePeriod;
        }

        return System.nanoTime() >= endTime;
    }
}
