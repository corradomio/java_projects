package org.hls.check;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jext.sourcecode.project.python.util.PythonSourceRoots;
import jext.util.DefaultHashMap;
//
// class FilesCount {
//
//     static class Count {
//         int count;
//
//         void incr() {
//             count += 1;
//         }
//     }
//
//     Map<String/*ext*/, Count> counts = new DefaultHashMap<>(k -> new Count());
//
//     private FilesCount parent;
//     private int count;
//     private File directory;
//     private Map<String, FilesCount> children = new HashMap<>();
//
//     FilesCount(File directory) {
//         this(null, directory);
//     }
//
//     FilesCount(FilesCount parent, File directory) {
//         this.parent = parent;
//         if (parent != null)
//             parent.children.put(directory.getName(), this);
//         this.directory = directory;
//     }
//
//     public void count() {
//         if (!directory.exists())
//             return;
//
//         File[] files = directory.listFiles(File::isFile);
//         if (files != null)
//         for (File file : files) {
//             if (file.getName().startsWith("."))
//                 continue;
//             String ext = extOf(file);
//             counts.get(ext).incr();
//             count++;
//         }
//
//         File[] sdirs = directory.listFiles(File::isDirectory);
//         if (sdirs != null)
//         for(File sdir : sdirs) {
//             if (sdir.getName().startsWith("."))
//                 continue;
//             FilesCount child = new FilesCount(this, sdir);
//             child.count();
//         }
//     }
//
//     private static String extOf(File file) {
//         String name = file.getName();
//         if (name.startsWith("."))
//             return name;
//         int pos = name.lastIndexOf(".");
//         return pos == -1 ? "" : name.substring(pos);
//     }
// }

public class CheckSourceRoots {

    public static void main(String[] args) {
        File dir = new File("D:/Projects.github/python_projects/flask-2.1.2");

        // FilesCount fc = new FilesCount(dir);
        // fc.count();

        PythonSourceRoots psr = new PythonSourceRoots();
        psr.scan(dir);
        psr.dump();

        System.out.println("Done");
    }
}
