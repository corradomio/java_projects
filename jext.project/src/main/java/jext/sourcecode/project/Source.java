package jext.sourcecode.project;

import jext.sourcecode.project.util.SourceInfo;

import java.util.List;

public interface Source extends Resource {

    SourceInfo getSourceInfo();

    // File   getFile();
    // String getPath();

    // String getModuleId();

    // Module getModule();

    // String getDigest();

    String getLanguage();

    List<Type> getTypes();

    List<RefType> getUsedTypes();

}
