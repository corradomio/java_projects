package jext.data.kv.swaydb;

import org.apache.commons.lang3.SerializationUtils;
import swaydb.data.slice.Slice;
import swaydb.java.serializers.Default;
import swaydb.java.serializers.Serializer;

import java.io.Serializable;

public class SwaySerializer<T extends Serializable> {

    private static class ObjectSerializer<T> implements Serializer<T> {
        @Override
        public Slice<Byte> write(T data) {
            byte[] serialized = SerializationUtils.serialize((Serializable) data);
            int n = serialized.length;
            Byte[] asbytes = new Byte[n];
            for (int i=0; i<n; ++i)
                asbytes[i] = serialized[i];
            return new Slice<>(asbytes, 0, n, n, null);
        }

        @Override
        public T read(Slice<Byte> slice) {
            int n = slice.size();
            byte[] serialized = new byte[n];
            for (int i=0; i<n; ++i)
                serialized[i] = slice.get(i);
            return SerializationUtils.deserialize(serialized);
        }
    }

    public static <T> Serializer<T> create(Class<T> clazz) {
        if (clazz == String.class)
            return (Serializer<T>) Default.stringSerializer();
        if (clazz == Short.class || clazz == short.class)
            return (Serializer<T>) Default.shortSerializer();
        if (clazz == Integer.class || clazz == int.class)
            return (Serializer<T>) Default.intSerializer();
        if (clazz == Long.class || clazz == long.class)
            return (Serializer<T>) Default.longSerializer();
        if (clazz == Float.class || clazz == float.class)
            return (Serializer<T>) Default.floatSerializer();
        if (clazz == Double.class || clazz == double.class)
            return (Serializer<T>) Default.doubleSerializer();
        if (clazz == Character.class || clazz == char.class)
            return (Serializer<T>) Default.charSerializer();
        if (clazz == Byte[].class || clazz == byte[].class)
            return (Serializer<T>) Default.javaByteArraySerializer();
        else
            return new ObjectSerializer<T>();
    }
}
