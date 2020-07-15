package jext.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClasspathElements {

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private class ClasspathElement {
        private String name;
        private File libFile;
        private JarEntry entry;
        private CtClass ctClazz;

        public ClasspathElement(String name, File libFile, JarEntry entry) {
            this.name = name;
            this.libFile = libFile;
            this.entry = entry;
        }

        public String getName() {
            return name;
        }

        public synchronized CtClass toCtClass() {
            if (ctClazz == null)
                try (JarFile jarFile = new JarFile(libFile);
                     InputStream is = jarFile.getInputStream(entry)) {
                    ctClazz = classPool.makeClass(is);
                }
                catch (IOException e) { }
            return ctClazz;
        }
    }

    // ----------------------------------------------------------------------
    // Protected Fields
    // ----------------------------------------------------------------------

    private ClassPool classPool = new ClassPool(false);
    private Map<String, ClasspathElement> classEntries = new TreeMap<>();
    private Set<File> libFiles = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ClasspathElements() { }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public boolean containsKey(String name) {
        return classEntries.containsKey(name);
    }

    public CtClass get(String name) {
        return classEntries.get(name).toCtClass();
    }

    public boolean isEmpty() {
        return classEntries.isEmpty();
    }

    public int size() {
        return classEntries.size();
    }

    public Collection<String> keySet() {
        return classEntries.keySet();
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    void addElement(String name, File libFile, JarEntry entry) {
        classEntries.put(name, new ClasspathElement(name, libFile, entry));
        if (!libFiles.contains(libFile))
        try {
            classPool.insertClassPath(libFile.getAbsolutePath());
            libFiles.add(libFile);
        } catch (NotFoundException e) { }
    }

}
