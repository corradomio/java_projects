package jext.serialization.protostuff;

import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProtostuffSerializer {

    static final DefaultIdStrategy STRATEGY = new DefaultIdStrategy(IdStrategy.DEFAULT_FLAGS
        | IdStrategy.ALLOW_NULL_ARRAY_ELEMENT
        | IdStrategy.MORPH_COLLECTION_INTERFACES
        | IdStrategy.MORPH_MAP_INTERFACES
        | IdStrategy.MORPH_NON_FINAL_POJOS);

    public static <T> void serialize(File serialized, T object) throws IOException {
        Schema<T> schema = RuntimeSchema.getSchema((Class<T>)object.getClass(), STRATEGY);
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(serialized)))
        {
            GraphIOUtil.writeTo(out, object, schema, buffer);
        }
        finally
        {
            buffer.clear();
        }
    }

    public static <T> T deserialize(File serialized, Class<T> clazz) throws IOException {
        Schema<T> schema = RuntimeSchema.getSchema(clazz, STRATEGY);
        try(InputStream input = new FileInputStream(serialized)) {
            T parsed = schema.newMessage();
            GraphIOUtil.mergeFrom(input, parsed, schema);
            return parsed;
        }
    }
}
