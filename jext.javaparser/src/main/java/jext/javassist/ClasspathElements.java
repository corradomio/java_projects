package jext.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import jext.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClasspathElements {

    // ----------------------------------------------------------------------
    // Factory method
    // ----------------------------------------------------------------------

    private static ClasspathElements _empty = new ClasspathElements();

    public static ClasspathElements empty() {
        return _empty;
    }

    // ----------------------------------------------------------------------
    // Protected Fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected ClassPool classPool = new ClassPool(false);
    Map<String, ClasspathElement> classpathElements = new TreeMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClasspathElements() { }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public boolean containsKey(String name) {
        return classpathElements.containsKey(name);
    }

    public CtClass get(String name) {
        return classpathElements.get(name).toCtClass();
    }

    public boolean isEmpty() {
        return classpathElements.isEmpty();
    }

    public int size() {
        return classpathElements.size();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    void appendClassPath(String bytecodeFile) throws IOException {
        try {
            classPool.appendClassPath(bytecodeFile);
        } catch (NotFoundException e) {
            throw new IOException(e);
        }
    }

    void addElement(String name, JarFile jarFile, JarEntry entry) {
        classpathElements.put(name, new ClasspathElement(name, jarFile, entry));
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public class ClasspathElement {
        private JarFile jarFile;
        private JarEntry entry;
        private String name;
        private CtClass ctClazz;

        public ClasspathElement(String name, JarFile jarFile, JarEntry entry) {
            this.jarFile = jarFile;
            this.entry = entry;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized CtClass toCtClass() {
            if (ctClazz == null)
                try (InputStream is = jarFile.getInputStream(entry)) {
                    ctClazz = classPool.makeClass(is);
                }
                catch (IOException e) {
                    logger.error(e, e);
                }
            return ctClazz;
        }

    }

    public void dump() {
        classpathElements.keySet().forEach(System.out::println);
    }
}
