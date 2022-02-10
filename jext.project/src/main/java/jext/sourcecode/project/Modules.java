package jext.sourcecode.project;

import jext.name.Name;

import java.util.List;

public interface Modules extends List<Module> {
    Module getModule();
    Module getModule(Name name);
    Module getModule(String nameOrId);
}
