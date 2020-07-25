package jext.buildtools;

import jext.buildtools.util.ResourceFile;

import java.io.File;

public class ResourceFactory {
    public static Resource newResource(File resourceFile, Module module) {
        return new ResourceFile(resourceFile, module);
    }
}
