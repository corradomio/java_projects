package jext.sourcecode.project.util;

import jext.logging.Logger;
import jext.name.Name;
import jext.name.NamedObject;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryStatus;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.util.FileUtils;
import jext.util.StringUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public abstract class BaseLibrary extends NamedObject implements Library {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger;

    protected Project project;

    protected File libraryFile;
    protected LibraryType libraryType;
    protected String language;

    protected Set<Name> definedTypes = new HashSet<>();
    protected Set<Name> undefinedTypes = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseLibrary(Name libraryName) {
        super(libraryName);

        this.logger = Logger.getLogger(getClass(), libraryName.toString());
    }

    protected BaseLibrary(Name libraryName, Project project) {
        super(libraryName);

        this.project = project;

        this.logger = Logger.getLogger(getClass(), libraryName.toString());
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

    // @Override
    // @Nullable
    // public Module getModule() {
    //     return module;
    // }

    @Override
    public LibraryType getLibraryType() {
        return libraryType;
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
    public String getDigest() {
        return FileUtils.digest(libraryFile);
    }

    // ----------------------------------------------------------------------
    // Version
    // ----------------------------------------------------------------------

    @Override
    public String getVersion() { return StringUtils.empty(); }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
