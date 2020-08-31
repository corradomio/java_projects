package jext.buildtools.source;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Types;

import java.util.HashSet;
import java.util.Set;


public abstract class BaseTypes implements Types {

    protected Module module;
    private Set<Name> importedTypes;
    private Set<Name> definedTypes;

    protected BaseTypes(Module module) {
        this.module = module;
    }

    @Override
    public Set<Name> getDefinedTypes() {
        if (definedTypes != null)
            return definedTypes;

        definedTypes = new HashSet<>();

        module.getSources().forEach(source -> {
            source.getTypes().forEach(type -> {
                definedTypes.add(type);
            });
        });

        return definedTypes;
    }

    @Override
    public Set<Name> getImportedTypes() {
        if (importedTypes != null)
            return importedTypes;

        importedTypes = new HashSet<>();

        module.getSources().forEach(source -> {
            importedTypes.addAll(source.getImportedTypes());
        });

        return importedTypes;
    }

}
