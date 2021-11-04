package jext.util;

import jext.io.filters.FalseFileFilter;
import jext.logging.Logger;

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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    // ----------------------------------------------------------------------
    // Digest
    // ----------------------------------------------------------------------

    private static MessageDigest algorithm() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5");
    }

    public static long digestAsLong(File file) {
        if (!file.exists() || !file.isFile())
            return 0;

        try {
            MessageDigest md = algorithm();
            update(md, file);
            return toLong(md);
        }
        catch (Exception e) {
            logger.error(e, e);
            return 0;
        }
    }

    private static final String NO_DIGEST = "0";

    public static String digest(File file) {
        return LongHash.toString(digestAsLong(file));
        // if (!file.exists() || !file.isFile())
        //     return NO_DIGEST;
        //
        // try {
        //     MessageDigest md = algorithm();
        //     update(md, file);
        //     return toDigest(md);
        // }
        // catch (Exception e) {
        //     logger.error(e, e);
        //     return NO_DIGEST;
        // }
    }

    // public static String digest(InputStream in) {
    //     try {
    //         MessageDigest md = algorithm();
    //         update(md, in);
    //         return toDigest(md);
    //     }
    //     catch (Exception e) {
    //         logger.error(e, e);
    //         return "0";
    //     }
    // }

    private static void update(MessageDigest md, File file) throws IOException {
        try(InputStream in = new FileInputStream(file)) {
            update(md, in);
        }
    }

    private static void update(MessageDigest md, InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0)
            md.update(buffer, 0, length);
    }

    private static long toLong(MessageDigest md) {
        byte[] digest = md.digest();
        return ((long) digest[0]) +
            (((long)digest[1]) <<  8) +
            (((long)digest[2]) << 16) +
            (((long)digest[3]) << 24) +
            (((long)digest[4]) << 32) +
            (((long)digest[5]) << 40) +
            (((long)digest[6]) << 48) +
            (((long)digest[7]) << 56);
    }

    // ----------------------------------------------------------------------
    // File properties
    // ----------------------------------------------------------------------

    /**
     * File name without extension
     */
    public static String getNameWithoutExt(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos != -1)
            name = name.substring(0, pos);
        return name;
    }

    /**
     * File extension
     */
    public static String getExtension(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos != -1)
            return name.substring(pos);
        return "";
    }

    /**
     * Add the file extension
     */
    public static File addExtension(File file, String ext) {
        if (!ext.startsWith("."))
            ext = "." + ext;
        return new File(file.getParentFile(), file.getName() + ext);
    }

    /**
     * Add the file extension ".1", ".2", ...
     */
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
     * Check if a file or a directory is empty
     */
    public static boolean isEmpty(File file) {
        if (!file.exists())
            return true;
        if (file.isFile())
            return file.length() == 0;
        if(file.isDirectory()) {
            String[] names = file.list();
            return names == null || names.length == 0;
        }
        else
            return false;
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

    /**
     * Retrieve the absolute path in 'normalized' form (\ -> /)
     */
    public static String getAbsolutePath(File file) {
        if (file == null) return null;
        return normalize(file.getAbsolutePath());
    }

    private static String normalize(String p) {
        p = p.replace('\\', '/');
        // while (p.contains("//"))
        //     p = p.replace("//", "/");
        return p;
    }

    public static List<String> getAbsolutePaths(Collection<File> files) {
        return files.stream()
            .map(FileUtils::getAbsolutePath)
            .collect(Collectors.toList());
    }

    public static File toFile(String path) {
        if (path == null)
            return null;
        else
            return new File(path);
    }

    public static List<File> toFiles(Collection<String> paths) {
        if (paths == null)
            return Collections.emptyList();
        else
            return paths.stream()
                .map(File::new)
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Compose 'baseDir' with 'path' to create a File object BUT it check if
     * 'path' is not an absolute path. In this case 'baseDir' is ignored
     *
     * @param baseDir base directory
     * @param path relative or absolute path.
     * @return a File object
     */
    public static File toFile(String baseDir, String path) {
        return toFile(new File(baseDir), path);
    }

    public static File toFile(File baseDir, String path) {
        if (isAbsolute(path))
            return new File(path);
        else
            return new File(baseDir, path);
    }

    public static List<File> toFile(File baseDir, List<String> paths) {
        return paths.stream()
            .map(path -> toFile(baseDir, path))
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    /**
     * Extract the 'relative path' as difference between 'parentFile' and
     * 'currentFile' in such way that:
     *
     *      currentFile = new File(parentFile, relativePath)
     *
     * @param parentFile  parent file
     * @param currentFile current file
     * @return the relative path
     */
    public static String relativePath(File parentFile, File currentFile) {
        String parentPath = getAbsolutePath(parentFile);
        String currentPath = getAbsolutePath(currentFile);
        if (!currentPath.startsWith(parentPath))
            return currentPath;
        String relativePath = currentPath.substring(parentPath.length());
        while (relativePath.startsWith("/"))
            relativePath = relativePath.substring(1);
        return relativePath;
    }

    /**
     * As 'relativePath' but strip the file extension
     *
     * @param parentFile  parent file
     * @param currentFile current file
     * @return teh relative path without extension
     */
    public static String relativePathNoExt(File parentFile, File currentFile) {
        String rpath = relativePath(parentFile, currentFile);
        // if the file names starts with a '.', we don't strip the 'extension'
        if (currentFile.getName().startsWith("."))
            return rpath;
        // we check that the last '.' is located AFTER the last '/'
        int pos = rpath.lastIndexOf('.');
        int sep = rpath.lastIndexOf('/');
        if (pos == -1 || pos < sep)
            return rpath;
        else
            return rpath.substring(0, pos);
    }

    public static File addParentPath(File directory, String relativePath) {
        int sep = relativePath.lastIndexOf("/");
        if (sep == -1)
            return directory;

        relativePath = relativePath.substring(0, sep);
        return new File(directory, relativePath);
    }

    // ----------------------------------------------------------------------
    // addAll/deleteAll
    // ----------------------------------------------------------------------

    public static void delete(File file) {
        delete(file, FalseFileFilter.INSTANCE);
    }

    public static void delete(File file, FileFilter exclude) {
        deleteAll(file, exclude);
    }

    /**
     * Delete recursively the directory specified
     * @param file a file or a directory
     */
    public static void deleteAll(File file) {
        deleteAll(file, FalseFileFilter.INSTANCE);
    }

    public static void deleteAll(File file, FileFilter exclude) {
        if (!file.exists())
            return;
        if (exclude.accept(file))
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

        if (file.isDirectory() && !isEmpty(file))
            return;

        if (!file.delete())
            logger.warnf("Unable to delete %s", getAbsolutePath(file));
    }

    // ----------------------------------------------------------------------
    // asList
    // ----------------------------------------------------------------------

    public static List<File> listFiles(File directory) {
        return asList(directory.listFiles());
    }

    public static List<String> asList(String[] names) {
        if (names == null || names.length == 0)
            return Collections.emptyList();
        else
            return Arrays.asList(names);
    }

    public static List<File> asList(File[] files) {
        if (files == null || files.length == 0)
            return Collections.emptyList();
        else
            return Arrays.asList(files);
    }

    public static Set<String> asSet(String[] names) {
        if (names == null || names.length == 0)
            return Collections.emptySet();

        return new HashSet<>(asList(names));
    }

    // ----------------------------------------------------------------------
    // NOT Recursive listFiles
    // ----------------------------------------------------------------------

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

    // ----------------------------------------------------------------------
    // Recursive listFiles
    // ----------------------------------------------------------------------

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
    //
    // ----------------------------------------------------------------------

    public static boolean isParent(File parent, File path) {
        return getAbsolutePath(parent).startsWith(getAbsolutePath(parent));
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
    // Copy files & directories
    // ----------------------------------------------------------------------

    public static void copy(File sourceFile, File destinationFile) {
        if (sourceFile.isFile())
            copyFile(sourceFile, destinationFile);
        else
            copyDir(sourceFile, destinationFile, file -> false);
    }

    public static void copy(File sourceFile, File destinationFile, FileFilter exclude) {
        if (sourceFile.isFile())
            copyFile(sourceFile, destinationFile);
        else
            copyDir(sourceFile, destinationFile, exclude);
    }

    private static void copyDir(File sourceDir, File destinationDir, FileFilter exclude) {
        if (!sourceDir.exists())
            return;
        if (!destinationDir.exists()) {
            mkdirs(destinationDir);
            destinationDir.setLastModified(sourceDir.lastModified());
        }

        // copy all files
        File[] files = sourceDir.listFiles(File::isFile);
        for (File sfile : files) {
            if (exclude.accept(sfile))
                continue;

            String name = sfile.getName();
            File dfile = new File(destinationDir, name);
            copyFile(sfile, dfile);
        }

        // copy all sub dirs
        File[] dirs = sourceDir.listFiles(File::isDirectory);
        for (File sdir : dirs) {
            if (exclude.accept(sdir))
                continue;

            String name = sdir.getName();
            File ddir = new File(destinationDir, name);
            copyDir(sdir, ddir, exclude);
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) {
        // We SUPPOSE that IF both files have the SAME size and the SAME timestamp
        // they are EQUAL and it is not necessary to copy.
        if (sourceFile.exists() && destinationFile.exists() &&
            sourceFile.length() == destinationFile.length() &&
            sourceFile.lastModified() == destinationFile.lastModified())
            return;

        File parentFile = destinationFile.getParentFile();
        if (!parentFile.exists())
            mkdirs(parentFile);

        try(InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destinationFile)) {
            copy(in, out);
        }
        catch(IOException e) {
            logger.error(e, e);
        }

        // align the timestamp
        destinationFile.setLastModified(sourceFile.lastModified());
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

    public static File mkdirs(File directory, String ... subdir) {
        File sdir = directory;
        for (String name : subdir)
            sdir = new File(sdir, name);

        if (!sdir.exists())
            sdir.mkdirs();

        return sdir;
    }

    // compare two directories.
    // If a file/directori is not in sourceDir, it is deleted in destinationDir
    public static void deleteIfNotInSource(File sourceDir, File destinationDir, FileFilter exclude) {
        if (exclude.accept(destinationDir))
            return;
        if (!destinationDir.exists())
            return;
        if (!sourceDir.exists()) {
            deleteAll(destinationDir);
            return;
        }

        // check the files
        {
            List<File> files = FileUtils.asList(destinationDir.listFiles(File::isFile));
            files.forEach(dstFile -> {
                String name = dstFile.getName();
                File srcFile = new File(sourceDir, name);
                if (exclude.accept(srcFile))
                    return;
                if (!srcFile.exists())
                    FileUtils.delete(dstFile);
            });
        }
        // check the subdirectories
        {
            List<File> dirs = FileUtils.asList(destinationDir.listFiles(File::isDirectory));
            dirs.forEach(dstDir -> {
                String name = dstDir.getName();
                File srcDir = new File(sourceDir, name);
                if (exclude.accept(dstDir))
                    return;
                if (!srcDir.exists())
                    deleteAll(dstDir);
                else
                    deleteIfNotInSource(srcDir, dstDir, exclude);
            });
        }
    }

    // ----------------------------------------------------------------------
    // Align files & directories
    // ----------------------------------------------------------------------

    /**
     * Align the content of the source directory with the content of the
     * destinaton directory
     * @param sourceDir source directory
     * @param destinationDir destination directory
     */
    public static void align(File sourceDir, File destinationDir) {
        align(sourceDir, destinationDir, FalseFileFilter.INSTANCE);
    }

    public static void align(File sourceDir, File destinationDir, FileFilter exclude) {
        // 1) copy the sourceDir's content into destinationDir
        copy(sourceDir, destinationDir, exclude);
        // 2) delete the files present in destinationDir BUT NOT in sourceDir
        delete(sourceDir, destinationDir, exclude);
    }

    public static void delete(File sourceFile, File destinationFile) {
        delete(sourceFile, destinationFile, FalseFileFilter.INSTANCE);
    }

    public static void delete(File sourceFile, File destinationFile, FileFilter exclude) {
        if (exclude.accept(destinationFile))
            return;
        if (exclude.accept(sourceFile))
            return;

        // sourceFile NOT exist  ->  destinationFile EXISTS
        if (!sourceFile.exists() && destinationFile.exists()) {
            delete(destinationFile);
            return;
        }
        // sourceFile EXISTS BUT it is excluded -> destinationFile EXISTS
        // if (sourceFile.exists() && exclude.accept(sourceFile) && destinationFile.exists()) {
        //     delete(destinationFile);
        //     return;
        // }

        if (!destinationFile.isDirectory()) {
            return;
        }

        // compare directory contents
        List<File> files = asList(destinationFile.listFiles(File::isFile));
        for (File file : files)
            delete(new File(sourceFile, file.getName()), file, exclude);

        // compare sub directories
        List<File> sdirs = asList(destinationFile.listFiles(File::isDirectory));
        for (File sdir : sdirs)
            delete(new File(sourceFile, sdir.getName()), sdir, exclude);
    }
}
