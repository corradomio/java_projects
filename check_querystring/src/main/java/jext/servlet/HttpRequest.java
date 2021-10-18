package jext.servlet;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    public static HttpRequest of(HttpServletRequest request) {
        HttpRequest hreq = new HttpRequest(request.getRequestURI(), request.getQueryString());
        hreq.parseQueryString();
        return hreq;
    }

    public static HttpRequest of(String requestUrl, String queryString) {
        HttpRequest hreq = new HttpRequest(requestUrl, queryString);
        hreq.parseQueryString();
        return hreq;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String requestUrl;
    private String queryString;

    private Map<String, String[]> qparams = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private HttpRequest(String requestUrl, String queryString) {
        this.requestUrl = requestUrl;
        this.queryString = queryString;
        if (this.queryString == null)
            this.queryString = "";
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getRequestUrl() {
        return requestUrl;
    }

    public String[] get(String key) {
        return qparams.get(key);
    }

    // -- String

    public String getString(String key) {
        return getString(key, "");
    }
    public String getString(String key, String defaultValue) {
        if (!qparams.containsKey(key))
            return defaultValue;
        else
            return qparams.get(key)[0];
    }

    // -- Integer

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        if (!qparams.containsKey(key))
            return defaultValue;
        else
            return parseInt(qparams.get(key)[0]);
    }

    public int[] getIntArray(String key) {
        if (!qparams.containsKey(key))
            return new int[0];
        String[] values = qparams.get(key);
        int[] iarray = new int[values.length];
        for (int i=0; i<values.length; ++i) {
            iarray[i] = parseInt(values[i]);
        }
        return iarray;
    }

    public int[] getIntArray(String key, int defaultValue) {
        if (!qparams.containsKey(key))
            return new int[]{defaultValue};
        String[] values = qparams.get(key);
        int[] iarray = new int[values.length];
        for (int i=0; i<values.length; ++i) {
            iarray[i] = parseInt(values[i]);
        }
        return iarray;
    }

    private static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    // -- Boolean

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (!qparams.containsKey(key))
            return defaultValue;
        else
            return parseBoolean(qparams.get(key)[0]);
    }

    private static boolean parseBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------
    // <([{\^-=$!|]})?*+.>
    //
    //      query ::= ? key=value [& ...]*
    //                       [; ...]*
    //
    //      value ::= single value or multiple values separated by comma
    //
    // Note:
    //
    //      'key=val1&key=val2'   must be equivalent to  'key=val1,val2'
    //      'key'  or  'key='     must be equivalent to  'key=true'
    //

    private void parseQueryString() {
        // this.qparams.putAll(javax.servlet.http.HttpUtils.parseQueryString( queryString ));

        String[] params = queryString.split("[?&;]");
        String key, value;
        String[] varray;

        for (String param : params) {
            param = param.trim();
            if (param.isEmpty()) continue;

            int pos = param.indexOf('=');
            if (pos == -1) {
                pos = param.length();
                param = param + "=";
            }

            key = decode(param.substring(0, pos));
            value = decode(param.substring(pos + 1));

            // check v1,v2,...
            if (value.contains(","))
                varray = value.split(",");
            else
                varray = new String[]{value};

            qparams.put(key, concat(varray, qparams.get(key)));
        }
    }

    private static String[] concat(String[] s1, String[] s2) {
        if (s1 == null || s1.length == 0) return s2;
        if (s2 == null || s2.length == 0) return s1;

        int k=0;
        String[] s12 = new String[s1.length+s2.length];
        for (int i=0; i<s1.length; i++,k++)
            s12[k] = s1[i];
        for (int i=0; i<s2.length; i++,k++)
            s12[k] = s2[i];
        return s12;
    }

    private static String decode(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
