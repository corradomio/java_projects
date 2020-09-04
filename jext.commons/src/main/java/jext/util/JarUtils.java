package jext.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class JarUtils {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String DOT_JMOD = ".jmod";
    public static final String DOT_CLASS = ".class";
    public static final String DOT_JAVA = ".java";
    private static final String CLASSES = "classes/";

    // ----------------------------------------------------------------------
    // List file names
    // ----------------------------------------------------------------------

    public static boolean containsClass(File libFile, String className) {
        if (!libFile.exists())
            return false;

        String entryName = className.replace('.', '/') + ".class";
        if (libFile.getName().endsWith(DOT_JMOD))
            entryName = "classes/" + entryName;

        try (JarFile jarFile = new JarFile(libFile)) {
            ZipEntry entry = jarFile.getEntry(entryName);
            return entry != null;
        }
        catch (IOException e) {}

        return false;
    }

    public static Set<String> listClassNames(File libFile) {
        if (!libFile.exists())
            return Collections.emptySet();

        boolean jmod = libFile.getName().endsWith(DOT_JMOD);
        return listFileNames(libFile, DOT_CLASS)
            .stream()
            .map(path -> (jmod) ? path.substring(CLASSES.length()) : path)
            .map(path -> path.substring(0, path.length()-DOT_CLASS.length())
                .replace('/', '.')
                .replace('$', '.'))
            .collect(Collectors.toSet());
    }

    /**
     * List of the file names present in the jar file
     *
     * @param libFile file to analyze
     * @param dotExt extension (with '.')
     * @return
     */
    public static List<String> listFileNames(File libFile, String dotExt) {
        if (!libFile.exists())
            return Collections.emptyList();

        List<String> names = new ArrayList<>();

        try (JarFile jarFile = new JarFile(libFile)) {
            JarEntry entry;
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                entry = e.nextElement();
                if (entry != null && !entry.isDirectory() && entry.getName().endsWith(dotExt)) {
                    String name = entry.getName();
                    names.add(name);
                }
            }
        }
        catch (IOException e) {}

        return names;
    }

    // ----------------------------------------------------------------------
    // Count file extensions
    // ----------------------------------------------------------------------

    // /**
    //  * Count the file extensions present in the jar|zip file.
    //  * If the compressed file contains
    //  * - a lot of .class, it is a java library
    //  * - a lot of .java, it is a compressed java source code
    //  * - a lot of .html, it is a compressed javadoc
    //  *
    //  * @param jarFile
    //  * @return
    //  */
    // public static Map<String,Integer> countFileExtensions(File jarFile) {
    //     Map<String,Integer> count = new HashMap<>();
    //
    //     logger.infof("Analyzing %s", jarFile.getAbsolutePath());
    //     try(ZipInputStream zip = new ZipInputStream(new FileInputStream(jarFile))) {
    //         for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
    //             logger.debugft("    analyzing %s", entry.getName());
    //             if (!entry.isDirectory()) {
    //                 String ext = extensionOf(entry.getName());
    //                 int n = count.getOrDefault(ext, 0);
    //                 count.put(ext, n+1);
    //             }
    //         }
    //     }
    //     catch (Exception e) {
    //         logger.error("Unable to analyze " + jarFile, e);
    //     }
    //
    //     return count;
    // }
    //
    // private static String extensionOf(String name) {
    //     int pos = name.lastIndexOf(".");
    //     return pos != -1 ? name.substring(pos+1) : "";
    // }

    // ----------------------------------------------------------------------
    // Open stream
    // ----------------------------------------------------------------------

    // /**
    //  * Return as InputStream the specified entry in the zip file
    //  *
    //  * @param jarFile compressed file
    //  * @param entryName entry to select
    //  * @return an InputStream
    //  * @throws IOException
    //  */
    // public static InputStream openInputStream(File jarFile, String entryName) throws IOException {
    //     ZipInputStream zip = new ZipInputStream(new FileInputStream(jarFile));
    //     for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
    //         if (entry.isDirectory())
    //             continue;
    //
    //         if (entry.getName().equals(entryName))
    //             return new InputStream() {
    //                 @Override
    //                 public int read() throws IOException {
    //                     return zip.read();
    //                 }
    //
    //                 @Override
    //                 public int read(byte b[], int off, int len) throws IOException {
    //                     return zip.read(b, off, len);
    //                 }
    //
    //                 @Override
    //                 public void close() {
    //                     try {
    //                         zip.close();
    //                     }
    //                     catch (IOException e) {
    //                         logger.errorf("openInputStream(%s, %s): %s", jarFile, entryName, e);
    //                     }
    //                 }
    //             };
    //     }
    //     zip.close();
    //     throw new FileNotFoundException(String.format("%s!%s", jarFile, entryName));
    // }

}
