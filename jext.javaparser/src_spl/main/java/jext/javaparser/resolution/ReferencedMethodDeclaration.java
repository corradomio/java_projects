package jext.javaparser.resolution;

import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedParameterDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import jext.lang.JavaUtils;

import java.util.Collections;
import java.util.List;

public class ReferencedMethodDeclaration implements ResolvedMethodDeclaration {

    private String qualifiedName;
    private int nParameters;

    public ReferencedMethodDeclaration(String typeName, String methodName, int nParameters) {
        this.qualifiedName = JavaUtils.fullName(typeName, methodName);
        this.nParameters = nParameters;
    }

    public ReferencedMethodDeclaration(String qualifiedName, int nParameters) {
        this.qualifiedName = qualifiedName;
        this.nParameters = nParameters;
    }

    @Override
    public ResolvedType getReturnType() {
        return null;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public boolean isDefaultMethod() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return false;
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
