package jext.io.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilePatterns {

    private List<FilePattern> patterns = new ArrayList<>();

    public boolean isEmpty() {
        return patterns.isEmpty();
    }

    public FilePatterns addAll(Collection<String> patterns) {
        for(String pattern : patterns)
            add(pattern);
        return this;
    }

    public FilePatterns add(String pattern) {
        FilePattern fpat = new FilePattern(pattern);
        patterns.add(fpat);
        return this;
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

    public boolean accept(String stext, String ltext) {
        return (accept(ltext) || accept(stext));
    }

}
