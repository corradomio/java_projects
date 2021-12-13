package jext.javaparser.resolution;

import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import jext.lang.JavaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ReferencedTypeDeclaration implements ResolvedReferenceTypeDeclaration {

    // ----------------------------------------------------------------------
    // Private fields
    // ----------------------------------------------------------------------

    public static final ReferencedTypeDeclaration OBJECT = new ReferencedTypeDeclaration(JavaUtils.JAVA_LANG_OBJECT, 0);

    private static final List<ResolvedTypeParameterDeclaration> NO_TYPE_PARAMS = Collections.emptyList();
    private static final List<ResolvedTypeParameterDeclaration> ONE_TYPE_PARAM = Collections.singletonList(ObjectTypeParameter.object());
    private static final List<ResolvedTypeParameterDeclaration> TWO_TYPE_PARAMS = Arrays.asList(
        ObjectTypeParameter.object(),
        ObjectTypeParameter.object());

    private final String qualifiedName;
    private List<ResolvedTypeParameterDeclaration> typeParams;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ReferencedTypeDeclaration(String qualifiedName, int nTypeParameters) {
        this.qualifiedName = qualifiedName;
        switch(nTypeParameters) {
            case 0:
                typeParams = NO_TYPE_PARAMS;
                break;
            case 1:
                typeParams = ONE_TYPE_PARAM;
                break;
            case 2:
                typeParams = TWO_TYPE_PARAMS;
                break;
            default:
                typeParams = new ArrayList<>();
                for (int i=0; i<nTypeParameters; ++i)
                    typeParams.add(ObjectTypeParameter.object());
                break;
        }
    }

    @Override
    public List<ResolvedReferenceType> getAncestors(boolean acceptIncompleteList) {
        return Collections.emptyList();
    }

    @Override
    public List<ResolvedFieldDeclaration> getAllFields() {
        return Collections.emptyList();
    }

    @Override
    public Set<ResolvedMethodDeclaration> getDeclaredMethods() {
        return Collections.emptySet();
    }

    @Override
    public Set<MethodUsage> getAllMethods() {
        return Collections.emptySet();
    }

    @Override
    public boolean isAssignableBy(ResolvedType type) {
        return false;
    }

    @Override
    public boolean isAssignableBy(ResolvedReferenceTypeDeclaration other) {
        return false;
    }

    @Override
    public boolean hasDirectlyAnnotation(String qualifiedName) {
        return false;
    }

    @Override
    public boolean isFunctionalInterface() {
        return false;
    }

    @Override
    public List<ResolvedConstructorDeclaration> getConstructors() {
        return Collections.emptyList();
    }

    @Override
    public Optional<ResolvedReferenceTypeDeclaration> containerType() {
        return Optional.empty();
    }

    @Override
    public String getPackageName() {
        return JavaUtils.namespaceOf(qualifiedName);
    }

    @Override
    public String getClassName() {
        return qualifiedName;
    }

    @Override
    public String getQualifiedName() {
        return qualifiedName;
    }

    @Override
    public String getName() {
        return JavaUtils.nameOf(qualifiedName);
    }

    @Override
    public List<ResolvedTypeParameterDeclaration> getTypeParameters() {
        return typeParams;
    }

    @Override
    public String toString() {
        return String.format("ReferencedTypeDeclaration[%s]", qualifiedName);
    }
}
