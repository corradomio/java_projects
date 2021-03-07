package jext.javaparser.analysis;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
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
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import jext.javaparser.symbolsolver.resolution.typesolvers.TypeSolverWithNamespace;
import jext.javaparser.util.JPUtils;
import jext.logging.Logger;


public class BaseVoidVisitorAdapter extends VoidVisitorWithDefaults<Void> {

    // ----------------------------------------------------------------------
    // Private Fields
    // ----------------------------------------------------------------------

    protected Logger logger = Logger.getLogger(getClass());

    protected CompilationUnit cu;
    protected TypeSolver ts;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public BaseVoidVisitorAdapter() {

    }

    // ----------------------------------------------------------------------
    // Operations
    // ----------------------------------------------------------------------

    protected void analyze(CompilationUnit cu, TypeSolver ts) {
        this.cu = cu;
        this.ts = ts;

        try {
            attach();
            visit(cu, null);
        }
        finally {
            detach();
        }
    }

    private void attach() {
        if (ts == null) return;
        SymbolResolver symbolResolver = new JavaSymbolSolver(ts);
        cu.setData(Node.SYMBOL_RESOLVER_KEY, symbolResolver);
    }

    private void detach() {
        cu.removeData(Node.SYMBOL_RESOLVER_KEY);
        JPUtils.removeTypeSolver(ts);
    }

    protected TypeSolverWithNamespace tsx() {
        return (TypeSolverWithNamespace) ts;
    }

    // ----------------------------------------------------------------------
    // Visit
    // ----------------------------------------------------------------------

    @Override
    public void visit(AnnotationDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(AnnotationMemberDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayAccessExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayCreationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayInitializerExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(AssertStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(AssignExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(BinaryExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(BlockComment n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(BlockStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(BooleanLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(BreakStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(CastExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(CatchClause n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(CharLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(CompilationUnit n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ConditionalExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ConstructorDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ContinueStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(DoStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(DoubleLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(EmptyStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(EnclosedExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumConstantDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ExplicitConstructorInvocationStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ExpressionStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldAccessExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ForEachStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ForStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(IfStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(InitializerDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(InstanceOfExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(IntegerLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(JavadocComment n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(LabeledStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(LineComment n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(LongLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MarkerAnnotationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MemberValuePair n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(NameExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(NormalAnnotationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(NullLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(PackageDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(Parameter n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(PrimitiveType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(Name n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SimpleName n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ArrayCreationLevel n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(IntersectionType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(UnionType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ReturnStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(StringLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SuperExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SwitchEntry n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SwitchStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SynchronizedStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ThisExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ThrowStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(TryStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(LocalClassDeclarationStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(TypeParameter n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(UnaryExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(UnknownType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarator n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VoidType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(WhileStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(WildcardType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(LambdaExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodReferenceExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(TypeExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(NodeList n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ImportDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ModuleDeclaration n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ModuleRequiresDirective n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ModuleExportsDirective n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ModuleProvidesDirective n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ModuleUsesDirective n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ModuleOpensDirective n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(UnparsableStmt n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(ReceiverParameter n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(VarType n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(Modifier n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(SwitchExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(TextBlockLiteralExpr n, Void arg) {
        super.visit(n, arg);
    }

    @Override
    public void visit(YieldStmt n, Void arg) {
        super.visit(n, arg);
    }
}
