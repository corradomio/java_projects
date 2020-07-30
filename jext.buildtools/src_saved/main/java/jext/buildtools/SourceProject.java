package jext.buildtools;

import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class SourceProject implements Project {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private final File projectRoot;
    private final Name name;
    private final Properties properties;

    private final List<ProjectAnalyzer> analyzers = new ArrayList<>();
    private List<Module> modules;
    // private LibraryFinder lfinder;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SourceProject(String name, File projectRoot, Properties props) {
        // super(new PathName(name));

        this.projectRoot = projectRoot;
        this.name = new PathName(name);
        this.properties = props;
    }

    public SourceProject initialize() {
        getModules();
        return this;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    public String getId() {
        return getName().toString();
    }

    public Name getName() {
        return name;
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getPath() {
        return FileUtils.getAbsolutePath(projectRoot);
    }

    public File getProjectDir() {
        return projectRoot;
    }

    public List<Module> getModules() {
        if (modules != null)
            return modules;

        // Parallel.forEach(analyzers, Project::initialize);
        analyzers.forEach(ProjectAnalyzer::initialize);

        // module names
        Set<Name> mnames = new LinkedHashSet<>();

        // collect the module names from all project analyzers
        for(ProjectAnalyzer analyzer : analyzers)
            analyzer.getModules().forEach(module -> {
                mnames.add(module.getName());
            });

        modules = new ArrayList<>();
        // create a 'SourceModule' for each name in 'mnames'
        mnames.forEach(mname -> {
            modules.add(new SourceModule(mname, this));
        });

        return modules;
    }

    public Module getModule(String name) {
        for(Module module : modules) {
            String mname  = module.getName().toString();
            if (mname.equals(name) || name.length() > 0 && mname.endsWith(name))
                return module;
        }
        return null;
    }

    public Module getModule(Name name) {
        for(Module module : modules) {
            Name mname  = module.getName();
            if (mname.equals(name))
                return module;
        }
        return null;
    }

    // @Override
    // public Project setLibraryFinder(LibraryFinder lfinder) {
    //     this.lfinder = lfinder;
    //     return this;
    // }
    //
    // @Override
    // public LibraryFinder getLibraryFinder() {
    //     return lfinder;
    // }

    // @Override
    public int compareTo(Named that) {
        return getName().compareTo(that.getName());
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    void addAnalyzer(ProjectAnalyzer analyzer) {
        this.analyzers.add(analyzer);
    }

    List<ProjectAnalyzer> getAnalyzers() {
        return analyzers;
    }

    // ----------------------------------------------------------------------
    // DEBUG
    // ----------------------------------------------------------------------

    public static void dump(ProjectAnalyzer project) {
        System.out.printf("Project %s\n", project.getName());
        project.getModules().forEach(module -> {
            System.out.printf("Module %s (%s)\n", module.getName(), module.isValid());
            System.out.println("... dmodules");
            module.getModuleDependencies()
                .forEach(dep -> {
                    System.out.printf("... ... %s\n", dep);
                });
            System.out.println("... dependencies");
            module.getMavenLibraries()
                .forEach(dep -> {
                    System.out.printf("... ... %s\n", dep);
                });
            module.getModuleDependencies()
                .forEach(dep -> {
                    System.out.printf("... ... %s\n", dep);
                });
            module.getLocalLibraries()
                .forEach(dep -> {
                    System.out.printf("... ... %s\n", dep);
                });
            System.out.println("... resources");
            module.getResources()
                .forEach(res -> {
                    System.out.printf("... ... %s\n", FileUtils.relativePath(module.getModuleDir(), res));
                });
            System.out.println("... sources");
            module.getSources()
                .forEach(src -> {
                    System.out.printf("... ... %s\n", FileUtils.relativePath(module.getModuleDir(), src));
                });
        });
        System.out.println("Done");
    }

}
