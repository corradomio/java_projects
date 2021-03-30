package jext.javaparser.resolution;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedConstructorDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import jext.lang.JavaUtils;

import java.util.Collections;
import java.util.List;

public class ReferenceConstructorDeclaration implements ResolvedConstructorDeclaration {

    private String qualifiedName;
    private int nParameters;

    public ReferenceConstructorDeclaration(String typeName, String methodName, int nParameters) {
        this.qualifiedName = JavaUtils.qualifiedName(typeName, methodName);
        this.nParameters = nParameters;
    }

    public ReferenceConstructorDeclaration(String qualifiedName, int nParameters) {
        this.qualifiedName = qualifiedName;
        this.nParameters = nParameters;
    }

    @Override
    public String getQualifiedName() {
        return qualifiedName;
    }

    @Override
    public ResolvedReferenceTypeDeclaration declaringType() {
        return null;
    }

    @Override
    public int getNumberOfParams() {
        return nParameters;
    }

    @Override
    public String getSignature() {
        return String.format("%s/%d", qualifiedName, nParameters);
    }

    @Override
    public ResolvedParameterDeclaration getParam(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNumberOfSpecifiedExceptions() {
        return 0;
    }

    @Override
    public ResolvedType getSpecifiedException(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AccessSpecifier accessSpecifier() {
        return null;
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
