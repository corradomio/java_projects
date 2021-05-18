package jext.snapshots.local;

import jext.logging.Logger;
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
    private SnapshotInfo sinfo;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    LocalSnapshot(File directory) {
        this(directory, directory.getName());
    }

    LocalSnapshot(File directory, String name) {
        this.directory = directory;
        this.name = name;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public String getName() {
        return name;
    }

    // ----------------------------------------------------------------------
    // Comparison
    // ----------------------------------------------------------------------

    @Override
    public boolean equalsTo(Snapshot snapshot) {
        check();

        LocalSnapshot that = (LocalSnapshot) snapshot;
        return this.sinfo.equalsTo(that.sinfo);
    }

    @Override
    public SnapshotDifferences compareWith(Snapshot snapshot) {
        check();
        LocalSnapshot that = (LocalSnapshot) snapshot;
        return this.sinfo.compareWith(that.sinfo);
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
        } catch (IOException e) {
            Logger.getLogger(Snapshot.class).error(e, e);
            sinfo = new SnapshotInfo();
        }
    }
}
