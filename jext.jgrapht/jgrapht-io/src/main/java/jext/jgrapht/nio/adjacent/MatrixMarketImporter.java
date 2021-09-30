package jext.jgrapht.nio.adjacent;

import org.jgrapht.Graph;
import org.jgrapht.nio.GraphImporter;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/*
    https://math.nist.gov/MatrixMarket/formats.html

    %%MatrixMarket matrix coordinate real general
    %=================================================================================
    %
    % This ASCII file represents a sparse MxN matrix with L
    % nonzeros in the following Matrix Market format:
    %
    % +----------------------------------------------+
    % |%%MatrixMarket matrix coordinate real general | <--- header line
    % |%                                             | <--+
    % |% comments                                    |    |-- 0 or more comment lines
    % |%                                             | <--+
    % |    M  N  L                                   | <--- rows, columns, entries
    % |    I1  J1  A(I1, J1)                         | <--+
    % |    I2  J2  A(I2, J2)                         |    |
    % |    I3  J3  A(I3, J3)                         |    |-- L lines
    % |        . . .                                 |    |
    % |    IL JL  A(IL, JL)                          | <--+
    % +----------------------------------------------+
    %
    % Indices are 1-based, i.e. A(1,1) is the first element.
    %
    %=================================================================================
      5  5  8
        1     1   1.000e+00
        2     2   1.050e+01
        3     3   1.500e-02
        1     4   6.000e+00
        4     2   2.505e+02
        4     4  -2.800e+02
        4     5   3.332e+01
        5     5   1.200e+01
 */
public class MatrixMarketImporter<V, E> implements GraphImporter<V, E> {

    private Map<String, V> vertices = new HashMap<>();
    private Supplier<V> vertexSupplier;

    @Override
    public void importGraph(Graph<V, E> g, Reader in) {
        String line;
        boolean head = true;

        vertexSupplier = g.getVertexSupplier();

        while((line = readLine(in)) != null) {
            line = line.trim();
            if (line.startsWith("%"))
                continue;
            if (head) {
                head = false;
                continue;
            }

            String[] parts = line.split(" ");
            V sourceVertex = vertexOf(parts[0].trim());
            V targetVertex = vertexOf(parts[1].trim());

            g.addVertex(sourceVertex);
            g.addVertex(targetVertex);
            g.addEdge(sourceVertex, targetVertex);
        }
    }

    private int ch = 0;
    private String readLine(Reader in) {
        try {
            StringBuilder sb = new StringBuilder();

            while (ch == '\n' || ch == '\r')
                ch = in.read();
            if (ch == -1)
                return null;

            sb.append((char)ch);
            while ((ch = in.read()) != -1 && ch != '\n' && ch != '\r')
                sb.append((char)ch);
            return sb.toString();
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private V vertexOf(String id) {
        if (!vertices.containsKey(id))
            vertices.put(id, vertexSupplier.get());
        return vertices.get(id);
    }
}
