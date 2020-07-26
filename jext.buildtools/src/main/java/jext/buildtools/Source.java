package jext.buildtools;

import java.util.Set;

public interface Source extends Named {

    Module getModule();

    Name getRoot();

    String getLanguage();

    Set<Name> getTypes();
    Set<Name> getImportedTypes();
    Set<Name> getImportedNamespaces();
}
