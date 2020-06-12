package jext.jgrapht.generate;

import jext.jgrapht.util.Distrib;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.generate.GraphGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomCavemanGraphGenerator<V, E> implements GraphGenerator<V, E, List<Set<V>>> {

    // generated graph
    private Graph<V, E> graph;

    // n of vertices
    private final int order;
    // n of edges
    private final int size;
    // n of communities
    private final int n;
    // probability of an edge in community
    private final double q;
    // probability of an edge between communities
    private final double p;
    // minimum dimension of a clique
    private final int clique;
    // weight distribution for community edges
    private Distrib communityWeights;
    // weight distribution for edges between communities
    private Distrib betweenWeights;

    // rando generator used with the edges
    private Random rnd = new Random();

    // generated vertices
    private V[] vertices;
    // community sizes
    private int[] csizes;
    // start index of community vertices
    private int[] cstart;
    // generated communities
    private List<Set<V>> communities;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    /**
     *Generate a graph using the "Caveman" model
     *
     * @param order n of vertices
     * @param size n of edges
     * @param n n of communities
     * @param clique minimum size of a community
     * @param p probability of an edge to be connected between communities
     * @param q probability of an edge to be connected into the community
     */
    public RandomCavemanGraphGenerator(int order, int size, int n, int clique, double p, double q) {
        this.order = order;
        this.size = size;
        this.n = n;
        this.clique = clique;
        this.p = p;
        this.q = q;
    }

    /**
     * Seed for the random generator
     * @param seed seed
     * @return itself
     */
    public RandomCavemanGraphGenerator<V, E> seed(long seed) {
        this.rnd = new Random(seed);
        return this;
    }

    /**
     * Weight distribution to use with community edges
     * @param distrib distribution
     * @return itself
     */
    public RandomCavemanGraphGenerator<V, E> communityWeights(Distrib distrib) {
        communityWeights = distrib;
        return this;
    }

    /**
     * Weight distribution to use with edges between communities
     * @param distrib distribution
     * @return itself
     */
    public RandomCavemanGraphGenerator<V, E> betweenWeights(Distrib distrib) {
        betweenWeights = distrib;
        return this;
    }

    @Override
    public void generateGraph(Graph<V, E> target, Map<String, List<Set<V>>> resultMap) {
        this.graph = target;

        initAlgorithm();
        computeCommunitySizes();
        computeCommunityStarts();

        addVertices();
        addEdgesAndWeights();

        composeCommunities(resultMap);
    }

    // ----------------------------------------------------------------------
    // Base clustering
    // ----------------------------------------------------------------------

    class Caves implements ClusteringAlgorithm.Clustering<V> {

        @Override
        public int getNumberClusters() {
            return communities.size();
        }

        @Override
        public List<Set<V>> getClusters() {
            return communities;
        }

        @Override
        public Iterator<Set<V>> iterator() {
            return communities.iterator();
        }
    }

    public ClusteringAlgorithm.Clustering<V> getClustering() {
        return new Caves();
    }

    public Graph<V, E> getGraph() {
        return graph;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void initAlgorithm() {
        communityWeights.setRandom(rnd);
        betweenWeights.setRandom(rnd);
    }

    private void computeCommunitySizes() {
        // sizes of each community
        csizes = new int[n];

        // generate 'n' (n of communities) random numbers
        // (plus a final '1.')
        double[] v = new double[n+1];
        for (int c = 1; c< n; ++c)
            v[c] = rnd.nextDouble();
        v[n] = 1.;
        Arrays.sort(v);

        // compute the 'difference' between two adjacent
        // values:   v'[i] = v[i+1]-v[i]
        for (int c = 0; c< n; ++c)
            v[c] = v[c+1]-v[c];

        // generate the community sizes
        int rest = order;
        for (int c = 0; c< n; ++c) {
            csizes[c] = (int)(order*v[c]);
            if (csizes[c] < clique)
                csizes[c] = clique;
            rest -= csizes[c];
        }

        // there are not enough vertices
        // add a vertex to each community
        for (int c=0; c<n && rest>0; ++c) {
            csizes[c] += 1;
            rest -= 1;
        }

        // there are too vertices
        // remove a vertex only from communities large enough
        for (int c=0; c<n && rest<0; ++c)
            if (csizes[c] > clique) {
                csizes[c] -= 1;
                rest += 1;
            }
    }

    private void computeCommunityStarts() {
        cstart = new int[n+1];
        cstart[0] = 0;
        for(int c = 1; c< n; ++c)
            cstart[c] = cstart[c-1] + csizes[c-1];
        cstart[n] = cstart[n-1] + csizes[n-1];
    }

    private void addVertices() {
        vertices = (V[]) new Object[order];
        for (int i=0; i<order; ++i)
            vertices[i] = graph.addVertex();
    }

    private void addEdgesAndWeights() {
        while (graph.edgeSet().size() != size) {
            int vi = rnd.nextInt(order);
            int vj = rnd.nextInt(order);
            int ci = communityOf(vi);
            int cj = communityOf(vj);
            double r = rnd.nextDouble();
            double weight = -1;

            // no loop
            if (vi == vj) continue;

            if (ci != cj) {
                // different communities
                if (r >= p)
                    continue;;
                if (betweenWeights != null)
                    weight = betweenWeights.nextValue();
            }
            else {
                // same community
                if (r >= q)
                    continue;
                if (communityWeights != null)
                    weight = communityWeights.nextValue();
            }
            V source = vertices[vi];
            V target = vertices[vj];

            E e = graph.addEdge(source, target);
            if (e != null && weight != -1) // edge not present AND valid weight
                graph.setEdgeWeight(e, weight);
        }
    }

    private int communityOf(int v) {
        for (int c=0; c<n; ++c)
            if (v < cstart[c+1])
                return c;
        throw new IllegalStateException("Invalid vertex " + v);
    }

    private void composeCommunities(Map<String, List<Set<V>>> resultMap) {
        communities = new ArrayList<>();
        int k = csizes.length;
        for (int i=0; i<k; ++i) {
            Set<V> community = new HashSet<>();
            for (int j=cstart[i]; j<cstart[i+1]; ++j)
                community.add(vertices[j]);
            communities.add(community);
        }

        if (resultMap != null) {
            resultMap.put("communities", communities);
        }
    }
}
