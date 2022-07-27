package jext.sourcecode.project.csharp.libraries;

import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.maven.MavenName;
import jext.util.Assert;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    private String version;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private NuGetLibrary(String name, String version) {
        super(MavenName.of(name, version));
        this.version = version;
        this.libraryType = LibraryType.REMOTE;
        this.libraryFiles = Collections.emptyList();
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public List<File> getFiles() {
        return Collections.emptyList();
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
