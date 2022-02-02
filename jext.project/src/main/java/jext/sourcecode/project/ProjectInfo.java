package jext.sourcecode.project;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ProjectInfo extends LinkedHashMap<String, Object> {

    private static final String VERSION = "version";
    private static final String VERSION_NUMBER = "2.0";

    public ProjectInfo() {

    }

    public void init() {
        put(VERSION, VERSION_NUMBER);
    }

    public boolean hasValidVersion() {
        return VERSION_NUMBER.equals(get(VERSION));
    }

    public void save (File jsonFile) throws IOException {
        JSONUtils.save(jsonFile, this);
    }

    public static ProjectInfo load(File jsonFile) throws IOException {
        return JSONUtils.load(jsonFile, ProjectInfo.class);
    }
}
