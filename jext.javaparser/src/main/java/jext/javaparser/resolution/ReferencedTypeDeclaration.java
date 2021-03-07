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
import org.apache.commons.jcs.access.exception.InvalidArgumentException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ReferencedTypeDeclaration implements ResolvedReferenceTypeDeclaration {

    private String qualifiedName;

    public ReferencedTypeDeclaration(String namespace, String name) {
        if (namespace == null || name == null)
            throw new InvalidArgumentException("namespace,name can not be null");
        this.qualifiedName = JavaUtils.qualifiedName(namespace, name);
    }

    public ReferencedTypeDeclaration(String qualifiedName) {
        if (qualifiedName == null)
            throw new InvalidArgumentException("qualifiedName can not be null");
        this.qualifiedName = qualifiedName;
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
        return Collections.emptyList();
    }
}
