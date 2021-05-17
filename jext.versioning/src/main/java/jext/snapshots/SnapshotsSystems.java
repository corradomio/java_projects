package jext.snapshots;

import jext.logging.Logger;
import jext.snapshots.local.LocalSnapshotsSystem;

import java.io.File;
import java.util.Properties;

public abstract class SnapshotsSystems {

    private static Logger logger = Logger.getLogger(SnapshotsSystems.class);


    public static SnapshotsSystem newInstance(File localDirectory, File snapshotsDirectory, Properties properties) {
        SnapshotsSystem ss = new LocalSnapshotsSystem()
            .setLocalDirectory(localDirectory)
            .setSnapshotsDirectory(snapshotsDirectory)
            .setProperties(properties);
        return ss;
    }

}
