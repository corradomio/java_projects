package jext.persistent;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Persistent {

    public static <T> void serialize(File serialized, T object) throws IOException {
        Kryo kryo = new Kryo();
        try(Output output = new Output(new FileOutputStream(serialized))) {
            kryo.writeClassAndObject(output, object);
        }
    }

    public static <T> T deserialize(File serialized, Class<T> clazz) throws IOException {
        Kryo kryo = new Kryo();
        try(Input input = new Input(new FileInputStream(serialized))) {
            return (T) kryo.readClassAndObject(input);
        }
    }
}
