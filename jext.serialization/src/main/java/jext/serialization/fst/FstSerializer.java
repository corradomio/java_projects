package jext.serialization.fst;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class FstSerializer {
    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public static <T> void serialize(File serialized, T object) throws IOException {

        try (OutputStream stream = new FileOutputStream(serialized);
             FSTObjectOutput out = conf.getObjectOutput(stream)) {
            out.writeObject( object, object.getClass() );
        }
    }
    public static <T> T deserialize(File serialized, Class<T> clazz) throws IOException {

        try(InputStream stream = new FileInputStream(serialized);
            FSTObjectInput in = new FSTObjectInput(stream)) {
            return (T) in.readObject(clazz);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
