package jext.snapshots;

import java.util.List;

public interface SnapshotsSystem {

    /**
     * Save the local snapshot in a new subdirectory inside 'snapshotsDirectory'
     * @return
     */
    Snapshot save();

    /**
     * List of saved snapshots
     */
    List<Snapshot> listSnapshots();

    /**
     * Retrieve the specified snapshot
     */
    Snapshot getSnapshot(String snapshotName);

    /**
     *
     * @param snapshotName
     */
    void rollback(String snapshotName);
    void rollback(Snapshot snapshot);

}
