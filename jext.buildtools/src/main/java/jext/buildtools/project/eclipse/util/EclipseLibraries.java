package jext.buildtools.project.eclipse;

import jext.buildtools.Library;
import jext.buildtools.Module;
import jext.buildtools.util.BaseLibraries;
import jext.buildtools.util.JarLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        libraries = module.listLibraries().stream()
                .map(libraryFile -> new JarLibrary(libraryFile, module))
                .collect(Collectors.toList());

        return libraries;
    }
}
