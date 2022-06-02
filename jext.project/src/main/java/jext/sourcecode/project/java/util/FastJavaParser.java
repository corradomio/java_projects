package jext.sourcecode.project.java.util;

import jext.sourcecode.project.java.JavaConstants;
import jext.java.TypeRole;
import jext.logging.Logger;
import jext.name.Name;
import jext.name.ObjectName;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FastJavaParser implements JavaConstants {

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

    // DEBUG
    public FastJavaParser clear() {
        parsed = false;
        return this;
    }

    private TypeRole parse() {
        if (parsed)
            return role;
        else
            parsed = true;

        String namespace = "";
        boolean exit = false;

        List<String> lines = FileUtils.toStrings(sourceFile, PUBLIC);
        for (String line : lines) {
            if (exit) break;

            line = line.trim();

            // line comment // ...
            if (line.startsWith("//" ) || line.contains("*"))
                continue;
            // empty line
            else if (line.isEmpty())
                continue;
            // package ...
            else if (line.startsWith(PACKAGE)) {
                namespace = parseNamespace(line);
            }
            // import static ...
            else if (line.startsWith(IMPORT_STATIC)) {
                // import static <namespace>.<name>.<symbol>;
                Name refType = parseImport(line);
                refType = refType.getParent();
                this.classImports.add(refType);
            }
            // import ...
            else if (line.startsWith(IMPORT)) {
                Name refType = parseImport(line);
                if (line.contains(".*;"))
                    this.namespaceImports.add(refType.getParent());
                else
                    this.classImports.add(refType);
            }
            // [public] [abstract] [final] [static] class|enum|[@]interface|record <name>[typeParams] { ...
            // ... class ...
            else if (line.contains(CLASS)) {
                this.role = TypeRole.CLASS;
                exit = true;
            // ... @interface ...
            } else if (line.contains(ANNOTATION)) {
                this.role = TypeRole.ANNOTATION;
                exit = true;
            // ... interface ...
            } else if (line.contains(INTERFACE)) {
                this.role = TypeRole.INTERFACE;
                exit = true;
            // ... enum ...
            } else if (line.contains(ENUM)) {
                this.role = TypeRole.ENUM;
                exit = true;
            // ... record ...
            } else if (line.contains(RECORD)) {
                this.role = TypeRole.CLASS;
                exit = true;
            } else {
                //
            }
        }

        if (role != TypeRole.UNKNOWN) {
            this.sourceRoot = parseRoot(namespace, sourceFile);
            this.type = parseType(namespace, sourceFile);
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
        // the namespace can be not present because
        //
        // 1) the file is in the root package OR
        // 2) the file is an 'example' file in some 'documentation' directory
        //
        // As experiment, we SUPPOSE that the file is in the correct directory IF
        //
        // a) the parent directory is "src", "source" or "sources"
        // b) the parent directories are "src/main/java
        //
        // otherwise the file is considered a simple "resource"
        //
        if (namespace.isEmpty()) {
            File parent = sourceFile.getParentFile();
            String name = parent.getName();
            if (name.equals("src") || name.startsWith("source"))
                return parent;
            if (FileUtils.getAbsolutePath(parent).endsWith("src/main/java"))
                return parent;
            else
                return null;
        }
        // 'package' is present
        String relativePath = namespace.replace(".", "/");
        if (!path.endsWith(relativePath))
            return null;
        int sep = path.lastIndexOf(relativePath);
        if (sep == -1)
            return null;

        String homePath = path.substring(0, sep - 1);
        return new File(homePath);
    }

}
