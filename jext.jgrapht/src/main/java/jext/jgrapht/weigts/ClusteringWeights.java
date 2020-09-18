package jext.jgrapht.weigts;

public interface ClusteringWeights<V, E> {

    int getClustersCount(int ci, int cj);

    int getInternalCount(int c);

    int getExternalCount(int c);

    // --

    double getClustersWeight(int ci, int cj);

    double getInternalWeight(int c);

    double getExternalWeight(int c);

    // -- similarity

    double getModularity();

    double getLouvainModularity();

    // -- distance == dissimilarity

    double getDunnIndex();

    double getDaviesBouldinIndex();

    // --

    double getSilhouette(V v);
}
