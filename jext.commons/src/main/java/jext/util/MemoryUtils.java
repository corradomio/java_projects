package jext.util;

public class MemoryUtils {

    private static final long B = 1;
    private static final long KB = 1024*B;
    private static final long MB = 1024*KB;
    private static final long GB = 1024*MB;

    public static long getAllocatedMemory() {
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        return total - free;
    }

    // public static float getAllocatedMemory(float unit) {
    //     return getAllocatedMemory()/unit;
    // }

    public static String format(float size) {
        if (size == 0)
            return "0";
        if (size < KB)
            return String.format("%d B", (int)size);
        if (size < MB)
            return String.format("%.03f KB", size/KB);
        if (size < GB)
            return String.format("%.03f MB", size/MB);
        else
            return String.format("%.03f GB", size/GB);
    }
}
