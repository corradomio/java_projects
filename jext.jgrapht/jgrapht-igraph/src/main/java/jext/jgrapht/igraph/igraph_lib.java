package jext.jgrapht.igraph;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface igraph extends Library {
    igraph INSTANCE = Native.load("igraph-0.9.4", igraph.class);

    enum directed_t {
        IGRAPH_UNDIRECTED,
        IGRAPH_DIRECTED
    }

    enum loops_t {
        IGRAPH_NO_LOOPS,
        IGRAPH_LOOPS,
        IGRAPH_LOOPS_TWICE,
        IGRAPH_LOOPS_ONCE
    }

    enum multiple_t {
        IGRAPH_NO_MULTIPLE,
        IGRAPH_MULTIPLE
    }

    enum order_t {
        IGRAPH_ASCENDING,
        IGRAPH_DESCENDING
    }

    enum optimal_t {
        IGRAPH_MINIMUM,
        IGRAPH_MAXIMUM
    }

    enum neimode_t {
        IGRAPH_OUT,
        IGRAPH_IN,
        IGRAPH_ALL,
        IGRAPH_TOTAL
    }

    enum connectedness_t {
        IGRAPH_WEAK,
        IGRAPH_STRONG
    }

    enum reciprocity_t {
        IGRAPH_RECIPROCITY_DEFAULT,
        IGRAPH_RECIPROCITY_RATIO
    }

    enum adjacency_t {
        IGRAPH_ADJ_DIRECTED,
        IGRAPH_ADJ_UNDIRECTED, IGRAPH_ADJ_MAX,
        IGRAPH_ADJ_UPPER, IGRAPH_ADJ_LOWER, IGRAPH_ADJ_MIN,
        IGRAPH_ADJ_PLUS
    }

    enum star_mode_t {
        IGRAPH_STAR_OUT, IGRAPH_STAR_IN,
        IGRAPH_STAR_UNDIRECTED,
        IGRAPH_STAR_MUTUAL
    }

    enum tree_mode_t {
        IGRAPH_TREE_OUT, IGRAPH_TREE_IN,
        IGRAPH_TREE_UNDIRECTED
    }

    enum erdos_renyi_t {
        IGRAPH_ERDOS_RENYI_GNP,
        IGRAPH_ERDOS_RENYI_GNM
    }

    enum get_adjacency_t {
        IGRAPH_GET_ADJACENCY_UPPER,
        IGRAPH_GET_ADJACENCY_LOWER,
        IGRAPH_GET_ADJACENCY_BOTH
    }

    enum degseq_t {
        IGRAPH_DEGSEQ_SIMPLE,
        IGRAPH_DEGSEQ_VL,
        IGRAPH_DEGSEQ_SIMPLE_NO_MULTIPLE,
        IGRAPH_DEGSEQ_SIMPLE_NO_MULTIPLE_UNIFORM
    }

    enum realize_degseq_t {
        IGRAPH_REALIZE_DEGSEQ_SMALLEST,
        IGRAPH_REALIZE_DEGSEQ_LARGEST,
        IGRAPH_REALIZE_DEGSEQ_INDEX
    }

    enum random_tree_t {
        IGRAPH_RANDOM_TREE_PRUFER,
        IGRAPH_RANDOM_TREE_LERW
    }

    enum fileformat_type_t {
        IGRAPH_FILEFORMAT_EDGELIST,
        IGRAPH_FILEFORMAT_NCOL,
        IGRAPH_FILEFORMAT_PAJEK,
        IGRAPH_FILEFORMAT_LGL,
        IGRAPH_FILEFORMAT_GRAPHML
    }

    enum rewiring_t {
        IGRAPH_REWIRING_SIMPLE,
        IGRAPH_REWIRING_SIMPLE_LOOPS
    }

    enum edgeorder_type_t {
        IGRAPH_EDGEORDER_ID,
        IGRAPH_EDGEORDER_FROM,
        IGRAPH_EDGEORDER_TO
    }

    enum to_directed_t {
        IGRAPH_TO_DIRECTED_ARBITRARY,
        IGRAPH_TO_DIRECTED_MUTUAL,
        IGRAPH_TO_DIRECTED_RANDOM,
        IGRAPH_TO_DIRECTED_ACYCLIC
    }

    enum to_undirected_t {
        IGRAPH_TO_UNDIRECTED_EACH,
        IGRAPH_TO_UNDIRECTED_COLLAPSE,
        IGRAPH_TO_UNDIRECTED_MUTUAL
    }

    enum vconn_nei_t {
        IGRAPH_VCONN_NEI_ERROR,
        IGRAPH_VCONN_NEI_NUMBER_OF_NODES,
        IGRAPH_VCONN_NEI_IGNORE,
        IGRAPH_VCONN_NEI_NEGATIVE
    }

    enum spincomm_update_t {
        IGRAPH_SPINCOMM_UPDATE_SIMPLE,
        IGRAPH_SPINCOMM_UPDATE_CONFIG
    }

    enum lazy_adlist_simplify_t {
        IGRAPH_DONT_SIMPLIFY,
        IGRAPH_SIMPLIFY
    }

    enum transitivity_mode_t {
        IGRAPH_TRANSITIVITY_NAN,
        IGRAPH_TRANSITIVITY_ZERO
    }

    enum spinglass_implementation_t {
        IGRAPH_SPINCOMM_IMP_ORIG,
        IGRAPH_SPINCOMM_IMP_NEG
    }

    enum community_comparison_t {
        IGRAPH_COMMCMP_VI,
        IGRAPH_COMMCMP_NMI,
        IGRAPH_COMMCMP_SPLIT_JOIN,
        IGRAPH_COMMCMP_RAND,
        IGRAPH_COMMCMP_ADJUSTED_RAND
    }

    enum add_weights_t {
        IGRAPH_ADD_WEIGHTS_NO,
        IGRAPH_ADD_WEIGHTS_YES,
        IGRAPH_ADD_WEIGHTS_IF_PRESENT
    }

    enum barabasi_algorithm_t {
        IGRAPH_BARABASI_BAG,
        IGRAPH_BARABASI_PSUMTREE,
        IGRAPH_BARABASI_PSUMTREE_MULTIPLE
    }

    enum fas_algorithm_t {
        IGRAPH_FAS_EXACT_IP,
        IGRAPH_FAS_APPROX_EADES
    }

    enum subgraph_implementation_t {
        IGRAPH_SUBGRAPH_AUTO,
        IGRAPH_SUBGRAPH_COPY_AND_DELETE,
        IGRAPH_SUBGRAPH_CREATE_FROM_SCRATCH
    }

    enum imitate_algorithm_t {
        IGRAPH_IMITATE_AUGMENTED,
        IGRAPH_IMITATE_BLIND,
        IGRAPH_IMITATE_CONTRACTED
    }

    enum layout_grid_t {
        IGRAPH_LAYOUT_GRID,
        IGRAPH_LAYOUT_NOGRID,
        IGRAPH_LAYOUT_AUTOGRID
    }

    enum random_walk_stuck_t {
        IGRAPH_RANDOM_WALK_STUCK_ERROR,
        IGRAPH_RANDOM_WALK_STUCK_RETURN
    }


    int igraph_version(String[] version_string, int[] major, int[] minor, int[] subminor);

    int igraph_empty(igraph_t[] g, int n, int directed);

    void igraph_destroy(igraph_t[] g);
}
