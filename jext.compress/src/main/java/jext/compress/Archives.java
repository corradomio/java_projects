package jext.compress;

import jext.logging.Logger;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Archives {

    private static Logger logger = Logger.getLogger(Archives.class);

    private static Map<String, Class<? extends ArchiveInputStream>> archivers = new HashMap<>();

    static {
        try (InputStream inp = Archives.class.getResourceAsStream("/jext/compress/archivers.properties")) {
            Properties props = new Properties();
            props.load(inp);

            for(String atype: props.stringPropertyNames()) {
                try {
                    Class aclass = Class.forName(props.getProperty(atype));

                    archivers.put(atype, aclass);
                }
                catch (Exception e) {
                    logger.error(e, e);
                }
            }
        }
        catch (Exception e) {
            logger.error(e, e);
        }
    }

    public static ArchiveInputStream openArchive(File compressedFile)
        throws IOException {
        String atype = extensionOf(compressedFile).toLowerCase();
        Class aclass = archivers.getOrDefault(atype, null);
        if (aclass == null)
            throw new IOException(String.format("Unsupported archiver for file %s", compressedFile.getAbsolutePath()));

        InputStream istream =  new FileInputStream(compressedFile);
        try {
            return (ArchiveInputStream) aclass.getConstructor(InputStream.class).newInstance(istream);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException  | NoSuchMethodException e) {
            throw new IOException(e);
        }
    }

    private static String extensionOf(File file) {
        int pos;
        String name = file.getName();

        // special handling for "name.tar.*"
        if (name.contains(".tar."))
            pos = name.lastIndexOf(".tar.");
        else
            pos = name.lastIndexOf('.');
        return name.substring(pos+1);
    }


    public static ArchiveEntry select(ArchiveInputStream stream, String path) throws IOException {
        ArchiveEntry entry;
        while ((entry = stream.getNextEntry()) != null) {
            if (entry.getName().equals(path))
                return entry;
        }
        return null;
    }
}
