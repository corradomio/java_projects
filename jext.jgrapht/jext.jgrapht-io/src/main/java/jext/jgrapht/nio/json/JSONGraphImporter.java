package jext.jgrapht.nio.json;

import jext.util.JSONUtils;
import jext.util.MapUtils;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;
import org.jgrapht.nio.ImportException;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * https://github.com/jsongraph/json-graph-specification
 *
 * @param <V>
 * @param <E>
 */
public class JSONGraphImporter<V, E> implements GraphImporter<V, E>  {

    private Map<String, V> vertices = new HashMap<>();
    private Supplier<V> vertexSupplier;

    @Override
    public void importGraph(Graph<V, E> g, Reader in) {

        vertexSupplier = g.getVertexSupplier();

        try {
            Map<String, Object> data = JSONUtils.parse(in, LinkedHashMap.class);
            Map<String, Object> graph = MapUtils.get(data, "graph");
            if (graph == null)
                throw new ImportException("Graph does not contain graph");

            /*
                "graph": {
                    "directed": false,
                    "type": "graph type",
                    "label": "graph label",
                    "metadata": {
                      "user-defined": "values"
                    },
                    ...
                }
             */

            boolean directed = MapUtils.getOrDefault(graph, "directed", false);
            Map<String, Map<String, Object>> nodes = MapUtils.get(graph, "nodes");
            List<Map<String, Object>> edges = MapUtils.get(graph, "edges");

            /*
                "nodes": {
                    "0": {
                        "label": "node label(0)",
                        "metadata": {
                            "type": "node type",
                            "user-defined": "values"
                        }
                    },
                    "1": {
                        "label": "node label(1)",
                        "metadata": {
                            "type": "node type",
                            "user-defined": "values"
                        }
                    }
                },
            */
            for(String v : nodes.keySet()) {
                V vertex = vertexOf(v);
                g.addVertex(vertex);
            }

            /*
            "edges": [
                {
                    "source": "0",
                    "relation": "edge relationship",
                    "target": "1",
                    "directed": false,
                    "label": "edge label",

                    "weight": 1.0,              // is this the correct way?

                    "metadata": {
                        "user-defined": "values"
                    }
                }
            ]
             */
            for(Map<String, Object> edge : edges) {
                String source = MapUtils.getString(edge, "source");
                String target = MapUtils.getString(edge, "target");

                V vertexSource = vertexOf(source);
                V vertexTarget = vertexOf(target);

                directed = MapUtils.getOrDefault(graph, "directed", false);
                double weight = MapUtils.getOrDefault(graph, "weight", 1.0);

                g.addEdge(vertexSource, vertexTarget);

                if (weight != 1.0)
                    g.setEdgeWeight(vertexSource, vertexTarget, weight);
            }


        } catch (IOException e) {
            throw new ImportException(e);
        }

    }

    private V vertexOf(String id) {
        if (!vertices.containsKey(id))
            vertices.put(id, vertexSupplier.get());
        return vertices.get(id);
    }
}
