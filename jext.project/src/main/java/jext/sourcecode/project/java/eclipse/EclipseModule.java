package jext.sourcecode.project.java.eclipse;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.java.JavaBaseModule;
import jext.sourcecode.project.java.eclipse.util.ClasspathFile;
import jext.sourcecode.project.java.libraries.ArchiveUtils;

import java.io.File;
import java.util.Set;

public class EclipseModule extends JavaBaseModule {

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

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}