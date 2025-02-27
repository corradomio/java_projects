package jext.javaparser.symbolsolver.resolution.typesolvers;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import jext.java.JavaConstants;
import jext.java.JavaUtils;
import jext.util.logging.Logger;
import jext.util.FileUtils;

import javax.annotation.Nullable;
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

public class ClassPoolRegistry implements JavaConstants {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final Logger logger = Logger.getLogger(ClassPoolRegistry.class);

    private static final String DOT_JAR = ".jar";
    private static final String DOT_JMOD = ".jmod";
    private static final String DOT_CLASS = ".class";
    private static final String CLASSES_SLASH = "classes/";
    private static final String DEFAULT_NAMESPACE = "";

    public class ClasspathElement {
        String name;
        File libraryFile;
        String libraryName;
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
    private final Set<String> namespaces;
    private final Set<String> libraryNames;
    private final Set<File> libraryFiles;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClassPoolRegistry() {
        this.classPool = new ClassPool(false);
        this.classpathElements = new HashMap<>();
        this.libraryNames = new TreeSet<>();
        this.libraryFiles = new HashSet<>();
        this.namespaces = new HashSet<>();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public boolean isEmpty() {
        return classpathElements.isEmpty();
    }

    public int size() {
        return classpathElements.size();
    }

    public ClasspathElement get(String name) {
        return classpathElements.get(name);
    }

    public boolean isNamespace(String name) {
        return namespaces.contains(name);
    }

    public boolean isType(String name) {
        return classpathElements.containsKey(name);
    }

    @Nullable
    public String getLibraryName(String name) {
        if (classpathElements.containsKey(name))
            return classpathElements.get(name).libraryName;
        else
            return null;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // public ClassPoolRegistry addJdk(Collection<File> jdkFiles, String jdkName) {
    //     addAll(jdkFiles, jdkName);
    //     addPrimitiveTypes(jdkName);
    //     checkJdk();
    //     return this;
    // }

    public ClassPoolRegistry addJdk(File jdk) {
        return addJdk(jdk, jdk.getName());
    }

    public ClassPoolRegistry addJdk(File jdk, String rtName) {
        add(jdk, rtName);                             // current directory
        add(new File(jdk, "lib"), rtName);      // jdk 1 -> 8
        add(new File(jdk, "jre/lib"), rtName);  // jre 1 -> 8
        add(new File(jdk, "jmods"), rtName);    // jdk 9 -> ...
        checkJdk(rtName, jdk);
        addPrimitiveTypes(rtName);
        return this;
    }

    public ClassPoolRegistry addAll(Collection<File> libraryFiles) {
        libraryFiles.forEach(this::add);
        return this;
    }

    public ClassPoolRegistry addAll(Collection<File> libraryFiles, String libraryName) {
        libraryFiles.forEach(libraryFile -> add(libraryFile, libraryName));
        return this;
    }

    public ClassPoolRegistry add(File libraryFile) {
        String name = libraryFile.getName();
        return add(libraryFile, libraryFile.getName());
    }

    public ClassPoolRegistry add(File libraryFile, String libraryName) {
        if (libraryFile.isFile())
            addFile(libraryFile, libraryName);
        else if (libraryFile.isDirectory())
            addDirectory(libraryFile, libraryName);
        // else
        //     logger.warnf("Library file %s not existent", FileUtils.getAbsolutePath(libraryFile));
        return this;
    }

    // ----------------------------------------------------------------------

    private void checkJdk(String rtName, File jdk) {
        if (classpathElements.isEmpty())
            logger.errorf("Invalid runtime library %s located at %s", rtName, FileUtils.getAbsolutePath(jdk));
    }

    private void addPrimitiveTypes(String libraryName) {
        for (String primitiveType : PRIMITIVE_TYPES)
            addElement(primitiveType, null, null, libraryName);
    }

    private void addDirectory(File directory, String libraryName) {
        File[] files;

        files = directory.listFiles((p, n) -> n.endsWith(DOT_JAR));
        if (files != null)
            for (File file : files) addFile(file, libraryName);
        files = directory.listFiles((p, n) -> n.endsWith(DOT_JMOD));
        if (files != null)
            for (File file : files) addFile(file, libraryName);
    }

    private void addFile(File libraryFile, String libraryName) {
        if (libraryFiles.contains(libraryFile))
            return;
        String fileName = libraryFile.getName();
        if (libraryNames.contains(fileName))
            return;

        libraryFiles.add(libraryFile);
        libraryNames.add(fileName);

        addElements(libraryFile, libraryName);
    }

    private void addElements(File libraryFile, String libraryName) {
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
                    String qualifiedName = epc.toClassName(entryName);
                    String namespace = epc.toNamespace(entryName);
                    addElement(qualifiedName, /*namespace,*/ libraryFile, entry, libraryName);
                    addNamespace(namespace);
                }
            }
        }
        catch (IOException e) {
            logger.error(e, e);
        }
    }

    private void addElement(String qualifiedName, /*String namespace,*/ File libraryFile, JarEntry entry, String libraryName) {
        if (classpathElements.containsKey(qualifiedName))
            return;

        ClasspathElement element = new ClasspathElement();
        element.name = qualifiedName;
        element.entry = entry;
        element.libraryFile = libraryFile;
        element.libraryName = libraryName;

        classpathElements.put(qualifiedName, element);
    }

    private void addNamespace(String namespace) {
        if (namespaces.contains(namespace))
            return;

        while(namespace.contains(".")) {
            namespaces.add(namespace);
            namespace = JavaUtils.namespaceOf(namespace);
        }
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
            if (pos <= CLASSES_SLASH.length())
                return "";
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
