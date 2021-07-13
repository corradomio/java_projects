package jext.sourcecode.project;

import jext.sourcecode.project.util.SourceInfo;

import java.util.List;
import java.util.Optional;

public interface Source extends Resource {

    SourceInfo getSourceInfo();

    Optional<String> getSourceRoot();

    String getLanguage();

    List<Type> getTypes();

    List<RefType> getUsedTypes();

}
