package jext.sourcecode.project.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface JavaConstants {

    String JAVA = "java";
    String JAVA_EXT = ".java";

    String PACKAGE = "package ";
    String IMPORT_STATIC = "import static ";
    String IMPORT = "import ";
    String PUBLIC = "public ";
    String CLASS = "class ";
    String INTERFACE = "interface ";
    String RECORD = "record ";
    String ENUM = "enum ";
    String ANNOTATION = "@interface ";

    String VOID = "void";
    String NULL = "null";
    String JAVA_LANG_NULL   = "java.lang.Null";
    String JAVA_LANG_VOID   = "java.lang.Void";
    String JAVA_LANG_CLASS  = "java.lang.Class";
    String JAVA_LANG_OBJECT = "java.lang.Object";

    Set<String> PRIMITIVE_TYPES =
        new HashSet<>(Arrays.asList(
            "boolean",
            "byte",
            "char",
            "short",
            "int",
            "long",
            "float",
            "double",
            "void"));
}
