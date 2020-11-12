package jext.jgrapht.nio.adjacent;

import jext.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, V> map = new HashMap<>();
    private Graph<V, E> g;
    private long vcount = 0;
    private long ecount = 0;
    private String separators = "\\s+";
    private String comment = "#";
    private int skipLines = 0;
    private boolean weighted;

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

    public AdjacentImporter<V, E> weighted(boolean weighted) {
        this.weighted = weighted;
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
        // LineNumberReader rdr = new LineNumberReader(in);
        String line;
        int lineNumber = 0;

        while (true) {
            try {
                ++lineNumber;
                line = rdr.readLine();
                if (line == null)
                    break;

                if (lineNumber <= skipLines)
                    continue;

                // line = line.trim();
                if (line.startsWith(comment))
                    continue;

                String[] parts = line.split(separators);
                assert parts.length >= 2;

                V sourceVertex = addVertex(parts[0]);
                V targetVertex = addVertex(parts[1]);
                E e = null;

                if (!sourceVertex.equals(targetVertex))
                    e = g.addEdge(sourceVertex, targetVertex);

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
        if (!map.containsKey(t)){
            V v = g.addVertex();
            map.put(t, v);

            ++vcount;
        }
        return map.get(t);
    }
}
