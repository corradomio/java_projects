package jext.javaparser.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import jext.io.util.FileFilters;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassPoolRegistry {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(ClassPoolRegistry.class);

    public class ClasspathElement {
        String name;
        File libraryFile;
        JarEntry entry;
        CtClass ctClazz;

        public CtClass toCtClass() throws IOException {
            synchronized (classPool) {
                if (ctClazz != null)
                    return ctClazz;

                try (JarFile jarFile = new JarFile(libraryFile);
                     InputStream is = jarFile.getInputStream(entry)) {
                    return classPool.makeClass(is);
                }
            }
        }
    }

    private ClassPool classPool = new ClassPool(false);
    private Map<String, ClasspathElement> classpathElements = new TreeMap<>();
    private Set<File> libraryFiles = new TreeSet<>();
    private Set<String> libraryNames = new TreeSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClassPoolRegistry() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean containsKey(String name) {
        return classpathElements.containsKey(name);
    }

    public ClasspathElement get(String name) {
        return classpathElements.get(name);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public void addAll(List<File> libraryFiles) {
        libraryFiles.forEach(this::add);
    }

    public void add(File libraryFile) {
        if (libraryFile.isFile())
            addFile(libraryFile);
        else
            addDirectory(libraryFile);
    }

    private void addDirectory(File libraryDirectory) {
        FileUtils.listFiles(libraryDirectory, FileFilters.IS_JAR)
            .forEach(this::addFile);
        FileUtils.listFiles(libraryDirectory, FileFilters.IS_JAR)
            .forEach(this::addFile);
    }

    private void addFile(File libraryFile) {
        String libraryName = libraryFile.getName();
        if (libraryFiles.contains(libraryFile))
            return;
        if (libraryNames.contains(libraryName))
            return;

        libraryFiles.add(libraryFile);
        libraryNames.add(libraryName);

        addEntries(libraryFile);
    }

    private void addEntries(File libraryFile) {
        try {
            classPool.insertClassPath(libraryFile.getAbsolutePath());
        } catch (NotFoundException e) {
            logger.error(e, e);
        }

        EntryPathConverter epc = getEntryPathConverter(libraryFile);

        try (JarFile jarFile = new JarFile(libraryFile)) {
            JarEntry entry;
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                entry = e.nextElement();
                if (entry != null && !entry.isDirectory() && entry.getName().endsWith(DOT_CLASS)) {
                    String name = epc.toClassName(entry.getName());
                    addEntry(name, libraryFile, entry);
                }
            }
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    private void addEntry(String name, File libraryFile, JarEntry entry) {
        ClasspathElement info = new ClasspathElement();
        info.name = name;
        info.libraryFile = libraryFile;
        info.entry = entry;

        classpathElements.put(name, info);
    }

    // ----------------------------------------------------------------------
    // EntryPath -> ClassName converters
    // ----------------------------------------------------------------------

    // private static final String DOT_JAR = ".jar";
    // private static final String DOT_JMOD = ".jmod";
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
        return (libFile.getName().endsWith(".jmod"))
            ? JMODPATH_CONVERTER
            : JARPATH_CONVERTER;
    }

}