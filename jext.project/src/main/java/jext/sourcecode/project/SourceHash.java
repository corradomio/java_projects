package jext.sourcecode.project;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SourceHash extends LinkedHashMap<String, Map<String, String>> {

    // used in parallel
    public synchronized void addHash(String moduleName, String sourceName, String digest) {
        if (!this.containsKey(moduleName))
            this.put(moduleName, new HashMap<>());
        Map<String, String> smap = this.get(moduleName);
        smap.put(sourceName, digest);
    }

    public void save (File jsonFile) throws IOException {
        JSONUtils.save(jsonFile, this);
    }

}
