package jext.javassist;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jext.logging.Logger;
import jext.nio.file.FilteredFileVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JVMByteCodeAnalyzer {

    private static JVMByteCodeAnalyzer instance = new JVMByteCodeAnalyzer();

    public static JVMByteCodeAnalyzer getInstance() {
        return instance;
    }

    // ----------------------------------------------------------------------
    // EntryPath -> ClassName convertes
    // ----------------------------------------------------------------------

    private static final String JMOD_EXT = ".jmod";
    private static final String DOT_CLASS = ".class";
    private static final String CLASSES_SLASH = "classes/";

    @FunctionalInterface
    private interface EntryPathConverter {
        String entryPathToClassName(String entryPath);
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

    private static EntryPathConverter getEntryPathConverter(Path filename) {
        return (filename.endsWith(JMOD_EXT))
            ? JMODPATH_CONVERTER
            : JARPATH_CONVERTER;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static Logger logger = Logger.getLogger(JVMByteCodeAnalyzer.class);

    private Cache<File, ClasspathElements> classpaths;
    private Cache<File, List<TypeDesc>> typedescs;


    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JVMByteCodeAnalyzer() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public synchronized ClasspathElements getClasspathElements(File fileToAnalyze) throws IOException {
        return getClasspathElementsNoSync(fileToAnalyze);
    }

    private ClasspathElements getClasspathElementsNoSync(File fileToAnalyze) throws IOException {
        check();

        try {
            return classpaths.get(fileToAnalyze, () -> composeClassPaths(fileToAnalyze));
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }


    public synchronized List<TypeDesc> getTypes(File libraryFile) {
        try {
            return getTypesNoSync(libraryFile);
        } catch (IOException e) {
            logger.error(e, e);
            return Collections.emptyList();
        }
    }

    private synchronized List<TypeDesc> getTypesNoSync(File fileToAnalyze) throws IOException {
        check();
        try {
            return typedescs.get(fileToAnalyze, () -> composeTypeDescs(fileToAnalyze));
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }


    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private ClasspathElements composeClassPaths(File fileToAnalyze) throws IOException {
        Path pathToAnalyze = fileToAnalyze.toPath();
        ClasspathElements cpe = new ClasspathElements();
        if (fileToAnalyze.isDirectory())
            analyzeDirectory(cpe, pathToAnalyze);
        else
            analyzeFile(cpe, pathToAnalyze);

        classpaths.put(fileToAnalyze, cpe);
        return cpe;
    }

    private List<TypeDesc> composeTypeDescs(File fileToAnalyze) throws IOException {
        ClasspathElements cpes = getClasspathElementsNoSync(fileToAnalyze);
        List<TypeDesc> descs = new ArrayList<>();

        for (ClasspathElements.ClasspathElement cpe : cpes.classpathElements.values()) {
            TypeDesc td = new TypeDesc();
            td.name = cpe.getName();

            descs.add(td);
        }

        return descs;
    }

    private void analyzeFile(ClasspathElements cpe, Path pathToAnalyze) throws IOException {

        EntryPathConverter epc = getEntryPathConverter(pathToAnalyze);

        String bytecodeFile = pathToAnalyze.toString();
        cpe.appendClassPath(bytecodeFile);

        JarFile jarFile = new JarFile(bytecodeFile);
        JarEntry entry;
        Enumeration<JarEntry> e = jarFile.entries();
        while (e.hasMoreElements()) {
            entry = e.nextElement();
            if (entry != null && !entry.isDirectory() && entry.getName().endsWith(".class")) {
                String name = epc.entryPathToClassName(entry.getName());
                cpe.addElement(name, jarFile, entry);
            }
        }
    }

    private void analyzeDirectory(ClasspathElements cpe, Path dirToAnalyze) throws IOException {
        Files.walkFileTree(dirToAnalyze, new FilteredFileVisitor<Path>() {
            @Override
            public boolean filterFile(Path file) {
                String name = file.toString();
                return name.endsWith(".jar") || name.endsWith(".jmod");
            }

            @Override
            public void onVisitFile(Path fileToAnalyze, BasicFileAttributes attrs) throws IOException {
                analyzeFile(cpe, fileToAnalyze);
            }
        });
    }

    // ----------------------------------------------------------------------
    // Cache
    // ----------------------------------------------------------------------

    private void check() {
        if (classpaths != null)
            return;

        classpaths = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.MINUTES)
            .build();

        typedescs = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.MINUTES)
            .build();
    }

}
