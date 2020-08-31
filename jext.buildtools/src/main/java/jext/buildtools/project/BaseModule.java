package jext.buildtools.project;

import jext.buildtools.Libraries;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Resources;
import jext.buildtools.Sources;
import jext.buildtools.Types;
import jext.buildtools.library.JarLibraries;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.resource.FileResources;
import jext.buildtools.source.SourceTypes;
import jext.buildtools.source.java.JavaSources;
import jext.buildtools.util.FileFilters;
import jext.buildtools.util.NamedObject;
import jext.buildtools.util.PathName;
import jext.logging.Logger;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;
import jext.util.SetUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseModule extends NamedObject implements Module {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Project project;
    protected File moduleDir;

    protected Sources sources;
    protected Libraries libraries;
    protected Resources resources;
    protected Types types;
    protected List<Module> dependencies;

    protected Logger logger;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected BaseModule(File moduleDir, Project project) {
        super(null);
        this.moduleDir = moduleDir;
        this.project = project;

        String rpath = FileUtils.relativePath(project.getDirectory(), moduleDir);
        setName(new PathName(rpath));

        this.logger = Logger.getLogger(getClass(), getName().getName());

        this.sources = new JavaSources(this);
        this.resources = new FileResources(this);
        this.libraries = new JarLibraries(this);
        this.types = new SourceTypes(this);

        List<String> includes = PropertiesUtils.getValues(project.getProperties(), Project.MODULE_RESOURCES);
        List<String> excludes = PropertiesUtils.getValues(project.getProperties(), Project.MODULE_EXCLUDE);

        this.resources.setIncludes(includes);
        this.resources.setExcludes(excludes);
        this.sources.setExcludes(excludes);
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject(){
        return project;
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
    public List<Module> getDependencies(boolean recursive) {
        if (dependencies != null)
            return dependencies;

        dependencies = new ArrayList<>();
        Types types = getTypes();

        for(Module dmodule : project.getModules()) {
            // skip the dependencies with itself
            if (dmodule.getName().equals(this.getName()))
                continue;

            Types dtypes = dmodule.getTypes();

            Set<Name> itypes  = SetUtils.intersection(types.getImportedTypes(), dtypes.getDefinedTypes());
            //Set<Name> ispaces = SetUtils.intersection(types.getImportedNamespaces(), dtypes.getDefinedNamespaces());

            if ((itypes.size() /*+ ispaces.size()*/) > 0)
                dependencies.add(dmodule);
        }

        return dependencies;
    }

    // ----------------------------------------------------------------------
    // Implementations
    // ----------------------------------------------------------------------

    public List<File> getDirectories() {
        return FileUtils.asList(moduleDir.listFiles(File::isDirectory))
                .stream()
                .filter(this::isValid)
                .collect(Collectors.toList());
    }

    public List<File> getLocalLibraries() {
        List<File> libraryFiles = new ArrayList<>();

        getDirectories().forEach(dir ->{
            FileUtils.listFiles(libraryFiles, dir, FileFilters.IS_JAR);
        });
        return libraryFiles;
    }

    public List<MavenCoords> getMavenLibraries() {
        return Collections.emptyList();
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
