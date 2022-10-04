package jext.sourcecode.project.java.maven;

import jext.java.JavaUtils;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.java.JavaBaseModule;
import jext.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MavenModule extends JavaBaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final MavenPom pom;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    MavenModule(File moduleHome, Project project) {
        super(moduleHome, project);
        this.pom = new MavenPom(moduleHome);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getMavenCoords() {
        return pom.getCoords().toString();
    }

    // ----------------------------------------------------------------------

    @Override
    public Library getRuntimeLibrary() {
        // try with the current configuration
        String javaVersion = pom.getJavaVersion();
        if (StringUtils.isEmpty(javaVersion))
            return super.getRuntimeLibrary();

        String runtimeName = JavaUtils.toJDK(javaVersion);
        return super.getRuntimeLibrary(runtimeName);
    }

    // ----------------------------------------------------------------------

    @Override
    public List<Module> getDependencies() {
        if (dependencies != null)
            return dependencies;

        List<Module> dmodules = getMavenDependencies();
        Set<Module> orderedDeps = getUsedTypesDependencies();
        orderedDeps.addAll(dmodules);

        this.dependencies = orderedDeps.isEmpty()
            ? Collections.emptyList()
            : new ArrayList<>(orderedDeps);

        return this.dependencies;
    }

    private List<Module> getMavenDependencies() {
        List<Module> dmodules = new ArrayList<>();
        pom.getDependencies().stream()
            .map(coords -> project.getModules().getModule(coords.toString()))
            .filter(Objects::nonNull)
            .forEach(dmodules::add)
        ;
        return dmodules;
    }

    // ----------------------------------------------------------------------

    @Override
    public Set<String> getMavenRepositories() {
        Set<String> repos = pom.getRepositories();
        repos.add("https://repo.maven.apache.org/maven2");
        return repos;
    }

    @Override
    protected void collectMavenLibraries(Set<Library> collectedLibraries) {

        MavenDownloader downloader = (MavenDownloader) project.getLibraryDownloader();

        pom.getDependencyCoords()
                .stream()
                .filter(this::isLibrary)        // skip maven coords that are Maven modules
                .map(downloader::getVersioned)  // force the library version
                .map(coords -> new MavenLibrary(coords, downloader).project(project))
                .forEach(collectedLibraries::add);

        // List<MavenCoords> coordList = getDirectCoordsDependencies();
        //
        // Set<MavenCoords> setCoords = new HashSet<>(coordList);
        //
        // Note: it is not responsibility of the module to resolve
        // the library dependencies.
        // It is better to delegate this part to
        //
        //      Project.getLibraries() -> resolved list of libraries
        //
        // coordList.forEach(dcoords -> {
        //     Set<MavenCoords> ddcoords = md.getDependencies(dcoords, maxDepth);
        //     setCoords.addAll(ddcoords);
        // });
        //
        // coordList.stream()
        //     .map(coords -> new MavenLibrary(coords, md).project(project))
        //     .forEach(collectedLibraries::add);
    }

    // private List<MavenCoords> getDirectCoordsDependencies() {
    //
    //     MavenDownloader downloader = (MavenDownloader) project.getLibraryDownloader();
    //
    //     return pom.getDependencyCoords()
    //         .stream()
    //         .filter(this::isLibrary)        // skip maven coords related to modules
    //         .map(downloader::getVersioned)  // force the library version
    //         .collect(Collectors.toList());
    // }

    private boolean isLibrary(MavenCoords coords) {
        return project.getModules().getModule(coords.toString()) == null;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
