package jext.snapshots;

import jext.util.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

    public File localDirectory;
    public File snapshotsDirectory;
    public Properties properties;
    public Map<String, File> serviceFiles;

    public void addServiceFile(File serviceFile) {
        if (serviceFiles == null)
            serviceFiles = new HashMap<>();
        String servicePath = FileUtils.relativePath(localDirectory, serviceFile);
        serviceFiles.put(servicePath, serviceFile);
    }

}
