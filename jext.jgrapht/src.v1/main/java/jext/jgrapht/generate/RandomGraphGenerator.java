package jext.jgrapht.generate;

import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomGraphGenerator<V, E> implements GraphGenerator<V, E, V> {

    private final int nVertices;
    private final int nEdges;
    private final Random random;

    /**
     *  Create a new $G(n, p)$ random graph generator. The generator does not create self-loops.
     *
     * @param n the number of nodes
     * @param m the number of edges
     */
    public RandomGraphGenerator(int n, int m) {
        this(n, m, new Random());
    }

    public RandomGraphGenerator(int n, int m, long seed) {
        this(n, m, new Random(seed));
    }

    public RandomGraphGenerator(int n, int m, Random r) {
        nVertices = n;
        nEdges = m;
        random = r;
    }

    @Override
    public void generateGraph(Graph<V, E> graph, Map<String, V> resultMap) {

        for(int i=0; i<nVertices; ++i)
            graph.addVertex();

        List<V> nodes = new ArrayList<>(graph.vertexSet());
        while(graph.edgeSet().size() < nEdges)
            try {
                V source = nodes.get(random.nextInt(nVertices));
                V target = nodes.get(random.nextInt(nVertices));
                graph.addEdge(source, target);
            }
            catch (Exception e){ }
    }
}
