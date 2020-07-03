package jext.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class JavaUtils {

    public static final String JAVA_LANG = "java.lang";
    public static final String JAVA_LANG_OBJECT = "java.lang.Object";
    public static final String ROOT_PACKAGE = "";

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
    public static final Set<String> PRIMITIVE_TYPES =
            new HashSet<>(Arrays.asList("boolean", "byte", "char", "short", "int", "long", "float", "double", "void"));

    public static boolean isPrimitiveType(String typeName) {
        return PRIMITIVE_TYPES.contains(typeName);
    }

    private static final Map<String, String> PRIMITIVE_MAP = new HashMap<String, String>(){{
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

    public static String fullName(String namespace, String name) {
        return fullName(namespace, name, false);
    }

    public static String fullName(String namespace, String name, boolean isInner) {
        if (namespace.length() == 0)
            return name;
        else if (isInner)
            return String.format("%s$%s", namespace, name);
        else
            return String.format("%s.%s", namespace, name);
    }

    public static boolean isInner(String fullName) {
        return fullName.contains("$");
    }

    public static String callName(String namespace, String name) {
        return String.format("%s:%s", namespace, name);
    }

    public static boolean isFullName(String typeName) {
        return typeName.contains(".");
    }

    // ----------------------------------------------------------------------

    // public static String lastOf(String fullName) {
    //     int pos = fullName.lastIndexOf(".");
    //     if (pos != -1)
    //         return fullName.substring(pos+1);
    //     else
    //         return fullName;
    // }

    // public static String headOf(String fullName) {
    //     int pos = fullName.indexOf(".");
    //     if (pos != -1)
    //         return fullName.substring(0, pos);
    //     else
    //         return fullName;
    // }

    // public static String restOf(String fullName) {
    //     int pos = fullName.indexOf(".");
    //     if (pos != -1)
    //         return fullName.substring(pos+1);
    //     else
    //         return "";
    // }

    public static String namespaceOf(String fullName) {
        int pos = fullName.lastIndexOf(".");
        if (pos != -1)
            return fullName.substring(0, pos);
        else
            return "";
    }

    public static String nameOf(String fullName) {
        int pos = fullName.lastIndexOf('.');
        return fullName.substring(pos+1);
    }

    // ----------------------------------------------------------------------

    private static Pattern NAMESPACE = Pattern.compile("([a-z]+)(\\.[a-z]+)+");

    public static boolean isNamespace(String symbol) {
        // <name>.<name>... with <name> in lowercase
        // a generic identifier starting with a lowercase letter
        return NAMESPACE.matcher(symbol).matches();
    }

    private static Pattern IDENTIFIER = Pattern.compile("[a-z_$][A-Z0-9a-z_$]*");

    public static boolean isIdentifier(String symbol) {
        // <name>.<name>... with <name> in lowercase
        // a generic identifier starting with a lowercase letter
        return IDENTIFIER.matcher(symbol).matches();
    }

    // ----------------------------------------------------------------------

    public static boolean isSignature(String symbol) {
        return symbol.contains("<") || symbol.contains("[");
    }

    public static String stripSignature(String signature) {
        while (signature.contains("<")) {
            int end = signature.lastIndexOf('>');
            int bgn = signature.lastIndexOf('<');
            signature = signature.substring(0, bgn) + signature.substring(end+1);
        }
        while (signature.contains("[")) {
            int end = signature.lastIndexOf(']');
            int bgn = signature.lastIndexOf('[');
            signature = signature.substring(0, bgn) + signature.substring(end+1);
        }
        return signature;
    }
}
