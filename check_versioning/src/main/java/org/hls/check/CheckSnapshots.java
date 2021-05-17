package org.hls.check;

import jext.snapshots.SnapshotsSystem;
import jext.snapshots.SnapshotsSystems;

import java.io.File;
import java.util.Properties;

public class CheckSnapshots {

    public static void main(String[] args) {
        Properties properties = new Properties();
        SnapshotsSystem ss = SnapshotsSystems.newInstance(new File("local"), new File("local/.spl/snapshots"), properties);

        ss.listSnapshots()
            .forEach(snapshot -> {
                System.out.println(snapshot);
            });

    }
}
