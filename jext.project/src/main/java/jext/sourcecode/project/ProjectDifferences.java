package jext.sourcecode.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jext.logging.Logger;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import jext.util.SetUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
 * The direction is from src_revision to dst_revision
 */
public class ProjectDifferences {

    public enum Status {
        ADDED,
        DELETED,
        CHANGED
    }

    // @JsonIgnore
    // public int srcRevision;
    // @JsonIgnore
    // public int dstRevision;

    public final Map<String, Status> diffModules = new HashMap<>();
    public final Map<String, Map<String, Status>> diffSources = new HashMap<>();
    public final Map<String, Map<String, Status>> diffLibraries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectDifferences() {
        // srcRevision = -1;
        // dstRevision = -1;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Check the there are no differences
     */
    @JsonIgnore
    public boolean isEmpty() {
        return diffModules.isEmpty() && diffSources.isEmpty() && diffLibraries.isEmpty();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @JsonIgnore
    public List<String> getAddedModules() {
        return getModules(Status.ADDED);
    }

    @JsonIgnore
    public List<String> getDeletedModules() {
        return getModules(Status.DELETED);
    }

    @JsonIgnore
    public List<String> getChangedModules() {
        return getModules(Status.CHANGED);
    }

    private List<String> getModules(Status status) {
        List<String> selectedModules = new ArrayList<>();
        for(String moduleName : diffModules.keySet())
            if (status.equals(diffModules.get(moduleName)))
                selectedModules.add(moduleName);
        return selectedModules;
    }

    // ----------------------------------------------------------------------

    /**
     * Select the sources added to each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getAddedSources() {
        return getModuleSources(Status.ADDED);
    }

    /**
     * Select the sources deleted from each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getDeletedSources() {
        return getModuleSources(Status.DELETED);
    }

    /**
     * Select the sources changed in each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getChangedSources() {
        return getModuleSources(Status.CHANGED);
    }

    private List<ModuleSource> getModuleSources(Status status) {
        List<ModuleSource> selectedSources = new ArrayList<>();

        for (String moduleName : diffSources.keySet()) {
            Map<String, Status> moduleSources = diffSources.get(moduleName);
            for (String sourceName : moduleSources.keySet()) {
                if (status.equals(moduleSources.get(sourceName)))
                    selectedSources.add(new ModuleSource(moduleName, sourceName));
            }

        }
        return selectedSources;
    }

    // ----------------------------------------------------------------------
    // compareProjects
    // ----------------------------------------------------------------------

    // Used temporary!
    private Map<String, Object> src_project;
    private Map<String, Object> dst_project;

    /**
     * This method is used when no 'previous' revision exists.
     * In this case, it contains only a list of ADDED modules
     */
    public void compareProjects(File srcProjectInfo, File dstProjectInfo) {
        try {
            if (srcProjectInfo == null || srcProjectInfo.equals(dstProjectInfo)) {
                dst_project = JSONUtils.load(dstProjectInfo, HashMap.class);

                allAddedModules();
            }
            else {
                src_project = JSONUtils.load(srcProjectInfo, HashMap.class);
                dst_project = JSONUtils.load(dstProjectInfo, HashMap.class);

                compareModules();
                compareDigests();
            }
        }
        catch (IOException e) {
            Logger.getLogger(ProjectDifferences.class).error(e, e);
        }
        finally {
            src_project = null;
            dst_project = null;
        }
    }

    private void allAddedModules() {
        Map<String, Object> dst_modules = MapUtils.get(dst_project, "modules");
        dst_modules.keySet().forEach(module -> {
            addModuleStatus(module, Status.ADDED);
        });
    }

    private void compareModules() {
        Map<String, Object> src_modules = MapUtils.get(src_project, "modules");
        Map<String, Object> dst_modules = MapUtils.get(dst_project, "modules");

        // check ADDED/CHANGED modules
        for (String moduleName : dst_modules.keySet()) {
            if (!src_modules.containsKey(moduleName)) {
                addModuleStatus(moduleName, Status.ADDED);
                continue;
            }

            Map<String, Object> src_module_sources_map = MapUtils.get(src_modules, moduleName, "sources");
            Map<String, Object> dst_module_sources_map = MapUtils.get(dst_modules, moduleName, "sources");
            Set<String> src_module_sources = src_module_sources_map.keySet();
            Set<String> dst_module_sources = dst_module_sources_map.keySet();

            if (!SetUtils.simmdiff(src_module_sources, dst_module_sources).isEmpty())
                addModuleStatus(moduleName, Status.CHANGED);
        }

        // check DELETED modules
        for (String moduleName : src_modules.keySet())
            if (!dst_modules.containsKey(moduleName))
                addModuleStatus(moduleName, Status.DELETED);
    }

    private void compareDigests() {
        Map<String, Object> src_modules = MapUtils.get(src_project, "modules");
        Map<String, Object> dst_modules = MapUtils.get(dst_project, "modules");

        // loop on modules
        for (String moduleName : src_modules.keySet()) {
            // skip added module
            if (!dst_modules.containsKey(moduleName)) {
                continue;
            }

            Map<String, Object> src_sources = MapUtils.get(src_modules, moduleName, "sources");
            Map<String, Object> dst_sources = MapUtils.get(dst_modules, moduleName, "sources");

            // check ADDED/CHANGED sources
            for (String sourceName : dst_sources.keySet()) {
                if (!src_sources.containsKey(sourceName)) {
                    addFileStatus(moduleName, sourceName, Status.ADDED);
                    continue;
                }

                long src_digest = MapUtils.getLong(src_sources, sourceName, "digest");
                long dst_digest = MapUtils.getLong(dst_sources, sourceName, "digest");
                if (src_digest != (dst_digest)) {
                    addFileStatus(moduleName, sourceName, Status.CHANGED);
                    addModuleStatus(moduleName, Status.CHANGED);
                }
            }

            // check DELETED sources
            for (String sourceName : src_sources.keySet()) {
                if (!dst_sources.containsKey(sourceName))
                    addFileStatus(moduleName, sourceName, Status.DELETED);
            }
        }
    }

    private /*synchronized*/ void addModuleStatus(String moduleName, Status status) {
        // can be processed in parallel
        diffModules.put(moduleName, status);

        if (Status.ADDED.equals(status)) {
            Map<String, Map<String, Object>> sources = MapUtils.get(dst_project, "modules", moduleName, "sources");
            sources.keySet().forEach(sourceName -> {
                if (!diffSources.containsKey(moduleName))
                    diffSources.put(moduleName, new TreeMap<>());
                diffSources.get(moduleName).put(sourceName, Status.ADDED);
            });
        }

        if (Status.DELETED.equals(status)) {
            Map<String, Map<String, Object>> sources = MapUtils.get(src_project, "modules", moduleName, "sources");
            sources.keySet().forEach(sourceName -> {
                if (!diffSources.containsKey(moduleName))
                    diffSources.put(moduleName, new TreeMap<>());
                diffSources.get(moduleName).put(sourceName, Status.DELETED);
            });
        }
    }

    private /*synchronized*/ void addFileStatus(String moduleName, String sourceName, Status status) {
        // can be processed in parallel
        if (!diffSources.containsKey(moduleName))
            diffSources.put(moduleName, new HashMap<>());
        diffSources.get(moduleName).put(sourceName, status);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public void dump() {
        System.out.println("Modules:");
        diffModules.forEach((m, s) -> {
            System.out.printf("  %s: %s\n", m, s);
        });
        System.out.println("Libraries:");
        diffLibraries.forEach((m, s) -> {
            System.out.printf("  %s: %s\n", m, s);
        });
        System.out.println("Sources:");
        diffSources.forEach((m, s) -> {
            System.out.printf("  Module %s:\n", m);
            s.forEach((src, status) -> {
                System.out.printf("    %s:  %s\n", src, status);
            });
        });
    }
}
