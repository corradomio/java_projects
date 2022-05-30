package jext.sourcecode.resources.python.libraries;

import jext.name.Name;
import jext.name.NamedObject;
import jext.name.PathName;
import jext.python.PythonConstants;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.none.NoneProject;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PythonLibrary extends NamedObject implements Library {

    protected File libraryDirectory;
    protected List<File> pythonFiles;
    private Module module = NoneProject.module();

    public PythonLibrary(File libraryDirectory) {
        super(PathName.of(libraryDirectory.getName()));
        this.libraryDirectory = libraryDirectory;
    }

    @Override
    public boolean isValid() {
        return libraryDirectory.isDirectory();
    }

    @Override
    public Project getProject() {
        return getModule().getProject();
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public LibraryType getLibraryType() {
        return LibraryType.LOCAL;
    }

    @Override
    public String getLanguage() {
        return PythonConstants.PYTHON;
    }

    @Override
    public String getPath() {
        return FileUtils.getAbsolutePath(libraryDirectory);
    }

    @Override
    public String getDigest() {
        return null;
    }

    @Override
    public File getFile() {
        return libraryDirectory;
    }

    @Override
    public List<File> getFiles() {
        if (pythonFiles == null)
            pythonFiles = FileUtils.listFiles(libraryDirectory, PythonConstants.PYTHON_EXT);
        return pythonFiles;
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
    public String getVersion() {
        return "";
    }

}
