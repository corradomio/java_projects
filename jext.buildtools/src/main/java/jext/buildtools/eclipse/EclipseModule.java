package jext.buildtools.eclipse;

import jext.buildtools.ModuleAnalyzer;
import jext.buildtools.Name;
import jext.buildtools.ProjectAnalyzer;
import jext.buildtools.eclipse.util.ClasspathFile;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.util.PathName;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseModule implements ModuleAnalyzer {

    private static final String JAVA = ".java";

    private EclipseProject project;
    private File moduleDir;
    private Name name;
    private ClasspathFile classpathFile;

    public EclipseModule(EclipseProject project) {
        this.project = project;
        this.moduleDir = project.getProjectDir();
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));
        this.classpathFile = new ClasspathFile(moduleDir);
    }

    public EclipseModule(File moduleDir, EclipseProject project) {
        this.project = project;
        this.moduleDir = moduleDir;
        this.name = new PathName(FileUtils.relativePath(project.getProjectDir(), moduleDir));
        this.classpathFile = new ClasspathFile(moduleDir);
    }


    @Override
    public ProjectAnalyzer getProject() {
        return project;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public boolean isValid() {
        return moduleDir.exists() && moduleDir.isDirectory() && classpathFile.exists();
    }

    @Override
    public File getModuleDir() {
        return moduleDir;
    }

    @Override
    public List<Name> getModuleDependencies() {
        return classpathFile.getModuleDependencies()
            .stream()
            .map(PathName::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<MavenCoords> getMavenLibraries() {
        return classpathFile.getMavenLibraries();
    }

    @Override
    public List<File> getLocalLibraries() {
        return classpathFile.getLocalLibraries();
    }

    @Override
    public List<File> getSources() {
        List<File> srcDirs = classpathFile.getSourceDirs();
        List<File> sources = new ArrayList<>();
        srcDirs.forEach(srcDir -> {
            FileUtils.listFiles(sources, srcDir, file -> file.getName().endsWith(JAVA));
        });
        return sources;
    }

    @Override
    public List<File> getResources() {
        List<File> resourceDirs = classpathFile.getResourceDirs();
        List<File> resources = new ArrayList<>();
        resourceDirs.forEach(srcDir -> {
            FileUtils.listFiles(resources, srcDir, file -> file.getName().endsWith(JAVA));
        });
        return resources;
    }
}
