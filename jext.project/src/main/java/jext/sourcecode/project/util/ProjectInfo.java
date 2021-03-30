package jext.sourcecode.project.util;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.util.FileUtils;
import jext.util.MapUtils;
import jext.util.SetUtils;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectInfo {

    public static Map<String, Object> analyze(Project project) {
        return new ProjectInfo(project).analyze();
    }

    private Project project;
    private Map<String, Object> pinfo = new LinkedHashMap<>();

    private ProjectInfo(Project project) {
        this.project = project;
    }

    private Map<String, Object> analyze() {
        pinfo.put("name", project.getName().getName());
        pinfo.put("fullname", project.getName().getFullName());
        pinfo.put("id", project.getId());
        pinfo.put("type", project.getProjectType());
        pinfo.put("home", FileUtils.getAbsolutePath(project.getProjectHome()));
        pinfo.put("properties", project.getProperties());
        
        // this can require A LOT OF TIME
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
            .stream()
            .map(module -> {
                    Map<String, Object> minfo = new LinkedHashMap<>();
                    analyze(module, minfo);
                    return minfo;
                })
            .collect(Collectors.toList()));

        pinfo.put("libraries", libraries
            .stream()
            .map(library -> {
                Map<String, Object> linfo = new LinkedHashMap<>();
                analyze(library, linfo);
                return linfo;
            })
            .collect(Collectors.toList()));

        pinfo.put("runtimeLibraries", rtLibraries
            .stream()
            .map(library -> {
                Map<String, Object> linfo = new LinkedHashMap<>();
                analyze(library, linfo);
                return linfo;
            })
            .collect(Collectors.toList()));

        pinfo.put("mavenRepositories", mavenRepos);

        return pinfo;
    }

    private void analyze(Module m, Map<String, Object> minfo) {

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
            .stream()
            .map(type -> type.getName().getFullName())
            .collect(Collectors.toList()));
        minfo.put("usedTypes", m.getUsedTypes()
            .stream()
            .map(type -> type.getName().getFullName())
            .collect(Collectors.toList()));
    }

    private void analyze(Library l, Map<String, Object> linfo) {
        linfo.put("name", l.getName().getName());
        linfo.put("fullname", l.getName().getFullName());
        linfo.put("id", l.getId());
        linfo.put("libraryType", l.getLibraryType());
        linfo.put("files", l.getFiles()
            .stream()
            .map(FileUtils::getAbsolutePath)
            .collect(Collectors.toList()));
    }
}
