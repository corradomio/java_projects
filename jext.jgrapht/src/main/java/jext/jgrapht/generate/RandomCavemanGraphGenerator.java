package jext.jgrapht.generate;

import jext.jgrapht.util.Distrib;
import jext.jgrapht.util.distrib.ConstantDistrib;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ClusteringAlgorithm;
import org.jgrapht.generate.GraphGenerator;

import java.util.ArrayList;
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
    // size distribution for community sizes
    private Distrib communitySizes;
    // weight distribution for community edges
    private Distrib communityWeights;
    // weight distribution for edges between communities
    private Distrib betweenWeights;
    // if to select the community to update or to select
    // a random one
    private boolean randomCommunity;

    // random generator used with the edges
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
     * @param nCommunities n of communities
     * @param betweenProb probability of an edge to be connected between communities
     * @param insideProb probability of an edge to be connected into the community
     */
    public RandomCavemanGraphGenerator(
            int order, int size,
            int nCommunities,
            double betweenProb,
            double insideProb) {
        this.order = order;         // n vertices
        this.size = size;           // n edges
        this.n = nCommunities;      // n communities
        this.p = betweenProb;       // between communities
        this.q = insideProb;        // inside community
        this.communitySizes = new ConstantDistrib((0.+order)/nCommunities);
        this.betweenWeights = new ConstantDistrib(1);
        this.communityWeights = new ConstantDistrib(1);
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
     * Size distribution to use with communities
     * @param distrib distribution
     * @return itself
     */
    public RandomCavemanGraphGenerator<V, E> communitySizes(Distrib distrib) {
        communitySizes = distrib;
        return this;
    }


    /**
     * If to select the community to update
     */
    public RandomCavemanGraphGenerator<V, E> randomCommunity(boolean useRandom) {
        randomCommunity = useRandom;
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
        communitySizes.random(rnd);
        communityWeights.random(rnd);
        betweenWeights.random(rnd);
    }

    private void computeCommunitySizes() {
        csizes = new int[n];

        int total = 0;
        for(int c=0; c<n; ++c){
            int csz = (int)communitySizes.nextValue();
            csizes[c] = csz;
            total += csz;
        }

        // not enough vertices
        while (total < order) {
            int c = findSmallest();
            csizes[c] += 1;
            total += 1;
        }

        // too vertices
        while(total > order) {
            int c = findLargest();
            if (csizes[c] > 3) {
                csizes[c] -= 1;
                total -= 1;
            }
        }
    }

    private int findSmallest() {
        if (randomCommunity)
            return rnd.nextInt(n);

        int selected = 0;
        int csz = csizes[0];
        for(int c=0;c<n; ++c) {
            if (csizes[c] < csz) {
                selected = c;
                csz = csizes[c];
            }
        }
        return selected;
    }

    private int findLargest() {
        if (randomCommunity)
            return rnd.nextInt(n);

        int selected = 0;
        int csz = csizes[0];
        for(int c=0;c<n; ++c) {
            if (csizes[c] > csz) {
                selected = c;
                csz = csizes[c];
            }
        }
        return selected;
    }

    private void computeCommunityStarts() {
        cstart = new int[n+1];
        cstart[0] = 0;
        for(int c = 1; c <= n; ++c)
            cstart[c] = cstart[c-1] + csizes[c-1];
        // cstart[n] = cstart[n-1] + csizes[n-1];
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
