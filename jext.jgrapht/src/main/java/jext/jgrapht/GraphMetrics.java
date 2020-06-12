package jext.jgrapht;

import jext.jgrapht.alg.closure.GraphComponents;
import jext.jgrapht.util.Statistics;
import org.jgrapht.Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reference: Machine Learning in Complex Network
 */
public class GraphMetrics<V, E> /*extends org.jgrapht.GraphMetrics*/ {

    private static org.jgrapht.GraphMetrics gm;

    private final Graph<V, E> graph;
    private final EdgeType edgeType;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public GraphMetrics(Graph<V, E> g) {
        this(g, EdgeType.UNDIRECTED);
    }

    public GraphMetrics(Graph<V, E> g, EdgeType et) {
        this.graph = g;
        this.edgeType = et;
    }

    // ----------------------------------------------------------------------
    //
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

    public int degreeOf(V vertex) {
        switch(edgeType){
            case UNDIRECTED:
                return graph.degreeOf(vertex);
            case IN_EDGE:
                return graph.inDegreeOf(vertex);
            case OUT_EDGE:
            default:
                return graph.outDegreeOf(vertex);
        }
    }

    public int getMaxDegree() {
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

    public Map<V, Integer> getDegrees() {
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

    public long[] vertexDegrees(){
        int n = graph.vertexSet().size();
        long[] degrees = new long[n];

        int[] i=new int[1];
        graph.vertexSet()
                .forEach(v -> {
                    degrees[i[0]++] = degreeOf(v);
                });
        return degrees;
    }

    // ----------------------------------------------------------------------

    private static long sq(long x) { return x*x; }
    private static double sq(double x) { return x*x; }

    public static class VertexStatistics extends Statistics {
        public int order;
        public int components;

        VertexStatistics() { }

        public void print() {
            System.out.printf("Vertices statistics\n");
            System.out.printf("  Order (n vertices): %d\n", order);
            System.out.printf("  Components: %d\n", components);
            System.out.printf("  Min degree: %f\n", min);
            System.out.printf("  Max degree: %f\n", max);
            System.out.printf("  Mean degree: %f, %f\n", mean, standardDeviation);
            System.out.printf("End\n");
        }
    }

    public VertexStatistics getVertexStatistics() {
        VertexStatistics ds = new VertexStatistics();
        ds.order = graph.vertexSet().size();
        ds.components = new GraphComponents<>(graph).getComponents(EdgeType.UNDIRECTED).size();

        graph.vertexSet()
                .stream()
                .map(this::degreeOf)
                .forEach(ds::add);

        return (VertexStatistics) ds.finish();
    }

    public double getDensity() {
        long nVertices = graph.vertexSet().size();
        double nEdges = graph.edgeSet().size();
        double tEdges = sq(nVertices);

        // maximum number of edges:
        // simple graph:  V(V-1)/2
        // direct graph:  V*(V-1)
        // simple graph + loop: V*V/2
        // direct graph + loop: V*V

        if (!graph.getType().isAllowingCycles())
            tEdges -= nVertices;
        if (!graph.getType().isDirected())
            tEdges /= 2;

        return nEdges / tEdges;
    }

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
            degreeMultiply = degreeSource*degreeTarget;
            degreeSum = degreeSource + degreeTarget;
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
               (.5*iEdges*a.degreeSquared)  - sq(.5*iEdges*a.degreeSum);
    }

    // ----------------------------------------------------------------------

    public static class EdgeStatistics extends Statistics {
        public int size;
        public int order;

        EdgeStatistics() { }

        public void print() {
            System.out.printf("Edges statistics\n");
            System.out.printf("  Size       : %d\n", size);
            System.out.printf("  Density    : %f\n", (2.*size)/(order*(order-1)));
            System.out.printf("  Weight     : %f\n", sum1);
            System.out.printf("  Min weight : %f\n", min);
            System.out.printf("  Max weight : %f\n", max);
            System.out.printf("  Mean weight: %f, %f\n", mean, standardDeviation);
            System.out.printf("End\n");        }
    }

    public EdgeStatistics getEdgeStatistics() {
        EdgeStatistics es = new EdgeStatistics();
        es.size  = graph.edgeSet().size();
        es.order = graph.vertexSet().size();

        graph.edgeSet()
                .stream()
                .map(this::weightOf)
                .forEach(es::add);

        return (EdgeStatistics) es.finish();
    }

    public double[] getEdgeWeights() {
        double[] weights = new double[graph.edgeSet().size()];
        int[] i = new int[1];
        graph.edgeSet()
                .stream()
                .map(graph::getEdgeWeight)
                .collect(Collectors.toList())
                .forEach(w -> {
                    weights[i[0]++] = w;
                });
        return weights;
    }

    public double weightOf(E e) {
        return graph.getEdgeWeight(e);
    }

    public double[] edgeWeights(){
        int n = graph.edgeSet().size();
        double[] weights = new double[n];

        int[] i=new int[1];
        graph.edgeSet()
                .forEach(e -> {
                    weights[i[0]++] = weightOf(e);
                });
        return weights;
    }

}
