package jext.sourcecode.project.csharp;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.csharp.util.CSharpSourcesImpl;
import jext.sourcecode.project.util.BaseModule;
import jext.util.PathUtils;
import jext.util.SetUtils;
import jext.util.concurrent.ConcurrentHashSet;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSharpModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private List<Module> dependencies;
    private Set<RefType> usedTypes;
    private Set<RefType> definedTypes;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    // ----------------------------------------------------------------------
    // Module dependencies
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getDependencies() {
        if (dependencies != null)
            return dependencies;

        // retrieve the list of extends namespaces used in the current module
        dependencies = new ArrayList<>();

        // external namespaces used by this module
        Set<RefType> usedTypes = getUsedTypes();

        project.getModules().forEach(dmodule -> {
            // skip the comparison with itself
            if (dmodule.getName().equals(name))
                return;

            // namespaces defined in the module
            Set<RefType> moduleTypes = dmodule.getTypes();

            // check if 'dmodule' contains the definition of some namespace
            // used by THIS module
            if (!SetUtils.intersection(usedTypes, moduleTypes).isEmpty())
                dependencies.add(dmodule);
        });

        return dependencies;
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        populateSources();
        populateTypes();

        return sources;
    }

    private void populateSources() {

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
    public Set<RefType> getTypes() {
        if (definedTypes == null)
            populateTypes();
        return definedTypes;
    }

    @Override
    public Set<RefType> getUsedTypes() {
        if (usedTypes == null)
            populateTypes();
        return usedTypes;
    }

    private void populateTypes() {
        Set<RefType> allUsedTypes = new ConcurrentHashSet<>();

        definedTypes = new ConcurrentHashSet<>();

        Parallel.forEach(getSources(), source -> {
            allUsedTypes.addAll(source.getUsedTypes());
            definedTypes.addAll(source.getTypes());
        });

        // getSources().forEach(source -> {
        //     allUsedTypes.addAll(source.getUsedTypes());
        //     definedTypes.addAll(source.getTypes());
        // });

        usedTypes = SetUtils.difference(allUsedTypes, definedTypes);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
