package jext.jgrapht;

import org.jgrapht.util.SupplierUtil;

import java.util.UUID;
import java.util.function.Supplier;

public class Vertices {

    /**
     *
     * @param vertexClass
     *      supported classes: int, Integer, long, Long, String, UUID
     *      and a generic class
     */
    public static <V> Supplier<V> vertexSupplier(Class<V> vertexClass) {
        if (vertexClass.equals(Integer.class) || vertexClass.equals(int.class))
            return (Supplier<V>) SupplierUtil.createIntegerSupplier();
        if (vertexClass.equals(Long.class) || vertexClass.equals(long.class))
            return (Supplier<V>) SupplierUtil.createLongSupplier();
        if (vertexClass.equals(String.class))
            return (Supplier<V>) SupplierUtil.createStringSupplier();
        if (vertexClass.equals(UUID.class))
            return (Supplier<V>) SupplierUtil.createRandomUUIDStringSupplier();
        else
            return SupplierUtil.createSupplier(vertexClass);
    }
}
