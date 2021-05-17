package jext.sourcecode.project;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;

public class SourceInfo {
    public long count;
    public long bytes;
    public long totalLines;
    public long blankLines;
    public long codeLines;

    // executed in parallel!
    public synchronized void add(SourceInfo info) {
        count += info.count;
        bytes += info.bytes;
        totalLines += info.totalLines;
        blankLines += info.blankLines;
        codeLines += info.codeLines;
    }

    public void save(File jsonFile) {
        try {
            JSONUtils.save(jsonFile, this);
        } catch (IOException e) {
            //logger.error(e, e);
        }
    }
}
