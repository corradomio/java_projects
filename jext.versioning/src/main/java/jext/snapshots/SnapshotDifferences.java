package jext.snapshots;

import java.util.HashMap;
import java.util.Map;

public class SnapshotDifferences {

    public Map<String, Snapshot.Status> modules = new HashMap<>();
    public Map<String, Map<String, Snapshot.Status>> files = new HashMap<>();

    public boolean isEmpty() {
        return modules.isEmpty() && files.isEmpty();
    }

    public synchronized void addModuleStatus(String modulePath, Snapshot.Status status) {
        // can be processed in parallel
        modules.put(modulePath, status);
    }

    public synchronized void addFileStatus(String modulePath, String filePath, Snapshot.Status status) {
        // can be processed in parallel
        if (!files.containsKey(modulePath))
            files.put(modulePath, new HashMap<>());
        files.get(modulePath).put(filePath, status);
    }
}
