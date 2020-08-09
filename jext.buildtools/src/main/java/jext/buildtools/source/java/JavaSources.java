package jext.buildtools.source.java;

import jext.buildtools.Module;
import jext.buildtools.Source;
import jext.buildtools.source.BaseSources;
import jext.buildtools.util.FileFilters;
import jext.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaSources extends BaseSources {

    public JavaSources(Module module) {
        super(module);
    }

    @Override
    public List<Source> getSources() {
        if (sources != null)
            return sources;

        List<File> sourceFiles = new ArrayList<>();

        module.listDirectories().forEach(sourceDir ->{
            // FileUtils.listFiles(sourceFiles, dir, FileFilters.IS_JAVA);
            sourceFiles.addAll(selector.getFiles(sourceDir));
        });

        sources = sourceFiles.stream()
                .map(sourceFile -> new JavaSource(sourceFile, module))
                .collect(Collectors.toList());

        return sources;
    }

}
