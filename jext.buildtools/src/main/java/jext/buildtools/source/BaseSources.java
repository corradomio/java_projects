package jext.buildtools.source;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Source;
import jext.buildtools.Sources;
import jext.buildtools.project.BaseModule;
import jext.io.file.FileSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class BaseSources implements Sources {

    protected BaseModule module;
    protected List<Source> sources;
    protected FileSet selector = new FileSet();

    protected BaseSources(Module module) {
        this.module = (BaseModule) module;
        selector.add(".java");
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
    public void setIncludes(Collection<String> includes) {
        selector.addAll(includes, false);
    }

    @Override
    public void setExcludes(Collection<String> excludes) {
        selector.addAll(excludes, true);
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
                .filter(source -> source.getName().getFullName().startsWith(root.getFullName()))
                .collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer<Source> consumer) {
        getSources().forEach(consumer);
    }
}
