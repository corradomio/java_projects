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
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CSharpRuntimeLibrary  extends BaseLibrary {

    private static final String NET = ".NET Core";
    private static final String NO_VERSION = "0.0";

    /**
     * Create a runtime library based on the installation directory.
     *
     * Note: for now it supports .NET Core
     *
     *
     * @param installDirectory
     */
    public static CSharpRuntimeLibrary newLibrary(File installDirectory) {
        /*
            filesystem layout

            [install]
                sdk
                    [version: 6.0.300]
                        *.dll (windows/linux)
         */

        // if in 'installDirectory' or '[installDirectory]/sdk' don't exist
        // return an empty runtime library
        File sdk = new File(installDirectory, "sdk");
        if (!installDirectory.exists() || !sdk.exists())
            return new CSharpRuntimeLibrary(NET, NO_VERSION, installDirectory);

        // scan for the version
        File[] versions = sdk.listFiles(File::isDirectory);
        if (versions == null || versions.length == 0)
            return new CSharpRuntimeLibrary(NET, NO_VERSION, installDirectory);

        String version = versions[0].getName();
        return new CSharpRuntimeLibrary(NET, version, installDirectory);
    }

    // ----------------------------------------------------------------------
    // Private properties
    // ----------------------------------------------------------------------

    private String version;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpRuntimeLibrary(String name, String version, File installationDirectory) {
        super(PathName.of(name));
        this.libraryFile = installationDirectory;
        this.libraryType = LibraryType.RUNTIME;
        this.version = version;
    }

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
        File libsDir = new File(libraryFile, String.format("sdk/%s", version));
        return FileUtils.listFiles(libsDir, ".dll");
    }

    @Override
    public Set<RefType> getTypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean contains(Name typeName) {
        return false;
    }
}
