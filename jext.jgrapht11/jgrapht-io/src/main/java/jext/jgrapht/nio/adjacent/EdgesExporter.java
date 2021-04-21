package jext.jgrapht.nio.adjacent;

import org.jgrapht.Graph;
import org.jgrapht.nio.GraphExporter;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public class EdgesExporter<V, E> implements GraphExporter<V, E> {
    private String header;
    private String separator = " ";
    private Function<V, String> vtoString = Object::toString;

    public EdgesExporter<V, E> withHeader(String header) {
        this.header = header;
        return this;
    }

    /**
     * Select the character used as values separator.
     * Default: spaces
     * @param sep separator
     */
    public EdgesExporter<V, E> withSeparator(String sep) {
        this.separator = sep;
        return this;
    }

    public EdgesExporter<V, E> withToString(Function<V, String> toString) {
        this.vtoString = toString;
        return this;
    }

    @Override
    public void exportGraph(Graph<V, E> graph, Writer writer) {

        if (header != null) {
            try {
                writer.write(header);
                writer.write("\n");
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }

        if (graph.getType().isWeighted()) {
            graph.edgeSet().forEach(e -> {
                try {
                    V sourceVertex = graph.getEdgeSource(e);
                    V targetVertex = graph.getEdgeTarget(e);
                    double weight = graph.getEdgeWeight(e);
                    writer.write(vtoString.apply(sourceVertex));
                    writer.write(separator);
                    writer.write(vtoString.apply(targetVertex));
                    writer.write(separator);
                    writer.write(Double.toString(weight));
                    writer.write("\n");
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            });
        }
        else {
            graph.edgeSet().forEach(e -> {
                try {
                    V sourceVertex = graph.getEdgeSource(e);
                    V targetVertex = graph.getEdgeTarget(e);
                    writer.write(vtoString.apply(sourceVertex));
                    writer.write(separator);
                    writer.write(vtoString.apply(targetVertex));
                    writer.write("\n");
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            });
        }

    }
}
