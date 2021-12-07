package jext.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import jext.serialization.ClassClosure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class KryoSerializer {

    public static <T> void serialize(File serialized, T object) throws IOException {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        ClassClosure.of(object).forEach(clazz -> {
            kryo.register(clazz);
        });
        kryo.setReferences(true);
        try(Output output = new Output(new FileOutputStream(serialized))) {
            kryo.writeClassAndObject(output, object);
        }
    }

    public static <T> T deserialize(File serialized, Class<T> objectClass) throws IOException {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        ClassClosure.of(objectClass).forEach(clazz -> {
            kryo.register(clazz);
        });
        try(Input input = new Input(new FileInputStream(serialized))) {
            return (T) kryo.readClassAndObject(input);
        }
    }
}
