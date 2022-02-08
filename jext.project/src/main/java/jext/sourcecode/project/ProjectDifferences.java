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

/**
 * A 'project difference' is composed by a list of differences between the content of
 * two different 'source-project' files.
 *
 * The project structure is saved in the file '[refId]-source-project-r[rev].json'.
 * where 'refId' is the project reference id, and 'rev' is the revision index, a
 * integer value >= 0. The NO_REVISION is represented by the integer -1.
 *
 * The differences between two project structures can be:
 *
 *  - module ADDED:   the module has been added to the project
 *  - module DELETED: the module has been deleted from the project
 *  - module CHANGED: the module contains
 *                    ADDED and/or DELETED source files or
 *                    ADDED and/or DELETED libraries
 *                    A module is NOT CHANGED if it contains CHANGED source files
 *
 *  - source ADDED:   the source file has been added to the module
 *  - source DELETED: the source file has been deleted from the module
 *  - source CHANGED: the content of the source file is changed
 *
 *  - library ADDED:  the library has been added to the module
 *
 * ONLY local libraries can be CHANGED. Libraries are never removed.
 *
 * If a module is ADDED or DELETED, this implies automatically that all module's
 * source files are ADDED or DELETED.
 *
 * The direction is from src_revision TO dst_revision
 */
public class ProjectDifferences {

    private static final Logger logger = Logger.getLogger(ProjectDifferences.class);

    private static class ProjectInfo {
        public File projectHome;
    }

    private static class RevisionInfo {
        public int  srcRevision;
        public File srcRevisionInfo;
        public int  dstRevision;
        public File dstRevisionInfo;
    }

    private static class ModuleInfo {
        public File moduleHome;

        public ModuleInfo() { }
        public ModuleInfo(File moduleHome) {
            this.moduleHome = moduleHome;
        }
    }

    private static class ModulesInfo extends HashMap<String, ModuleInfo> {}

    private static class LibraryInfo {
        public List<File> files;

        public LibraryInfo() { }
        public LibraryInfo(List<File> files) {
            this.files = files;
        }
    }

    private static class LibrariesInfo extends HashMap<String, LibraryInfo> {}

    private static class DiffModules extends HashMap<String, RevisionStatus>{}
    private static class DiffModuleSources extends HashMap<String, RevisionStatus> {}
    private static class DiffSources extends HashMap<String, DiffModuleSources> {}
    private static class DiffLibraries extends HashMap<String, RevisionStatus> {}

    // public final Map<String, Object> project = new HashMap<>();
    // public final Map<String, Object> revisions = new HashMap<>();
    // public final Map<String, Object> modules = new HashMap<>();
    // public final Map<String, Object> libraries = new HashMap<>();
    // public final Map<String, String> diffModules = new HashMap<>();
    // public final Map<String, Map<String, String>> diffSources = new HashMap<>();
    // public final Map<String, String> diffLibraries = new HashMap<>();

    public final ProjectInfo project = new ProjectInfo();
    public final RevisionInfo revisions = new RevisionInfo();
    public final ModulesInfo modules = new ModulesInfo();
    public final LibrariesInfo libraries = new LibrariesInfo();

    public final DiffModules diffModules = new DiffModules();
    public final DiffSources diffSources = new DiffSources();
    public final DiffLibraries diffLibraries = new DiffLibraries();

    // private static final String PROJECT_HOME = "projectHome";
    // private static final String SRC_REVISION_INFO = "srcRevisionInfo";
    // private static final String SRC_REVISION = "srcRevision";
    // private static final String DST_REVISION_INFO = "dstRevisionInfo";
    // private static final String DST_REVISION = "dstRevision";
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
        // ProjectDifferences pdiff = new ProjectDifferences();
        // Map<String, Object> data = load(differencesFile, HashMap.class);
        //
        // pdiff.project.putAll(MapUtils.get(data, "project"));
        // pdiff.revisions.putAll(MapUtils.get(data, "revisions"));
        // pdiff.modules.putAll(MapUtils.get(data, "modules"));
        // pdiff.libraries.putAll(MapUtils.get(data, "libraries"));
        // pdiff.diffModules.putAll(MapUtils.get(data, "diffModules"));
        // pdiff.diffSources.putAll(MapUtils.get(data, "diffSources"));
        // pdiff.diffLibraries.putAll(MapUtils.get(data, "diffLibraries"));

        ProjectDifferences pdiff = null;
        try {
            pdiff = JSONUtils.load(differencesFile, ProjectDifferences.class);
        } catch (IOException e) {
            logger.error(e, e);
            pdiff = new ProjectDifferences();
        }

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
            File srcProjectInfo, int srcRevision,
            File dstProjectInfo, int dstRevision)
    {
        Map<String, Object> projectInfo;

        project.projectHome = projectHome;
        revisions.srcRevision = srcRevision;
        revisions.srcRevisionInfo = srcProjectInfo;
        revisions.dstRevision = dstRevision;
        revisions.dstRevisionInfo = dstProjectInfo;

        // project.put(PROJECT_HOME, FileUtils.getAbsolutePath(projectHome));
        // revisions.put(SRC_REVISION_INFO, FileUtils.getAbsolutePath(srcRevisionFile));
        // revisions.put(SRC_REVISION, srcRevision);
        // revisions.put(DST_REVISION_INFO, FileUtils.getAbsolutePath(dstRevisionFile));
        // revisions.put(DST_REVISION, dstRevision);

        if (srcProjectInfo.exists()) {
            projectInfo = load(srcProjectInfo, HashMap.class);
            addModules(projectInfo);
            addLibraries(projectInfo);
        }
        if (dstProjectInfo.exists()) {
            projectInfo = load(dstProjectInfo, HashMap.class);
            addModules(projectInfo);
            addLibraries(projectInfo);
        }
    }

    private void addModules(Map<String, Object> projectInfo) {
        // check for revision -1: the revision file doesn't exist
        Map<String, Object> modules = MapUtils.get(projectInfo, "modules");
        File projectHome = project.projectHome;

        for(String moduleName : modules.keySet()) {
            String modulePath = MapUtils.get(modules, moduleName, "path");
            // this.modules.put(modulePath, MapUtils.asMap(
            //         "moduleHome",new File(projectHome, modulePath)
            // ));
            File moduleHome = new File(projectHome, modulePath);
            this.modules.put(moduleName, new ModuleInfo(moduleHome));
        }
    }

    private void addLibraries(Map<String, Object> projectInfo) {
        Map<String, Object> libraries = MapUtils.get(projectInfo, "libraries");

        for(String libraryName : libraries.keySet()) {
            List<String> libraryFiles = MapUtils.get(libraries, libraryName, "files");
            // this.libraries.put(libraryName, MapUtils.asMap(
            //         "files", libraryFiles
            // ));
            List<File> files = FileUtils.toFiles(libraryFiles);
            this.libraries.put(libraryName, new LibraryInfo(files));
        }
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    /**
     * Check if the data structure represent the difference between two different
     * revision
     * @return false for revisions (-1, r1), true for revisions (r1,r2) where
     * r1 and r2 are != -1 (NO_REVISION)
     */
    @JsonIgnore
    public boolean isDifference() {
        // return MapUtils.getInt(revisions, SRC_REVISION) != NO_REVISION;
        return revisions.srcRevision != NO_REVISION;
    }

    /**
     * Check the there are no differences
     */
    @JsonIgnore
    public boolean isEmpty() {
        return diffModules.isEmpty() && diffSources.isEmpty() && diffLibraries.isEmpty();
    }

    @JsonIgnore
    public File getProjectHome() {
        // return new File(MapUtils.getString(project, "projectHome"));
        return project.projectHome;
    }

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
        return getModuleSources(RevisionStatus.ADDED);
    }

    /**
     * Select the sources deleted from each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getDeletedSources() {
        return getModuleSources(RevisionStatus.DELETED);
    }

    /**
     * Select the sources changed in each module
     *
     * @return a the map [moduleName, list[sourceName]]
     */
    @JsonIgnore
    public List<ModuleSource> getChangedSources() {
        return getModuleSources(RevisionStatus.CHANGED);
    }

    private List<ModuleSource> getModuleSources(RevisionStatus status) {
        List<ModuleSource> selectedSources = new ArrayList<>();

        for (String moduleName : diffSources.keySet()) {
            Map<String, RevisionStatus> moduleSources = diffSources.get(moduleName);
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
        return getSourceFiles(RevisionStatus.ADDED);
    }

    @JsonIgnore
    public List<File> getDeletedSourceFiles() {
        return getSourceFiles(RevisionStatus.DELETED);
    }

    private List<File> getSourceFiles(RevisionStatus status) {
        List<File> files = new ArrayList<>();

        for (String moduleName : diffSources.keySet()) {
            // File moduleHome = new File(MapUtils.getString(modules, moduleName, "moduleHome"));
            File moduleHome = modules.get(moduleName).moduleHome;

            Map<String, RevisionStatus> moduleSources = diffSources.get(moduleName);

            for (String sourcePath : moduleSources.keySet()) {
                if (status.equals(moduleSources.get(sourcePath)))
                    files.add(new File(moduleHome, sourcePath));
            }
        }

        return files;
    }

    @JsonIgnore
    public List<File> getAddedLibraryFiles() {
        RevisionStatus status = RevisionStatus.ADDED;
        List<File> lfiles = new ArrayList<>();

        for (String libraryName : diffLibraries.keySet()) {
            RevisionStatus lstatus = diffLibraries.get(libraryName);
            if (!lstatus.equals(status))
                continue;

            // List<File> lfiles = FileUtils.toFiles(MapUtils.get(libraries, libraryName, "files"));
            List<File> files = libraries.get(libraryName).files;
            lfiles.addAll(files);
        }

        return lfiles;
    }

    // ----------------------------------------------------------------------
    // compareProjects
    // ----------------------------------------------------------------------

    // Used temporary!
    private Map<String, Object> tmpsrc;
    private Map<String, Object> tmpdst;

    public void compareRevisions() {
        // int previousRevision = MapUtils.get(revisions, SRC_REVISION);
        // File srcProjectInfo = new File(MapUtils.getString(revisions, SRC_REVISION_INFO));
        // File dstProjectInfo = new File(MapUtils.getString(revisions, DST_REVISION_INFO));
        int previousRevision = revisions.srcRevision;;
        File srcProjectInfo = revisions.srcRevisionInfo;
        File dstProjectInfo = revisions.dstRevisionInfo;

        if (previousRevision == NO_REVISION)
            srcProjectInfo = null;

        try {
            if (srcProjectInfo == null || srcProjectInfo.equals(dstProjectInfo)) {
                tmpdst = JSONUtils.load(dstProjectInfo, HashMap.class);

                allAddedModules();
                allAddedLibraries();
            }
            else {
                tmpsrc = JSONUtils.load(srcProjectInfo, HashMap.class);
                tmpdst = JSONUtils.load(dstProjectInfo, HashMap.class);

                compareModules();
                compareLibraries();
                compareModuleDigests();
            }
        }
        catch (IOException e) {
            Logger.getLogger(ProjectDifferences.class).error(e, e);
        }
        finally {
            tmpsrc = null;
            tmpdst = null;
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
        Map<String, Object> dst_modules = MapUtils.get(tmpdst, "modules");
        dst_modules.keySet().forEach(module -> {
            addModuleStatus(module, RevisionStatus.ADDED);
        });
    }

    private void allAddedLibraries() {
        Map<String, Object> libraries = MapUtils.get(tmpdst, "libraries");
        libraries.keySet().forEach(libraryName -> {
            addLibraryStatus(libraryName, RevisionStatus.ADDED);
        });
    }

    private void compareModules() {
        Map<String, Object> src_modules = MapUtils.get(tmpsrc, "modules");
        Map<String, Object> dst_modules = MapUtils.get(tmpdst, "modules");

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
        Map<String, Object> src_modules = MapUtils.get(tmpsrc, "modules");
        Map<String, Object> dst_modules = MapUtils.get(tmpdst, "modules");

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
        Map<String, Object> src_libraries = MapUtils.get(tmpsrc, "libraries");
        Map<String, Object> dst_libraries = MapUtils.get(tmpdst, "libraries");

        for (String libraryName : dst_libraries.keySet())
            if (!src_libraries.containsKey(libraryName))
                addLibraryStatus(libraryName, RevisionStatus.ADDED);
    }

    private void addModuleStatus(String moduleName, RevisionStatus status) {
        // can be processed in parallel
        diffModules.put(moduleName, status);

        if (RevisionStatus.ADDED.equals(status)) {
            Map<String, Map<String, Object>> sources = MapUtils.get(tmpdst, "modules", moduleName, "sources");
            sources.keySet().forEach(sourceName -> {
                if (!diffSources.containsKey(moduleName))
                    diffSources.put(moduleName, new DiffModuleSources());
                diffSources.get(moduleName).put(sourceName, RevisionStatus.ADDED);
            });
        }

        if (RevisionStatus.DELETED.equals(status)) {
            Map<String, Map<String, Object>> sources = MapUtils.get(tmpsrc, "modules", moduleName, "sources");
            sources.keySet().forEach(sourceName -> {
                if (!diffSources.containsKey(moduleName))
                    diffSources.put(moduleName, new DiffModuleSources());
                diffSources.get(moduleName).put(sourceName, RevisionStatus.DELETED);
            });
        }
    }

    private void addFileStatus(String moduleName, String sourceName, RevisionStatus status) {
        // can be processed in parallel
        if (!diffSources.containsKey(moduleName))
            diffSources.put(moduleName, new DiffModuleSources());
        diffSources.get(moduleName).put(sourceName, status);
    }

    private void addLibraryStatus(String libraryName, RevisionStatus status) {
        diffLibraries.put(libraryName, status);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
