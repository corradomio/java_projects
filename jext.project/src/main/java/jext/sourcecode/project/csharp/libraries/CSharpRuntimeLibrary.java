package jext.sourcecode.project.csharp.libraries;

import jext.io.filters.FileFilters;
import jext.name.PathName;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.csharp.util.DotNetAssemblyUtils;
import jext.sourcecode.project.java.maven.MavenName;
import jext.sourcecode.project.util.BaseLibrary;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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

    public CSharpRuntimeLibrary(String name, String version, List<File> installationDirectories) {
        super(MavenName.of(name, version));
        this.libraryFile = installationDirectories.get(0);
        this.version = version;
        this.installationDirectories = installationDirectories;
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
        if (libraryFiles == null)
            populate();
        return libraryFiles;
    }

    private void populate() {
        libraryFiles = new Assemblies();
        installationDirectories.forEach(idir -> {
            if (!idir.exists()) {
                logger.errorf("[%s] Directory '%s' not existent",
                        getName().getFullName(),
                        FileUtils.getAbsolutePath(idir));
                return;
            }

            // In 'theory', the only directory to consider is the directory containing
            // 'dotnet.dll' assembli

            // '.../ref' are scanned recursively
            if (idir.getName().equals("ref") || idir.getAbsolutePath().contains(".Ref")){
                FileUtils.listFiles(idir, FileFilters.IS_DLL).stream()
                        .filter(DotNetAssemblyUtils::isAssembly)
                        .forEach(assembly -> {
                            libraryFiles.add(assembly);
                        });
            }
            // '.../sdk' are NOT scanned recursively
            else if (idir.getName().contains("sdk")){
                FileUtils.listFiles(idir, DLL).stream()
                        .filter(DotNetAssemblyUtils::isAssembly)
                        .forEach(assembly -> {
                            libraryFiles.add(assembly);
                        });
            }
            else {
                FileUtils.listFiles(idir, DLL).stream()
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
