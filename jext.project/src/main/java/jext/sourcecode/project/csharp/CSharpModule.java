package jext.sourcecode.project.csharp;

import jext.maven.MavenCoords;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.csharp.libraries.CSharpLocalLibrary;
import jext.sourcecode.project.csharp.util.CSharpProjectFile;
import jext.sourcecode.project.csharp.util.CSharpSourcesImpl;
import jext.sourcecode.project.csharp.util.DirectoryBuildPropsFile;
import jext.sourcecode.project.csharp.util.PackagesConfig;
import jext.sourcecode.project.util.BaseModule;
import jext.util.FileUtils;
import jext.util.PathUtils;
import jext.util.SetUtils;
import jext.util.concurrent.ConcurrentHashSet;
import jext.util.concurrent.Parallel;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class CSharpModule extends BaseModule {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private static final String CSPROJ = ".csproj";
    private static final String EXT_DLL = ".dll";

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
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) {
                    File dir = path.toFile();

                    if (!csproject.isValidDir(moduleHome, dir))
                        return FileVisitResult.SKIP_SUBTREE;
                    else
                        return FileVisitResult.CONTINUE;

                    // check if it is a excluded directory
                    // if (csproject.isExcluded(dir))
                    //     return FileVisitResult.SKIP_SUBTREE;
                    // if (((CSharpProject) project).isModuleDir(dir))
                    //     return FileVisitResult.SKIP_SUBTREE;
                    // else
                    //     return FileVisitResult.CONTINUE;
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
        Library rtLibrary;

        try {
            String runtimeLibrary = GuessRuntimeLibrary.guessRuntimeLibrary(this);
            rtLibrary = project.getLibraryFinder().getRuntimeLibrary(runtimeLibrary);
        }
        catch (ProjectException e) {
            // no runtime library found
            // return project default runtime
            rtLibrary = ((CSharpProject)project).getRTLibrary();
        }

        return rtLibrary;
    }

    @Override
    public Set<Library> getDeclaredLibraries() {
        if (declaredLibraries != null)
            return declaredLibraries;

        declaredLibraries = new HashSet<>();

        collectLibrariesFromPackagesConfig();
        collectLibrariesFromPackageReference();

        return declaredLibraries;
    }

    public Set<Library> getLocalLibraries() {
        if (localLibraries != null)
            return localLibraries;

        localLibraries = new HashSet<>();

        // Note: linux is CASE SENSITIVE

        collectLocalLibraries("lib");
        collectLocalLibraries("Lib");
        collectLocalLibraries("library");
        collectLocalLibraries("Library");
        collectLocalLibrariesFromReference();

        return localLibraries;
    }

    // ----------------------------------------------------------------------
    // collectLocalLibraries

    private void collectLocalLibraries(String libdir) {

        // check if there exists '[moduleHome]/lib'
        // If it is present, collect all .dll as a single 'local library'
        File libDirectory = new File(getModuleHome(), libdir);
        if (!libDirectory.isDirectory())
            return;

        // scan for 'lib/*.dll'
        List<File> dllFiles = FileUtils.asList(libDirectory.listFiles((dir, name) -> name.endsWith(EXT_DLL)));
        if (!dllFiles.isEmpty()) {
            Name libraryName = PathName.of(getName(), libdir);
            Library localLibrary = new CSharpLocalLibrary(libraryName, libDirectory, dllFiles);
            localLibraries.add(localLibrary);
        }

        // scan for subdirectories
        List<File> subdirs = FileUtils.asList(libDirectory.listFiles(File::isDirectory));
        subdirs.forEach(subdir -> {
            // scan for .dll
            List<File> libFiles = FileUtils.listFiles(subdir, new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(EXT_DLL);
                }
            });

            if (libFiles.isEmpty())
                return;

            Name libraryName = PathName.of(getName(), subdir.getName());
            Library localLibrary = new CSharpLocalLibrary(libraryName, subdir, dllFiles);
            localLibraries.add(localLibrary);
        });
    }

    // ----------------------------------------------------------------------
    // collectLibrariesFromPackagesConfig

    private void collectLibrariesFromPackagesConfig() {
        CSharpLibraryFinder lfinder = (CSharpLibraryFinder) project.getLibraryFinder();

        // check libraries in 'package.config' file (.NET Framework)
        PackagesConfig packageConfig = new PackagesConfig(moduleHome);

        List<MavenCoords> dotnetlibs = packageConfig.getLibraries();
        dotnetlibs.forEach(coords -> {
            coords = lfinder.normalize(coords);

            Library library = lfinder.getLibrary(coords);

            declaredLibraries.add(library);
        });
    }
    private void collectLocalLibrariesFromReference() {
        File projectHome = project.getProjectHome();

        // global properties
        //      [projectHome]/Directory.Build.(props|targets)
        //       [moduleHome]/Directory.Build.(props|targets)
        Properties globalProperties = new Properties();

        globalProperties.putAll(DirectoryBuildPropsFile.props(  projectHome).getProperties());
        globalProperties.putAll(DirectoryBuildPropsFile.targets(projectHome).getProperties());
        globalProperties.putAll(DirectoryBuildPropsFile.props(  projectHome, moduleHome).getProperties());
        globalProperties.putAll(DirectoryBuildPropsFile.targets(projectHome, moduleHome).getProperties());

        List<File> csprojFiles = FileUtils.listFiles(moduleHome, pathname -> pathname.getName().endsWith(CSPROJ));
        csprojFiles.forEach(csprojFile -> {

            CSharpProjectFile cspjf = new CSharpProjectFile(projectHome, csprojFile);
            cspjf.addProperties(globalProperties);

            List<CSharpProjectFile.LocalReference> localReferences = cspjf.getLocalReferences();
            localReferences.forEach(lr -> {

                Library library = new CSharpLocalLibrary(PathName.of(lr.name), lr.file);

                this.localLibraries.add(library);
            });
        });
    }

    // ----------------------------------------------------------------------
    // collectLibrariesFromPackageReference
    //
    //  Note: the directory can contains MULTIPLE ".csproj" files!!!!

    private void collectLibrariesFromPackageReference() {
        CSharpLibraryFinder lfinder = (CSharpLibraryFinder) project.getLibraryFinder();

        File projectHome = project.getProjectHome();

        // global properties
        //      [projectHome]/Directory.Build.(props|targets)
        //       [moduleHome]/Directory.Build.(props|targets)
        Properties globalProperties = new Properties();

        globalProperties.putAll(DirectoryBuildPropsFile.props(  projectHome).getProperties());
        globalProperties.putAll(DirectoryBuildPropsFile.targets(projectHome).getProperties());
        globalProperties.putAll(DirectoryBuildPropsFile.props(  projectHome, moduleHome).getProperties());
        globalProperties.putAll(DirectoryBuildPropsFile.targets(projectHome, moduleHome).getProperties());

        List<File> csprojFiles = FileUtils.listFiles(moduleHome, pathname -> pathname.getName().endsWith(CSPROJ));
        csprojFiles.forEach(csprojFile -> {

            CSharpProjectFile cspjf = new CSharpProjectFile(projectHome, csprojFile);
            cspjf.addProperties(globalProperties);

            List<MavenCoords> packageReferences = cspjf.getPackageReferences();
            packageReferences.forEach(coords -> {
                coords = lfinder.normalize(coords);

                Library library = lfinder.getLibrary(coords);

                declaredLibraries.add(library);
            });
        });
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

        usedTypes = SetUtils.difference(allUsedTypes, definedTypes);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
