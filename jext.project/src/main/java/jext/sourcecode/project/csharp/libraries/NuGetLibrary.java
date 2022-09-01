package jext.sourcecode.project.csharp.libraries;

import jext.maven.MavenCoords;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.csharp.util.DotNetResolved;
import jext.sourcecode.project.java.maven.MavenName;
import jext.util.Assert;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class NuGetLibrary extends CSharpLibrary {

    public static NuGetLibrary of(String name, String version) {
        if (name == null || name.isEmpty() || version == null)
            throw new IllegalArgumentException();

        if ("6.0.8".equals(version))
            Assert.nop();
        return new NuGetLibrary(name, version);
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private MavenCoords coords;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private NuGetLibrary(String name, String version) {
        super(MavenName.of(name, version));
        this.version = version;
        this.libraryType = LibraryType.REMOTE;
        this.libraryFiles = Collections.emptyList();
        this.coords = MavenCoords.of(name, version);
    }

    public NuGetLibrary(MavenCoords coords, File libraryDirectory) {
        super(MavenName.of(coords));
        this.libraryFile = libraryDirectory;
        this.version = coords.version;
        this.libraryType = LibraryType.REMOTE;
        this.coords = coords;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public List<File> getFiles() {
        if (libraryFiles == null)
            populateFiles();
        return libraryFiles;
    }

    /*
        Problem:
        the directory lib contains a list of subdirectories as, for example:

        lib/
            net20
            net35
            net45
            net472
            netstandard1.0
            netstandard1.3
            netstandard2.0
            netcoreapp3.0
            netcoreapp3.1
            net6.0

        In theory, it is necessary to select the correct implementation.
        This depends on the project, however
     */
    private void populateFiles() {
        File lib = new File(libraryFile, "lib");

        // select the .NET implementation
        DotNetResolved dnv = new DotNetResolved(lib);
        String impl = dnv.select();

        File selectedImpl = new File(lib, impl);
        this.libraryFiles = FileUtils.listFiles(selectedImpl, ".dll");
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
