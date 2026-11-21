package jext.jgrapht.nio.adjacent;

import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

/**
 * Import a graph defined by a list of edges:
 *
 *              1 2
 *              2 4
 *              ...
 *
 * if weighted
 *
 *              1 2 w12
 *              2 4 w24
 *              ...
 *
 * The separator can be space, comma, etc.
 * The header is optional (it can be a CSV file)
 */
public class EdgesGraphImporter<V, E> implements GraphImporter<V, E> {

    private Graph<V, E> g;
    private long vcount = 0;
    private long ecount = 0;
    private String separator = "\\s+";
    private String comment = "#";
    private int skipLines = 0;
    private Function<String, V> stoVertex = (x) -> (V)x;

    public EdgesGraphImporter() { }

    /**
     * Select the character used as values separator.
     * Default: spaces
     * @param sep separator
     */
    public EdgesGraphImporter<V, E> withSeparator(String sep) {
        this.separator = sep;
        return this;
    }

    /**
     * Select the character used as single line comment.
     * Default: '#'
     * @param comments first character used for single line comments
     */
    public EdgesGraphImporter<V, E> withComment(String comments) {
        this.comment = comments;
        return this;
    }

    /**
     * Skip a predefined number of header lines
     * Default: 0
     * @param nLines n of lines to skip
     */
    public EdgesGraphImporter<V, E> withSkipLines(int nLines) {
        this.skipLines = nLines;
        return this;
    }

    /**
     * Function used to convert a string in a vertex of type V
     * Default: identity
     * @param toVertex function String->V
     */
    public EdgesGraphImporter<V, E> withToVertex(Function<String, V> toVertex) {
        this.stoVertex = toVertex;
        return this;
    }

    // @Override
    // public void importGraph(Graph<V, E> g, File file)
    // {
    //     if (file.getName().toLowerCase().endsWith(".zip"))
    //     try(FileInputStream fis = new FileInputStream(file)) {
    //         ZipInputStream zis = new ZipInputStream(fis);
    //         ZipEntry entry = zis.getNextEntry();
    //
    //         importGraph(g, new InputStreamReader(zis));
    //     } catch (IOException e) {
    //         throw new ImportException(e);
    //     }
    //     else if (file.getName().toLowerCase().endsWith(".gz"))
    //     try (FileInputStream fis = new FileInputStream(file)) {
    //         GZIPInputStream gzis = new GZIPInputStream(fis);
    //
    //         importGraph(g, new InputStreamReader(gzis));
    //     } catch (IOException e) {
    //         throw new ImportException(e);
    //     }
    //     else
    //     try (InputStreamReader reader =
    //                  new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
    //     {
    //         importGraph(g, reader);
    //     } catch (IOException e) {
    //         throw new ImportException(e);
    //     }
    // }

    @Override
    public void importGraph(Graph<V, E> g, Reader in) {
        this.g = g;

        BufferedReader rdr = new BufferedReader(in);
        String line;
        int iLine = 0;
        boolean weighted = g.getType().isWeighted();

        while (true) {
            try {
                ++iLine;
                line = rdr.readLine();
                if (line == null)
                    break;

                if (iLine <= skipLines)
                    continue;

                // line = line.trim();
                if (line.startsWith(comment))
                    continue;

                String[] parts = line.split(separator);
                assert parts.length >= 2;

                V sourceVertex = addVertex(parts[0]);
                V targetVertex = addVertex(parts[1]);
                E e = null;

                // NO LOOPS
                if (!sourceVertex.equals(targetVertex)) {
                    e = g.addEdge(sourceVertex, targetVertex);
                }

                // is weighted?
                if (weighted && e != null) {
                    double weight = Double.parseDouble(parts[2]);
                    g.setEdgeWeight(e, weight);
                }

                ++ecount;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private V addVertex(String t) {
        V v = stoVertex.apply(t);
        g.addVertex(v);
        ++vcount;
        return v;
    }
}
