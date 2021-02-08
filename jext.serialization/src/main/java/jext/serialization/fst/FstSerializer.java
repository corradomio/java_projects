package jext.serialization.fst;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FstSerializer {
    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public static <T> void serialize(File serialized, T object) throws IOException {

        try (OutputStream stream = new FileOutputStream(serialized)) {
            FSTObjectOutput out = conf.getObjectOutput(stream);
            out.writeObject( object, object.getClass() );
        }
    }
}
