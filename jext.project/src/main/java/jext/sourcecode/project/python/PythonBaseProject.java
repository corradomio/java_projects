package jext.sourcecode.project.python;

import jext.io.file.FilePatterns;
import jext.python.PythonConstants;
import jext.sourcecode.project.LibrarySet;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.python.util.PythonProjectLibrary;
import jext.sourcecode.project.python.util.PythonSourcesImpl;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.SourcesImpl;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.List;
import java.util.Properties;

public abstract class PythonBaseProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    private static final String DEFAULT_SOURCES = ".py";
    private static final String DEFAULT_RESOURCES = ".xml,.properties,.json";
    private static final String DEFAULT_EXCLUDES = ".*";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    protected PythonBaseProject(String projectName, File projectHome, Properties properties, String projectType) {
        super(projectName, projectHome, properties, projectType);

        this.properties.setProperty(Project.PROJECT_LANGUAGE, PythonConstants.PYTHON);

        // Default properties:
        //
        //      project.type
        //      module.exclude.$
        //      module.sources.$
        //      module.resources.$
        //
        if (!this.properties.containsKey(PROJECT_TYPE))
            this.properties.put(PROJECT_TYPE, getProjectType());
        if (!this.properties.containsKey(GLOBAL_MODULE_EXCLUDE))
            this.properties.put(GLOBAL_MODULE_EXCLUDE, DEFAULT_EXCLUDES);
        if (!this.properties.containsKey(GLOBAL_MODULE_SOURCES))
            this.properties.put(GLOBAL_MODULE_SOURCES, DEFAULT_SOURCES);
        if (!this.properties.containsKey(GLOBAL_MODULE_RESOURCES))
            this.properties.put(GLOBAL_MODULE_RESOURCES, DEFAULT_RESOURCES);

        List<String> sources = PropertiesUtils.getValues(this.getProperties(), MODULE_SOURCES);
        List<String> resources = PropertiesUtils.getValues(this.getProperties(), MODULE_RESOURCES);
        List<String> excludes = PropertiesUtils.getValues(this.getProperties(), MODULE_EXCLUDE);

        // file selectors
        this.fpSources = new FilePatterns().addAll(sources);
        this.fpResources = new FilePatterns().addAll(resources);
        this.fpExcludes = new FilePatterns().addAll(excludes);
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        if (sources != null)
            return sources;

        sources = new PythonSourcesImpl(getProjectHome());
        for (Module module : getModules())
            sources.addAll(module.getSources());
        return sources;
    }

    // ----------------------------------------------------------------------
    // Libraries
    // ----------------------------------------------------------------------

    @Override
    public LibrarySet getLibraries() {
        if (libraries != null)
            return libraries;

        super.getLibraries();
        libraries.add(new PythonProjectLibrary(this));
        return libraries;
    }

    // ----------------------------------------------------------------------
    // Runtime library
    // ----------------------------------------------------------------------

    @Override
    public String getRuntimeLibrary() {
        String rtLibrary = properties.getProperty(RUNTIME_LIBRARY, null);

        if (rtLibrary == null)
            rtLibrary = PythonGuessRuntimeLibrary.DEFAULT_PYTHON_RUNTIME_LIBRARY;

        return rtLibrary;
    }

    // ----------------------------------------------------------------------
    // File & directory exclusions
    // ----------------------------------------------------------------------

    boolean isExcluded(File filePath) {
        return fpExcludes.accept(projectHome, filePath);
    }

    boolean isSourceFile(File filePath) {
        return fpSources.accept(projectHome, filePath);
    }

}