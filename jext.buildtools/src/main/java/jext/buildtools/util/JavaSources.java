package jext.buildtools.util;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Source;
import jext.buildtools.Sources;
import jext.buildtools.java.JavaSource;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JavaSources implements Sources {

    private BaseModule module;
    private List<Source> sources;

    public JavaSources(Module module) {
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
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        List<File> sourceFiles = new ArrayList<>();

        module.listDirectories().forEach(dir ->{
            FileUtils.listFiles(sourceFiles, dir, FileFilters.IS_JAVA);
        });

        sources = sourceFiles.stream()
                .map(sourceFile -> new JavaSource(sourceFile, module))
                .collect(Collectors.toList());

        return sources;
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
