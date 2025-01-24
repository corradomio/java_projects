package jext.jgrapht.nio.json;

import jext.util.JSONUtils;
import jext.util.MapUtils;
import org.jgrapht.Graph;
import org.jgrapht.GraphType;
import org.jgrapht.nio.BaseExporter;
import org.jgrapht.nio.ExportException;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.IntegerIdProvider;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/jsongraph/json-graph-specification
 *
 * @param <V>
 * @param <E>
 */
public class JSONGraphExporter<V, E> extends BaseExporter<V, E> implements GraphExporter<V, E> {

    public JSONGraphExporter() {
        super(new IntegerIdProvider<>());
    }

    @Override
    public void exportGraph(Graph<V, E> g, Writer writer) {

        GraphType t = g.getType();

        try {
            Map<Object,Object> nodes = new HashMap<>();
            List< Map<String,Object>> edges = new ArrayList<>();

            g.vertexSet().forEach(v -> {
                nodes.put(wrap(v), vertexAttributes(v));
            });

            g.edgeSet().forEach(e -> {
                edges.add(MapUtils.asMap(
                    "source", wrap(g.getEdgeSource(e)),
                    "target", wrap(g.getEdgeTarget(e)),
                    "directed", t.isDirected()
                ));
            });

            Map<String,Object> result = MapUtils.asMap(
                "graph", MapUtils.asMap(
                    "directed", t.isDirected(),
                    "weighted", t.isWeighted(),
                    "type", t.toString(),
                    // "metadata", Collections.emptyMap(),
                    "nodes", nodes,
                    "edges", edges
                )
            );

            JSONUtils.save(writer, result);
        } catch (IOException e) {
            throw new ExportException(e);
        }
    }

    private Map<String, Object> graphAttributes(Graph<V, E> graph) {
        return Collections.emptyMap();
    }

    private Map<String, Object> vertexAttributes(V vertex) {
        return Collections.emptyMap();
    }

    private Map<String, Object> edgeAttributes(E edge) {
        return Collections.emptyMap();
    }

    private String wrap(V v) {
        // if (v == null)
        //     return null;
        // if (v instanceof Number)
        //     return v;
        // else
            return v.toString();
    }
}
