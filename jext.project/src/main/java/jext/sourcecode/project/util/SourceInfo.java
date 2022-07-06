package jext.sourcecode.project.util;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;

public class SourceInfo {

    protected static final String VERSION_NUMBER = "1.1";
    public String version;

    public long modules;
    public long count;
    public long bytes;
    public long totalLines;
    public long blankLines;
    public long codeLines;

    public SourceInfo() {
        version = VERSION_NUMBER;
    }

    // executed in parallel!
    public synchronized void add(SourceInfo info) {
        count += info.count;
        bytes += info.bytes;
        totalLines += info.totalLines;
        blankLines += info.blankLines;
        codeLines += info.codeLines;
    }

    public void save(File jsonFile) {
        JSONUtils.save(jsonFile, this);
    }

    public static SourceInfo load(File jsonFile) throws IOException {
        return JSONUtils.load(jsonFile, SourceInfo.class);
    }

}
