package jext.sourcecode.project.csharp.libraries;

import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.RefType;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class NuGetLibrary extends CSharpLibrary {

    public static NuGetLibrary of(String name, String version) {
        return new NuGetLibrary(name, version);
    }


    private String version;

    private NuGetLibrary(String name, String version) {
        super(PathName.of(name));
        this.version = version;
    }

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
