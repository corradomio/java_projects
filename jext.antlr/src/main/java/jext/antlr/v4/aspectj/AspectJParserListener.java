// Generated from AspectJParser.g4 by ANTLR 4.10.1
package jext.antlr.v4.aspectj;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AspectJParser}.
 */
public interface AspectJParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(AspectJParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(AspectJParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#aspectBody}.
	 * @param ctx the parse tree
	 */
	void enterAspectBody(AspectJParser.AspectBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#aspectBody}.
	 * @param ctx the parse tree
	 */
	void exitAspectBody(AspectJParser.AspectBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(AspectJParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(AspectJParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#aspectBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAspectBodyDeclaration(AspectJParser.AspectBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#aspectBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAspectBodyDeclaration(AspectJParser.AspectBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaration(AspectJParser.MemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaration(AspectJParser.MemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(AspectJParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(AspectJParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classPattern}.
	 * @param ctx the parse tree
	 */
	void enterClassPattern(AspectJParser.ClassPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classPattern}.
	 * @param ctx the parse tree
	 */
	void exitClassPattern(AspectJParser.ClassPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classPatternList}.
	 * @param ctx the parse tree
	 */
	void enterClassPatternList(AspectJParser.ClassPatternListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classPatternList}.
	 * @param ctx the parse tree
	 */
	void exitClassPatternList(AspectJParser.ClassPatternListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#aspectDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAspectDeclaration(AspectJParser.AspectDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#aspectDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAspectDeclaration(AspectJParser.AspectDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#advice}.
	 * @param ctx the parse tree
	 */
	void enterAdvice(AspectJParser.AdviceContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#advice}.
	 * @param ctx the parse tree
	 */
	void exitAdvice(AspectJParser.AdviceContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#adviceSpec}.
	 * @param ctx the parse tree
	 */
	void enterAdviceSpec(AspectJParser.AdviceSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#adviceSpec}.
	 * @param ctx the parse tree
	 */
	void exitAdviceSpec(AspectJParser.AdviceSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#perClause}.
	 * @param ctx the parse tree
	 */
	void enterPerClause(AspectJParser.PerClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#perClause}.
	 * @param ctx the parse tree
	 */
	void exitPerClause(AspectJParser.PerClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#pointcutDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPointcutDeclaration(AspectJParser.PointcutDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#pointcutDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPointcutDeclaration(AspectJParser.PointcutDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#pointcutExpression}.
	 * @param ctx the parse tree
	 */
	void enterPointcutExpression(AspectJParser.PointcutExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#pointcutExpression}.
	 * @param ctx the parse tree
	 */
	void exitPointcutExpression(AspectJParser.PointcutExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterCallPointcut(AspectJParser.CallPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitCallPointcut(AspectJParser.CallPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExecutionPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterExecutionPointcut(AspectJParser.ExecutionPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExecutionPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitExecutionPointcut(AspectJParser.ExecutionPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InitializationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterInitializationPointcut(AspectJParser.InitializationPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InitializationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitInitializationPointcut(AspectJParser.InitializationPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreInitializationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterPreInitializationPointcut(AspectJParser.PreInitializationPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreInitializationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitPreInitializationPointcut(AspectJParser.PreInitializationPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StaticInitializationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterStaticInitializationPointcut(AspectJParser.StaticInitializationPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StaticInitializationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitStaticInitializationPointcut(AspectJParser.StaticInitializationPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterGetPointcut(AspectJParser.GetPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitGetPointcut(AspectJParser.GetPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterSetPointcut(AspectJParser.SetPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitSetPointcut(AspectJParser.SetPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HandlerPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterHandlerPointcut(AspectJParser.HandlerPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HandlerPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitHandlerPointcut(AspectJParser.HandlerPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdviceExecutionPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAdviceExecutionPointcut(AspectJParser.AdviceExecutionPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdviceExecutionPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAdviceExecutionPointcut(AspectJParser.AdviceExecutionPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WithinPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterWithinPointcut(AspectJParser.WithinPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WithinPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitWithinPointcut(AspectJParser.WithinPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WithinCodePointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterWithinCodePointcut(AspectJParser.WithinCodePointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WithinCodePointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitWithinCodePointcut(AspectJParser.WithinCodePointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CFlowPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterCFlowPointcut(AspectJParser.CFlowPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CFlowPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitCFlowPointcut(AspectJParser.CFlowPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CFlowBelowPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterCFlowBelowPointcut(AspectJParser.CFlowBelowPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CFlowBelowPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitCFlowBelowPointcut(AspectJParser.CFlowBelowPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterIfPointcut(AspectJParser.IfPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitIfPointcut(AspectJParser.IfPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ThisPointcutPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterThisPointcutPointcut(AspectJParser.ThisPointcutPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ThisPointcutPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitThisPointcutPointcut(AspectJParser.ThisPointcutPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TargetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterTargetPointcut(AspectJParser.TargetPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TargetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitTargetPointcut(AspectJParser.TargetPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArgsPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterArgsPointcut(AspectJParser.ArgsPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArgsPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitArgsPointcut(AspectJParser.ArgsPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationThisPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationThisPointcut(AspectJParser.AnnotationThisPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationThisPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationThisPointcut(AspectJParser.AnnotationThisPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationTargetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTargetPointcut(AspectJParser.AnnotationTargetPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationTargetPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTargetPointcut(AspectJParser.AnnotationTargetPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationArgsPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationArgsPointcut(AspectJParser.AnnotationArgsPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationArgsPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationArgsPointcut(AspectJParser.AnnotationArgsPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationWithinPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationWithinPointcut(AspectJParser.AnnotationWithinPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationWithinPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationWithinPointcut(AspectJParser.AnnotationWithinPointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationWithinCodePointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationWithinCodePointcut(AspectJParser.AnnotationWithinCodePointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationWithinCodePointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationWithinCodePointcut(AspectJParser.AnnotationWithinCodePointcutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationPointcut(AspectJParser.AnnotationPointcutContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationPointcut}
	 * labeled alternative in {@link AspectJParser#pointcutPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationPointcut(AspectJParser.AnnotationPointcutContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#referencePointcut}.
	 * @param ctx the parse tree
	 */
	void enterReferencePointcut(AspectJParser.ReferencePointcutContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#referencePointcut}.
	 * @param ctx the parse tree
	 */
	void exitReferencePointcut(AspectJParser.ReferencePointcutContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interTypeMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterTypeMemberDeclaration(AspectJParser.InterTypeMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interTypeMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterTypeMemberDeclaration(AspectJParser.InterTypeMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterTypeDeclaration(AspectJParser.InterTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterTypeDeclaration(AspectJParser.InterTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typePattern}.
	 * @param ctx the parse tree
	 */
	void enterTypePattern(AspectJParser.TypePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typePattern}.
	 * @param ctx the parse tree
	 */
	void exitTypePattern(AspectJParser.TypePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#simpleTypePattern}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypePattern(AspectJParser.SimpleTypePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#simpleTypePattern}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypePattern(AspectJParser.SimpleTypePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#dottedNamePattern}.
	 * @param ctx the parse tree
	 */
	void enterDottedNamePattern(AspectJParser.DottedNamePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#dottedNamePattern}.
	 * @param ctx the parse tree
	 */
	void exitDottedNamePattern(AspectJParser.DottedNamePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#optionalParensTypePattern}.
	 * @param ctx the parse tree
	 */
	void enterOptionalParensTypePattern(AspectJParser.OptionalParensTypePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#optionalParensTypePattern}.
	 * @param ctx the parse tree
	 */
	void exitOptionalParensTypePattern(AspectJParser.OptionalParensTypePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#fieldPattern}.
	 * @param ctx the parse tree
	 */
	void enterFieldPattern(AspectJParser.FieldPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#fieldPattern}.
	 * @param ctx the parse tree
	 */
	void exitFieldPattern(AspectJParser.FieldPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#fieldModifiersPattern}.
	 * @param ctx the parse tree
	 */
	void enterFieldModifiersPattern(AspectJParser.FieldModifiersPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#fieldModifiersPattern}.
	 * @param ctx the parse tree
	 */
	void exitFieldModifiersPattern(AspectJParser.FieldModifiersPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void enterFieldModifier(AspectJParser.FieldModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void exitFieldModifier(AspectJParser.FieldModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#dotOrDotDot}.
	 * @param ctx the parse tree
	 */
	void enterDotOrDotDot(AspectJParser.DotOrDotDotContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#dotOrDotDot}.
	 * @param ctx the parse tree
	 */
	void exitDotOrDotDot(AspectJParser.DotOrDotDotContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#simpleNamePattern}.
	 * @param ctx the parse tree
	 */
	void enterSimpleNamePattern(AspectJParser.SimpleNamePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#simpleNamePattern}.
	 * @param ctx the parse tree
	 */
	void exitSimpleNamePattern(AspectJParser.SimpleNamePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#methodOrConstructorPattern}.
	 * @param ctx the parse tree
	 */
	void enterMethodOrConstructorPattern(AspectJParser.MethodOrConstructorPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#methodOrConstructorPattern}.
	 * @param ctx the parse tree
	 */
	void exitMethodOrConstructorPattern(AspectJParser.MethodOrConstructorPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#methodPattern}.
	 * @param ctx the parse tree
	 */
	void enterMethodPattern(AspectJParser.MethodPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#methodPattern}.
	 * @param ctx the parse tree
	 */
	void exitMethodPattern(AspectJParser.MethodPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#methodModifiersPattern}.
	 * @param ctx the parse tree
	 */
	void enterMethodModifiersPattern(AspectJParser.MethodModifiersPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#methodModifiersPattern}.
	 * @param ctx the parse tree
	 */
	void exitMethodModifiersPattern(AspectJParser.MethodModifiersPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void enterMethodModifier(AspectJParser.MethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void exitMethodModifier(AspectJParser.MethodModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#formalsPattern}.
	 * @param ctx the parse tree
	 */
	void enterFormalsPattern(AspectJParser.FormalsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#formalsPattern}.
	 * @param ctx the parse tree
	 */
	void exitFormalsPattern(AspectJParser.FormalsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#formalsPatternAfterDotDot}.
	 * @param ctx the parse tree
	 */
	void enterFormalsPatternAfterDotDot(AspectJParser.FormalsPatternAfterDotDotContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#formalsPatternAfterDotDot}.
	 * @param ctx the parse tree
	 */
	void exitFormalsPatternAfterDotDot(AspectJParser.FormalsPatternAfterDotDotContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#throwsPattern}.
	 * @param ctx the parse tree
	 */
	void enterThrowsPattern(AspectJParser.ThrowsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#throwsPattern}.
	 * @param ctx the parse tree
	 */
	void exitThrowsPattern(AspectJParser.ThrowsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typePatternList}.
	 * @param ctx the parse tree
	 */
	void enterTypePatternList(AspectJParser.TypePatternListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typePatternList}.
	 * @param ctx the parse tree
	 */
	void exitTypePatternList(AspectJParser.TypePatternListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constructorPattern}.
	 * @param ctx the parse tree
	 */
	void enterConstructorPattern(AspectJParser.ConstructorPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constructorPattern}.
	 * @param ctx the parse tree
	 */
	void exitConstructorPattern(AspectJParser.ConstructorPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constructorModifiersPattern}.
	 * @param ctx the parse tree
	 */
	void enterConstructorModifiersPattern(AspectJParser.ConstructorModifiersPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constructorModifiersPattern}.
	 * @param ctx the parse tree
	 */
	void exitConstructorModifiersPattern(AspectJParser.ConstructorModifiersPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constructorModifier}.
	 * @param ctx the parse tree
	 */
	void enterConstructorModifier(AspectJParser.ConstructorModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constructorModifier}.
	 * @param ctx the parse tree
	 */
	void exitConstructorModifier(AspectJParser.ConstructorModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationPattern}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationPattern(AspectJParser.AnnotationPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationPattern}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationPattern(AspectJParser.AnnotationPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationTypePattern}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypePattern(AspectJParser.AnnotationTypePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationTypePattern}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypePattern(AspectJParser.AnnotationTypePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#formalParametersPattern}.
	 * @param ctx the parse tree
	 */
	void enterFormalParametersPattern(AspectJParser.FormalParametersPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#formalParametersPattern}.
	 * @param ctx the parse tree
	 */
	void exitFormalParametersPattern(AspectJParser.FormalParametersPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeOrIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeOrIdentifier(AspectJParser.TypeOrIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeOrIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeOrIdentifier(AspectJParser.TypeOrIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationOrIdentifer}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationOrIdentifer(AspectJParser.AnnotationOrIdentiferContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationOrIdentifer}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationOrIdentifer(AspectJParser.AnnotationOrIdentiferContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationsOrIdentifiersPattern}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationsOrIdentifiersPattern(AspectJParser.AnnotationsOrIdentifiersPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationsOrIdentifiersPattern}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationsOrIdentifiersPattern(AspectJParser.AnnotationsOrIdentifiersPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationsOrIdentifiersPatternAfterDotDot}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationsOrIdentifiersPatternAfterDotDot(AspectJParser.AnnotationsOrIdentifiersPatternAfterDotDotContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationsOrIdentifiersPatternAfterDotDot}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationsOrIdentifiersPatternAfterDotDot(AspectJParser.AnnotationsOrIdentifiersPatternAfterDotDotContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#argsPattern}.
	 * @param ctx the parse tree
	 */
	void enterArgsPattern(AspectJParser.ArgsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#argsPattern}.
	 * @param ctx the parse tree
	 */
	void exitArgsPattern(AspectJParser.ArgsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#argsPatternList}.
	 * @param ctx the parse tree
	 */
	void enterArgsPatternList(AspectJParser.ArgsPatternListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#argsPatternList}.
	 * @param ctx the parse tree
	 */
	void exitArgsPatternList(AspectJParser.ArgsPatternListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(AspectJParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(AspectJParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(AspectJParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(AspectJParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(AspectJParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(AspectJParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumDeclaration(AspectJParser.EnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumDeclaration(AspectJParser.EnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstant(AspectJParser.EnumConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstant(AspectJParser.EnumConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(AspectJParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(AspectJParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(AspectJParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(AspectJParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(AspectJParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(AspectJParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(AspectJParser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(AspectJParser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaration(AspectJParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaration(AspectJParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(AspectJParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(AspectJParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#enumConstantName}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstantName(AspectJParser.EnumConstantNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#enumConstantName}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstantName(AspectJParser.EnumConstantNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(AspectJParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(AspectJParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(AspectJParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(AspectJParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(AspectJParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(AspectJParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeDeclaration(AspectJParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeDeclaration(AspectJParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodRest(AspectJParser.AnnotationMethodRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodRest(AspectJParser.AnnotationMethodRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AspectJParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AspectJParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(AspectJParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(AspectJParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(AspectJParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(AspectJParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(AspectJParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(AspectJParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#createdName}.
	 * @param ctx the parse tree
	 */
	void enterCreatedName(AspectJParser.CreatedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#createdName}.
	 * @param ctx the parse tree
	 */
	void exitCreatedName(AspectJParser.CreatedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void enterInnerCreator(AspectJParser.InnerCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void exitInnerCreator(AspectJParser.InnerCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void enterSuperSuffix(AspectJParser.SuperSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void exitSuperSuffix(AspectJParser.SuperSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocationSuffix(AspectJParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocationSuffix(AspectJParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(AspectJParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(AspectJParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(AspectJParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(AspectJParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(AspectJParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(AspectJParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(AspectJParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(AspectJParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceModifier(AspectJParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceModifier(AspectJParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(AspectJParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(AspectJParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(AspectJParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(AspectJParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(AspectJParser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(AspectJParser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstants(AspectJParser.EnumConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstants(AspectJParser.EnumConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclarations(AspectJParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclarations(AspectJParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(AspectJParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(AspectJParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(AspectJParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(AspectJParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(AspectJParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(AspectJParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericMethodDeclaration(AspectJParser.GenericMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericMethodDeclaration(AspectJParser.GenericMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericConstructorDeclaration(AspectJParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericConstructorDeclaration(AspectJParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(AspectJParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(AspectJParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBodyDeclaration(AspectJParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBodyDeclaration(AspectJParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclaration(AspectJParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclaration(AspectJParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstDeclaration(AspectJParser.ConstDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstDeclaration(AspectJParser.ConstDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericInterfaceMethodDeclaration(AspectJParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericInterfaceMethodDeclaration(AspectJParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(AspectJParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(AspectJParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(AspectJParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(AspectJParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(AspectJParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(AspectJParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(AspectJParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(AspectJParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(AspectJParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(AspectJParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(AspectJParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(AspectJParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(AspectJParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(AspectJParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(AspectJParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(AspectJParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNameList(AspectJParser.QualifiedNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNameList(AspectJParser.QualifiedNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(AspectJParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(AspectJParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(AspectJParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(AspectJParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(AspectJParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(AspectJParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalParameter(AspectJParser.LastFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalParameter(AspectJParser.LastFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(AspectJParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(AspectJParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBody(AspectJParser.ConstructorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBody(AspectJParser.ConstructorBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(AspectJParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(AspectJParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationName}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationName(AspectJParser.AnnotationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationName}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationName(AspectJParser.AnnotationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairs(AspectJParser.ElementValuePairsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairs(AspectJParser.ElementValuePairsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(AspectJParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(AspectJParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterElementValueArrayInitializer(AspectJParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitElementValueArrayInitializer(AspectJParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeBody(AspectJParser.AnnotationTypeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeBody(AspectJParser.AnnotationTypeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementDeclaration(AspectJParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementDeclaration(AspectJParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementRest(AspectJParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementRest(AspectJParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodOrConstantRest(AspectJParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodOrConstantRest(AspectJParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationConstantRest(AspectJParser.AnnotationConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationConstantRest(AspectJParser.AnnotationConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(AspectJParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(AspectJParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(AspectJParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(AspectJParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(AspectJParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(AspectJParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(AspectJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(AspectJParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(AspectJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(AspectJParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#catchType}.
	 * @param ctx the parse tree
	 */
	void enterCatchType(AspectJParser.CatchTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#catchType}.
	 * @param ctx the parse tree
	 */
	void exitCatchType(AspectJParser.CatchTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void enterFinallyBlock(AspectJParser.FinallyBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void exitFinallyBlock(AspectJParser.FinallyBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void enterResourceSpecification(AspectJParser.ResourceSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void exitResourceSpecification(AspectJParser.ResourceSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#resources}.
	 * @param ctx the parse tree
	 */
	void enterResources(AspectJParser.ResourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#resources}.
	 * @param ctx the parse tree
	 */
	void exitResources(AspectJParser.ResourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(AspectJParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(AspectJParser.ResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(AspectJParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(AspectJParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(AspectJParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(AspectJParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(AspectJParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(AspectJParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(AspectJParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(AspectJParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(AspectJParser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(AspectJParser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(AspectJParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(AspectJParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(AspectJParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(AspectJParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(AspectJParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(AspectJParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(AspectJParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(AspectJParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(AspectJParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(AspectJParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(AspectJParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(AspectJParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreatorRest(AspectJParser.ArrayCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreatorRest(AspectJParser.ArrayCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(AspectJParser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(AspectJParser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocation(AspectJParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocation(AspectJParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArguments(AspectJParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArguments(AspectJParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsOrDiamond(AspectJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsOrDiamond(AspectJParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArgumentsOrDiamond(AspectJParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArgumentsOrDiamond(AspectJParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link AspectJParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(AspectJParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AspectJParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(AspectJParser.ArgumentsContext ctx);
}