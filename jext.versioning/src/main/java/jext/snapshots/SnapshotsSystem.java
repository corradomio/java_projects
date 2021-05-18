package jext.snapshots;

import java.util.List;
import java.util.Optional;

public interface SnapshotsSystem {

    /**
     * Special snapshot names
     */
    String FIRST = "first";
    String LAST = "last";
    String CURRENT = "current";

    /**
     * Save the local snapshot in a new subdirectory inside 'snapshotsDirectory'
     * @return the saved snapshot
     */
    Snapshot save();

    /**
     * Ordered list of saved snapshots
     */
    List<Snapshot> listSnapshots();

    /**
     * Retrieve the specified snapshot.
     * Special names:
     *
     *      - "first"    first snapshot (it may not exist)
     *      - "last":    last snapshot  (it may not exist)
     *      - "current": current snapshot
     */
    Optional<Snapshot> getSnapshot(String snapshotName);

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
    Snapshot rollback(String snapshotName);
    Snapshot rollback(Snapshot snapshot);

}
