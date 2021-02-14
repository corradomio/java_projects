package jext.sourcecode.project.maven;

import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;
import jext.maven.MavenPom;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

    public MavenModule(File moduleHome, Project project) {
        super(moduleHome, project);
        this.pom = new MavenPom(moduleHome);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // Override 'BaseModule::getId()' to use the 'module Maven coords'
    // @Override
    // public String getId() {
    //
    //     if (pom.exists())
    //         return pom.getCoords().toString();
    //     else
    //         return super.getId();
    // }

    public String getMavenCoords() {
        return pom.getCoords().toString();
    }

    // ----------------------------------------------------------------------

    @Override
    protected List<Module> getDependencies() {
        //
        // Override 'BaseModule::getDependencies()' to reorder the
        // dependencies based on the 'building system configuration file'
        //

        Set<Module> orderedDeps = new TreeSet<>(COMPARATOR);

        List<MavenCoords> dcoords = pom.getDependencyCoords();

        // speedup: dcoords is empty
        if (dcoords.isEmpty())
            return super.getDependencies();

        // retrieve the list of module dependencies based on
        // the Maven configuration file ('pom.xml')
        dcoords.forEach(coords -> {
            Module dmodule = project.getModule(coords.toString());
            if (dmodule != null) {
                orderedDeps.add(dmodule);
            }
        });

        // speedup: Maven dependencies is empty
        if (orderedDeps.isEmpty())
            return super.getDependencies();

        // add the missing dependencies based on the module types intersection
        orderedDeps.addAll(super.getDependencies());

        if (orderedDeps.isEmpty())
            return Collections.emptyList();
        else
            return new ArrayList<>(orderedDeps);
    }

    @Override
    protected List<String> getMavenRepositories() {
        return pom.getRepositories();
    }

    @Override
    protected List<Library> getMavenLibraries() {

        MavenDownloader md = project.getLibraryDownloader();

        List<MavenCoords> coordList = getDirectCoordsDependencies();
        // md.checkArtifacts(coordList);

        Set<MavenCoords> setCoords = new HashSet<>(coordList);

        coordList.forEach(dcoords -> {
            List<MavenCoords> ddcoords = md.getDependencies(dcoords, maxDepth);
            setCoords.addAll(ddcoords);
        });

        // md.checkArtifacts(new ArrayList<>(setCoords));

        return setCoords.stream()
            .map(coords -> new MavenLibrary(coords, md, project))
            .collect(Collectors.toList());
    }

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
