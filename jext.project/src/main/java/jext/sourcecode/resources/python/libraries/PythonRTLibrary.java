package jext.sourcecode.resources.python.libraries;

import jext.name.PathName;

import java.io.File;

public class PythonRTLibrary extends PythonLibrary {

    public PythonRTLibrary(String name, File libraryDirectory) {
        super(libraryDirectory);
        setNameWithId(PathName.of(name));
    }

}
