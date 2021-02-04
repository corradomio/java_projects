package jext.compress;

import org.apache.commons.compress.archivers.ArchiveInputStream;

import jext.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
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

    public static InputStream open(File file) throws IOException {
        return open(file, true);
    }

    public static InputStream open(File compressedFile, boolean useDefault)
        throws IOException {
        String atype = extensionOf(compressedFile).toLowerCase();
        try {
            Class<?> aclass = archivers.getOrDefault(atype, null);
            InputStream istream = new FileInputStream(compressedFile);

            if (aclass == null)
                if (useDefault)
                    return istream;
                else
                    throw new IOException(String.format("Unsupported archiver for file %s", compressedFile.getAbsolutePath()));

            return (ArchiveInputStream) aclass.getConstructor(InputStream.class).newInstance(istream);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IOException(e);
        }
    }

    public static BufferedReader openText(File compressedFile, String charset) throws IOException {
        if (charset == null)
            charset = "UTF-8";
        InputStream istream = open(compressedFile, true);
        InputStreamReader rdr = new InputStreamReader(istream, charset);
        return new BufferedReader(rdr);
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

}
