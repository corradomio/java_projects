package jext.snapshots.local;

import jext.snapshots.SnapshotsSystem;
import jext.snapshots.SourcesSnapshot;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class LocalSnapshotsSystem implements SnapshotsSystem {

    private Properties properties = new Properties();
    private File localDirectory;
    private File snapshotsDirectory;

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
    public SourcesSnapshot save() {
        return null;
    }

    @Override
    public List<SourcesSnapshot> listSnapshots() {
        return null;
    }

    @Override
    public SourcesSnapshot getSnapshot(String snapshotName) {
        return null;
    }

    @Override
    public void rollback(String snapshotName) {

    }

    @Override
    public void rollback(SourcesSnapshot snapshot) {

    }
}
