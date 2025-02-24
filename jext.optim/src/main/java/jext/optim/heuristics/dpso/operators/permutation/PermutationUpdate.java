package jext.optim.heuristics.dpso.operators.permutation;

import jext.optim.domain.permutation.Permutation;
import jext.optim.heuristics.dpso.Particle;
import jext.optim.heuristics.dpso.Population;
import jext.optim.heuristics.dpso.UpdatePolicy;

import java.util.random.RandomGenerator;

import static java.lang.Math.max;

public class PermutationUpdate implements UpdatePolicy<Permutation> {

    @Override
    public boolean compareWith(Particle<Permutation> particle, Particle<Permutation> reference, int index) {
        return particle.candidate().get(index) == reference.candidate().get(index);
    }

    @Override
    public Particle<Permutation> updateParticle(Particle<Permutation> original, RandomGenerator rng, Population<Permutation> population) {
        Permutation candidate = original.candidate().clone();
        int length = candidate.length();
        double[] psticky = original.psticky();

        Particle<Permutation> pbest = population.getPopulationBest();
        Particle<Permutation> gbest = population.getGlobalBest();
        double stickiness = population.getStickiness();
        double is = population.getIs();
        double ip = population.getIp();
        double ig = population.getIg();

        for (int d=0; d<length; d++) {
            double cognitive = compareWith(original, pbest, d) ? 0 : ip;
            double social    = compareWith(original, gbest, d) ? 0 : ig;
            double pd = is*(1 - psticky[d]) + cognitive + social;

            double r = rng.nextDouble();
            if (r < pd) {
                candidate.flip(d);
                psticky[d] = 1;
            }
            else {
                psticky[d] = max(0, psticky[d] - stickiness);
            }
        }

        return new Particle<>(candidate, original);
    }
}
