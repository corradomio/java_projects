package jext.sourcecode.project;

import jext.sourcecode.project.util.ProjectUtils;
import jext.util.FileUtils;
import jext.util.MapUtils;
import jext.util.SetUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectAnalyzer {

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static void analyzeProject(Project project, File jsonFile) throws IOException {
        ProjectInfo pinfo = analyzeProject(project);
        pinfo.save(jsonFile);
    }

    public static void analyzeSources(Project project, File jsonFile) throws IOException {
        SourceInfo sinfo = analyzeSources(project);
        sinfo.save(jsonFile);
    }

    // public static void analyzeImplementation(Project project, File jsonFile) throws IOException {
    //     SourceHash shash = analyzeImplementation(project);
    //     shash.save(jsonFile);
    // }

    // -----------------------------------------------------------------------

    public static ProjectInfo analyzeProject(Project project) {
        // this can require A LOT OF TIME
        // we have observed this problem with Gradle projects based on
        // Java 11 (we are using Java 8), because Gradle toolkit
        // try to start a Gradle instance for each module, but
        // for some incompatibilities, the instance doesn't start

        ProjectAnalyzer analyzer = new ProjectAnalyzer(project);
        ProjectInfo pinfo = analyzer.analyzeProject();
        return pinfo;
    }

    public static SourceInfo analyzeSources(Project project) {
        ProjectAnalyzer analyzer = new ProjectAnalyzer(project);
        SourceInfo sinfo = analyzer.analyzeSources();
        return sinfo;
    }

    // public static SourceHash analyzeImplementation(Project project) {
    //     ProjectAnalyzer analyzer = new ProjectAnalyzer(project);
    //     SourceHash shash = analyzer.analyzeImplementation();
    //     return shash;
    // }

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    private Project project;
    private ProjectInfo pinfo = new ProjectInfo();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private ProjectAnalyzer(Project project) {
        this.project = project;
    }

    // ----------------------------------------------------------------------
    // Analyze project
    // ----------------------------------------------------------------------

    private ProjectInfo analyzeProject() {
        pinfo.init();
        pinfo.put("name", project.getName().getName());
        pinfo.put("fullname", project.getName().getFullName());
        pinfo.put("id", project.getId());
        pinfo.put("type", project.getProjectType());
        pinfo.put("home", FileUtils.getAbsolutePath(project.getProjectHome()));
        pinfo.put("properties", project.getProperties());

        List<Module> modules = project.getModules();
        Set<Library> libraries = project.getLibraries();
        Set<Library> rtLibraries = ProjectUtils.getRuntimeLibraries(project);
        Set<String> mavenRepos = ProjectUtils.getMavenRepositories(project);

        pinfo.put("counts", MapUtils.asMap(
            "modules", modules.size(),
            "libraries", libraries.size(),
            "runtimeLibraries", rtLibraries.size(),
            "sources", ProjectUtils.getSources(project).size(),
            "mavenRepositories", mavenRepos.size()
        ));

        pinfo.put("modules", modules
            .parallelStream()
            .map(this::analyzeModule)
            .collect(Collectors.toList()));

        pinfo.put("libraries", libraries
            .parallelStream()
            .map(this::analyzeLibrary)
            .collect(Collectors.toList()));

        pinfo.put("runtimeLibraries", rtLibraries
            .stream()
            .map(this::analyzeLibrary)
            .collect(Collectors.toList()));

        pinfo.put("mavenRepositories", mavenRepos);

        return pinfo;
    }

    private Map<String, Object> analyzeModule(Module m) {
        Map<String, Object> minfo = new LinkedHashMap<>();

        // compute the list of unique libraries in this module
        Set<Library> definedLibraries = SetUtils.differenceOrdered(
            new HashSet<>(m.getDeclaredLibraries()),
            new HashSet<>(m.getLibraries())
        );

        minfo.put("name", m.getName().getName());
        minfo.put("fullname", m.getName().getFullName());
        minfo.put("id", m.getId());
        minfo.put("moduleHome", FileUtils.getAbsolutePath(m.getModuleHome()));
        minfo.put("path", m.getPath());
        minfo.put("properties", m.getProperties());
        minfo.put("sourceRoots", m.getSourceRoots()
            .stream()
            .map(FileUtils::getAbsolutePath)
            .collect(Collectors.toList()));
        minfo.put("dependencies", m.getDependencies()
            .stream()
            .map(dmodule -> dmodule.getName().getFullName())
            .collect(Collectors.toList()));
        minfo.put("counts", MapUtils.asMap(
            "resources", m.getResources().size(),
            "sources", m.getSources().size(),
            "sourceRoots", m.getSourceRoots().size(),
            "dependencies", m.getDependencies().size(),
            "libraries", m.getLibraries().size(),
            "definedLibraries", definedLibraries.size(),
            "types", m.getTypes().size(),
            "usedTypes", m.getUsedTypes().size()
        ));

        // libraries

        minfo.put("runtimeLibrary", m.getRuntimeLibrary().getName().getFullName());
        minfo.put("libraries", m.getLibraries()
            .stream()
            .map(library -> library.getName().getFullName())
            .collect(Collectors.toList()));
        minfo.put("definedLibraries",definedLibraries
            .stream()
            .map(library -> library.getName().getFullName())
            .collect(Collectors.toList()));

        // types

        minfo.put("types", m.getTypes()
            .parallelStream()
            .map(type -> type.getName().getFullName())
            .collect(Collectors.toList()));
        minfo.put("usedTypes", m.getUsedTypes()
            .parallelStream()
            .map(type -> type.getName().getFullName())
            .collect(Collectors.toList()));

        return minfo;
    }

    private Map<String, Object> analyzeLibrary(Library l) {
        Map<String, Object> linfo = new LinkedHashMap<>();

        linfo.put("name", l.getName().getName());
        linfo.put("fullname", l.getName().getFullName());
        linfo.put("id", l.getId());
        linfo.put("libraryType", l.getLibraryType());
        linfo.put("files", l.getFiles()
            .stream()
            .map(FileUtils::getAbsolutePath)
            .collect(Collectors.toList()));

        return linfo;
    }

    // ----------------------------------------------------------------------
    // Analyze sources
    // ----------------------------------------------------------------------

    private SourceInfo  analyzeSources() {
        // create the main map
        SourceInfo sinfo = new SourceInfo();
        sinfo.init();
        ProjectUtils.getSources(project)
            .parallelStream()
            .forEach(source -> analyzeSource(sinfo, source));
        return sinfo;
    }

    private void analyzeSource(SourceInfo sinfo, Source source) {
        sinfo.add(source.getSourceInfo());
        String moduleName = source.getModule().getName().getFullName();
        String sourceName = source.getName().getFullName();
        String digest = source.getDigest();
        sinfo.addDigest(moduleName, sourceName, digest);
    }

    // ----------------------------------------------------------------------
    // Analyze source hashes
    // ----------------------------------------------------------------------

    // private SourceHash analyzeImplementation() {
    //     SourceHash shash = new SourceHash();
    //     ProjectUtils.getSources(project)
    //         .parallelStream()
    //         .forEach(source -> analyzeImplementation(shash, source));
    //     return shash;
    // }

    // private void analyzeImplementation(SourceHash shash, Source source) {
    //     String moduleName = source.getModule().getName().getFullName();
    //     String sourceName = source.getName().getFullName();
    //     String digest = source.getDigest();
    //     shash.addHash(moduleName, sourceName, digest);
    // }
}
