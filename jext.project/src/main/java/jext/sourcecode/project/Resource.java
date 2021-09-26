package jext.sourcecode.project;

import jext.name.IdNamed;

import java.io.File;

public interface Resource extends IdNamed {

    // Name getName();

    String getPath();

    File   getFile();

    Module getModule();
    // String getModuleId();

    Project getProject();

    String getDigest();

    String getMimeType();

}
