package jext.snapshots;

public interface Snapshot {

    /**
     * Special snapshot names
     */
    String FIRST = "$first";
    String PREVIOUS = "$previous";
    String LAST = "$last";
    String CURRENT = "$current";

    enum Status {
        ADDED,
        REMOVED,
        CHANGED
    }

    String getName();

    int getId();

    long getTimestamp();

    /**
     * Delete the current snapshot
     */
    void delete();

    /**
     * Check if this snapshot is equals to 'that'
     */
    boolean equalsTo(Snapshot that);

    /**
     * Retrieve the differences between two snapshots:
     *
     *    - modules ADDED, REMOVED, CHANGED
     *
     * for each module
     *
     *    - files ADDED, REMOVED, CHANGED
     *
     * The status follows the following role:
     *
     *      the objects in THIS snapshot respect the second snapshot.
     *
     * That is:
     *
     *    - an object is ADDED if it is present in THIS snapshot but not in THAT
     *    - an object id REMOVED if it is NOT present in THIS snapshot but it IS present in THAT
     *
     * @return the comparison
     */
    SnapshotDifferences compareWith(Snapshot that);
}
