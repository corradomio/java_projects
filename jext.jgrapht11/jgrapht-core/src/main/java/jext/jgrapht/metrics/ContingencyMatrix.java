package jext.jgrapht.metrics;

public interface ContingencyMatrix {

    double getUnbalancingIndex();

    double getPurity();

    double getGiniIndex();

    double getEntropy();

    double getRandIndex();

    double getAdjustedRandIndex();

    double getJaccardIndex();

    double getFowlkesMallowsIndex();

    double getNormalizedGamma();
}
