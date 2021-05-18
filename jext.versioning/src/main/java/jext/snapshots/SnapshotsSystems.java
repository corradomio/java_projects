package jext.snapshots;

import jext.snapshots.local.LocalSnapshotsSystem;

public abstract class SnapshotsSystems {

    public static SnapshotsSystem newInstance(SnapshotsConfiguration configuration) {
        SnapshotsSystem ss = new LocalSnapshotsSystem(configuration);
        return ss;
    }

}
