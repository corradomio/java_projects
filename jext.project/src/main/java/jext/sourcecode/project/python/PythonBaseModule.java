package jext.sourcecode.project.python;

import jext.python.PythonConstants;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.python.util.PythonSourcesImpl;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.project.util.SourcesImpl;
import jext.sourcecode.resources.SourceCode;
import jext.sourcecode.resources.libraries.InvalidLibrary;
import jext.util.PathUtils;
import jext.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Set;

public class PythonBaseModule extends BaseModule {

    protected PythonBaseModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    // ----------------------------------------------------------------------
    // Module content
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new PythonSourcesImpl(this);
        Module self = this;
        PythonBaseProject pyproject = (PythonBaseProject) project;

        try {
            Files.walkFileTree(moduleHome.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (pyproject.isExcluded(dir.toFile()))
                        return FileVisitResult.SKIP_SUBTREE;
                    else
                        return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (pyproject.isExcluded(file.toFile()))
                        return FileVisitResult.CONTINUE;
                    if (!pyproject.isSourceFile(file.toFile()))
                        return FileVisitResult.CONTINUE;

                    String ext = PathUtils.getExtension(file.toString());
                    if (PythonConstants.PYTHON_EXT.equals(ext)) {
                        Source source = SourceCode.newSource(file.toFile(), self);
                        sources.add(source);
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) { }



        return sources;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Library getRuntimeLibrary() {
        // check if it is possible to retrieve the runtime library locally
        String runtimeName = PythonGuessRuntimeLibrary.guessRuntimeLibrary(this);

        // check if the project has a specified jdk
        if (StringUtils.isEmpty(runtimeName))
            runtimeName = project.getRuntimeLibrary();

        return getRuntimeLibrary(runtimeName);
    }

    protected Library getRuntimeLibrary(String runtimeName) {
        String rtLibrary = project.getRuntimeLibrary();
        LibraryFinder lfinder = getProject().getLibraryFinder();

        Library runtimeLibrary = lfinder.getRuntimeLibrary(runtimeName);
        if (runtimeLibrary == null) {
            logger.errorf("Python Library %s not available. Used the default %s", runtimeName, rtLibrary);
            runtimeLibrary = lfinder.getRuntimeLibrary(rtLibrary);
        }

        if (runtimeLibrary == null) {
            logger.errorf("Python Library %s not available. Used an 'empty library'", runtimeName);
            runtimeLibrary = new InvalidLibrary(runtimeName, getProject());
        }

        return runtimeLibrary;
    }
    @Override
    public Set<Library> getDeclaredLibraries() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
