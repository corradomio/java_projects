package jext.javaparser.resolution;

import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParametrizable;
import jext.lang.JavaConstants;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ObjectTypeParameter implements ResolvedTypeParameterDeclaration {

    private static final ObjectTypeParameter INSTANCE = new ObjectTypeParameter();
    public static ResolvedTypeParameterDeclaration object() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return JavaConstants.OBJECT;
    }

    @Override
    public String getContainerQualifiedName() {
        return JavaConstants.JAVA_LANG_OBJECT;
    }

    @Override
    public String getContainerId() {
        return null;
    }

    @Override
    public ResolvedTypeParametrizable getContainer() {
        return null;
    }

    @Override
    public List<Bound> getBounds() {
        return Collections.emptyList();
    }

    @Override
    public Optional<ResolvedReferenceTypeDeclaration> containerType() {
        return Optional.empty();
    }
}