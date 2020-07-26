package jext.buildtools.util;

import jext.buildtools.Name;
import jext.buildtools.Types;
import jext.buildtools.Module;

import java.util.HashSet;
import java.util.Set;


public abstract class BaseTypes implements Types {

    protected Module module;
    private Set<Name> importedTypes;
    private Set<Name> importedNamespaces;
    private Set<Name> definedTypes;
    private Set<Name> definedNamespaces;

    protected BaseTypes(Module module) {
        this.module = module;
    }

    @Override
    public Set<Name> getDefinedTypes() {
        if (definedTypes != null)
            return definedTypes;

        retrieveDefinedTypes();

        return definedTypes;
    }

    @Override
    public Set<Name> getDefinedNamespaces() {
        if (definedNamespaces != null)
            return definedNamespaces;

        retrieveDefinedTypes();

        return definedNamespaces;
    }

    private void retrieveDefinedTypes() {
        definedTypes = new HashSet<>();
        definedNamespaces = new HashSet<>();

        module.getSources().forEach(source -> {
            source.getTypes().forEach(type -> {
                definedTypes.add(type);
                definedNamespaces.add(type.getParent());
            });
        });
    }

    @Override
    public Set<Name> getImportedTypes() {
        if (importedTypes != null)
            return importedTypes;

        retrieveImportedTypes();

        return importedTypes;
    }

    @Override
    public Set<Name> getImportedNamespaces() {
        if (importedNamespaces != null)
            return importedNamespaces;

        retrieveImportedTypes();

        return importedNamespaces;
    }

    private void retrieveImportedTypes() {
        importedTypes = new HashSet<>();
        importedNamespaces = new HashSet<>();

        module.getSources().forEach(source -> {
            importedTypes.addAll(source.getImportedTypes());
            importedNamespaces.addAll(source.getImportedNamespaces());
        });
    }
}
