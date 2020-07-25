package jext.buildtools;

import java.util.List;
import java.util.Properties;

public interface Module {

    Name getName();

    /** Project owner */
    Project getProject();

    default Module getParent() {
        return getProject().getModule(getName().getParentName());
    }

    /** Module properties */
    Properties getProperties();

    /** Path on the filesystem if it exists */
    String getPath();

    /**
     * Retrieve the (first step) dependency modules
     * Note: sometimes can be complex to identify the module dependencies
     */
    List<Module> getDependencies(boolean recursive);
    List<Module> getDependencies();

    /** Sub-modules */
    // List<Module> getModules(boolean recursive);

    /** Retrieve the module with the specified name */
    // Module getModule(String moduleName);

    /** Sources used in the module */
    List<Source> getSources();

    // Source getSource(String sourceName);

    /** Libraries used in the module */
    List<Library> getLibraries();

    // /** Retrieve the library with the specified name */
    // Library getLibrary(String libraryName);

    /**  Resources used in the module */
    List<Resource> getResources();

    // -- types

    // /** Register a type */
    // Module registerType(RefType type);

    // RefType getType(Name typeName);

    // List<? extends RefType> getTypes();

    // void saveTypes();
}
