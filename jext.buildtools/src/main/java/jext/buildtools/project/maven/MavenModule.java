package jext.buildtools.project.maven;

import jext.buildtools.Project;
import jext.buildtools.maven.MavenPom;
import jext.buildtools.util.BaseModule;
import jext.buildtools.Module;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MavenModule extends BaseModule {
    private MavenPom pom;

    public MavenModule(File moduleDir, Project project) {
        super(moduleDir, project);
        this.pom = new MavenPom(moduleDir);
    }

    public String getId() {
        return pom.getCoords().toString();
    }

    public List<Module> getChildren() {
        List<Module> children = new ArrayList<>();

        pom.getModules().forEach(child -> {
            File dirChild = new File(moduleDir, child);
            Module dmodule = project.findModule(dirChild.getAbsolutePath());
            if (dmodule != null)
                children.add(dmodule);
        });
        return children;
    }
}
