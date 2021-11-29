package jext.lang;

import java.util.regex.Pattern;

public class JavaUtils implements JavaConstants {

    public static boolean isPrimitive(String name) {
        return PRIMITIVE_TYPES.contains(name);
    }

    public static String boxed(String name) {
        return PRIMITIVE_BOXED.getOrDefault(name, name);
    }

    public static String signature(String type) {
        if (PRIMITIVE_SIGNATURE.containsKey(type))
            return PRIMITIVE_SIGNATURE.get(type);
        else
            return String.format("L%s;", type);
    }

    // ----------------------------------------------------------------------

    public static String qualifiedName(String namespace, String name) {
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

    // ----------------------------------------------------------------------

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
    // Namespace    <name>[.<name>]*
    // Class name   <upperCase><name>
    //


    private static Pattern INNER_CLASSNAME = Pattern.compile("[A-Z$][0-9A-Za-z_$]*(\\.[A-Z$][0-9A-Za-z_$]*)+");
    private static Pattern CLASSNAME  = Pattern.compile("[A-Z$][0-9A-Za-z_$]*");
    private static Pattern IDENTIFIER = Pattern.compile("[a-z_$][0-9A-Za-z_$]*");
    private static Pattern CONSTANT = Pattern.compile("[0-9A-Z_$]+");
    private static Pattern NAMESPACE = Pattern.compile("[0-9a-z_$.]+");
    private static String SPECIAL_CHARS = "|?<(,:";


    public static boolean isNamespace(String symbol) {
        // a namespace is composed by lowercase characters and '.'
        return NAMESPACE.matcher(symbol).matches();
    }

    public static boolean isConstant(String symbol) {
        // a constant is composed only by uppercase characters
        return CONSTANT.matcher(symbol).matches();
    }

    public static boolean isIdentifier(String symbol) {
        // in general, an identifier starts with a lowercase letter
        return IDENTIFIER.matcher(symbol).matches();
    }

    public static boolean isClassName(String symbol) {
        // in general, a class starts with an uppercase letter
        // or it contains "." to fully qualified

        //return symbol.contains(".") || CLASSNAME.matcher(symbol).matches();
        return CLASSNAME.matcher(symbol).matches() || INNER_CLASSNAME.matcher(symbol).matches();
    }

    public static boolean isQualified(String symbol) {
        // in general, a symbol containing a dot is a qualified name
        return symbol.contains(".");
    }

    public static boolean isSpecialName(String symbol) {
        for (int i=0; i<SPECIAL_CHARS.length(); ++i)
            if (symbol.indexOf(SPECIAL_CHARS.charAt(i)) != -1)
                return true;
        return false;
    }

    // ----------------------------------------------------------------------

    /**
     * The string is a class. Can be a generics. It strip the generic part.
     * Sometime, it can be a method call:
     *
     *      [type].method(...)
     *
     * it strip also the 'method part'
     *
     * @param signature
     * @return
     */

    public static String toPlainSignature(String signature) {
        int d;
        char ch;
        StringBuilder sb;

        // string "... .method(...)"
        if (signature.contains("(")) {
            int pos = signature.indexOf('(');
            int end = signature.lastIndexOf('.', pos-1);

            if (end == -1)
                signature = signature.substring(0, pos);
            else
                signature = signature.substring(0, end);
        }

        if (!signature.contains("<"))
            return signature;

        // strip <...>
        d = 0;
        sb = new StringBuilder();
        for (int i=0; i<signature.length(); ++i) {
            ch = signature.charAt(i);
            if (ch == '<') {
                d++;
                continue;
            }
            else if (ch == '>') {
                d--;
                continue;
            }
            else if (d > 0) {
                continue;
            }
            else {
                sb.append(ch);
            }
        }

        signature = sb.toString();

        // strip [...]
        d = 0;
        sb = new StringBuilder();
        for (int i=0; i<signature.length(); ++i) {
            ch = signature.charAt(i);
            if (ch == '[') {
                d++;
                continue;
            }
            else if (ch == ']') {
                d--;
                continue;
            }
            else if (d > 0) {
                continue;
            }
            else {
                sb.append(ch);
            }
        }

        signature = sb.toString();

        return signature;
    }

    public static boolean isPlainSignature(String signature) {
        return !signature.contains("<") && !signature.contains("(");
    }

    public static boolean isPlainMethodSignature(String signature) {
        return !signature.contains("<");
    }

    // ----------------------------------------------------------------------

    public static String toPlainMethodSignature(String signature) {
        int state = 0;
        int len = signature.length();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<len; ++i) {
            char ch = signature.charAt(i);
            if (ch == '<')
                state += 1;
            else if (ch == '>')
                state -= 1;
            else if (state == 0)
                sb.append(ch);
            else
                continue;
        }
        return sb.toString();
    }

    public static String toPlainMethodSignature(String methodName, int nParams) {
        if (nParams == 0)
            return methodName + "()";

        StringBuilder sb = new StringBuilder(methodName).append("(").append(JAVA_LANG_OBJECT);
        for (int i=1; i<nParams; ++i)
            sb.append(", ").append(JAVA_LANG_OBJECT);
        sb.append(")");
        return sb.toString();
    }
}
