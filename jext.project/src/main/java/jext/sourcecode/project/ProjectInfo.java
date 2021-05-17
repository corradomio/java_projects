package jext.sourcecode.project;

import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ProjectInfo extends LinkedHashMap<String, Object> {

    public void save (File jsonFile) throws IOException {
        JSONUtils.save(jsonFile, this);
    }
}
