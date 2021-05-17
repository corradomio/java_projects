package jext.sourcecode.project;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class SourceInfo {

    private static final String VERSION_NUMBER = "1.1";

    public long timestamp;
    public String version;

    public long count;
    public long bytes;
    public long totalLines;
    public long blankLines;
    public long codeLines;

    public Map<String, Map<String, String>> modules;

    public SourceInfo() {

    }

    public void init() {
        this.modules = new TreeMap<>();
        this.timestamp = System.currentTimeMillis();
        this.version = VERSION_NUMBER;
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
        if (!modules.containsKey(moduleName))
            modules.put(moduleName, new TreeMap<>());
        Map<String, String> sdmap = modules.get(moduleName);
        sdmap.put(sourceName, digest);
    }

    public boolean hasValidVersion() {
        return VERSION_NUMBER.equals(version);
    }

    public void save(File jsonFile) throws IOException {
        JSONUtils.save(jsonFile, this);
    }

    public static SourceInfo load(File jsonFile) throws IOException {
        return JSONUtils.load(jsonFile, SourceInfo.class);
    }
}
