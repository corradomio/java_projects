package jext.compress;

import jext.logging.Logger;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Archives {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    /**
     * Check, based on the file extension, if the file is a supported compressed
     * file
     *
     * @param file file to check
     * @return true if it is a supported compressed file
     */
    public static boolean isCompressed(File file) {
        String name = file.getName();
        for(String ext : archivers.keySet())
            if (name.endsWith(ext))
                return true;
        return false;
    }

    /**
     * Read the compressed stream as a text stream
     * @param compressedFile compressed file
     * @param path internal entry's path as 'key1/key2/...'
     * @return a buffered reader
     * @throws IOException
     */
    public static BufferedReader openText(File compressedFile, String path) throws IOException {
        ArchiveInputStream stream = openArchive(compressedFile);
        ArchiveEntry entry;
        if (path == null || path.isEmpty())
            entry = selectFirst(stream);
        else
            entry = select(stream, path);
        if (entry == null)
            throw new IOException(String.format("Entry %s not found in file %s", path, compressedFile.getAbsolutePath()));
        return new BufferedReader(new InputStreamReader(stream));
    }

    public static String readText(File compressedFile, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader rdr = openText(compressedFile, path)) {
            for(String line = rdr.readLine(); line != null; line = rdr.readLine())
                sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Open the archive in the readonly mode. The compressed streams can be selected using
     * ArchiveInputStream.getNextEntry()
     *
     * @param compressedFile compressed file
     * @return a viewer to the internal streams
     * @throws IOException
     */
    public static ArchiveInputStream openArchive(File compressedFile)
        throws IOException {
        String atype = extensionOf(compressedFile).toLowerCase();
        Class aclass = archivers.getOrDefault(atype, null);
        if (aclass == null) {
            atype = guessType(compressedFile);
            aclass = archivers.getOrDefault(atype, null);
        }
        if (aclass == null)
            throw new IOException(String.format("Unsupported archiver for file %s", compressedFile.getAbsolutePath()));

        InputStream istream =  new FileInputStream(compressedFile);
        try {
            return (ArchiveInputStream) aclass.getConstructor(InputStream.class).newInstance(istream);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException  | NoSuchMethodException e) {
            throw new IOException(e);
        }
    }

    /**
     * Select the first compressed stream
     * @param stream stream to the compressed file
     * @return the first compressed stream
     * @throws IOException
     */
    public static ArchiveEntry selectFirst(ArchiveInputStream stream) throws IOException {
        return stream.getNextEntry();
    }


    /**
     * Sele the compressed stream with the specified path or the first stream if path is null
     * or the empty string
     * @param stream stream to the compressed file
     * @param path path of the selected stream or null or the empty string
     * @return the selected compressed stream
     * @throws IOException
     */
    public static ArchiveEntry select(ArchiveInputStream stream, String path) throws IOException {
        if (path == null || path.length() == 0)
            return selectFirst(stream);

        ArchiveEntry entry;
        while ((entry = stream.getNextEntry()) != null) {
            if (entry.getName().equals(path))
                return entry;
        }
        return null;
    }

    /**
     * Uncompress the content of the compressed file into the specified directory
     *
     * @param compressedFile compressed file
     * @param outputDirectory where to write the compressed streams
     * @throws IOException
     */
    public static void uncompress(File compressedFile, File outputDirectory) throws IOException {
        ArchiveEntry entry;
        try (ArchiveInputStream ais = openArchive(compressedFile)) {
            while ((entry = ais.getNextEntry()) != null) {
                if (entry.isDirectory())
                    continue;
                File file = new File(outputDirectory, entry.getName());
                file.getParentFile().mkdirs();
                Files.copy(ais, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static List<ArchiveEntry> listEntries(File compressedFile, String prefix) throws IOException {
        List<ArchiveEntry> entries = new ArrayList<>();
        ArchiveEntry entry;
        try (ArchiveInputStream ais = openArchive(compressedFile)) {
            while ((entry = ais.getNextEntry()) != null) {
                if (entry.isDirectory())
                    continue;
                if (!entry.getName().startsWith(prefix))
                    continue;

                entries.add(entry);
            }
        }
        return entries;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------
    // https://en.wikipedia.org/wiki/List_of_file_signatures
    //

    private static String guessType(File file) throws IOException {
        byte[] header = new byte[8];

        try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(header);
        }

        // https://en.wikipedia.org/wiki/List_of_file_signatures

        if (header[0] == 'P' && header[1] == 'K')
            return "zip";
        if (header[0] == 0x1F && header[1] == 0x8B)
            return "tgz";
        if (header[0] == 'R' && header[1] == 'a' && header[2] == 'r')
            return "rar";
        if (header[0] == '7' && header[1] == 'z')
            return "7z";
        if (header[0] == 0xFD && header[1] == 0x37 && header[2] == 0x7A)
            return "txz";

        throw new IOException("Unsupported file format");
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
