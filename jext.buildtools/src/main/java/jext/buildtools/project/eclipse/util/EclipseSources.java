package jext.buildtools.project.eclipse.util;

import jext.buildtools.Module;
import jext.buildtools.Source;
import jext.buildtools.project.eclipse.EclipseModule;
import jext.buildtools.source.BaseSources;
import jext.buildtools.source.java.JavaSource;
import jext.buildtools.util.FileFilters;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseSources extends BaseSources {

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private ClasspathFile classpathFile;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public EclipseSources(Module module) {
        super(module);
        this.classpathFile = ((EclipseModule)module).getClasspathFile();
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        List<File> sourceFiles = new ArrayList<>();
        classpathFile.getSourceDirs().forEach(sourceDir -> {
            //FileUtils.listFiles(sourceFiles, sourceDir, FileFilters.IS_JAVA);
            sourceFiles.addAll(selector.getFiles(sourceDir));
        });

        sources = sourceFiles.stream()
                .map(sourceFile -> new JavaSource(sourceFile, module))
                .collect(Collectors.toList());

        return sources;
    }

}
