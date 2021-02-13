package jext.sourcecode.project.util;

// import ae.ebtic.spl.analysis.components.Component;
// import ae.ebtic.spl.analysis.features.Feature;

import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Resource;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProjectUtils {

    public static List<Source> getSources(Project project) {
        List<Source> sources = new ArrayList<>();

        // for (Module module : project.getModules())
        //     for (SourceRoot sourceRoot : module.getSourceRoots())
        //         sources.addAll(sourceRoot.getSources());

        for (Module module : project.getModules())
                sources.addAll(module.getSources());

        return sources;
    }

    public static Source getSource(Project project, String nameOrId) {
        for (Module module : project.getModules()) {
            Source source = module.getSource(nameOrId);
            if (source != null)
                return source;
        }
        return null;
    }

    // ----------------------------------------------------------------------

    public static List<Resource> getResources(Project project) {
        List<Resource> resources = new ArrayList<>();

        for (Module module : project.getModules())
            resources.addAll(module.getResources());

        return resources;
    }

    public static Resource getResource(Project project, String nameOrId) {
        for (Module module : project.getModules()) {
            Resource resource = module.getResource(nameOrId);
            if (resource != null)
                return resource;
        }
        return null;
    }

    // ----------------------------------------------------------------------

    // public static List<Library> getLibraries(Project project) {
    //     Set<Library> libraries = new HashSet<>();
    //
    //     for (Module module : project.getModules())
    //         libraries.addAll(module.getLibraries());
    //
    //     return new ArrayList<>(libraries);
    // }
    //
    // public static Library getLibrary(Project project, String nameOrId) {
    //     for (Module module : project.getModules()) {
    //         Library library = module.getLibrary(nameOrId);
    //         if (library != null)
    //             return library;
    //     }
    //     return null;
    // }

    // ----------------------------------------------------------------------

    public static List<Type> getTypes(Project project) {
        List<Type> definedTypes = new ArrayList<>();
        project.getModules().forEach(module -> {
            module.getTypes().forEach(refType -> {
                definedTypes.add(refType.asType());
            });
        });

        return definedTypes;
    }

    public static RefType getType(Project project, String typeId) {
        for(Module module : project.getModules()) {
            for(RefType type : module.getTypes()) {
                if (type.getId().equals(typeId))
                    return type;
                if (type.getName().getFullName().equals(typeId))
                    return type;
            }
        }

        return null;
    }

    /**
     * Collect all module's defined types
     *
     * @param project
     * @return
     */
    public static Set<RefType> getDefinedTypes(Project project) {
        Set<RefType> definedTypes = new HashSet<>();

        project.getModules().forEach(module -> {
            definedTypes.addAll(module.getTypes());
        });

        return definedTypes;
    }

    /**
     * Collect all module's used types
     *
     * @param project
     * @return
     */
    public static Set<RefType> getUsedTypes(Project project) {
        Set<RefType> usedTypes = new HashSet<>();

        project.getModules().forEach(module -> {
            usedTypes.addAll(module.getUsedTypes());
        });

        return usedTypes;
    }

    /**
     * Scann all modules for a defined type with the specified name
     * @param project
     * @param typeName
     * @return
     */
    public static Optional<RefType> findType(Project project, String typeName) {
        for (Module module : project.getModules()) {
            for (RefType refType : module.getTypes())
                if (refType.getName().getFullName().equals(typeName) ||
                    refType.getName().getName().equals(typeName) ||
                    refType.getId().equals(typeName))
                    return Optional.of(refType);
        }
        return Optional.empty();
    }

    // ----------------------------------------------------------------------

    // public static Set<Component> getComponents(Project project) {
    //     Set<Component> components = new HashSet<>();
    //
    //     project.getModules().forEach(module -> {
    //         module.getTypes().forEach(refType -> {
    //             components.addAll(refType.asType().getComponents());
    //         });
    //     });
    //     return components;
    // }

    // ----------------------------------------------------------------------

    // public static Set<Feature> getFeatures(Project project) {
    //     Set<Feature> features = new HashSet<>();
    //
    //     project.getModules().forEach(module -> {
    //         module.getTypes().forEach(refType -> {
    //             features.addAll(refType.asType().getFeatures());
    //         });
    //     });
    //     return features;
    // }

}
