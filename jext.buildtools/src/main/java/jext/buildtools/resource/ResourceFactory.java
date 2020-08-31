package jext.buildtools.resource;

import jext.buildtools.Module;
import jext.buildtools.Resource;

import java.io.File;

public class ResourceFactory {

    public static Resource newResource(File resourceFile, Module module) {
        String name = resourceFile.getName();
        if (name.endsWith(".xml"))
            return new XMLResourceFile(resourceFile, module);
        if (name.endsWith(".properties"))
            return new PropertiesResourceFile(resourceFile, module);
        else
            return new ResourceFile(resourceFile, module);
    }
}
