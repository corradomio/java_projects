package jext.sourcecode.project;

import jext.sourcecode.project.util.SourceInfo;

import java.util.List;
import java.util.Optional;

public interface Source extends Resource {

    SourceInfo getSourceInfo();

    /**
     * The source root is present ONLY if the source file
     * is a ""valid"" source file, located in a ""valid""
     * directory.
     *
     * The concept of "valid" depends on the programming
     * language
     */
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
