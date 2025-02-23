package jext.optim.heuristics.pbil;

import java.util.random.RandomGenerator;

public class ProbUtils {

    public static double[][] randomProbability(RandomGenerator rng, int length, int size) {

        double[][] prob = randomMatrix(rng, length, size);
        normalize(prob);

        return prob;
    }

    private static double[][] randomMatrix(RandomGenerator rng, int length, int size) {
        double[][] prob = new double[length][size];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < size; j++)
                prob[i][j] = rng.nextDouble();
        return prob;
    }

    public static void normalize(double[][] probs) {
        for (int i = 0; i < probs.length; i++)
            normalize(probs[i]);
    }

    public static void normalize(double[] probs) {
        double sum = sum(probs);
        for (int i=0; i<probs.length; i++)
            probs[i] /= sum;
    }

    public static double sum(double[] probs) {
        double sum = 0.0;
        for (int i = 0; i < probs.length; i++)
            sum += probs[i];
        return sum;
    }

    public static int nextMember(RandomGenerator rng, double[] membersProb) {
        int length = membersProb.length;
        int member = rng.nextInt(length);
        double r = rng.nextDouble();
        double prob = 0;
        for (int i = 0; i < length; i++) {
            member = (member+i) % length;
            prob += membersProb[member];
            if (r <= prob)
                return member;
        }

        return member;
    }

    /*
        p1+p2+p3 = 1

        p1' = p1 + delta
        p2' = p2 - delta / (p2 + p3) = p2 - delta p2 / (1 - p1)
        p3' = p3 - delta / (p2 + p3) = p3 - delta p3 / (1 - p1)
     */

    public static void improveMember(int improvedMember, double[] membersProb, double learningRate) {
        int size = membersProb.length;
        double memberProb = membersProb[improvedMember];
        double improveProb = (1 - memberProb) * learningRate;

        membersProb[improvedMember] = memberProb + improveProb;

        double partial = 1 - memberProb;

        for (int member=0; member<size; member++) {
            if (member != improvedMember) {
                memberProb = membersProb[member];
                membersProb[member] = memberProb - memberProb/partial*improveProb;
            }
        }
    }
}
