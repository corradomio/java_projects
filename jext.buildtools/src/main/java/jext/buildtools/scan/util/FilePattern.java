package jext.io.fileset;

import jext.util.FileUtils;
import jext.util.Wildcard;

import java.io.File;

public class FilePattern {

    private boolean recursive;
    private Wildcard wcard;

    public FilePattern(String wcard) {
        this.recursive = wcard.contains("/") || wcard.contains("**");
        this.wcard = new Wildcard(wcard);
    }

    public boolean accept(File baseDir, File file) {
        String name;
        if (recursive)
            name = FileUtils.relativePath(baseDir, file);
        else
            name = file.getName();
        return wcard.accept(name);
    }
}
