package jext.sourcecode.resources;

import jext.name.Name;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryType;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.util.NamedObject;
import jext.sourcecode.project.Project;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.StringUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public abstract class BaseLibrary extends NamedObject implements Library {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger;

    protected Project project;

    protected File libraryFile;
    protected LibraryType libraryType;
    protected Module module;

    protected Set<Name> definedTypes = new HashSet<>();
    protected Set<Name> undefinedTypes = new HashSet<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

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

    @Override
    @Nullable
    public String getModuleId() {
        return module != null ? module.getId() : null;
    }

    @Override
    @Nullable
    public Module getModule() {
        return module;
    }

    @Override
    public LibraryType getLibraryType() {
        return libraryType;
    }

    @Override
    public String getDigest() {
        return FileUtils.digest(libraryFile);
    }

    // @Override
    // public List<Library> getDependencies() {
    //     return Collections.emptyList();
    // }

    // @Override
    // public boolean isLocal() {
    //     LibraryType ltype = getLibraryType();
    //     return !(ltype == LibraryType.MAVEN
    //         || ltype == LibraryType.RUNTIME
    //     );
    // }

    // ----------------------------------------------------------------------
    // Version
    // ----------------------------------------------------------------------

    @Override
    public String getVersion() { return StringUtils.empty(); }

    @Override
    public String getLatest() { return StringUtils.empty(); }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
