package jext.buildtools.project.ant;

import jext.buildtools.Module;
import jext.buildtools.project.ant.util.LibrariesFinder;
import jext.buildtools.project.ant.util.SourcesFinder;
import jext.buildtools.project.BaseProject;
import jext.util.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class AntProject extends BaseProject {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static final String TYPE = "ant";
    public static final String MODULE_FILE = "build.xml";

    public static boolean isProject(File projectDir) {
        File projectFile = new File(projectDir, MODULE_FILE);
        if (projectFile.exists())
            return true;
        if (FileUtils.listFiles(projectDir, file -> file.getName().equals(MODULE_FILE)).size() > 0)
            return true;
        return false;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public AntProject(File projectDir, Properties properties) {
        super(projectDir, properties, TYPE);
        if (!properties.containsKey(PROJECT_MODULE))
            this.properties.setProperty(PROJECT_MODULE, MODULE_FILE);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    protected Module newModule(File moduleDir) {
        return new AntModule(moduleDir, this);
    }

    @Override
    public List<Module> getModules() {
        if (modules != null)
            return modules;

        SourcesFinder sfinder = new SourcesFinder(this);
        LibrariesFinder lfinder = new LibrariesFinder(this);

        sfinder.findSources();
        lfinder.findLibraries();

        Set<File> moduleDirs = new HashSet<>();
        moduleDirs.addAll(sfinder.getRoots());
        moduleDirs.addAll(lfinder.getRoots());

        if (moduleDirs.contains(getDirectory())) {
            moduleDirs.remove(getDirectory());
            moduleDirs = FileUtils.simplify(moduleDirs);
            moduleDirs.add(getDirectory());
        }
        else {
            moduleDirs = FileUtils.simplify(moduleDirs);
        }

        modules = moduleDirs.stream()
                .map(moduleDir -> new AntModule(moduleDir, this))
                .collect(Collectors.toList());

        return modules;
    }
}
