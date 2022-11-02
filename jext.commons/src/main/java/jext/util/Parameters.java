package jext.util;

import jext.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Parameters extends HashMap<String, Object> {

    private static final Parameters EMPTY = new Parameters() {

        @Override
        public Parameters add(Properties properties) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Parameters add(Map<String, Object> map) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Parameters add(String prefix, Map<String, Object> map) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Parameters addIfMissing(Map<String, Object> map) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Parameters addIfMissing(Properties properties) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Parameters setValue(String name, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object put(String key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map<? extends String, ?> m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object putIfAbsent(String key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean replace(String key, Object oldValue, Object newValue) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object replace(String key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void replaceAll(BiFunction<? super String, ? super Object, ?> function) {
            throw new UnsupportedOperationException();
        }
    };

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    /**
     * Create an empty map.
     * In the origin, it was a red-only object
     * 
     * @return an empty map
     */
    public static Parameters empty() { return  EMPTY; /*params();*/ }   // could be updated

    /**
     * Create an empty map
     * 
     * @return an empty map
     */
    public static Parameters params() { return new Parameters(); }

    // /**
    //  * Create an object populated with the specified map
    //  *
    //  * @param params map used for initialization
    //  * @return the map
    //  */
    // public static Parameters params(Map<String, ?> params) {
    //     return params().add((Map<String, Object>) params);
    // }

    /**
     * Create an object populated with the list of key/value pairs
     *
     * @param name first key
     * @param value first value
     * @param a remaining key/values
     * @return the map
     */
    public static Parameters params(String name, Object value, Object... a) {
        Parameters params = params();
        params.put(name, value);

        int at = 0;
        while (at < a.length-1) {
            String key = a[at++].toString();
            Object val = a[at++];

            params.put(key, val);
        }
        return params;
    }

    /**
     * Select a subset of keys
     * 
     * @param params map
     * @param keys keys to select
     * @return a new map with the selected keys (can be empty)
     */
    public static Parameters select(Map<String, ?> params, String... keys) {
        Parameters nparams = empty();
        for (String key : keys)
            nparams.put(key, params.get(key));
        return nparams;
    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public Parameters() { }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    // /**
    //  * Add one or more key/value pairs
    //  *
    //  * @param name first key
    //  * @param value first value
    //  * @param a remaining key/values
    //  * @return itself
    //  */
    // public Parameters add(String name, Object value, Object... a) {
    //     this.put(name, value);
    //
    //     int at = 0;
    //     while (at < a.length-1) {
    //         String key = a[at++].toString();
    //         Object val = a[at++];
    //
    //         this.put(key, val);
    //     }
    //     return this;
    // }

    /**
     * Add the content of the properties object 
     * 
     * @param properties properties to add
     * @return itself
     */
    public Parameters add(Properties properties) {
        properties.forEach((k, v) -> {
            put(k.toString(), v);
        });
        return this;
    }

    /**
     * Add the content of the map
     * 
     * @param map map to add
     * @return itself
     */
    public Parameters add(Map<String, Object> map) {
        if (map != null)
            super.putAll(map);
        return this;
    }

    /**
     * Add the content of the map changing the keys in the map with
     * the selected prefix
     * 
     * @param prefix string to use as prefix in the map keys
     * @param map map to add
     * @return itself
     */
    public Parameters add(String prefix, Map<String, Object> map) {
        if (map == null)
            return this;

        for (String key : map.keySet()) {
            super.put(prefix + key, map.get(key));
        }
        return this;
    }

    /**
     * Add the elements in the map if not already present
     *
     * @param map map to add
     * @return itself
     */
    public Parameters addIfMissing(Map<String, Object> map) {
        for (String key : map.keySet())
            if (!containsKey(key))
                put(key, map.get(key));
        return this;
    }

    public Parameters addIfMissing(Properties properties) {
        for (String key : properties.stringPropertyNames())
            if (!containsKey(key))
                put(key, properties.get(key));
        return this;
    }

    // ----------------------------------------------------------------------
    // The following method convert each value in the correspondent type.
    // In general it is supported:
    //
    //  0) null -> null
    //  1) any object type -> String
    //  2) any object type -> same type
    //  2) String -> String, int, float, boolean, String[]

    /**
     * Retrieve a string array, encoded as a string sequence separated by ','
     *
     * @param name name of the key
     * @return the string array or null if the key is not present
     */
    public String[] getStrings(String name) {
        if (!super.containsKey(name))
            return null;
        Object obj = super.get(name);
        if (obj instanceof String[])
            return (String[])obj;
        String value = obj.toString();
        if (value.contains(",")) {
            String[] a = value.split(",");
            for (int i=0; i<a.length; ++i)
                a[i] = a[i].trim();
            return a;
        }
        else
            return new String[]{ value };
    }

    /**
     * Retrieve a string
     *
     * @param name name of the key
     * @return the string or null
     */
    public String getString(String name) {
        return getString(name, null);
    }

    /**
     * Retrieve a string
     *
     * @param name name of the key
     * @param defval default value
     * @return the string or the default value
     */
    public String getString(String name, String defval) {
        if (!this.containsKey(name)) return defval;
        return super.get(name).toString();
    }

    /**
     * Retrieve a boolean
     *
     * @param name name of the key
     * @param defval default value
     * @return the boolean value
     */
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

    /**
     * Retrieve an integer
     *
     * @param name name of the key
     * @param defval default value
     * @return the boolean value
     */
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

    /**
     * Retrieve a long
     *
     * @param name name of the key
     * @param defval default value
     * @return the boolean value
     */
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

    /**
     * Convert this object in a Properties object
     * @return a Properties object
     */
    public Properties toProperties() {
        Properties props = new Properties();
        keySet().forEach(k -> {
            props.put(k, get(k).toString());
        });
        return props;
    }

    // ----------------------------------------------------------------------
    // Special operations
    // ----------------------------------------------------------------------
    // ?p1=v1&...

    /**
     * Parse a query string: '?p1=v1&...
     * @param query a query string
     * @return itself
     */
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

    // public static Parameters load(String paramsPath) {
    //     return load(new File(paramsPath));
    // }

    /**
     * Fill a Properties object with the content of the file
     *
     * @param propertiesFile file to read
     * @return Properties object
     */
    public static Parameters load(File propertiesFile) {
        Parameters params = new Parameters();
        Properties props = new Properties();
        try(InputStream in = new FileInputStream(propertiesFile)) {
            props.load(in);
        }
        catch (IOException e) {
            Logger.getLogger(Parameters.class).error("Unable to read properties file " + propertiesFile, e);
        }

        props.forEach((k, v) -> {
            params.put(k.toString(), v);
        });

        return params;
    }

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

}
