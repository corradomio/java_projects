package jext.javaparser.analysis;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.CompactConstructorDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleProvidesDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.modules.ModuleUsesDirective;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.IntersectionType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.GenericVisitor;

import java.util.ArrayList;
import java.util.List;

public class ListVisitorAdapter<R, A> implements GenericVisitor<R, A> {

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    private List<GenericVisitor<R, A>> vlist = new ArrayList<>();

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ListVisitorAdapter() {

    }

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public ListVisitorAdapter<R, A> add(GenericVisitor<R, A> v) {
        this.vlist.add(v);
        return this;
    }

    public ListVisitorAdapter<R, A> addAll(List<GenericVisitor<R, A>> vlist) {
        this.vlist.addAll(vlist);
        return this;
    }

    // ----------------------------------------------------------------------
    // Visit
    // ----------------------------------------------------------------------

    @Override
    public R visit(NodeList n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(AnnotationDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(AnnotationMemberDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ArrayAccessExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ArrayCreationExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ArrayCreationLevel n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ArrayInitializerExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ArrayType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(AssertStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(AssignExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(BinaryExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(BlockComment n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(BlockStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(BooleanLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(BreakStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(CastExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(CatchClause n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(CharLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ClassExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ClassOrInterfaceDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(RecordDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(CompactConstructorDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ClassOrInterfaceType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(CompilationUnit n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ConditionalExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ConstructorDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ContinueStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(DoStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(DoubleLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(EmptyStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(EnclosedExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(EnumConstantDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(EnumDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ExplicitConstructorInvocationStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ExpressionStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(FieldAccessExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(FieldDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ForStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ForEachStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(IfStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ImportDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(InitializerDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(InstanceOfExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(IntegerLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(IntersectionType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(JavadocComment n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(LabeledStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(LambdaExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(LineComment n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(LocalClassDeclarationStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(LocalRecordDeclarationStmt n, A arg) {
        return null;
    }

    @Override
    public R visit(LongLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(MarkerAnnotationExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(MemberValuePair n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(MethodCallExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(MethodDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(MethodReferenceExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(NameExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(Name n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(NormalAnnotationExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(NullLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ObjectCreationExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(PackageDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(Parameter n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(PrimitiveType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ReturnStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SimpleName n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SingleMemberAnnotationExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(StringLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SuperExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SwitchEntry n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SwitchStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SynchronizedStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ThisExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ThrowStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(TryStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(TypeExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(TypeParameter n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(UnaryExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(UnionType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(UnknownType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(VariableDeclarationExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(VariableDeclarator n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(VoidType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(WhileStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(WildcardType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ModuleDeclaration n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ModuleRequiresDirective n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ModuleExportsDirective n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ModuleProvidesDirective n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ModuleUsesDirective n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ModuleOpensDirective n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(UnparsableStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(ReceiverParameter n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(VarType n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(Modifier n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(SwitchExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(TextBlockLiteralExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(YieldStmt n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }

    @Override
    public R visit(PatternExpr n, A arg) {
        vlist.forEach(self -> self.visit(n, arg));
        return null;
    }
}
