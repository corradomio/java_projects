package jext.buildtools;

import jext.buildtools.maven.MavenCoords;

import java.io.File;
import java.util.List;

public interface ModuleAnalyzer {

    ProjectAnalyzer getProject();

    Name getName();

    boolean isValid();

    File getModuleDir();

    List<Name> getModuleDependencies();

    List<MavenCoords> getMavenLibraries();

    List<File> getLocalLibraries();

    List<File> getSources();

    List<File> getResources();
}
