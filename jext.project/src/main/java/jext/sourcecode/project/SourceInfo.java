package jext.sourcecode.project;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class SourceInfo {

    public long count;
    public long bytes;
    public long totalLines;
    public long blankLines;
    public long codeLines;

    public Map<String, Map<String, String>> hashes;

    public SourceInfo() {
        //this(false);
    }

    public SourceInfo(boolean root) {
        if (root)
            hashes = new TreeMap<>();
    }

    // executed in parallel!
    public synchronized void add(SourceInfo info) {
        count += info.count;
        bytes += info.bytes;
        totalLines += info.totalLines;
        blankLines += info.blankLines;
        codeLines += info.codeLines;
    }

    public synchronized void addDigest(String moduleName, String sourceName, String digest) {
        if (!hashes.containsKey(moduleName))
            hashes.put(moduleName, new TreeMap<>());
        Map<String, String> sdmap = hashes.get(moduleName);
        sdmap.put(sourceName, digest);
    }

    public void save(File jsonFile) {
        try {
            JSONUtils.save(jsonFile, this);
        } catch (IOException e) {
            //logger.error(e, e);
        }
    }
}
