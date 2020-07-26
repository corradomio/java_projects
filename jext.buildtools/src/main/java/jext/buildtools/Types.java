package jext.buildtools;

import java.util.Set;

public interface Types {

    Set<Name> getDefinedTypes();
    Set<Name> getDefinedNamespaces();
    Set<Name> getImportedTypes();
    Set<Name> getImportedNamespaces();
}
