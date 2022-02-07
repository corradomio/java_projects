package jext.sourcecode.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.JSONUtils;
import jext.util.MapUtils;
import jext.util.SetUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    private static final Logger logger = Logger.getLogger(ProjectDifferences.class);

    public final Map<String, Object> project = new HashMap<>();
    public final Map<String, Object> revisions = new HashMap<>();
    public final Map<String, Object> modules = new HashMap<>();
    public final Map<String, Object> libraries = new HashMap<>();
    public final Map<String, String> diffModules = new HashMap<>();
    public final Map<String, Map<String, String>> diffSources = new HashMap<>();
    public final Map<String, String> diffLibraries = new HashMap<>();


    private static final String PROJECT_HOME = "projectHome";
    private static final String SRC_REVISION_INFO = "srcRevisionInfo";
    private static final String SRC_REVISION = "srcRevision";
    private static final String DST_REVISION_INFO = "dstRevisionInfo";
    private static final String DST_REVISION = "dstRevision";
    private static final int NO_REVISION = -1;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectDifferences() {

    }

    // ----------------------------------------------------------------------
    // Factory method
    // ----------------------------------------------------------------------

    public static ProjectDifferences load(File differencesFile) {
        ProjectDifferences pdiff = new ProjectDifferences();
        Map<String, Object> data = load(differencesFile, HashMap.class);

        pdiff.project.putAll(MapUtils.get(data, "project"));
        pdiff.revisions.putAll(MapUtils.get(data, "revisions"));
        pdiff.modules.putAll(MapUtils.get(data, "modules"));
        pdiff.libraries.putAll(MapUtils.get(data, "libraries"));
        pdiff.diffModules.putAll(MapUtils.get(data, "diffModules"));
        pdiff.diffSources.putAll(MapUtils.get(data, "diffSources"));
        pdiff.diffLibraries.putAll(MapUtils.get(data, "diffLibraries"));

        return pdiff;
    }

    private static Map<String, Object> load(File jsonFile, Class clazz) {
        try {
            return JSONUtils.load(jsonFile, HashMap.class);
        } catch (IOException e) {
            logger.error(e, e);
            return Collections.emptyMap();
        }
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public void setRevisionsInfo(
            File projectHome,
            File srcRevisionFile, int srcRevision,
            File dstRevisionFile, int dstRevision)
    {
        Map<String, Object> projectInfo;

        project.put(PROJECT_HOME, FileUtils.getAbsolutePath(projectHome));
        revisions.put(SRC_REVISION_INFO, FileUtils.getAbsolutePath(srcRevisionFile));
        revisions.put(SRC_REVISION, srcRevision);
        revisions.put(DST_REVISION_INFO, FileUtils.getAbsolutePath(dstRevisionFile));
        revisions.put(DST_REVISION, dstRevision);

        if (srcRevisionFile.exists()) {
            projectInfo = load(srcRevisionFile, HashMap.class);
            addModules(projectHome, projectInfo);
            addLibraries(projectInfo);
        }
        if (dstRevisionFile.exists()) {
            projectInfo = load(dstRevisionFile, HashMap.class);
            addModules(projectHome, projectInfo);
            addLibraries(projectInfo);
        }
    }

    private void addModules(File projectHome, Map<String, Object> projectInfo) {
        // check for revision -1: the revision file doesn't exist
        Map<String, Object> modules = MapUtils.get(projectInfo, "modules");

        for(String moduleName : modules.keySet()) {
            String modulePath = MapUtils.get(modules, moduleName, "path");
            this.modules.put(modulePath, MapUtils.asMap(
                    "moduleHome",new File(projectHome, modulePath)
            ));
        }
    }

    private void addLibraries(Map<String, Object> projectInfo) {
        Map<String, Object> libraries = MapUtils.get(projectInfo, "libraries");

        for(String libraryName : libraries.keySet()) {
            List<String> libraryFiles = MapUtils.get(libraries, libraryName, "files");
            this.libraries.put(libraryName, MapUtils.asMap(
                    "files", libraryFiles
            ));
        }
    }

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
        return getModules(RevisionStatus.ADDED);
    }

    @JsonIgnore
    public List<String> getDeletedModules() {
        return getModules(RevisionStatus.DELETED);
    }

    @JsonIgnore
    public List<String> getChangedModules() {
        return getModules(RevisionStatus.CHANGED);
    }

    private List<String> getModules(RevisionStatus status) {
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
        return getModuleSources(RevisionStatus.ADDED.toString());
    }

    /**
     * Select the sources deleted from each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getDeletedSources() {
        return getModuleSources(RevisionStatus.DELETED.toString());
    }

    /**
     * Select the sources changed in each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getChangedSources() {
        return getModuleSources(RevisionStatus.CHANGED.toString());
    }

    private List<ModuleSource> getModuleSources(String status) {
        List<ModuleSource> selectedSources = new ArrayList<>();

        for (String moduleName : diffSources.keySet()) {
            Map<String, String> moduleSources = diffSources.get(moduleName);
            for (String sourceName : moduleSources.keySet()) {
                if (status.equals(moduleSources.get(sourceName)))
                    selectedSources.add(new ModuleSource(moduleName, sourceName));
            }

        }
        return selectedSources;
    }

    // ----------------------------------------------------------------------

    @JsonIgnore
    public List<File> getAddedSourceFiles() {
        return getSourceFiles(RevisionStatus.ADDED.toString());
    }

    @JsonIgnore
    public List<File> getDeletedSourceFiles() {
        return getSourceFiles(RevisionStatus.DELETED.toString());
    }

    private List<File> getSourceFiles(String status) {
        List<File> files = new ArrayList<>();

        for (String moduleName : diffSources.keySet()) {
            File moduleHome = new File(MapUtils.getString(modules, moduleName, "moduleHome"));

            Map<String, String> moduleSources = diffSources.get(moduleHome);

            for (String sourcePath : moduleSources.keySet()) {
                if (status.equals(moduleSources.get(sourcePath)))
                    files.add(new File(moduleName, sourcePath));
            }
        }

        return files;
    }

    @JsonIgnore
    public List<File> getAddedLibraryFiles() {
        String status = RevisionStatus.ADDED.toString();
        List<File> files = new ArrayList<>();

        for (String libraryName : diffLibraries.keySet()) {
            String lstatus = diffLibraries.get(libraryName);
            if (!lstatus.equals(status))
                continue;

            List<File> lfiles = FileUtils.toFiles(MapUtils.get(libraries, libraryName, "files"));
            files.addAll(lfiles);
        }

        return files;
    }

    // ----------------------------------------------------------------------
    // compareProjects
    // ----------------------------------------------------------------------

    // Used temporary!
    private Map<String, Object> src_project;
    private Map<String, Object> dst_project;

    public void compareRevisions() {
        int previousRevision = MapUtils.get(revisions, SRC_REVISION);
        File srcProjectInfo = new File(MapUtils.getString(revisions, SRC_REVISION_INFO));
        File dstProjectInfo = new File(MapUtils.getString(revisions, DST_REVISION_INFO));

        if (previousRevision == NO_REVISION)
            srcProjectInfo = null;

        try {
            if (srcProjectInfo == null || srcProjectInfo.equals(dstProjectInfo)) {
                dst_project = JSONUtils.load(dstProjectInfo, HashMap.class);

                allAddedModules();
                allAddedLibraries();
            }
            else {
                src_project = JSONUtils.load(srcProjectInfo, HashMap.class);
                dst_project = JSONUtils.load(dstProjectInfo, HashMap.class);

                compareModules();
                compareLibraries();
                compareModuleDigests();
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

    // /**
    //  * This method is used when no 'previous' revision exists.
    //  * In this case, it contains only a list of ADDED modules
    //  */
    // public void compareProjects(File srcProjectInfo, File dstProjectInfo) {
    //     try {
    //         if (srcProjectInfo == null || srcProjectInfo.equals(dstProjectInfo)) {
    //             dst_project = JSONUtils.load(dstProjectInfo, HashMap.class);
    //
    //             allAddedModules();
    //         }
    //         else {
    //             src_project = JSONUtils.load(srcProjectInfo, HashMap.class);
    //             dst_project = JSONUtils.load(dstProjectInfo, HashMap.class);
    //
    //             compareModules();
    //             compareDigests();
    //         }
    //     }
    //     catch (IOException e) {
    //         Logger.getLogger(ProjectDifferences.class).error(e, e);
    //     }
    //     finally {
    //         src_project = null;
    //         dst_project = null;
    //     }
    // }

    private void allAddedModules() {
        Map<String, Object> dst_modules = MapUtils.get(dst_project, "modules");
        dst_modules.keySet().forEach(module -> {
            addModuleStatus(module, RevisionStatus.ADDED);
        });
    }

    private void allAddedLibraries() {
        Map<String, Object> libraries = MapUtils.get(dst_project, "libraries");
        libraries.keySet().forEach(libraryName -> {
            addLibraryStatus(libraryName, RevisionStatus.ADDED);
        });
    }

    private void compareModules() {
        Map<String, Object> src_modules = MapUtils.get(src_project, "modules");
        Map<String, Object> dst_modules = MapUtils.get(dst_project, "modules");

        // check ADDED/CHANGED modules
        for (String moduleName : dst_modules.keySet()) {
            if (!src_modules.containsKey(moduleName)) {
                addModuleStatus(moduleName, RevisionStatus.ADDED);
                continue;
            }

            Map<String, Object> src_module_sources_map = MapUtils.get(src_modules, moduleName, "sources");
            Map<String, Object> dst_module_sources_map = MapUtils.get(dst_modules, moduleName, "sources");
            Set<String> src_module_sources = src_module_sources_map.keySet();
            Set<String> dst_module_sources = dst_module_sources_map.keySet();

            if (!SetUtils.simmdiff(src_module_sources, dst_module_sources).isEmpty())
                addModuleStatus(moduleName, RevisionStatus.CHANGED);
        }

        // check DELETED modules
        for (String moduleName : src_modules.keySet())
            if (!dst_modules.containsKey(moduleName))
                addModuleStatus(moduleName, RevisionStatus.DELETED);
    }

    private void compareModuleDigests() {
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
                    addFileStatus(moduleName, sourceName, RevisionStatus.ADDED);
                    continue;
                }

                String src_digest = MapUtils.getString(src_sources, sourceName, "digest");
                String dst_digest = MapUtils.getString(dst_sources, sourceName, "digest");
                if (!src_digest.equals(dst_digest)) {
                    addFileStatus(moduleName, sourceName, RevisionStatus.CHANGED);
                    addModuleStatus(moduleName, RevisionStatus.CHANGED);
                }
            }

            // check DELETED sources
            for (String sourceName : src_sources.keySet()) {
                if (!dst_sources.containsKey(sourceName))
                    addFileStatus(moduleName, sourceName, RevisionStatus.DELETED);
            }
        }
    }

    private void compareLibraries() {
        Map<String, Object> src_libraries = MapUtils.get(src_project, "libraries");
        Map<String, Object> dst_libraries = MapUtils.get(dst_project, "libraries");

        for (String libraryName : dst_libraries.keySet())
            if (!src_libraries.containsKey(libraryName))
                addLibraryStatus(libraryName, RevisionStatus.ADDED);
    }

    private void addModuleStatus(String moduleName, RevisionStatus status) {
        // can be processed in parallel
        diffModules.put(moduleName, status.toString());

        if (RevisionStatus.ADDED.equals(status)) {
            Map<String, Map<String, Object>> sources = MapUtils.get(dst_project, "modules", moduleName, "sources");
            sources.keySet().forEach(sourceName -> {
                if (!diffSources.containsKey(moduleName))
                    diffSources.put(moduleName, new TreeMap<>());
                diffSources.get(moduleName).put(sourceName, RevisionStatus.ADDED.toString());
            });
        }

        if (RevisionStatus.DELETED.equals(status)) {
            Map<String, Map<String, Object>> sources = MapUtils.get(src_project, "modules", moduleName, "sources");
            sources.keySet().forEach(sourceName -> {
                if (!diffSources.containsKey(moduleName))
                    diffSources.put(moduleName, new TreeMap<>());
                diffSources.get(moduleName).put(sourceName, RevisionStatus.DELETED.toString());
            });
        }
    }

    private void addFileStatus(String moduleName, String sourceName, RevisionStatus status) {
        // can be processed in parallel
        if (!diffSources.containsKey(moduleName))
            diffSources.put(moduleName, new HashMap<>());
        diffSources.get(moduleName).put(sourceName, status.toString());
    }

    private void addLibraryStatus(String libraryName, RevisionStatus status) {
        diffLibraries.put(libraryName, status.toString());
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
