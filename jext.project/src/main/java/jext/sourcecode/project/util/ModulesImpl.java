package jext.sourcecode.project.util;

import jext.name.Name;
import jext.sourcecode.project.Modules;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.util.HashMap;

import java.util.ArrayList;
import java.util.Map;

public class ModulesImpl extends ArrayList<Module> implements Modules {

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

    public boolean add(Module module) {
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
            if (module.getName().getName().equals(nameOrId))
                return module;
        }

        return null;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
