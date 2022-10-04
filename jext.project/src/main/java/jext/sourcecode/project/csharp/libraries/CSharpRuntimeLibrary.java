package jext.sourcecode.project.csharp.libraries;

import jext.io.filters.FileFilters;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.csharp.util.DotNetAssemblyUtils;
import jext.sourcecode.project.java.maven.MavenName;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSharpRuntimeLibrary extends CSharpLibrary {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String DLL = ".dll";

    private static class Assemblies extends ArrayList<File> {
        private Set<String> names = new HashSet<>();

        @Override
        public boolean add(File assembly) {
            String name = assembly.getName();
            if (names.contains(name))
                return false;

            names.add(name);
            return super.add(assembly);
        }
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final List<File> installationDirectories;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    // A runtime library is located at
    //
    //      <.NET Core/Framework>/<version>/sdk/<version>/*.dll
    //

    public CSharpRuntimeLibrary(String name, String version, List<File> libraryFiles) {
        super(MavenName.of(name, version));
        if (libraryFiles.isEmpty())
            this.libraryFile = new File("/unknown");
        else {
            this.libraryFile = libraryFiles.get(0);
            if (this.libraryFile.isFile())
                this.libraryFile = this.libraryFile.getParentFile();
        }
        this.version = version;
        this.installationDirectories = libraryFiles;
        this.libraryType = LibraryType.RUNTIME;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return libraryFile.exists();
    }

    @Override
    public LibraryStatus getLibraryStatus() {
        return LibraryStatus.VALID;
    }

    @Override
    public List<File> getFiles() {
        if (libraryFiles == null) {
            populate();
        }
        return libraryFiles;
    }

    private void populate() {
        // Warning:  listFiles ALREADY skip duplicated assembly names!
        libraryFiles = new Assemblies();
        installationDirectories.forEach(dirOrFile -> {
            if (dirOrFile.isFile()) {
                libraryFiles.add(dirOrFile);
                return;
            }
            else if (!dirOrFile.exists()) {
                logger.errorf("[%s] Directory '%s' not existent",
                        getName().getFullName(),
                        FileUtils.getAbsolutePath(dirOrFile));
                return;
            }

            // In 'theory', the only directory to consider is the directory containing
            // 'dotnet.dll' assembly

            // '.../ref' are scanned recursively
            if (dirOrFile.getName().equals("ref") || dirOrFile.getAbsolutePath().contains(".Ref")){
                FileUtils.listFiles(dirOrFile, FileFilters.IS_DLL).stream()
                        .filter(DotNetAssemblyUtils::isAssembly)
                        .forEach(assembly -> {
                            libraryFiles.add(assembly);
                        });
            }
            // '.../sdk' are NOT scanned recursively
            else if (dirOrFile.getName().contains("sdk")){
                FileUtils.listFiles(dirOrFile, DLL).stream()
                        .filter(DotNetAssemblyUtils::isAssembly)
                        .forEach(assembly -> {
                            libraryFiles.add(assembly);
                        });
            }
            // other directories are scanned recursively
            else {
                FileUtils.listFiles(dirOrFile, DLL).stream()
                        .filter(DotNetAssemblyUtils::isAssembly)
                        .forEach(assembly -> {
                            libraryFiles.add(assembly);
                        });
            }
        });
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
