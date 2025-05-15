package org.apache.commons.math4.legacy.ml.clustering;

import org.apache.commons.math4.legacy.ml.distance.DistanceMeasure;
import org.apache.commons.math4.legacy.ml.distance.EuclideanDistance;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BisectingKMeansPlusPlusClusterer<T extends Clusterable> extends Clusterer<T> {

    private final int maxSize;
    private final int maxIterations;
    private final UniformRandomProvider random;

    public BisectingKMeansPlusPlusClusterer(final int maxSize) {
        this(maxSize, Integer.MAX_VALUE);
    }

    public BisectingKMeansPlusPlusClusterer(final int maxSize, final int maxIterations) {
        this(maxSize, maxIterations, new EuclideanDistance());
    }

    public BisectingKMeansPlusPlusClusterer(final int maxSize, final int maxIterations, final DistanceMeasure measure) {
        this(maxSize, maxIterations, measure, RandomSource.MT_64.create());
    }

    public BisectingKMeansPlusPlusClusterer(final int maxSize, final int maxIterations, final DistanceMeasure measure, final UniformRandomProvider random) {
        super(measure);
        this.maxSize = maxSize;
        this.maxIterations = maxIterations;
        this.random = random;
    }

    @Override
    public List<? extends Cluster<T>> cluster(Collection<T> points) {
        boolean modified = true;
        KMeansPlusPlusClusterer<T> algo;
        DistanceMeasure measure = getDistanceMeasure();

        if (points.size() <= maxSize)
            algo = new KMeansPlusPlusClusterer<>(1, maxIterations, measure, random);
        else
            algo = new KMeansPlusPlusClusterer<>(2, maxIterations, measure, random);

        // create the starting
        List<CentroidCluster<T>> clusters = algo.cluster(points);

        algo = new KMeansPlusPlusClusterer<>(2, maxIterations, getDistanceMeasure());

        while (modified) {
            modified = false;
            List<CentroidCluster<T>> nclusters = new ArrayList<>();

            for (CentroidCluster<T> cc : clusters) {
                if (cc.getPoints().size() <= maxSize) {
                    nclusters.add(cc);
                    continue;
                }

                List<CentroidCluster<T>> bisected = algo.cluster(cc.getPoints());
                nclusters.addAll(bisected);

                modified = true;
            }

            clusters = nclusters;
        }

        return clusters;
    }
}
