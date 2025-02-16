package jext.util;

import jext.io.filters.FalseFileFilter;
import jext.util.logging.Logger;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static jext.util.ArrayUtils.asList;
import static jext.util.FileUtils.getAbsolutePath;
import static jext.util.FileUtils.isEmpty;

public class FileCopy {

    private static final Logger logger = Logger.getLogger(FileCopy.class);

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

    // ----------------------------------------------------------------------
    // Delete files
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
            if (children != null)
                for(File child : children)
                    deleteAll(child);

            // delete the subdirectories
            File[] subdirs = file.listFiles(File::isDirectory);
            if (subdirs != null)
                for (File subd : subdirs)
                    deleteAll(subd);
        }

        if (file.isDirectory() && !isEmpty(file))
            return;

        if (!file.delete())
            logger.warnf("Unable to delete %s", getAbsolutePath(file));
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
            List<File> files = asList(destinationDir.listFiles(File::isFile));
            files.forEach(dstFile -> {
                String name = dstFile.getName();
                File srcFile = new File(sourceDir, name);
                if (exclude.accept(srcFile))
                    return;
                if (!srcFile.exists())
                    delete(dstFile);
            });
        }
        // check the subdirectories
        {
            List<File> dirs = asList(destinationDir.listFiles(File::isDirectory));
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
