package jext.java;

import jext.logging.Logger;
import jext.name.Name;
import jext.name.ObjectName;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class FastJavaParser {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private File sourceFile;
    private File sourceRoot;
    private Name type = new ObjectName("Unknown");
    private TypeRole role = TypeRole.UNKNOWN;
    private List<Name> namespaceImports = new ArrayList<>();
    private List<Name> classImports = new ArrayList<>();
    private boolean parsed;

    private Logger logger;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public FastJavaParser(File sourceFile) {
        this.sourceFile = sourceFile;
        this.logger = Logger.getLogger(getClass(), sourceFile.getName());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public Name getType() {
        parse();
        return type;
    }

    public TypeRole getRole() {
        parse();
        return role;
    }

    public List<Name> getImportedClasses() {
        parse();
        return classImports;
    }

    public List<Name> getImportedNamespaces() {
        parse();
        return namespaceImports;
    }

    public Optional<File> getSourceRoot() {
        parse();
        return Optional.ofNullable(sourceRoot);
    }

    // ----------------------------------------------------------------------
    // Parse the source code
    // ----------------------------------------------------------------------

    public TypeRole parse() {
        if (parsed)
            return this.role;
        else
            parsed = true;

        try {
            String namespace = "";
            boolean exit = false;

            List<String> lines = FileUtils.toStrings(sourceFile, JavaUtils.PUBLIC);
            for (String line : lines) {
                if (exit) break;

                // // ...
                if (line.startsWith("//"))
                    continue;
                    // package ...
                else if (line.startsWith(JavaUtils.PACKAGE)) {
                    namespace = parseNamespace(line);
                }
                // import static ...
                else if (line.startsWith(JavaUtils.IMPORT_STATIC)) {
                    // import static <namespace>.<name>.<symbol>;
                    Name refType = parseImport(line);
                    refType = refType.getParent();
                    this.classImports.add(refType);
                }
                // import ...
                else if (line.startsWith(JavaUtils.IMPORT)) {
                    Name refType = parseImport(line);
                    if (line.contains(".*;"))
                        this.namespaceImports.add(refType.getParent());
                    else
                        this.classImports.add(refType);
                }
                // [public] [abstract] [final] [static] class|enum|interface [@]<name>[typeParams] { ...
                else if (line.contains(JavaUtils.CLASS)) {
                    this.role = TypeRole.CLASS;
                    exit = true;
                } else if (line.contains(JavaUtils.INTERFACE)) {
                    this.role = TypeRole.INTERFACE;
                    exit = true;
                } else if (line.contains(JavaUtils.ENUM)) {
                    this.role = TypeRole.ENUM;
                    exit = true;
                } else if (line.contains(JavaUtils.ANNOTATION)) {
                    this.role = TypeRole.ANNOTATION;
                    exit = true;
                } else {
                    //
                }
            }

            if (role != TypeRole.UNKNOWN) {
                this.sourceRoot = parseRoot(namespace, sourceFile);
                this.type = parseType(namespace, sourceFile);
            } else {
                role = TypeRole.UNKNOWN;
            }
        } catch (Throwable t) {
            logger.error(t, t);
        }

        return this.role;
    }

    private static String parseNamespace(String line) {
        // package <namespace>;
        int sep = line.indexOf(' ');
        int end = line.indexOf(';', sep);

        if (end == -1)
            return line.substring(sep + 1).trim();
        else
            return line.substring(sep + 1, end).trim();
    }

    private static Name parseImport(String line) {
        // import <namespace>.*;
        // import <namespace>.<name>;
        // import static <namespace>.<name>.<symbol>;
        int sep = line.indexOf(' ');
        int end = line.indexOf(';', sep);

        String symbol;
        if (end == -1)
            symbol = line.substring(sep + 1).trim();
        else
            symbol = line.substring(sep + 1, end).trim();

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
        if (namespace.isEmpty())
            return sourceFile.getParentFile();
        if (!path.endsWith(relativePath))
            return null;
        int sep = path.indexOf(relativePath);
        if (sep == -1)
            return null;
        else
            return new File(path.substring(0, sep - 1));
    }

}
