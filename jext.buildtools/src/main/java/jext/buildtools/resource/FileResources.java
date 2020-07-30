package jext.buildtools.resource;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Resource;
import jext.buildtools.Resources;
import jext.buildtools.util.BaseModule;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FileResources implements Resources {

    private BaseModule module;
    private List<Resource> resources;
    private Set<String> extensions = new HashSet<>();

    public FileResources(Module module) {
        this.module = (BaseModule) module;
    }

    public void setExtension(Set<String> extensions) {
        this.extensions.addAll(extensions);
    }

    @Override
    public boolean isEmpty() {
        return getResources().isEmpty();
    }

    @Override
    public int size() {
        return getResources().size();
    }

    @Override
    public Set<Name> getRoots() {
        Set<Name> roots = new HashSet<>();
        getResources().forEach(library -> {
            Name root = library.getName().getParent();
            roots.add(root);
        });
        return roots;
    }

    @Override
    public List<Resource> getResources() {
        if (resources != null)
            return resources;

        List<File> resourceFiles = new ArrayList<>();

        module.listDirectories().forEach(dir ->{
            FileUtils.listFiles(resourceFiles, dir, file -> extensions.contains(FileUtils.getExtension(file)));
        });

        resources = resourceFiles.stream()
                .map(resourceFile -> new ResourceFile(resourceFile, module))
                .collect(Collectors.toList());

        return resources;
    }

    @Override
    public List<Resource> getResources(Name root) {
        return getResources().stream()
                .filter(resource -> resource.getName().getFullname().startsWith(root.getFullname()))
                .collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer<Resource> consumer) {
        getResources().forEach(consumer);
    }
}
