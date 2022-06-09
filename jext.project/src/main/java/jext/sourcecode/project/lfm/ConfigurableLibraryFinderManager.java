package jext.sourcecode.project.lfm;

import jext.configuration.Configuration;
import jext.configuration.HierarchicalConfiguration;
import jext.configuration.XMLConfiguration;
import jext.logging.Logger;
import jext.sourcecode.project.LibraryDownloader;
import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.lfm.csharp.CSharpFinderConfiguration;
import jext.sourcecode.project.lfm.java.JavaFinderConfiguration;
import jext.sourcecode.project.lfm.python.PythonFinderConfiguration;

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
        XMLConfiguration configuration = new XMLConfiguration(configurationFile);
        Configuration lfmconfig =  configuration.configurationAt("extlibsManager");
        return getManager(lfmconfig);
    }

    public static ConfigurableLibraryFinderManager getManager(Configuration configuration) {
        ConfigurableLibraryFinderManager lfm = new ConfigurableLibraryFinderManager();
        lfm.configure(configuration);
        return lfm;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected HierarchicalConfiguration configuration;
    protected Map<String, LibraryFinder> lfinders = new HashMap<>();

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
        this.configuration = (HierarchicalConfiguration) configuration;

        this.configuration.configurationsAt("language")
            .forEach(this::configureLanguage);
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
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
