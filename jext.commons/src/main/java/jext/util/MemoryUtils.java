package jext.util;

public class MemoryUtils {

    private static final long B = 1;
    private static final long KB = 1024*B;
    private static final long MB = 1024*KB;
    private static final long GB = 1024*MB;

    public static long allocatedMemory() {
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        return total - free;
    }

    public static long maxMemory() {
        Runtime rt = Runtime.getRuntime();
        return rt.maxMemory();
    }

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

    public static long parse(String memsize) {
        memsize = memsize.trim();
        int n = memsize.length();
        if (memsize.endsWith("GB"))
            return (long) (GB*Double.parseDouble(memsize.substring(0,n-2).trim()));
        if (memsize.endsWith("MB"))
            return (long) (MB*Double.parseDouble(memsize.substring(0,n-2).trim()));
        if (memsize.endsWith("KB"))
            return (long) (KB*Double.parseDouble(memsize.substring(0,n-2).trim()));
        if (memsize.endsWith("B"))
            return (long) (B*Double.parseDouble(memsize.substring(0,n-1).trim()));
        else
            return (long) (B*Double.parseDouble(memsize));
    }
}
