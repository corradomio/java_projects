package jext.sourcecode.project;

import jext.logging.Logger;
import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectDifferences {

    public enum Status {
        ADDED,
        REMOVED,
        CHANGED
    }

    public Map<String, Status> diffModules = new HashMap<>();
    public Map<String, Map<String, Status>> diffSources = new HashMap<>();
    public Map<String, Map<String, Status>> diffLibraries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectDifferences() {

    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Check the there are no differences
     */
    public boolean isEmpty() {
        return diffModules.isEmpty() && diffSources.isEmpty() && diffLibraries.isEmpty();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // local cache to speedup the operations
    // private final Map<Status, List<String>> stateModules = new HashMap<>();

    private List<Module> addedModules;
    private List<Source> addedSources;

    public List<Module> getAddedModules(Project project) {
        if (addedModules != null)
            return addedModules;

        addedModules = getModules(Status.ADDED).stream()
            .map(moduleName -> project.getModule(moduleName))
            .collect(Collectors.toList());
        return addedModules;
    }

    public List<Module> getRemovedModules(Project project) {
        return getModules(Status.REMOVED).stream()
            .map(moduleName -> project.getModule(moduleName))
            .collect(Collectors.toList());
    }

    private List<String> getModules(Status status) {
        // if (stateModules.containsKey(status))
        //     return stateModules.get(status);

        List<String> modules = new ArrayList<>();
        for(String moduleName : diffModules.keySet())
            if (status.equals(diffModules.get(moduleName)))
                modules.add(moduleName);
        // stateModules.put(status, modules);
        return modules;
    }

    public List<Source> getAddedSources(Project project) {
        if (addedSources != null)
            return addedSources;

        addedSources = new ArrayList<>();

        // add sources of added modules
        for (Module module : getAddedModules(project))
            addedSources.addAll(module.getSources());

        // scan sources module by module
        for (String moduleName : diffSources.keySet()) {
            Module module = project.getModule(moduleName);
            Map<String, Status> moduleSources = diffSources.get(moduleName);
            for (String sourceName : moduleSources.keySet())
                if (Status.ADDED.equals(moduleSources.get(sourceName)))
                    addedSources.add(module.getSource(sourceName));
        }

        return addedSources;
    }

    public List<Source> getRemovedSources(Project project) {

        // add sources of removed modules
        List<Source> removedSources = new ArrayList<>();
        for (Module module : getRemovedModules(project))
            removedSources.addAll(module.getSources());

        // scan sources module by module
        for (String moduleName : diffSources.keySet()) {
            Module module = project.getModule(moduleName);
            Map<String, Status> moduleSources = diffSources.get(moduleName);
            for (String sourceName : moduleSources.keySet())
                if (Status.REMOVED.equals(moduleSources.get(sourceName)))
                    removedSources.add(module.getSource(sourceName));
        }

        return removedSources;
    }

    // ----------------------------------------------------------------------
    // compareRevisions
    // ----------------------------------------------------------------------

    // Used temporary!
    private Map<String, Object> this_revision;
    private Map<String, Object> that_revision;

    public void compareRevisions(File thisProjectInfo, File thatProjectInfo) {
        try {
            this_revision = JSONUtils.load(thisProjectInfo, HashMap.class);
            that_revision = JSONUtils.load(thatProjectInfo, HashMap.class);

            compareModules();
            compareDigests();
        }
        catch (IOException e) {
            Logger.getLogger(ProjectDifferences.class).error(e, e);
        }
        finally {
            this_revision = null;
            that_revision = null;
        }
    }

    private void compareModules() {
        Map<String, Object> this_modules = MapUtils.get(this_revision, "modules");
        Map<String, Object> that_modules = MapUtils.get(that_revision, "modules");

        // check ADDED/CHANGED modules
        for (String moduleName : this_modules.keySet()) {
            if (!that_modules.containsKey(moduleName)) {
                this.addModuleStatus(moduleName, Status.ADDED);
                continue;
            }

            int this_sources_count = MapUtils.get(this_modules, moduleName, "counts", "sources");
            int that_sources_count = MapUtils.get(that_modules, moduleName, "counts", "sources");

            if (this_sources_count != that_sources_count)
                this.addModuleStatus(moduleName, Status.CHANGED);
        }

        // check REMOVED modules
        for (String moduleName : that_modules.keySet())
            if (!this_modules.containsKey(moduleName))
                this.addModuleStatus(moduleName, Status.REMOVED);
    }

    private void compareDigests() {
        Map<String, Object> this_modules = MapUtils.get(this_revision, "modules");
        Map<String, Object> that_modules = MapUtils.get(that_revision, "modules");

        // loop on modules
        for (String moduleName : this_modules.keySet()) {
            // skip added module
            if (!that_modules.containsKey(moduleName)) {
                continue;
            }

            Map<String, Object> this_sources = MapUtils.get(this_modules, moduleName, "sources");
            Map<String, Object> that_sources = MapUtils.get(that_modules, moduleName, "sources");

            // check ADDED/CHANGED sources
            for (String sourceName : this_sources.keySet()) {

                if (!that_sources.containsKey(sourceName)) {
                    this.addFileStatus(moduleName, sourceName, Status.ADDED);
                    continue;
                }

                String this_digest = MapUtils.get(this_sources, sourceName, "digest");
                String that_digest = MapUtils.get(that_sources, sourceName, "digest");
                if (!this_digest.equals(that_digest))
                    this.addFileStatus(moduleName, sourceName, Status.CHANGED);
            }

            // check REMOVED sources
            for (String sourceName : that_sources.keySet()) {
                if (!this_sources.containsKey(sourceName))
                    this.addFileStatus(moduleName, sourceName, Status.REMOVED);
            }
        }
    }

    private /*synchronized*/ void addModuleStatus(String moduleName, Status status) {
        // can be processed in parallel
        diffModules.put(moduleName, status);
    }

    private /*synchronized*/ void addFileStatus(String moduleName, String sourceName, Status status) {
        // can be processed in parallel
        if (!diffSources.containsKey(moduleName))
            diffSources.put(moduleName, new HashMap<>());
        diffSources.get(moduleName).put(sourceName, status);
    }
}
