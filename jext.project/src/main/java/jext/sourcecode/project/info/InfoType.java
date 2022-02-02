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
import jext.sourcecode.project.TypeParam;
import jext.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
    public int getTypeParametersCount() {
        return 0;
    }

    @Override
    public List<TypeParam> getTypeParameters() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public Name getNamespace() {
        return name.getParent();
    }

    @Override
    public String getDigest() {
        return getSource().getDigest();
    }

    @Override
    public Set<String> getModifiers() {
        return Collections.emptySet();
    }

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
