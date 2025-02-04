package jext.jgrapht.nio;

import org.jgrapht.nio.GraphExporter;
import org.jgrapht.nio.IntegerIdProvider;

public abstract class BaseExporter<V, E> extends org.jgrapht.nio.BaseExporter<V, E> implements GraphExporter<V, E> {

    public BaseExporter() {
        super(new IntegerIdProvider<>());
    }

    // public void exportGraph(Graph<V, E> g, File file) {
    //     try (Writer writer = new FileWriter(file, StandardCharsets.UTF_8)) {
    //         exportGraph(g, writer);
    //     } catch (IOException e) {
    //         throw new ExportException(e);
    //     }
    // }
}
