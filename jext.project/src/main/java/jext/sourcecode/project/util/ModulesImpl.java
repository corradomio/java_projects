package jext.sourcecode.project.util;

import jext.util.logging.Logger;
import jext.name.Name;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.util.HashMap;

import java.util.ArrayList;
import java.util.Map;

public class ModulesImpl extends ArrayList<Module> implements Modules {

    private static final Logger logger = Logger.getLogger(Modules.class);

    private Map<String, Module> nameMap = new HashMap<>();
    private Map<String, Module> idMap   = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ModulesImpl() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    public boolean hasModule(String nameOrId) {
        return idMap.containsKey(nameOrId) || nameMap.containsKey(nameOrId);
    }

    public boolean add(Module module) {
        String fullname = module.getName().getFullName();
        if (nameMap.containsKey(fullname)) {
            // In C#, it is possible to have TWO o more ".csproj" files in
            // the SAME directory.
            // This is not a problem: we consider only ONE module

            // logger.errorf("Duplicated module %s", fullname);
            return false;
        }

        nameMap.put(module.getName().getFullName(), module);
        nameMap.put(module.getId(), module);
        return super.add(module);
    }

    @Override
    public Module getModule() {
        return getModule(Project.ROOT_MODULE_NAME);
    }

    @Override
    public Module getModule(Name name) {
        return getModule(name.getFullName());
    }

    @Override
    public Module getModule(String nameOrId) {
        if (nameOrId.isEmpty() || nameOrId.equals("0"))
            nameOrId = Project.ROOT_MODULE_NAME;

        if (nameMap.containsKey(nameOrId))
            return nameMap.get(nameOrId);
        if (idMap.containsKey(nameOrId))
            return idMap.get(nameOrId);

        for (Module module : this) {
            if (module.getId().equals(nameOrId))
                return module;
            if (module.getRefId().equals(nameOrId))
                return module;
            if (module.getName().getFullName().equals(nameOrId))
                return module;
            if (module.getPath().equals(nameOrId))
                return module;

            // DANGEROUS
            // if (module.getName().getName().equals(nameOrId))
            //     return module;
        }

        return null;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
