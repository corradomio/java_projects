package jext.snapshots;

import java.io.File;
import java.util.List;
import java.util.Properties;

public interface SnapshotsSystem {

    SnapshotsSystem setProperties(Properties properties);
    SnapshotsSystem setLocalDirectory(File localDirectory);
    SnapshotsSystem setSnapshotsDirectory(File snapshotsDirectory);

    /**
     * Save the local snapshot in a new subdirectory inside 'snapshotsDirectory'
     * @return
     */
    SourcesSnapshot save();

    /**
     * List of saved snapshots
     */
    List<SourcesSnapshot> listSnapshots();

    /**
     * Retrieve the specified snapshot
     */
    SourcesSnapshot getSnapshot(String snapshotName);

    /**
     *
     * @param snapshotName
     */
    void rollback(String snapshotName);
    void rollback(SourcesSnapshot snapshot);

}
