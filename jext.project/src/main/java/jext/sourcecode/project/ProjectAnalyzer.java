package jext.sourcecode.project;

import jext.sourcecode.project.util.ModulesInfo;
import jext.sourcecode.project.util.ProjectUtils;
import jext.sourcecode.project.util.SourceInfo;
import jext.util.FileUtils;
import jext.util.MapUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
        pinfo.put("type", project.getProjectType());
        // pinfo.put("projectHome", FileUtils.getAbsolutePath(project.getProjectHome()));
        pinfo.put("properties", project.getProperties());

        List<Module> modules = project.getModules();
        LibrarySet libraries = project.getLibraries();
        Set<Library> usedLibraries = libraries.getUsedLibraries();
        Set<Library> unusedLibraries = libraries.getUnusedLibraries();
        Set<Library> rtLibraries = ProjectUtils.getRuntimeLibraries(project);
        Set<String> mavenRepos = ProjectUtils.getMavenRepositories(project);

        pinfo.put("counts", MapUtils.asMap(
            "modules", modules.size(),
            "libraries", usedLibraries.size(),
            "unusedLibraries", unusedLibraries.size(),
            "runtimeLibraries", rtLibraries.size(),
            "sources", project.getSources().size(),
            "mavenRepositories", mavenRepos.size()
        ));

        pinfo.put("modules", modules
            .parallelStream()
            .map(this::analyzeModule)
            .collect(Collectors.toMap(minfo -> minfo.get("fullname"), minfo -> minfo)));

        pinfo.put("libraries", usedLibraries
            .parallelStream()
            .map(this::analyzeLibrary)
            .collect(Collectors.toList()));

        pinfo.put("unusedLibraries", unusedLibraries
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

        // sources
        // Map<String, Object> sinfos = new TreeMap<>();
        // for (Source source : m.getSources())
        //     sinfos.put(source.getName().getFullName(),
        //         analyzeSource(source));

        minfo.put("sources", m.getSources()
            .parallelStream()
            .map(this::analyzeSource)
            .collect(Collectors.toMap(sinfo -> (String)sinfo.get("fullname"), sinfo -> sinfo)));

        // runtime libraries
        minfo.put("runtimeLibrary", m.getRuntimeLibrary().getName().getFullName());

        // libraries

        // minfo.put("libraries", m.getLibraries()
        //     .stream()
        //     .map(library -> library.getName().getFullName())
        //     .collect(Collectors.toList()));
        minfo.put("declaredLibraries", m.getDeclaredLibraries()
            .stream()
            .map(library -> library.getName().getFullName())
            .collect(Collectors.toList()));

        // types
        // minfo.put("types", m.getTypes()
        //     .parallelStream()
        //     .map(type -> type.getName().getFullName())
        //     .collect(Collectors.toList()));
        // minfo.put("usedTypes", m.getUsedTypes()
        //     .parallelStream()
        //     .map(type -> type.getName().getFullName())
        //     .collect(Collectors.toList()));

        return minfo;
    }

    private static boolean notNull(Module m) {
        if (m == null)
            return false;
        else
            return true;
    }

    private Map<String, Object> analyzeLibrary(Library l) {
        Map<String, Object> linfo = MapUtils.asTreeMap(
            "name", l.getName().getName(),
            "fullname", l.getName().getFullName(),
            "id", l.getId(),
            "digest", l.getDigest(),
            "libraryType", l.getLibraryType(),
            "version", l.getVersion(),
            "valid", l.isValid(),
            "file", FileUtils.getAbsolutePath(l.getFile()),
            "files", FileUtils.getAbsolutePaths(l.getFiles())
        );

        return linfo;
    }

    private Map<String, Object> analyzeSource(Source s) {
        String sourceRoot;
        if (s.getSourceRoot().isPresent())
            sourceRoot = s.getSourceRoot().get();
        else
            sourceRoot = null;

        return MapUtils.asMap(
            "name", s.getName().getName()
            ,"fullname", s.getName().getFullName()
            ,"id", s.getId()
            ,"path", s.getPath()
            ,"digest", s.getDigest()
            ,"language", s.getLanguage()
            ,"mimeType", s.getMimeType()
            ,"sourceInfo", s.getSourceInfo()
            ,"sourceRoot", sourceRoot
            ,"types", s.getTypes().stream()
                .map(type -> type.getName().getFullName())
                .collect(Collectors.toList())
        );
    }

    // ----------------------------------------------------------------------
    // Analyze sources
    // ----------------------------------------------------------------------

    private SourceInfo  analyzeSources() {
        // create the main map
        ModulesInfo minfo = new ModulesInfo();
        minfo.init();
        project.getSources()
            .parallelStream()
            .forEach(source -> analyzeSource(minfo, source));
        return minfo;
    }

    private void analyzeSource(ModulesInfo minfo, Source source) {
        minfo.add(source.getSourceInfo());
        String modulePath = source.getModule().getName().getFullName();
        String sourcePath = source.getName().getFullName();
        long digest = source.getDigest();
        minfo.addDigest(modulePath, sourcePath, digest);
    }

}
