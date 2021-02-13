package jext.sourcecode.project.util;

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
}
