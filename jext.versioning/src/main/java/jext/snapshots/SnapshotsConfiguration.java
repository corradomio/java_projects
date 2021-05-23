package jext.snapshots;

import jext.util.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SnapshotsConfiguration {

    /** Directory containing the current source code */
    public File localDirectory;
    /** Directory where the snapshots are saved */
    public File snapshotsDirectory;
    /** Snapshot properties */
    public Properties properties;
    /** Extra service files saved & retrieved */
    public Map<String, File> serviceFiles;
    /** Subdirectory containing the service files */
    public String servicePath = ".spl";

    public SnapshotsConfiguration() {

    }

    public void addServiceFile(File serviceFile) {
        if (serviceFiles == null)
            serviceFiles = new HashMap<>();
        String servicePath = FileUtils.relativePath(localDirectory, serviceFile);
        serviceFiles.put(servicePath, serviceFile);
    }

}
