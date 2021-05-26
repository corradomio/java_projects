package jext.sourcecode.project;

import java.util.List;
import java.util.Optional;

public interface Source extends Resource {

    SourceInfo getSourceInfo();

    Optional<String> getSourceRoot();

    String getLanguage();

    List<? extends Type> getTypes();

    List<? extends RefType> getUsedTypes();

}
