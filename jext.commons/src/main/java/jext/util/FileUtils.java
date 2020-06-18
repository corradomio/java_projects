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

    // public static String toCanonicalPath(File parentDir, String path) {
    //     try {
    //         return normalize(new File(parentDir, path).getCanonicalPath());
    //     } catch (IOException e) {
    //         return normalize(new File(parentDir, path).getAbsolutePath());
    //     }
    // }

    public static File toAbsoluteFile(String home, String path) {
        if (isAbsolute(path))
            return new File(path);
        else
            return new File(home, path);
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

    // ----------------------------------------------------------------------
    // addAll/deleteAll
    // ----------------------------------------------------------------------

    private static void addAll(List<File> list, File[] array) {
        list.addAll(asList(array));
    }

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


    public static List<File> listFiles1(File directory, String ext) {
        return asList(directory.listFiles(pathname -> pathname.getName().endsWith(ext)));
    }

    /** List files with extension */
    public static List<File> listFiles(File directory, String ext) {
        return listFiles(directory, pathname -> pathname.getName().endsWith(ext));
    }

    public static List<File> listFiles(File directory, FileFilter fileFilter) {
        List<File> collectedFiles = new ArrayList<>();
        listFiles(collectedFiles, directory, fileFilter);
        return collectedFiles;
    }

    public static List<File> listFiles(List<File> dirs, FileFilter fileFilter) {
        List<File> collectedFiles = new ArrayList<>();
        dirs.forEach(dir -> listFiles(collectedFiles, dir, fileFilter));
        return collectedFiles;
    }

    /**
     * Collect the files in 'files'
     */
    public static void listFiles(List<File> collectedFiles, File directory, FileFilter fileFilter)
    {
        File[] selected = directory.listFiles(file -> file.isFile() && fileFilter.accept(file));
        addAll(collectedFiles, selected);

        asList(directory.listFiles(File::isDirectory))
            .forEach(dir -> {
                listFiles(collectedFiles, dir, fileFilter);
            });
    }

    // ----------------------------------------------------------------------
    // File properties
    // ----------------------------------------------------------------------

    public static void walk(File directory) {

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

    // ----------------------------------------------------------------------
    // Read a text file
    // ----------------------------------------------------------------------

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

    public static List<String> toStrings(File file) {
        if (file == null || !file.exists())
            return Collections.emptyList();
        List<String> lines = new ArrayList<>();
        try {
            InputStream stream = new FileInputStream(file);
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
                }
            }

            stream.close();
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
