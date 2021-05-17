package org.hls.check;

import jext.snapshots.Configuration;
import jext.snapshots.SnapshotsSystem;
import jext.snapshots.SnapshotsSystems;

import java.io.File;
import java.util.Properties;

public class CheckSnapshots {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.localDirectory = new File("local");
        config.snapshotsDirectory = new File("local/.spl/snapshots");
        config.properties = new Properties();
        config.properties.setProperty("exclude", "local,.spl");

        SnapshotsSystem ss = SnapshotsSystems.newInstance(config);

        ss.save();

        ss.listSnapshots()
            .forEach(snapshot -> {
                System.out.println(snapshot);
            });

    }
}
