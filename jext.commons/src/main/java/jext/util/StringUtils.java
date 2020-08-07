package jext.util;

import jext.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class StringUtils {

    private static final String EMPTY_STRING = "";
    private static final String[] EMPTY_ARRAY = new String[0];
    private static Logger logger = Logger.getLogger(StringUtils.class) ;


    public static String empty() {
        return EMPTY_STRING;
    }

    public static String[] emptyArray() { return EMPTY_ARRAY; }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String compose(String[] v, String sep) {
        if (v == null || v.length == 0)
            return EMPTY_STRING;

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
     * If 'varname' is not present in 'params', it will not replaced
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
        return Integer.toHexString(s.hashCode());
        // try {
        //     MessageDigest md = MessageDigest.getInstance("MD5");
        //     byte[] data = s.getBytes();
        //
        //     md.update(data, 0, data.length);
        //
        //     byte[] digest = md.digest();
        //     int k = digest.length/2;
        //
        //     byte[] smalld = new byte[k];
        //     for (int i=0; i< smalld.length; ++i)
        //         smalld[i] = (byte)(digest[i] ^ digest[k+i]);// ^ digest[2*k+i] ^ digest[3*k+i]);
        //
        //     return DatatypeConverter.printHexBinary(smalld);
        // }
        // catch (Exception e) {
        //     return "0";
        // }
    }

    public static long digest64(String s){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            for(int i=0; i<s.length(); ++i) {
                char ch = s.charAt(i);
                md.update((byte)((ch     ) & 0xFF));
                md.update((byte)((ch >> 8) & 0xFF));
            }

            byte[] digest = md.digest();
            long[] smalld = new long[4];
            for (int i=0; i< smalld.length; ++i)
                smalld[i] = (digest[i] ^ digest[4+i]);

            return (smalld[0] <<  8) |
                         (smalld[1] << 16) |
                         (smalld[2] << 32) |
                         (smalld[3] << 48);
        }
        catch (Exception e) {
            return 0L;
        }
    }

    private static Pattern LOWERCASE = Pattern.compile(
        "[a-z0-9.$]+"
    );

    public static boolean isLowerCase(String s) {
        return LOWERCASE.matcher(s).matches();
    }

    public static List<String> asList(String[] a) {
        if (a == null)
            return java.util.Collections.emptyList();
        else
            return Arrays.asList(a);
    }
}
