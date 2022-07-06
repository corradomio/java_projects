package jext.sourcecode.project.util;

import java.util.Map;
import java.util.TreeMap;

public class ModulesInfo extends SourceInfo {

    private static final String VERSION_NUMBER = "1.1";

    public String version;
    public Map<String, Map<String, String>> modules;


    public void init() {
        this.modules = new TreeMap<>();
        this.version = VERSION_NUMBER;
    }

    public synchronized void addDigest(String modulePath, String sourcePath, String digest) {
        if (!modules.containsKey(modulePath)) {
            modules.put(modulePath, new TreeMap<>());
        }
        Map<String, String> sdmap = modules.get(modulePath);
        sdmap.put(sourcePath, digest);
    }

}
