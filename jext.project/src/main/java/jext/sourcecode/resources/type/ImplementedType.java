package jext.sourcecode.resources.type;

import jext.name.Name;

// import ae.ebtic.spl.analysis.common.EdgeDirection;
// import ae.ebtic.spl.analysis.components.Component;
// import ae.ebtic.spl.analysis.features.Feature;
// import ae.ebtic.spl.analysis.sourcecode.model.Field;
// import ae.ebtic.spl.analysis.sourcecode.model.Method;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.util.NamedObject;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.java.TypeRole;

import java.util.List;

public class ImplementedType extends NamedObject implements Type {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    private Source source;
    private TypeRole role;
    public boolean innerType;
    public int nParams;

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public ImplementedType(Name name) {
        super(name);
        this.role = TypeRole.UNKNOWN;
    }

    public ImplementedType(Name name, TypeRole role, Source source) {
        super(name);
        this.source = source;
        this.role = role;
    }

    // ----------------------------------------------------------------------
    // Properties
    // ----------------------------------------------------------------------

    @Override
    public Project getProject() {
        return source.getProject();
    }

    // -- Type conversion

    @Override
    public boolean isType() {
        return true;
    }

    @Override
    public Type asType() {
        return this;
    }

    // -- type role

    @Override
    public TypeRole getRole() {
        return role;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    // --

    // @Override
    // public int getTypeParametersCount() {
    //     return nParams;
    // }

    @Override
    public Library getLibrary() {
        return null;
    }

    @Override
    public String getLibraryId() {
        return null;
    }

    // -- Source

    @Override
    public String getSourceId() {
        return source.getId();
    }

    @Override
    public Source getSource() {
        return source;
    }

    // -- Module

    @Override
    public String getModuleId() {
        return source.getModule().getId();
    }

    @Override
    public Module getModule() {
        return source.getModule();
    }

    // -- Types/fields/methods

    // @Override
    // public List<Type> getUseTypes(TypeUse useType, EdgeDirection direction, boolean recursive, boolean refTypes) {
    //     return Collections.emptyList();
    // }

    // @Override
    // public List<Field> getFields() {
    //     return Collections.emptyList();
    // }

    // @Override
    // public List<Method> getMethods() {
    //     return Collections.emptyList();
    // }

    // -- Score

    @Override
    public List<Double> getScore() {
        return null;
    }

    // -- Other models

    // @Override
    // public List<Component> getComponents() {
    //     return Collections.emptyList();
    // }

    // @Override
    // public List<Feature> getFeatures() {
    //     return Collections.emptyList();
    // }

    // -- Entry point

    @Override
    public boolean isEntryPoint() {
        // none to do in the source code
        return false;
    }

    @Override
    public long[] getCountMethods() {
        return null;
    }

    // ----------------------------------------------------------------------
    // Implementation
    // ----------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getName().getFullName();
    }

}
