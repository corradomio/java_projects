package jext.sourcecode.project.python;

import jext.io.file.FilePatterns;
import jext.sourcecode.project.util.BaseProject;
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
    // Runtime library
    // ----------------------------------------------------------------------

    @Override
    public String getRuntimeLibrary() {
        String rtLibrary = properties.getProperty(RUNTIME_LIBRARY, null);

        if (rtLibrary == null)
            rtLibrary = PythonGuessRuntimeLibrary.DEFAULT_PYTHON_RUNTIME_LIBRARY;

        return rtLibrary;
    }

}
