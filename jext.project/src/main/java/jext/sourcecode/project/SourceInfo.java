package jext.sourcecode.project.util;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;

public class SourceInfo {
    public long count;
    public long bytes;
    public long totalLines;
    public long blankLines;
    public long codeLines;

    public void add(SourceInfo info) {
        count += info.count;
        bytes += info.bytes;
        totalLines += info.totalLines;
        blankLines += info.blankLines;
        codeLines += info.codeLines;
    }

    public void save(File sinfoFile) {
        try {
            JSONUtils.save(sinfoFile, this);
        } catch (IOException e) {
            //logger.error(e, e);
        }
    }
}
