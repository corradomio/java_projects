package jext.javaparser.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import jext.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ClassPoolRegistry {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(ClassPoolRegistry.class);

    private static final String DOT_JAR = ".jar";
    private static final String DOT_JMOD = ".jmod";
    private static final String DOT_CLASS = ".class";
    private static final String CLASSES_SLASH = "classes/";

    public class ClasspathElement {
        String name;
        File libraryFile;
        JarEntry entry;
        CtClass ctClazz;

        public CtClass toCtClass() throws IOException {
            synchronized (this) {
                if (ctClazz != null)
                    return ctClazz;

                try (JarFile jarFile = new JarFile(libraryFile);
                     InputStream is = jarFile.getInputStream(entry)) {
                    ctClazz = classPool.makeClass(is);
                }
                return ctClazz;
            }
        }
    }

    private final ClassPool classPool;
    private final Map<String, ClasspathElement> classpathElements;
    private final Set<String> libraryNames;
    private final Set<File> librarieFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClassPoolRegistry() {
        this.classPool = new ClassPool(false);
        this.classpathElements = new HashMap<>();
        this.libraryNames = new TreeSet<>();
        this.librarieFiles = new HashSet<>();
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

    public ClassPoolRegistry addJdk(File jdk) {
        add(new File(jdk, "lib"));      // jdk 1 -> 8
        add(new File(jdk, "jre/lib"));  // jre 1 -> 8
        add(new File(jdk, "jmods"));    // jdk 9 -> ...
        return this;
    }

    public ClassPoolRegistry addAll(List<File> libraryFiles) {
        libraryFiles.forEach(this::add);
        return this;
    }

    public ClassPoolRegistry add(File libraryFile) {
        if (libraryFile.isFile())
            addFile(libraryFile);
        else
            addDirectory(libraryFile);
        return this;
    }

    private void addDirectory(File directory) {
        File[] files;

        files = directory.listFiles((p, n) -> n.endsWith(DOT_JAR));
        if (files != null)
            for (File file : files) addFile(file);
        files = directory.listFiles((p, n) -> n.endsWith(DOT_JMOD));
        if (files != null)
            for (File file : files) addFile(file);
    }

    private void addFile(File libraryFile) {
        if (librarieFiles.contains(libraryFile))
            return;
        String libraryName = libraryFile.getName();
        if (libraryNames.contains(libraryName))
            return;

        librarieFiles.add(libraryFile);
        libraryNames.add(libraryName);

        addElements(libraryFile);
    }

    private void addElements(File libraryFile) {
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
                    addElement(name, libraryFile, entry);
                }
            }
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    private void addElement(String name, File libraryFile, JarEntry entry) {
        ClasspathElement element = new ClasspathElement();
        element.name = name;
        element.libraryFile = libraryFile;
        element.entry = entry;

        classpathElements.put(name, element);
    }

    // ----------------------------------------------------------------------
    // EntryPath -> ClassName converters
    // ----------------------------------------------------------------------

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
