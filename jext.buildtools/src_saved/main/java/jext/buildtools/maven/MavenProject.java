package jext.buildtools.maven;

import jext.buildtools.Name;
import jext.buildtools.ProjectAnalyzer;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MavenProject implements ProjectAnalyzer, MavenConst {

    /**
     * A project is a Maven project IF
     * 1) the project directory contains the file 'pom.xml' OR
     * 2) SEVERAL subdirectories of the current directory contain the file 'pom.xml'
     *
     * @param projectDir
     * @return
     */
    public static boolean isValid(File projectDir) {
        File pomFile = new File(projectDir, MavenPom.POM);
        if (pomFile.exists())
            return true;

        List<File> dirs = FileUtils.asList(projectDir.listFiles(File::isDirectory));
        long count = dirs.stream()
            .filter(moduleDir -> new File(moduleDir, MavenPom.POM).exists())
            .count();
        return count > 1;
    }

    private File projectDir;
    private MavenModule rootModule;
    private List<MavenModule> modules;

    public MavenProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new MavenModule(this);
    }

    @Override
    public String getName() {
        return projectDir.getName();
    }

    @Override
    public File getProjectDir() {
        return projectDir;
    }

    @Override
    public MavenProject initialize() {
        getModules();
        return this;
    }

    @Override
    public List<MavenModule> getModules() {
        if (modules != null)
            return modules;

        modules = FileUtils.listFiles(projectDir, file -> "pom.xml".equals(file.getName()))
            .stream()
            .map(pomFile -> new MavenModule(pomFile, this))
            .collect(Collectors.toList());

        modules.forEach(MavenModule::initialize);

        return modules;
    }

    @Override
    public MavenModule getModule(String name) {
        boolean isCoords = name.contains(":");
        if (isCoords) {
            for (MavenModule module : getModules())
                if (module.getCoords().equals(name))
                    return module;
        }
        else {
            for (MavenModule module : getModules()) {
                Name mname = module.getName();
                if (mname.toString().equals(name) || name.length() > 0 && mname.toString().endsWith(name))
                    return module;
            }
        }
        return null;
    }

}
