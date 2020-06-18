package jext.buildtools.maven;

import jext.buildtools.Name;
import jext.buildtools.Project;
import jext.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MavenProject implements Project {

    private File projectDir;
    private MavenModule rootModule;
    private List<MavenModule> modules;

    public MavenProject(File projectDir) {
        this.projectDir = projectDir;
        this.rootModule = new MavenModule(this);
    }

    public String getName() {
        return projectDir.getName();
    }

    public File getProjectDir() {
        return projectDir;
    }

    public MavenModule getRootModule() {
        return rootModule;
    }

    public List<MavenModule> getModules() {
        if (modules != null)
            return modules;

        modules = FileUtils.listFiles(projectDir, file -> "pom.xml".equals(file.getName()))
                .stream()
                .map(pomFile -> new MavenModule(pomFile, this))
                .collect(Collectors.toList());

        // modules = new ArrayList<>();
        // Queue<MavenModule> toVisit = new LinkedList<>();
        // toVisit.add(rootModule);
        //
        // while (!toVisit.isEmpty()) {
        //     MavenModule visited = toVisit.remove();
        //     toVisit.addAll(visited.getModules());
        //     modules.add(visited);
        // }

        return modules;
    }

    public void analyzeStructure() {
        getModules()
                .forEach(MavenModule::analyzeStructure);
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
                Name mname = module.getName();
                if (mname.toString().equals(name) || mname.toString().endsWith(name))
                    return module;
            }
        }
        return null;
    }

    // public boolean isModule(String coords) {
    //     return getModule(coords) != null;
    // }

    // private void findPomFiles() {
    //     List<File> pomFiles = FileUtils.listFiles(projectDir, file -> "pom.xml".equals(file.getName()));
    // }

    // public void dump() {
    //     System.out.println(rootModule.getCoords());
    //     System.out.println("Modules");
    //     getModules().forEach(module -> {
    //         System.out.printf("... '%s' (%s)\n", module.getName(), module.getCoords());
    //         List<MavenModule> dmodules = module.getModuleDependencies();
    //         List<Library> dependencies = module.getDependencies();
    //         if (!dmodules.isEmpty()) {
    //             System.out.print("... ... Modules\n");
    //             dmodules.forEach(dmodule -> {
    //                 System.out.printf("... ... ... %s\n", dmodule.getName());
    //             });
    //         }
    //         if (!dependencies.isEmpty()) {
    //             System.out.print("... ... Dependencies\n");
    //             dependencies.forEach(coords -> {
    //                 System.out.printf("... ... ... %s\n", coords.toString());
    //             });
    //         }
    //     });
    //     System.out.println("Done");
    // }
}
