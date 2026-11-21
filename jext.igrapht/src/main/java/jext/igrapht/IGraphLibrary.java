package jext.igrapht;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.LongByReference;

/** Direct JNA mapping of the igraph 1.0.1 public C ABI (64-bit integers). */
public interface IGraphLibrary extends Library {
    int SUCCESS = 0;
    int OUT = 1;
    int IN = 2;
    int ALL = 3;
    int LOOPS_TWICE = 1;
    int LOOPS_ONCE = 2;
    int WEAK = 1;
    int STRONG = 2;

    @Structure.FieldOrder({"storBegin", "storEnd", "end"})
    class RealVector extends Structure {
        public Pointer storBegin;
        public Pointer storEnd;
        public Pointer end;
    }

    @Structure.FieldOrder({"storBegin", "storEnd", "end"})
    class IntVector extends Structure {
        public Pointer storBegin;
        public Pointer storEnd;
        public Pointer end;
    }

    @Structure.FieldOrder({"n", "directed", "from", "to", "oi", "ii", "os", "is", "attr", "cache"})
    class Graph extends Structure {
        public long n;
        public byte directed;
        public IntVector from;
        public IntVector to;
        public IntVector oi;
        public IntVector ii;
        public IntVector os;
        public IntVector is;
        public Pointer attr;
        public Pointer cache;

        public Graph() {
            from = new IntVector();
            to = new IntVector();
            oi = new IntVector();
            ii = new IntVector();
            os = new IntVector();
            is = new IntVector();
        }
    }

    @Structure.FieldOrder({"vid", "vecptr", "adj", "range"})
    class VsData extends Union {
        public long vid;
        public Pointer vecptr;
        public byte[] adj = new byte[24];
        public long[] range = new long[2];
    }

    @Structure.FieldOrder({"type", "data"})
    class Vs extends Structure {
        public int type;
        public VsData data;

        public Vs() {
            data = new VsData();
            data.setType("vid");
        }

        public static class ByValue extends Vs implements Structure.ByValue {
        }
    }

    @Structure.FieldOrder({"vid", "eid", "vecptr", "incident", "range", "path", "between"})
    class EsData extends Union {
        public long vid;
        public long eid;
        public Pointer vecptr;
        public long[] incident = new long[2];
        public long[] range = new long[2];
        public byte[] path = new byte[16];
        public byte[] between = new byte[24];
    }

    @Structure.FieldOrder({"type", "data"})
    class Es extends Structure {
        public int type;
        public EsData data;

        public Es() {
            data = new EsData();
            data.setType("eid");
        }

        public static class ByValue extends Es implements Structure.ByValue {
        }
    }

    int igraph_setup();
    int igraph_empty(Graph graph, long vertices, byte directed);
    void igraph_destroy(Graph graph);
    int igraph_add_vertices(Graph graph, long count, Pointer attributes);
    int igraph_add_edge(Graph graph, long source, long target);
    int igraph_delete_vertices(Graph graph, Vs.ByValue vertices);
    int igraph_delete_edges(Graph graph, Es.ByValue edges);
    Vs.ByValue igraph_vss_1(long vertex);
    Es.ByValue igraph_ess_1(long edge);

    int igraph_degree_1(Graph graph, LongByReference degree, long vertex, int mode, int loops);
    int igraph_is_connected(Graph graph, ByteByReference connected, int mode);
    int igraph_get_shortest_path(Graph graph, RealVector weights, IntVector vertices,
                                 IntVector edges, long source, long target, int mode);
    long igraph_vcount(Graph graph);
    long igraph_ecount(Graph graph);
    byte igraph_is_directed(Graph graph);

    int igraph_vector_init(RealVector vector, long size);
    void igraph_vector_destroy(RealVector vector);
    int igraph_vector_resize(RealVector vector, long size);
    double igraph_vector_get(RealVector vector, long position);
    void igraph_vector_set(RealVector vector, long position, double value);
    long igraph_vector_size(RealVector vector);

    int igraph_vector_int_init(IntVector vector, long size);
    void igraph_vector_int_destroy(IntVector vector);
    long igraph_vector_int_get(IntVector vector, long position);
    long igraph_vector_int_size(IntVector vector);

    String igraph_strerror(int error);
    Pointer igraph_set_error_handler(Pointer handler);
}
