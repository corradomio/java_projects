package jext.sourcecode.project.util;

import jext.logging.Logger;
import jext.name.Name;
import jext.name.NamedObject;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.lfm.LibraryLicense;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class BaseLibrary extends NamedObject implements Library {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger;

    protected Project project;

    protected File libraryFile;
    protected List<File> libraryFiles;
    protected LibraryType libraryType;
    protected String language;
    protected String version = "";

    protected Set<Name> definedTypes = new HashSet<>();
    protected Set<Name> undefinedTypes = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseLibrary(Name libraryName) {
        super(libraryName);

        this.logger = Logger.getLogger(getClass(), libraryName.toString());
    }

    // ----------------------------------------------------------------------
    // Version
    // ----------------------------------------------------------------------

    @Override
    public String getVersion() {
        return version;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public String getPath() {
        File projectHome = getProject().getProjectHome();
        return FileUtils.relativePath(projectHome, libraryFile);
    }

    @Override
    public File getFile() {
        return this.libraryFile;
    }

    @Override
    public LibraryType getLibraryType() {
        return libraryType;
    }

    @Override
    public LibraryLicense getLibraryLicense() {
        return LibraryLicense.none();
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public LibraryStatus getLibraryStatus() {
        return LibraryStatus.VALID;
    }

    @Override
    public Set<Library> getDependencies() {
        return Collections.emptySet();
    }

    // ----------------------------------------------------------------------
    // Types
    // ----------------------------------------------------------------------

    public Library project(Project project) {
        this.project = project;
        return this;
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
