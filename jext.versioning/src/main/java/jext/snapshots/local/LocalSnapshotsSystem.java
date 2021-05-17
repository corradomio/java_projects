package jext.snapshots.local;

import jext.io.filters.FileFilters;
import jext.snapshots.Configuration;
import jext.snapshots.SnapshotsSystem;
import jext.snapshots.Snapshot;
import jext.util.FileUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class LocalSnapshotsSystem implements SnapshotsSystem {

    private static final String EXCLUDE = "exclude";

    private Configuration configuration;
    private Properties properties = new Properties();
    private File localDirectory;
    private File snapshotsDirectory;
    private FileFilter excludeFilter = FileFilters.none();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LocalSnapshotsSystem(Configuration configuration) {
        this.configuration = configuration;
        if (configuration.properties != null)
            this.properties.putAll(configuration.properties);
        this.localDirectory = configuration.localDirectory;
        this.snapshotsDirectory = configuration.snapshotsDirectory;

        makeExcludeFilter();
    }

    private void makeExcludeFilter() {
        if (!properties.containsKey(EXCLUDE)) {
            excludeFilter = FileFilters.none();
            return;
        }

        List<String> patterns = StringUtils.split(properties.getProperty(EXCLUDE), ",");
        excludeFilter = FileFilters.wildcards(patterns);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public Snapshot save() {
        File snapshotDirectory = selectDirectory();

        FileUtils.copy(localDirectory, snapshotDirectory, excludeFilter);
        return new LocalSnapshot(snapshotDirectory);
    }

    private File selectDirectory() {
        File snapshotDirectory;
        for (int i=0; true; ++i) {
            snapshotDirectory = new File(snapshotsDirectory, "v" + i);
            if (!snapshotDirectory.exists())
                break;
        }
        return snapshotDirectory;
    }

    @Override
    public List<Snapshot> listSnapshots() {
        return FileUtils.listFiles(snapshotsDirectory)
            .stream()
            .map(LocalSnapshot::new)
            .collect(Collectors.toList());
    }

    @Override
    public Snapshot getSnapshot(String snapshotName) {
        return null;
    }

    @Override
    public void rollback(String snapshotName) {

    }

    @Override
    public void rollback(Snapshot snapshot) {

    }
}
