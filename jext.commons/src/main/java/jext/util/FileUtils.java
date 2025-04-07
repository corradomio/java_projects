package jext.util;

import jext.io.filters.FileFilters;
import jext.util.logging.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static jext.util.ArrayUtils.asList;

public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class);

    // ----------------------------------------------------------------------
    // Count extensions
    // ----------------------------------------------------------------------

    public static Map<String, AtomicInteger> countExtensions(File directory) {
        // count
        Map<String, AtomicInteger> extCounts = new HashMap<String, AtomicInteger>() {
            @Override
            public AtomicInteger get(Object key) {
                if (!super.containsKey(key))
                    super.put((String)key, new AtomicInteger());
                return super.get(key);
            }
        };

        try {
            Files.walkFileTree(directory.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String ext = PathUtils.getExtension(file.toString());
                    extCounts.get(ext).incrementAndGet();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            // In 'theory', it never happen
            Logger.getLogger(FileUtils.class).error(e, e);
        }

        return extCounts;
    }

    // ----------------------------------------------------------------------
    // File properties
    // ----------------------------------------------------------------------

    /**
     * File name without extension
     */
    public static String getNameWithoutExt(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf('.');
        if (pos != -1)
            name = name.substring(0, pos);
        return name;
    }

    /**
     * File extension (with '.')
     */
    public static String getExtension(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf('.');
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
            return addExtension(file, String.format(".%d", counter));
    }

    /**
     * Replace the current file extension
     */
    public static File replaceExtension(File file, String ext) {
        if (!ext.startsWith("."))
            ext = "." + ext;
        String name = file.getName();
        int pos = name.lastIndexOf('.');
        return new File(file.getParentFile(), name.substring(0, pos) + ext);
    }

    // ----------------------------------------------------------------------
    // File/directory empty
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
    // Absolute path
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
        return p;
    }

    /**
     * Retrieve the absolute paths of a list of files
     */
    public static List<String> getAbsolutePaths(Collection<File> files) {
        return files.stream()
            .map(FileUtils::getAbsolutePath)
            .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------------
    // Safe toFile
    // ----------------------------------------------------------------------

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
                .filter(Objects::nonNull)
                .map(File::new)
                .collect(Collectors.toList());
    }

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
    // Relative path
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

    // ----------------------------------------------------------------------
    // Safe version of File.listFiles(...)
    // NOT Recursive listFiles
    // ----------------------------------------------------------------------

    public static List<File> listFiles(File directory) {
        return asList(directory.listFiles());
    }

    // NOT recursive
    public static List<File> listFiles(File directory, String ext) {
        return asList(directory.listFiles((dir, name) -> name.endsWith(ext)));
    }

    // ----------------------------------------------------------------------
    // Recursive listFiles
    // ----------------------------------------------------------------------

    /**
     * Select the files inside all sub directories specified by depth
     *
     * @param directory root directory
     * @param depth sub directory depth. 0 means the current directory
     * @param filter filter used to select the files inside the directory
     * @param directoryFilter filter used to select which sub directories to scan
     * @return list of files
     */
    // NOT TOTALLY recursive
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
        return listFiles(directory, filter, FileFilters.TRUE);
    }

    // Recursive!
    public static List<File> listFiles(File directory, FileFilter filter, FileFilter directoryFilter) {
        Assert.notNull(directory, "directory");
        List<File> collectedFiles = new ArrayList<>();
        listFiles(collectedFiles, directory, filter, directoryFilter);
        return collectedFiles;
    }

    // Recursive!
    public static void listFiles(List<File> collectedFiles, File directory, FileFilter fileFilter, FileFilter directoryFilter) {
        if (directory == null) return;
        collectedFiles.addAll(asList(directory.listFiles(file -> file.isFile() && fileFilter.accept(file))));
        asList(directory.listFiles(File::isDirectory))
            .stream().filter(directoryFilter::accept)
            .forEach(sundir -> listFiles(collectedFiles, sundir, fileFilter, directoryFilter));
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static boolean isParent(File parent, File path) {
        return getAbsolutePath(parent).startsWith(getAbsolutePath(path));
    }

    // ----------------------------------------------------------------------
    // findFile
    // ----------------------------------------------------------------------

    public static File findFile(File directory, String ext) {
        File[] files = directory.listFiles((dir, name) -> name.endsWith(ext));
        if (files == null || files.length == 0)
            return null;
        if (files.length > 1)
            // in theory it is necessary to log
            return files[0];
        else
            return files[0];
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------
    public static void delete(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            List<File> files = listFiles(fileOrDirectory);
            for (File file : files)
                delete(file);
        }
        fileOrDirectory.delete();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
