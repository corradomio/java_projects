package jext.versioning;

import java.util.List;

public interface SnapshotsSystem {



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
