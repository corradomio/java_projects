package jext.sourcecode.project.maven;

import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.util.BaseModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MavenModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private MavenPom pom;
    private int maxDepth = 3;

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
        pom.getDependencies()
            .stream()
            .map(coords -> getProject().getModule(coords.toString()))
            .filter(Objects::nonNull)
            .forEach(dmodule -> {
                dmodules.add(dmodule);
            });
        return dmodules;
    }

    @Override
    public Set<String> getMavenRepositories() {
        Set<String> repos = pom.getRepositories();
        repos.add("https://repo.maven.apache.org/maven2");
        return repos;
    }

    @Override
    protected void collectMavenLibraries(Set<Library> collectedLibraries) {

        MavenDownloader md = project.getLibraryDownloader();

        List<MavenCoords> coordList = getDirectCoordsDependencies();

        Set<MavenCoords> setCoords = new HashSet<>(coordList);

        coordList.forEach(dcoords -> {
            List<MavenCoords> ddcoords = md.getDependencies(dcoords, maxDepth);
            setCoords.addAll(ddcoords);
        });

        // md.checkArtifacts(new ArrayList<>(setCoords));

        setCoords.stream()
            .map(coords -> new MavenLibrary(coords, md, project))
            .forEach(collectedLibraries::add);
    }

    // @Override
    // protected List<Library> getMavenLibraries() {
    //
    //     MavenDownloader md = project.getLibraryDownloader();
    //
    //     List<MavenCoords> coordList = getDirectCoordsDependencies();
    //     // md.checkArtifacts(coordList);
    //
    //     Set<MavenCoords> setCoords = new HashSet<>(coordList);
    //
    //     coordList.forEach(dcoords -> {
    //         List<MavenCoords> ddcoords = md.getDependencies(dcoords, maxDepth);
    //         setCoords.addAll(ddcoords);
    //     });
    //
    //     // md.checkArtifacts(new ArrayList<>(setCoords));
    //
    //     return setCoords.stream()
    //         .map(coords -> new MavenLibrary(coords, md, project))
    //         .collect(Collectors.toList());
    // }

    private List<MavenCoords> getDirectCoordsDependencies() {

        MavenDownloader md = project.getLibraryDownloader();

        return pom.getDependencyCoords()
            .stream()
            .filter(this::isLibrary)
            .map(md::getVersioned)
            .collect(Collectors.toList());
    }

    private boolean isLibrary(MavenCoords coords) {
        return project.getModule(coords.toString()) == null;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
