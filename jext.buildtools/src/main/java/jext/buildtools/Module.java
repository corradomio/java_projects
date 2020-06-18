package jext.buildtools;

import java.util.List;

public interface Module {

    Project getProject();

    Name getName();

    boolean isValid();

    List<? extends Module> getModuleDependencies();

    List<? extends Dependency> getDependencies();
}
