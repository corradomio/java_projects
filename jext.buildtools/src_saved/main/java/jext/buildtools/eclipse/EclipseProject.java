package jext.buildtools.eclipse;

import jext.buildtools.ModuleAnalyzer;
import jext.buildtools.Name;
import jext.buildtools.ProjectAnalyzer;
import jext.logging.Logger;
import jext.util.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EclipseProject implements ProjectAnalyzer {

    public static boolean isValid(File projectDir) {
        File classpathFile = new File(projectDir, ".classpath");
        File projectFile = new File(projectDir, ".project");

        return classpathFile.exists() && projectFile.exists();
    }


    private static Logger logger = Logger.getLogger(EclipseProject.class);
    private final File projectDir;
    private Properties properties = PropertiesUtils.empty();
    private final EclipseModule rootModule;
    private List<EclipseModule> modules;

    public EclipseProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new EclipseModule(this);
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
    public ProjectAnalyzer initialize() {
        getModules();
        return this;
    }

    @Override
    public List<EclipseModule> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();

        try {
            Files.walkFileTree(projectDir.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    File moduleDir = dir.toFile();
                    File classpathFile = new File(moduleDir, ".classpath");
                    if (classpathFile.exists())
                        modules.add(new EclipseModule(moduleDir, EclipseProject.this));
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) { }

        return modules;
    }

    @Override
    public ModuleAnalyzer getModule(String name) {
        for (EclipseModule module : getModules()) {
            Name mname = module.getName();
            if (mname.toString().equals(name) || name.length() > 0 && mname.toString().endsWith(name))
                return module;
        }
        return null;
    }

}
