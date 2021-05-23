package jext.sourcecode.project;

import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class SourceInfo {

    private static final String VERSION_NUMBER = "1.1";

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
        this.version = VERSION_NUMBER;
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

    public synchronized void addDigest(String modulePath, String sourcePath, String digest) {
        if (!modules.containsKey(modulePath)) {
            modules.put(modulePath, new TreeMap<>());
        }
        Map<String, String> sdmap = modules.get(modulePath);
        sdmap.put(sourcePath, digest);
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
