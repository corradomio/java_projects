package jext.snapshots.local;

import jext.snapshots.Snapshot;

import java.io.File;

public class LocalSnapshot implements Snapshot {

    private File directory;

    LocalSnapshot(File directory) {
        this.directory = directory;
    }

    @Override
    public String getName() {
        return directory.getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}
