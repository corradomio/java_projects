package jext.buildtools.resource;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Resource;
import jext.buildtools.Resources;
import jext.buildtools.util.BaseModule;
import jext.io.file.FileSet;
import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FileResources implements Resources {

    private BaseModule module;
    private List<Resource> resources;
    private FileSet selector = new FileSet();

    public FileResources(Module module) {
        this.module = (BaseModule) module;
        this.selector.add(".xml");
        this.selector.add(".properties");
        this.selector.add(".gradle");
        this.selector.add("resources");
        this.selector.add("webapps");
    }

    @Override
    public void setIncludes(Collection<String> includes) {
        this.selector.addAll(includes, false);
    }

    @Override
    public void setExcludes(Collection<String> excludes) {
        this.selector.addAll(excludes, true);
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

        Set<File> resourceFiles = new HashSet<>();

        module.listDirectories().forEach(resourceDir ->{
            // for (String resourceName : resourceNames) {
            //     if (resourceName.startsWith("."))
            //         addResourceFiles(resourceFiles, resourceDir, resourceName);
            //     else
            //         addResourceDirs(resourceFiles, resourceDir, resourceName);
            // }

            resourceFiles.addAll(selector.getFiles(resourceDir));
        });

        resources = resourceFiles.stream()
                .map(resourceFile -> new ResourceFile(resourceFile, module))
                .collect(Collectors.toList());

        return resources;
    }

    private void addResourceFiles(Set<File> resourceFiles, File resourceDir, String resourceExt){
        FileUtils.listFiles(resourceFiles, resourceDir, file -> resourceExt.equals(FileUtils.getExtension(file)));
    }

    private void addResourceDirs(Set<File> resourceFiles, File resourceDir, String dirName) {
        try {
            Files.walkFileTree(resourceDir.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    File resourceDir = dir.toFile();
                    if (dirName.equals(resourceDir.getName()))
                        FileUtils.listFiles(resourceFiles, resourceDir);
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });
        }
        catch(IOException e) { }
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
