package jext.vfs.util;

import jext.vfs.VFile;
import jext.vfs.VFileSystem;

public class VFileUtils {

    public static void dump(VFileSystem fs) {
        dump(fs.getRoot(), 0);
        fs.close();
        println(0, "<<done>>");
    }

    public static void dump(VFile file) {
        dump(file, 0);
    }

    private static void dump(VFile file, int depth) {
        if (file.isFile()) {
            println(depth, "file %s (%s)", file.getName(), file.getFileName().getPath());
        }
        else if (file.isFolder()) {
            println(depth, "<<folder %s>> (%s)", file.getName(), file.getFileName().getPath());
            for(VFile child : file.listFiles())
                dump(child, depth+1);
        }
        else {
            println(depth, "unknown %s", file.getFileName());
        }

    }

    private static void println(int depth, String msg, Object... args) {
        while(depth-- > 0)
            System.out.print("  ");
        System.out.println(String.format(msg, args));
    }

}
