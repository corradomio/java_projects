package org.hls.check;

import jext.logging.Logger;
import jext.snapshots.Snapshot;
import jext.snapshots.SnapshotsConfiguration;
import jext.snapshots.SnapshotsSystem;
import jext.snapshots.SnapshotsSystems;

import java.io.File;
import java.util.Optional;
import java.util.Properties;

public class CheckSnapshots {

    public static void main(String[] args) {
        Logger.configure();

        File localDirectory = new File("D:\\SPLGroup\\spl-workspaces\\sample-project\\acmeair-monolithic-java-master");
        SnapshotsConfiguration config = new SnapshotsConfiguration();
        config.localDirectory = localDirectory;
        config.snapshotsDirectory = new File(localDirectory, ".spl\\snapshots");
        config.properties = new Properties();
        config.properties.setProperty("exclude", "local,.*");
        config.addServiceFile(new File(localDirectory, ".spl/project-info.json"));
        config.addServiceFile(new File(localDirectory, ".spl/source-info.json"));

        SnapshotsSystem ss = SnapshotsSystems.newInstance(config);

        Snapshot s = ss.getSnapshot();
        System.out.println(s.getId());

        ss.listSnapshots()
            .forEach(snapshot -> {
                System.out.println(snapshot);
            });

    }
}
