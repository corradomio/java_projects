package jext.java;

import jext.name.Name;
import jext.name.ObjectName;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FastJavaParser {

    private File sourceFile;
    private File sourceRoot;
    private Name type;
    private TypeRole role = TypeRole.UNKNOWN;
    private List<Name> starImports = new ArrayList<>();
    private List<Name> namedImports = new ArrayList<>();
    private boolean parsed;

    public FastJavaParser(File sourceFile) {
        this.sourceFile = sourceFile;
    }

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
        return line.substring(sep+1, end).trim();
    }

    private static Name parseImport(String line) {
        // import <namespace>.*;
        // import <namespace>.<name>;
        // import static <namespace>.<name>.<symbol>;
        int end = line.indexOf(';');
        int sep = line.lastIndexOf(' ', end);
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
        if (!path.endsWith(relativePath))
            return null;
        int sep = path.indexOf(relativePath);
        if (sep == -1)
            return null;
        else
            return new File(path.substring(0, sep-1));
    }

    public Name getType() {
        return type;
    }

    public List<Name> getNamedImports() {
        return namedImports;
    }
}