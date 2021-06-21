package jext.sourcecode.project;

import jext.logging.Logger;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.util.ProjectUtils;
import jext.util.JSONUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A 'project difference' is composed by a list of differences between the content of
 * two different 'project-info' files.
 *
 * The 'current' project structure is saved in the file 'project-info.json'.
 * The project structure with 'revision 'rev' (an integer >=0) is saved in the file
 * 'project-info-[rev].json'.
 *
 * The differences between two project structures can be:
 *
 *  - module ADDED: the module has been added to the project
 *  - module DELETED: the module has been deleted from the project
 *  - module CHANGED: the module contains
 *                    ADDED and/or DELETED source files or
 *                    ADDED and/or DELETED libraries
 *
 *  - source ADDED: the source file has been added to the module
 *  - source DELETED: the source file has been deleted from the module
 *  - source CHANGED: the content of the source file is changed
 *
 * If a module is ADDED or DELETED, this implies automatically that all module's
 * source files are ADDED or DELETED.
 *
 */
public class ProjectDifferences {

    public enum Status {
        ADDED,
        DELETED,
        CHANGED
    }

    public final Map<String, Status> diffModules = new HashMap<>();
    public final Map<String, Map<String, Status>> diffSources = new HashMap<>();
    public final Map<String, Map<String, Status>> diffLibraries = new HashMap<>();

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

    // local cache to speedup some operations
    public List<Name> getAddedModules() {
        return getModules(Status.ADDED);
    }

    public List<Name> getDeletedModules() {
        return getModules(Status.DELETED);
    }

    private List<Name> getModules(Status status) {
        // if (stateModules.containsKey(status))
        //     return stateModules.get(status);

        List<Name> modules = new ArrayList<>();
        for(String moduleName : diffModules.keySet())
            if (status.equals(diffModules.get(moduleName)))
                modules.add(new PathName(moduleName));
        // stateModules.put(status, modules);
        return modules;
    }

    public List<Source> getAddedSources(Project project) {
        List<Source> addedSources = new ArrayList<>();

        // add sources of added modules
        for (Module module : ProjectUtils.getModules(project, getAddedModules()))
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

    public List<String> getDeletedSources() {

        // add sources of deleted modules
        List<String> deletedSources = new ArrayList<>();

        return deletedSources;
    }

    // ----------------------------------------------------------------------
    // compareRevisions
    // ----------------------------------------------------------------------

    // Used temporary!
    private Map<String, Object> this_revision;
    private Map<String, Object> that_revision;

    /**
     * This method is used when no 'previous' revision exists.
     * In this case, it contains only a list of ADDED modules
     */
    public void compareRevisions(File srcProjectInfo, File dstProjectInfo) {
        try {
            if (srcProjectInfo == null || srcProjectInfo.equals(dstProjectInfo)) {
                that_revision = JSONUtils.load(dstProjectInfo, HashMap.class);

                allAddedModules();
            }
            else {
                this_revision = JSONUtils.load(srcProjectInfo, HashMap.class);
                that_revision = JSONUtils.load(dstProjectInfo, HashMap.class);

                compareModules();
                compareDigests();
            }
        }
        catch (IOException e) {
            Logger.getLogger(ProjectDifferences.class).error(e, e);
        }
        finally {
            this_revision = null;
            that_revision = null;
        }
    }

    private void allAddedModules() {
        Map<String, Object> that_modules = MapUtils.get(that_revision, "modules");
        that_modules.keySet().forEach(module -> {
            addModuleStatus(module, Status.ADDED);
        });
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

        // check DELETED modules
        for (String moduleName : that_modules.keySet())
            if (!this_modules.containsKey(moduleName))
                this.addModuleStatus(moduleName, Status.DELETED);
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

            // check DELETED sources
            for (String sourceName : that_sources.keySet()) {
                if (!this_sources.containsKey(sourceName))
                    this.addFileStatus(moduleName, sourceName, Status.DELETED);
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
