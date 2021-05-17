package jext.snapshots.local;

import jext.io.filters.FileFilters;
import jext.snapshots.SnapshotsSystem;
import jext.snapshots.Snapshot;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class LocalSnapshotsSystem implements SnapshotsSystem {

    private Properties properties = new Properties();
    private File localDirectory;
    private File snapshotsDirectory;
    private FileFilter excludeFilter = FileFilters.none();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LocalSnapshotsSystem() {

    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    @Override
    public SnapshotsSystem setProperties(Properties properties) {
        this.properties.putAll(properties);
        return this;
    }

    @Override
    public SnapshotsSystem setLocalDirectory(File localDirectory) {
        this.localDirectory = localDirectory;
        return this;
    }

    @Override
    public SnapshotsSystem setSnapshotsDirectory(File snapshotsDirectory) {
        this.snapshotsDirectory = snapshotsDirectory;
        return this;
    }

    // ----------------------------------------------------------------------

    @Override
    public Snapshot save() {
        File snapshotDirectory = selectDirectory();

        FileUtils.copy(localDirectory, snapshotDirectory, excludeFilter);
        return null;
    }

    private File selectDirectory() {
        int i=0;
        File snapshotDirectory = new File(snapshotsDirectory, "v0");
        while (snapshotDirectory.exists()) {
            i += 1;
            snapshotDirectory = new File(snapshotsDirectory, "v" + i);
        }
        return snapshotDirectory;
    }

    @Override
    public List<Snapshot> listSnapshots() {
        return FileUtils.listFiles(snapshotsDirectory)
            .stream()
            .map(LocalSnapshot::new)
            .collect(Collectors.toList());
    }

    @Override
    public Snapshot getSnapshot(String snapshotName) {
        return null;
    }

    @Override
    public void rollback(String snapshotName) {

    }

    @Override
    public void rollback(Snapshot snapshot) {

    }
}
