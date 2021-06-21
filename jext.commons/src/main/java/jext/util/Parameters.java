package jext.util;

import jext.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Parameters extends HashMap<String, Object> {

    private static Parameters NO_PARAMS = new Parameters() {
        @Override
        public Parameters put(String name, Object value ) { throw new UnsupportedOperationException();}
    };

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static Parameters empty() { return NO_PARAMS; }

    public static Parameters params() { return new Parameters(); }

    public static Parameters params(Map<String, ?> params) {
        return params().add((Map<String, Object>) params);
    }

    public static Parameters params(String name, Object value, Object... a) {
        Parameters params = params().add(name, value);

        int at = 0;
        while (at < a.length-1) {
            String key = a[at++].toString();
            Object val = a[at++];

            params.put(key, val);
        }
        return params;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Parameters() { }

    // public Parameters(Map m) {
    //     putAll(m);
    // }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public Parameters add(String name, Object value, Object... a) {
        this.put(name, value);

        int at = 0;
        while (at < a.length-1) {
            String key = a[at++].toString();
            Object val = a[at++];

            this.put(key, val);
        }
        return this;
    }

    public Parameters add(Properties props) {
        props.forEach((k, v) -> {
            add(k.toString(), v);
        });
        return this;
    }

    public Parameters add(Map<String, Object> params) {
        if (params != null)
            super.putAll(params);
        return this;
    }

    public Parameters add(String prefix, Map<String, Object> params) {
        if (params == null)
            return this;

        for (String key : params.keySet()) {
            super.put(prefix + key, params.get(key));
        }
        return this;
    }

    // ----------------------------------------------------------------------

    public String[] getStringArray(String name) {
        if (!super.containsKey(name))
            return null;
        Object obj = super.get(name);
        if (obj instanceof String[])
            return (String[])obj;
        String value = obj.toString();
        if (value.contains(","))
            return value.split(",");
        else
            return new String[]{ value };
    }

    public String getString(String name) {
        return super.get(name).toString();
    }

    public String getString(String name, String defval) {
        if (!this.containsKey(name)) return defval;
        return this.get(name).toString();
    }

    public boolean getBoolean(String name, boolean defval) {
        if (!this.containsKey(name)) return defval;
        Object value = this.get(name);
        if (value instanceof Boolean)
            return (Boolean) value;
        if (value instanceof String)
            return Boolean.parseBoolean((String) value);
        else {
            return Boolean.parseBoolean(value.toString());
        }
    }

    public int getInt(String name, int defval) {
        if (!this.containsKey(name)) return defval;
        Object value = this.get(name);
        if (value instanceof Integer)
            return ((Integer) value).intValue();
        if (value instanceof Long)
            return ((Long) value).intValue();
        if (value instanceof String)
            return Integer.parseInt((String) value);
        else {
            return Integer.parseInt(value.toString());
        }
    }

    public long getLong(String name, long defval) {
        if (!this.containsKey(name)) return defval;
        Object value = this.get(name);
        if (value instanceof Integer)
            return ((Integer) value).longValue();
        if (value instanceof Long)
            return ((Long) value).longValue();
        if (value instanceof String)
            return Long.parseLong((String) value);
        else {
            return Integer.parseInt(value.toString());
        }
    }

    public Parameters setValue(String name, Object value) {
        this.put(name, value);
        return this;
    }

    // ----------------------------------------------------------------------

    public Properties toProperties() {
        Properties props = new Properties();
        keySet().forEach(k -> {
            props.put(k, get(k).toString());
        });
        return props;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------
    // ?p1=v1&...

    public Parameters parse(String query) {
        if (StringUtils.isEmpty(query))
            return this;

        String name, value;
        String[] sparams = query.split("[?&]");
        for(String param : sparams) {
            int pos = param.indexOf('=');
            if (pos != -1) {
                name = param.substring(0, pos);
                value = param.substring(pos+1);
            }
            else {
                name = param;
                value = "";
            }

            if (name.length() == 0)
                continue;

            this.put(name, value);
        }

        return this;
    }

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

    public static Parameters load(String paramsPath) {
        return load(new File(paramsPath));
    }

    /**
     * Fill a Properties object with the content of a file
     *
     * @param propsFile file to read
     * @return Properties object
     */
    public static Parameters load(File propsFile) {
        Parameters params = new Parameters();
        Properties props = new Properties();
        try(InputStream in = new FileInputStream(propsFile)) {
            props.load(in);
        }
        catch (IOException e) {
            Logger.getLogger(Parameters.class).error("Unable to read properties file " + propsFile, e);
        }

        props.forEach((k, v) -> {
            params.put(k.toString(), v);
        });

        return params;
    }

}
