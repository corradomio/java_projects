package jext.sourcecode.project.util;

import jext.sourcecode.project.Source;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Sources;
import jext.util.Assert;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SourcesImpl extends ArrayList<Source> implements Sources {

    private File projectHome;
    private File moduleHome;
    // private List<File> sourceRootDirectories;
    private List<File> sourceFiles;
    private Set<String> sourceRoots;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public SourcesImpl(Module module) {
        this.projectHome = module.getProject().getProjectHome();
        this.moduleHome = module.getModuleHome();
    }

    public SourcesImpl(File projectHome) {
        this.projectHome = projectHome;
        this.moduleHome = projectHome;
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    @Override
    public boolean add(Source source) {
        // if (sourceRoots == null)
        //     sourceRoots = new HashSet<>();
        // source.getSourceRoot().ifPresent(sroot -> sourceRoots.add(sroot));
        return super.add(source);
    }

    @Override
    public Source getSource(String sourceId) {
        for (Source source : this) {
            if (source.getId().equals(sourceId))
                return source;
            if (source.getName().getFullName().equals(sourceId))
                return source;
            if (source.getName().getName().equals(sourceId))
                return source;
        }
        return null;
    }

    @Override
    public Set<String> getSourceRoots() {
        if (sourceRoots != null)
            return sourceRoots;

        sourceRoots = new HashSet<>();
        for(Source source : this) {
            source.getSourceRoot().ifPresent(sourceRoots::add);
        }
        return sourceRoots;
    }

    @Override
    public List<File> getSourceRootDirectories() {
        // if (sourceRootDirectories != null)
        //     return sourceRootDirectories;
        //
        // sourceRootDirectories = getSourceRoots().stream()
        //     .map(sroot -> {
        //         if(FileUtils.isAbsolute(sroot)){
        //             return new File(sroot);
        //         }
        //         else {
        //             return new File(moduleHome, sroot);
        //         }
        //     })
        //     .peek(sourceRoot -> Assert.verify(sourceRoot.isDirectory(), "%s is not a directory", sourceRoot))
        //     .collect(Collectors.toList());
        // return sourceRootDirectories;

        return getSourceRoots().stream()
                .map(sroot -> new File(moduleHome, sroot))
                .peek(sourceRoot -> Assert.verify(sourceRoot.isDirectory(), "%s is not a directory", sourceRoot))
                .collect(Collectors.toList());
    }

    @Override
    public List<File> getSourceFiles() {
        if (sourceFiles != null)
            return sourceFiles;

        sourceFiles = new ArrayList<>();
        for(Source source : this) {
            sourceFiles.add(source.getFile());
        }
        return sourceFiles;
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
