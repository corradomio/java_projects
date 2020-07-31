package jext.util;

import jext.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    // ----------------------------------------------------------------------
    // Digest
    // ----------------------------------------------------------------------

    public static String digest(File file) {
        if (!file.exists())
            return "";
        try(InputStream in = new FileInputStream(file)) {
            return digest(in);
        } catch (Exception e) {
            logger.error(e, e);
            return "";
        }
    }

    public static String digest(InputStream in) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0)
                md.update(buffer, 0, length);

            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        }
        catch (Exception e) {
            logger.error(e, e);
            return "0";
        }
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static boolean isParent(File parent, File path) {
        return path.getAbsolutePath().startsWith(parent.getAbsolutePath());
    }

    /**
     * Check if the path is absolute:
     *
     *      /...
     *      c:/...
     *
     */
    public static boolean isAbsolute(String path) {
        path = normalize(path);
        return path.startsWith("/") || path.indexOf(":/") == 1;
    }

    public static File toFile(String baseDir, String path) {
        return toFile(new File(baseDir), path);
    }

    public static File toFile(File baseDir, String path) {
        if (isAbsolute(path))
            return new File(path);
        else
            return new File(baseDir, path);
    }

    public static String relativePath(File parentDir, File file) {
        String parentPath = normalize(parentDir.getAbsolutePath());
        String currentPath = normalize(file.getAbsolutePath());
        if (!currentPath.startsWith(parentPath))
            return currentPath;
        String relativePath = currentPath.substring(parentPath.length());
        while (relativePath.startsWith("/"))
            relativePath = relativePath.substring(1);
        return relativePath;
    }

    public static String getAbsolutePath(File file) {
        return normalize(file.getAbsolutePath());
    }

    public static String toCanonicalPath(File parentDir, String path) {
        try {
            return normalize(new File(parentDir, path).getCanonicalPath());
        } catch (IOException e) {
            return normalize(new File(parentDir, path).getAbsolutePath());
        }
    }

    // ----------------------------------------------------------------------
    // addAll/deleteAll
    // ----------------------------------------------------------------------

    /**
     * Delete recursively the directory specified
     * @param file a file or a directory
     */
    public static void deleteAll(File file) {
        if (file.isDirectory()) {
            // delete the files
            File[] children = file.listFiles(File::isFile);
            if (children != null) for(File child : children)
                deleteAll(child);

            // delete the subdirectories
            File[] subdirs = file.listFiles(File::isDirectory);
            if (subdirs != null) for (File subd : subdirs)
                deleteAll(subd);
        }
        if (!file.delete())
            logger.warnf("Unable to delete %s", file.getAbsolutePath());
    }

    // ----------------------------------------------------------------------
    // Recursive listFiles
    // ----------------------------------------------------------------------

    public static List<File> asList(File[] files) {
        if (files == null || files.length == 0)
            return Collections.emptyList();
        else
            return Arrays.asList(files);
    }

    // NOT recursive
    public static List<File> listFiles(File directory, String ext) {
        if (directory == null) return Collections.emptyList();
        return asList(directory.listFiles((dir, name) -> name.endsWith(ext)));
    }

    // Recursive!
    public static List<File> listFiles(File directory, FileFilter filter) {
        if (directory == null) return Collections.emptyList();
        List<File> collectedFiles = new ArrayList<>();
        listFiles(collectedFiles, directory, filter);
        return collectedFiles;
    }

    // Recursive!
    public static void listFiles(Collection<File> collectedFiles, File directory, FileFilter filter) {
        if (directory == null) return;
        collectedFiles.addAll(asList(directory.listFiles(file -> file.isFile() && filter.accept(file))));
        asList(directory.listFiles(File::isDirectory))
                .forEach(sundir -> listFiles(collectedFiles, sundir, filter));
    }

    // Recursive!
    public static void listFiles(Collection<File> collectedFiles, File directory) {
        listFiles(collectedFiles, directory, pathname -> true);
    }

    // ----------------------------------------------------------------------
    // File properties
    // ----------------------------------------------------------------------

    public static String getNameWithoutExt(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos != -1)
            name = name.substring(0, pos);
        return name;
    }

    public static String getExtension(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos != -1)
            return name.substring(pos);
        return "";
    }

    // ----------------------------------------------------------------------
    // Read a text file
    // ----------------------------------------------------------------------

    /** Read the content of a file as a string */
    public static String toString(File file)
    {
        try {
            byte[] encoded = Files.readAllBytes(file.toPath());
            return new String(encoded, Charset.defaultCharset());
        } catch (IOException e) {
            logger.errorf("Unable to read %s: %s", file, e);
            return "";
        }
    }

    /** Read the content of a file as a list of lines */
    public static List<String> toStrings(File file) {
        return toStrings(file, null);
    }

    public static List<String> toStrings(File file, String terminator) {
        if (file == null || !file.exists())
            return Collections.emptyList();
        List<String> lines = new ArrayList<>();
        try(InputStream is = new FileInputStream(file)) {
            InputStream stream = is;
            if (file.getName().endsWith(".gz"))
                stream = new GZIPInputStream(stream);

            else if (file.getName().endsWith(".zip")) {
                ZipInputStream zis = new ZipInputStream(stream);
                ZipEntry ze = zis.getNextEntry();
                stream = zis;
            }

            try(BufferedReader r = new BufferedReader(new InputStreamReader(stream))) {
                for(String line = r.readLine(); line != null; line = r.readLine()) {
                    lines.add(line);
                    if (terminator != null && line.startsWith(terminator))
                        break;
                }
            }
        }
        catch (IOException e) {
            logger.errorf("Unable to read %s: %s", file, e);
        }

        return lines;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private static String normalize(String p) {
        p = p.replace('\\', '/');
        // while (p.contains("//"))
        //     p = p.replace("//", "/");
        return p;
    }

}
