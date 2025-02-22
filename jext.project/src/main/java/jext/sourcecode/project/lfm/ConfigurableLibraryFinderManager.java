package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.configuration.XMLConfiguration;
import jext.lang.OperatingSystem;
import jext.lang.OperatingSystemUtils;
import jext.util.logging.Logger;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.lfm.csharp.CSharpFinderConfiguration;
import jext.sourcecode.project.lfm.java.JavaFinderConfiguration;
import jext.sourcecode.project.lfm.python.PythonFinderConfiguration;
import jext.util.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static jext.sourcecode.project.csharp.CSharpConstants.CSHARP;
import static jext.sourcecode.project.java.AndroidConstants.ANDROID;
import static jext.sourcecode.project.java.JavaConstants.JAVA;
import static jext.sourcecode.project.python.PythonConstants.PYTHON;

public class ConfigurableLibraryFinderManager implements LibraryFinderManager {

    // ----------------------------------------------------------------------
    // Factory methods
    // ----------------------------------------------------------------------

    public static ConfigurableLibraryFinderManager getManager(File configurationFile) {
        Configuration lfmconfig = getConfiguration(configurationFile);
        return getManager(lfmconfig);
    }

    public static ConfigurableLibraryFinderManager getManager(Configuration configuration) {
        if (configuration == null)
            throw new IllegalArgumentException("configuration is null");
        ConfigurableLibraryFinderManager lfm = new ConfigurableLibraryFinderManager();
        lfm.configure(configuration);
        return lfm;
    }

    private static Configuration getConfiguration(File configurationFile) {
        if (!configurationFile.exists())
            throw new IllegalArgumentException(String.format("File %s not found", configurationFile));
        XMLConfiguration configuration = new XMLConfiguration(configurationFile);
        return configuration.configurationAt("extlibsManager");
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    private File configurationFile;
    private HierarchicalConfiguration configuration;
    private Map<String, LibraryFinder> lfinders = new HashMap<>();
    private long lastModified = 0;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ConfigurableLibraryFinderManager() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public Collection<LibraryFinder> getLibraryFinders() {
        return lfinders.values();
    }

    @Override
    public LibraryFinder getLibraryFinder(String language) {
        reloadConfiguration();

        LibraryFinder lfinder = lfinders.get(language);
        if (lfinder == null)
            throw new ProjectException("Unsupported language " + language);
        return lfinder;
    }

    @Override
    public LibraryFinder newLibraryFinder(Project project) {
        return getLibraryFinder(project.getLanguage()).newFinder(project);
    }

    // ----------------------------------------------------------------------
    // Configuration
    // ----------------------------------------------------------------------

    public void configure(Configuration configuration) {
        this.configurationFile = configuration.getFile();
        this.configuration = (HierarchicalConfiguration) configuration;

        replaceConfiguration();

        logger.info("configure");

        reloadConfiguration();

        logger.info("done");
    }


    private void replaceConfiguration() {
        String path = this.configuration.getString("@path", "");
        if (path.isEmpty()  || path.equals("."))
            return;

        // compose the path of the new configuration file
        File newConfigurationFile = FileUtils.toFile(configuration.getFile().getParentFile(), path);

        // if the fole doesn't exist -> exit
        if (!newConfigurationFile.exists() || !newConfigurationFile.isFile()) {
                logger.errorf("Configuration file '%s' not existent.", FileUtils.getAbsolutePath(configurationFile));
            return;
        }

        this.configurationFile = newConfigurationFile;
    }


    private void reloadConfiguration() {

        // if the file timestamp is the same -> exit
        if (lastModified == configurationFile.lastModified())
            return;

        try {
            Configuration currentConfiguration = getConfiguration(configurationFile);
            this.configuration = (HierarchicalConfiguration) currentConfiguration;
            this.lastModified = configurationFile.lastModified();
            this.lfinders = new HashMap<>();

            // configure library finders by language
            this.configuration.configurationsAt("language")
                    .forEach(this::configureLanguage);
        }
        catch (Exception e) {
            logger.errorf("Configuration file '%s' invalid.", FileUtils.getAbsolutePath(configurationFile));
        }
    }

    private void configureLanguage(Configuration configuration) {
        String language = configuration.getString("@name");
        LanguageFinderConfiguration lconfig;

        if(JAVA.equals(language))
            lconfig = new JavaFinderConfiguration();
        else if(ANDROID.equals(language))
            lconfig = new JavaFinderConfiguration();
        else if (PYTHON.equals(language))
            lconfig = new PythonFinderConfiguration();
        else if (CSHARP.equals(language))
            lconfig = new CSharpFinderConfiguration();
        else {
            logger.errorf("Missing configuration for language '%s'", language);
            lconfig = new JavaFinderConfiguration();
        }

        lconfig.configure(configuration);

        LibraryFinder lfinder = lconfig.createFinder();

        lfinders.put(lfinder.getLanguage(), lfinder);

        configured(lfinder);
    }

    private void configured(LibraryFinder lfinder) {
        logger.infof("    %s", lfinder.getLanguage());
        lfinder.getRuntimeLibraries().forEach(rtlib -> {
            logger.infof("        %s: version=%s", rtlib.getName().getName(), rtlib.getVersion());
            // rtlib.getFiles().forEach(file -> {
            //     logger.infof("        %s", file);
            // });
        });
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
