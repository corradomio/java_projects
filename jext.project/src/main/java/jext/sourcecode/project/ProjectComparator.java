package jext.sourcecode.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jext.exception.InvalidParameterException;
import jext.name.Name;
import jext.util.HashMap;
import jext.util.JSONUtils;
import jext.util.SetUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProjectComparator {

    // ----------------------------------------------------------------------
    // Factory method
    // ----------------------------------------------------------------------

    public static ProjectComparator compare(Project psrc, Project pdst, int revision) {
        ProjectComparator pcomp = new ProjectComparator(psrc, pdst, revision);
        pcomp.compare();
        return pcomp;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    /** Destination project revision */
    private int revision;
    /** Source project revision */
    private Project psrc;
    /** Destination project revision */
    private Project pdst;
    /** number of project changes (at the moment, n of files added/removed/changed */
    private int nchanges;

    // ----------------------------------------------------------------------
    // Public fields
    // ----------------------------------------------------------------------

    public static class ProjectInfo {
        public File projectHome;
        public int revision;
        public String refId;
        public long timestamp;

        public ProjectInfo() { }
        private ProjectInfo(Project p, int rev) {
            revision = rev;
            projectHome = p.getProjectHome();
            refId = p.getId();
            timestamp = System.currentTimeMillis();
        }
    }
    public ProjectInfo project;

    public static class SourceInfo {
        public RevisionStatus status;
        public String name;
        public File file;

        public SourceInfo() { }
        private SourceInfo(Source source, RevisionStatus status) {
            this.status = status;
            this.name = source.getName().getFullName();
            this.file = source.getFile();
        }
    }

    public static class ModuleInfo {
        public RevisionStatus status;
        public String name;

        public Map<String, SourceInfo> sources = new HashMap<>();

        public ModuleInfo() { }
        public ModuleInfo(Module module, RevisionStatus status) {
            this.status = status;
            this.name = module.getName().getFullName();

            if (status == RevisionStatus.ADDED || status == RevisionStatus.DELETED)
                addSources(module.getSources(), status);
        }

        int addSources(Collection<Source> sources, RevisionStatus status) {
            sources.forEach(source -> {
                this.sources.put(source.getName().getFullName(), new SourceInfo(source, status));
            });

            return this.sources.size();
        }

    }
    public Map<String, ModuleInfo> modules = new HashMap<>();

    public static class LibraryInfo {
        public RevisionStatus status;
        public String name;
        public List<File> files;

        public LibraryInfo() { }
        private LibraryInfo(Library library, RevisionStatus status) {
            this.status = status;
            this.name = library.getName().getFullName();
            this.files = library.getFiles();
        }
    }
    public Map<String, LibraryInfo> libraries = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ProjectComparator() {

    }

    private ProjectComparator(Project psrc, Project pdst, int revision) {
        this.psrc = psrc;
        this.pdst = pdst;
        this.revision = revision;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @JsonIgnore
    public File getProjectHome() {
        return this.project.projectHome;
    }

    @JsonIgnore
    public boolean isDifference() {
        return psrc != null && pdst != null;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return nchanges == 0;
    }

    @JsonIgnore
    public List<String> getAddedModules() {
        return getModules(RevisionStatus.ADDED);
    }

    @JsonIgnore
    public List<String> getChangedModules() {
        return getModules(RevisionStatus.CHANGED);
    }

    @JsonIgnore
    public List<String> getDeletedModules() {
        return getModules(RevisionStatus.DELETED);
    }

    @JsonIgnore
    private List<String> getModules(RevisionStatus status) {
        List<String> modules = new ArrayList<>();
        for (Map.Entry<String, ModuleInfo> mi : this.modules.entrySet()) {
            if (mi.getValue().status == status)
                modules.add(mi.getKey());
        }
        return modules;
    }

    @JsonIgnore
    public List<ModuleSource> getAddedSources() {
        return getSources(RevisionStatus.ADDED);
    }

    @JsonIgnore
    public List<ModuleSource> getDeletedSources() {
        return getSources(RevisionStatus.DELETED);
    }

    @JsonIgnore
    public List<ModuleSource> getChangedSources() {
        return getSources(RevisionStatus.CHANGED);
    }

    @JsonIgnore
    private List<ModuleSource> getSources(RevisionStatus status) {
        List<ModuleSource> sources = new ArrayList<>();
        for (ModuleInfo mi : this.modules.values()) {
            for (SourceInfo si : mi.sources.values())
                if (si.status == status)
                    sources.add(new ModuleSource(mi.name, si.name));
        }
        return sources;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @JsonIgnore
    public List<File> getAddedSourceFiles() {
        return getSourceFiles(RevisionStatus.ADDED);
    }

    @JsonIgnore
    public List<File> getDeletedSourceFiles() {
        return getSourceFiles(RevisionStatus.DELETED);
    }

    @JsonIgnore
    private List<File> getSourceFiles(RevisionStatus status) {
        List<File> files = new ArrayList<>();
        for (ModuleInfo mi : this.modules.values()) {
            for (SourceInfo si : mi.sources.values())
                if (si.status == status)
                    files.add(si.file);
        }
        return files;
    }

    @JsonIgnore
    public Set<File> getAddedLibraryFiles() {
        Set<File> files = new HashSet<>();

        for (LibraryInfo li : this.libraries.values()) {
            if (li.status == RevisionStatus.ADDED)
                files.addAll(li.files);
        }

        return files;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private void compare() {
        // save the information about the project
        setProjectInfo();

        // if psrc is null, pdst is the first revision
        if (psrc == null) {
            nchanges += addModules(pdst.getModules(), RevisionStatus.ADDED);
            nchanges += addLibraries(pdst.getLibraries(), RevisionStatus.ADDED);
            return;
        }

        // compare the project revisions
        // check added/removed methods
        Set<Module> addedModules   = SetUtils.difference(pdst.getModules(), psrc.getModules());
        Set<Module> deletedModules = SetUtils.difference(psrc.getModules(), pdst.getModules());
        Set<Module> commonModules  = SetUtils.intersection(pdst.getModules(), psrc.getModules());

        nchanges += addModules(addedModules, RevisionStatus.ADDED);
        nchanges += addModules(deletedModules, RevisionStatus.DELETED);

        // check added libraries
        Set<Library> addedLibraries = SetUtils.difference(pdst.getLibraries(), psrc.getLibraries());
        addLibraries(addedLibraries, RevisionStatus.ADDED);

        // for each module check the sources
        for (Module module : commonModules) {
            Name moduleName = module.getName();
            Module msrc = psrc.getModules().getModule(moduleName);
            Module mdst = pdst.getModules().getModule(moduleName);

            Set<Source> addedSources   = SetUtils.difference(mdst.getSources(), msrc.getSources());
            Set<Source> deletedSources = SetUtils.difference(msrc.getSources(), mdst.getSources());
            Set<Source> commonSources  = SetUtils.intersection(mdst.getSources(), msrc.getSources());
            Set<Source> changedSources = new HashSet<>();

            nchanges += addSources(module, addedSources, RevisionStatus.ADDED);
            nchanges += addSources(module, deletedSources, RevisionStatus.DELETED);

            // check for changed sources
            for (Source source : commonSources) {
                Name sourceName = source.getName();
                Source ssrc = msrc.getSources().getSource(sourceName);
                Source sdst = mdst.getSources().getSource(sourceName);

                if (!ssrc.getDigest().equals(sdst.getDigest()))
                    changedSources.add(sdst);
            }

            nchanges += addSources(module, changedSources, RevisionStatus.CHANGED);
        }

    }

    // ----------------------------------------------------------------------

    private void setProjectInfo() {
        // psrc CAN BE null
        // pdst MUST NOT BE null
        // check that all projects have the SAME project home
        if (psrc != null && pdst != null && !psrc.getProjectHome().equals(pdst.getProjectHome()))
            throw new InvalidParameterException("pdst", "Project revisions don't have the same project home");
        if (pdst == null)
            throw new InvalidParameterException("pdst", "Project can be not null");

        this.project = new ProjectInfo(pdst, revision);
    }

    private int addModules(Collection<Module> modules, RevisionStatus status) {
        modules.forEach(module -> {
            this.modules.put(module.getName().getFullName(), new ModuleInfo(module, status));
        });

        return this.modules.size();
    }

    private int addSources(Module module, Collection<Source> sources, RevisionStatus status) {
        String moduleName = module.getName().getFullName();
        if (!modules.containsKey(moduleName))
            modules.put(moduleName, new ModuleInfo(module, RevisionStatus.CHANGED));

        ModuleInfo minfo = modules.get(moduleName);
        return minfo.addSources(sources, status);
    }

    private int addLibraries(Collection<Library> libs, RevisionStatus status) {
        libs.forEach(library -> {
            libraries.put(library.getName().getFullName(), new LibraryInfo(library, status));
        });
        return libraries.size();
    }

    // ----------------------------------------------------------------------
    // IO
    // ----------------------------------------------------------------------

    public void save(File jsonFile) {
        JSONUtils.save(jsonFile, this);
    }

    public static ProjectComparator load(File jsonFile) throws IOException {
        return JSONUtils.load(jsonFile, ProjectComparator.class);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
