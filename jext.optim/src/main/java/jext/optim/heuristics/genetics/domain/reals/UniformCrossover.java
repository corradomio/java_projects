package jext.optim.heuristics.genetics.domain.reals;

import jext.optim.heuristics.genetics.Chromosome;
import jext.optim.heuristics.genetics.ChromosomePair;
import jext.optim.heuristics.genetics.util.AbstractCrossoverPolicy;

import java.util.random.RandomGenerator;

public class UniformCrossover extends AbstractCrossoverPolicy<Vector> {

    private double prob;

    public UniformCrossover(double prob) {
        this.prob = prob;
    }

    @Override
    public ChromosomePair<Vector> crossover(Chromosome<Vector> first, Chromosome<Vector> second, RandomGenerator rng) {
        // Vector v1 = first.candidate();
        // Vector v2 = second.candidate();
        // double[] d1 = v1.data();
        // double[] d2 = v2.data();
        // int n = d1.length;
        //
        // double[] r1 = Arrays.copyOf(d1, n);
        // double[] r2 = Arrays.copyOf(d2, n);
        //
        // for (int i=0; i<n; ++i) {
        //     double r = rng.nextDouble();
        //     if (r < prob) {
        //         double t = r1[i];
        //         r1[i] = r2[i];
        //         r2[i] = t;
        //     }
        // }
        //
        // return ChromosomePair.of(new Vector(r1, v1), new Vector(r2, v2), first);

        Vector v1 = first.candidate();
        Vector v2 = second.candidate();

        Vector r1 = v1.clone();
        Vector r2 = v2.clone();

        int n = v1.length();

        for (int i=0; i<n; i++) {
            double r = rng.nextDouble();
            if (r < prob) {
                r1.set(i, v2.get(i));
                r2.set(i, v1.get(i));
            }
        }

        return ChromosomePair.of(r1, r2, first);
    }
}
