package jext.sourcecode.project.csharp;

import jext.io.file.FilePatterns;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.ModulesImpl;
import jext.util.FileUtils;
import jext.util.PropertiesUtils;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class CSharpProject extends BaseProject {

    // ----------------------------------------------------------------------
    // Constants
    // ----------------------------------------------------------------------

    public static final String TYPE = "simple";

    private static final String DEFAULT_SOURCES = ".cs";
    private static final String DEFAULT_RESOURCES = ".xml,.json,.csproj";
    private static final String DEFAULT_EXCLUDES = ".*";

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public CSharpProject(String projectName, File projectHome, Properties properties) {
        super(projectName, projectHome, properties, TYPE);

        this.properties.setProperty(Project.PROJECT_LANGUAGE, CSharpConstants.CSHARP);

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
    // Modules
    // ----------------------------------------------------------------------

    @Override
    public Modules getModules() {
        if (modules != null)
            return modules;

        modules = new ModulesImpl();

        addProjModules();
        addRootModule();

        return modules;
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new CSharpModule(moduleHome, this);
    }

    private void addProjModules() {
        List<File> projFiles = FileUtils.listFiles(this.projectHome, pathname -> pathname.getName().endsWith(".csproj"));
        projFiles.forEach(projFile -> {
            File moduleHome = projFile.getParentFile();
            Module module = newModule(moduleHome);
            modules.add(module);
        });
    }

    // ----------------------------------------------------------------------
    // RuntimeLibrary
    // ----------------------------------------------------------------------

    @Override
    public String getRuntimeLibrary() {
        String rtLibrary = properties.getProperty(RUNTIME_LIBRARY, null);
        if (rtLibrary == null)
            rtLibrary = GuessRuntimeLibrary.DEFAULT_RUNTIME_LIBRARY;

        return rtLibrary;
    }

    Library getRTLibrary() {
        String rtLibrary = getRuntimeLibrary();
        return lfinder.getRuntimeLibrary(rtLibrary);
    }

    // ----------------------------------------------------------------------
    // Sources
    // ----------------------------------------------------------------------

    @Override
    public Sources getSources() {
        return null;
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

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
