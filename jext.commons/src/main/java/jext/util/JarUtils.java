package jext.util;

import jext.logging.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class JarUtils {

    // ----------------------------------------------------------------------
    // Archive analyzers
    // ----------------------------------------------------------------------

    private interface FileAnalyzer {
        boolean exists();
        boolean containsClass(String className);
        Set<String> listClassNames();
    }

    private static abstract class BaseAnalyzer implements FileAnalyzer {
        protected File libFile;

        protected BaseAnalyzer(File libFile) {
            this.libFile = libFile;
        }

        @Override
        public boolean exists() {
            return libFile.exists();
        }

        @Override
        public boolean containsClass(String className) {
            String entryName = asEntryName(className);

            // check for a top class
            try (JarFile jarFile = new JarFile(libFile)) {
                ZipEntry entry = jarFile.getEntry(entryName);
                if (entry != null)
                    return true;
            }
            catch (IOException e) {}

            // check for an inner class (containing '$')
            try (JarFile jarFile = new JarFile(libFile)) {
                ZipEntry entry;
                Enumeration<JarEntry> e = jarFile.entries();
                while (e.hasMoreElements()) {
                    entry = e.nextElement();
                    if (entry != null && !entry.isDirectory() && entry.getName().endsWith(DOT_CLASS)) {
                        String thisName = entry.getName().replace('$', '/');
                        if (thisName.equals(entryName))
                            return true;
                    }
                }
            }
            catch (IOException e) {}

            // not found
            return false;
        }

        @Override
        public Set<String> listClassNames() {
            return listFileNames(libFile, DOT_CLASS)
                .stream()
                .map(this::asClassName)
                .collect(Collectors.toSet());
        }

        protected abstract String asEntryName(String className);

        protected abstract String asClassName(String entryName);
    }

    private static class JarAnalyzer extends BaseAnalyzer {

        protected JarAnalyzer(File file) {
            super(file);
        }

        @Override
        protected String asEntryName(String className) {
            return className.replace('.', '/') + DOT_CLASS;
        }

        @Override
        protected String asClassName(String entryName) {
            // strip end ".class"
            return entryName.substring(0, entryName.length() - DOT_CLASS.length())
                .replace('/', '.')
                .replace('$', '.');
        }
    }

    private static class JModAnalyzer extends BaseAnalyzer {

        protected JModAnalyzer(File file) {
            super(file);
        }

        @Override
        protected String asEntryName(String className) {
            return CLASSES + className.replace('.', '/') + DOT_CLASS;
        }

        @Override
        protected String asClassName(String entryName) {
            // strip end ".class"
            return entryName.substring(CLASSES.length(), entryName.length() - DOT_CLASS.length())
                .replace('/', '.')
                .replace('$', '.');
        }
    }

    // ----------------------------------------------------------------------
    // ".aar" archive
    // ----------------------------------------------------------------------
    // it is a "zip" file
    // the classes are saved in "<zip>/classes.jar"

    private static class AarAnalyzer implements FileAnalyzer {

        private File libFile;

        protected AarAnalyzer(File libFile) {
            this.libFile = libFile;
        }

        @Override
        public boolean exists() {
            return libFile.exists();
        }

        @Override
        public boolean containsClass(String className) {

            try(ZipFile zfile = new ZipFile(libFile)) {
                ZipEntry classes = zfile.getEntry("classes.jar");
                try(JarInputStream is = new JarInputStream(zfile.getInputStream(classes))) {
                    ZipEntry ze = is.getNextEntry();
                    while (ze != null) {
                        if (ze.getName().endsWith(DOT_CLASS)) {
                            String thisName = asClassName(ze.getName());
                            if (className.equals(thisName))
                                return true;
                        }
                        ze = is.getNextEntry();
                    }
                }
            }
            catch(IOException e) {
                return false;
            }

            return false;
        }

        private String asClassName(String entryName) {
            // strip end ".class"
            return entryName.substring(0, entryName.length() - DOT_CLASS.length())
                .replace('/', '.')
                .replace('$', '.');
        }

        @Override
        public Set<String> listClassNames() {
            Set<String> classNames = new HashSet<>();

            try(ZipFile zfile = new ZipFile(libFile)) {
                ZipEntry classes = zfile.getEntry("classes.jar");
                try(JarInputStream is = new JarInputStream(zfile.getInputStream(classes))) {
                    ZipEntry ze = is.getNextEntry();
                    while (ze != null) {
                        if (ze.getName().endsWith(DOT_CLASS)) {
                            String thisName = asClassName(ze.getName());
                            classNames.add(thisName);
                        }
                        ze = is.getNextEntry();
                    }
                }
            }
            catch(IOException e) {
                return classNames;
            }

            return classNames;
        }
    }

    // ----------------------------------------------------------------------

    private static FileAnalyzer newAnalyzer(File libFile) {
        String name = libFile.getName();
        if (name.endsWith(DOT_AAR))
            return new AarAnalyzer(libFile);
        if (name.endsWith(DOT_JMOD))
            return new JModAnalyzer(libFile);
        else
            return new JarAnalyzer(libFile);
    }


    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(JarUtils.class);

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String DOT_AAR = ".aar";
    private static final String DOT_JAR = ".jar";
    private static final String DOT_JMOD = ".jmod";
    private static final String DOT_CLASS = ".class";
    private static final String CLASSES = "classes/";

    // ----------------------------------------------------------------------
    // List file names
    // ----------------------------------------------------------------------

    public static boolean containsClass(File libFile, String className) {

        FileAnalyzer fa = newAnalyzer(libFile);

        if (!fa.exists())
            return false;
        else
            return fa.containsClass(className);
    }

    public static Set<String> listClassNames(File libFile) {

        FileAnalyzer fa = newAnalyzer(libFile);

        if (!fa.exists())
            return Collections.emptySet();
        else
            return fa.listClassNames();
    }

    /**
     * List of the file names present in the jar file
     *
     * @param libFile file to analyze
     * @param dotExt extension (with '.')
     * @return
     */
    private static List<String> listFileNames(File libFile, String dotExt) {
        if (!libFile.exists())
            return Collections.emptyList();

        List<String> names = new ArrayList<>();
        logger.debugf("Analyzing %s", libFile.getAbsolutePath());

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
    // Extract jar from aar
    // ----------------------------------------------------------------------

    public static File extractJarFromAar(File aarArchive) {
        File jarArchive = new File(aarArchive.getParentFile(), FileUtils.getNameWithoutExt(aarArchive) + ".jar");
        if (jarArchive.exists() || !aarArchive.exists())
            return jarArchive;

        try(ZipFile zfile = new ZipFile(aarArchive)) {
            ZipEntry classes = zfile.getEntry("classes.jar");
            try(InputStream  is = zfile.getInputStream(classes);
                OutputStream os = new FileOutputStream(jarArchive)) {
                copy(is, os);
            }
        }
        catch(IOException e) { }

        return jarArchive;
    }

    private static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        for (int n = is.read(buffer); n > 0; n = is.read(buffer))
            os.write(buffer, 0, n);
    }

}
