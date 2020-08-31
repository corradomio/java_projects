package jext.buildtools.source.java;

import jext.buildtools.Name;
import jext.buildtools.util.ObjectName;
import jext.util.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FastJavaParser {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private File sourceFile;
    private File sourceRoot;
    private Name type;
    private TypeRole role = TypeRole.UNKNOWN;
    private Set<Name> starImports = new HashSet<>();
    private Set<Name> namedImports = new HashSet<>();
    private boolean parsed;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public FastJavaParser(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public Name getType() {
        return type;
    }

    public Set<Name> getNamedImports() {
        return namedImports;
    }

    public Set<Name> getStarImports() {
        return starImports;
    }

    public File getSourceRoot() {
        return sourceRoot;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public FastJavaParser parse() {
        if (parsed)
            return this;
        else
            parsed = true;

        String namespace = "";
        boolean exit = false;

        List<String> lines = FileUtils.toStrings(sourceFile, JavaUtils.PUBLIC);
        for(String line : lines) {
            if (exit) break;

            // package <namespace>;
            if (line.startsWith(JavaUtils.PACKAGE)) {
                namespace = parseNamespace(line);
            }
            // public [abstract] [final] [static] class|enum|interface [@]<name>[typeParams] { ...
            else if (line.startsWith(JavaUtils.PUBLIC)) {
                if (line.contains(JavaUtils.CLASS)) {
                    this.role = TypeRole.CLASS;
                }
                else if (line.contains(JavaUtils.INTERFACE)) {
                    this.role = TypeRole.INTERFACE;
                }
                else if (line.contains(JavaUtils.ENUM)) {
                    this.role = TypeRole.ENUM;
                }
                else if (line.contains(JavaUtils.ANNOTATION)) {
                    this.role = TypeRole.ANNOTATION;
                }
                else {
                    this.role = TypeRole.UNKNOWN;
                }
                exit = true;
            }
            else if (line.startsWith(JavaUtils.IMPORT_STATIC)) {
                // import static <namespace>.<name>.<symbol>;
                Name refType = parseImport(line);
                refType = refType.getParent();
                this.namedImports.add(refType);
            }
            else if (line.startsWith(JavaUtils.IMPORT)) {
                Name refType = parseImport(line);
                if (line.contains(".*;"))
                    this.starImports.add(refType.getParent());
                else
                    this.namedImports.add(refType);
            }
        }
        this.sourceRoot = parseRoot(namespace, sourceFile);
        this.type = parseType(namespace, sourceFile);

        return this;
    }

    private static String parseNamespace(String line) {
        // package <namespace>;
        int sep = line.indexOf(' ');
        int end = line.indexOf(';');
        if (end == -1) end = line.length();
        return line.substring(sep+1, end).trim();
    }

    private static Name parseImport(String line) {
        // import <namespace>.*;
        // import <namespace>.<name>;
        // import static <namespace>.<name>.<symbol>;
        int end = line.indexOf(';');
        int sep = line.lastIndexOf(' ', end);
        if (end == -1) end = line.length();
        String symbol = line.substring(sep+1, end).trim();
        return new ObjectName(symbol);
    }

    private static Name parseType(String namespace, File sourceFile) {
        // public [static] [final] [abstract] class|enum|interface [@]<name> ...
        String className = FileUtils.getNameWithoutExt(sourceFile);
        if (className.contains("-"))
            return null;
        else
            return new ObjectName(namespace, className);
    }

    private static File parseRoot(String namespace, File sourceFile) {
        String path = FileUtils.getAbsolutePath(sourceFile.getParentFile());
        String relativePath = namespace.replace(".", "/");
        // if (!path.endsWith(relativePath))
        //     return null;
        if (namespace.isEmpty())
            return sourceFile.getParentFile();
        int sep = path.lastIndexOf(relativePath);
        if (sep == -1)
            return parentByCount(sourceFile, namespace.split("\\.").length);
        else
            return new File(path.substring(0, sep-1));
    }

    private static File parentByCount(File file, int components) {
        for(int i=0; i<components; ++i)
            file = file.getParentFile();
        return file;
    }

}
