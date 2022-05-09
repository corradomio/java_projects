package jext.sourcecode.resources.libraries;

import jext.java.JavaConstants;
import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.resources.BaseLibrary;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InvalidLibrary extends BaseLibrary implements Library {

    public InvalidLibrary(String libraryName, Project project) {
        super(PathName.of(libraryName), project);
    }

    public InvalidLibrary(Name libraryName, Project project) {
        super(libraryName, project);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public LibraryType getLibraryType() {
        return LibraryType.INVALID;
    }

    @Override
    public String getPath() {
        return "";
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public String getDigest() {
        return "0";
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

    @Override
    public String getLanguage() {
        return JavaConstants.JAVA;
    }

    @Override
    public String getVersion() {
        return "";
    }

}
