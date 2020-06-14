package jext.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MavenProject {

    private File projectDir;
    private MavenModule rootModule;
    private List<MavenModule> modules;

    public MavenProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new MavenModule(projectDir, this);
    }

    public File getProjectDir() {
        return projectDir;
    }

    public List<MavenModule> getModules() {
        if (modules != null)
            return modules;

        modules = new ArrayList<>();
        Queue<MavenModule> toVisit = new LinkedList<>();
        toVisit.add(rootModule);

        while (!toVisit.isEmpty()) {
            MavenModule visited = toVisit.remove();
            toVisit.addAll(visited.getModules());
            modules.add(visited);
        }

        return modules;
    }

    public MavenModule getModule(String name) {
        boolean isCoords = name.contains(":");
        if (isCoords) {
            for (MavenModule module : getModules())
                if (module.getCoords().equals(name))
                    return module;
        }
        else {
            for (MavenModule module : getModules()) {
                String mname = module.getName();
                if (mname.equals(name) || mname.endsWith(name))
                    return module;
            }
        }
        return null;
    }

    public boolean isModule(String coords) {
        return getModule(coords) != null;
    }

    public void dump() {
        System.out.println(rootModule.getCoords());
        System.out.println("Modules");
        getModules().forEach(module -> {
            System.out.printf("... %s: %s\n", module.getName(), module.getCoords());
            List<MavenModule> dmodules = module.getModuleDependencies();
            List<MavenCoords> dependencies = module.getDependencies();
            if (!dmodules.isEmpty()) {
                System.out.printf("... ... Modules\n");
                dmodules.forEach(dmodule -> {
                    System.out.printf("... ... ... %s\n", dmodule.getName());
                });
            }
            if (!dependencies.isEmpty()) {
                System.out.printf("... ... Dependencies\n");
                dependencies.forEach(coords -> {
                    System.out.printf("... ... ... %s\n", coords.getName());
                });
            }
        });
        System.out.println("Done");
    }
}
