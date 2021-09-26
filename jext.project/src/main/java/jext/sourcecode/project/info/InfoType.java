package jext.sourcecode.project.info;

import jext.java.TypeRole;
import jext.name.Name;
import jext.name.Named;
import jext.name.ObjectName;
import jext.sourcecode.project.Library;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.Project;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.Type;
import jext.sourcecode.project.TypeUse;
import jext.sourcecode.project.UseDirection;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static jext.lang.JavaUtils.PUBLIC;

public class InfoType implements Type {

    private InfoSource source;
    private Name name;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    InfoType(InfoSource source, String typeName) {
        this.source = source;
        this.name = new ObjectName(typeName);
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    @Override
    public String getId() {
        return null;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Project getProject() {
        return source.getProject();
    }

    @Override
    public Library getLibrary() {
        return null;
    }

    // @Override
    // public String getLibraryId() {
    //     return null;
    // }

    @Override
    public TypeRole getRole() {
        return TypeRole.UNKNOWN;
    }

    @Override
    public boolean isType() {
        return true;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public List<RefType> getElements() {
        return Collections.emptyList();
    }

    @Override
    public Type asType() {
        return this;
    }

    @Override
    public Set<String> getModifiers() {
        return Collections.emptySet();
    }

    @Override
    public String getVisibility() {
        return PUBLIC;
    }

    @Override
    public Set<String> getStructure() {
        return Collections.emptySet();
    }

    @Override
    public int getTypeParametersCount() {
        return 0;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    // @Override
    // public String getSourceId() {
    //     return source.getId();
    // }

    // @Override
    // public String getModuleId() {
    //     return source.getModuleId();
    // }

    // @Override
    // public String getModuleRefId() {
    //     return source.getModule().getRefId();
    // }

    @Override
    public Name getNamespace() {
        return name.getParent();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isInner() {
        return false;
    }

    @Override
    public List<Type> getUseTypes(TypeUse useType, UseDirection direction, boolean recursive, boolean refTypes) {
        return Collections.emptyList();
    }

    // @Override
    // public List<Field> getFields() {
    //     return Collections.emptyList();
    // }

    // @Override
    // public List<Method> getMethods() {
    //     return Collections.emptyList();
    // }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public Module getModule() {
        return source.getModule();
    }

    @Override
    public int compareTo(Named o) {
        return getNamespace().compareTo(o.getName());
    }
}
