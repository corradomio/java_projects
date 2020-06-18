package jext.buildtools.scan.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePatterns {

    private List<FilePattern> patterns = new ArrayList<>();

    public boolean add(String pattern) {
        FilePattern fpat = new FilePattern(pattern);
        patterns.add(fpat);
        return fpat.recursive;
    }

    public void add(FilePattern pattern) {
        patterns.add(pattern);
    }

    public boolean accept(String text) {
        for(FilePattern pattern : patterns)
            if (pattern.accept(text))
                return true;
        return false;
    }

    public boolean accept(File baseDir, File file) {
        for(FilePattern pattern : patterns)
            if (pattern.accept(baseDir, file))
                return true;
        return false;
    }
}
