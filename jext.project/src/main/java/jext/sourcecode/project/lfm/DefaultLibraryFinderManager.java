package jext.sourcecode.project.lfm;

import jext.sourcecode.project.LibraryFinder;
import jext.sourcecode.project.LibraryFinderManager;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.csharp.CSharpLibraryFinder;
import jext.sourcecode.project.java.JavaLibraryFinder;
import jext.sourcecode.project.none.NullLibraryFinder;
import jext.sourcecode.project.python.PythonLibraryFinder;
import jext.util.HashMap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultLibraryFinderManager implements LibraryFinderManager {

    // ----------------------------------------------------------------------
    // Singleton methods
    // ----------------------------------------------------------------------

    private static DefaultLibraryFinderManager instance = new DefaultLibraryFinderManager();

    public static DefaultLibraryFinderManager instance() {
        return instance;
    }

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Map<String, LibraryFinder> lfinders = new HashMap<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private DefaultLibraryFinderManager() {
        register(new JavaLibraryFinder());
        register(new PythonLibraryFinder());
        register(new CSharpLibraryFinder());
        register(NullLibraryFinder.instance());
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Collection<LibraryFinder> getLibraryFinders() {
        return lfinders.values();
    }

    @Override
    public LibraryFinder getLibraryFinder(String language) {
        return lfinders.get(language);
    }

    @Override
    public LibraryFinder newLibraryFinder(Project project) {
        return getLibraryFinder(project.getLanguage()).newFinder(project);
    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    private void register(LibraryFinder lfinder) {
        lfinders.put(lfinder.getLanguage(), lfinder);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
