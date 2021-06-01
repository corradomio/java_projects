package jext.sourcecode.project.eclipse;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.eclipse.util.ClasspathFile;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.resources.libraries.ArchiveUtils;

import java.io.File;
import java.util.Set;

public class EclipseModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private ClasspathFile classpathFile;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    EclipseModule(File moduleHome, Project project) {
        super(moduleHome, project);
        this.classpathFile = new ClasspathFile(moduleHome);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public ClasspathFile getClasspathFile() {
        return classpathFile;
    }

    @Override
    protected void collectLocalLibraries(Set<Library> collectedLibraries) {
        super.collectLocalLibraries(collectedLibraries);
        classpathFile.getLocalLibraries()
            .stream()
            .filter(jarFile -> jarFile.exists())
            .map(jarFile -> ArchiveUtils.newLibrary(jarFile, this))
            .forEach(collectedLibraries::add);
    }

    // @Override
    // protected void collectEclipseLibraries(Set<Library> collectedLibraries) {
    //     List<MavenCoords> coordList = classpathFile.getMavenLibraries()
    //         .stream()
    //         .map(MavenCoords::new)
    //         .collect(Collectors.toList());
    //
    //     MavenDownloader md = project.getLibraryDownloader();
    //     // md.checkArtifacts(coordList);
    //
    //     coordList
    //         .stream()
    //         .map(coords -> new MavenLibrary(coords, md, project))
    //         .forEach(collectedLibraries::add);
    // }

    // @Override
    // protected List<Library> getLocalLibraries() {
    //     List<Library> localLibraries = super.getLocalLibraries();
    //     classpathFile.getLocalLibraries()
    //         .stream()
    //         .filter(jarFile -> jarFile.exists())
    //         .map(jarFile -> ArchiveUtils.newLibrary(jarFile, this))
    //         .forEach(localLibraries::add);
    //     return localLibraries;
    // }

    // @Override
    // protected List<Library> getMavenLibraries() {
    //
    //     List<MavenCoords> coordList = classpathFile.getMavenLibraries()
    //         .stream()
    //         .map(MavenCoords::new)
    //         .collect(Collectors.toList());
    //
    //     MavenDownloader md = project.getLibraryDownloader();
    //     // md.checkArtifacts(coordList);
    //
    //     return coordList
    //         .stream()
    //         .map(coords -> new MavenLibrary(coords, md, project))
    //         .collect(Collectors.toList());
    // }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}