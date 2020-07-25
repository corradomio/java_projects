package jext.buildtools.util;

import jext.buildtools.Resource;

import java.io.File;

public class FileResource extends BaseResource implements Resource {

    public FileResource(File resourceFile, BaseModule module) {
        super(resourceFile, module);

    }
}
