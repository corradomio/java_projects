package jext.sourcecode.resources.csharp.libraries;

import jext.sourcecode.project.csharp.CSharpConstants;
import jext.name.Name;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.resources.BaseLibrary;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CSharpRuntimeLibrary  extends BaseLibrary {

    public CSharpRuntimeLibrary(Name libraryName, Project project) {
        super(libraryName, project);
        this.libraryType = LibraryType.RUNTIME;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getLanguage() {
        return CSharpConstants.CSHARP;
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
