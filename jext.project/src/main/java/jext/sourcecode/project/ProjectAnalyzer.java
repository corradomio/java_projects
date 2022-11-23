package jext.sourcecode.project;

import jext.logging.Logger;
import jext.sourcecode.project.util.ProjectUtils;
import jext.util.FileUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        pinfo.put("repository", project.getName().getParentName());
        pinfo.put("fullname", project.getName().getFullName());
        pinfo.put("id", project.getId());
        // pinfo.put("type", project.getProjectType());
        pinfo.put("projectHome", FileUtils.getAbsolutePath(project.getProjectHome()));
        pinfo.put("properties", project.getProperties());

        List<Module> modules = project.getModules();
        LibrarySet libraries = project.getLibraries();
        Set<Library> usedLibraries = libraries.getUsedLibraries();
        Set<Library> unusedLibraries = libraries.getUnusedLibraries();
        Set<Library> rtLibraries = ProjectUtils.getRuntimeLibraries(project);
        Set<String> mavenRepos = ProjectUtils.getMavenRepositories(project);
        Set<LibraryRepository> libRepos = ProjectUtils.getLibraryRepositories(project);

        pinfo.put("counts", MapUtils.asMap(
            "modules", modules.size(),
            "libraries", usedLibraries.size(),
            "unusedLibraries", unusedLibraries.size(),
            "runtimeLibraries", rtLibraries.size(),
            "sources", project.getSources().size(),
            "mavenRepositories", mavenRepos.size(),
            "libraryRepositories", libRepos.size()

        ));

        List<Map<String, Object>> minfoList = modules
            .parallelStream()
            .map(this::analyzeModule)
            .collect(Collectors.toList());

        Map<String, Map<String, Object>> minfoMap = new HashMap<>();
        minfoList.forEach(minfo -> {
            String fullname = MapUtils.get(minfo, "fullname");
            if (minfoMap.containsKey(fullname))
                Logger.getLogger(ProjectAnalyzer.class).errorf("Duplicated module %s", fullname);

            minfoMap.put(fullname, minfo);
        });

        // pinfo.put("modules", modules
        //     .parallelStream()
        //     .map(this::analyzeModule)
        //     .collect(Collectors.toMap(minfo -> minfo.get("fullname"), minfo -> minfo)));
        pinfo.put("modules", minfoMap);

        pinfo.put("libraries", usedLibraries
            .parallelStream()
            .map(this::analyzeLibrary)
            .collect(Collectors.toMap(linfo -> linfo.get("fullname"), linfo -> linfo)));

        pinfo.put("unusedLibraries", unusedLibraries
            .parallelStream()
            .map(this::analyzeLibrary)
            .collect(Collectors.toList()));

        pinfo.put("runtimeLibraries", rtLibraries
            .stream()
            .map(this::analyzeLibrary)
            .collect(Collectors.toList()));

        pinfo.put("mavenRepositories", mavenRepos);

        pinfo.put("libraryRepositories", libRepos
            .stream()
            .map(this::analyzeLibrepo)
            .collect(Collectors.toList()));

        return pinfo;
    }

    private Map<String, Object> analyzeModule(Module m) {
        File moduleHome = m.getModuleHome();
        Map<String, Object> minfo = new HashMap<>();

        Set<Library> declaredLibraries = m.getDeclaredLibraries();

        // general information

        minfo.put("name", m.getName().getName());
        minfo.put("fullname", m.getName().getFullName());
        minfo.put("id", m.getId());
        // minfo.put("moduleHome", FileUtils.getAbsolutePath(m.getModuleHome()));
        minfo.put("path", m.getPath());
        minfo.put("properties", m.getProperties());
        minfo.put("digest", m.getDigest());
        minfo.put("refId", m.getRefId());
        minfo.put("type", "module");

        // module dependencies
        minfo.put("dependencies", m.getDependencies()
            .stream()
            .filter(ProjectAnalyzer::notNull)
            .map(dmodule -> dmodule.getName().getFullName())
            .collect(Collectors.toList()));

        // object counts
        minfo.put("counts", MapUtils.asMap(
            // "resources", m.getResources().size(),
            "sources", m.getSources().size(),
            "sourceRoots", m.getSources().getSourceRoots().size(),
            "dependencies", m.getDependencies().size(),
            // "libraries", m.getLibraries().size(),
            "declaredLibraries", declaredLibraries.size(),
            "types", m.getTypes().size(),
            "usedTypes", m.getUsedTypes().size()
        ));

        // source roots
        minfo.put("sourceRoots", m.getSources().getSourceRoots());

        minfo.put("sources", m.getSources()
            .parallelStream()
            .map(this::analyzeSource)
            .collect(Collectors.toMap(sinfo -> (String)sinfo.get("fullname"), sinfo -> sinfo)));

        // runtime libraries
        minfo.put("runtimeLibrary", m.getRuntimeLibrary().getName().getFullName());

        // libraries

        minfo.put("declaredLibraries", m.getDeclaredLibraries()
            .stream()
            .map(library -> library.getName().getFullName())
            .collect(Collectors.toList()));

        minfo.put("localLibraries", m.getLocalLibraries()
                .stream()
                .map(library -> library.getName().getFullName())
                .collect(Collectors.toList()));

        return minfo;
    }

    private static boolean notNull(Module m) {
        if (m == null)
            return false;
        else
            return true;
    }

    private Map<String, Object> analyzeLibrary(Library l) {
        return MapUtils.asTreeMap(
            "name", l.getName().getName(),
            "fullname", l.getName().getFullName(),
            "id", l.getId(),
            // "digest", l.getDigest(),
            "libraryType", l.getLibraryType(),
            "language", l.getLanguage(),
            "version", l.getVersion(),
            "valid", l.isValid(),
            "file", FileUtils.getAbsolutePath(l.getFile()),
            "files", FileUtils.getAbsolutePaths(l.getFiles()),
            "licenseType", l.getLibraryLicense().getType(),
            "licenseUrl", l.getLibraryLicense().getUrl(),
            "type", "library"
        );
    }

    private Map<String, Object> analyzeLibrepo(LibraryRepository lr) {
        return MapUtils.asTreeMap(
            "id", lr.getId(),
            "name", lr.getName(),
            "repositoryType", lr.getRepositoryType(),
            "url", lr.getUrl(),
            "type", "librepo"
        );
    }

    private Map<String, Object> analyzeSource(Source s) {

        Map<String, Object> info = MapUtils.asMap(
            "name", s.getName().getName()
            ,"fullname", s.getName().getFullName()
            ,"id", s.getId()
            ,"path", s.getPath()
            ,"digest", s.getDigest()
            ,"language", s.getLanguage()
            ,"mimeType", s.getMimeType()
            ,"sourceInfo", s.getSourceInfo()
            ,"types", s.getTypes().stream()
                .map(type -> type.getName().getFullName())
                .collect(Collectors.toList())
            ,"type", "source"
        );

        Optional<String> sourceRoot = s.getSourceRoot();
        sourceRoot.ifPresent(value -> info.put("sourceRoot", value));

        return info;
    }

    // ----------------------------------------------------------------------
    // Analyze sources
    // ----------------------------------------------------------------------

    // private SourceInfo  analyzeSources() {
    //     // create the main map
    //     ModulesInfo minfo = new ModulesInfo();
    //     minfo.init();
    //     project.getSources()
    //         .parallelStream()
    //         .forEach(source -> analyzeSource(minfo, source));
    //     return minfo;
    // }

    // private void analyzeSource(ModulesInfo minfo, Source source) {
    //     minfo.add(source.getSourceInfo());
    //     String modulePath = source.getModule().getName().getFullName();
    //     String sourcePath = source.getName().getFullName();
    //     long digest = source.getDigest();
    //     minfo.addDigest(modulePath, sourcePath, digest);
    // }

}
