package jext.sourcecode.project.util;

import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SourceInfo {

    public long count;
    public long bytes;
    public long totalLines;
    public long blankLines;
    public long codeLines;

    public SourceInfo() {

    }

    public Map<String, Object> getCounts() {
        return MapUtils.asMap(
            "count", count,
            "bytes", bytes,
            "totalLines", totalLines,
            "blankLines", blankLines,
            "codeLines", codeLines
        );
    }

    // executed in parallel!
    public synchronized void add(SourceInfo info) {
        count += info.count;
        bytes += info.bytes;
        totalLines += info.totalLines;
        blankLines += info.blankLines;
        codeLines += info.codeLines;
    }

    public void save(File jsonFile) throws IOException {
        JSONUtils.save(jsonFile, this);
    }

    public static SourceInfo load(File jsonFile) throws IOException {
        return JSONUtils.load(jsonFile, SourceInfo.class);
    }
}
