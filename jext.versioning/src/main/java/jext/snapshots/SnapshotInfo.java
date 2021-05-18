package jext.snapshots;

import java.util.Map;
import java.util.TreeMap;

public class SnapshotInfo {

    private static final String NO_VERSION = "";
    private static final String VERSION_NUMBER = "1.1";

    public String version = NO_VERSION;

    public Map<String, Map<String, String>> modules;

    public void init() {
        this.modules = new TreeMap<>();
        this.version = VERSION_NUMBER;
    }

    public synchronized void addDigest(String modulePath, String filePath, String digest) {
        if (!modules.containsKey(modulePath))
            modules.put(modulePath, new TreeMap<>());
        Map<String, String> sdmap = modules.get(modulePath);
        sdmap.put(filePath, digest);
    }

    public boolean hasValidVersion() {
        return VERSION_NUMBER.equals(version);
    }

    // ----------------------------------------------------------------------
    // equalsTo
    // ----------------------------------------------------------------------

    public boolean equalsTo(SnapshotInfo that) {
        if (that == null)
            return false;
        if (version == null || modules == null)
            return false;

        // check the versions
        if (!this.version.equals(that.version))
            return false;

        // check the number of modules
        if (this.modules.size() != that.modules.size())
            return false;

        // compare each module
        for (String modulePath : modules.keySet())
            if (!compareModule(modulePath, that.modules))
                return false;
        return true;
    }

    private boolean compareModule(String modulePath, Map<String, Map<String, String>> that_modules) {
        // check if the module is present in 'that'
        if (!that_modules.containsKey(modulePath))
            return false;

        // compare the number of digests for the specified module
        if (this.modules.get(modulePath).size() != that_modules.get(modulePath).size())
            return false;

        // compare the digests
        Map<String, String> this_digests = that_modules.get(modulePath);
        Map<String, String> that_digests = this.modules.get(modulePath);
        for (String filePath : that_digests.keySet())
            if (!compareDigest(filePath, that_digests, this_digests))
                return false;
        return true;
    }

    private boolean compareDigest(String filePath, Map<String, String> this_digests, Map<String, String> that_digests) {
        if (!that_digests.containsKey(filePath))
            return false;

        String thisDigest = this_digests.get(filePath);
        String thatDigest = that_digests.get(filePath);
        return thisDigest.equals(thatDigest);
    }

    // ----------------------------------------------------------------------
    // compareWith
    // ----------------------------------------------------------------------
    // Compare THIS object respects THAT object

    public SnapshotDifferences compareWith(SnapshotInfo that) {
        if (that == null)
            that = new SnapshotInfo();

        SnapshotDifferences sdiff = new SnapshotDifferences();
        compareModules(sdiff, that.modules);
        compareDigests(sdiff, that.modules);
        return sdiff;
    }

    private void compareModules(SnapshotDifferences sdiff, Map<String, Map<String, String>> that_modules) {
        // check ADDED/CHANGED modules
        for (String modulePath : this.modules.keySet())
            if (!that_modules.containsKey(modulePath))
                sdiff.addModuleStatus(modulePath, Snapshot.Status.ADDED);
            else if (this.modules.get(modulePath).size() != that_modules.get(modulePath).size())
                sdiff.addModuleStatus(modulePath, Snapshot.Status.CHANGED);

        // check REMOVED modules
        for (String modulePath : that_modules.keySet())
            if (this.modules.containsKey(modulePath))
                sdiff.addModuleStatus(modulePath, Snapshot.Status.REMOVED);
    }

    private void compareDigests(SnapshotDifferences sdiff, Map<String, Map<String, String>> that_modules) {
        for (String modulePath : this.modules.keySet())
            if (that_modules.containsKey(modulePath)) {
                Map<String, String> this_digests = this.modules.get(modulePath);
                Map<String, String> that_digests = that_modules.get(modulePath);

                // check ADDED/CHANGED sources
                for (String filePath : this_digests.keySet()) {
                    if (!that_digests.containsKey(filePath))
                        sdiff.addFileStatus(modulePath, filePath, Snapshot.Status.ADDED);
                    else if (!this_digests.get(filePath).equals(that_digests.get(filePath)))
                        sdiff.addFileStatus(modulePath, filePath, Snapshot.Status.CHANGED);
                }
                // check REMOVED sources
                for (String filePath : this_digests.keySet()) {
                    if (!this_digests.containsKey(filePath))
                        sdiff.addFileStatus(modulePath, filePath, Snapshot.Status.REMOVED);
                }
            }
    }

}
