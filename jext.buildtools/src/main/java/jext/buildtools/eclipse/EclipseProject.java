package jext.buildtools.eclipse;

import jext.buildtools.Project;
import jext.buildtools.Module;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EclipseProject implements Project {

    private File projectDir;
    private List<EclipseModule> modules;

    public EclipseProject(File projectDir) {
        this.projectDir = projectDir;
    }

    public String getName() {
        return projectDir.getName();
    }

    @Override
    public File getProjectDir() {
        return projectDir;
    }

    @Override
    public List<EclipseModule> getModules() {
        if (modules == null)
            retrieveModules();
        return modules;
    }

    @Override
    public Module getModule(String name) {
        for (EclipseModule module : getModules()) {
            String moduleName = module.getName().toString();
            if (moduleName.equals(name)|| moduleName.endsWith(name))
                return module;
        }
        return null;
    }

    private void retrieveModules() {
        modules = new ArrayList<>();

        FileUtils.asList(projectDir.listFiles(file -> {
            File dotProject = new File(file, ".project");
            return file.isDirectory() && dotProject.exists();
        })).forEach(moduleDir -> {
            modules.add(new EclipseModule(moduleDir, this));
        });
    }

}
