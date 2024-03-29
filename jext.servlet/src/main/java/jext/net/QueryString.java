package jext.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QueryString extends HashMap<String, String[]> {
    
    public static QueryString of(String queryString) {
        QueryString qs = new QueryString(queryString);
        qs.parseQueryString();
        return qs;
    }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private final String queryString;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private QueryString(String queryString) {
        this.queryString = queryString;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // -- String

    public String getString(String key) {
        return getString(key, "");
    }
    public String getString(String key, String defaultValue) {
        if (!this.containsKey(key))
            return defaultValue;
        else
            return this.get(key)[0];
    }

    public String[] getStringArray(String key) {
        if (!this.containsKey(key))
            return new String[]{};
        String[] values = this.get(key);
        List<String> list = new ArrayList<>();
        for (String value : values) {
            String[] items = value.split(",");
            for (String item : items) {
                item = item.trim();
                if (!item.isEmpty())
                    list.add(item);
            }
        }
        values = list.toArray(values);
        return values;
    }

    // -- Integer

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        if (!this.containsKey(key))
            return defaultValue;
        else
            return parseInt(this.get(key)[0]);
    }

    public double getDouble(String key, double defaultValue) {
        if (!this.containsKey(key))
            return defaultValue;
        else
            return parseDouble(this.get(key)[0]);
    }

    public int[] getIntArray(String key) {
        if (!this.containsKey(key))
            return new int[0];
        String[] values = this.get(key);
        int[] iarray = new int[values.length];
        for (int i=0; i<values.length; ++i) {
            iarray[i] = parseInt(values[i]);
        }
        return iarray;
    }

    public int[] getIntArray(String key, int defaultValue) {
        if (!this.containsKey(key))
            return new int[]{defaultValue};
        String[] values = this.get(key);
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

    private static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // -- Boolean

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (!this.containsKey(key))
            return defaultValue;
        else
            return parseBoolean(this.get(key)[0]);
    }

    private static boolean parseBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    private void parseQueryString() {
        // the method is 'obsolete' and doesn't support:  p=1,2
        // this.this.putAll(javax.servlet.http.HttpUtils.parseQueryString( queryString ));

        if (queryString == null)
            return;

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

            this.put(key, concat(varray, this.get(key)));
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
}
