package jext.sourcecode.project.none;

import jext.name.Name;
import jext.name.PathName;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.java.JavaConstants;
import jext.sourcecode.project.util.BaseLibrary;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InvalidLibrary extends BaseLibrary {

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
    public LibraryStatus getLibraryStatus() {
        return LibraryStatus.NOTEXISTENT;
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
