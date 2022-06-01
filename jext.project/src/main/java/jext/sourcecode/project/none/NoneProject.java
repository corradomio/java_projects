package jext.sourcecode.project.none;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Sources;
import jext.sourcecode.project.util.BaseProject;
import jext.sourcecode.project.util.ModulesImpl;
import jext.sourcecode.project.util.SourcesImpl;

import java.io.File;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

public class NoneProject  extends BaseProject {

    private static NoneProject instance = new NoneProject();

    public static Project project() {
        return instance;
    }

    public static Module module() {
        return instance.getModules().getModule();
    }

    protected NoneProject() {
        super("none", new File("."), new Properties(), "none");
    }

    @Override
    public String getRuntimeLibrary() {
        return null;
    }

    @Override
    public Sources getSources() {
        return new SourcesImpl(new File(".")) {
            @Override
            protected Set<String> getSourceRootsNoSync() {
                return Collections.emptySet();
            }
        };
    }

    @Override
    public Modules getModules() {
        return new ModulesImpl();
    }

    @Override
    protected Module newModule(File moduleHome) {
        return new NoneModule(moduleHome, this);
    }
}
