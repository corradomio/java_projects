package jext.buildtools.util;

import jext.buildtools.Libraries;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Resources;
import jext.buildtools.Sources;
import jext.buildtools.Types;
import jext.buildtools.project.simple.SimpleProject;
import jext.util.FileUtils;
import jext.util.SetUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseModule implements Module {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Project project;
    protected File moduleDir;
    protected Name name;

    protected Sources sources;
    protected Libraries libraries;
    protected Resources resources;
    protected Types types;
    protected List<Module> dependencies;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseModule(File moduleDir, Project project) {
        this.moduleDir = moduleDir;
        this.project = project;
        String rpath = FileUtils.relativePath(project.getDirectory(), moduleDir);
        this.name = new PathName(rpath);

        this.sources = new JavaSources(this);
        this.libraries = new JarLibraries(this);
        this.resources = new FileResources(this);
        this.types = new SourcesTypes(this);

        String exts = project.getProperties().getProperty(SimpleProject.PROJECT_RESOURCES, ".xml,.properties");
        Set<String> extensions = new HashSet<>(Arrays.asList(exts.split(",")));
        ((FileResources)this.resources).setExtension(extensions);
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
        return sources;
    }

    @Override
    public Libraries getLibraries() {
        return libraries;
    }

    @Override
    public Resources getResources() {
        return resources;
    }

    @Override
    public Types getTypes() {
        return types;
    }

    @Override
    public List<Module> getDependencies() {
        if (dependencies != null)
            return dependencies;

        dependencies = new ArrayList<>();
        Types types = getTypes();

        for(Module dmodule : project.getModules()) {
            Types dtypes = dmodule.getTypes();

            Set<Name> itypes  = SetUtils.intersection(types.getImportedTypes(), dtypes.getDefinedTypes());
            Set<Name> ispaces = SetUtils.intersection(types.getImportedNamespaces(), dtypes.getDefinedNamespaces());

            if ((itypes.size() + ispaces.size()) > 0)
                dependencies.add(dmodule);
        }

        return dependencies;
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
