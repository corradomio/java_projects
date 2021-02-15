package jext.sourcecode.project;

import jext.sourcecode.project.util.SourceInfo;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface Source extends Resource {

    SourceInfo getSourceInfo();

    Optional<File> getSourceRoot();

    String getLanguage();

    List<? extends Type> getTypes();

    List<? extends RefType> getUsedTypes();

}
