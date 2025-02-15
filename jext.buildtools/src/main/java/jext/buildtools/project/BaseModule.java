package jext.buildtools.project;

import jext.buildtools.Libraries;
import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.buildtools.Resource;
import jext.buildtools.Resources;
import jext.buildtools.Source;
import jext.buildtools.Sources;
import jext.buildtools.Types;
import jext.buildtools.library.JarLibraries;
import jext.buildtools.project.gradle.GradleModule;
import jext.io.file.FilePatterns;
import jext.io.file.FileSet;
import jext.maven.MavenCoords;
import jext.buildtools.resource.FileResources;
import jext.buildtools.source.SourceTypes;
import jext.buildtools.source.java.JavaSources;
import jext.buildtools.util.FileFilters;
import jext.buildtools.util.NamedObject;
import jext.buildtools.util.PathName;
import jext.util.logging.Logger;
import jext.nio.file.FilteredFileVisitor;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;
import jext.util.SetUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseModule extends NamedObject implements Module {

    // ----------------------------------------------------------------------
    // Protected fields
    // ----------------------------------------------------------------------

    protected Project project;
    protected File moduleDir;

    protected List<File> directories;
    protected List<Source> sources;
    protected Libraries libraries;
    protected List<Module> dependencies;

    protected Types types;

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

        // this.sources = new JavaSources(this);
        // this.resources = new FileResources(this);
        this.libraries = new JarLibraries(this);
        this.types = new SourceTypes(this);

        // List<String> resources = PropertiesUtils.getValues(project.getProperties(), Project.MODULE_RESOURCES);
        // List<String> excludes = PropertiesUtils.getValues(project.getProperties(), Project.MODULE_EXCLUDE);

        // this.resources.setIncludes(resources);
        // this.resources.setExcludes(excludes);
        // this.sources.setExcludes(excludes);
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

    // ----------------------------------------------------------------------
    // Collections
    // ----------------------------------------------------------------------

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        sources = new ArrayList<>();

        getDirectories().forEach(dir -> {
            List<Source> srclist = getBaseProject().getSources(dir, this);
            sources.addAll(srclist);
        });

        return sources;
    }

    @Override
    public Types getTypes() {
        return types;
    }

    // ----------------------------------------------------------------------
    // Resources
    // ----------------------------------------------------------------------

    @Override
    public List<Resource> getResources() {

        // local resources
        List<Resource> resources = new ArrayList<>();

        resources.addAll(getBaseProject().getResources(moduleDir, this));

        // sub resources
        getDirectories().forEach(dir -> {
            List<Resource> reslist = getBaseProject().getResources(dir, this);
            resources.addAll(reslist);
        });

        return resources;
    }

    // ----------------------------------------------------------------------
    // Dependencies
    // ----------------------------------------------------------------------

    @Override
    public List<Module> getDependencies(boolean recursive) {
        if (dependencies == null)
            dependencies = getDependencies();

        if (!recursive)
            return dependencies;
        else
            return getRecursiveDependencies();
    }

    protected List<Module> getDependencies() {

        class DepInfo implements Comparable<DepInfo> {
            Module dmodule;
            int size;

            DepInfo(Module m, int s) {
                dmodule = m;
                size = s;
            }

            @Override
            public int compareTo(DepInfo that) {
                // decreasing order
                return that.size - this.size;
            }
        }

        Types types = getTypes();
        List<Module> dependencies = project.getModules().stream()
            // skip itself
            .filter(dmodule -> !dmodule.getName().equals(this.getName()))
            // evaluate the type intersection size
            .map(dmodule -> new DepInfo(dmodule,
                SetUtils.intersection(
                    types.getImportedTypes(),
                    dmodule.getTypes().getDefinedTypes()).size()))
            // remove invalid dependencies (intersection size == 0)
            .filter(depinfo -> depinfo.size > 0)
            // order by intersection size in decreasing way
            .sorted()
            // extract the module
            .map(depInfo -> depInfo.dmodule)
            // collect the modules
            .collect(Collectors.toList());

        return dependencies;
    }

    private List<Module> getRecursiveDependencies() {
        Queue<Module> toVisit = new LinkedList<>(getDependencies(false));

        Set<Module> visited = new HashSet<>();

        while (!toVisit.isEmpty()) {
            Module dmodule = toVisit.remove();
            if (visited.contains(dmodule))
                continue;

            visited.add(dmodule);
            toVisit.addAll(dmodule.getDependencies(false));
        }

        return new ArrayList<>(visited);
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public Libraries getLibraries() {
        return libraries;
    }

    public List<File> getLocalLibraries() {
        List<File> libraryFiles = new ArrayList<>();

        getDirectories().forEach(dir ->{
            FileUtils.listFiles(libraryFiles, dir, FileFilters.IS_JAR);
        });
        return libraryFiles;
    }

    public abstract List<MavenCoords> getMavenLibraries();

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    public List<File> getDirectories() {
        if (directories == null)
            directories = getBaseProject().getDirectories(moduleDir);
        return directories;
    }

    public BaseProject getBaseProject() {
        return (BaseProject) project;
    }

}
