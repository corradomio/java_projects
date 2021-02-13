package jext.sourcecode.project.eclipse;

import jext.sourcecode.project.eclipse.util.ClasspathFile;
import jext.sourcecode.project.maven.MavenLibrary;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.resources.libraries.ArchiveUtils;
import jext.maven.MavenCoords;
import jext.maven.MavenDownloader;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private ClasspathFile classpathFile;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public EclipseModule(File moduleHome, Project project) {
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
    protected List<Library> getLocalLibraries() {
        return classpathFile.getLocalLibraries()
            .stream()
            .map(jarFile -> ArchiveUtils.newLibrary(jarFile, this))
            .collect(Collectors.toList());
    }

    @Override
    protected List<Library> getMavenLibraries() {

        List<MavenCoords> coordList = classpathFile.getMavenLibraries()
            .stream()
            .map(MavenCoords::new)
            .collect(Collectors.toList());

        MavenDownloader md = project.getLibraryDownloader();
        md.checkArtifacts(coordList);

        return coordList
            .stream()
            .map(coords -> new MavenLibrary(coords, md, project))
            .collect(Collectors.toList());
    }

}