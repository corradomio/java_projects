package jext.sourcecode.project.csharp.libraries;

import jext.io.filters.FileFilters;
import jext.name.PathName;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.csharp.util.DotNetAssemblyUtils;
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

    private static final String NET = ".NET Core";
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

    private String version;
    private List<File> assemblies;
    private List<File> installationDirectories;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------
    // A runtime library is located at
    //
    //      <.NET Core/Framework>/<version>/sdk/<version>/*.dll
    //

    public CSharpRuntimeLibrary(String name, String version, List<File> installationDirectories) {
        super(PathName.of(name));
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
    public String getVersion() {
        return version;
    }

    @Override
    public List<File> getFiles() {
        if (assemblies != null)
            return assemblies;

        assemblies = new Assemblies();
        installationDirectories.forEach(idir -> {
            if (!idir.exists()) {
                logger.errorf("[%s] Directory '%s' not existent",
                    getName().getFullName(),
                    FileUtils.getAbsolutePath(idir));
                return;
            }
            // ONLY '.../ref' are scanned recursively
            if (idir.getName().equals("ref") || idir.getAbsolutePath().contains(".Ref")){
                FileUtils.listFiles(idir, FileFilters.IS_DLL).stream()
                    .filter(DotNetAssemblyUtils::isAssembly)
                    .forEach(assembly -> {
                        assemblies.add(assembly);
                    });
            }
            //
            else if (idir.getName().equals("sdk")){
                FileUtils.listFiles(idir, DLL).stream()
                    .filter(DotNetAssemblyUtils::isAssembly)
                    .forEach(assembly -> {
                        assemblies.add(assembly);
                    });
            }
            else {
                FileUtils.listFiles(idir, DLL).stream()
                    .filter(DotNetAssemblyUtils::isAssembly)
                    .forEach(assembly -> {
                        assemblies.add(assembly);
                    });
            }
        });

        return assemblies;
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    @Override
    public Set<RefType> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean contains(Name typeName) {
        return false;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
