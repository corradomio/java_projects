package jext.javaparser.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import jext.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavassistSymbols {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(JavassistSymbols.class);

    private static final String DOT_JAR = ".jar";
    private static final String DOT_JMOD = ".jmod";
    private static final String DOT_CLASS = ".class";
    private static final String CLASSES_SLASH = "classes/";
    private static final String DEFAULT_NAMESPACE = "";

    public class ClasspathElement {
        String name;
        String libraryName;
        File   libraryFile;
        JarEntry entry;
        CtClass ctClazz;

        public CtClass toCtClass() {
            synchronized (this) {
                if (ctClazz != null)
                    return ctClazz;

                try (JarFile jarFile = new JarFile(libraryFile);
                     InputStream is = jarFile.getInputStream(entry)) {
                    ctClazz = classPool.makeClass(is);
                }
                catch (IOException e) {

                }
                return ctClazz;
            }
        }

    }

    private final ClassPool   classPool;
    private final Map<String, ClasspathElement> classpathElements;
    private final Set<String> namespaces;
    private final Set<String> libraryNames;
    private final Set<File>   libraryFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JavassistSymbols() {
        this.classPool = new ClassPool(false);
        this.classpathElements = new HashMap<>();
        this.libraryNames = new TreeSet<>();
        this.libraryFiles = new HashSet<>();
        this.namespaces = new HashSet<>();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public ClasspathElement get(String name) {
        return classpathElements.get(name);
    }

    public boolean isNamespace(String name) {
        return namespaces.contains(name);
    }

    public boolean isType(String name) {
        return classpathElements.containsKey(name);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public JavassistSymbols addJdk(File jdk) {
        String libraryName = jdk.getName();
        add(libraryName, new File(jdk, "lib"));      // jdk 1 -> 8
        add(libraryName, new File(jdk, "jre/lib"));  // jre 1 -> 8
        add(libraryName, new File(jdk, "jmods"));    // jdk 9 -> ...
        return this;
    }

    public JavassistSymbols addAll(String libraryName, Collection<File> libraryFiles) {
        libraryFiles.forEach(libraryFile -> {
            add(libraryName, libraryFile);
        });
        return this;
    }

    public JavassistSymbols add(File libraryFile) {
        add(libraryFile.getName(), libraryFile);
        return this;
    }

    public JavassistSymbols add(String libraryName, File libraryFile) {
        if (libraryFile.isFile())
            addFile(libraryName, libraryFile);
        else if (libraryFile.isDirectory())
            addDirectory(libraryName, libraryFile);
        // else
        //     logger.warnf("Library file %s not existent", FileUtils.getAbsolutePath(libraryFile));
        return this;
    }

    private void addDirectory(String libraryName, File directory) {
        File[] files;

        files = directory.listFiles((p, n) -> n.endsWith(DOT_JAR));
        if (files != null)
            for (File file : files) addFile(libraryName, file);
        files = directory.listFiles((p, n) -> n.endsWith(DOT_JMOD));
        if (files != null)
            for (File file : files) addFile(libraryName, file);
    }

    private void addFile(String libraryName, File libraryFile) {
        String fileName = libraryFile.getName();
        if (libraryFiles.contains(libraryFile))
            return;
        if (libraryNames.contains(fileName))
            return;

        libraryFiles.add(libraryFile);
        libraryNames.add(fileName);

        addElements(libraryName, libraryFile);
    }

    private void addElements(String libraryName, File libraryFile) {
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
                    String entryName = entry.getName();
                    String className = epc.toClassName(entryName);
                    String namespace = epc.toNamespace(entryName);
                    addElement(className, namespace, libraryFile, entry, libraryName);
                }
            }
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    protected void addElement(String name, String namespace, File libraryFile, JarEntry entry, String libraryName) {
        ClasspathElement element = new ClasspathElement();
        element.name = name;
        element.libraryFile = libraryFile;
        element.libraryName = libraryName;
        element.entry = entry;

        classpathElements.put(name, element);
        namespaces.add(namespace);
    }

    // ----------------------------------------------------------------------
    // EntryPath -> ClassName converters
    // ----------------------------------------------------------------------

    private interface EntryPathConverter {
        String toClassName(String entryPath);
        String toNamespace(String namespace);
    }

    private static EntryPathConverter JARPATH_CONVERTER = new EntryPathConverter() {

        @Override
        public String toClassName(String entryPath) {
            // if (!entryPath.endsWith(DOT_CLASS)) {
            //     throw new IllegalStateException();
            // }
            String className = entryPath.substring(0, entryPath.length() - DOT_CLASS.length());
            className = className.replace('/', '.');
            className = className.replace('$', '.');
            return className;
        }

        @Override
        public String toNamespace(String entryPath) {
            int pos = entryPath.lastIndexOf('/');
            String namespace = pos != -1 ? entryPath.substring(0, pos) : DEFAULT_NAMESPACE;
            namespace = namespace.replace('/', '.');
            return namespace;
        }
    };


    private static EntryPathConverter JMODPATH_CONVERTER = new EntryPathConverter() {

        @Override
        public String toClassName(String entryPath) {
            // if (!entryPath.endsWith(DOT_CLASS) || !entryPath.startsWith(CLASSES_SLASH)) {
            //     throw new IllegalStateException();
            // }
            String className = entryPath.substring(CLASSES_SLASH.length(), entryPath.length() - DOT_CLASS.length());
            className = className.replace('/', '.');
            className = className.replace('$', '.');
            return className;
        };

        @Override
        public String toNamespace(String entryPath) {
            int pos = entryPath.lastIndexOf('/');
            String namespace = entryPath.substring(CLASSES_SLASH.length(), pos);
            namespace = namespace.replace('/', '.');
            return namespace;
        }
    };


    private static EntryPathConverter getEntryPathConverter(File libFile) {
        return (libFile.getName().endsWith(".jmod"))
            ? JMODPATH_CONVERTER
            : JARPATH_CONVERTER;
    }

}