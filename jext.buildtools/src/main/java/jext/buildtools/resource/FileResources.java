package jext.buildtools.resource;

import jext.buildtools.Module;
import jext.buildtools.Name;
import jext.buildtools.Resource;
import jext.buildtools.Resources;
import jext.buildtools.project.BaseModule;
import jext.io.file.FileSet;
import jext.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FileResources implements Resources {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private BaseModule module;
    private List<Resource> resources;
    private FileSet selector = new FileSet();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public FileResources(Module module) {
        this.module = (BaseModule) module;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

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

        List<File> resourceFiles = new ArrayList<>(
            selector.getFiles(module.getDirectory())
        );

        module.getDirectories().forEach(resourceDir ->{
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
                .filter(resource -> resource.getName().getFullName().startsWith(root.getFullName()))
                .collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer<Resource> consumer) {
        getResources().forEach(consumer);
    }
}
