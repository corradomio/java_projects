package jext.jgrapht;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;

import java.util.function.Supplier;

public class Edges {

    /**
     *
     * @param edgeClass
     *      supported classes: DefaultEdge, DefaultWeightedEdge
     */
    public static <E> Supplier<E> edgeSupplier(Class<E> edgeClass) {
        if (edgeClass.equals(DefaultEdge.class))
            return (Supplier<E>) SupplierUtil.createDefaultEdgeSupplier();
        if (edgeClass.equals(DefaultWeightedEdge.class))
            return (Supplier<E>) SupplierUtil.createDefaultWeightedEdgeSupplier();
        else
            return SupplierUtil.createSupplier(edgeClass);
    }
}

