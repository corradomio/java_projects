package org.hls.check;

import jext.maven.Version;
import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckVersions2 {

    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> data = JSONUtils.parse(new File("records.json"), ArrayList.class);
        for (Map<String, Object> map : data) {
            String version = (String) map.get("n.version");
            System.out.println(Version.of(version));
        }

    }
}
