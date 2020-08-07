package jext.buildtools.project.maven;

import jext.buildtools.Project;
import jext.buildtools.maven.MavenCoords;
import jext.buildtools.maven.MavenPom;
import jext.buildtools.util.BaseModule;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MavenModule extends BaseModule {

    private MavenPom pom;

    public MavenModule(File moduleDir, Project project) {
        super(moduleDir, project);
        this.pom = new MavenPom(moduleDir);
    }

    public String getId() {
        return pom.getCoords().toString();
    }

    // public List<Module> getChildren() {
    //     List<Module> children = new ArrayList<>();
    //
    //     pom.getModules().forEach(child -> {
    //         File dirChild = new File(moduleDir, child);
    //         Module dmodule = project.findModule(dirChild.getAbsolutePath());
    //         if (dmodule != null)
    //             children.add(dmodule);
    //     });
    //     return children;
    // }

    public List<MavenCoords> listMavenLibraries() {
        return pom.getDependencyCoords()
                .stream()
                .filter(this::isLibrary)
                .collect(Collectors.toList());
    }

    private boolean isLibrary(MavenCoords coords) {
        return project.findModule(coords.toString()) == null;
    }
}
