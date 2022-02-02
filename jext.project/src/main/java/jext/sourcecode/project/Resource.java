package jext.sourcecode.project;

import jext.name.IdNamed;

import java.io.File;

public interface Resource extends IdNamed {

    String getPath();

    File   getFile();

    Module getModule();

    Project getProject();

    String getDigest();

    String getMimeType();

}
