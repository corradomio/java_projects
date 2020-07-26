package jext.buildtools.project.eclipse;

import jext.buildtools.Module;
import jext.buildtools.Source;
import jext.buildtools.java.JavaSource;
import jext.buildtools.util.BaseSources;
import jext.buildtools.util.FileFilters;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EclipseSources extends BaseSources {

    private ClasspathFile classpathFile;

    protected EclipseSources(Module module) {
        super(module);
        this.classpathFile = ((EclipseModule)module).getClasspathFile();
    }

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        List<File> sourceFiles = new ArrayList<>();
        File moduleDir = module.getDirectory();

        classpathFile.getSourceDirs().forEach(sdir -> {
            FileUtils.listFiles(sourceFiles, sdir, FileFilters.IS_JAVA);
        });

        sources = sourceFiles.stream()
                .map(sourceFile -> new JavaSource(sourceFile, module))
                .collect(Collectors.toList());

        return sources;
    }

}
