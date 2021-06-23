package jext.net;

import java.util.HashMap;
import java.util.Map;

public class QueryString extends HashMap<String, String> {

    private static final QueryString EMPTY = new QueryString(){
        @Override
        public String put(String key, String value) {
            return super.put(key, value);
        }

        @Override
        public void putAll(Map<? extends String, ? extends String> m) {
            super.putAll(m);
        }

        @Override
        public String remove(Object key) {
            return super.remove(key);
        }

        @Override
        public void clear() {
            super.clear();
        }
    };
    private static final String NONE = "";

    public static QueryString of(String queryString) {
        if (queryString == null || queryString.isEmpty())
            return EMPTY;

        QueryString that = new QueryString();

        // query ::= ?key=value [&key=value]*
        //                      [;key=value]*
        // <([{\^-=$!|]})?*+.>
        String name, value;
        String[] params = queryString.split("[?&;]");
        for(String param : params) {
            int pos = param.indexOf('=');
            if (pos != -1) {
                name = param.substring(0, pos);
                value = param.substring(pos+1);
            }
            else {
                name = param;
                value = NONE;
            }

            if (name.length() > 0)
                that.put(name, value);
        }

        return that;
    }

    public boolean isArray(String key) {
        if (!super.containsKey(key))
            return false;
        else
            return super.get(key).contains(",");
    }

    public int getInt(String key, int defaultValue) {
        if (!super.containsKey(key))
            return defaultValue;
        else
            return Integer.parseInt(super.get(key));
    }

    public int[] getIntArray(String key) {
        if (!containsKey(key))
            return new int[0];
        String value = get(key);
        String[] parts = value.split(",");

        int[] ints = new int[parts.length];
        for (int i=0; i<parts.length; ++i)
            ints[i] = Integer.parseInt(parts[i]);
        return ints;
    }
}
