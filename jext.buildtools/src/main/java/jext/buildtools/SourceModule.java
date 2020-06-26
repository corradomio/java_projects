package jext.buildtools;

import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenDownloader;
import jext.buildtools.util.ResourceFile;
import jext.buildtools.util.SourceCode;
import jext.logging.Logger;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class SourceModule implements Module {

    private Logger logger = Logger.getLogger(SourceModule.class);

    private final SourceProject project;
    private File moduleDir;
    private final Name name;
    private List<ModuleAnalyzer> analizers = new ArrayList<>();
    private Properties properties = new Properties();

    SourceModule(Name name, SourceProject project) {
        this.project = project;
        this.name = name;
        project.getAnalyzers().forEach(prj -> {
            ModuleAnalyzer analyzer = prj.getModule(this.name.toString());
            if (analyzer == null) return;
            analizers.add(analyzer);
            if (moduleDir == null)
                moduleDir = analyzer.getModuleDir();
            else if (!moduleDir.equals(analyzer.getModuleDir())) {
                logger.errorf("Conflict in module directory between modules defined using different project types:\n" +
                    "  %s != %s", moduleDir.getAbsolutePath(), analyzer.getModuleDir().getAbsolutePath());
            }
        });
    }

    public Project getProject() {
        return project;
    }

    public Name getName() {
        return name;
    }

    public File getModuleDir() {
        return moduleDir;
    }

    public boolean isValid() {
        return moduleDir != null && moduleDir.exists() && moduleDir.isDirectory();
    }

    public List<Name> getModuleDependencies() {
        if (!isValid())
            return Collections.emptyList();

        Set<Name> dmodules = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            dmodules.addAll(analyzer.getModuleDependencies());

        return new ArrayList<>(dmodules);
    }

    public List<MavenCoords> getMavenDependencies() {
        if (!isValid())
            return Collections.emptyList();

        Set<MavenCoords> dmaven = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            dmaven.addAll(analyzer.getMavenLibraries());

        return new ArrayList<>(dmaven);
    }

    public List<File> getLocalDependencies() {
        if (!isValid())
            return Collections.emptyList();

        Set<File> dlocal = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            dlocal.addAll(analyzer.getLocalLibraries());

        return new ArrayList<>(dlocal);
    }

    // public List<File> getSources() {
    //     Set<File> sources = new HashSet<>();
    //     for(ModuleAnalyzer analyzer : analizers)
    //         sources.addAll(analyzer.getSources());
    //
    //     return new ArrayList<>(sources);
    // }

    // public List<File> getResources() {
    //     Set<File> resources = new HashSet<>();
    //     for(ModuleAnalyzer analyzer : analizers)
    //         resources.addAll(analyzer.getResources());
    //
    //     return new ArrayList<>(resources);
    // }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        ModuleAnalyzer that = (ModuleAnalyzer) obj;
        return getName().equals(that.getName());
    }

    @Override
    public String toString() {
        return String.format("SourceModule[%s]", getName());
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    // @Override
    public String getId() {
        return getName().toString();
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getPath() {
        return FileUtils.getAbsolutePath(moduleDir);
    }

    @Override
    public List<Module> getDependencies() {
        return getDependencies(true);
    }

    @Override
    public List<Module> getDependencies(boolean recursive) {
        if (!recursive)
            return getModuleDependencies()
            .stream()
            .map(project::getModule)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        Set<Name> dmodules = new HashSet<>();
        Queue<Name> toVisit = new LinkedList<>(getModuleDependencies());
        while (!toVisit.isEmpty()) {
            Name dmodule = toVisit.remove();
            if (dmodules.contains(dmodule))
                continue;

            dmodules.add(dmodule);
            toVisit.addAll((((SourceModule)project.getModule(dmodule)).getModuleDependencies()));
        }

        return dmodules
            .stream()
            .map(project::getModule)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public List<Source> getSources() {

        Set<File> sources = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            sources.addAll(analyzer.getSources());

        return sources.stream()
            .map(file -> SourceCode.newSource(file, this))
            .filter(Source::isValid)
            .collect(Collectors.toList());
    }

    @Override
    public List<Resource> getResources() {
        Set<File> resources = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            resources.addAll(analyzer.getResources());

        return resources.stream()
            .map(file -> new ResourceFile(file, this))
            .collect(Collectors.toList());
    }

    // @Override
    // public Source getSource(String sourceName) {
    //     return null;
    // }

    @Override
    public List<Library> getLibraries() {
        Set<MavenCoords> mavenLibs = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            mavenLibs.addAll(analyzer.getMavenLibraries());

        Set<File> localLibs = new HashSet<>();
        for(ModuleAnalyzer analyzer : analizers)
            localLibs.addAll(analyzer.getLocalLibraries());

        // MavenDownloader downloader = getProject().getDownloader();

        List<Library> libraries = new ArrayList<>();
        // mavenLibs.forEach(coords -> {
        //     libraries.add(new MavenLibrary(coords, downloader));
        // });

        // localLibs.forEach(file -> {
        //     libraries.add(new JarLibrary(file));
        // });

        return libraries;
    }

    // @Override
    public int compareTo(Named that) {
        return getName().compareTo(that.getName());
    }
}
