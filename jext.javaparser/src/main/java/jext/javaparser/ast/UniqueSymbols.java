package jext.javaparser.ast;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleExportsDirective;
import com.github.javaparser.ast.modules.ModuleOpensDirective;
import com.github.javaparser.ast.modules.ModuleRequiresDirective;
import com.github.javaparser.ast.stmt.AssertStmt;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.UnknownType;
import com.github.javaparser.ast.type.VoidType;
import com.github.javaparser.ast.type.WildcardType;
import jext.javaparser.ast.unique.ArrayListUnique;
import jext.javaparser.ast.unique.DefaultUnique;
import jext.javaparser.ast.unique.NodeListUnique;
import jext.javaparser.ast.unique.NoneUnique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueSymbols {

    private Symbols symbols = new Symbols();
    private Map<Class<?>, Unique> uniques = new HashMap<>();
    private Set<Integer> processed = new HashSet<>();

    public UniqueSymbols() {
        DefaultUnique defaultUnique = new DefaultUnique(this);

        uniques.put(CompilationUnit.class, defaultUnique);
        uniques.put(PackageDeclaration.class, defaultUnique);
        uniques.put(ImportDeclaration.class, defaultUnique);
        uniques.put(ClassOrInterfaceDeclaration.class, defaultUnique);
        uniques.put(EnumDeclaration.class, defaultUnique);
        uniques.put(EnumConstantDeclaration.class, defaultUnique);
        uniques.put(AnnotationDeclaration.class, defaultUnique);
        uniques.put(MethodDeclaration.class, defaultUnique);
        uniques.put(Modifier.class, defaultUnique);
        uniques.put(Modifier.Keyword.class, defaultUnique);

        uniques.put(ClassOrInterfaceType.class, defaultUnique);
        uniques.put(Name.class, defaultUnique);
        uniques.put(SimpleName.class, defaultUnique);
        uniques.put(TypeParameter.class, defaultUnique);
        uniques.put(VariableDeclarator.class, defaultUnique);
        uniques.put(Parameter.class, defaultUnique);
        uniques.put(UnknownType.class, defaultUnique);

        uniques.put(BlockStmt.class, defaultUnique);
        uniques.put(IfStmt.class, defaultUnique);
        uniques.put(ExpressionStmt.class, defaultUnique);
        uniques.put(ThrowStmt.class, defaultUnique);
        uniques.put(ReturnStmt.class, defaultUnique);
        uniques.put(SwitchStmt.class, defaultUnique);
        uniques.put(SwitchEntry.class, defaultUnique);
        uniques.put(SwitchEntry.Type.class, defaultUnique);
        uniques.put(BreakStmt.class, defaultUnique);
        uniques.put(TryStmt.class, defaultUnique);
        uniques.put(CatchClause.class, defaultUnique);

        uniques.put(MemberValuePair.class, defaultUnique);
        uniques.put(ArrayCreationLevel.class, defaultUnique);

        uniques.put(NodeList.class, new NodeListUnique(this));
        uniques.put(ArrayList.class, new ArrayListUnique(this));

        uniques.put(ClassExpr.class, defaultUnique);
        uniques.put(ArrayInitializerExpr.class, defaultUnique);
        uniques.put(ObjectCreationExpr.class, defaultUnique);
        uniques.put(BinaryExpr.class, defaultUnique);
        uniques.put(UnaryExpr.class, defaultUnique);
        uniques.put(LambdaExpr.class, defaultUnique);
        uniques.put(MethodCallExpr.class, defaultUnique);
        uniques.put(VariableDeclarationExpr.class, defaultUnique);
        uniques.put(EnclosedExpr.class, defaultUnique);
        uniques.put(ConditionalExpr.class, defaultUnique);
        uniques.put(MarkerAnnotationExpr.class, defaultUnique);
        uniques.put(SingleMemberAnnotationExpr.class, defaultUnique);
        uniques.put(CastExpr.class, defaultUnique);
        uniques.put(TypeExpr.class, defaultUnique);

        uniques.put(FieldAccessExpr.class, defaultUnique);
        uniques.put(NameExpr.class, defaultUnique);
        uniques.put(NormalAnnotationExpr.class, defaultUnique);
        uniques.put(ThisExpr.class, defaultUnique);
        uniques.put(ArrayCreationExpr.class, defaultUnique);

        uniques.put(BinaryExpr.Operator.class, defaultUnique);
        uniques.put(UnaryExpr.Operator.class, defaultUnique);
        uniques.put(AssignExpr.Operator.class, defaultUnique);

        uniques.put(ModuleDeclaration.class, defaultUnique);
        uniques.put(ModuleExportsDirective.class, defaultUnique);
        uniques.put(ModuleOpensDirective.class, defaultUnique);
        uniques.put(ModuleRequiresDirective.class, defaultUnique);

        uniques.put(VoidType.class, defaultUnique);
        uniques.put(AssertStmt.class, defaultUnique);
        uniques.put(AssignExpr.class, defaultUnique);
        uniques.put(FieldDeclaration.class, defaultUnique);
        uniques.put(ForEachStmt.class, defaultUnique);
        uniques.put(ForStmt.class, defaultUnique);
        uniques.put(InstanceOfExpr.class, defaultUnique);
        uniques.put(EmptyStmt.class, defaultUnique);
        uniques.put(MethodReferenceExpr.class, defaultUnique);
        uniques.put(ArrayAccessExpr.class, defaultUnique);

        // NONE

        uniques.put(CompilationUnit.Storage.class, NoneUnique.instance());
        uniques.put(Boolean.class, NoneUnique.instance());
        uniques.put(Short.class, NoneUnique.instance());
        uniques.put(Integer.class, NoneUnique.instance());
        uniques.put(Long.class, NoneUnique.instance());
        uniques.put(Float.class, NoneUnique.instance());
        uniques.put(Double.class, NoneUnique.instance());
        uniques.put(PrimitiveType.class, NoneUnique.instance());
        uniques.put(ArrayType.class, NoneUnique.instance());
        uniques.put(WildcardType.class, NoneUnique.instance());

        uniques.put(StringLiteralExpr.class, NoneUnique.instance());
        uniques.put(BooleanLiteralExpr.class, NoneUnique.instance());
        uniques.put(IntegerLiteralExpr.class, NoneUnique.instance());
        uniques.put(LongLiteralExpr.class, NoneUnique.instance());
        uniques.put(DoubleLiteralExpr.class, NoneUnique.instance());
        uniques.put(NullLiteralExpr.class, NoneUnique.instance());
        uniques.put(CharLiteralExpr.class, NoneUnique.instance());
    }

    public <T> Unique get(Class<T> clazz) {
        if (uniques.containsKey(clazz))
            return uniques.get(clazz);

        System.out.printf("No 'unique' for %s\n", clazz);
        uniques.put(clazz, NoneUnique.instance());
        return NoneUnique.instance();
    }

    public String unique(String symbol) {
        return symbols.get(symbol);
    }



    public void analyze(Object o) {
        if (o == null) return;

        if(o.getClass().isArray()) {
            Object[] a = (Object[])o;
            for(Object e : a)
                analyze(e);
        }
        else {
            int id = System.identityHashCode(o);
            if (processed.contains(id)) return;
            processed.add(id);

            get(o.getClass()).analyze(o);
        }
    }

    public void stats() {
        symbols.dump();
    }
}
