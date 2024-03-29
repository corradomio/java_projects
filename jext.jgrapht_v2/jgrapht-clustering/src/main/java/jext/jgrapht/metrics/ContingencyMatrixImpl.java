package jext.jgrapht.metrics;

import jext.jgrapht.util.MathUtil;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jext.jgrapht.util.MathUtil.div;
import static jext.jgrapht.util.MathUtil.log2;
import static jext.jgrapht.util.MathUtil.sq;
import static jext.jgrapht.util.MathUtil.sqrt;
import static jext.jgrapht.util.MathUtil.sum;

public class ContingencyMatrixImpl<V> implements ContingencyMatrix {

    ClusteringAlgorithm.Clustering<V> truth;

    private int k;
    private int kd;
    private int kt;
    private int[][] m;
    private int[] ni;
    private int[] mj;
    private int n;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ContingencyMatrixImpl(ClusteringAlgorithm.Clustering<V> truth) {
        this.truth = truth;
    }


    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ContingencyMatrixImpl<V> using(ClusteringAlgorithm.Clustering<V> other) {
        this.k = other.getNumberClusters();

        this.kt = truth.getNumberClusters();
        this.kd = other.getNumberClusters();
        this.m  = MathUtil.intMatrix(kt, kd);
        this.ni = MathUtil.intVector(kt);
        this.mj = MathUtil.intVector(kd);

        Set<V> v1 = verticesOf(truth);
        Set<V> v2 = verticesOf(other);
        if (!union(v1, v2).equals(intersection(v1, v2)))
            throw new IllegalArgumentException("Invalid clustering");

        // contingency matrix
        for(V v : v1) {
            int ci = clusterOf(v, truth);
            int cj = clusterOf(v, other);
            m[ci][cj] += 1;
        }

        // sum by columns (ni)/rows (mj
        for(int ci = 0; ci < kt; ++ci) {
            for (int cj=0; cj<kd; ++cj) {
                int nij = m[ci][cj];
                ni[ci] += nij;
                mj[cj] += nij;
                n += nij;
            }
        }

        return this;
    }

    private Set<V> verticesOf(ClusteringAlgorithm.Clustering<V> clustering) {
        Set<V> vertices = new HashSet<>();
        clustering.getClusters().forEach(vertices::addAll);
        return vertices;
    }

    private int clusterOf(V v, ClusteringAlgorithm.Clustering<V> clustering) {
        int k = clustering.getNumberClusters();
        List<Set<V>> clusters = clustering.getClusters();
        for (int i=0; i<k; ++i)
            if (clusters.get(i).contains(v))
                return i;
        return -1;
    }

    private static <T> Set<T> union(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>();
        r.addAll(s1);
        r.addAll(s2);
        return r;
    }

    private static <T> Set<T> intersection(Set<T> s1, Set<T> s2) {
        Set<T> r = new HashSet<T>(s1);
        r.retainAll(s2);
        return r;
    }

    // ----------------------------------------------------------------------
    // Metrics
    // ----------------------------------------------------------------------

    /**
     * n: n vertices
     * k: n clusters
     * nj: n vertices cluster j
     *
     * 2020 - Analysis of a parallel MCMC algorithm for graph coloring with nearly uniform balancing
     * eq (8)
     */
    public double getUnbalancingIndex() {
        // int[] i = new int[1];
        // int k = getNumberClusters();
        // int[] nj = new int[k];
        //
        // clustering.getClusters().forEach(cluster -> {
        //     int csize = cluster.size();
        //     nj[i[0]] = csize;
        //     i[0] += 1;
        // });

        // double n = sum(mj);
        // double nk = n/k;
        //
        // double ui = 0;
        // for (int j=0; j<k; ++j) {
        //     ui += sq(mj[j] - nk);
        // }
        // ui = Math.sqrt(ui/k);
        //
        // return ui;

        double n = sum(ni);
        double nkt = n/kt;

        double ui = 0;
        for (int i=0; i<kt; ++i) {
            ui += sq(ni[i] - nkt);
        }
        ui = Math.sqrt(ui/kt);

        return ui;
    }

    public double getPurity() {
        double num = 0;
        double den = 0;

        for(int j=0; j<kd; j++) {
            num += P(j);
            den += M(j);
        }

        return div(num, den);
    }

    public double getGiniIndex() {
        double num = 0;
        double den = 0;

        for(int j=0; j<kd; j++){
            num += G(j)*M(j);
            den += M(j);
        }

        return div(num, den);
    }

    public double getEntropy() {
        double num = 0;
        double den = 0;

        for(int j=0; j<kd; j++){
            num += E(j)* M(j);
            den += M(j);
        }

        return div(num, den);
    }

    public double getRandIndex() {
        compute();

        double num = a + d;
        double den = a + b + c + d; // == n*(n-1)/2

        return div(num, den);
    }

    public double getAdjustedRandIndex() {
        // https://en.wikipedia.org/wiki/Rand_index
        compute();

        // nij*(nij-1)/2
        double nij1h = nij1/2.;

        // ni*(ni-1)/2
        double ni1h = 0;
        for(int i=0; i<kt; ++i) {
            ni1h += ni[i] * (ni[i] - 1);
        }
        ni1h /= 2;

        // mj*(mj-1)/2
        double mj1h = 0;
        for(int j=0; j<kd; ++j) {
            mj1h += mj[j] * (mj[j] - 1);
        }
        mj1h /= 2.;

        // n*(n-1)/2
        double n1h = n*(n-1)/2.;

        double num = nij1h - div(ni1h*mj1h, n1h);
        double den = (ni1h + mj1h)/2 - div(ni1h*mj1h, n1h);

        return div(num, den);
    }

    public double getJaccardIndex() {
        compute();

        double num = a;
        double den = a + b + c;

        return div(num, den);
    }

    public double getFowlkesMallowsIndex() {
        compute();

        double m1 = a + b;
        double m2 = a + c;

        double num = a;
        double den = sqrt(m1*m2);

        return div(num, den);
    }

    /**
     *
     * M = a+b+c+d
     * m1 = a+b
     * m2 = a+c
     *              (M*a - m1*m2)
     * normGamma = -----------------------------------
     *              sqrt(m1*m2*(M - m1)*(M - m2))
     *
     * Theodoridis - 16.10
     */
    public double getNormalizedGamma() {
        compute();

        double M = a + b + c + d;
        double m1 = a + b;
        double m2 = a + c;

        double num = (M*a - m1*m2);
        double den = sqrt(m1*m2*(M - m1)*(M - m2));

        return div(num, den);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private int N(int i) {
        return ni[i];
    }

    private int M(int j) {
        return mj[j];
    }

    private int P(int j) {
        int maxi = m[0][j];
        for (int i=1; i<kt; ++i) {
            if (m[i][j] > maxi) {
                maxi = m[i][j];
            }
        }
        return maxi;
    }

    private double G(int j) {
        double gini = 1.;
        double mj = M(j);
        for (int i=0; i<kt; ++i) {
            gini -= sq(div(m[i][j], mj));
        }
        return gini;
    }

    private double E(int j) {
        double entropy = 0;
        for(int i=0; i<kt; ++i) {
            entropy -= div(m[i][j], M(j))*log2(div(m[i][j], M(j)));
        }
        return entropy;
    }

    // ----------------------------------------------------------------------

    private double n2;          // n^2
    private double nij1;        // nij(nij-1)
    private double nij2;        // nij^2
    private double ni2;         // ni^2
    private double mj2;         // mj^2
    private double a,b,c,d;
    private boolean done;

    private void compute() {
        if (done)
            return;
        else
            done = true;

        // n^2
        n2 = sq(n);

        // SUM nij(nij-1), SUM nij^2
        nij1 = 0;
        nij2 = 0;
        for (int i=0; i<kt; ++i)
            for(int j=0; j<kd; ++j) {
                int nij = m[i][j];
                nij1 += nij*(nij - 1);
                nij2 += sq(nij);
            }

        // SUM ni^2
        ni2 = 0;
        for (int i=0; i<kt; ++i)
            ni2 += sq(ni[i]);

        // SUM mj^2
        mj2 = 0;
        for (int j=0; j<kd; ++j)
            mj2 += sq(mj[j]);

        // coefficients
        a = nij1/2;
        b = (mj2 - nij2)/2;
        c = (ni2 - nij2)/2;
        d = ((n2 + nij2) - (ni2 + mj2))/2;
    }
}
