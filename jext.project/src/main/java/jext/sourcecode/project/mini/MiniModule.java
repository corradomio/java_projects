package jext.sourcecode.project.mini;

import jext.sourcecode.project.Project;
import jext.sourcecode.project.util.BaseModule;
import jext.sourcecode.resources.java.JavaSourceCode;

import java.io.File;
import java.util.ArrayList;

public class MiniModule extends BaseModule {

    protected MiniModule(File moduleHome, Project project) {
        super(moduleHome, project);
    }

    void addSource(File sourceFile) {
        sources = new ArrayList<>();
        sources.add(new JavaSourceCode(sourceFile, this));
    }
}
