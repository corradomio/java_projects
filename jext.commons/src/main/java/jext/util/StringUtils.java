package jext.util;

import jext.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtils {

    private static final Logger logger = Logger.getLogger(StringUtils.class) ;

    private static final String EMPTY_STRING = "";
    private static final String[] EMPTY_ARRAY = new String[0];

    // ----------------------------------------------------------------------
    // Empty String
    // Empty String Array
    // ----------------------------------------------------------------------

    public static String empty() {
        return EMPTY_STRING;
    }
    public static String[] emptyArray() { return EMPTY_ARRAY; }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
    public static boolean isNotEmpty(String s) { return s != null && s.length() > 0; }

    // ----------------------------------------------------------------------
    // Compose
    // ----------------------------------------------------------------------
    //  strings + separator -> <string1><separator><string2>...

    public static String compose(Collection<String> v, String sep) {
        if (v == null || v.size() == 0)
            return "";

        Iterator<String> it = v.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(it.next());
        while(it.hasNext()) {
            sb.append(sep);
            sb.append(it.next());
        }
        return sb.toString();
    }

    public static String compose(String[] v, String sep) {
        if (v == null || v.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(v[0]);
        for(int i=1; i<v.length; ++i) {
            sb.append(sep);
            sb.append(v[i]);
        }
        return sb.toString();
    }

    // ----------------------------------------------------------------------
    // List<String <-> String[]
    // ----------------------------------------------------------------------

    public static String toString(Object obj) {
        if (obj == null)
            return null;
        else
            return obj.toString();
    }

    public static String[] toArray(Collection<String> l) {
        if (l == null || l.isEmpty())
            return EMPTY_ARRAY;
        String[] a = new String[l.size()];
        int i=0;
        for (String e : l) {
            a[i++] = e;
        }
        return a;
    }

    public static List<String> asList(Object obj) {
        if (obj == null)
            return Collections.emptyList();
        if (obj instanceof String)
            return Collections.singletonList((String)obj);
        if (obj instanceof List)
            return (List<String>)obj;
        if (obj instanceof String[])
            return Arrays.asList((String[]) obj);
        if (obj instanceof Collection)
            return new ArrayList<>((Collection)obj);
        else
            return Collections.singletonList(obj.toString());
    }

    public static Set<String> asSet(Object obj) {
        if (obj == null)
            return Collections.emptySet();
        if (obj instanceof String)
            return Collections.singleton((String)obj);
        if (obj instanceof List)
            return new HashSet<String>((List)obj);
        if (obj instanceof String[])
            return new HashSet<>(Arrays.asList((String[]) obj));
        if (obj instanceof Collection)
            return new HashSet<String>((Collection)obj);
        else
            return Collections.singleton(obj.toString());
    }

    /**
     * Collect only not empty strings
     */
    public static List<String> asList(String[] strings) {
        if (strings == null)
            return Collections.emptyList();

        List<String> slist = new ArrayList<>();
        for (String s : strings) {
            s = s.trim();
            if (!s.isEmpty())
                slist.add(s);
        }
        return slist;
    }

    /**
     * As String.split() but return a List[String] instead than a String[]
     */
    public static List<String> split(String s, String sep) {
        return asList(s.split(sep));
    }

    /**
     * Append s to obj.
     * If obj is a String,   creates and array of 2 elements
     * If obj is a String[], creates a new array with s appended at the end
     */
    public static String[] append(Object obj, String s) {
        if (obj instanceof String) {
            return new String[]{
                (String)obj,
                s
            };
        }
        else {
            String[] a = (String[]) obj;
            String[] r = new String[a.length + 1];
            for (int i = 0; i < a.length; ++i)
                r[i] = a[i];
            r[a.length] = s;
            return r;
        }
    }

    // ----------------------------------------------------------------------
    // Replace
    // ----------------------------------------------------------------------

    /**
     * Inside the string 'template', replace the variables '${varname}' with the value
     * presents in 'params'.
     *
     * If 'varname' is not present in 'params', it will not replaces
     *
     * @param template template string
     * @param params parameters
     * @return
     */
    public static String replace(String template, Map<String, Object> params) {
        String t = template;
        int pos = t.indexOf("${");
        while (pos != -1) {
            int end = t.indexOf("}", pos);
            if (end == -1) {
                logger.errorf(String.format("Missing a '}' in '%s'", template));
                break;
            }

            String pname = t.substring(pos+2, end);
            if (params.containsKey(pname)) {
                Object v = params.get(pname);
                String value = v != null ? v.toString() : "<null>";

                t = t.substring(0, pos) + value + t.substring(end+1);
            }
            else {
                pos = end;
                logger.errorf(String.format("Parameter ${%s} not resolved in '%s'", pname, template));
            }

            pos = t.indexOf("${", pos);
        }

        return t;
    }

    // ----------------------------------------------------------------------
    // Parametric format
    // ----------------------------------------------------------------------
    // Replace "${name}" with the value in the dictionary

    public static String format(String template, Map<String, Object> params) {
        return replace(template, params);
    }

    // ----------------------------------------------------------------------
    // Extract parts
    // ----------------------------------------------------------------------
    // s ::= a.b.c:
    //      firstOf(s)  -> a
    //      restOf(s)   -> b.c
    //      prefixOf(s) -> a.b
    //      lastOf(s)   -> c

    public static String firstOf(String s, String sep) {
        int pos = s.indexOf(sep);
        if (pos != -1)
            return s.substring(0, pos);
        else
            return empty();
    }

    public static String restOf(String s, String sep) {
        int pos = s.indexOf(sep);
        if (pos != -1)
            return s.substring(pos+1);
        else
            return s;
    }

    public static String prefixOf(String s, String sep) {
        int pos = s.lastIndexOf(sep);
        return s.substring(0, pos);
    }

    public static String lastOf(String s, String sep) {
        int pos = s.lastIndexOf(sep);
        if (pos != -1)
            return s.substring(pos+sep.length());
        else
            return s;
    }

    // ----------------------------------------------------------------------
    // Misc
    // ----------------------------------------------------------------------

    /**
     * Check is a character in 't' is present in 's'
     */
    public static boolean containsOneOf(String s, String t) {
        for (int i=0; i<t.length(); ++i)
            if (s.indexOf(t.charAt(i)) != -1)
                return true;
        return false;
    }

    /**
     * Safe substring
     */
    public static String substring(String s, int start, int end) {
        if (start < 0)
            start = s.length() + start;
        if (end < 0)
            end = s.length() + end;
        if (start > end) {
            int t = start;
            start = end;
            end = t;
        }
        return s.substring(start, end);
    }

    /**
     * Count how many times 't' is present in 's'
     */
    public static int count(String s, String t) {
        int n = 0;
        int pos = s.indexOf(t);
        while (pos != -1) {
            n++;
            pos = s.indexOf(t, pos+t.length());
        }
        return n;
    }

    /**
     * Replace '\n' with ' ' and multiple spaces with a single space
     * Remove spaces from head and tail
     */
    public static String trim(String s) {
        s = s.replace('\n', ' ');
        while(s.contains("  "))
            s = s.replace("  ", " ");
        return s.trim();
    }

    /**
     * Split the string on '\n', trime each line and re-compose the string
     */
    public static String trimnl(String s) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(s.split("\n"))
                .map(String::trim)
                .forEach(part -> {
                    if (sb.length() > 0)
                        sb.append("\n");
                    sb.append(part);
                });
        return sb.toString().trim();
    }

    /**
     * String digest
     */
    public static String digest(String s) {
        return Integer.toHexString(s.hashCode());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
