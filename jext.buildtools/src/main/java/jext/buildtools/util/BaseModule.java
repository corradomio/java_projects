package jext.buildtools.util;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Sources;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseModule implements Module {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Project project;
    protected File moduleDir;
    protected Name name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseModule(File moduleDir,Project project) {
        this.moduleDir = moduleDir;
        this.project = project;
        String rpath = FileUtils.relativePath(project.getDirectory(), moduleDir);
        this.name = new PathName(rpath);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject(){
        return project;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public File getDirectory() {
        return moduleDir;
    }

    @Override
    public Sources getSources() {
        return new JavaSources(this);
    }

    // ----------------------------------------------------------------------
    // Implementations
    // ----------------------------------------------------------------------

    protected List<File> listDirectories() {
        return FileUtils.asList(moduleDir.listFiles(File::isDirectory))
                .stream()
                .filter(this::isValid)
                .collect(Collectors.toList());
    }

    private boolean isValid(File directory) {
        if (directory.getName().startsWith("."))
            return false;
        for (Module module : project.getModules())
            if (FileUtils.isParent(directory, module.getDirectory()))
                return false;
        return true;
    }

}
