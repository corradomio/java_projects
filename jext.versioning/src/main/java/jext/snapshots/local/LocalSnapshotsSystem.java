package jext.snapshots.local;

import jext.io.filters.FileFilters;
import jext.snapshots.Snapshot;
import jext.snapshots.SnapshotsConfiguration;
import jext.snapshots.SnapshotsSystem;
import jext.util.FileUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class LocalSnapshotsSystem implements SnapshotsSystem {

    private static final String DEFAULT_EXCLUDES = ".*";
    private static final String EXCLUDE = "exclude";
    static final String SERVICE_FILE = ".spl/source-info.json";

    private SnapshotsConfiguration configuration;
    private Properties properties = new Properties();
    private File localDirectory;
    private File snapshotsDirectory;
    private FileFilter excludeFilter = FileFilters.none();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public LocalSnapshotsSystem(SnapshotsConfiguration configuration) {
        this.configuration = configuration;
        if (configuration.properties != null)
            this.properties.putAll(configuration.properties);
        this.localDirectory = configuration.localDirectory;
        this.snapshotsDirectory = configuration.snapshotsDirectory;

        makeExcludeFilter();
    }

    private void makeExcludeFilter() {
        excludeFilter = FileFilters.wildcards(DEFAULT_EXCLUDES);
        if (!properties.containsKey(EXCLUDE))
            return;

        List<String> patterns = StringUtils.split(properties.getProperty(EXCLUDE), ",");
        excludeFilter = FileFilters.or(
            FileFilters.wildcards(patterns),
            excludeFilter
        );
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public void delete() {
        FileUtils.delete(snapshotsDirectory);
    }

    @Override
    public Snapshot save() {
        FileUtils.mkdirs(snapshotsDirectory);
        File snapshotDirectory = selectDirectory();

        saveFiles(snapshotDirectory);
        saveServiceFiles(snapshotDirectory);

        return new LocalSnapshot(snapshotDirectory);
    }

    private void saveFiles(File snapshotDirectory) {
        FileUtils.mkdirs(snapshotDirectory);
        FileUtils.copy(localDirectory, snapshotDirectory, excludeFilter);
    }

    private void saveServiceFiles(File snapshotDirectory) {
        Map<String, File> serviceFiles = configuration.serviceFiles;
        if (serviceFiles == null)
            return;

        for (String relativePath : serviceFiles.keySet()) {
            File serviceFile = serviceFiles.get(relativePath);
            File savedFile = new File(snapshotDirectory, relativePath);
            FileUtils.copy(serviceFile, savedFile);
        }


    }

    private File selectDirectory() {
        File snapshotDirectory;
        for (int i=0; true; ++i) {
            String name = String.format("%04d", i);
            snapshotDirectory = new File(snapshotsDirectory, name);
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
            .sorted((o1, o2) -> {
                long t1 = o1.getTimestamp();
                long t2 = o2.getTimestamp();
                return Long.compare(t1, t2);
            })
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Snapshot> getSnapshot(String snapshotName) {
        if (Snapshot.CURRENT.equals(snapshotName))
            return Optional.of(new LocalSnapshot(localDirectory, Snapshot.CURRENT));

        List<Snapshot> snapshots = listSnapshots();
        int nSnapshots = snapshots.size();

        if (Snapshot.FIRST.equals(snapshotName)) {
            if (nSnapshots != 0)
                return Optional.of(snapshots.get(0));
        }

        if (Snapshot.LAST.equals(snapshotName)) {
            if (nSnapshots != 0)
                return Optional.of(snapshots.get(nSnapshots-1));
        }

        if (Snapshot.PREVIOUS.equals(snapshotName)) {
            if (nSnapshots >= 2)
                return Optional.of(snapshots.get(nSnapshots-2));
        }

        for (Snapshot snapshot : snapshots)
            if (snapshot.getName().equals(snapshotName))
                return Optional.of(snapshot);

        return Optional.empty();
    }

    @Override
    public Snapshot rollback(String snapshotName) {
        return rollback(getSnapshot(snapshotName).get());
    }

    @Override
    public Snapshot rollback(Snapshot snapshot) {
        return null;
    }
}
