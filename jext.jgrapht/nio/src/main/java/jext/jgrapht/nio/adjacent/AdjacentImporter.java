package jext.jgrapht.nio.adjacent;

import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Import a graph defined by a list of adjacent nodes:
 *
 *              1 2\n
 *              2 4\n
 *
 * if weighted
 *
 *              1 2 w12\n
 *              2 4 w24\n
 *
 * Sepataror can be space, comma, etc.
 * @param <V>
 * @param <E>
 */
public class AdjacentImporter<V, E> implements GraphImporter<V, E> {

    private static Logger logger = Logger.getLogger(AdjacentImporter.class);
    private Graph<V, E> g;
    private long vcount = 0;
    private long ecount = 0;
    private String separators = "\\s+";
    private String comment = "#";
    private int skipLines = 0;
    private Function<String, V> toVertex;

    public AdjacentImporter() { }

    public AdjacentImporter<V, E> withSeparator(String sep) {
        this.separators = sep;
        return this;
    }

    public AdjacentImporter<V, E> withComment(String comments) {
        this.comment = comments;
        return this;
    }

    public AdjacentImporter<V, E> withSkipLines(int nLines) {
        this.skipLines = nLines;
        return this;
    }

    /**
     * Function used to convert a string in a vertex of type V
     * @param toVertex function String->V
     * @return self
     */
    public AdjacentImporter<V, E> withToVertex(Function<String, V> toVertex) {
        this.toVertex = toVertex;
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

                String[] parts = line.split(separators);
                assert parts.length >= 2;

                V sourceVertex = addVertex(parts[0]);
                V targetVertex = addVertex(parts[1]);
                E e = null;

                // NO LOOPS
                if (!sourceVertex.equals(targetVertex))
                    e = g.addEdge(sourceVertex, targetVertex);

                // is weighted?
                if (weighted && e != null) {
                    double weight = Double.parseDouble(parts[2]);
                    g.setEdgeWeight(e, weight);
                }

                ++ecount;
                if ((vcount + ecount)%1000000 == 0)
                    logger.debugft("Imported %d vertices, %d edges", vcount, ecount);

            } catch (IOException e) {
                logger.error(e, e);
            }
        }
    }

    private V addVertex(String t) {
        V v = toVertex.apply(t);
        g.addVertex(v);
        ++vcount;
        return v;
    }
}
