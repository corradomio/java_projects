package jext.util;

import jext.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;


public class StringUtils {

    private static Logger logger = Logger.getLogger(StringUtils.class) ;

    private static final String EMPTY_STRING = "";
    public static String empty() {
        return EMPTY_STRING;
    }

    private static final String[] EMPTY_ARRAY = new String[0];
    public static String[] emptyArray() { return EMPTY_ARRAY; }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
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

    public static String format(String template, Map<String, Object> params) {
        return replace(template, params);
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

    public static String trim(String s) {
        s = s.replace('\n', ' ');
        while(s.contains("  "))
            s = s.replace("  ", " ");
        return s.trim();
    }

    public static String digest(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] data = s.getBytes();

            md.update(data, 0, data.length);

            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        }
        catch (Exception e) {
            logger.error(e, e);
            return "0";
        }
    }

    private static Pattern LOWERCASE = Pattern.compile(
        "[a-z0-9.$]+"
    );

    public static boolean isLowerCase(String s) {
        return LOWERCASE.matcher(s).matches();
    }
}
