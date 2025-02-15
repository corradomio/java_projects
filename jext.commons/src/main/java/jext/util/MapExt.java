package jext.util;

import java.util.Map;

import static jext.util.MapUtils.get;

public class MapExt {

    // ----------------------------------------------------------------------

    public static boolean[] getBooleanArray(Map<String, Object> map, String... keys) {
        return ArrayExt.asBooleanArray(get(map, keys));
    }

    public static int[] getIntArray(Map<String, Object> map, String... keys) {
        return ArrayExt.asIntArray(get(map, keys));
    }

    public static double[] getDoubleArray(Map<String, Object> map, String... keys) {
        return ArrayExt.asDoubleArray(get(map, keys));
    }

    public static String[] getStringArray(Map<String, Object> map, String... keys) {
        return ArrayExt.asStringArray(get(map, keys));
    }

    // ----------------------------------------------------------------------

    public static double getOrDefault(Map<String, Object> map, String key, int index, double defaultValue){
        double[] dArray = getDoubleArray(map, key);

        if(dArray == null || dArray.length<=index)
            return defaultValue;

        return dArray[index];
    }

    public static boolean getOrDefault(Map<String, Object> map, String key, int index, boolean defaultValue){
        boolean[] dArray = getBooleanArray(map, key);

        if(dArray == null || dArray.length<=index)
            return defaultValue;

        return dArray[index];
    }

}
