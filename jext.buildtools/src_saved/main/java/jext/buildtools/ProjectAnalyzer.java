package jext.buildtools;

import java.io.File;
import java.util.List;

public interface ProjectAnalyzer {

    String getName();

    File getProjectDir();

    List<? extends ModuleAnalyzer> getModules();

    ModuleAnalyzer getModule(String name);

    ProjectAnalyzer initialize();
}
