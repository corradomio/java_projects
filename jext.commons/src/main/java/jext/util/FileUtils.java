package jext.util;

import jext.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
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
        if (!file.exists() || !file.isFile())
            return "0";
        try(InputStream in = new FileInputStream(file)) {
            return digest(in);
        }
        catch (Exception e) {
            logger.error(e, e);
            return "0";
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

    // public static int countComponents(File file) {
    //     if (file == null)
    //         return 0;
    //     String path = getAbsolutePath(file);
    //     return path.split("/").length;
    // }

    public static File addExtension(File file, String ext) {
        if (!ext.startsWith("."))
            ext = "." + ext;
        return new File(file.getParentFile(), file.getName() + ext);
    }

    public static File addExtension(File file, int counter) {
        if (counter <= 0)
            return file;
        else
            return new File(file.getParentFile(), file.getName() + String.format(".%d", counter));
    }
    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Check if the path is absolute:
     *
     *      /...
     *      c:/...
     *
     */
    public static boolean isAbsolute(String path) {
        path = normalize(path);
        //     linux/mac               windows: c:/...
        return path.startsWith("/") || path.indexOf(":/") == 1;
    }

    public static String getAbsolutePath(File file) {
        return normalize(file.getAbsolutePath());
    }

    // ----------------------------------------------------------------------

    public static File toFile(String baseDir, String path) {
        return toFile(new File(baseDir), path);
    }

    public static File toFile(File baseDir, String path) {
        if (isAbsolute(path))
            return new File(path);
        else
            return new File(baseDir, path);
    }

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

    public static String relativePathNoExt(File parentDir, File file) {
        String rpath = relativePath(parentDir, file);
        // if the file names starts with a '.', we don't strip the 'extension'
        if (file.getName().startsWith("."))
            return rpath;
        // we check that the last '.' is located AFTER the last '/'
        int pos = rpath.lastIndexOf('.');
        int sep = rpath.lastIndexOf('/');
        if (pos == -1 || pos < sep)
            return rpath;
        else
            return rpath.substring(0, pos);
    }

    // public static String toCanonicalPath(File parentDir, String path) {
    //     try {
    //         return normalize(new File(parentDir, path).getCanonicalPath());
    //     }
    //     catch (IOException e) {
    //         return normalize(new File(parentDir, path).getAbsolutePath());
    //     }
    // }

    // ----------------------------------------------------------------------
    // addAll/deleteAll
    // ----------------------------------------------------------------------

    /**
     * Delete recursively the directory specified
     * @param file a file or a directory
     */
    public static void deleteAll(File file) {
        if (!file.exists())
            return;
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

    public static List<String> asList(String[] files) {
        if (files == null || files.length == 0)
            return Collections.emptyList();
        else
            return Arrays.asList(files);
    }

    public static List<File> asList(File[] files) {
        if (files == null || files.length == 0)
            return Collections.emptyList();
        else
            return Arrays.asList(files);
    }

    public static List<File> listFiles(File directory) {
        return asList(directory.listFiles());
    }

    // NOT recursive
    public static List<File> listFiles(File directory, String ext) {
        return asList(directory.listFiles((dir, name) -> name.endsWith(ext)));
    }

    /**
     * Select the files inside all sub directories specified by depth
     *
     * @param directory root directory
     * @param depth sub directory depth. 0 means the current directory
     * @param filter filter used to select the files inside the directory
     * @param directoryFilter filter used to select which sub directories to scan
     * @return list of files
     */
    // NOT recursive
    public static List<File> listFiles(File directory, int depth, FileFilter filter, FileFilter directoryFilter) {
        if (depth == 0)
            return asList(directory.listFiles(filter));

        List<File> files = new ArrayList<>();
        asList(directory.listFiles(directoryFilter))
            .forEach(subdir -> {
                files.addAll(listFiles(subdir, depth-1, filter, directoryFilter));
            });
        return files;
    }

    // Recursive!
    public static List<File> listFiles(File directory, FileFilter filter) {
        if (directory == null) return Collections.emptyList();
        List<File> collectedFiles = new ArrayList<>();
        listFiles(collectedFiles, directory, filter);
        return collectedFiles;
    }

    // Recursive!
    public static void listFiles(List<File> collectedFiles, File directory, FileFilter filter) {
        if (directory == null) return;
        collectedFiles.addAll(asList(directory.listFiles(file -> file.isFile() && filter.accept(file))));
        asList(directory.listFiles(File::isDirectory))
            .forEach(sundir -> listFiles(collectedFiles, sundir, filter));
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
    //
    // ----------------------------------------------------------------------

    public static boolean isParent(File parent, File path) {
        return normalize(path.getAbsolutePath()).startsWith(normalize(parent.getAbsolutePath()));
    }

    /**
     * Remove all files that are a 'subfile' of other files: in
     *
     *      { 'd:/a/b/c', 'd:/a/b/c/d/e'}
     *
     * it removes 'd:/a/b/c/d/e'
     */
    // public static Set<File> simplify(Collection<File> files) {
    //     List<File> simplified = new ArrayList<>(files);
    //     simplified.sort((o1, o2) -> (o2.getAbsolutePath().length() - o1.getAbsolutePath().length()));
    //
    //     boolean update = true;
    //     while (update) {
    //         update = false;
    //         int n = simplified.size();
    //
    //         loop: for (int i=0; i<n; ++i) {
    //             File o1 = simplified.get(i);
    //             for (int j=i+1; j<n; ++j) {
    //                 File o2 = simplified.get(j);
    //                 if (FileUtils.isParent(o2, o1)) {
    //                     simplified.remove(i);
    //                     update = true;
    //                     break loop;
    //                 }
    //             }
    //         }
    //     }
    //
    //     return new HashSet<>(simplified);
    // }

    // ----------------------------------------------------------------------
    // Read a text file
    // ----------------------------------------------------------------------

    /** Read the content of a file as a string */
    public static String toString(File file)
    {
        try {
            byte[] encoded = Files.readAllBytes(file.toPath());
            return new String(encoded, Charset.defaultCharset());
        }
        catch (IOException e) {
            logger.errorf("Unable to read %s: %s", file, e);
            return "";
        }
    }

    public static String toString(InputStream stream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copy(stream, baos);
        return new String(baos.toByteArray(), Charset.defaultCharset());
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

    public static void asFile(File file, String content) {
        try(Writer w = new FileWriter(file)) {
            w.write(content);
        }
        catch (IOException e) {
            logger.errorf("Unable to write %s: %s", file, e);
        }
    }

    public static void asFile(File file, List<String> content) {
        try(Writer w = new FileWriter(file)) {
            for (String line : content) {
                w.write(line);
                w.write("\n");
            }
        }
        catch (IOException e) {
            logger.errorf("Unable to write %s: %s", file, e);
        }
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public static void copy(File sourceFile, File destinationFile) {

        try(InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destinationFile)) {
            copy(in, out);
        }
        catch(IOException e) {
            logger.error(e, e);
        }
    }

    public static void copy(InputStream in, OutputStream out) {
        try {
            byte[] b = new byte[1024];
            for (int n = in.read(b); n > 0; n = in.read(b))
                out.write(b, 0, n);
        }
        catch(IOException e) {
            logger.error(e, e);
        }
    }

    public static void mkdirs(File directory) {
        directory.mkdirs();
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
