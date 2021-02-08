package jext.compress;

import jext.compress.nocomp.NoCompressInputStream;
import jext.logging.Logger;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Archives {

    private static Logger logger = Logger.getLogger(Archives.class);

    private static Properties archivers = new Properties();

    static {
        try (InputStream inp = Archives.class.getResourceAsStream("/jext/compress/archives.properties")) {
            archivers.load(inp);
        }
        catch (Exception e) { }
    }

    public static InputStream open(File file) throws IOException {
        return open(file, true);
    }

    public static ArchiveInputStream open(File compressedFile, boolean useDefault)
        throws IOException {
        try {
            InputStream istream = new FileInputStream(compressedFile);
            Class<? extends ArchiveInputStream> aclass = createArchiver(compressedFile);
            return aclass.getConstructor(InputStream.class).newInstance(istream);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException
            | InstantiationException | ClassNotFoundException e) {
            throw new IOException("Unable to open compressed file", e);
        }
    }

    public static BufferedReader openText(File compressedFile, String charset) throws IOException {
        if (charset == null)
            charset = "UTF-8";
        ArchiveInputStream istream = open(compressedFile, true);
        istream.getNextEntry();
        InputStreamReader rdr = new InputStreamReader(istream, charset);
        return new BufferedReader(rdr);
    }

    private static Class<? extends ArchiveInputStream> createArchiver(File file) throws ClassNotFoundException {
        String aclass = findArchiverClass(file);
        if (aclass == null)
            return NoCompressInputStream.class;

        return (Class) Class.forName(aclass);
    }

    private static String findArchiverClass(File file) {
        String[] SPECIAL_HANDLING = new String[]{
            ".tar.gz",
            ".tar.xz"
        };

        String name = file.getName();

        // special handling for ".tar.gz"
        for (String aext : SPECIAL_HANDLING)
            if (name.endsWith(aext))
                return archivers.getProperty(aext);

        for(String aext : archivers.stringPropertyNames())
            if (name.endsWith(aext))
                return archivers.getProperty(aext);

        return null;
    }

}
