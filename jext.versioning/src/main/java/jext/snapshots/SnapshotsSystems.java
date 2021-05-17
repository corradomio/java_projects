package jext.snapshots;

import jext.logging.Logger;
import jext.snapshots.local.LocalSnapshotsSystem;

public abstract class SnapshotsSystems {

    private static Logger logger = Logger.getLogger(SnapshotsSystems.class);

    public static SnapshotsSystem newInstance(SnapshotsConfiguration configuration) {
        SnapshotsSystem ss = new LocalSnapshotsSystem(configuration);
        return ss;
    }

}
