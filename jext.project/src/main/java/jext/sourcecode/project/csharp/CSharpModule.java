package jext.sourcecode.project.csharp;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.csharp.util.CSharpSourcesImpl;
import jext.sourcecode.project.util.BaseModule;
import jext.util.PathUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Set;

public class CSharpModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new CSharpSourcesImpl(this);
        Module self = this;
        CSharpProject csproject = (CSharpProject) project;

        try {
            Files.walkFileTree(moduleHome.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (csproject.isExcluded(dir.toFile()))
                        return FileVisitResult.SKIP_SUBTREE;
                    else
                        return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (csproject.isExcluded(file.toFile()))
                        return FileVisitResult.CONTINUE;
                    if (!csproject.isSourceFile(file.toFile()))
                        return FileVisitResult.CONTINUE;

                    String ext = PathUtils.getExtension(file.toString());
                    if (CSharpConstants.CSHARP_EXT.equals(ext)) {
                        Source source = CSharpSourceCode.newSource(file.toFile(), self);
                        sources.add(source);
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
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
        return ((CSharpProject)project).getRTLibrary();
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public Set<Type> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<RefType> getUsedTypes() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
