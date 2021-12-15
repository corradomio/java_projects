package jext.sourcecode.project;

import java.util.List;

public interface Modules extends List<Module> {
    Module getModule();
    Module getModule(String nameOrId);
}
