package jext.jgrapht;

import jext.jgrapht.alg.closure.GraphComponents;
import jext.jgrapht.edges.EdgeType;
import jext.jgrapht.util.Statistics;
import org.jgrapht.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static jext.jgrapht.util.Utils.sq;

/**
 * Reference: Machine Learning in Complex Network
 */
public class GraphMetrics<V, E> /*extends org.jgrapht.GraphMetrics*/ {

    private final Graph<V, E> graph;
    private final EdgeType edgeType;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public GraphMetrics(Graph<V, E> g) {
        this(g, EdgeType.UNDIRECTED);
    }

    public GraphMetrics(Graph<V, E> g, EdgeType et) {
        this.graph = g;
        this.edgeType = et;
    }

    // ----------------------------------------------------------------------
    // From org.jgrapht.GraphMetrics
    // ----------------------------------------------------------------------

    public double getDiameter() {
        return org.jgrapht.GraphMetrics.getDiameter(graph);
    }

    public double getRadius() {
        return org.jgrapht.GraphMetrics.getRadius(graph);
    }

    public int getGirth() {
        return org.jgrapht.GraphMetrics.getGirth(graph);
    }

    public long getNumberOfTriangles() {
        return org.jgrapht.GraphMetrics.getNumberOfTriangles(graph);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Number of vertices
     */
    public int getOrder() {
        return graph.vertexSet().size();
    }

    /**
     * Number of edges
     */
    public int getSize() {
        return graph.edgeSet().size();
    }

    /**
     * Density: number of edges divided by the number of edges in a clique
     */
    public double getDensity() {
        long nVertices = getOrder();
        double nEdges = getSize();
        double tEdges = sq(nVertices);

        if (nVertices == 0)
            return 0;

        // maximum number of edges:
        // simple graph:  V*(V-1)/2
        // direct graph:  V*(V-1)
        // simple graph + loop: V*V/2
        // direct graph + loop: V*V

        if (!graph.getType().isAllowingCycles())
            tEdges -= nVertices;
        if (!graph.getType().isDirected())
            tEdges /= 2;

        return nEdges / tEdges;
    }

    // ----------------------------------------------------------------------
    // Assortativity
    // ----------------------------------------------------------------------

    private static class Assortativity {
        final long degreeMultiply;
        final long degreeSum;
        final long degreeSquared;

        static Assortativity instance = new Assortativity();
        static Assortativity empty() { return instance; }

        private Assortativity() {
            degreeMultiply = 0;
            degreeSum = 0;
            degreeSquared = 0;
        }

        Assortativity(Assortativity a1, Assortativity a2){
            degreeMultiply = a1.degreeMultiply + a2.degreeMultiply;
            degreeSum = a1.degreeSum + a2.degreeSum;
            degreeSquared = a1.degreeSquared + a2.degreeSquared;
        }

        Assortativity(int degreeSource, int degreeTarget){
            degreeMultiply = (long)degreeSource*degreeTarget;
            degreeSum = (long)degreeSource + degreeTarget;
            degreeSquared = sq(degreeSource) + sq(degreeTarget);
        }

        Assortativity sum(Assortativity other) {
            return new Assortativity(this, other);
        }
    }

    public double getAssortativity() {
        int nEdges = graph.edgeSet().size();
        Assortativity a = graph.edgeSet().parallelStream()
                .map(e -> {
                    V sourceVertex = graph.getEdgeSource(e);
                    V targetVertex = graph.getEdgeTarget(e);
                    return new Assortativity(graph.degreeOf(sourceVertex), graph.degreeOf(targetVertex));
                }).reduce(Assortativity::sum).orElse(Assortativity.empty());

        if (nEdges == 0) return 0.;

        double iEdges = 1./nEdges;
        return (   iEdges*a.degreeMultiply) - sq(.5*iEdges*a.degreeSum) /
                (.5*iEdges*a.degreeSquared) - sq(.5*iEdges*a.degreeSum);
    }

    // ----------------------------------------------------------------------
    // Vertex degree
    // ----------------------------------------------------------------------

    public int degreeOf(V vertex) {
        return degreeOf(vertex, edgeType);
    }

    public int getMaxDegree() {
        return getMaxDegree(edgeType);
    }

    public Map<V, Integer> getDegrees() {
        return getDegrees(edgeType);
    }

    public long[] getVerticesDegree(){
        return getVerticesDegree(edgeType);
    }

    public int degreeOf(V vertex, EdgeType edgeType) {
        switch(edgeType){
            // case UNDIRECTED:
            //     return graph.degreeOf(vertex);
            case IN_EDGE:
                return graph.inDegreeOf(vertex);
            case OUT_EDGE:
                return graph.outDegreeOf(vertex);
            default:
                return graph.degreeOf(vertex);
        }
    }

    public int getMaxDegree(EdgeType edgeType) {
        switch (edgeType) {
            case IN_EDGE:
                return graph.vertexSet()
                        .parallelStream()
                        .map(graph::inDegreeOf)
                        .max(Comparator.comparingInt(i -> i)).orElse(0);
            case OUT_EDGE:
                return graph.vertexSet()
                        .parallelStream()
                        .map(graph::outDegreeOf)
                        .max(Comparator.comparingInt(i -> i)).orElse(0);
            default:
                return graph.vertexSet()
                        .parallelStream()
                        .map(graph::degreeOf)
                        .max(Comparator.comparingInt(i -> i)).orElse(0);
        }
    }

    public  Map<V, Integer> getDegrees(EdgeType edgeType) {
        Map<V, Integer> degrees = new HashMap<>();
        switch (edgeType) {
            case IN_EDGE:
                graph.vertexSet().forEach(v -> {
                    degrees.put(v, graph.inDegreeOf(v));
                });
            case OUT_EDGE:
                graph.vertexSet().forEach(v -> {
                    degrees.put(v, graph.outDegreeOf(v));
                });
                break;
            default:
                graph.vertexSet().forEach(v -> {
                    degrees.put(v, graph.degreeOf(v));
                });
                break;
        }
        return degrees;
    }

    public long[] getVerticesDegree(EdgeType edgeType){
        int n = getOrder();
        long[] degrees = new long[n];

        int[] i = new int[1];
        graph.vertexSet()
                .forEach(v -> {
                    degrees[i[0]++] = degreeOf(v, edgeType);
                });
        return degrees;
    }

    // ----------------------------------------------------------------------
    // VertexStatistics
    // ----------------------------------------------------------------------

    public static class VertexStatistics extends Statistics {
        public int order;

        VertexStatistics() { }

        public void print() {
            System.out.printf("Vertices statistics\n");
            System.out.printf("  Order (n vertices): %d\n", order);
            System.out.printf("  Min degree: %.4f\n", min);
            System.out.printf("  Max degree: %.4f\n", max);
            System.out.printf("  Mean degree: %.4f, %.4f\n", mean, standardDeviation);
            System.out.printf("End\n");
        }
    }

    public VertexStatistics getVertexStatistics() {
        VertexStatistics ds = new VertexStatistics();
        ds.order = getOrder();

        graph.vertexSet()
                .stream()
                .map(this::degreeOf)
                .forEach(ds::add);

        return (VertexStatistics) ds.finish();
    }

    // ----------------------------------------------------------------------
    // Edge weight
    // ----------------------------------------------------------------------

    // public double[] getEdgeWeights() {
    //     int n = graph.edgeSet().size();
    //     double[] weights = new double[n];
    //     int[] i = new int[1];
    //
    //     graph.edgeSet()
    //             .stream()
    //             .map(graph::getEdgeWeight)
    //             .collect(Collectors.toList())
    //             .forEach(w -> {
    //                 weights[i[0]++] = w;
    //             });
    //     return weights;
    // }

    public double weightOf(E e) {
        return graph.getEdgeWeight(e);
    }

    public double[] getEdgesWeight(){
        int n = graph.edgeSet().size();
        double[] weights = new double[n];
        int[] i=new int[1];

        graph.edgeSet()
                .forEach(e -> {
                    weights[i[0]++] = weightOf(e);
                });
        return weights;
    }

    // ----------------------------------------------------------------------
    // EdgeStatistics
    // ----------------------------------------------------------------------

    public static class EdgeStatistics extends Statistics {
        public int size;
        public int order;
        public int components;
        public double density;

        EdgeStatistics() { }

        public void print() {
            System.out.printf("Edges statistics\n");
            System.out.printf("  Size       : %d\n", size);
            System.out.printf("  Components : %d\n", components);
            System.out.printf("  Density    : %.4f\n", density);
            System.out.printf("  Weight     : %.4f\n", count*mean);
            System.out.printf("  Min weight : %.4f\n", min);
            System.out.printf("  Max weight : %.4f\n", max);
            System.out.printf("  Mean weight: %.4f, %.4f\n", mean, standardDeviation);
            System.out.printf("End\n");        }
    }

    public EdgeStatistics getEdgeStatistics() {
        EdgeStatistics es = new EdgeStatistics();
        es.order = getOrder();
        es.size  = getSize();
        es.density = getDensity();
        es.components = new GraphComponents<>(graph).getComponents(EdgeType.UNDIRECTED).size();

        graph.edgeSet()
                .stream()
                .map(this::weightOf)
                .forEach(es::add);

        return (EdgeStatistics) es.finish();
    }

}
