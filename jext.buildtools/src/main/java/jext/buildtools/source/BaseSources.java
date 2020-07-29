package jext.buildtools.source;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Source;
import jext.buildtools.Sources;
import jext.buildtools.util.BaseModule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class BaseSources implements Sources {

    protected BaseModule module;
    protected List<Source> sources;

    protected BaseSources(Module module) {
        this.module = (BaseModule) module;
    }

    @Override
    public boolean isEmpty() {
        return getSources().isEmpty();
    }

    @Override
    public int size() {
        return getSources().size();
    }

    @Override
    public Set<Name> getRoots() {
        Set<Name> roots = new HashSet<>();
        getSources().forEach(source -> {
            roots.add(source.getRoot());
        });
        return roots;
    }

    @Override
    public List<Source> getSources(Name root) {
        return getSources().stream()
                .filter(source -> source.getName().getFullname().startsWith(root.getFullname()))
                .collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer<Source> consumer) {
        getSources().forEach(consumer);
    }
}
