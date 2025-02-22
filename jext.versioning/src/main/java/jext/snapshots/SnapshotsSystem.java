package jext.snapshots;

import java.util.List;
import java.util.Optional;

public interface SnapshotsSystem {

    /**
     * Delete all snapshots
     */
    void delete();

    /**
     * Save the local snapshot in a new subdirectory inside 'snapshotsDirectory'
     * @return the saved snapshot
     */
    Snapshot save();

    /**
     * id- Ordered list of saved snapshots
     */
    List<Snapshot> listSnapshots();

    /**
     * Retrieve the specified snapshot.
     * Special names:
     *
     *      - Snapshot.FIRST        first snapshot (it may not exist)
     *      - Snapshot.LAST         last snapshot  (it may not exist)
     *      - Snapshot.PREVIOUS     previous to last snapshot  (it may not exist)
     *      - Snapshot.CURRENT      current snapshot
     */
    Optional<Snapshot> getSnapshot(String snapshotName);
    Optional<Snapshot> getSnapshot(int snapshotId);

    /** Retrieve the CURRENT snapshot */
    Snapshot getSnapshot();

    /**
     * Restore the "current" snapshot with the specified snapshot,
     * and delete the specified and all subsequent snapshots.
     * Special names:
     *
     *      - "first"    first snapshot (it may not exist)
     *      - "last":    last snapshot  (it may not exist)
     *      - "current": current snapshot
     *
     * @return the current snapshot
     */
    // Snapshot rollback(String snapshotName);
    // Snapshot rollback(Snapshot snapshot);

}
