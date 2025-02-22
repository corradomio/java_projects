package jext.snapshots.local;

import jext.util.logging.Logger;
import jext.snapshots.Snapshot;
import jext.snapshots.SnapshotDifferences;
import jext.snapshots.SnapshotInfo;
import jext.util.FileUtils;
import jext.util.JSONUtils;

import java.io.File;
import java.io.IOException;

import static jext.snapshots.local.LocalSnapshotsSystem.SERVICE_FILE;

public class LocalSnapshot implements Snapshot {

    private File directory;
    private String name;
    private int snapshotId;
    private long timestamp;
    private SnapshotInfo sinfo;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    LocalSnapshot(File directory) {
        this.directory = directory;
        this.name = directory.getName();
        this.snapshotId = Integer.parseInt(this.name);
    }

    LocalSnapshot(File directory, String name, int snapshotId) {
        this.directory = directory;
        this.name = name;
        this.snapshotId = snapshotId;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRevision() {
        return snapshotId;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public SnapshotInfo getInfo() {
        check();
        return sinfo;
    }

    // ----------------------------------------------------------------------
    // Comparison
    // ----------------------------------------------------------------------

    @Override
    public boolean equalsTo(Snapshot snapshot) {
        LocalSnapshot that = (LocalSnapshot) snapshot;
        return this.getInfo().equalsTo(that.getInfo());
    }

    @Override
    public SnapshotDifferences compareWith(Snapshot snapshot) {
        check();
        LocalSnapshot that = (LocalSnapshot) snapshot;
        return this.getInfo().compareWith(that.getInfo());
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void delete() {
        // delete recursively everything
        FileUtils.delete(directory);
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Snapshot[%s]", getName());
    }

    private void check() {
        if (sinfo != null)
            return;

        try {
            File serviceFile = new File(directory, SERVICE_FILE);
            sinfo = JSONUtils.load(serviceFile, SnapshotInfo.class);
            timestamp = serviceFile.lastModified();
        } catch (IOException e) {
            Logger.getLogger(Snapshot.class).error(e, e);
            sinfo = new SnapshotInfo();
        }
    }
}
