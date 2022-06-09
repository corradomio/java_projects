package jext.sourcecode.project.csharp.libraries;

import jext.name.PathName;
import jext.sourcecode.project.csharp.CSharpConstants;
import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.maven.MavenName;
import jext.sourcecode.project.util.BaseLibrary;
import jext.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CSharpRuntimeLibrary extends BaseLibrary {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String NET = ".NET Core";
    private static final String DLL = ".dll";

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private String version;
    private List<File> files;
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
    public String getVersion() {
        return version;
    }

    @Override
    public String getLanguage() {
        return CSharpConstants.CSHARP;
    }

    @Override
    public List<File> getFiles() {
        if (files != null)
            return files;

        files = new ArrayList<>();
        installationDirectories.forEach(idir -> {
            files.addAll(FileUtils.listFiles(idir, DLL));
        });

        return files;
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
