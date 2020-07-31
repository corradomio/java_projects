package jext.io.file;

import jext.util.FileUtils;
import jext.util.Wildcard;

import java.io.File;

public class FilePattern {

    public final boolean recursive;
    private Wildcard wcard;

    public FilePattern(String pattern) {
        this.recursive = pattern.contains("/") || pattern.contains("**");
        this.wcard = new Wildcard(pattern);
    }

    public boolean accept(File baseDir, File file) {
        String name;
        if (recursive)
            name = FileUtils.relativePath(baseDir, file);
        else
            name = file.getName();
        return wcard.accept(name);
    }

    public boolean accept(String text) {
        String name = text.replace("\\", "/");
        if (recursive) {
            int sep = name.lastIndexOf('/');
            name = name.substring(sep+1);
        }
        return wcard.accept(name);
    }
}
