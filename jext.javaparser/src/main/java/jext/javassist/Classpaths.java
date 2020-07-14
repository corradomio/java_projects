package jext.javassist;

import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class Classpaths {

    private static Classpaths instance = new Classpaths();

    public static Classpaths getInstance() {
        return instance;
    }

    // ----------------------------------------------------------------------
    // EntryPath -> ClassName converters
    // ----------------------------------------------------------------------

    private static final String DOT_JAR = ".jar";
    private static final String DOT_JMOD = ".jmod";
    private static final String DOT_CLASS = ".class";
    private static final String CLASSES_SLASH = "classes/";

    @FunctionalInterface
    private interface EntryPathConverter {
        String toClassName(String entryPath);
    }

    private static EntryPathConverter JARPATH_CONVERTER = entryPath -> {
        if (!entryPath.endsWith(DOT_CLASS)) {
            throw new IllegalStateException();
        }
        String className = entryPath.substring(0, entryPath.length() - DOT_CLASS.length());
        className = className.replace('/', '.');
        className = className.replace('$', '.');
        return className;
    };

    private static EntryPathConverter JMODPATH_CONVERTER = entryPath -> {
        if (!entryPath.endsWith(DOT_CLASS) || !entryPath.startsWith(CLASSES_SLASH)) {
            throw new IllegalStateException();
        }
        String className = entryPath.substring(CLASSES_SLASH.length(), entryPath.length() - DOT_CLASS.length());
        className = className.replace('/', '.');
        className = className.replace('$', '.');
        return className;
    };

    private static EntryPathConverter getEntryPathConverter(File libFile) {
        return (libFile.getName().endsWith(DOT_JMOD))
            ? JMODPATH_CONVERTER
            : JARPATH_CONVERTER;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Map<File, ClasspathElements> classpaths = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Classpaths() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public synchronized ClasspathElements getClasspathElements(File libFile) {
        if (!classpaths.containsKey(libFile))
            composeClasspath(libFile);

        return classpaths.get(libFile);
    }

    public Collection<File> classpaths() {
        return this.classpaths.keySet();
    }

    public Collection<ClasspathElements> classpathElements() {
        return this.classpaths.values();
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private ClasspathElements composeClasspath(File libFile) {
        ClasspathElements cpe = new ClasspathElements();

        if (libFile.isDirectory())
            analyzeDirectory(cpe, libFile);
        else
            analyzeFile(cpe, libFile);

        classpaths.put(libFile, cpe);
        return cpe;
    }

    private void analyzeFile(ClasspathElements cpe, File libFile) {

        EntryPathConverter epc = getEntryPathConverter(libFile);

        try (JarFile jarFile = new JarFile(libFile)) {
            JarEntry entry;
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                entry = e.nextElement();
                if (entry != null && !entry.isDirectory() && entry.getName().endsWith(DOT_CLASS)) {
                    String name = epc.toClassName(entry.getName());
                    cpe.addElement(name, libFile, entry);
                }
            }
        }
        catch (IOException e) {}
    }

    private void analyzeDirectory(ClasspathElements cpe, File libDir) {
        FileUtils.listFiles(libDir, DOT_JAR).forEach(libFile ->analyzeFile(cpe, libFile));
        FileUtils.listFiles(libDir, DOT_JMOD).forEach(libFile ->analyzeFile(cpe, libFile));
    }

}
