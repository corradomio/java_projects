package jext.buildtools.project.gradle.util;

import jext.buildtools.Source;
import jext.buildtools.project.gradle.GradleModule;
import jext.buildtools.source.java.JavaSources;

import java.util.List;

public class GradleSources extends JavaSources {

    public GradleSources(GradleModule module) {
        super(module);
    }

    @Override
    public List<Source> getSources() {
        return super.getSources();
    }
}
