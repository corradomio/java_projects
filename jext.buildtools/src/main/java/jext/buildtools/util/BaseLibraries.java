package jext.buildtools.util;

import jext.buildtools.Libraries;
import jext.buildtools.Library;
import jext.buildtools.Name;
import jext.buildtools.Module;
import jext.buildtools.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class BaseLibraries implements Libraries {

    protected BaseModule module;
    protected List<Library> libraries;

    protected BaseLibraries(Module module) {
        this.module = (BaseModule) module;
    }

    @Override
    public boolean isEmpty() {
        return getLibraries().isEmpty();
    }

    @Override
    public int size() {
        return getLibraries().size();
    }

    @Override
    public Set<Name> getRoots() {
        Set<Name> roots = new HashSet<>();
        getLibraries().forEach(library -> {
            Name root = library.getName().getParent();
            roots.add(root);
        });
        return roots;
    }

    @Override
    public List<Library> getLibraries(Name root) {
        return getLibraries().stream()
                .filter(library -> library.getName().getFullname().startsWith(root.getFullname()))
                .collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer<Library> consumer) {
        getLibraries().forEach(consumer);
    }
}
