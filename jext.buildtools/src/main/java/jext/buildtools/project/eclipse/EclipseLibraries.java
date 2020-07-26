package jext.buildtools.project.eclipse;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.util.BaseLibraries;
import jext.buildtools.util.JarLibrary;

import java.util.ArrayList;
import java.util.List;

public class EclipseLibraries extends BaseLibraries {

    private ClasspathFile classpathFile;

    public EclipseLibraries(Module module) {
        super(module);
        this.classpathFile = ((EclipseModule)module).getClasspathFile();
    }

    @Override
    public List<Library> getLibraries() {
        if (libraries != null)
            return libraries;

        libraries = new ArrayList<>();
        classpathFile.getLocalLibraries().forEach(jarFile -> {
            libraries.add(new JarLibrary(jarFile, module));
        });

        return libraries;
    }
}
