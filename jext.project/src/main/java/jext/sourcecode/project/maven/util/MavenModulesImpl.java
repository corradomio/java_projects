package jext.sourcecode.project.maven.util;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.maven.MavenModule;
import jext.sourcecode.project.util.ModulesImpl;

public class MavenModulesImpl extends ModulesImpl {

    @Override
    public Module getModule(String nameOrId) {
        for (Module module : this) {
            if (((MavenModule) module).getMavenCoords().equals(nameOrId))
                return module;
        }

        return super.getModule(nameOrId);
    }
}
