package jext.sourcecode.project;

import jext.sourcecode.project.util.SourceInfo;

import java.util.List;
import java.util.Optional;

public interface Source extends Resource {

    SourceInfo getSourceInfo();

    Optional<String> getSourceRoot();

    String getLanguage();

    /**
     * List of types implemented inside the file
     * (for now just one)
     */
    List<Type> getTypes();

    /**
     * List of imports
     */
    List<RefType> getUsedTypes();

}
