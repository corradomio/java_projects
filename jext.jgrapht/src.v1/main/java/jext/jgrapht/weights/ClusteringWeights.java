package jext.jgrapht.weights;

import jext.jgrapht.WeightType;

public interface ClusteringWeights<V, E> {

    int getGraphSize();

    int getClustersCount(int ci, int cj);

    int getInternalCount(int c);

    int getExternalCount(int c);

    // --

    double getGraphWeight(WeightType weightType);
    double getClustersWeight(int ci, int cj, WeightType weightType);
    double getInternalWeight(int c, WeightType weightType);
    double getExternalWeight(int c, WeightType weightType);

    // -- similarity

    /*
        Modularity is one measure of the structure of networks or graphs. It was designed to measure the strength
        of division of a network into modules (also called groups, clusters or communities). Networks with **high
        modularity** have dense connections between the nodes within modules but sparse connections between nodes in
        different modules. Modularity is often used in optimization methods for detecting community structure in
        networks. However, it has been shown that modularity suffers a resolution limit and, therefore, it is
        unable to detect small communities. Biological networks, including animal brains, exhibit a high degree of
        modularity.

        similarity: high values inside
                    low values outside
     */
    double getModularity();

    double getLouvainModularity();

    // -- distance == dissimilarity

    double getDunnIndex();

    double getDaviesBouldinIndex();

    // --

    double getSilhouette(V v);
}