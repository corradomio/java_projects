package jext.lang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface JavaConstants {

    String JAVA = "java";

    String PACKAGE = "package ";
    String IMPORT_STATIC = "import static ";
    String IMPORT = "import ";
    String PUBLIC = "public ";
    String CLASS = "class ";
    String INTERFACE = "interface ";
    String RECORD = "record ";
    String ENUM = "enum ";
    String ANNOTATION = "@interface ";

    String ROOT = "";

    String VOID = "void";
    String NULL = "null";
    String OBJECT = "Object";

    String JAVA_LANG = "java.lang";

    String JAVA_LANG_NULL   = "java.lang.Null";
    String JAVA_LANG_VOID   = "java.lang.Void";
    String JAVA_LANG_CLASS  = "java.lang.Class";
    String JAVA_LANG_OBJECT = "java.lang.Object";

    Set<String> VISIBILITIES =
        new HashSet<>(Arrays.asList(
            "public",
            "protected",
            "private",
            "package"
        ));

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

    Map<String, String> PRIMITIVE_BOXED = new HashMap<String, String>(){{
        put("byte",     "java.lang.Byte");
        put("char",     "java.lang.Character");
        put("double",   "java.lang.Double");
        put("float",    "java.lang.Float");
        put("int",      "java.lang.Integer");
        put("long",     "java.lang.Long");
        put("short",    "java.lang.Short");
        put("void",     "java.lang.Void");
        put("boolean",  "java.lang.Boolean");
        put("null",     "java.lang.Null");
    }};


    /*
        B       byte
        C       char
        D       double
        F       float
        I       int
        J       long
        S       short
        Z       boolean
        L<className>;   class
        [       array
     */
    Map<String, String> PRIMITIVE_SIGNATURE = new HashMap<String, String>(){{
        put("B", "byte");
        put("C", "char");
        put("D", "double");
        put("F", "float");
        put("I", "int");
        put("J", "long");
        put("S", "short");
        put("V", "void");
        put("Z", "boolean");
    }};

    String[] JAVA_LANG_TYPES = {
        //
        // Root of the Java's Object System
        //
        "java.lang.Object",

        //
        // Interfaces
        //
        "java.lang.Appendable",
        "java.lang.AutoCloseable",
        "java.lang.CharSequence",
        "java.lang.Cloneable",
        // "java.lang.Comparable",     // 1
        // "java.lang.Iterable",       // 1
        "java.lang.Readable",
        "java.lang.Runnable",
        // "java.lang.Thread.UncaughtExceptionHandler",

        //
        // Classes
        //
        "java.lang.Boolean",
        "java.lang.Byte",
        "java.lang.Character",
        // "java.lang.Character.Subset",
        // "java.lang.Character.UnicodeBlock",
        // "java.lang.Class",          // 1
        "java.lang.ClassLoader",
        "java.lang.ClassValue",
        "java.lang.Compiler",
        "java.lang.Double",
        "java.lang.Enum",
        "java.lang.Float",
        "java.lang.InheritableThreadLocal",
        "java.lang.Integer",
        "java.lang.Long",
        "java.lang.Math",
        "java.lang.Number",
        // "java.lang.Object",
        "java.lang.Package",
        "java.lang.Process",
        "java.lang.ProcessBuilder",
        // "java.lang.ProcessBuilder.Redirect",
        "java.lang.Runtime",
        "java.lang.RuntimePermission",
        "java.lang.SecurityManager",
        "java.lang.Short",
        "java.lang.StackTraceElement",
        "java.lang.StrictMath",
        "java.lang.String",
        "java.lang.StringBuffer",
        "java.lang.StringBuilder",
        "java.lang.System",
        "java.lang.Thread",
        "java.lang.ThreadGroup",
        // "java.lang.ThreadLocal",        // 1
        "java.lang.Throwable",
        "java.lang.Void",

        // "java.lang.Character.UnicodeScript",
        // "java.lang.ProcessBuilder.Redirect.Type",
        // "java.lang.Thread.State",

        //
        // Exceptions
        //
        "java.lang.ArithmeticException",
        "java.lang.ArrayIndexOutOfBoundsException",
        "java.lang.ArrayStoreException",
        "java.lang.ClassCastException",
        "java.lang.ClassNotFoundException",
        "java.lang.CloneNotSupportedException",
        "java.lang.EnumConstantNotPresentException",
        "java.lang.Exception",
        "java.lang.IllegalAccessException",
        "java.lang.IllegalArgumentException",
        "java.lang.IllegalMonitorStateException",
        "java.lang.IllegalStateException",
        "java.lang.IllegalThreadStateException",
        "java.lang.IndexOutOfBoundsException",
        "java.lang.InstantiationException",
        "java.lang.InterruptedException",
        "java.lang.NegativeArraySizeException",
        "java.lang.NoSuchFieldException",
        "java.lang.NoSuchMethodException",
        "java.lang.NullPointerException",
        "java.lang.NumberFormatException",
        "java.lang.ReflectiveOperationException",
        "java.lang.RuntimeException",
        "java.lang.SecurityException",
        "java.lang.StringIndexOutOfBoundsException",
        "java.lang.TypeNotPresentException",
        "java.lang.UnsupportedOperationException",

        //
        // Errors
        //
        "java.lang.AbstractMethodError",
        "java.lang.AssertionError",
        "java.lang.BootstrapMethodError",
        "java.lang.ClassCircularityError",
        "java.lang.ClassFormatError",
        "java.lang.Error",
        "java.lang.ExceptionInInitializerError",
        "java.lang.IllegalAccessError",
        "java.lang.IncompatibleClassChangeError",
        "java.lang.InstantiationError",
        "java.lang.InternalError",
        "java.lang.LinkageError",
        "java.lang.NoClassDefFoundError",
        "java.lang.NoSuchFieldError",
        "java.lang.NoSuchMethodError",
        "java.lang.OutOfMemoryError",
        "java.lang.StackOverflowError",
        "java.lang.ThreadDeath",
        "java.lang.UnknownError",
        "java.lang.UnsatisfiedLinkError",
        "java.lang.UnsupportedClassVersionError",
        "java.lang.VerifyError",
        "java.lang.VirtualMachineError",

        //
        // Annotations
        //
        "java.lang.Deprecated",
        "java.lang.FunctionalInterface",
        "java.lang.Override",
        "java.lang.SafeVarargs",
        "java.lang.SuppressWarnings"
    };

    String[] JAVA_LANG_GENERICS_ONE = {
        //
        // Interfaces
        //
        "java.lang.Comparable",     // Comparable<T>
        "java.lang.Iterable",       // Iterable<T>

        //
        // Classes
        //
        "java.lang.Class",          // Class<T>
        "java.lang.ThreadLocal",    // ThreadLocal<T>
    };

    String[] JAVA_LANG_GENERICS_TWO = {

    };
}
