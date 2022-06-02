package jext.sourcecode.project.util;

import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Resources;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ResourcesImpl extends ArrayList<Resource> implements Resources {

    private Module module;

    public ResourcesImpl(Module module) {
        this.module = module;
    }

    @Override
    public Resource getResource(String nameOrId) {
        File directory = FileUtils.addParentPath(module.getModuleHome(), nameOrId);

        List<File> resources = FileUtils.listFiles(directory);
        for (File resourceFile : resources) {
            Resource resource = new ResourceFile(resourceFile, module);
            if (resource.getName().getFullName().equals(nameOrId))
                return resource;
            if (resource.getId().equals(nameOrId))
                return resource;
            if (resource.getName().getName().equals(nameOrId))
                return resource;
        }

        return null;
    }

}
