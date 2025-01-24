package jext.jgrapht.nio.csv;

import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.BaseExporter;
import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.IntegerIdProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Vertex/Edge CSV Graph Exported
 *
 * @param <V>
 * @param <E>
 */
public class VECSVExporter<V, E> extends BaseExporter<V, E> implements GraphExporter<V, E> {

    private Function<V, String> vtoString = Object::toString;

    public VECSVExporter() {
        super(new IntegerIdProvider<>());
    }

    @Override
    public void exportGraph(Graph<V, E> graph, Writer writer) {
        throw new UnsupportedOperationException("Unsupported 'exportGraph(g, Writer). To use 'exportGraph(g, File)'");
    }

    @Override
    public void exportGraph(Graph<V, E> graph, File file) {
        selectToString(graph);
        exportVertices(graph, file);
        exportEdges(graph, file);
    }

    private void selectToString(Graph<V, E> g) {
        // if (g.vertexSet().isEmpty())
        //     return;
        //
        // V v = g.vertexSet().iterator().next();
        // Class<?> vClass = v.getClass();
        // if (vClass == Integer.class || vClass == Long.class) {
        //     vtoString = Object::toString;
        // }
        // else if (vClass == String.class) {
        //     vtoString = v11 -> String.format("\"%s\"", v11);
        // }
        // else {
        //     vtoString = v12 -> String.format("\"%s\"", v12);
        // }
    }

    private String atoString(Attribute att) {
        if (att.getType() == AttributeType.STRING)
            return String.format("\"%s\"", att.getValue());
        else
            return att.getValue();
    }

    private void exportVertices(Graph<V, E> g, File file) {
        file = new File(file.getParentFile(), nameOf(file) + "-vertices.csv");

        try(Writer w = new FileWriter(file)) {
            String header = composeVerticesHeader(g);
            w.write(header);

            for (V v: g.vertexSet()) {
                StringBuilder bldr = new StringBuilder();
                bldr.append(vtoString.apply(v));

                Optional<Map<String, Attribute>> vatts = getVertexAttributes(v);
                vatts.ifPresent(map -> appendAttributes(bldr, map));

                bldr.append("\n");

                w.write(bldr.toString());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void exportEdges(Graph<V, E> g, File file) {
        file = new File(file.getParentFile(), nameOf(file) + "-edges.csv");

        try(Writer w = new FileWriter(file)) {
            String header = composeEdgesHeader(g);
            w.write(header);

            for (E e : g.edgeSet()) {
                StringBuilder bldr = new StringBuilder();
                bldr.append(vtoString.apply(g.getEdgeSource(e)))
                    .append(",")
                    .append(vtoString.apply(g.getEdgeTarget(e)));

                Optional<Map<String, Attribute>> eatts = getEdgeAttributes(e);
                eatts.ifPresent(map -> appendAttributes(bldr, map));

                bldr.append("\n");

                w.write(bldr.toString());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void appendAttributes(StringBuilder bldr, Map<String, Attribute> atts) {
        for (String key : atts.keySet())
            bldr.append(",")
                .append(atoString(atts.get(key)));
    }

    private String composeVerticesHeader(Graph<V, E> g) {
        StringBuilder sb = new StringBuilder("id");

        if (g.vertexSet().isEmpty())
            return sb.append("\n").toString();

        V v = g.vertexSet().iterator().next();
        Optional<Map<String, Attribute>> vatts = getVertexAttributes(v);
        if (!vatts.isPresent())
            return sb.append("\n").toString();

        for (String key : vatts.get().keySet())
            sb.append(",").append(key);

        return sb.append("\n").toString();
    }

    private String composeEdgesHeader(Graph<V, E> g) {
        StringBuilder sb =
            new StringBuilder("" +
                "# source,target -> direct edge\n" +
                "# id1,id2   -> undirected edge\n");

        if (g.getType().isDirected())
            sb.append("source,target");
        else
            sb.append("id1,id2");

        if (g.edgeSet().isEmpty())
            return sb.append("\n").toString();

        E e = g.edgeSet().iterator().next();
        Optional<Map<String, Attribute>> eatts = getEdgeAttributes(e);
        if (!eatts.isPresent())
            return sb.append("\n").toString();

        for (String key : eatts.get().keySet())
            sb.append(",").append(key);

        return sb.append("\n").toString();
    }

    private static String nameOf(File file) {
        String name = file.getName();
        if (name.endsWith(".csv")) {
            int pos = name.lastIndexOf('.');
            return name.substring(0, pos);
        }
        return name;
    }
}
