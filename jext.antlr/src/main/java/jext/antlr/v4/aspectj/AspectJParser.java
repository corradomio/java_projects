// Generated from AspectJParser.g4 by ANTLR 4.7.2
package jext.antlr.v4.aspectj;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AspectJParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOTDOT=1, DQUOTE=2, ADVICEEXECUTION=3, ANNOTATION=4, ARGS=5, AFTER=6, 
		AROUND=7, ASPECT=8, BEFORE=9, CALL=10, CFLOW=11, CFLOWBELOW=12, DECLARE=13, 
		ERROR=14, EXECUTION=15, GET=16, HANDLER=17, INITIALIZATION=18, ISSINGLETON=19, 
		PARENTS=20, PERCFLOW=21, PERCFLOWBELOW=22, PERTARGET=23, PERTHIS=24, PERTYPEWITHIN=25, 
		POINTCUT=26, PRECEDENCE=27, PREINITIALIZATION=28, PRIVILEGED=29, RETURNING=30, 
		SET=31, SOFT=32, STATICINITIALIZATION=33, TARGET=34, THROWING=35, WARNING=36, 
		WITHIN=37, WITHINCODE=38, ANNOTATION_AFTER=39, ANNOTATION_AFTERRETURNING=40, 
		ANNOTATION_AFTERTHROWING=41, ANNOTATION_AROUND=42, ANNOTATION_ASPECT=43, 
		ANNOTATION_BEFORE=44, ANNOTATION_DECLAREPARENTS=45, ANNOTATION_DECLAREMIXIN=46, 
		ANNOTATION_DECLAREWARNING=47, ANNOTATION_DECLAREERROR=48, ANNOTATION_DECLAREPRECEDENCE=49, 
		ANNOTATION_POINTCUT=50, ANNOTATION_CONSTRUCTOR=51, ANNOTATION_DEFAULTIMPL=52, 
		ANNOTATION_FIELD=53, ANNOTATION_INTERFACES=54, ANNOTATION_TYPE=55, ANNOTATION_METHOD=56, 
		ANNOTATION_VALUE=57, AT=58, ABSTRACT=59, ASSERT=60, BOOLEAN=61, BREAK=62, 
		BYTE=63, CASE=64, CATCH=65, CHAR=66, CLASS=67, CONST=68, CONTINUE=69, 
		DEFAULT=70, DO=71, DOUBLE=72, ELSE=73, ENUM=74, EXTENDS=75, FINAL=76, 
		FINALLY=77, FLOAT=78, FOR=79, IF=80, GOTO=81, IMPLEMENTS=82, IMPORT=83, 
		INSTANCEOF=84, INT=85, INTERFACE=86, LONG=87, NATIVE=88, NEW=89, PACKAGE=90, 
		PRIVATE=91, PROTECTED=92, PUBLIC=93, RETURN=94, SHORT=95, STATIC=96, STRICTFP=97, 
		SUPER=98, SWITCH=99, SYNCHRONIZED=100, THIS=101, THROW=102, THROWS=103, 
		TRANSIENT=104, TRY=105, VOID=106, VOLATILE=107, WHILE=108, IntegerLiteral=109, 
		FloatingPointLiteral=110, BooleanLiteral=111, CharacterLiteral=112, StringLiteral=113, 
		NullLiteral=114, LPAREN=115, RPAREN=116, LBRACE=117, RBRACE=118, LBRACK=119, 
		RBRACK=120, SEMI=121, COMMA=122, DOT=123, ASSIGN=124, GT=125, LT=126, 
		BANG=127, TILDE=128, QUESTION=129, COLON=130, EQUAL=131, LE=132, GE=133, 
		NOTEQUAL=134, AND=135, OR=136, INC=137, DEC=138, ADD=139, SUB=140, MUL=141, 
		DIV=142, BITAND=143, BITOR=144, CARET=145, MOD=146, ADD_ASSIGN=147, SUB_ASSIGN=148, 
		MUL_ASSIGN=149, DIV_ASSIGN=150, AND_ASSIGN=151, OR_ASSIGN=152, XOR_ASSIGN=153, 
		MOD_ASSIGN=154, LSHIFT_ASSIGN=155, RSHIFT_ASSIGN=156, URSHIFT_ASSIGN=157, 
		Identifier=158, ELLIPSIS=159, WS=160, COMMENT=161, LINE_COMMENT=162, WS1=163, 
		COMMENT1=164, LINE_COMMENT1=165, INVALID1=166, WS2=167, COMMENT2=168, 
		LINE_COMMENT2=169, WS3=170, COMMENT3=171, LINE_COMMENT3=172, INVALID3=173, 
		WS4=174, COMMENT4=175, LINE_COMMENT4=176, INVALID4=177;
	public static final int
		RULE_typeDeclaration = 0, RULE_aspectBody = 1, RULE_classBodyDeclaration = 2, 
		RULE_aspectBodyDeclaration = 3, RULE_memberDeclaration = 4, RULE_annotation = 5, 
		RULE_classPattern = 6, RULE_classPatternList = 7, RULE_aspectDeclaration = 8, 
		RULE_advice = 9, RULE_adviceSpec = 10, RULE_perClause = 11, RULE_pointcutDeclaration = 12, 
		RULE_pointcutExpression = 13, RULE_pointcutPrimitive = 14, RULE_referencePointcut = 15, 
		RULE_interTypeMemberDeclaration = 16, RULE_interTypeDeclaration = 17, 
		RULE_typePattern = 18, RULE_simpleTypePattern = 19, RULE_dottedNamePattern = 20, 
		RULE_optionalParensTypePattern = 21, RULE_fieldPattern = 22, RULE_fieldModifiersPattern = 23, 
		RULE_fieldModifier = 24, RULE_dotOrDotDot = 25, RULE_simpleNamePattern = 26, 
		RULE_methodOrConstructorPattern = 27, RULE_methodPattern = 28, RULE_methodModifiersPattern = 29, 
		RULE_methodModifier = 30, RULE_formalsPattern = 31, RULE_formalsPatternAfterDotDot = 32, 
		RULE_throwsPattern = 33, RULE_typePatternList = 34, RULE_constructorPattern = 35, 
		RULE_constructorModifiersPattern = 36, RULE_constructorModifier = 37, 
		RULE_annotationPattern = 38, RULE_annotationTypePattern = 39, RULE_formalParametersPattern = 40, 
		RULE_typeOrIdentifier = 41, RULE_annotationOrIdentifer = 42, RULE_annotationsOrIdentifiersPattern = 43, 
		RULE_annotationsOrIdentifiersPatternAfterDotDot = 44, RULE_argsPattern = 45, 
		RULE_argsPatternList = 46, RULE_id = 47, RULE_classDeclaration = 48, RULE_typeParameter = 49, 
		RULE_enumDeclaration = 50, RULE_enumConstant = 51, RULE_interfaceDeclaration = 52, 
		RULE_methodDeclaration = 53, RULE_constructorDeclaration = 54, RULE_constantDeclarator = 55, 
		RULE_interfaceMethodDeclaration = 56, RULE_variableDeclaratorId = 57, 
		RULE_enumConstantName = 58, RULE_classOrInterfaceType = 59, RULE_qualifiedName = 60, 
		RULE_elementValuePair = 61, RULE_annotationTypeDeclaration = 62, RULE_annotationMethodRest = 63, 
		RULE_statement = 64, RULE_catchClause = 65, RULE_expression = 66, RULE_primary = 67, 
		RULE_createdName = 68, RULE_innerCreator = 69, RULE_superSuffix = 70, 
		RULE_explicitGenericInvocationSuffix = 71, RULE_compilationUnit = 72, 
		RULE_packageDeclaration = 73, RULE_importDeclaration = 74, RULE_modifier = 75, 
		RULE_classOrInterfaceModifier = 76, RULE_variableModifier = 77, RULE_typeParameters = 78, 
		RULE_typeBound = 79, RULE_enumConstants = 80, RULE_enumBodyDeclarations = 81, 
		RULE_typeList = 82, RULE_classBody = 83, RULE_interfaceBody = 84, RULE_genericMethodDeclaration = 85, 
		RULE_genericConstructorDeclaration = 86, RULE_fieldDeclaration = 87, RULE_interfaceBodyDeclaration = 88, 
		RULE_interfaceMemberDeclaration = 89, RULE_constDeclaration = 90, RULE_genericInterfaceMethodDeclaration = 91, 
		RULE_variableDeclarators = 92, RULE_variableDeclarator = 93, RULE_variableInitializer = 94, 
		RULE_arrayInitializer = 95, RULE_type = 96, RULE_primitiveType = 97, RULE_typeArguments = 98, 
		RULE_typeArgument = 99, RULE_qualifiedNameList = 100, RULE_formalParameters = 101, 
		RULE_formalParameterList = 102, RULE_formalParameter = 103, RULE_lastFormalParameter = 104, 
		RULE_methodBody = 105, RULE_constructorBody = 106, RULE_literal = 107, 
		RULE_annotationName = 108, RULE_elementValuePairs = 109, RULE_elementValue = 110, 
		RULE_elementValueArrayInitializer = 111, RULE_annotationTypeBody = 112, 
		RULE_annotationTypeElementDeclaration = 113, RULE_annotationTypeElementRest = 114, 
		RULE_annotationMethodOrConstantRest = 115, RULE_annotationConstantRest = 116, 
		RULE_defaultValue = 117, RULE_block = 118, RULE_blockStatement = 119, 
		RULE_localVariableDeclarationStatement = 120, RULE_localVariableDeclaration = 121, 
		RULE_catchType = 122, RULE_finallyBlock = 123, RULE_resourceSpecification = 124, 
		RULE_resources = 125, RULE_resource = 126, RULE_switchBlockStatementGroup = 127, 
		RULE_switchLabel = 128, RULE_forControl = 129, RULE_forInit = 130, RULE_enhancedForControl = 131, 
		RULE_forUpdate = 132, RULE_parExpression = 133, RULE_expressionList = 134, 
		RULE_statementExpression = 135, RULE_constantExpression = 136, RULE_creator = 137, 
		RULE_arrayCreatorRest = 138, RULE_classCreatorRest = 139, RULE_explicitGenericInvocation = 140, 
		RULE_nonWildcardTypeArguments = 141, RULE_typeArgumentsOrDiamond = 142, 
		RULE_nonWildcardTypeArgumentsOrDiamond = 143, RULE_arguments = 144;
	private static String[] makeRuleNames() {
		return new String[] {
			"typeDeclaration", "aspectBody", "classBodyDeclaration", "aspectBodyDeclaration", 
			"memberDeclaration", "annotation", "classPattern", "classPatternList", 
			"aspectDeclaration", "advice", "adviceSpec", "perClause", "pointcutDeclaration", 
			"pointcutExpression", "pointcutPrimitive", "referencePointcut", "interTypeMemberDeclaration", 
			"interTypeDeclaration", "typePattern", "simpleTypePattern", "dottedNamePattern", 
			"optionalParensTypePattern", "fieldPattern", "fieldModifiersPattern", 
			"fieldModifier", "dotOrDotDot", "simpleNamePattern", "methodOrConstructorPattern", 
			"methodPattern", "methodModifiersPattern", "methodModifier", "formalsPattern", 
			"formalsPatternAfterDotDot", "throwsPattern", "typePatternList", "constructorPattern", 
			"constructorModifiersPattern", "constructorModifier", "annotationPattern", 
			"annotationTypePattern", "formalParametersPattern", "typeOrIdentifier", 
			"annotationOrIdentifer", "annotationsOrIdentifiersPattern", "annotationsOrIdentifiersPatternAfterDotDot", 
			"argsPattern", "argsPatternList", "id", "classDeclaration", "typeParameter", 
			"enumDeclaration", "enumConstant", "interfaceDeclaration", "methodDeclaration", 
			"constructorDeclaration", "constantDeclarator", "interfaceMethodDeclaration", 
			"variableDeclaratorId", "enumConstantName", "classOrInterfaceType", "qualifiedName", 
			"elementValuePair", "annotationTypeDeclaration", "annotationMethodRest", 
			"statement", "catchClause", "expression", "primary", "createdName", "innerCreator", 
			"superSuffix", "explicitGenericInvocationSuffix", "compilationUnit", 
			"packageDeclaration", "importDeclaration", "modifier", "classOrInterfaceModifier", 
			"variableModifier", "typeParameters", "typeBound", "enumConstants", "enumBodyDeclarations", 
			"typeList", "classBody", "interfaceBody", "genericMethodDeclaration", 
			"genericConstructorDeclaration", "fieldDeclaration", "interfaceBodyDeclaration", 
			"interfaceMemberDeclaration", "constDeclaration", "genericInterfaceMethodDeclaration", 
			"variableDeclarators", "variableDeclarator", "variableInitializer", "arrayInitializer", 
			"type", "primitiveType", "typeArguments", "typeArgument", "qualifiedNameList", 
			"formalParameters", "formalParameterList", "formalParameter", "lastFormalParameter", 
			"methodBody", "constructorBody", "literal", "annotationName", "elementValuePairs", 
			"elementValue", "elementValueArrayInitializer", "annotationTypeBody", 
			"annotationTypeElementDeclaration", "annotationTypeElementRest", "annotationMethodOrConstantRest", 
			"annotationConstantRest", "defaultValue", "block", "blockStatement", 
			"localVariableDeclarationStatement", "localVariableDeclaration", "catchType", 
			"finallyBlock", "resourceSpecification", "resources", "resource", "switchBlockStatementGroup", 
			"switchLabel", "forControl", "forInit", "enhancedForControl", "forUpdate", 
			"parExpression", "expressionList", "statementExpression", "constantExpression", 
			"creator", "arrayCreatorRest", "classCreatorRest", "explicitGenericInvocation", 
			"nonWildcardTypeArguments", "typeArgumentsOrDiamond", "nonWildcardTypeArgumentsOrDiamond", 
			"arguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'..'", "'\"'", "'adviceexecution'", "'annotation'", "'args'", 
			"'after'", "'around'", "'aspect'", "'before'", "'call'", "'cflow'", "'cflowbelow'", 
			"'declare'", "'error'", "'execution'", "'get'", "'handler'", "'initialization'", 
			"'issingleton'", "'parents'", "'percflow'", "'percflowbelow'", "'pertarget'", 
			"'perthis'", "'pertypewithin'", "'pointcut'", "'precedence'", "'preinitialization'", 
			"'privileged'", "'returning'", "'set'", "'soft'", "'staticinitialization'", 
			"'target'", "'throwing'", "'warning'", "'within'", "'withincode'", "'After'", 
			"'AfterReturning'", "'AfterThrowing'", "'Around'", "'Aspect'", "'Before'", 
			"'DeclareParents'", "'DeclareMixin'", "'DeclareWarning'", "'DeclareError'", 
			"'DeclarePrecedence'", "'Pointcut'", "'constructor'", "'defaultImpl'", 
			"'field'", "'interfaces'", "'type'", "'method'", "'value'", "'@'", "'abstract'", 
			"'assert'", "'boolean'", "'break'", "'byte'", "'case'", "'catch'", "'char'", 
			"'class'", "'const'", "'continue'", "'default'", "'do'", "'double'", 
			"'else'", "'enum'", "'extends'", "'final'", "'finally'", "'float'", "'for'", 
			"'if'", "'goto'", "'implements'", "'import'", "'instanceof'", "'int'", 
			"'interface'", "'long'", "'native'", "'new'", "'package'", "'private'", 
			"'protected'", "'public'", "'return'", "'short'", "'static'", "'strictfp'", 
			"'super'", "'switch'", "'synchronized'", "'this'", "'throw'", "'throws'", 
			"'transient'", "'try'", "'void'", "'volatile'", "'while'", null, null, 
			null, null, null, "'null'", "'('", "')'", "'{'", "'}'", "'['", "']'", 
			"';'", "','", "'.'", "'='", "'>'", "'<'", "'!'", "'~'", "'?'", "':'", 
			"'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'++'", "'--'", "'+'", 
			"'-'", "'*'", "'/'", "'&'", "'|'", "'^'", "'%'", "'+='", "'-='", "'*='", 
			"'/='", "'&='", "'|='", "'^='", "'%='", "'<<='", "'>>='", "'>>>='", null, 
			"'...'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DOTDOT", "DQUOTE", "ADVICEEXECUTION", "ANNOTATION", "ARGS", "AFTER", 
			"AROUND", "ASPECT", "BEFORE", "CALL", "CFLOW", "CFLOWBELOW", "DECLARE", 
			"ERROR", "EXECUTION", "GET", "HANDLER", "INITIALIZATION", "ISSINGLETON", 
			"PARENTS", "PERCFLOW", "PERCFLOWBELOW", "PERTARGET", "PERTHIS", "PERTYPEWITHIN", 
			"POINTCUT", "PRECEDENCE", "PREINITIALIZATION", "PRIVILEGED", "RETURNING", 
			"SET", "SOFT", "STATICINITIALIZATION", "TARGET", "THROWING", "WARNING", 
			"WITHIN", "WITHINCODE", "ANNOTATION_AFTER", "ANNOTATION_AFTERRETURNING", 
			"ANNOTATION_AFTERTHROWING", "ANNOTATION_AROUND", "ANNOTATION_ASPECT", 
			"ANNOTATION_BEFORE", "ANNOTATION_DECLAREPARENTS", "ANNOTATION_DECLAREMIXIN", 
			"ANNOTATION_DECLAREWARNING", "ANNOTATION_DECLAREERROR", "ANNOTATION_DECLAREPRECEDENCE", 
			"ANNOTATION_POINTCUT", "ANNOTATION_CONSTRUCTOR", "ANNOTATION_DEFAULTIMPL", 
			"ANNOTATION_FIELD", "ANNOTATION_INTERFACES", "ANNOTATION_TYPE", "ANNOTATION_METHOD", 
			"ANNOTATION_VALUE", "AT", "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", 
			"CASE", "CATCH", "CHAR", "CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", 
			"DOUBLE", "ELSE", "ENUM", "EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", 
			"IF", "GOTO", "IMPLEMENTS", "IMPORT", "INSTANCEOF", "INT", "INTERFACE", 
			"LONG", "NATIVE", "NEW", "PACKAGE", "PRIVATE", "PROTECTED", "PUBLIC", 
			"RETURN", "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH", "SYNCHRONIZED", 
			"THIS", "THROW", "THROWS", "TRANSIENT", "TRY", "VOID", "VOLATILE", "WHILE", 
			"IntegerLiteral", "FloatingPointLiteral", "BooleanLiteral", "CharacterLiteral", 
			"StringLiteral", "NullLiteral", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "BANG", 
			"TILDE", "QUESTION", "COLON", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", 
			"OR", "INC", "DEC", "ADD", "SUB", "MUL", "DIV", "BITAND", "BITOR", "CARET", 
			"MOD", "ADD_ASSIGN", "SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", 
			"OR_ASSIGN", "XOR_ASSIGN", "MOD_ASSIGN", "LSHIFT_ASSIGN", "RSHIFT_ASSIGN", 
			"URSHIFT_ASSIGN", "Identifier", "ELLIPSIS", "WS", "COMMENT", "LINE_COMMENT", 
			"WS1", "COMMENT1", "LINE_COMMENT1", "INVALID1", "WS2", "COMMENT2", "LINE_COMMENT2", 
			"WS3", "COMMENT3", "LINE_COMMENT3", "INVALID3", "WS4", "COMMENT4", "LINE_COMMENT4", 
			"INVALID4"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AspectJParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AspectJParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TypeDeclarationContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public AspectDeclarationContext aspectDeclaration() {
			return getRuleContext(AspectDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeDeclaration(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_typeDeclaration);
		int _la;
		try {
			int _alt;
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)))) != 0)) {
					{
					{
					setState(290);
					classOrInterfaceModifier();
					}
					}
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(296);
				classDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)))) != 0)) {
					{
					{
					setState(297);
					classOrInterfaceModifier();
					}
					}
					setState(302);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(303);
				enumDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)))) != 0)) {
					{
					{
					setState(304);
					classOrInterfaceModifier();
					}
					}
					setState(309);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(310);
				interfaceDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(311);
						classOrInterfaceModifier();
						}
						} 
					}
					setState(316);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(317);
				annotationTypeDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(318);
						classOrInterfaceModifier();
						}
						} 
					}
					setState(323);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				setState(324);
				aspectDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(325);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AspectBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<AspectBodyDeclarationContext> aspectBodyDeclaration() {
			return getRuleContexts(AspectBodyDeclarationContext.class);
		}
		public AspectBodyDeclarationContext aspectBodyDeclaration(int i) {
			return getRuleContext(AspectBodyDeclarationContext.class,i);
		}
		public AspectBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aspectBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAspectBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAspectBody(this);
		}
	}

	public final AspectBodyContext aspectBody() throws RecognitionException {
		AspectBodyContext _localctx = new AspectBodyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_aspectBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(LBRACE);
			setState(332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(329);
				aspectBodyDeclaration();
				}
				}
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(335);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyDeclarationContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode STATIC() { return getToken(AspectJParser.STATIC, 0); }
		public MemberDeclarationContext memberDeclaration() {
			return getRuleContext(MemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public AspectDeclarationContext aspectDeclaration() {
			return getRuleContext(AspectDeclarationContext.class,0);
		}
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassBodyDeclaration(this);
		}
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classBodyDeclaration);
		int _la;
		try {
			int _alt;
			setState(351);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(337);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(338);
					match(STATIC);
					}
				}

				setState(341);
				block();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(345);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(342);
						modifier();
						}
						} 
					}
					setState(347);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				setState(348);
				memberDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(349);
				match(STATIC);
				setState(350);
				aspectDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AspectBodyDeclarationContext extends ParserRuleContext {
		public ClassBodyDeclarationContext classBodyDeclaration() {
			return getRuleContext(ClassBodyDeclarationContext.class,0);
		}
		public AdviceContext advice() {
			return getRuleContext(AdviceContext.class,0);
		}
		public InterTypeMemberDeclarationContext interTypeMemberDeclaration() {
			return getRuleContext(InterTypeMemberDeclarationContext.class,0);
		}
		public InterTypeDeclarationContext interTypeDeclaration() {
			return getRuleContext(InterTypeDeclarationContext.class,0);
		}
		public AspectBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aspectBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAspectBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAspectBodyDeclaration(this);
		}
	}

	public final AspectBodyDeclarationContext aspectBodyDeclaration() throws RecognitionException {
		AspectBodyDeclarationContext _localctx = new AspectBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_aspectBodyDeclaration);
		try {
			setState(357);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				classBodyDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(354);
				advice();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(355);
				interTypeMemberDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(356);
				interTypeDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberDeclarationContext extends ParserRuleContext {
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext genericMethodDeclaration() {
			return getRuleContext(GenericMethodDeclarationContext.class,0);
		}
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext genericConstructorDeclaration() {
			return getRuleContext(GenericConstructorDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public PointcutDeclarationContext pointcutDeclaration() {
			return getRuleContext(PointcutDeclarationContext.class,0);
		}
		public MemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMemberDeclaration(this);
		}
	}

	public final MemberDeclarationContext memberDeclaration() throws RecognitionException {
		MemberDeclarationContext _localctx = new MemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_memberDeclaration);
		try {
			setState(369);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				methodDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				genericMethodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(361);
				fieldDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(362);
				constructorDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(363);
				genericConstructorDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(364);
				interfaceDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(365);
				annotationTypeDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(366);
				classDeclaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(367);
				enumDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(368);
				pointcutDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public AnnotationNameContext annotationName() {
			return getRuleContext(AnnotationNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ElementValuePairsContext elementValuePairs() {
			return getRuleContext(ElementValuePairsContext.class,0);
		}
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public TerminalNode ANNOTATION_AFTER() { return getToken(AspectJParser.ANNOTATION_AFTER, 0); }
		public List<TerminalNode> DQUOTE() { return getTokens(AspectJParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(AspectJParser.DQUOTE, i);
		}
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public TerminalNode ANNOTATION_AFTERRETURNING() { return getToken(AspectJParser.ANNOTATION_AFTERRETURNING, 0); }
		public TerminalNode POINTCUT() { return getToken(AspectJParser.POINTCUT, 0); }
		public List<TerminalNode> ASSIGN() { return getTokens(AspectJParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(AspectJParser.ASSIGN, i);
		}
		public TerminalNode COMMA() { return getToken(AspectJParser.COMMA, 0); }
		public TerminalNode RETURNING() { return getToken(AspectJParser.RETURNING, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode ANNOTATION_AFTERTHROWING() { return getToken(AspectJParser.ANNOTATION_AFTERTHROWING, 0); }
		public TerminalNode ANNOTATION_AROUND() { return getToken(AspectJParser.ANNOTATION_AROUND, 0); }
		public TerminalNode ANNOTATION_ASPECT() { return getToken(AspectJParser.ANNOTATION_ASPECT, 0); }
		public PerClauseContext perClause() {
			return getRuleContext(PerClauseContext.class,0);
		}
		public TerminalNode ANNOTATION_BEFORE() { return getToken(AspectJParser.ANNOTATION_BEFORE, 0); }
		public TerminalNode ANNOTATION_DECLAREERROR() { return getToken(AspectJParser.ANNOTATION_DECLAREERROR, 0); }
		public TerminalNode ANNOTATION_DECLAREMIXIN() { return getToken(AspectJParser.ANNOTATION_DECLAREMIXIN, 0); }
		public TerminalNode ANNOTATION_VALUE() { return getToken(AspectJParser.ANNOTATION_VALUE, 0); }
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode ANNOTATION_INTERFACES() { return getToken(AspectJParser.ANNOTATION_INTERFACES, 0); }
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public ClassPatternListContext classPatternList() {
			return getRuleContext(ClassPatternListContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public TerminalNode ANNOTATION_DECLAREPARENTS() { return getToken(AspectJParser.ANNOTATION_DECLAREPARENTS, 0); }
		public TerminalNode ANNOTATION_DEFAULTIMPL() { return getToken(AspectJParser.ANNOTATION_DEFAULTIMPL, 0); }
		public ClassPatternContext classPattern() {
			return getRuleContext(ClassPatternContext.class,0);
		}
		public TerminalNode ANNOTATION_DECLAREPRECEDENCE() { return getToken(AspectJParser.ANNOTATION_DECLAREPRECEDENCE, 0); }
		public TypePatternListContext typePatternList() {
			return getRuleContext(TypePatternListContext.class,0);
		}
		public TerminalNode ANNOTATION_DECLAREWARNING() { return getToken(AspectJParser.ANNOTATION_DECLAREWARNING, 0); }
		public TerminalNode ANNOTATION_POINTCUT() { return getToken(AspectJParser.ANNOTATION_POINTCUT, 0); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_annotation);
		int _la;
		try {
			setState(518);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				match(AT);
				setState(372);
				annotationName();
				setState(379);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(373);
					match(LPAREN);
					setState(376);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						setState(374);
						elementValuePairs();
						}
						break;
					case 2:
						{
						setState(375);
						elementValue();
						}
						break;
					}
					setState(378);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				match(AT);
				setState(382);
				match(ANNOTATION_AFTER);
				setState(383);
				match(LPAREN);
				setState(384);
				match(DQUOTE);
				setState(385);
				pointcutExpression(0);
				setState(386);
				match(DQUOTE);
				setState(387);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(389);
				match(AT);
				setState(390);
				match(ANNOTATION_AFTERRETURNING);
				setState(391);
				match(LPAREN);
				setState(392);
				match(DQUOTE);
				setState(393);
				pointcutExpression(0);
				setState(394);
				match(DQUOTE);
				setState(395);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(397);
				match(AT);
				setState(398);
				match(ANNOTATION_AFTERRETURNING);
				setState(399);
				match(LPAREN);
				setState(400);
				match(POINTCUT);
				setState(401);
				match(ASSIGN);
				setState(402);
				match(DQUOTE);
				setState(403);
				pointcutExpression(0);
				setState(404);
				match(DQUOTE);
				setState(405);
				match(COMMA);
				setState(406);
				match(RETURNING);
				setState(407);
				match(ASSIGN);
				setState(408);
				match(DQUOTE);
				setState(409);
				id();
				setState(410);
				match(DQUOTE);
				setState(411);
				match(RPAREN);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(413);
				match(AT);
				setState(414);
				match(ANNOTATION_AFTERTHROWING);
				setState(415);
				match(LPAREN);
				setState(416);
				match(DQUOTE);
				setState(417);
				pointcutExpression(0);
				setState(418);
				match(DQUOTE);
				setState(419);
				match(RPAREN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(421);
				match(AT);
				setState(422);
				match(ANNOTATION_AROUND);
				setState(423);
				match(LPAREN);
				setState(424);
				match(DQUOTE);
				setState(425);
				pointcutExpression(0);
				setState(426);
				match(DQUOTE);
				setState(427);
				match(RPAREN);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(429);
				match(AT);
				setState(430);
				match(ANNOTATION_ASPECT);
				setState(437);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(431);
					match(LPAREN);
					setState(432);
					match(DQUOTE);
					setState(433);
					perClause();
					setState(434);
					match(DQUOTE);
					setState(435);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(439);
				match(AT);
				setState(440);
				match(ANNOTATION_BEFORE);
				setState(441);
				match(LPAREN);
				setState(442);
				match(DQUOTE);
				setState(443);
				pointcutExpression(0);
				setState(444);
				match(DQUOTE);
				setState(445);
				match(RPAREN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(447);
				match(AT);
				setState(448);
				match(ANNOTATION_DECLAREERROR);
				setState(449);
				match(LPAREN);
				setState(450);
				match(DQUOTE);
				setState(451);
				pointcutExpression(0);
				setState(452);
				match(DQUOTE);
				setState(453);
				match(RPAREN);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(455);
				match(AT);
				setState(456);
				match(ANNOTATION_DECLAREMIXIN);
				setState(457);
				match(LPAREN);
				setState(458);
				match(ANNOTATION_VALUE);
				setState(459);
				match(ASSIGN);
				setState(460);
				match(DQUOTE);
				setState(461);
				typePattern(0);
				setState(462);
				match(DQUOTE);
				setState(463);
				match(COMMA);
				setState(464);
				match(ANNOTATION_INTERFACES);
				setState(465);
				match(ASSIGN);
				setState(466);
				match(LBRACE);
				setState(467);
				classPatternList();
				setState(468);
				match(RBRACE);
				setState(469);
				match(RPAREN);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(471);
				match(AT);
				setState(472);
				match(ANNOTATION_DECLAREPARENTS);
				setState(473);
				match(LPAREN);
				setState(474);
				match(DQUOTE);
				setState(475);
				typePattern(0);
				setState(476);
				match(DQUOTE);
				setState(477);
				match(RPAREN);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(479);
				match(AT);
				setState(480);
				match(ANNOTATION_DECLAREPARENTS);
				setState(481);
				match(LPAREN);
				setState(482);
				match(ANNOTATION_VALUE);
				setState(483);
				match(ASSIGN);
				setState(484);
				match(DQUOTE);
				setState(485);
				typePattern(0);
				setState(486);
				match(DQUOTE);
				setState(487);
				match(COMMA);
				setState(488);
				match(ANNOTATION_DEFAULTIMPL);
				setState(489);
				match(ASSIGN);
				setState(490);
				classPattern();
				setState(491);
				match(RPAREN);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(493);
				match(AT);
				setState(494);
				match(ANNOTATION_DECLAREPRECEDENCE);
				setState(495);
				match(LPAREN);
				setState(496);
				match(DQUOTE);
				setState(497);
				typePatternList();
				setState(498);
				match(DQUOTE);
				setState(499);
				match(RPAREN);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(501);
				match(AT);
				setState(502);
				match(ANNOTATION_DECLAREWARNING);
				setState(503);
				match(LPAREN);
				setState(504);
				match(DQUOTE);
				setState(505);
				pointcutExpression(0);
				setState(506);
				match(DQUOTE);
				setState(507);
				match(RPAREN);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(509);
				match(AT);
				setState(510);
				match(ANNOTATION_POINTCUT);
				setState(511);
				match(LPAREN);
				setState(512);
				match(DQUOTE);
				setState(514);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << ADVICEEXECUTION) | (1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (IF - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (LPAREN - 66)) | (1L << (DOT - 66)) | (1L << (BANG - 66)))) != 0) || _la==MUL || _la==Identifier) {
					{
					setState(513);
					pointcutExpression(0);
					}
				}

				setState(516);
				match(DQUOTE);
				setState(517);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassPatternContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(AspectJParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(AspectJParser.DOT, i);
		}
		public TerminalNode CLASS() { return getToken(AspectJParser.CLASS, 0); }
		public ClassPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassPattern(this);
		}
	}

	public final ClassPatternContext classPattern() throws RecognitionException {
		ClassPatternContext _localctx = new ClassPatternContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classPattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			id();
			setState(525);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(521);
					match(DOT);
					setState(522);
					id();
					}
					} 
				}
				setState(527);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(528);
			match(DOT);
			setState(529);
			match(CLASS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassPatternListContext extends ParserRuleContext {
		public List<ClassPatternContext> classPattern() {
			return getRuleContexts(ClassPatternContext.class);
		}
		public ClassPatternContext classPattern(int i) {
			return getRuleContext(ClassPatternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ClassPatternListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classPatternList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassPatternList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassPatternList(this);
		}
	}

	public final ClassPatternListContext classPatternList() throws RecognitionException {
		ClassPatternListContext _localctx = new ClassPatternListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classPatternList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			classPattern();
			setState(536);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(532);
				match(COMMA);
				setState(533);
				classPattern();
				}
				}
				setState(538);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AspectDeclarationContext extends ParserRuleContext {
		public TerminalNode ASPECT() { return getToken(AspectJParser.ASPECT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public AspectBodyContext aspectBody() {
			return getRuleContext(AspectBodyContext.class,0);
		}
		public TerminalNode PRIVILEGED() { return getToken(AspectJParser.PRIVILEGED, 0); }
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(AspectJParser.EXTENDS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(AspectJParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public PerClauseContext perClause() {
			return getRuleContext(PerClauseContext.class,0);
		}
		public AspectDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aspectDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAspectDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAspectDeclaration(this);
		}
	}

	public final AspectDeclarationContext aspectDeclaration() throws RecognitionException {
		AspectDeclarationContext _localctx = new AspectDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_aspectDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIVILEGED) {
				{
				setState(539);
				match(PRIVILEGED);
				}
			}

			setState(545);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
				{
				{
				setState(542);
				modifier();
				}
				}
				setState(547);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(548);
			match(ASPECT);
			setState(549);
			id();
			setState(551);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(550);
				typeParameters();
				}
			}

			setState(555);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(553);
				match(EXTENDS);
				setState(554);
				type();
				}
			}

			setState(559);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(557);
				match(IMPLEMENTS);
				setState(558);
				typeList();
				}
			}

			setState(562);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ISSINGLETON) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN))) != 0)) {
				{
				setState(561);
				perClause();
				}
			}

			setState(564);
			aspectBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdviceContext extends ParserRuleContext {
		public AdviceSpecContext adviceSpec() {
			return getRuleContext(AdviceSpecContext.class,0);
		}
		public TerminalNode COLON() { return getToken(AspectJParser.COLON, 0); }
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode STRICTFP() { return getToken(AspectJParser.STRICTFP, 0); }
		public TerminalNode THROWS() { return getToken(AspectJParser.THROWS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public AdviceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_advice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAdvice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAdvice(this);
		}
	}

	public final AdviceContext advice() throws RecognitionException {
		AdviceContext _localctx = new AdviceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_advice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(569);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(566);
				annotation();
				}
				}
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(573);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRICTFP) {
				{
				setState(572);
				match(STRICTFP);
				}
			}

			setState(575);
			adviceSpec();
			setState(578);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(576);
				match(THROWS);
				setState(577);
				typeList();
				}
			}

			setState(580);
			match(COLON);
			setState(581);
			pointcutExpression(0);
			setState(582);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdviceSpecContext extends ParserRuleContext {
		public TerminalNode BEFORE() { return getToken(AspectJParser.BEFORE, 0); }
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode AFTER() { return getToken(AspectJParser.AFTER, 0); }
		public TerminalNode RETURNING() { return getToken(AspectJParser.RETURNING, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public FormalParameterContext formalParameter() {
			return getRuleContext(FormalParameterContext.class,0);
		}
		public TerminalNode THROWING() { return getToken(AspectJParser.THROWING, 0); }
		public TerminalNode AROUND() { return getToken(AspectJParser.AROUND, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(AspectJParser.VOID, 0); }
		public AdviceSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_adviceSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAdviceSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAdviceSpec(this);
		}
	}

	public final AdviceSpecContext adviceSpec() throws RecognitionException {
		AdviceSpecContext _localctx = new AdviceSpecContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_adviceSpec);
		int _la;
		try {
			setState(614);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(584);
				match(BEFORE);
				setState(585);
				formalParameters();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(586);
				match(AFTER);
				setState(587);
				formalParameters();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(588);
				match(AFTER);
				setState(589);
				formalParameters();
				setState(590);
				match(RETURNING);
				setState(596);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(591);
					match(LPAREN);
					setState(593);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)))) != 0) || _la==Identifier) {
						{
						setState(592);
						formalParameter();
						}
					}

					setState(595);
					match(RPAREN);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(598);
				match(AFTER);
				setState(599);
				formalParameters();
				setState(600);
				match(THROWING);
				setState(606);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(601);
					match(LPAREN);
					setState(603);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)))) != 0) || _la==Identifier) {
						{
						setState(602);
						formalParameter();
						}
					}

					setState(605);
					match(RPAREN);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(610);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANNOTATION:
				case ARGS:
				case AFTER:
				case AROUND:
				case ASPECT:
				case BEFORE:
				case CALL:
				case CFLOW:
				case CFLOWBELOW:
				case DECLARE:
				case ERROR:
				case EXECUTION:
				case GET:
				case HANDLER:
				case INITIALIZATION:
				case ISSINGLETON:
				case PARENTS:
				case PERCFLOW:
				case PERCFLOWBELOW:
				case PERTARGET:
				case PERTHIS:
				case PERTYPEWITHIN:
				case POINTCUT:
				case PRECEDENCE:
				case PREINITIALIZATION:
				case PRIVILEGED:
				case RETURNING:
				case SET:
				case SOFT:
				case STATICINITIALIZATION:
				case TARGET:
				case THROWING:
				case WARNING:
				case WITHIN:
				case WITHINCODE:
				case ANNOTATION_METHOD:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case Identifier:
					{
					setState(608);
					type();
					}
					break;
				case VOID:
					{
					setState(609);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(612);
				match(AROUND);
				setState(613);
				formalParameters();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PerClauseContext extends ParserRuleContext {
		public TerminalNode PERTARGET() { return getToken(AspectJParser.PERTARGET, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public TerminalNode PERTHIS() { return getToken(AspectJParser.PERTHIS, 0); }
		public TerminalNode PERCFLOW() { return getToken(AspectJParser.PERCFLOW, 0); }
		public TerminalNode PERCFLOWBELOW() { return getToken(AspectJParser.PERCFLOWBELOW, 0); }
		public TerminalNode PERTYPEWITHIN() { return getToken(AspectJParser.PERTYPEWITHIN, 0); }
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode ISSINGLETON() { return getToken(AspectJParser.ISSINGLETON, 0); }
		public PerClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_perClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPerClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPerClause(this);
		}
	}

	public final PerClauseContext perClause() throws RecognitionException {
		PerClauseContext _localctx = new PerClauseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_perClause);
		try {
			setState(644);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PERTARGET:
				enterOuterAlt(_localctx, 1);
				{
				setState(616);
				match(PERTARGET);
				setState(617);
				match(LPAREN);
				setState(618);
				pointcutExpression(0);
				setState(619);
				match(RPAREN);
				}
				break;
			case PERTHIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(621);
				match(PERTHIS);
				setState(622);
				match(LPAREN);
				setState(623);
				pointcutExpression(0);
				setState(624);
				match(RPAREN);
				}
				break;
			case PERCFLOW:
				enterOuterAlt(_localctx, 3);
				{
				setState(626);
				match(PERCFLOW);
				setState(627);
				match(LPAREN);
				setState(628);
				pointcutExpression(0);
				setState(629);
				match(RPAREN);
				}
				break;
			case PERCFLOWBELOW:
				enterOuterAlt(_localctx, 4);
				{
				setState(631);
				match(PERCFLOWBELOW);
				setState(632);
				match(LPAREN);
				setState(633);
				pointcutExpression(0);
				setState(634);
				match(RPAREN);
				}
				break;
			case PERTYPEWITHIN:
				enterOuterAlt(_localctx, 5);
				{
				setState(636);
				match(PERTYPEWITHIN);
				setState(637);
				match(LPAREN);
				setState(638);
				typePattern(0);
				setState(639);
				match(RPAREN);
				}
				break;
			case ISSINGLETON:
				enterOuterAlt(_localctx, 6);
				{
				setState(641);
				match(ISSINGLETON);
				setState(642);
				match(LPAREN);
				setState(643);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointcutDeclarationContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(AspectJParser.ABSTRACT, 0); }
		public TerminalNode POINTCUT() { return getToken(AspectJParser.POINTCUT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode COLON() { return getToken(AspectJParser.COLON, 0); }
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public PointcutDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointcutDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPointcutDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPointcutDeclaration(this);
		}
	}

	public final PointcutDeclarationContext pointcutDeclaration() throws RecognitionException {
		PointcutDeclarationContext _localctx = new PointcutDeclarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pointcutDeclaration);
		int _la;
		try {
			setState(671);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(646);
				match(ABSTRACT);
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(647);
					modifier();
					}
					}
					setState(652);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(653);
				match(POINTCUT);
				setState(654);
				id();
				setState(655);
				formalParameters();
				setState(656);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(661);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(658);
					modifier();
					}
					}
					setState(663);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(664);
				match(POINTCUT);
				setState(665);
				id();
				setState(666);
				formalParameters();
				setState(667);
				match(COLON);
				setState(668);
				pointcutExpression(0);
				setState(669);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointcutExpressionContext extends ParserRuleContext {
		public PointcutPrimitiveContext pointcutPrimitive() {
			return getRuleContext(PointcutPrimitiveContext.class,0);
		}
		public ReferencePointcutContext referencePointcut() {
			return getRuleContext(ReferencePointcutContext.class,0);
		}
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public List<PointcutExpressionContext> pointcutExpression() {
			return getRuleContexts(PointcutExpressionContext.class);
		}
		public PointcutExpressionContext pointcutExpression(int i) {
			return getRuleContext(PointcutExpressionContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public TerminalNode AND() { return getToken(AspectJParser.AND, 0); }
		public TerminalNode OR() { return getToken(AspectJParser.OR, 0); }
		public PointcutExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointcutExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPointcutExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPointcutExpression(this);
		}
	}

	public final PointcutExpressionContext pointcutExpression() throws RecognitionException {
		return pointcutExpression(0);
	}

	private PointcutExpressionContext pointcutExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PointcutExpressionContext _localctx = new PointcutExpressionContext(_ctx, _parentState);
		PointcutExpressionContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_pointcutExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(684);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(676);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(674);
					pointcutPrimitive();
					}
					break;
				case 2:
					{
					setState(675);
					referencePointcut();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(678);
				match(BANG);
				setState(679);
				pointcutExpression(4);
				}
				break;
			case 3:
				{
				setState(680);
				match(LPAREN);
				setState(681);
				pointcutExpression(0);
				setState(682);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(694);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(692);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						_localctx = new PointcutExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_pointcutExpression);
						setState(686);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(687);
						match(AND);
						setState(688);
						pointcutExpression(3);
						}
						break;
					case 2:
						{
						_localctx = new PointcutExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_pointcutExpression);
						setState(689);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(690);
						match(OR);
						setState(691);
						pointcutExpression(2);
						}
						break;
					}
					} 
				}
				setState(696);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PointcutPrimitiveContext extends ParserRuleContext {
		public PointcutPrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointcutPrimitive; }
	 
		public PointcutPrimitiveContext() { }
		public void copyFrom(PointcutPrimitiveContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InitializationPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode INITIALIZATION() { return getToken(AspectJParser.INITIALIZATION, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ConstructorPatternContext constructorPattern() {
			return getRuleContext(ConstructorPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public InitializationPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInitializationPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInitializationPointcut(this);
		}
	}
	public static class StaticInitializationPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode STATICINITIALIZATION() { return getToken(AspectJParser.STATICINITIALIZATION, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public OptionalParensTypePatternContext optionalParensTypePattern() {
			return getRuleContext(OptionalParensTypePatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public StaticInitializationPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterStaticInitializationPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitStaticInitializationPointcut(this);
		}
	}
	public static class CFlowPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode CFLOW() { return getToken(AspectJParser.CFLOW, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public CFlowPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCFlowPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCFlowPointcut(this);
		}
	}
	public static class AnnotationArgsPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode ARGS() { return getToken(AspectJParser.ARGS, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public AnnotationsOrIdentifiersPatternContext annotationsOrIdentifiersPattern() {
			return getRuleContext(AnnotationsOrIdentifiersPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationArgsPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationArgsPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationArgsPointcut(this);
		}
	}
	public static class GetPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode GET() { return getToken(AspectJParser.GET, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public FieldPatternContext fieldPattern() {
			return getRuleContext(FieldPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public GetPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterGetPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitGetPointcut(this);
		}
	}
	public static class ExecutionPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode EXECUTION() { return getToken(AspectJParser.EXECUTION, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public MethodOrConstructorPatternContext methodOrConstructorPattern() {
			return getRuleContext(MethodOrConstructorPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ExecutionPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterExecutionPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitExecutionPointcut(this);
		}
	}
	public static class TargetPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode TARGET() { return getToken(AspectJParser.TARGET, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TypeOrIdentifierContext typeOrIdentifier() {
			return getRuleContext(TypeOrIdentifierContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public TargetPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTargetPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTargetPointcut(this);
		}
	}
	public static class AdviceExecutionPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode ADVICEEXECUTION() { return getToken(AspectJParser.ADVICEEXECUTION, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AdviceExecutionPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAdviceExecutionPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAdviceExecutionPointcut(this);
		}
	}
	public static class AnnotationPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode ANNOTATION() { return getToken(AspectJParser.ANNOTATION, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationPointcut(this);
		}
	}
	public static class AnnotationTargetPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode TARGET() { return getToken(AspectJParser.TARGET, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationTargetPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationTargetPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationTargetPointcut(this);
		}
	}
	public static class AnnotationThisPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode THIS() { return getToken(AspectJParser.THIS, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationThisPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationThisPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationThisPointcut(this);
		}
	}
	public static class SetPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode SET() { return getToken(AspectJParser.SET, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public FieldPatternContext fieldPattern() {
			return getRuleContext(FieldPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public SetPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterSetPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitSetPointcut(this);
		}
	}
	public static class WithinCodePointcutContext extends PointcutPrimitiveContext {
		public TerminalNode WITHINCODE() { return getToken(AspectJParser.WITHINCODE, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public MethodOrConstructorPatternContext methodOrConstructorPattern() {
			return getRuleContext(MethodOrConstructorPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public WithinCodePointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterWithinCodePointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitWithinCodePointcut(this);
		}
	}
	public static class ArgsPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode ARGS() { return getToken(AspectJParser.ARGS, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ArgsPatternListContext argsPatternList() {
			return getRuleContext(ArgsPatternListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ArgsPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterArgsPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitArgsPointcut(this);
		}
	}
	public static class AnnotationWithinPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode WITHIN() { return getToken(AspectJParser.WITHIN, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationWithinPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationWithinPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationWithinPointcut(this);
		}
	}
	public static class CallPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode CALL() { return getToken(AspectJParser.CALL, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public MethodOrConstructorPatternContext methodOrConstructorPattern() {
			return getRuleContext(MethodOrConstructorPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public CallPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCallPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCallPointcut(this);
		}
	}
	public static class WithinPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode WITHIN() { return getToken(AspectJParser.WITHIN, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public OptionalParensTypePatternContext optionalParensTypePattern() {
			return getRuleContext(OptionalParensTypePatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public WithinPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterWithinPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitWithinPointcut(this);
		}
	}
	public static class AnnotationWithinCodePointcutContext extends PointcutPrimitiveContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode WITHINCODE() { return getToken(AspectJParser.WITHINCODE, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationWithinCodePointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationWithinCodePointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationWithinCodePointcut(this);
		}
	}
	public static class IfPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode IF() { return getToken(AspectJParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterIfPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitIfPointcut(this);
		}
	}
	public static class PreInitializationPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode PREINITIALIZATION() { return getToken(AspectJParser.PREINITIALIZATION, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ConstructorPatternContext constructorPattern() {
			return getRuleContext(ConstructorPatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public PreInitializationPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPreInitializationPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPreInitializationPointcut(this);
		}
	}
	public static class CFlowBelowPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode CFLOWBELOW() { return getToken(AspectJParser.CFLOWBELOW, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public CFlowBelowPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCFlowBelowPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCFlowBelowPointcut(this);
		}
	}
	public static class ThisPointcutPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode THIS() { return getToken(AspectJParser.THIS, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TypeOrIdentifierContext typeOrIdentifier() {
			return getRuleContext(TypeOrIdentifierContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ThisPointcutPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterThisPointcutPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitThisPointcutPointcut(this);
		}
	}
	public static class HandlerPointcutContext extends PointcutPrimitiveContext {
		public TerminalNode HANDLER() { return getToken(AspectJParser.HANDLER, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public OptionalParensTypePatternContext optionalParensTypePattern() {
			return getRuleContext(OptionalParensTypePatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public HandlerPointcutContext(PointcutPrimitiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterHandlerPointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitHandlerPointcut(this);
		}
	}

	public final PointcutPrimitiveContext pointcutPrimitive() throws RecognitionException {
		PointcutPrimitiveContext _localctx = new PointcutPrimitiveContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pointcutPrimitive);
		int _la;
		try {
			setState(817);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				_localctx = new CallPointcutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(697);
				match(CALL);
				setState(698);
				match(LPAREN);
				setState(699);
				methodOrConstructorPattern();
				setState(700);
				match(RPAREN);
				}
				break;
			case 2:
				_localctx = new ExecutionPointcutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(702);
				match(EXECUTION);
				setState(703);
				match(LPAREN);
				setState(704);
				methodOrConstructorPattern();
				setState(705);
				match(RPAREN);
				}
				break;
			case 3:
				_localctx = new InitializationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(707);
				match(INITIALIZATION);
				setState(708);
				match(LPAREN);
				setState(709);
				constructorPattern();
				setState(710);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new PreInitializationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(712);
				match(PREINITIALIZATION);
				setState(713);
				match(LPAREN);
				setState(714);
				constructorPattern();
				setState(715);
				match(RPAREN);
				}
				break;
			case 5:
				_localctx = new StaticInitializationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(717);
				match(STATICINITIALIZATION);
				setState(718);
				match(LPAREN);
				setState(719);
				optionalParensTypePattern();
				setState(720);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new GetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(722);
				match(GET);
				setState(723);
				match(LPAREN);
				setState(724);
				fieldPattern();
				setState(725);
				match(RPAREN);
				}
				break;
			case 7:
				_localctx = new SetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(727);
				match(SET);
				setState(728);
				match(LPAREN);
				setState(729);
				fieldPattern();
				setState(730);
				match(RPAREN);
				}
				break;
			case 8:
				_localctx = new HandlerPointcutContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(732);
				match(HANDLER);
				setState(733);
				match(LPAREN);
				setState(734);
				optionalParensTypePattern();
				setState(735);
				match(RPAREN);
				}
				break;
			case 9:
				_localctx = new AdviceExecutionPointcutContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(737);
				match(ADVICEEXECUTION);
				setState(738);
				match(LPAREN);
				setState(739);
				match(RPAREN);
				}
				break;
			case 10:
				_localctx = new WithinPointcutContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(740);
				match(WITHIN);
				setState(741);
				match(LPAREN);
				setState(742);
				optionalParensTypePattern();
				setState(743);
				match(RPAREN);
				}
				break;
			case 11:
				_localctx = new WithinCodePointcutContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(745);
				match(WITHINCODE);
				setState(746);
				match(LPAREN);
				setState(747);
				methodOrConstructorPattern();
				setState(748);
				match(RPAREN);
				}
				break;
			case 12:
				_localctx = new CFlowPointcutContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(750);
				match(CFLOW);
				setState(751);
				match(LPAREN);
				setState(752);
				pointcutExpression(0);
				setState(753);
				match(RPAREN);
				}
				break;
			case 13:
				_localctx = new CFlowBelowPointcutContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(755);
				match(CFLOWBELOW);
				setState(756);
				match(LPAREN);
				setState(757);
				pointcutExpression(0);
				setState(758);
				match(RPAREN);
				}
				break;
			case 14:
				_localctx = new IfPointcutContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(760);
				match(IF);
				setState(761);
				match(LPAREN);
				setState(763);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(762);
					expression(0);
					}
				}

				setState(765);
				match(RPAREN);
				}
				break;
			case 15:
				_localctx = new ThisPointcutPointcutContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(766);
				match(THIS);
				setState(767);
				match(LPAREN);
				setState(768);
				typeOrIdentifier();
				setState(769);
				match(RPAREN);
				}
				break;
			case 16:
				_localctx = new TargetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(771);
				match(TARGET);
				setState(772);
				match(LPAREN);
				setState(773);
				typeOrIdentifier();
				setState(774);
				match(RPAREN);
				}
				break;
			case 17:
				_localctx = new ArgsPointcutContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(776);
				match(ARGS);
				setState(777);
				match(LPAREN);
				setState(778);
				argsPatternList();
				setState(779);
				match(RPAREN);
				}
				break;
			case 18:
				_localctx = new AnnotationThisPointcutContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(781);
				match(AT);
				setState(782);
				match(THIS);
				setState(783);
				match(LPAREN);
				setState(784);
				annotationOrIdentifer();
				setState(785);
				match(RPAREN);
				}
				break;
			case 19:
				_localctx = new AnnotationTargetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(787);
				match(AT);
				setState(788);
				match(TARGET);
				setState(789);
				match(LPAREN);
				setState(790);
				annotationOrIdentifer();
				setState(791);
				match(RPAREN);
				}
				break;
			case 20:
				_localctx = new AnnotationArgsPointcutContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(793);
				match(AT);
				setState(794);
				match(ARGS);
				setState(795);
				match(LPAREN);
				setState(796);
				annotationsOrIdentifiersPattern();
				setState(797);
				match(RPAREN);
				}
				break;
			case 21:
				_localctx = new AnnotationWithinPointcutContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(799);
				match(AT);
				setState(800);
				match(WITHIN);
				setState(801);
				match(LPAREN);
				setState(802);
				annotationOrIdentifer();
				setState(803);
				match(RPAREN);
				}
				break;
			case 22:
				_localctx = new AnnotationWithinCodePointcutContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(805);
				match(AT);
				setState(806);
				match(WITHINCODE);
				setState(807);
				match(LPAREN);
				setState(808);
				annotationOrIdentifer();
				setState(809);
				match(RPAREN);
				}
				break;
			case 23:
				_localctx = new AnnotationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(811);
				match(AT);
				setState(812);
				match(ANNOTATION);
				setState(813);
				match(LPAREN);
				setState(814);
				annotationOrIdentifer();
				setState(815);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferencePointcutContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public FormalParametersPatternContext formalParametersPattern() {
			return getRuleContext(FormalParametersPatternContext.class,0);
		}
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public ReferencePointcutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referencePointcut; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterReferencePointcut(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitReferencePointcut(this);
		}
	}

	public final ReferencePointcutContext referencePointcut() throws RecognitionException {
		ReferencePointcutContext _localctx = new ReferencePointcutContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_referencePointcut);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(822);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(819);
				typePattern(0);
				setState(820);
				match(DOT);
				}
				break;
			}
			setState(824);
			id();
			setState(825);
			formalParametersPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterTypeMemberDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public TerminalNode VOID() { return getToken(AspectJParser.VOID, 0); }
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode THROWS() { return getToken(AspectJParser.THROWS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode ABSTRACT() { return getToken(AspectJParser.ABSTRACT, 0); }
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public TerminalNode NEW() { return getToken(AspectJParser.NEW, 0); }
		public TerminalNode ASSIGN() { return getToken(AspectJParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InterTypeMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interTypeMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterTypeMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterTypeMemberDeclaration(this);
		}
	}

	public final InterTypeMemberDeclarationContext interTypeMemberDeclaration() throws RecognitionException {
		InterTypeMemberDeclarationContext _localctx = new InterTypeMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_interTypeMemberDeclaration);
		int _la;
		try {
			int _alt;
			setState(909);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(830);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(827);
					modifier();
					}
					}
					setState(832);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(835);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANNOTATION:
				case ARGS:
				case AFTER:
				case AROUND:
				case ASPECT:
				case BEFORE:
				case CALL:
				case CFLOW:
				case CFLOWBELOW:
				case DECLARE:
				case ERROR:
				case EXECUTION:
				case GET:
				case HANDLER:
				case INITIALIZATION:
				case ISSINGLETON:
				case PARENTS:
				case PERCFLOW:
				case PERCFLOWBELOW:
				case PERTARGET:
				case PERTHIS:
				case PERTYPEWITHIN:
				case POINTCUT:
				case PRECEDENCE:
				case PREINITIALIZATION:
				case PRIVILEGED:
				case RETURNING:
				case SET:
				case SOFT:
				case STATICINITIALIZATION:
				case TARGET:
				case THROWING:
				case WARNING:
				case WITHIN:
				case WITHINCODE:
				case ANNOTATION_METHOD:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case Identifier:
					{
					setState(833);
					type();
					}
					break;
				case VOID:
					{
					setState(834);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(837);
				type();
				setState(838);
				match(DOT);
				setState(839);
				id();
				setState(840);
				formalParameters();
				setState(843);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(841);
					match(THROWS);
					setState(842);
					typeList();
					}
				}

				setState(845);
				methodBody();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(850);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(847);
						modifier();
						}
						} 
					}
					setState(852);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				}
				setState(853);
				match(ABSTRACT);
				setState(857);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(854);
					modifier();
					}
					}
					setState(859);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(862);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANNOTATION:
				case ARGS:
				case AFTER:
				case AROUND:
				case ASPECT:
				case BEFORE:
				case CALL:
				case CFLOW:
				case CFLOWBELOW:
				case DECLARE:
				case ERROR:
				case EXECUTION:
				case GET:
				case HANDLER:
				case INITIALIZATION:
				case ISSINGLETON:
				case PARENTS:
				case PERCFLOW:
				case PERCFLOWBELOW:
				case PERTARGET:
				case PERTHIS:
				case PERTYPEWITHIN:
				case POINTCUT:
				case PRECEDENCE:
				case PREINITIALIZATION:
				case PRIVILEGED:
				case RETURNING:
				case SET:
				case SOFT:
				case STATICINITIALIZATION:
				case TARGET:
				case THROWING:
				case WARNING:
				case WITHIN:
				case WITHINCODE:
				case ANNOTATION_METHOD:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case Identifier:
					{
					setState(860);
					type();
					}
					break;
				case VOID:
					{
					setState(861);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(864);
				type();
				setState(865);
				match(DOT);
				setState(866);
				id();
				setState(867);
				formalParameters();
				setState(870);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(868);
					match(THROWS);
					setState(869);
					typeList();
					}
				}

				setState(872);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(877);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(874);
					modifier();
					}
					}
					setState(879);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(880);
				type();
				setState(881);
				match(DOT);
				setState(882);
				match(NEW);
				setState(883);
				formalParameters();
				setState(886);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(884);
					match(THROWS);
					setState(885);
					typeList();
					}
				}

				setState(888);
				methodBody();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(893);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(890);
					modifier();
					}
					}
					setState(895);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(898);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANNOTATION:
				case ARGS:
				case AFTER:
				case AROUND:
				case ASPECT:
				case BEFORE:
				case CALL:
				case CFLOW:
				case CFLOWBELOW:
				case DECLARE:
				case ERROR:
				case EXECUTION:
				case GET:
				case HANDLER:
				case INITIALIZATION:
				case ISSINGLETON:
				case PARENTS:
				case PERCFLOW:
				case PERCFLOWBELOW:
				case PERTARGET:
				case PERTHIS:
				case PERTYPEWITHIN:
				case POINTCUT:
				case PRECEDENCE:
				case PREINITIALIZATION:
				case PRIVILEGED:
				case RETURNING:
				case SET:
				case SOFT:
				case STATICINITIALIZATION:
				case TARGET:
				case THROWING:
				case WARNING:
				case WITHIN:
				case WITHINCODE:
				case ANNOTATION_METHOD:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case Identifier:
					{
					setState(896);
					type();
					}
					break;
				case VOID:
					{
					setState(897);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(900);
				type();
				setState(901);
				match(DOT);
				setState(902);
				id();
				setState(905);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(903);
					match(ASSIGN);
					setState(904);
					expression(0);
					}
				}

				setState(907);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode DECLARE() { return getToken(AspectJParser.DECLARE, 0); }
		public TerminalNode PARENTS() { return getToken(AspectJParser.PARENTS, 0); }
		public List<TerminalNode> COLON() { return getTokens(AspectJParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(AspectJParser.COLON, i);
		}
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(AspectJParser.EXTENDS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(AspectJParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode WARNING() { return getToken(AspectJParser.WARNING, 0); }
		public PointcutExpressionContext pointcutExpression() {
			return getRuleContext(PointcutExpressionContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(AspectJParser.StringLiteral, 0); }
		public TerminalNode ERROR() { return getToken(AspectJParser.ERROR, 0); }
		public TerminalNode SOFT() { return getToken(AspectJParser.SOFT, 0); }
		public TerminalNode PRECEDENCE() { return getToken(AspectJParser.PRECEDENCE, 0); }
		public TypePatternListContext typePatternList() {
			return getRuleContext(TypePatternListContext.class,0);
		}
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode ANNOTATION_TYPE() { return getToken(AspectJParser.ANNOTATION_TYPE, 0); }
		public TerminalNode ANNOTATION_METHOD() { return getToken(AspectJParser.ANNOTATION_METHOD, 0); }
		public MethodPatternContext methodPattern() {
			return getRuleContext(MethodPatternContext.class,0);
		}
		public TerminalNode ANNOTATION_CONSTRUCTOR() { return getToken(AspectJParser.ANNOTATION_CONSTRUCTOR, 0); }
		public ConstructorPatternContext constructorPattern() {
			return getRuleContext(ConstructorPatternContext.class,0);
		}
		public TerminalNode ANNOTATION_FIELD() { return getToken(AspectJParser.ANNOTATION_FIELD, 0); }
		public FieldPatternContext fieldPattern() {
			return getRuleContext(FieldPatternContext.class,0);
		}
		public InterTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterTypeDeclaration(this);
		}
	}

	public final InterTypeDeclarationContext interTypeDeclaration() throws RecognitionException {
		InterTypeDeclarationContext _localctx = new InterTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_interTypeDeclaration);
		int _la;
		try {
			setState(1011);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(911);
				match(DECLARE);
				setState(912);
				match(PARENTS);
				setState(913);
				match(COLON);
				setState(915);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(914);
					annotation();
					}
				}

				setState(917);
				typePattern(0);
				setState(918);
				match(EXTENDS);
				setState(919);
				type();
				setState(920);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(922);
				match(DECLARE);
				setState(923);
				match(PARENTS);
				setState(924);
				match(COLON);
				setState(926);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(925);
					annotation();
					}
				}

				setState(928);
				typePattern(0);
				setState(929);
				match(IMPLEMENTS);
				setState(930);
				typeList();
				setState(931);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(933);
				match(DECLARE);
				setState(934);
				match(WARNING);
				setState(935);
				match(COLON);
				setState(937);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(936);
					annotation();
					}
					break;
				}
				setState(939);
				pointcutExpression(0);
				setState(940);
				match(COLON);
				setState(941);
				match(StringLiteral);
				setState(942);
				match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(944);
				match(DECLARE);
				setState(945);
				match(ERROR);
				setState(946);
				match(COLON);
				setState(948);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
				case 1:
					{
					setState(947);
					annotation();
					}
					break;
				}
				setState(950);
				pointcutExpression(0);
				setState(951);
				match(COLON);
				setState(952);
				match(StringLiteral);
				setState(953);
				match(SEMI);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(955);
				match(DECLARE);
				setState(956);
				match(SOFT);
				setState(957);
				match(COLON);
				setState(959);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(958);
					annotation();
					}
				}

				setState(961);
				type();
				setState(962);
				match(COLON);
				setState(963);
				pointcutExpression(0);
				setState(964);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(966);
				match(DECLARE);
				setState(967);
				match(PRECEDENCE);
				setState(968);
				match(COLON);
				setState(970);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(969);
					annotation();
					}
				}

				setState(972);
				typePatternList();
				setState(973);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(975);
				match(DECLARE);
				setState(976);
				match(AT);
				setState(977);
				match(ANNOTATION_TYPE);
				setState(978);
				match(COLON);
				setState(979);
				typePattern(0);
				setState(980);
				match(COLON);
				setState(981);
				annotation();
				setState(982);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(984);
				match(DECLARE);
				setState(985);
				match(AT);
				setState(986);
				match(ANNOTATION_METHOD);
				setState(987);
				match(COLON);
				setState(988);
				methodPattern();
				setState(989);
				match(COLON);
				setState(990);
				annotation();
				setState(991);
				match(SEMI);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(993);
				match(DECLARE);
				setState(994);
				match(AT);
				setState(995);
				match(ANNOTATION_CONSTRUCTOR);
				setState(996);
				match(COLON);
				setState(997);
				constructorPattern();
				setState(998);
				match(COLON);
				setState(999);
				annotation();
				setState(1000);
				match(SEMI);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1002);
				match(DECLARE);
				setState(1003);
				match(AT);
				setState(1004);
				match(ANNOTATION_FIELD);
				setState(1005);
				match(COLON);
				setState(1006);
				fieldPattern();
				setState(1007);
				match(COLON);
				setState(1008);
				annotation();
				setState(1009);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypePatternContext extends ParserRuleContext {
		public SimpleTypePatternContext simpleTypePattern() {
			return getRuleContext(SimpleTypePatternContext.class,0);
		}
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public List<TypePatternContext> typePattern() {
			return getRuleContexts(TypePatternContext.class);
		}
		public TypePatternContext typePattern(int i) {
			return getRuleContext(TypePatternContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationPatternContext annotationPattern() {
			return getRuleContext(AnnotationPatternContext.class,0);
		}
		public TerminalNode AND() { return getToken(AspectJParser.AND, 0); }
		public TerminalNode OR() { return getToken(AspectJParser.OR, 0); }
		public TypePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypePattern(this);
		}
	}

	public final TypePatternContext typePattern() throws RecognitionException {
		return typePattern(0);
	}

	private TypePatternContext typePattern(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypePatternContext _localctx = new TypePatternContext(_ctx, _parentState);
		TypePatternContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_typePattern, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1024);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOTDOT:
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case VOID:
			case DOT:
			case MUL:
			case Identifier:
				{
				setState(1014);
				simpleTypePattern();
				}
				break;
			case BANG:
				{
				setState(1015);
				match(BANG);
				setState(1016);
				typePattern(4);
				}
				break;
			case LPAREN:
				{
				setState(1017);
				match(LPAREN);
				setState(1019);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
				case 1:
					{
					setState(1018);
					annotationPattern();
					}
					break;
				}
				setState(1021);
				typePattern(0);
				setState(1022);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(1034);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1032);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
					case 1:
						{
						_localctx = new TypePatternContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_typePattern);
						setState(1026);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1027);
						match(AND);
						setState(1028);
						typePattern(3);
						}
						break;
					case 2:
						{
						_localctx = new TypePatternContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_typePattern);
						setState(1029);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1030);
						match(OR);
						setState(1031);
						typePattern(2);
						}
						break;
					}
					} 
				}
				setState(1036);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SimpleTypePatternContext extends ParserRuleContext {
		public DottedNamePatternContext dottedNamePattern() {
			return getRuleContext(DottedNamePatternContext.class,0);
		}
		public TerminalNode ADD() { return getToken(AspectJParser.ADD, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public SimpleTypePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTypePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterSimpleTypePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitSimpleTypePattern(this);
		}
	}

	public final SimpleTypePatternContext simpleTypePattern() throws RecognitionException {
		SimpleTypePatternContext _localctx = new SimpleTypePatternContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_simpleTypePattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1037);
			dottedNamePattern();
			setState(1039);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(1038);
				match(ADD);
				}
				break;
			}
			setState(1045);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1041);
					match(LBRACK);
					setState(1042);
					match(RBRACK);
					}
					} 
				}
				setState(1047);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DottedNamePatternContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(AspectJParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(AspectJParser.MUL, i);
		}
		public List<TerminalNode> DOT() { return getTokens(AspectJParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(AspectJParser.DOT, i);
		}
		public List<TerminalNode> DOTDOT() { return getTokens(AspectJParser.DOTDOT); }
		public TerminalNode DOTDOT(int i) {
			return getToken(AspectJParser.DOTDOT, i);
		}
		public TerminalNode VOID() { return getToken(AspectJParser.VOID, 0); }
		public DottedNamePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dottedNamePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterDottedNamePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitDottedNamePattern(this);
		}
	}

	public final DottedNamePatternContext dottedNamePattern() throws RecognitionException {
		DottedNamePatternContext _localctx = new DottedNamePatternContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_dottedNamePattern);
		try {
			int _alt;
			setState(1058);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOTDOT:
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case DOT:
			case MUL:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1053); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(1053);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
						case 1:
							{
							setState(1048);
							type();
							}
							break;
						case 2:
							{
							setState(1049);
							id();
							}
							break;
						case 3:
							{
							setState(1050);
							match(MUL);
							}
							break;
						case 4:
							{
							setState(1051);
							match(DOT);
							}
							break;
						case 5:
							{
							setState(1052);
							match(DOTDOT);
							}
							break;
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1055); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(1057);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalParensTypePatternContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationPatternContext annotationPattern() {
			return getRuleContext(AnnotationPatternContext.class,0);
		}
		public OptionalParensTypePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalParensTypePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterOptionalParensTypePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitOptionalParensTypePattern(this);
		}
	}

	public final OptionalParensTypePatternContext optionalParensTypePattern() throws RecognitionException {
		OptionalParensTypePatternContext _localctx = new OptionalParensTypePatternContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_optionalParensTypePattern);
		try {
			setState(1071);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1060);
				match(LPAREN);
				setState(1062);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					{
					setState(1061);
					annotationPattern();
					}
					break;
				}
				setState(1064);
				typePattern(0);
				setState(1065);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1068);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(1067);
					annotationPattern();
					}
					break;
				}
				setState(1070);
				typePattern(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldPatternContext extends ParserRuleContext {
		public List<TypePatternContext> typePattern() {
			return getRuleContexts(TypePatternContext.class);
		}
		public TypePatternContext typePattern(int i) {
			return getRuleContext(TypePatternContext.class,i);
		}
		public SimpleNamePatternContext simpleNamePattern() {
			return getRuleContext(SimpleNamePatternContext.class,0);
		}
		public AnnotationPatternContext annotationPattern() {
			return getRuleContext(AnnotationPatternContext.class,0);
		}
		public FieldModifiersPatternContext fieldModifiersPattern() {
			return getRuleContext(FieldModifiersPatternContext.class,0);
		}
		public DotOrDotDotContext dotOrDotDot() {
			return getRuleContext(DotOrDotDotContext.class,0);
		}
		public FieldPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFieldPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFieldPattern(this);
		}
	}

	public final FieldPatternContext fieldPattern() throws RecognitionException {
		FieldPatternContext _localctx = new FieldPatternContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fieldPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(1073);
				annotationPattern();
				}
				break;
			}
			setState(1077);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(1076);
				fieldModifiersPattern();
				}
				break;
			}
			setState(1079);
			typePattern(0);
			setState(1083);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(1080);
				typePattern(0);
				setState(1081);
				dotOrDotDot();
				}
				break;
			}
			setState(1085);
			simpleNamePattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldModifiersPatternContext extends ParserRuleContext {
		public FieldModifierContext fieldModifier() {
			return getRuleContext(FieldModifierContext.class,0);
		}
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public List<FieldModifiersPatternContext> fieldModifiersPattern() {
			return getRuleContexts(FieldModifiersPatternContext.class);
		}
		public FieldModifiersPatternContext fieldModifiersPattern(int i) {
			return getRuleContext(FieldModifiersPatternContext.class,i);
		}
		public FieldModifiersPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldModifiersPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFieldModifiersPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFieldModifiersPattern(this);
		}
	}

	public final FieldModifiersPatternContext fieldModifiersPattern() throws RecognitionException {
		FieldModifiersPatternContext _localctx = new FieldModifiersPatternContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_fieldModifiersPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1088);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1087);
				match(BANG);
				}
			}

			setState(1090);
			fieldModifier();
			setState(1094);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1091);
					fieldModifiersPattern();
					}
					} 
				}
				setState(1096);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldModifierContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(AspectJParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(AspectJParser.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(AspectJParser.PROTECTED, 0); }
		public TerminalNode STATIC() { return getToken(AspectJParser.STATIC, 0); }
		public TerminalNode TRANSIENT() { return getToken(AspectJParser.TRANSIENT, 0); }
		public TerminalNode FINAL() { return getToken(AspectJParser.FINAL, 0); }
		public FieldModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFieldModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFieldModifier(this);
		}
	}

	public final FieldModifierContext fieldModifier() throws RecognitionException {
		FieldModifierContext _localctx = new FieldModifierContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_fieldModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1097);
			_la = _input.LA(1);
			if ( !(((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (FINAL - 76)) | (1L << (PRIVATE - 76)) | (1L << (PROTECTED - 76)) | (1L << (PUBLIC - 76)) | (1L << (STATIC - 76)) | (1L << (TRANSIENT - 76)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DotOrDotDotContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public TerminalNode DOTDOT() { return getToken(AspectJParser.DOTDOT, 0); }
		public DotOrDotDotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotOrDotDot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterDotOrDotDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitDotOrDotDot(this);
		}
	}

	public final DotOrDotDotContext dotOrDotDot() throws RecognitionException {
		DotOrDotDotContext _localctx = new DotOrDotDotContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_dotOrDotDot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1099);
			_la = _input.LA(1);
			if ( !(_la==DOTDOT || _la==DOT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleNamePatternContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(AspectJParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(AspectJParser.MUL, i);
		}
		public SimpleNamePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleNamePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterSimpleNamePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitSimpleNamePattern(this);
		}
	}

	public final SimpleNamePatternContext simpleNamePattern() throws RecognitionException {
		SimpleNamePatternContext _localctx = new SimpleNamePatternContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_simpleNamePattern);
		int _la;
		try {
			int _alt;
			setState(1124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1101);
				id();
				setState(1106);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1102);
						match(MUL);
						setState(1103);
						id();
						}
						} 
					}
					setState(1108);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
				}
				setState(1110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MUL) {
					{
					setState(1109);
					match(MUL);
					}
				}

				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1112);
				match(MUL);
				setState(1118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1113);
						id();
						setState(1114);
						match(MUL);
						}
						} 
					}
					setState(1120);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				}
				setState(1122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD))) != 0) || _la==Identifier) {
					{
					setState(1121);
					id();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodOrConstructorPatternContext extends ParserRuleContext {
		public MethodPatternContext methodPattern() {
			return getRuleContext(MethodPatternContext.class,0);
		}
		public ConstructorPatternContext constructorPattern() {
			return getRuleContext(ConstructorPatternContext.class,0);
		}
		public MethodOrConstructorPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodOrConstructorPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMethodOrConstructorPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMethodOrConstructorPattern(this);
		}
	}

	public final MethodOrConstructorPatternContext methodOrConstructorPattern() throws RecognitionException {
		MethodOrConstructorPatternContext _localctx = new MethodOrConstructorPatternContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_methodOrConstructorPattern);
		try {
			setState(1128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1126);
				methodPattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1127);
				constructorPattern();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodPatternContext extends ParserRuleContext {
		public List<TypePatternContext> typePattern() {
			return getRuleContexts(TypePatternContext.class);
		}
		public TypePatternContext typePattern(int i) {
			return getRuleContext(TypePatternContext.class,i);
		}
		public SimpleNamePatternContext simpleNamePattern() {
			return getRuleContext(SimpleNamePatternContext.class,0);
		}
		public FormalParametersPatternContext formalParametersPattern() {
			return getRuleContext(FormalParametersPatternContext.class,0);
		}
		public AnnotationPatternContext annotationPattern() {
			return getRuleContext(AnnotationPatternContext.class,0);
		}
		public MethodModifiersPatternContext methodModifiersPattern() {
			return getRuleContext(MethodModifiersPatternContext.class,0);
		}
		public DotOrDotDotContext dotOrDotDot() {
			return getRuleContext(DotOrDotDotContext.class,0);
		}
		public ThrowsPatternContext throwsPattern() {
			return getRuleContext(ThrowsPatternContext.class,0);
		}
		public MethodPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMethodPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMethodPattern(this);
		}
	}

	public final MethodPatternContext methodPattern() throws RecognitionException {
		MethodPatternContext _localctx = new MethodPatternContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_methodPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				{
				setState(1130);
				annotationPattern();
				}
				break;
			}
			setState(1134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(1133);
				methodModifiersPattern();
				}
				break;
			}
			setState(1136);
			typePattern(0);
			setState(1140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(1137);
				typePattern(0);
				setState(1138);
				dotOrDotDot();
				}
				break;
			}
			setState(1142);
			simpleNamePattern();
			setState(1143);
			formalParametersPattern();
			setState(1145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1144);
				throwsPattern();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodModifiersPatternContext extends ParserRuleContext {
		public MethodModifierContext methodModifier() {
			return getRuleContext(MethodModifierContext.class,0);
		}
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public List<MethodModifiersPatternContext> methodModifiersPattern() {
			return getRuleContexts(MethodModifiersPatternContext.class);
		}
		public MethodModifiersPatternContext methodModifiersPattern(int i) {
			return getRuleContext(MethodModifiersPatternContext.class,i);
		}
		public MethodModifiersPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodModifiersPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMethodModifiersPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMethodModifiersPattern(this);
		}
	}

	public final MethodModifiersPatternContext methodModifiersPattern() throws RecognitionException {
		MethodModifiersPatternContext _localctx = new MethodModifiersPatternContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_methodModifiersPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1147);
				match(BANG);
				}
			}

			setState(1150);
			methodModifier();
			setState(1154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1151);
					methodModifiersPattern();
					}
					} 
				}
				setState(1156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodModifierContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(AspectJParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(AspectJParser.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(AspectJParser.PROTECTED, 0); }
		public TerminalNode STATIC() { return getToken(AspectJParser.STATIC, 0); }
		public TerminalNode SYNCHRONIZED() { return getToken(AspectJParser.SYNCHRONIZED, 0); }
		public TerminalNode FINAL() { return getToken(AspectJParser.FINAL, 0); }
		public MethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMethodModifier(this);
		}
	}

	public final MethodModifierContext methodModifier() throws RecognitionException {
		MethodModifierContext _localctx = new MethodModifierContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_methodModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1157);
			_la = _input.LA(1);
			if ( !(((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (FINAL - 76)) | (1L << (PRIVATE - 76)) | (1L << (PROTECTED - 76)) | (1L << (PUBLIC - 76)) | (1L << (STATIC - 76)) | (1L << (SYNCHRONIZED - 76)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalsPatternContext extends ParserRuleContext {
		public TerminalNode DOTDOT() { return getToken(AspectJParser.DOTDOT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public List<FormalsPatternAfterDotDotContext> formalsPatternAfterDotDot() {
			return getRuleContexts(FormalsPatternAfterDotDotContext.class);
		}
		public FormalsPatternAfterDotDotContext formalsPatternAfterDotDot(int i) {
			return getRuleContext(FormalsPatternAfterDotDotContext.class,i);
		}
		public OptionalParensTypePatternContext optionalParensTypePattern() {
			return getRuleContext(OptionalParensTypePatternContext.class,0);
		}
		public List<FormalsPatternContext> formalsPattern() {
			return getRuleContexts(FormalsPatternContext.class);
		}
		public FormalsPatternContext formalsPattern(int i) {
			return getRuleContext(FormalsPatternContext.class,i);
		}
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode ELLIPSIS() { return getToken(AspectJParser.ELLIPSIS, 0); }
		public FormalsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalsPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFormalsPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFormalsPattern(this);
		}
	}

	public final FormalsPatternContext formalsPattern() throws RecognitionException {
		FormalsPatternContext _localctx = new FormalsPatternContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_formalsPattern);
		try {
			int _alt;
			setState(1178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1159);
				match(DOTDOT);
				setState(1164);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1160);
						match(COMMA);
						setState(1161);
						formalsPatternAfterDotDot();
						}
						} 
					}
					setState(1166);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1167);
				optionalParensTypePattern();
				setState(1172);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1168);
						match(COMMA);
						setState(1169);
						formalsPattern();
						}
						} 
					}
					setState(1174);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1175);
				typePattern(0);
				setState(1176);
				match(ELLIPSIS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalsPatternAfterDotDotContext extends ParserRuleContext {
		public OptionalParensTypePatternContext optionalParensTypePattern() {
			return getRuleContext(OptionalParensTypePatternContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public List<FormalsPatternAfterDotDotContext> formalsPatternAfterDotDot() {
			return getRuleContexts(FormalsPatternAfterDotDotContext.class);
		}
		public FormalsPatternAfterDotDotContext formalsPatternAfterDotDot(int i) {
			return getRuleContext(FormalsPatternAfterDotDotContext.class,i);
		}
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode ELLIPSIS() { return getToken(AspectJParser.ELLIPSIS, 0); }
		public FormalsPatternAfterDotDotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalsPatternAfterDotDot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFormalsPatternAfterDotDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFormalsPatternAfterDotDot(this);
		}
	}

	public final FormalsPatternAfterDotDotContext formalsPatternAfterDotDot() throws RecognitionException {
		FormalsPatternAfterDotDotContext _localctx = new FormalsPatternAfterDotDotContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_formalsPatternAfterDotDot);
		try {
			int _alt;
			setState(1191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1180);
				optionalParensTypePattern();
				setState(1185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1181);
						match(COMMA);
						setState(1182);
						formalsPatternAfterDotDot();
						}
						} 
					}
					setState(1187);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1188);
				typePattern(0);
				setState(1189);
				match(ELLIPSIS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowsPatternContext extends ParserRuleContext {
		public TerminalNode THROWS() { return getToken(AspectJParser.THROWS, 0); }
		public TypePatternListContext typePatternList() {
			return getRuleContext(TypePatternListContext.class,0);
		}
		public ThrowsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwsPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterThrowsPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitThrowsPattern(this);
		}
	}

	public final ThrowsPatternContext throwsPattern() throws RecognitionException {
		ThrowsPatternContext _localctx = new ThrowsPatternContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_throwsPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1193);
			match(THROWS);
			setState(1194);
			typePatternList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypePatternListContext extends ParserRuleContext {
		public List<TypePatternContext> typePattern() {
			return getRuleContexts(TypePatternContext.class);
		}
		public TypePatternContext typePattern(int i) {
			return getRuleContext(TypePatternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public TypePatternListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typePatternList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypePatternList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypePatternList(this);
		}
	}

	public final TypePatternListContext typePatternList() throws RecognitionException {
		TypePatternListContext _localctx = new TypePatternListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_typePatternList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1196);
			typePattern(0);
			setState(1201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1197);
				match(COMMA);
				setState(1198);
				typePattern(0);
				}
				}
				setState(1203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorPatternContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(AspectJParser.NEW, 0); }
		public FormalParametersPatternContext formalParametersPattern() {
			return getRuleContext(FormalParametersPatternContext.class,0);
		}
		public AnnotationPatternContext annotationPattern() {
			return getRuleContext(AnnotationPatternContext.class,0);
		}
		public ConstructorModifiersPatternContext constructorModifiersPattern() {
			return getRuleContext(ConstructorModifiersPatternContext.class,0);
		}
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public DotOrDotDotContext dotOrDotDot() {
			return getRuleContext(DotOrDotDotContext.class,0);
		}
		public ThrowsPatternContext throwsPattern() {
			return getRuleContext(ThrowsPatternContext.class,0);
		}
		public ConstructorPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstructorPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstructorPattern(this);
		}
	}

	public final ConstructorPatternContext constructorPattern() throws RecognitionException {
		ConstructorPatternContext _localctx = new ConstructorPatternContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_constructorPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				{
				setState(1204);
				annotationPattern();
				}
				break;
			}
			setState(1208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				{
				setState(1207);
				constructorModifiersPattern();
				}
				break;
			}
			setState(1213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)) | (1L << (VOID - 66)) | (1L << (LPAREN - 66)) | (1L << (DOT - 66)) | (1L << (BANG - 66)))) != 0) || _la==MUL || _la==Identifier) {
				{
				setState(1210);
				typePattern(0);
				setState(1211);
				dotOrDotDot();
				}
			}

			setState(1215);
			match(NEW);
			setState(1216);
			formalParametersPattern();
			setState(1218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1217);
				throwsPattern();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorModifiersPatternContext extends ParserRuleContext {
		public ConstructorModifierContext constructorModifier() {
			return getRuleContext(ConstructorModifierContext.class,0);
		}
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public List<ConstructorModifiersPatternContext> constructorModifiersPattern() {
			return getRuleContexts(ConstructorModifiersPatternContext.class);
		}
		public ConstructorModifiersPatternContext constructorModifiersPattern(int i) {
			return getRuleContext(ConstructorModifiersPatternContext.class,i);
		}
		public ConstructorModifiersPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorModifiersPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstructorModifiersPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstructorModifiersPattern(this);
		}
	}

	public final ConstructorModifiersPatternContext constructorModifiersPattern() throws RecognitionException {
		ConstructorModifiersPatternContext _localctx = new ConstructorModifiersPatternContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_constructorModifiersPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1220);
				match(BANG);
				}
			}

			setState(1223);
			constructorModifier();
			setState(1227);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1224);
					constructorModifiersPattern();
					}
					} 
				}
				setState(1229);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorModifierContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(AspectJParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(AspectJParser.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(AspectJParser.PROTECTED, 0); }
		public ConstructorModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstructorModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstructorModifier(this);
		}
	}

	public final ConstructorModifierContext constructorModifier() throws RecognitionException {
		ConstructorModifierContext _localctx = new ConstructorModifierContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_constructorModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1230);
			_la = _input.LA(1);
			if ( !(((((_la - 91)) & ~0x3f) == 0 && ((1L << (_la - 91)) & ((1L << (PRIVATE - 91)) | (1L << (PROTECTED - 91)) | (1L << (PUBLIC - 91)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationPatternContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public AnnotationTypePatternContext annotationTypePattern() {
			return getRuleContext(AnnotationTypePatternContext.class,0);
		}
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public List<AnnotationPatternContext> annotationPattern() {
			return getRuleContexts(AnnotationPatternContext.class);
		}
		public AnnotationPatternContext annotationPattern(int i) {
			return getRuleContext(AnnotationPatternContext.class,i);
		}
		public AnnotationPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationPattern(this);
		}
	}

	public final AnnotationPatternContext annotationPattern() throws RecognitionException {
		AnnotationPatternContext _localctx = new AnnotationPatternContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_annotationPattern);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1232);
				match(BANG);
				}
			}

			setState(1235);
			match(AT);
			setState(1236);
			annotationTypePattern();
			setState(1240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1237);
					annotationPattern();
					}
					} 
				}
				setState(1242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypePatternContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TypePatternContext typePattern() {
			return getRuleContext(TypePatternContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public AnnotationTypePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationTypePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationTypePattern(this);
		}
	}

	public final AnnotationTypePatternContext annotationTypePattern() throws RecognitionException {
		AnnotationTypePatternContext _localctx = new AnnotationTypePatternContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_annotationTypePattern);
		try {
			setState(1248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1243);
				qualifiedName();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(1244);
				match(LPAREN);
				setState(1245);
				typePattern(0);
				setState(1246);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParametersPatternContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public FormalsPatternContext formalsPattern() {
			return getRuleContext(FormalsPatternContext.class,0);
		}
		public FormalParametersPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParametersPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFormalParametersPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFormalParametersPattern(this);
		}
	}

	public final FormalParametersPatternContext formalParametersPattern() throws RecognitionException {
		FormalParametersPatternContext _localctx = new FormalParametersPatternContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_formalParametersPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1250);
			match(LPAREN);
			setState(1252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)) | (1L << (VOID - 66)) | (1L << (LPAREN - 66)) | (1L << (DOT - 66)) | (1L << (BANG - 66)))) != 0) || _la==MUL || _la==Identifier) {
				{
				setState(1251);
				formalsPattern();
				}
			}

			setState(1254);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeOrIdentifierContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TypeOrIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeOrIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeOrIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeOrIdentifier(this);
		}
	}

	public final TypeOrIdentifierContext typeOrIdentifier() throws RecognitionException {
		TypeOrIdentifierContext _localctx = new TypeOrIdentifierContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_typeOrIdentifier);
		try {
			setState(1258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1256);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1257);
				variableDeclaratorId();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationOrIdentiferContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public AnnotationOrIdentiferContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationOrIdentifer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationOrIdentifer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationOrIdentifer(this);
		}
	}

	public final AnnotationOrIdentiferContext annotationOrIdentifer() throws RecognitionException {
		AnnotationOrIdentiferContext _localctx = new AnnotationOrIdentiferContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_annotationOrIdentifer);
		try {
			setState(1262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1260);
				qualifiedName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1261);
				id();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationsOrIdentifiersPatternContext extends ParserRuleContext {
		public TerminalNode DOTDOT() { return getToken(AspectJParser.DOTDOT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public AnnotationsOrIdentifiersPatternAfterDotDotContext annotationsOrIdentifiersPatternAfterDotDot() {
			return getRuleContext(AnnotationsOrIdentifiersPatternAfterDotDotContext.class,0);
		}
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public List<AnnotationsOrIdentifiersPatternContext> annotationsOrIdentifiersPattern() {
			return getRuleContexts(AnnotationsOrIdentifiersPatternContext.class);
		}
		public AnnotationsOrIdentifiersPatternContext annotationsOrIdentifiersPattern(int i) {
			return getRuleContext(AnnotationsOrIdentifiersPatternContext.class,i);
		}
		public TerminalNode MUL() { return getToken(AspectJParser.MUL, 0); }
		public AnnotationsOrIdentifiersPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationsOrIdentifiersPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationsOrIdentifiersPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationsOrIdentifiersPattern(this);
		}
	}

	public final AnnotationsOrIdentifiersPatternContext annotationsOrIdentifiersPattern() throws RecognitionException {
		AnnotationsOrIdentifiersPatternContext _localctx = new AnnotationsOrIdentifiersPatternContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_annotationsOrIdentifiersPattern);
		try {
			int _alt;
			setState(1285);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOTDOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1264);
				match(DOTDOT);
				setState(1267);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
				case 1:
					{
					setState(1265);
					match(COMMA);
					setState(1266);
					annotationsOrIdentifiersPatternAfterDotDot();
					}
					break;
				}
				}
				break;
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1269);
				annotationOrIdentifer();
				setState(1274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1270);
						match(COMMA);
						setState(1271);
						annotationsOrIdentifiersPattern();
						}
						} 
					}
					setState(1276);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
				}
				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 3);
				{
				setState(1277);
				match(MUL);
				setState(1282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1278);
						match(COMMA);
						setState(1279);
						annotationsOrIdentifiersPattern();
						}
						} 
					}
					setState(1284);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationsOrIdentifiersPatternAfterDotDotContext extends ParserRuleContext {
		public AnnotationOrIdentiferContext annotationOrIdentifer() {
			return getRuleContext(AnnotationOrIdentiferContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public List<AnnotationsOrIdentifiersPatternAfterDotDotContext> annotationsOrIdentifiersPatternAfterDotDot() {
			return getRuleContexts(AnnotationsOrIdentifiersPatternAfterDotDotContext.class);
		}
		public AnnotationsOrIdentifiersPatternAfterDotDotContext annotationsOrIdentifiersPatternAfterDotDot(int i) {
			return getRuleContext(AnnotationsOrIdentifiersPatternAfterDotDotContext.class,i);
		}
		public TerminalNode MUL() { return getToken(AspectJParser.MUL, 0); }
		public AnnotationsOrIdentifiersPatternAfterDotDotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationsOrIdentifiersPatternAfterDotDot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationsOrIdentifiersPatternAfterDotDot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationsOrIdentifiersPatternAfterDotDot(this);
		}
	}

	public final AnnotationsOrIdentifiersPatternAfterDotDotContext annotationsOrIdentifiersPatternAfterDotDot() throws RecognitionException {
		AnnotationsOrIdentifiersPatternAfterDotDotContext _localctx = new AnnotationsOrIdentifiersPatternAfterDotDotContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_annotationsOrIdentifiersPatternAfterDotDot);
		try {
			int _alt;
			setState(1303);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1287);
				annotationOrIdentifer();
				setState(1292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,116,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1288);
						match(COMMA);
						setState(1289);
						annotationsOrIdentifiersPatternAfterDotDot();
						}
						} 
					}
					setState(1294);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,116,_ctx);
				}
				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1295);
				match(MUL);
				setState(1300);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1296);
						match(COMMA);
						setState(1297);
						annotationsOrIdentifiersPatternAfterDotDot();
						}
						} 
					}
					setState(1302);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsPatternContext extends ParserRuleContext {
		public TypeOrIdentifierContext typeOrIdentifier() {
			return getRuleContext(TypeOrIdentifierContext.class,0);
		}
		public TerminalNode MUL() { return getToken(AspectJParser.MUL, 0); }
		public TerminalNode DOTDOT() { return getToken(AspectJParser.DOTDOT, 0); }
		public ArgsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argsPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterArgsPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitArgsPattern(this);
		}
	}

	public final ArgsPatternContext argsPattern() throws RecognitionException {
		ArgsPatternContext _localctx = new ArgsPatternContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_argsPattern);
		int _la;
		try {
			setState(1307);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1305);
				typeOrIdentifier();
				}
				break;
			case DOTDOT:
			case MUL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1306);
				_la = _input.LA(1);
				if ( !(_la==DOTDOT || _la==MUL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsPatternListContext extends ParserRuleContext {
		public List<ArgsPatternContext> argsPattern() {
			return getRuleContexts(ArgsPatternContext.class);
		}
		public ArgsPatternContext argsPattern(int i) {
			return getRuleContext(ArgsPatternContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ArgsPatternListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argsPatternList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterArgsPatternList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitArgsPatternList(this);
		}
	}

	public final ArgsPatternListContext argsPatternList() throws RecognitionException {
		ArgsPatternListContext _localctx = new ArgsPatternListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_argsPatternList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1309);
			argsPattern();
			setState(1314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1310);
				match(COMMA);
				setState(1311);
				argsPattern();
				}
				}
				setState(1316);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode ARGS() { return getToken(AspectJParser.ARGS, 0); }
		public TerminalNode AFTER() { return getToken(AspectJParser.AFTER, 0); }
		public TerminalNode AROUND() { return getToken(AspectJParser.AROUND, 0); }
		public TerminalNode ASPECT() { return getToken(AspectJParser.ASPECT, 0); }
		public TerminalNode ANNOTATION() { return getToken(AspectJParser.ANNOTATION, 0); }
		public TerminalNode BEFORE() { return getToken(AspectJParser.BEFORE, 0); }
		public TerminalNode CALL() { return getToken(AspectJParser.CALL, 0); }
		public TerminalNode CFLOW() { return getToken(AspectJParser.CFLOW, 0); }
		public TerminalNode CFLOWBELOW() { return getToken(AspectJParser.CFLOWBELOW, 0); }
		public TerminalNode DECLARE() { return getToken(AspectJParser.DECLARE, 0); }
		public TerminalNode ERROR() { return getToken(AspectJParser.ERROR, 0); }
		public TerminalNode EXECUTION() { return getToken(AspectJParser.EXECUTION, 0); }
		public TerminalNode GET() { return getToken(AspectJParser.GET, 0); }
		public TerminalNode HANDLER() { return getToken(AspectJParser.HANDLER, 0); }
		public TerminalNode INITIALIZATION() { return getToken(AspectJParser.INITIALIZATION, 0); }
		public TerminalNode ISSINGLETON() { return getToken(AspectJParser.ISSINGLETON, 0); }
		public TerminalNode PARENTS() { return getToken(AspectJParser.PARENTS, 0); }
		public TerminalNode PERCFLOW() { return getToken(AspectJParser.PERCFLOW, 0); }
		public TerminalNode PERCFLOWBELOW() { return getToken(AspectJParser.PERCFLOWBELOW, 0); }
		public TerminalNode PERTARGET() { return getToken(AspectJParser.PERTARGET, 0); }
		public TerminalNode PERTHIS() { return getToken(AspectJParser.PERTHIS, 0); }
		public TerminalNode PERTYPEWITHIN() { return getToken(AspectJParser.PERTYPEWITHIN, 0); }
		public TerminalNode POINTCUT() { return getToken(AspectJParser.POINTCUT, 0); }
		public TerminalNode PRECEDENCE() { return getToken(AspectJParser.PRECEDENCE, 0); }
		public TerminalNode PREINITIALIZATION() { return getToken(AspectJParser.PREINITIALIZATION, 0); }
		public TerminalNode PRIVILEGED() { return getToken(AspectJParser.PRIVILEGED, 0); }
		public TerminalNode RETURNING() { return getToken(AspectJParser.RETURNING, 0); }
		public TerminalNode SET() { return getToken(AspectJParser.SET, 0); }
		public TerminalNode SOFT() { return getToken(AspectJParser.SOFT, 0); }
		public TerminalNode STATICINITIALIZATION() { return getToken(AspectJParser.STATICINITIALIZATION, 0); }
		public TerminalNode TARGET() { return getToken(AspectJParser.TARGET, 0); }
		public TerminalNode THROWING() { return getToken(AspectJParser.THROWING, 0); }
		public TerminalNode WARNING() { return getToken(AspectJParser.WARNING, 0); }
		public TerminalNode WITHIN() { return getToken(AspectJParser.WITHIN, 0); }
		public TerminalNode WITHINCODE() { return getToken(AspectJParser.WITHINCODE, 0); }
		public TerminalNode ANNOTATION_METHOD() { return getToken(AspectJParser.ANNOTATION_METHOD, 0); }
		public TerminalNode Identifier() { return getToken(AspectJParser.Identifier, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_id);
		int _la;
		try {
			setState(1319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
				enterOuterAlt(_localctx, 1);
				{
				setState(1317);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1318);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(AspectJParser.CLASS, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(AspectJParser.EXTENDS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(AspectJParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1321);
			match(CLASS);
			setState(1322);
			id();
			setState(1324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1323);
				typeParameters();
				}
			}

			setState(1328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1326);
				match(EXTENDS);
				setState(1327);
				type();
				}
			}

			setState(1332);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1330);
				match(IMPLEMENTS);
				setState(1331);
				typeList();
				}
			}

			setState(1334);
			classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParameterContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(AspectJParser.EXTENDS, 0); }
		public TypeBoundContext typeBound() {
			return getRuleContext(TypeBoundContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeParameter(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_typeParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1336);
			id();
			setState(1339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1337);
				match(EXTENDS);
				setState(1338);
				typeBound();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDeclarationContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(AspectJParser.ENUM, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public TerminalNode IMPLEMENTS() { return getToken(AspectJParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public EnumConstantsContext enumConstants() {
			return getRuleContext(EnumConstantsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AspectJParser.COMMA, 0); }
		public EnumBodyDeclarationsContext enumBodyDeclarations() {
			return getRuleContext(EnumBodyDeclarationsContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitEnumDeclaration(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1341);
			match(ENUM);
			setState(1342);
			id();
			setState(1345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1343);
				match(IMPLEMENTS);
				setState(1344);
				typeList();
				}
			}

			setState(1347);
			match(LBRACE);
			setState(1349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT))) != 0) || _la==Identifier) {
				{
				setState(1348);
				enumConstants();
				}
			}

			setState(1352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1351);
				match(COMMA);
				}
			}

			setState(1355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1354);
				enumBodyDeclarations();
				}
			}

			setState(1357);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public EnumConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterEnumConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitEnumConstant(this);
		}
	}

	public final EnumConstantContext enumConstant() throws RecognitionException {
		EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_enumConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1359);
				annotation();
				}
				}
				setState(1364);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1365);
			id();
			setState(1367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(1366);
				arguments();
				}
			}

			setState(1370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(1369);
				classBody();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(AspectJParser.INTERFACE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(AspectJParser.EXTENDS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterfaceDeclaration(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1372);
			match(INTERFACE);
			setState(1373);
			id();
			setState(1375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1374);
				typeParameters();
				}
			}

			setState(1379);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1377);
				match(EXTENDS);
				setState(1378);
				typeList();
				}
			}

			setState(1381);
			interfaceBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(AspectJParser.VOID, 0); }
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public TerminalNode THROWS() { return getToken(AspectJParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_methodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				{
				setState(1383);
				type();
				}
				break;
			case VOID:
				{
				setState(1384);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1387);
			id();
			setState(1388);
			formalParameters();
			setState(1393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1389);
				match(LBRACK);
				setState(1390);
				match(RBRACK);
				}
				}
				setState(1395);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1396);
				match(THROWS);
				setState(1397);
				qualifiedNameList();
				}
			}

			setState(1402);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(1400);
				methodBody();
				}
				break;
			case SEMI:
				{
				setState(1401);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public ConstructorBodyContext constructorBody() {
			return getRuleContext(ConstructorBodyContext.class,0);
		}
		public TerminalNode THROWS() { return getToken(AspectJParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstructorDeclaration(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1404);
			id();
			setState(1405);
			formalParameters();
			setState(1408);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1406);
				match(THROWS);
				setState(1407);
				qualifiedNameList();
				}
			}

			setState(1410);
			constructorBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantDeclaratorContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AspectJParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public ConstantDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstantDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstantDeclarator(this);
		}
	}

	public final ConstantDeclaratorContext constantDeclarator() throws RecognitionException {
		ConstantDeclaratorContext _localctx = new ConstantDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_constantDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1412);
			id();
			setState(1417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1413);
				match(LBRACK);
				setState(1414);
				match(RBRACK);
				}
				}
				setState(1419);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1420);
			match(ASSIGN);
			setState(1421);
			variableInitializer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMethodDeclarationContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(AspectJParser.VOID, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public TerminalNode THROWS() { return getToken(AspectJParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public InterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterfaceMethodDeclaration(this);
		}
	}

	public final InterfaceMethodDeclarationContext interfaceMethodDeclaration() throws RecognitionException {
		InterfaceMethodDeclarationContext _localctx = new InterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_interfaceMethodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1425);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				{
				setState(1423);
				type();
				}
				break;
			case VOID:
				{
				setState(1424);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1427);
			id();
			setState(1428);
			formalParameters();
			setState(1433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1429);
				match(LBRACK);
				setState(1430);
				match(RBRACK);
				}
				}
				setState(1435);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1436);
				match(THROWS);
				setState(1437);
				qualifiedNameList();
				}
			}

			setState(1440);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitVariableDeclaratorId(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_variableDeclaratorId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1442);
			id();
			setState(1447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1443);
				match(LBRACK);
				setState(1444);
				match(RBRACK);
				}
				}
				setState(1449);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantNameContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public EnumConstantNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstantName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterEnumConstantName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitEnumConstantName(this);
		}
	}

	public final EnumConstantNameContext enumConstantName() throws RecognitionException {
		EnumConstantNameContext _localctx = new EnumConstantNameContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_enumConstantName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1450);
			id();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TypeArgumentsContext> typeArguments() {
			return getRuleContexts(TypeArgumentsContext.class);
		}
		public TypeArgumentsContext typeArguments(int i) {
			return getRuleContext(TypeArgumentsContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(AspectJParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(AspectJParser.DOT, i);
		}
		public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassOrInterfaceType(this);
		}
	}

	public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
		ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_classOrInterfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1452);
			id();
			setState(1454);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,145,_ctx) ) {
			case 1:
				{
				setState(1453);
				typeArguments();
				}
				break;
			}
			setState(1463);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,147,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1456);
					match(DOT);
					setState(1457);
					id();
					setState(1459);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,146,_ctx) ) {
					case 1:
						{
						setState(1458);
						typeArguments();
						}
						break;
					}
					}
					} 
				}
				setState(1465);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,147,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(AspectJParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(AspectJParser.DOT, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitQualifiedName(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1466);
			id();
			setState(1471);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,148,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1467);
					match(DOT);
					setState(1468);
					id();
					}
					} 
				}
				setState(1473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,148,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValuePairContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AspectJParser.ASSIGN, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterElementValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitElementValuePair(this);
		}
	}

	public final ElementValuePairContext elementValuePair() throws RecognitionException {
		ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_elementValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1474);
			id();
			setState(1475);
			match(ASSIGN);
			setState(1476);
			elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(AspectJParser.AT, 0); }
		public TerminalNode INTERFACE() { return getToken(AspectJParser.INTERFACE, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public AnnotationTypeBodyContext annotationTypeBody() {
			return getRuleContext(AnnotationTypeBodyContext.class,0);
		}
		public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationTypeDeclaration(this);
		}
	}

	public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
		AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_annotationTypeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1478);
			match(AT);
			setState(1479);
			match(INTERFACE);
			setState(1480);
			id();
			setState(1481);
			annotationTypeBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationMethodRestContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public AnnotationMethodRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationMethodRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationMethodRest(this);
		}
	}

	public final AnnotationMethodRestContext annotationMethodRest() throws RecognitionException {
		AnnotationMethodRestContext _localctx = new AnnotationMethodRestContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_annotationMethodRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1483);
			id();
			setState(1484);
			match(LPAREN);
			setState(1485);
			match(RPAREN);
			setState(1487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1486);
				defaultValue();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(AspectJParser.ASSERT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public TerminalNode COLON() { return getToken(AspectJParser.COLON, 0); }
		public TerminalNode IF() { return getToken(AspectJParser.IF, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AspectJParser.ELSE, 0); }
		public TerminalNode FOR() { return getToken(AspectJParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ForControlContext forControl() {
			return getRuleContext(ForControlContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public TerminalNode WHILE() { return getToken(AspectJParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(AspectJParser.DO, 0); }
		public TerminalNode TRY() { return getToken(AspectJParser.TRY, 0); }
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public ResourceSpecificationContext resourceSpecification() {
			return getRuleContext(ResourceSpecificationContext.class,0);
		}
		public TerminalNode SWITCH() { return getToken(AspectJParser.SWITCH, 0); }
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public TerminalNode SYNCHRONIZED() { return getToken(AspectJParser.SYNCHRONIZED, 0); }
		public TerminalNode RETURN() { return getToken(AspectJParser.RETURN, 0); }
		public TerminalNode THROW() { return getToken(AspectJParser.THROW, 0); }
		public TerminalNode BREAK() { return getToken(AspectJParser.BREAK, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode CONTINUE() { return getToken(AspectJParser.CONTINUE, 0); }
		public StatementExpressionContext statementExpression() {
			return getRuleContext(StatementExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(1594);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,162,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1489);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1490);
				match(ASSERT);
				setState(1491);
				expression(0);
				setState(1494);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1492);
					match(COLON);
					setState(1493);
					expression(0);
					}
				}

				setState(1496);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1498);
				match(IF);
				setState(1499);
				parExpression();
				setState(1500);
				statement();
				setState(1503);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,151,_ctx) ) {
				case 1:
					{
					setState(1501);
					match(ELSE);
					setState(1502);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1505);
				match(FOR);
				setState(1506);
				match(LPAREN);
				setState(1507);
				forControl();
				setState(1508);
				match(RPAREN);
				setState(1509);
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1511);
				match(WHILE);
				setState(1512);
				parExpression();
				setState(1513);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1515);
				match(DO);
				setState(1516);
				statement();
				setState(1517);
				match(WHILE);
				setState(1518);
				parExpression();
				setState(1519);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1521);
				match(TRY);
				setState(1522);
				block();
				setState(1532);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(1524); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(1523);
						catchClause();
						}
						}
						setState(1526); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==CATCH );
					setState(1529);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(1528);
						finallyBlock();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(1531);
					finallyBlock();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1534);
				match(TRY);
				setState(1535);
				resourceSpecification();
				setState(1536);
				block();
				setState(1540);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(1537);
					catchClause();
					}
					}
					setState(1542);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1544);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(1543);
					finallyBlock();
					}
				}

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1546);
				match(SWITCH);
				setState(1547);
				parExpression();
				setState(1548);
				match(LBRACE);
				setState(1552);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,157,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1549);
						switchBlockStatementGroup();
						}
						} 
					}
					setState(1554);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,157,_ctx);
				}
				setState(1558);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(1555);
					switchLabel();
					}
					}
					setState(1560);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1561);
				match(RBRACE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1563);
				match(SYNCHRONIZED);
				setState(1564);
				parExpression();
				setState(1565);
				block();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1567);
				match(RETURN);
				setState(1569);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(1568);
					expression(0);
					}
				}

				setState(1571);
				match(SEMI);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1572);
				match(THROW);
				setState(1573);
				expression(0);
				setState(1574);
				match(SEMI);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1576);
				match(BREAK);
				setState(1578);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD))) != 0) || _la==Identifier) {
					{
					setState(1577);
					id();
					}
				}

				setState(1580);
				match(SEMI);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1581);
				match(CONTINUE);
				setState(1583);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD))) != 0) || _la==Identifier) {
					{
					setState(1582);
					id();
					}
				}

				setState(1585);
				match(SEMI);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1586);
				match(SEMI);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1587);
				statementExpression();
				setState(1588);
				match(SEMI);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1590);
				id();
				setState(1591);
				match(COLON);
				setState(1592);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchClauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(AspectJParser.CATCH, 0); }
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public CatchTypeContext catchType() {
			return getRuleContext(CatchTypeContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCatchClause(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_catchClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1596);
			match(CATCH);
			setState(1597);
			match(LPAREN);
			setState(1601);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(1598);
				variableModifier();
				}
				}
				setState(1603);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1604);
			catchType();
			setState(1605);
			id();
			setState(1606);
			match(RPAREN);
			setState(1607);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalNode NEW() { return getToken(AspectJParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(AspectJParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(AspectJParser.SUB, 0); }
		public TerminalNode INC() { return getToken(AspectJParser.INC, 0); }
		public TerminalNode DEC() { return getToken(AspectJParser.DEC, 0); }
		public TerminalNode TILDE() { return getToken(AspectJParser.TILDE, 0); }
		public TerminalNode BANG() { return getToken(AspectJParser.BANG, 0); }
		public TerminalNode MUL() { return getToken(AspectJParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(AspectJParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(AspectJParser.MOD, 0); }
		public List<TerminalNode> LT() { return getTokens(AspectJParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(AspectJParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(AspectJParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(AspectJParser.GT, i);
		}
		public TerminalNode LE() { return getToken(AspectJParser.LE, 0); }
		public TerminalNode GE() { return getToken(AspectJParser.GE, 0); }
		public TerminalNode EQUAL() { return getToken(AspectJParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(AspectJParser.NOTEQUAL, 0); }
		public TerminalNode BITAND() { return getToken(AspectJParser.BITAND, 0); }
		public TerminalNode CARET() { return getToken(AspectJParser.CARET, 0); }
		public TerminalNode BITOR() { return getToken(AspectJParser.BITOR, 0); }
		public TerminalNode AND() { return getToken(AspectJParser.AND, 0); }
		public TerminalNode OR() { return getToken(AspectJParser.OR, 0); }
		public TerminalNode QUESTION() { return getToken(AspectJParser.QUESTION, 0); }
		public TerminalNode COLON() { return getToken(AspectJParser.COLON, 0); }
		public TerminalNode ASSIGN() { return getToken(AspectJParser.ASSIGN, 0); }
		public TerminalNode ADD_ASSIGN() { return getToken(AspectJParser.ADD_ASSIGN, 0); }
		public TerminalNode SUB_ASSIGN() { return getToken(AspectJParser.SUB_ASSIGN, 0); }
		public TerminalNode MUL_ASSIGN() { return getToken(AspectJParser.MUL_ASSIGN, 0); }
		public TerminalNode DIV_ASSIGN() { return getToken(AspectJParser.DIV_ASSIGN, 0); }
		public TerminalNode AND_ASSIGN() { return getToken(AspectJParser.AND_ASSIGN, 0); }
		public TerminalNode OR_ASSIGN() { return getToken(AspectJParser.OR_ASSIGN, 0); }
		public TerminalNode XOR_ASSIGN() { return getToken(AspectJParser.XOR_ASSIGN, 0); }
		public TerminalNode RSHIFT_ASSIGN() { return getToken(AspectJParser.RSHIFT_ASSIGN, 0); }
		public TerminalNode URSHIFT_ASSIGN() { return getToken(AspectJParser.URSHIFT_ASSIGN, 0); }
		public TerminalNode LSHIFT_ASSIGN() { return getToken(AspectJParser.LSHIFT_ASSIGN, 0); }
		public TerminalNode MOD_ASSIGN() { return getToken(AspectJParser.MOD_ASSIGN, 0); }
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode THIS() { return getToken(AspectJParser.THIS, 0); }
		public InnerCreatorContext innerCreator() {
			return getRuleContext(InnerCreatorContext.class,0);
		}
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(AspectJParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext explicitGenericInvocation() {
			return getRuleContext(ExplicitGenericInvocationContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(AspectJParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(AspectJParser.RBRACK, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode INSTANCEOF() { return getToken(AspectJParser.INSTANCEOF, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 132;
		enterRecursionRule(_localctx, 132, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1622);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,164,_ctx) ) {
			case 1:
				{
				setState(1610);
				primary();
				}
				break;
			case 2:
				{
				setState(1611);
				match(NEW);
				setState(1612);
				creator();
				}
				break;
			case 3:
				{
				setState(1613);
				match(LPAREN);
				setState(1614);
				type();
				setState(1615);
				match(RPAREN);
				setState(1616);
				expression(17);
				}
				break;
			case 4:
				{
				setState(1618);
				_la = _input.LA(1);
				if ( !(((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1619);
				expression(15);
				}
				break;
			case 5:
				{
				setState(1620);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==TILDE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1621);
				expression(14);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1709);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,169,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1707);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,168,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1624);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1625);
						_la = _input.LA(1);
						if ( !(((((_la - 141)) & ~0x3f) == 0 && ((1L << (_la - 141)) & ((1L << (MUL - 141)) | (1L << (DIV - 141)) | (1L << (MOD - 141)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1626);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1627);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1628);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1629);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1630);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1638);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,165,_ctx) ) {
						case 1:
							{
							setState(1631);
							match(LT);
							setState(1632);
							match(LT);
							}
							break;
						case 2:
							{
							setState(1633);
							match(GT);
							setState(1634);
							match(GT);
							setState(1635);
							match(GT);
							}
							break;
						case 3:
							{
							setState(1636);
							match(GT);
							setState(1637);
							match(GT);
							}
							break;
						}
						setState(1640);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1641);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1642);
						_la = _input.LA(1);
						if ( !(((((_la - 125)) & ~0x3f) == 0 && ((1L << (_la - 125)) & ((1L << (GT - 125)) | (1L << (LT - 125)) | (1L << (LE - 125)) | (1L << (GE - 125)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1643);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1644);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1645);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1646);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1647);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1648);
						match(BITAND);
						setState(1649);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1650);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1651);
						match(CARET);
						setState(1652);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1653);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1654);
						match(BITOR);
						setState(1655);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1656);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1657);
						match(AND);
						setState(1658);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1659);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1660);
						match(OR);
						setState(1661);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1662);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1663);
						match(QUESTION);
						setState(1664);
						expression(0);
						setState(1665);
						match(COLON);
						setState(1666);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1668);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1669);
						_la = _input.LA(1);
						if ( !(((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & ((1L << (ASSIGN - 124)) | (1L << (ADD_ASSIGN - 124)) | (1L << (SUB_ASSIGN - 124)) | (1L << (MUL_ASSIGN - 124)) | (1L << (DIV_ASSIGN - 124)) | (1L << (AND_ASSIGN - 124)) | (1L << (OR_ASSIGN - 124)) | (1L << (XOR_ASSIGN - 124)) | (1L << (MOD_ASSIGN - 124)) | (1L << (LSHIFT_ASSIGN - 124)) | (1L << (RSHIFT_ASSIGN - 124)) | (1L << (URSHIFT_ASSIGN - 124)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1670);
						expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1671);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1672);
						match(DOT);
						setState(1673);
						id();
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1674);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(1675);
						match(DOT);
						setState(1676);
						match(THIS);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1677);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1678);
						match(DOT);
						setState(1679);
						match(NEW);
						setState(1681);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==LT) {
							{
							setState(1680);
							nonWildcardTypeArguments();
							}
						}

						setState(1683);
						innerCreator();
						}
						break;
					case 16:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1684);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(1685);
						match(DOT);
						setState(1686);
						match(SUPER);
						setState(1687);
						superSuffix();
						}
						break;
					case 17:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1688);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(1689);
						match(DOT);
						setState(1690);
						explicitGenericInvocation();
						}
						break;
					case 18:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1691);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1692);
						match(LBRACK);
						setState(1693);
						expression(0);
						setState(1694);
						match(RBRACK);
						}
						break;
					case 19:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1696);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1697);
						match(LPAREN);
						setState(1699);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
							{
							setState(1698);
							expressionList();
							}
						}

						setState(1701);
						match(RPAREN);
						}
						break;
					case 20:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1702);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1703);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 21:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1704);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1705);
						match(INSTANCEOF);
						setState(1706);
						type();
						}
						break;
					}
					} 
				}
				setState(1711);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,169,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public TerminalNode THIS() { return getToken(AspectJParser.THIS, 0); }
		public TerminalNode SUPER() { return getToken(AspectJParser.SUPER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public TerminalNode CLASS() { return getToken(AspectJParser.CLASS, 0); }
		public TerminalNode VOID() { return getToken(AspectJParser.VOID, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_primary);
		try {
			setState(1733);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,171,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1712);
				match(LPAREN);
				setState(1713);
				expression(0);
				setState(1714);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1716);
				match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1717);
				match(SUPER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1718);
				literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1719);
				id();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1720);
				type();
				setState(1721);
				match(DOT);
				setState(1722);
				match(CLASS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1724);
				match(VOID);
				setState(1725);
				match(DOT);
				setState(1726);
				match(CLASS);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1727);
				nonWildcardTypeArguments();
				setState(1731);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ANNOTATION:
				case ARGS:
				case AFTER:
				case AROUND:
				case ASPECT:
				case BEFORE:
				case CALL:
				case CFLOW:
				case CFLOWBELOW:
				case DECLARE:
				case ERROR:
				case EXECUTION:
				case GET:
				case HANDLER:
				case INITIALIZATION:
				case ISSINGLETON:
				case PARENTS:
				case PERCFLOW:
				case PERCFLOWBELOW:
				case PERTARGET:
				case PERTHIS:
				case PERTYPEWITHIN:
				case POINTCUT:
				case PRECEDENCE:
				case PREINITIALIZATION:
				case PRIVILEGED:
				case RETURNING:
				case SET:
				case SOFT:
				case STATICINITIALIZATION:
				case TARGET:
				case THROWING:
				case WARNING:
				case WITHIN:
				case WITHINCODE:
				case ANNOTATION_METHOD:
				case SUPER:
				case Identifier:
					{
					setState(1728);
					explicitGenericInvocationSuffix();
					}
					break;
				case THIS:
					{
					setState(1729);
					match(THIS);
					setState(1730);
					arguments();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatedNameContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public List<TypeArgumentsOrDiamondContext> typeArgumentsOrDiamond() {
			return getRuleContexts(TypeArgumentsOrDiamondContext.class);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond(int i) {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(AspectJParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(AspectJParser.DOT, i);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public CreatedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createdName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCreatedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCreatedName(this);
		}
	}

	public final CreatedNameContext createdName() throws RecognitionException {
		CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_createdName);
		int _la;
		try {
			setState(1750);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1735);
				id();
				setState(1737);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1736);
					typeArgumentsOrDiamond();
					}
				}

				setState(1746);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(1739);
					match(DOT);
					setState(1740);
					id();
					setState(1742);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1741);
						typeArgumentsOrDiamond();
						}
					}

					}
					}
					setState(1748);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1749);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InnerCreatorContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() {
			return getRuleContext(NonWildcardTypeArgumentsOrDiamondContext.class,0);
		}
		public InnerCreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerCreator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInnerCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInnerCreator(this);
		}
	}

	public final InnerCreatorContext innerCreator() throws RecognitionException {
		InnerCreatorContext _localctx = new InnerCreatorContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_innerCreator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1752);
			id();
			setState(1754);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1753);
				nonWildcardTypeArgumentsOrDiamond();
				}
			}

			setState(1756);
			classCreatorRest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperSuffixContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public SuperSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterSuperSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitSuperSuffix(this);
		}
	}

	public final SuperSuffixContext superSuffix() throws RecognitionException {
		SuperSuffixContext _localctx = new SuperSuffixContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_superSuffix);
		try {
			setState(1764);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1758);
				arguments();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1759);
				match(DOT);
				setState(1760);
				id();
				setState(1762);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,177,_ctx) ) {
				case 1:
					{
					setState(1761);
					arguments();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationSuffixContext extends ParserRuleContext {
		public TerminalNode SUPER() { return getToken(AspectJParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocationSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterExplicitGenericInvocationSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitExplicitGenericInvocationSuffix(this);
		}
	}

	public final ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() throws RecognitionException {
		ExplicitGenericInvocationSuffixContext _localctx = new ExplicitGenericInvocationSuffixContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_explicitGenericInvocationSuffix);
		try {
			setState(1771);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1766);
				match(SUPER);
				setState(1767);
				superSuffix();
				}
				break;
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1768);
				id();
				setState(1769);
				arguments();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AspectJParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1774);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,180,_ctx) ) {
			case 1:
				{
				setState(1773);
				packageDeclaration();
				}
				break;
			}
			setState(1779);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(1776);
				importDeclaration();
				}
				}
				setState(1781);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1785);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASPECT) | (1L << PRIVILEGED) | (1L << AT) | (1L << ABSTRACT))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (CLASS - 67)) | (1L << (ENUM - 67)) | (1L << (FINAL - 67)) | (1L << (INTERFACE - 67)) | (1L << (NATIVE - 67)) | (1L << (PRIVATE - 67)) | (1L << (PROTECTED - 67)) | (1L << (PUBLIC - 67)) | (1L << (STATIC - 67)) | (1L << (STRICTFP - 67)) | (1L << (SYNCHRONIZED - 67)) | (1L << (TRANSIENT - 67)) | (1L << (VOLATILE - 67)) | (1L << (SEMI - 67)))) != 0)) {
				{
				{
				setState(1782);
				typeDeclaration();
				}
				}
				setState(1787);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1788);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageDeclarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(AspectJParser.PACKAGE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPackageDeclaration(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_packageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1793);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1790);
				annotation();
				}
				}
				setState(1795);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1796);
			match(PACKAGE);
			setState(1797);
			qualifiedName();
			setState(1798);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclarationContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(AspectJParser.IMPORT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public TerminalNode STATIC() { return getToken(AspectJParser.STATIC, 0); }
		public TerminalNode DOT() { return getToken(AspectJParser.DOT, 0); }
		public TerminalNode MUL() { return getToken(AspectJParser.MUL, 0); }
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitImportDeclaration(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1800);
			match(IMPORT);
			setState(1802);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(1801);
				match(STATIC);
				}
			}

			setState(1804);
			qualifiedName();
			setState(1807);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(1805);
				match(DOT);
				setState(1806);
				match(MUL);
				}
			}

			setState(1809);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifierContext extends ParserRuleContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier() {
			return getRuleContext(ClassOrInterfaceModifierContext.class,0);
		}
		public TerminalNode NATIVE() { return getToken(AspectJParser.NATIVE, 0); }
		public TerminalNode SYNCHRONIZED() { return getToken(AspectJParser.SYNCHRONIZED, 0); }
		public TerminalNode TRANSIENT() { return getToken(AspectJParser.TRANSIENT, 0); }
		public TerminalNode VOLATILE() { return getToken(AspectJParser.VOLATILE, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitModifier(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_modifier);
		int _la;
		try {
			setState(1813);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
			case ABSTRACT:
			case FINAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
				enterOuterAlt(_localctx, 1);
				{
				setState(1811);
				classOrInterfaceModifier();
				}
				break;
			case NATIVE:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1812);
				_la = _input.LA(1);
				if ( !(((((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & ((1L << (NATIVE - 88)) | (1L << (SYNCHRONIZED - 88)) | (1L << (TRANSIENT - 88)) | (1L << (VOLATILE - 88)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(AspectJParser.PUBLIC, 0); }
		public TerminalNode PROTECTED() { return getToken(AspectJParser.PROTECTED, 0); }
		public TerminalNode PRIVATE() { return getToken(AspectJParser.PRIVATE, 0); }
		public TerminalNode STATIC() { return getToken(AspectJParser.STATIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(AspectJParser.ABSTRACT, 0); }
		public TerminalNode FINAL() { return getToken(AspectJParser.FINAL, 0); }
		public TerminalNode STRICTFP() { return getToken(AspectJParser.STRICTFP, 0); }
		public ClassOrInterfaceModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassOrInterfaceModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassOrInterfaceModifier(this);
		}
	}

	public final ClassOrInterfaceModifierContext classOrInterfaceModifier() throws RecognitionException {
		ClassOrInterfaceModifierContext _localctx = new ClassOrInterfaceModifierContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_classOrInterfaceModifier);
		int _la;
		try {
			setState(1817);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1815);
				annotation();
				}
				break;
			case ABSTRACT:
			case FINAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
				enterOuterAlt(_localctx, 2);
				{
				setState(1816);
				_la = _input.LA(1);
				if ( !(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & ((1L << (ABSTRACT - 59)) | (1L << (FINAL - 59)) | (1L << (PRIVATE - 59)) | (1L << (PROTECTED - 59)) | (1L << (PUBLIC - 59)) | (1L << (STATIC - 59)) | (1L << (STRICTFP - 59)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableModifierContext extends ParserRuleContext {
		public TerminalNode FINAL() { return getToken(AspectJParser.FINAL, 0); }
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public VariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitVariableModifier(this);
		}
	}

	public final VariableModifierContext variableModifier() throws RecognitionException {
		VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_variableModifier);
		try {
			setState(1821);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(1819);
				match(FINAL);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1820);
				annotation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParametersContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(AspectJParser.LT, 0); }
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(AspectJParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeParameters(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1823);
			match(LT);
			setState(1824);
			typeParameter();
			setState(1829);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1825);
				match(COMMA);
				setState(1826);
				typeParameter();
				}
				}
				setState(1831);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1832);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeBoundContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> BITAND() { return getTokens(AspectJParser.BITAND); }
		public TerminalNode BITAND(int i) {
			return getToken(AspectJParser.BITAND, i);
		}
		public TypeBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeBound(this);
		}
	}

	public final TypeBoundContext typeBound() throws RecognitionException {
		TypeBoundContext _localctx = new TypeBoundContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_typeBound);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1834);
			type();
			setState(1839);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(1835);
				match(BITAND);
				setState(1836);
				type();
				}
				}
				setState(1841);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantsContext extends ParserRuleContext {
		public List<EnumConstantContext> enumConstant() {
			return getRuleContexts(EnumConstantContext.class);
		}
		public EnumConstantContext enumConstant(int i) {
			return getRuleContext(EnumConstantContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public EnumConstantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterEnumConstants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitEnumConstants(this);
		}
	}

	public final EnumConstantsContext enumConstants() throws RecognitionException {
		EnumConstantsContext _localctx = new EnumConstantsContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_enumConstants);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1842);
			enumConstant();
			setState(1847);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1843);
					match(COMMA);
					setState(1844);
					enumConstant();
					}
					} 
				}
				setState(1849);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumBodyDeclarationsContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public EnumBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterEnumBodyDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitEnumBodyDeclarations(this);
		}
	}

	public final EnumBodyDeclarationsContext enumBodyDeclarations() throws RecognitionException {
		EnumBodyDeclarationsContext _localctx = new EnumBodyDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_enumBodyDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1850);
			match(SEMI);
			setState(1854);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(1851);
				classBodyDeclaration();
				}
				}
				setState(1856);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeList(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1857);
			type();
			setState(1862);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1858);
				match(COMMA);
				setState(1859);
				type();
				}
				}
				setState(1864);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1865);
			match(LBRACE);
			setState(1869);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(1866);
				classBodyDeclaration();
				}
				}
				setState(1871);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1872);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<InterfaceBodyDeclarationContext> interfaceBodyDeclaration() {
			return getRuleContexts(InterfaceBodyDeclarationContext.class);
		}
		public InterfaceBodyDeclarationContext interfaceBodyDeclaration(int i) {
			return getRuleContext(InterfaceBodyDeclarationContext.class,i);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterfaceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterfaceBody(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1874);
			match(LBRACE);
			setState(1878);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(1875);
				interfaceBodyDeclaration();
				}
				}
				setState(1880);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1881);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterGenericMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitGenericMethodDeclaration(this);
		}
	}

	public final GenericMethodDeclarationContext genericMethodDeclaration() throws RecognitionException {
		GenericMethodDeclarationContext _localctx = new GenericMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_genericMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1883);
			typeParameters();
			setState(1884);
			methodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericConstructorDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterGenericConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitGenericConstructorDeclaration(this);
		}
	}

	public final GenericConstructorDeclarationContext genericConstructorDeclaration() throws RecognitionException {
		GenericConstructorDeclarationContext _localctx = new GenericConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_genericConstructorDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1886);
			typeParameters();
			setState(1887);
			constructorDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFieldDeclaration(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_fieldDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1889);
			type();
			setState(1890);
			variableDeclarators();
			setState(1891);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyDeclarationContext extends ParserRuleContext {
		public InterfaceMemberDeclarationContext interfaceMemberDeclaration() {
			return getRuleContext(InterfaceMemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public InterfaceBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterfaceBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterfaceBodyDeclaration(this);
		}
	}

	public final InterfaceBodyDeclarationContext interfaceBodyDeclaration() throws RecognitionException {
		InterfaceBodyDeclarationContext _localctx = new InterfaceBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_interfaceBodyDeclaration);
		try {
			int _alt;
			setState(1901);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case AT:
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOID:
			case VOLATILE:
			case LT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1896);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,196,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1893);
						modifier();
						}
						} 
					}
					setState(1898);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,196,_ctx);
				}
				setState(1899);
				interfaceMemberDeclaration();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(1900);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMemberDeclarationContext extends ParserRuleContext {
		public ConstDeclarationContext constDeclaration() {
			return getRuleContext(ConstDeclarationContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() {
			return getRuleContext(GenericInterfaceMethodDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterInterfaceMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitInterfaceMemberDeclaration(this);
		}
	}

	public final InterfaceMemberDeclarationContext interfaceMemberDeclaration() throws RecognitionException {
		InterfaceMemberDeclarationContext _localctx = new InterfaceMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_interfaceMemberDeclaration);
		try {
			setState(1910);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,198,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1903);
				constDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1904);
				interfaceMethodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1905);
				genericInterfaceMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1906);
				interfaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1907);
				annotationTypeDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1908);
				classDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1909);
				enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ConstantDeclaratorContext> constantDeclarator() {
			return getRuleContexts(ConstantDeclaratorContext.class);
		}
		public ConstantDeclaratorContext constantDeclarator(int i) {
			return getRuleContext(ConstantDeclaratorContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ConstDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstDeclaration(this);
		}
	}

	public final ConstDeclarationContext constDeclaration() throws RecognitionException {
		ConstDeclarationContext _localctx = new ConstDeclarationContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_constDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1912);
			type();
			setState(1913);
			constantDeclarator();
			setState(1918);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1914);
				match(COMMA);
				setState(1915);
				constantDeclarator();
				}
				}
				setState(1920);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1921);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericInterfaceMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericInterfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitGenericInterfaceMethodDeclaration(this);
		}
	}

	public final GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() throws RecognitionException {
		GenericInterfaceMethodDeclarationContext _localctx = new GenericInterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_genericInterfaceMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1923);
			typeParameters();
			setState(1924);
			interfaceMethodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorsContext extends ParserRuleContext {
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public VariableDeclaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterVariableDeclarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitVariableDeclarators(this);
		}
	}

	public final VariableDeclaratorsContext variableDeclarators() throws RecognitionException {
		VariableDeclaratorsContext _localctx = new VariableDeclaratorsContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_variableDeclarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1926);
			variableDeclarator();
			setState(1931);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1927);
				match(COMMA);
				setState(1928);
				variableDeclarator();
				}
				}
				setState(1933);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AspectJParser.ASSIGN, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitVariableDeclarator(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_variableDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1934);
			variableDeclaratorId();
			setState(1937);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(1935);
				match(ASSIGN);
				setState(1936);
				variableInitializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableInitializerContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitVariableInitializer(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_variableInitializer);
		try {
			setState(1941);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1939);
				arrayInitializer();
				}
				break;
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1940);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitArrayInitializer(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_arrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1943);
			match(LBRACE);
			setState(1955);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				setState(1944);
				variableInitializer();
				setState(1949);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,203,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1945);
						match(COMMA);
						setState(1946);
						variableInitializer();
						}
						} 
					}
					setState(1951);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,203,_ctx);
				}
				setState(1953);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1952);
					match(COMMA);
					}
				}

				}
			}

			setState(1957);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_type);
		try {
			int _alt;
			setState(1975);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1959);
				classOrInterfaceType();
				setState(1964);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,206,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1960);
						match(LBRACK);
						setState(1961);
						match(RBRACK);
						}
						} 
					}
					setState(1966);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,206,_ctx);
				}
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1967);
				primitiveType();
				setState(1972);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,207,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1968);
						match(LBRACK);
						setState(1969);
						match(RBRACK);
						}
						} 
					}
					setState(1974);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,207,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(AspectJParser.BOOLEAN, 0); }
		public TerminalNode CHAR() { return getToken(AspectJParser.CHAR, 0); }
		public TerminalNode BYTE() { return getToken(AspectJParser.BYTE, 0); }
		public TerminalNode SHORT() { return getToken(AspectJParser.SHORT, 0); }
		public TerminalNode INT() { return getToken(AspectJParser.INT, 0); }
		public TerminalNode LONG() { return getToken(AspectJParser.LONG, 0); }
		public TerminalNode FLOAT() { return getToken(AspectJParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(AspectJParser.DOUBLE, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitPrimitiveType(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1977);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & ((1L << (BOOLEAN - 61)) | (1L << (BYTE - 61)) | (1L << (CHAR - 61)) | (1L << (DOUBLE - 61)) | (1L << (FLOAT - 61)) | (1L << (INT - 61)) | (1L << (LONG - 61)) | (1L << (SHORT - 61)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(AspectJParser.LT, 0); }
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public TerminalNode GT() { return getToken(AspectJParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeArguments(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_typeArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1979);
			match(LT);
			setState(1980);
			typeArgument();
			setState(1985);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1981);
				match(COMMA);
				setState(1982);
				typeArgument();
				}
				}
				setState(1987);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1988);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode QUESTION() { return getToken(AspectJParser.QUESTION, 0); }
		public TerminalNode EXTENDS() { return getToken(AspectJParser.EXTENDS, 0); }
		public TerminalNode SUPER() { return getToken(AspectJParser.SUPER, 0); }
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeArgument(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_typeArgument);
		int _la;
		try {
			setState(1996);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1990);
				type();
				}
				break;
			case QUESTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(1991);
				match(QUESTION);
				setState(1994);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS || _la==SUPER) {
					{
					setState(1992);
					_la = _input.LA(1);
					if ( !(_la==EXTENDS || _la==SUPER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1993);
					type();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameListContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public QualifiedNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterQualifiedNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitQualifiedNameList(this);
		}
	}

	public final QualifiedNameListContext qualifiedNameList() throws RecognitionException {
		QualifiedNameListContext _localctx = new QualifiedNameListContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_qualifiedNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1998);
			qualifiedName();
			setState(2003);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1999);
				match(COMMA);
				setState(2000);
				qualifiedName();
				}
				}
				setState(2005);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParametersContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2006);
			match(LPAREN);
			setState(2008);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)))) != 0) || _la==Identifier) {
				{
				setState(2007);
				formalParameterList();
				}
			}

			setState(2010);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public LastFormalParameterContext lastFormalParameter() {
			return getRuleContext(LastFormalParameterContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_formalParameterList);
		int _la;
		try {
			int _alt;
			setState(2025);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,216,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2012);
				formalParameter();
				setState(2017);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,214,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2013);
						match(COMMA);
						setState(2014);
						formalParameter();
						}
						} 
					}
					setState(2019);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,214,_ctx);
				}
				setState(2022);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(2020);
					match(COMMA);
					setState(2021);
					lastFormalParameter();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2024);
				lastFormalParameter();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_formalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2030);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2027);
				variableModifier();
				}
				}
				setState(2032);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2033);
			type();
			setState(2034);
			variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LastFormalParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ELLIPSIS() { return getToken(AspectJParser.ELLIPSIS, 0); }
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LastFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterLastFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitLastFormalParameter(this);
		}
	}

	public final LastFormalParameterContext lastFormalParameter() throws RecognitionException {
		LastFormalParameterContext _localctx = new LastFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_lastFormalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2039);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2036);
				variableModifier();
				}
				}
				setState(2041);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2042);
			type();
			setState(2043);
			match(ELLIPSIS);
			setState(2044);
			variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitMethodBody(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_methodBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2046);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ConstructorBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstructorBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstructorBody(this);
		}
	}

	public final ConstructorBodyContext constructorBody() throws RecognitionException {
		ConstructorBodyContext _localctx = new ConstructorBodyContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_constructorBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2048);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(AspectJParser.IntegerLiteral, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(AspectJParser.FloatingPointLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(AspectJParser.CharacterLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(AspectJParser.StringLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(AspectJParser.BooleanLiteral, 0); }
		public TerminalNode NullLiteral() { return getToken(AspectJParser.NullLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2050);
			_la = _input.LA(1);
			if ( !(((((_la - 109)) & ~0x3f) == 0 && ((1L << (_la - 109)) & ((1L << (IntegerLiteral - 109)) | (1L << (FloatingPointLiteral - 109)) | (1L << (BooleanLiteral - 109)) | (1L << (CharacterLiteral - 109)) | (1L << (StringLiteral - 109)) | (1L << (NullLiteral - 109)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationNameContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public AnnotationNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationName(this);
		}
	}

	public final AnnotationNameContext annotationName() throws RecognitionException {
		AnnotationNameContext _localctx = new AnnotationNameContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_annotationName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2052);
			qualifiedName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValuePairsContext extends ParserRuleContext {
		public List<ElementValuePairContext> elementValuePair() {
			return getRuleContexts(ElementValuePairContext.class);
		}
		public ElementValuePairContext elementValuePair(int i) {
			return getRuleContext(ElementValuePairContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ElementValuePairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterElementValuePairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitElementValuePairs(this);
		}
	}

	public final ElementValuePairsContext elementValuePairs() throws RecognitionException {
		ElementValuePairsContext _localctx = new ElementValuePairsContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_elementValuePairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2054);
			elementValuePair();
			setState(2059);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2055);
				match(COMMA);
				setState(2056);
				elementValuePair();
				}
				}
				setState(2061);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ElementValueArrayInitializerContext elementValueArrayInitializer() {
			return getRuleContext(ElementValueArrayInitializerContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitElementValue(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_elementValue);
		try {
			setState(2065);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(2062);
				expression(0);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(2063);
				annotation();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2064);
				elementValueArrayInitializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValueArrayInitializerContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<ElementValueContext> elementValue() {
			return getRuleContexts(ElementValueContext.class);
		}
		public ElementValueContext elementValue(int i) {
			return getRuleContext(ElementValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueArrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterElementValueArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitElementValueArrayInitializer(this);
		}
	}

	public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
		ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_elementValueArrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2067);
			match(LBRACE);
			setState(2076);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				setState(2068);
				elementValue();
				setState(2073);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,221,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2069);
						match(COMMA);
						setState(2070);
						elementValue();
						}
						} 
					}
					setState(2075);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,221,_ctx);
				}
				}
			}

			setState(2079);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(2078);
				match(COMMA);
				}
			}

			setState(2081);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<AnnotationTypeElementDeclarationContext> annotationTypeElementDeclaration() {
			return getRuleContexts(AnnotationTypeElementDeclarationContext.class);
		}
		public AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration(int i) {
			return getRuleContext(AnnotationTypeElementDeclarationContext.class,i);
		}
		public AnnotationTypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationTypeBody(this);
		}
	}

	public final AnnotationTypeBodyContext annotationTypeBody() throws RecognitionException {
		AnnotationTypeBodyContext _localctx = new AnnotationTypeBodyContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_annotationTypeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2083);
			match(LBRACE);
			setState(2087);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOLATILE - 66)) | (1L << (SEMI - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(2084);
				annotationTypeElementDeclaration();
				}
				}
				setState(2089);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2090);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeElementDeclarationContext extends ParserRuleContext {
		public AnnotationTypeElementRestContext annotationTypeElementRest() {
			return getRuleContext(AnnotationTypeElementRestContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public AnnotationTypeElementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationTypeElementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationTypeElementDeclaration(this);
		}
	}

	public final AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() throws RecognitionException {
		AnnotationTypeElementDeclarationContext _localctx = new AnnotationTypeElementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_annotationTypeElementDeclaration);
		try {
			int _alt;
			setState(2100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case AT:
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(2095);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,225,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2092);
						modifier();
						}
						} 
					}
					setState(2097);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,225,_ctx);
				}
				setState(2098);
				annotationTypeElementRest();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(2099);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeElementRestContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() {
			return getRuleContext(AnnotationMethodOrConstantRestContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public AnnotationTypeElementRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationTypeElementRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationTypeElementRest(this);
		}
	}

	public final AnnotationTypeElementRestContext annotationTypeElementRest() throws RecognitionException {
		AnnotationTypeElementRestContext _localctx = new AnnotationTypeElementRestContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_annotationTypeElementRest);
		try {
			setState(2122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(2102);
				type();
				setState(2103);
				annotationMethodOrConstantRest();
				setState(2104);
				match(SEMI);
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 2);
				{
				setState(2106);
				classDeclaration();
				setState(2108);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,227,_ctx) ) {
				case 1:
					{
					setState(2107);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case INTERFACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2110);
				interfaceDeclaration();
				setState(2112);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,228,_ctx) ) {
				case 1:
					{
					setState(2111);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case ENUM:
				enterOuterAlt(_localctx, 4);
				{
				setState(2114);
				enumDeclaration();
				setState(2116);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,229,_ctx) ) {
				case 1:
					{
					setState(2115);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 5);
				{
				setState(2118);
				annotationTypeDeclaration();
				setState(2120);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,230,_ctx) ) {
				case 1:
					{
					setState(2119);
					match(SEMI);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationMethodOrConstantRestContext extends ParserRuleContext {
		public AnnotationMethodRestContext annotationMethodRest() {
			return getRuleContext(AnnotationMethodRestContext.class,0);
		}
		public AnnotationConstantRestContext annotationConstantRest() {
			return getRuleContext(AnnotationConstantRestContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodOrConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationMethodOrConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationMethodOrConstantRest(this);
		}
	}

	public final AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() throws RecognitionException {
		AnnotationMethodOrConstantRestContext _localctx = new AnnotationMethodOrConstantRestContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_annotationMethodOrConstantRest);
		try {
			setState(2126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,232,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2124);
				annotationMethodRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2125);
				annotationConstantRest();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationConstantRestContext extends ParserRuleContext {
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public AnnotationConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterAnnotationConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitAnnotationConstantRest(this);
		}
	}

	public final AnnotationConstantRestContext annotationConstantRest() throws RecognitionException {
		AnnotationConstantRestContext _localctx = new AnnotationConstantRestContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_annotationConstantRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2128);
			variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(AspectJParser.DEFAULT, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2130);
			match(DEFAULT);
			setState(2131);
			elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(AspectJParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AspectJParser.RBRACE, 0); }
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2133);
			match(LBRACE);
			setState(2137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (CONTINUE - 66)) | (1L << (DO - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (FOR - 66)) | (1L << (IF - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (NEW - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (RETURN - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SUPER - 66)) | (1L << (SWITCH - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (THIS - 66)) | (1L << (THROW - 66)) | (1L << (TRANSIENT - 66)) | (1L << (TRY - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (WHILE - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				{
				setState(2134);
				blockStatement();
				}
				}
				setState(2139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2140);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public LocalVariableDeclarationStatementContext localVariableDeclarationStatement() {
			return getRuleContext(LocalVariableDeclarationStatementContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitBlockStatement(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_blockStatement);
		try {
			setState(2145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,234,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2142);
				localVariableDeclarationStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2143);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2144);
				typeDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVariableDeclarationStatementContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public LocalVariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterLocalVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitLocalVariableDeclarationStatement(this);
		}
	}

	public final LocalVariableDeclarationStatementContext localVariableDeclarationStatement() throws RecognitionException {
		LocalVariableDeclarationStatementContext _localctx = new LocalVariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_localVariableDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2147);
			localVariableDeclaration();
			setState(2148);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterLocalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitLocalVariableDeclaration(this);
		}
	}

	public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
		LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_localVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2150);
				variableModifier();
				}
				}
				setState(2155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2156);
			type();
			setState(2157);
			variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchTypeContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<TerminalNode> BITOR() { return getTokens(AspectJParser.BITOR); }
		public TerminalNode BITOR(int i) {
			return getToken(AspectJParser.BITOR, i);
		}
		public CatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCatchType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCatchType(this);
		}
	}

	public final CatchTypeContext catchType() throws RecognitionException {
		CatchTypeContext _localctx = new CatchTypeContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_catchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2159);
			qualifiedName();
			setState(2164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(2160);
				match(BITOR);
				setState(2161);
				qualifiedName();
				}
				}
				setState(2166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinallyBlockContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(AspectJParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterFinallyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitFinallyBlock(this);
		}
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2167);
			match(FINALLY);
			setState(2168);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceSpecificationContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ResourcesContext resources() {
			return getRuleContext(ResourcesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(AspectJParser.SEMI, 0); }
		public ResourceSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterResourceSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitResourceSpecification(this);
		}
	}

	public final ResourceSpecificationContext resourceSpecification() throws RecognitionException {
		ResourceSpecificationContext _localctx = new ResourceSpecificationContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_resourceSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2170);
			match(LPAREN);
			setState(2171);
			resources();
			setState(2173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(2172);
				match(SEMI);
				}
			}

			setState(2175);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourcesContext extends ParserRuleContext {
		public List<ResourceContext> resource() {
			return getRuleContexts(ResourceContext.class);
		}
		public ResourceContext resource(int i) {
			return getRuleContext(ResourceContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(AspectJParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(AspectJParser.SEMI, i);
		}
		public ResourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resources; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterResources(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitResources(this);
		}
	}

	public final ResourcesContext resources() throws RecognitionException {
		ResourcesContext _localctx = new ResourcesContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_resources);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2177);
			resource();
			setState(2182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,238,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2178);
					match(SEMI);
					setState(2179);
					resource();
					}
					} 
				}
				setState(2184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,238,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceContext extends ParserRuleContext {
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(AspectJParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2185);
				variableModifier();
				}
				}
				setState(2190);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2191);
			classOrInterfaceType();
			setState(2192);
			variableDeclaratorId();
			setState(2193);
			match(ASSIGN);
			setState(2194);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitSwitchBlockStatementGroup(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_switchBlockStatementGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2196);
				switchLabel();
				}
				}
				setState(2199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE || _la==DEFAULT );
			setState(2202); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2201);
				blockStatement();
				}
				}
				setState(2204); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (CONTINUE - 66)) | (1L << (DO - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (FOR - 66)) | (1L << (IF - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (NEW - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (RETURN - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SUPER - 66)) | (1L << (SWITCH - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (THIS - 66)) | (1L << (THROW - 66)) | (1L << (TRANSIENT - 66)) | (1L << (TRY - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (WHILE - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchLabelContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(AspectJParser.CASE, 0); }
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(AspectJParser.COLON, 0); }
		public EnumConstantNameContext enumConstantName() {
			return getRuleContext(EnumConstantNameContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(AspectJParser.DEFAULT, 0); }
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitSwitchLabel(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_switchLabel);
		try {
			setState(2216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,242,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2206);
				match(CASE);
				setState(2207);
				constantExpression();
				setState(2208);
				match(COLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2210);
				match(CASE);
				setState(2211);
				enumConstantName();
				setState(2212);
				match(COLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2214);
				match(DEFAULT);
				setState(2215);
				match(COLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForControlContext extends ParserRuleContext {
		public EnhancedForControlContext enhancedForControl() {
			return getRuleContext(EnhancedForControlContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(AspectJParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(AspectJParser.SEMI, i);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitForControl(this);
		}
	}

	public final ForControlContext forControl() throws RecognitionException {
		ForControlContext _localctx = new ForControlContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_forControl);
		int _la;
		try {
			setState(2230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,246,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2218);
				enhancedForControl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(2219);
					forInit();
					}
				}

				setState(2222);
				match(SEMI);
				setState(2224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(2223);
					expression(0);
					}
				}

				setState(2226);
				match(SEMI);
				setState(2228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(2227);
					forUpdate();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitForInit(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_forInit);
		try {
			setState(2234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,247,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2232);
				localVariableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2233);
				expressionList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnhancedForControlContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public TerminalNode COLON() { return getToken(AspectJParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public EnhancedForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterEnhancedForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitEnhancedForControl(this);
		}
	}

	public final EnhancedForControlContext enhancedForControl() throws RecognitionException {
		EnhancedForControlContext _localctx = new EnhancedForControlContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_enhancedForControl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2236);
				variableModifier();
				}
				}
				setState(2241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2242);
			type();
			setState(2243);
			variableDeclaratorId();
			setState(2244);
			match(COLON);
			setState(2245);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForUpdateContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitForUpdate(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2247);
			expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitParExpression(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2249);
			match(LPAREN);
			setState(2250);
			expression(0);
			setState(2251);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AspectJParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AspectJParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2253);
			expression(0);
			setState(2258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2254);
				match(COMMA);
				setState(2255);
				expression(0);
				}
				}
				setState(2260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterStatementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitStatementExpression(this);
		}
	}

	public final StatementExpressionContext statementExpression() throws RecognitionException {
		StatementExpressionContext _localctx = new StatementExpressionContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_statementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2261);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterConstantExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitConstantExpression(this);
		}
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2263);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public CreatedNameContext createdName() {
			return getRuleContext(CreatedNameContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public ArrayCreatorRestContext arrayCreatorRest() {
			return getRuleContext(ArrayCreatorRestContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitCreator(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_creator);
		try {
			setState(2274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(2265);
				nonWildcardTypeArguments();
				setState(2266);
				createdName();
				setState(2267);
				classCreatorRest();
				}
				break;
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(2269);
				createdName();
				setState(2272);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LBRACK:
					{
					setState(2270);
					arrayCreatorRest();
					}
					break;
				case LPAREN:
					{
					setState(2271);
					classCreatorRest();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayCreatorRestContext extends ParserRuleContext {
		public List<TerminalNode> LBRACK() { return getTokens(AspectJParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(AspectJParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(AspectJParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(AspectJParser.RBRACK, i);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterArrayCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitArrayCreatorRest(this);
		}
	}

	public final ArrayCreatorRestContext arrayCreatorRest() throws RecognitionException {
		ArrayCreatorRestContext _localctx = new ArrayCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_arrayCreatorRest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2276);
			match(LBRACK);
			setState(2304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RBRACK:
				{
				setState(2277);
				match(RBRACK);
				setState(2282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACK) {
					{
					{
					setState(2278);
					match(LBRACK);
					setState(2279);
					match(RBRACK);
					}
					}
					setState(2284);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2285);
				arrayInitializer();
				}
				break;
			case ANNOTATION:
			case ARGS:
			case AFTER:
			case AROUND:
			case ASPECT:
			case BEFORE:
			case CALL:
			case CFLOW:
			case CFLOWBELOW:
			case DECLARE:
			case ERROR:
			case EXECUTION:
			case GET:
			case HANDLER:
			case INITIALIZATION:
			case ISSINGLETON:
			case PARENTS:
			case PERCFLOW:
			case PERCFLOWBELOW:
			case PERTARGET:
			case PERTHIS:
			case PERTYPEWITHIN:
			case POINTCUT:
			case PRECEDENCE:
			case PREINITIALIZATION:
			case PRIVILEGED:
			case RETURNING:
			case SET:
			case SOFT:
			case STATICINITIALIZATION:
			case TARGET:
			case THROWING:
			case WARNING:
			case WITHIN:
			case WITHINCODE:
			case ANNOTATION_METHOD:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
				{
				setState(2286);
				expression(0);
				setState(2287);
				match(RBRACK);
				setState(2294);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,253,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2288);
						match(LBRACK);
						setState(2289);
						expression(0);
						setState(2290);
						match(RBRACK);
						}
						} 
					}
					setState(2296);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,253,_ctx);
				}
				setState(2301);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,254,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2297);
						match(LBRACK);
						setState(2298);
						match(RBRACK);
						}
						} 
					}
					setState(2303);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,254,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassCreatorRestContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterClassCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitClassCreatorRest(this);
		}
	}

	public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
		ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_classCreatorRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2306);
			arguments();
			setState(2308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,256,_ctx) ) {
			case 1:
				{
				setState(2307);
				classBody();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterExplicitGenericInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitExplicitGenericInvocation(this);
		}
	}

	public final ExplicitGenericInvocationContext explicitGenericInvocation() throws RecognitionException {
		ExplicitGenericInvocationContext _localctx = new ExplicitGenericInvocationContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_explicitGenericInvocation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2310);
			nonWildcardTypeArguments();
			setState(2311);
			explicitGenericInvocationSuffix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonWildcardTypeArgumentsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(AspectJParser.LT, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode GT() { return getToken(AspectJParser.GT, 0); }
		public NonWildcardTypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterNonWildcardTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitNonWildcardTypeArguments(this);
		}
	}

	public final NonWildcardTypeArgumentsContext nonWildcardTypeArguments() throws RecognitionException {
		NonWildcardTypeArgumentsContext _localctx = new NonWildcardTypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_nonWildcardTypeArguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2313);
			match(LT);
			setState(2314);
			typeList();
			setState(2315);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(AspectJParser.LT, 0); }
		public TerminalNode GT() { return getToken(AspectJParser.GT, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public TypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitTypeArgumentsOrDiamond(this);
		}
	}

	public final TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() throws RecognitionException {
		TypeArgumentsOrDiamondContext _localctx = new TypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_typeArgumentsOrDiamond);
		try {
			setState(2320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,257,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2317);
				match(LT);
				setState(2318);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2319);
				typeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonWildcardTypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(AspectJParser.LT, 0); }
		public TerminalNode GT() { return getToken(AspectJParser.GT, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitNonWildcardTypeArgumentsOrDiamond(this);
		}
	}

	public final NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() throws RecognitionException {
		NonWildcardTypeArgumentsOrDiamondContext _localctx = new NonWildcardTypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 286, RULE_nonWildcardTypeArgumentsOrDiamond);
		try {
			setState(2325);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,258,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2322);
				match(LT);
				setState(2323);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2324);
				nonWildcardTypeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(AspectJParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AspectJParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AspectJParserListener ) ((AspectJParserListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 288, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2327);
			match(LPAREN);
			setState(2329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ANNOTATION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << ANNOTATION_METHOD) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				setState(2328);
				expressionList();
				}
			}

			setState(2331);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return pointcutExpression_sempred((PointcutExpressionContext)_localctx, predIndex);
		case 18:
			return typePattern_sempred((TypePatternContext)_localctx, predIndex);
		case 66:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean pointcutExpression_sempred(PointcutExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean typePattern_sempred(TypePatternContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		case 12:
			return precpred(_ctx, 4);
		case 13:
			return precpred(_ctx, 3);
		case 14:
			return precpred(_ctx, 2);
		case 15:
			return precpred(_ctx, 1);
		case 16:
			return precpred(_ctx, 25);
		case 17:
			return precpred(_ctx, 24);
		case 18:
			return precpred(_ctx, 23);
		case 19:
			return precpred(_ctx, 22);
		case 20:
			return precpred(_ctx, 21);
		case 21:
			return precpred(_ctx, 20);
		case 22:
			return precpred(_ctx, 19);
		case 23:
			return precpred(_ctx, 16);
		case 24:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00b3\u0920\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089"+
		"\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d\4\u008e"+
		"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092\t\u0092"+
		"\3\2\7\2\u0126\n\2\f\2\16\2\u0129\13\2\3\2\3\2\7\2\u012d\n\2\f\2\16\2"+
		"\u0130\13\2\3\2\3\2\7\2\u0134\n\2\f\2\16\2\u0137\13\2\3\2\3\2\7\2\u013b"+
		"\n\2\f\2\16\2\u013e\13\2\3\2\3\2\7\2\u0142\n\2\f\2\16\2\u0145\13\2\3\2"+
		"\3\2\5\2\u0149\n\2\3\3\3\3\7\3\u014d\n\3\f\3\16\3\u0150\13\3\3\3\3\3\3"+
		"\4\3\4\5\4\u0156\n\4\3\4\3\4\7\4\u015a\n\4\f\4\16\4\u015d\13\4\3\4\3\4"+
		"\3\4\5\4\u0162\n\4\3\5\3\5\3\5\3\5\5\5\u0168\n\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6\u0174\n\6\3\7\3\7\3\7\3\7\3\7\5\7\u017b\n\7\3\7"+
		"\5\7\u017e\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u01b8\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0205"+
		"\n\7\3\7\3\7\5\7\u0209\n\7\3\b\3\b\3\b\7\b\u020e\n\b\f\b\16\b\u0211\13"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\7\t\u0219\n\t\f\t\16\t\u021c\13\t\3\n\5\n\u021f"+
		"\n\n\3\n\7\n\u0222\n\n\f\n\16\n\u0225\13\n\3\n\3\n\3\n\5\n\u022a\n\n\3"+
		"\n\3\n\5\n\u022e\n\n\3\n\3\n\5\n\u0232\n\n\3\n\5\n\u0235\n\n\3\n\3\n\3"+
		"\13\7\13\u023a\n\13\f\13\16\13\u023d\13\13\3\13\5\13\u0240\n\13\3\13\3"+
		"\13\3\13\5\13\u0245\n\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u0254\n\f\3\f\5\f\u0257\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u025e"+
		"\n\f\3\f\5\f\u0261\n\f\3\f\3\f\5\f\u0265\n\f\3\f\3\f\5\f\u0269\n\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0287\n\r\3\16\3\16\7\16\u028b"+
		"\n\16\f\16\16\16\u028e\13\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0296"+
		"\n\16\f\16\16\16\u0299\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u02a2"+
		"\n\16\3\17\3\17\3\17\5\17\u02a7\n\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u02af\n\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u02b7\n\17\f\17\16\17\u02ba"+
		"\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u02fe\n\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0334\n\20\3\21"+
		"\3\21\3\21\5\21\u0339\n\21\3\21\3\21\3\21\3\22\7\22\u033f\n\22\f\22\16"+
		"\22\u0342\13\22\3\22\3\22\5\22\u0346\n\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u034e\n\22\3\22\3\22\3\22\7\22\u0353\n\22\f\22\16\22\u0356\13\22"+
		"\3\22\3\22\7\22\u035a\n\22\f\22\16\22\u035d\13\22\3\22\3\22\5\22\u0361"+
		"\n\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0369\n\22\3\22\3\22\3\22\7\22"+
		"\u036e\n\22\f\22\16\22\u0371\13\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22"+
		"\u0379\n\22\3\22\3\22\3\22\7\22\u037e\n\22\f\22\16\22\u0381\13\22\3\22"+
		"\3\22\5\22\u0385\n\22\3\22\3\22\3\22\3\22\3\22\5\22\u038c\n\22\3\22\3"+
		"\22\5\22\u0390\n\22\3\23\3\23\3\23\3\23\5\23\u0396\n\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u03a1\n\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u03ac\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\5\23\u03b7\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u03c2\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u03cd"+
		"\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u03f6"+
		"\n\23\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u03fe\n\24\3\24\3\24\3\24\5\24"+
		"\u0403\n\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u040b\n\24\f\24\16\24\u040e"+
		"\13\24\3\25\3\25\5\25\u0412\n\25\3\25\3\25\7\25\u0416\n\25\f\25\16\25"+
		"\u0419\13\25\3\26\3\26\3\26\3\26\3\26\6\26\u0420\n\26\r\26\16\26\u0421"+
		"\3\26\5\26\u0425\n\26\3\27\3\27\5\27\u0429\n\27\3\27\3\27\3\27\3\27\5"+
		"\27\u042f\n\27\3\27\5\27\u0432\n\27\3\30\5\30\u0435\n\30\3\30\5\30\u0438"+
		"\n\30\3\30\3\30\3\30\3\30\5\30\u043e\n\30\3\30\3\30\3\31\5\31\u0443\n"+
		"\31\3\31\3\31\7\31\u0447\n\31\f\31\16\31\u044a\13\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\34\7\34\u0453\n\34\f\34\16\34\u0456\13\34\3\34\5\34\u0459"+
		"\n\34\3\34\3\34\3\34\3\34\7\34\u045f\n\34\f\34\16\34\u0462\13\34\3\34"+
		"\5\34\u0465\n\34\5\34\u0467\n\34\3\35\3\35\5\35\u046b\n\35\3\36\5\36\u046e"+
		"\n\36\3\36\5\36\u0471\n\36\3\36\3\36\3\36\3\36\5\36\u0477\n\36\3\36\3"+
		"\36\3\36\5\36\u047c\n\36\3\37\5\37\u047f\n\37\3\37\3\37\7\37\u0483\n\37"+
		"\f\37\16\37\u0486\13\37\3 \3 \3!\3!\3!\7!\u048d\n!\f!\16!\u0490\13!\3"+
		"!\3!\3!\7!\u0495\n!\f!\16!\u0498\13!\3!\3!\3!\5!\u049d\n!\3\"\3\"\3\""+
		"\7\"\u04a2\n\"\f\"\16\"\u04a5\13\"\3\"\3\"\3\"\5\"\u04aa\n\"\3#\3#\3#"+
		"\3$\3$\3$\7$\u04b2\n$\f$\16$\u04b5\13$\3%\5%\u04b8\n%\3%\5%\u04bb\n%\3"+
		"%\3%\3%\5%\u04c0\n%\3%\3%\3%\5%\u04c5\n%\3&\5&\u04c8\n&\3&\3&\7&\u04cc"+
		"\n&\f&\16&\u04cf\13&\3\'\3\'\3(\5(\u04d4\n(\3(\3(\3(\7(\u04d9\n(\f(\16"+
		"(\u04dc\13(\3)\3)\3)\3)\3)\5)\u04e3\n)\3*\3*\5*\u04e7\n*\3*\3*\3+\3+\5"+
		"+\u04ed\n+\3,\3,\5,\u04f1\n,\3-\3-\3-\5-\u04f6\n-\3-\3-\3-\7-\u04fb\n"+
		"-\f-\16-\u04fe\13-\3-\3-\3-\7-\u0503\n-\f-\16-\u0506\13-\5-\u0508\n-\3"+
		".\3.\3.\7.\u050d\n.\f.\16.\u0510\13.\3.\3.\3.\7.\u0515\n.\f.\16.\u0518"+
		"\13.\5.\u051a\n.\3/\3/\5/\u051e\n/\3\60\3\60\3\60\7\60\u0523\n\60\f\60"+
		"\16\60\u0526\13\60\3\61\3\61\5\61\u052a\n\61\3\62\3\62\3\62\5\62\u052f"+
		"\n\62\3\62\3\62\5\62\u0533\n\62\3\62\3\62\5\62\u0537\n\62\3\62\3\62\3"+
		"\63\3\63\3\63\5\63\u053e\n\63\3\64\3\64\3\64\3\64\5\64\u0544\n\64\3\64"+
		"\3\64\5\64\u0548\n\64\3\64\5\64\u054b\n\64\3\64\5\64\u054e\n\64\3\64\3"+
		"\64\3\65\7\65\u0553\n\65\f\65\16\65\u0556\13\65\3\65\3\65\5\65\u055a\n"+
		"\65\3\65\5\65\u055d\n\65\3\66\3\66\3\66\5\66\u0562\n\66\3\66\3\66\5\66"+
		"\u0566\n\66\3\66\3\66\3\67\3\67\5\67\u056c\n\67\3\67\3\67\3\67\3\67\7"+
		"\67\u0572\n\67\f\67\16\67\u0575\13\67\3\67\3\67\5\67\u0579\n\67\3\67\3"+
		"\67\5\67\u057d\n\67\38\38\38\38\58\u0583\n8\38\38\39\39\39\79\u058a\n"+
		"9\f9\169\u058d\139\39\39\39\3:\3:\5:\u0594\n:\3:\3:\3:\3:\7:\u059a\n:"+
		"\f:\16:\u059d\13:\3:\3:\5:\u05a1\n:\3:\3:\3;\3;\3;\7;\u05a8\n;\f;\16;"+
		"\u05ab\13;\3<\3<\3=\3=\5=\u05b1\n=\3=\3=\3=\5=\u05b6\n=\7=\u05b8\n=\f"+
		"=\16=\u05bb\13=\3>\3>\3>\7>\u05c0\n>\f>\16>\u05c3\13>\3?\3?\3?\3?\3@\3"+
		"@\3@\3@\3@\3A\3A\3A\3A\5A\u05d2\nA\3B\3B\3B\3B\3B\5B\u05d9\nB\3B\3B\3"+
		"B\3B\3B\3B\3B\5B\u05e2\nB\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3"+
		"B\3B\3B\3B\3B\6B\u05f7\nB\rB\16B\u05f8\3B\5B\u05fc\nB\3B\5B\u05ff\nB\3"+
		"B\3B\3B\3B\7B\u0605\nB\fB\16B\u0608\13B\3B\5B\u060b\nB\3B\3B\3B\3B\7B"+
		"\u0611\nB\fB\16B\u0614\13B\3B\7B\u0617\nB\fB\16B\u061a\13B\3B\3B\3B\3"+
		"B\3B\3B\3B\3B\5B\u0624\nB\3B\3B\3B\3B\3B\3B\3B\5B\u062d\nB\3B\3B\3B\5"+
		"B\u0632\nB\3B\3B\3B\3B\3B\3B\3B\3B\3B\5B\u063d\nB\3C\3C\3C\7C\u0642\n"+
		"C\fC\16C\u0645\13C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D"+
		"\3D\5D\u0659\nD\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\5D\u0669\nD"+
		"\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D"+
		"\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\5D\u0694\nD\3D"+
		"\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\5D\u06a6\nD\3D\3D\3D\3D"+
		"\3D\3D\7D\u06ae\nD\fD\16D\u06b1\13D\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3"+
		"E\3E\3E\3E\3E\3E\3E\3E\5E\u06c6\nE\5E\u06c8\nE\3F\3F\5F\u06cc\nF\3F\3"+
		"F\3F\5F\u06d1\nF\7F\u06d3\nF\fF\16F\u06d6\13F\3F\5F\u06d9\nF\3G\3G\5G"+
		"\u06dd\nG\3G\3G\3H\3H\3H\3H\5H\u06e5\nH\5H\u06e7\nH\3I\3I\3I\3I\3I\5I"+
		"\u06ee\nI\3J\5J\u06f1\nJ\3J\7J\u06f4\nJ\fJ\16J\u06f7\13J\3J\7J\u06fa\n"+
		"J\fJ\16J\u06fd\13J\3J\3J\3K\7K\u0702\nK\fK\16K\u0705\13K\3K\3K\3K\3K\3"+
		"L\3L\5L\u070d\nL\3L\3L\3L\5L\u0712\nL\3L\3L\3M\3M\5M\u0718\nM\3N\3N\5"+
		"N\u071c\nN\3O\3O\5O\u0720\nO\3P\3P\3P\3P\7P\u0726\nP\fP\16P\u0729\13P"+
		"\3P\3P\3Q\3Q\3Q\7Q\u0730\nQ\fQ\16Q\u0733\13Q\3R\3R\3R\7R\u0738\nR\fR\16"+
		"R\u073b\13R\3S\3S\7S\u073f\nS\fS\16S\u0742\13S\3T\3T\3T\7T\u0747\nT\f"+
		"T\16T\u074a\13T\3U\3U\7U\u074e\nU\fU\16U\u0751\13U\3U\3U\3V\3V\7V\u0757"+
		"\nV\fV\16V\u075a\13V\3V\3V\3W\3W\3W\3X\3X\3X\3Y\3Y\3Y\3Y\3Z\7Z\u0769\n"+
		"Z\fZ\16Z\u076c\13Z\3Z\3Z\5Z\u0770\nZ\3[\3[\3[\3[\3[\3[\3[\5[\u0779\n["+
		"\3\\\3\\\3\\\3\\\7\\\u077f\n\\\f\\\16\\\u0782\13\\\3\\\3\\\3]\3]\3]\3"+
		"^\3^\3^\7^\u078c\n^\f^\16^\u078f\13^\3_\3_\3_\5_\u0794\n_\3`\3`\5`\u0798"+
		"\n`\3a\3a\3a\3a\7a\u079e\na\fa\16a\u07a1\13a\3a\5a\u07a4\na\5a\u07a6\n"+
		"a\3a\3a\3b\3b\3b\7b\u07ad\nb\fb\16b\u07b0\13b\3b\3b\3b\7b\u07b5\nb\fb"+
		"\16b\u07b8\13b\5b\u07ba\nb\3c\3c\3d\3d\3d\3d\7d\u07c2\nd\fd\16d\u07c5"+
		"\13d\3d\3d\3e\3e\3e\3e\5e\u07cd\ne\5e\u07cf\ne\3f\3f\3f\7f\u07d4\nf\f"+
		"f\16f\u07d7\13f\3g\3g\5g\u07db\ng\3g\3g\3h\3h\3h\7h\u07e2\nh\fh\16h\u07e5"+
		"\13h\3h\3h\5h\u07e9\nh\3h\5h\u07ec\nh\3i\7i\u07ef\ni\fi\16i\u07f2\13i"+
		"\3i\3i\3i\3j\7j\u07f8\nj\fj\16j\u07fb\13j\3j\3j\3j\3j\3k\3k\3l\3l\3m\3"+
		"m\3n\3n\3o\3o\3o\7o\u080c\no\fo\16o\u080f\13o\3p\3p\3p\5p\u0814\np\3q"+
		"\3q\3q\3q\7q\u081a\nq\fq\16q\u081d\13q\5q\u081f\nq\3q\5q\u0822\nq\3q\3"+
		"q\3r\3r\7r\u0828\nr\fr\16r\u082b\13r\3r\3r\3s\7s\u0830\ns\fs\16s\u0833"+
		"\13s\3s\3s\5s\u0837\ns\3t\3t\3t\3t\3t\3t\5t\u083f\nt\3t\3t\5t\u0843\n"+
		"t\3t\3t\5t\u0847\nt\3t\3t\5t\u084b\nt\5t\u084d\nt\3u\3u\5u\u0851\nu\3"+
		"v\3v\3w\3w\3w\3x\3x\7x\u085a\nx\fx\16x\u085d\13x\3x\3x\3y\3y\3y\5y\u0864"+
		"\ny\3z\3z\3z\3{\7{\u086a\n{\f{\16{\u086d\13{\3{\3{\3{\3|\3|\3|\7|\u0875"+
		"\n|\f|\16|\u0878\13|\3}\3}\3}\3~\3~\3~\5~\u0880\n~\3~\3~\3\177\3\177\3"+
		"\177\7\177\u0887\n\177\f\177\16\177\u088a\13\177\3\u0080\7\u0080\u088d"+
		"\n\u0080\f\u0080\16\u0080\u0890\13\u0080\3\u0080\3\u0080\3\u0080\3\u0080"+
		"\3\u0080\3\u0081\6\u0081\u0898\n\u0081\r\u0081\16\u0081\u0899\3\u0081"+
		"\6\u0081\u089d\n\u0081\r\u0081\16\u0081\u089e\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\5\u0082\u08ab"+
		"\n\u0082\3\u0083\3\u0083\5\u0083\u08af\n\u0083\3\u0083\3\u0083\5\u0083"+
		"\u08b3\n\u0083\3\u0083\3\u0083\5\u0083\u08b7\n\u0083\5\u0083\u08b9\n\u0083"+
		"\3\u0084\3\u0084\5\u0084\u08bd\n\u0084\3\u0085\7\u0085\u08c0\n\u0085\f"+
		"\u0085\16\u0085\u08c3\13\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085"+
		"\3\u0086\3\u0086\3\u0087\3\u0087\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088"+
		"\7\u0088\u08d3\n\u0088\f\u0088\16\u0088\u08d6\13\u0088\3\u0089\3\u0089"+
		"\3\u008a\3\u008a\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b"+
		"\5\u008b\u08e3\n\u008b\5\u008b\u08e5\n\u008b\3\u008c\3\u008c\3\u008c\3"+
		"\u008c\7\u008c\u08eb\n\u008c\f\u008c\16\u008c\u08ee\13\u008c\3\u008c\3"+
		"\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\7\u008c\u08f7\n\u008c\f"+
		"\u008c\16\u008c\u08fa\13\u008c\3\u008c\3\u008c\7\u008c\u08fe\n\u008c\f"+
		"\u008c\16\u008c\u0901\13\u008c\5\u008c\u0903\n\u008c\3\u008d\3\u008d\5"+
		"\u008d\u0907\n\u008d\3\u008e\3\u008e\3\u008e\3\u008f\3\u008f\3\u008f\3"+
		"\u008f\3\u0090\3\u0090\3\u0090\5\u0090\u0913\n\u0090\3\u0091\3\u0091\3"+
		"\u0091\5\u0091\u0918\n\u0091\3\u0092\3\u0092\5\u0092\u091c\n\u0092\3\u0092"+
		"\3\u0092\3\u0092\2\5\34&\u0086\u0093\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080"+
		"\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098"+
		"\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0"+
		"\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8"+
		"\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0"+
		"\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8"+
		"\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106\u0108\u010a\u010c\u010e\u0110"+
		"\u0112\u0114\u0116\u0118\u011a\u011c\u011e\u0120\u0122\2\25\6\2NN]_bb"+
		"jj\4\2\3\3}}\6\2NN]_bbff\3\2]_\4\2\3\3\u008f\u008f\4\2\6(::\3\2\u008b"+
		"\u008e\3\2\u0081\u0082\4\2\u008f\u0090\u0094\u0094\3\2\u008d\u008e\4\2"+
		"\177\u0080\u0086\u0087\4\2\u0085\u0085\u0088\u0088\4\2~~\u0095\u009f\3"+
		"\2\u008b\u008c\6\2ZZffjjmm\6\2==NN]_bc\n\2??AADDJJPPWWYYaa\4\2MMdd\3\2"+
		"ot\2\u0a14\2\u0148\3\2\2\2\4\u014a\3\2\2\2\6\u0161\3\2\2\2\b\u0167\3\2"+
		"\2\2\n\u0173\3\2\2\2\f\u0208\3\2\2\2\16\u020a\3\2\2\2\20\u0215\3\2\2\2"+
		"\22\u021e\3\2\2\2\24\u023b\3\2\2\2\26\u0268\3\2\2\2\30\u0286\3\2\2\2\32"+
		"\u02a1\3\2\2\2\34\u02ae\3\2\2\2\36\u0333\3\2\2\2 \u0338\3\2\2\2\"\u038f"+
		"\3\2\2\2$\u03f5\3\2\2\2&\u0402\3\2\2\2(\u040f\3\2\2\2*\u0424\3\2\2\2,"+
		"\u0431\3\2\2\2.\u0434\3\2\2\2\60\u0442\3\2\2\2\62\u044b\3\2\2\2\64\u044d"+
		"\3\2\2\2\66\u0466\3\2\2\28\u046a\3\2\2\2:\u046d\3\2\2\2<\u047e\3\2\2\2"+
		">\u0487\3\2\2\2@\u049c\3\2\2\2B\u04a9\3\2\2\2D\u04ab\3\2\2\2F\u04ae\3"+
		"\2\2\2H\u04b7\3\2\2\2J\u04c7\3\2\2\2L\u04d0\3\2\2\2N\u04d3\3\2\2\2P\u04e2"+
		"\3\2\2\2R\u04e4\3\2\2\2T\u04ec\3\2\2\2V\u04f0\3\2\2\2X\u0507\3\2\2\2Z"+
		"\u0519\3\2\2\2\\\u051d\3\2\2\2^\u051f\3\2\2\2`\u0529\3\2\2\2b\u052b\3"+
		"\2\2\2d\u053a\3\2\2\2f\u053f\3\2\2\2h\u0554\3\2\2\2j\u055e\3\2\2\2l\u056b"+
		"\3\2\2\2n\u057e\3\2\2\2p\u0586\3\2\2\2r\u0593\3\2\2\2t\u05a4\3\2\2\2v"+
		"\u05ac\3\2\2\2x\u05ae\3\2\2\2z\u05bc\3\2\2\2|\u05c4\3\2\2\2~\u05c8\3\2"+
		"\2\2\u0080\u05cd\3\2\2\2\u0082\u063c\3\2\2\2\u0084\u063e\3\2\2\2\u0086"+
		"\u0658\3\2\2\2\u0088\u06c7\3\2\2\2\u008a\u06d8\3\2\2\2\u008c\u06da\3\2"+
		"\2\2\u008e\u06e6\3\2\2\2\u0090\u06ed\3\2\2\2\u0092\u06f0\3\2\2\2\u0094"+
		"\u0703\3\2\2\2\u0096\u070a\3\2\2\2\u0098\u0717\3\2\2\2\u009a\u071b\3\2"+
		"\2\2\u009c\u071f\3\2\2\2\u009e\u0721\3\2\2\2\u00a0\u072c\3\2\2\2\u00a2"+
		"\u0734\3\2\2\2\u00a4\u073c\3\2\2\2\u00a6\u0743\3\2\2\2\u00a8\u074b\3\2"+
		"\2\2\u00aa\u0754\3\2\2\2\u00ac\u075d\3\2\2\2\u00ae\u0760\3\2\2\2\u00b0"+
		"\u0763\3\2\2\2\u00b2\u076f\3\2\2\2\u00b4\u0778\3\2\2\2\u00b6\u077a\3\2"+
		"\2\2\u00b8\u0785\3\2\2\2\u00ba\u0788\3\2\2\2\u00bc\u0790\3\2\2\2\u00be"+
		"\u0797\3\2\2\2\u00c0\u0799\3\2\2\2\u00c2\u07b9\3\2\2\2\u00c4\u07bb\3\2"+
		"\2\2\u00c6\u07bd\3\2\2\2\u00c8\u07ce\3\2\2\2\u00ca\u07d0\3\2\2\2\u00cc"+
		"\u07d8\3\2\2\2\u00ce\u07eb\3\2\2\2\u00d0\u07f0\3\2\2\2\u00d2\u07f9\3\2"+
		"\2\2\u00d4\u0800\3\2\2\2\u00d6\u0802\3\2\2\2\u00d8\u0804\3\2\2\2\u00da"+
		"\u0806\3\2\2\2\u00dc\u0808\3\2\2\2\u00de\u0813\3\2\2\2\u00e0\u0815\3\2"+
		"\2\2\u00e2\u0825\3\2\2\2\u00e4\u0836\3\2\2\2\u00e6\u084c\3\2\2\2\u00e8"+
		"\u0850\3\2\2\2\u00ea\u0852\3\2\2\2\u00ec\u0854\3\2\2\2\u00ee\u0857\3\2"+
		"\2\2\u00f0\u0863\3\2\2\2\u00f2\u0865\3\2\2\2\u00f4\u086b\3\2\2\2\u00f6"+
		"\u0871\3\2\2\2\u00f8\u0879\3\2\2\2\u00fa\u087c\3\2\2\2\u00fc\u0883\3\2"+
		"\2\2\u00fe\u088e\3\2\2\2\u0100\u0897\3\2\2\2\u0102\u08aa\3\2\2\2\u0104"+
		"\u08b8\3\2\2\2\u0106\u08bc\3\2\2\2\u0108\u08c1\3\2\2\2\u010a\u08c9\3\2"+
		"\2\2\u010c\u08cb\3\2\2\2\u010e\u08cf\3\2\2\2\u0110\u08d7\3\2\2\2\u0112"+
		"\u08d9\3\2\2\2\u0114\u08e4\3\2\2\2\u0116\u08e6\3\2\2\2\u0118\u0904\3\2"+
		"\2\2\u011a\u0908\3\2\2\2\u011c\u090b\3\2\2\2\u011e\u0912\3\2\2\2\u0120"+
		"\u0917\3\2\2\2\u0122\u0919\3\2\2\2\u0124\u0126\5\u009aN\2\u0125\u0124"+
		"\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u0149\5b\62\2\u012b\u012d\5\u009a"+
		"N\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e"+
		"\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0149\5f"+
		"\64\2\u0132\u0134\5\u009aN\2\u0133\u0132\3\2\2\2\u0134\u0137\3\2\2\2\u0135"+
		"\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0138\3\2\2\2\u0137\u0135\3\2"+
		"\2\2\u0138\u0149\5j\66\2\u0139\u013b\5\u009aN\2\u013a\u0139\3\2\2\2\u013b"+
		"\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013f\3\2"+
		"\2\2\u013e\u013c\3\2\2\2\u013f\u0149\5~@\2\u0140\u0142\5\u009aN\2\u0141"+
		"\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2"+
		"\2\2\u0144\u0146\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0149\5\22\n\2\u0147"+
		"\u0149\7{\2\2\u0148\u0127\3\2\2\2\u0148\u012e\3\2\2\2\u0148\u0135\3\2"+
		"\2\2\u0148\u013c\3\2\2\2\u0148\u0143\3\2\2\2\u0148\u0147\3\2\2\2\u0149"+
		"\3\3\2\2\2\u014a\u014e\7w\2\2\u014b\u014d\5\b\5\2\u014c\u014b\3\2\2\2"+
		"\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151"+
		"\3\2\2\2\u0150\u014e\3\2\2\2\u0151\u0152\7x\2\2\u0152\5\3\2\2\2\u0153"+
		"\u0162\7{\2\2\u0154\u0156\7b\2\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2"+
		"\2\u0156\u0157\3\2\2\2\u0157\u0162\5\u00eex\2\u0158\u015a\5\u0098M\2\u0159"+
		"\u0158\3\2\2\2\u015a\u015d\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2"+
		"\2\2\u015c\u015e\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u0162\5\n\6\2\u015f"+
		"\u0160\7b\2\2\u0160\u0162\5\22\n\2\u0161\u0153\3\2\2\2\u0161\u0155\3\2"+
		"\2\2\u0161\u015b\3\2\2\2\u0161\u015f\3\2\2\2\u0162\7\3\2\2\2\u0163\u0168"+
		"\5\6\4\2\u0164\u0168\5\24\13\2\u0165\u0168\5\"\22\2\u0166\u0168\5$\23"+
		"\2\u0167\u0163\3\2\2\2\u0167\u0164\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0166"+
		"\3\2\2\2\u0168\t\3\2\2\2\u0169\u0174\5l\67\2\u016a\u0174\5\u00acW\2\u016b"+
		"\u0174\5\u00b0Y\2\u016c\u0174\5n8\2\u016d\u0174\5\u00aeX\2\u016e\u0174"+
		"\5j\66\2\u016f\u0174\5~@\2\u0170\u0174\5b\62\2\u0171\u0174\5f\64\2\u0172"+
		"\u0174\5\32\16\2\u0173\u0169\3\2\2\2\u0173\u016a\3\2\2\2\u0173\u016b\3"+
		"\2\2\2\u0173\u016c\3\2\2\2\u0173\u016d\3\2\2\2\u0173\u016e\3\2\2\2\u0173"+
		"\u016f\3\2\2\2\u0173\u0170\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0172\3\2"+
		"\2\2\u0174\13\3\2\2\2\u0175\u0176\7<\2\2\u0176\u017d\5\u00dan\2\u0177"+
		"\u017a\7u\2\2\u0178\u017b\5\u00dco\2\u0179\u017b\5\u00dep\2\u017a\u0178"+
		"\3\2\2\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\3\2\2\2\u017c"+
		"\u017e\7v\2\2\u017d\u0177\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u0209\3\2"+
		"\2\2\u017f\u0180\7<\2\2\u0180\u0181\7)\2\2\u0181\u0182\7u\2\2\u0182\u0183"+
		"\7\4\2\2\u0183\u0184\5\34\17\2\u0184\u0185\7\4\2\2\u0185\u0186\7v\2\2"+
		"\u0186\u0209\3\2\2\2\u0187\u0188\7<\2\2\u0188\u0189\7*\2\2\u0189\u018a"+
		"\7u\2\2\u018a\u018b\7\4\2\2\u018b\u018c\5\34\17\2\u018c\u018d\7\4\2\2"+
		"\u018d\u018e\7v\2\2\u018e\u0209\3\2\2\2\u018f\u0190\7<\2\2\u0190\u0191"+
		"\7*\2\2\u0191\u0192\7u\2\2\u0192\u0193\7\34\2\2\u0193\u0194\7~\2\2\u0194"+
		"\u0195\7\4\2\2\u0195\u0196\5\34\17\2\u0196\u0197\7\4\2\2\u0197\u0198\7"+
		"|\2\2\u0198\u0199\7 \2\2\u0199\u019a\7~\2\2\u019a\u019b\7\4\2\2\u019b"+
		"\u019c\5`\61\2\u019c\u019d\7\4\2\2\u019d\u019e\7v\2\2\u019e\u0209\3\2"+
		"\2\2\u019f\u01a0\7<\2\2\u01a0\u01a1\7+\2\2\u01a1\u01a2\7u\2\2\u01a2\u01a3"+
		"\7\4\2\2\u01a3\u01a4\5\34\17\2\u01a4\u01a5\7\4\2\2\u01a5\u01a6\7v\2\2"+
		"\u01a6\u0209\3\2\2\2\u01a7\u01a8\7<\2\2\u01a8\u01a9\7,\2\2\u01a9\u01aa"+
		"\7u\2\2\u01aa\u01ab\7\4\2\2\u01ab\u01ac\5\34\17\2\u01ac\u01ad\7\4\2\2"+
		"\u01ad\u01ae\7v\2\2\u01ae\u0209\3\2\2\2\u01af\u01b0\7<\2\2\u01b0\u01b7"+
		"\7-\2\2\u01b1\u01b2\7u\2\2\u01b2\u01b3\7\4\2\2\u01b3\u01b4\5\30\r\2\u01b4"+
		"\u01b5\7\4\2\2\u01b5\u01b6\7v\2\2\u01b6\u01b8\3\2\2\2\u01b7\u01b1\3\2"+
		"\2\2\u01b7\u01b8\3\2\2\2\u01b8\u0209\3\2\2\2\u01b9\u01ba\7<\2\2\u01ba"+
		"\u01bb\7.\2\2\u01bb\u01bc\7u\2\2\u01bc\u01bd\7\4\2\2\u01bd\u01be\5\34"+
		"\17\2\u01be\u01bf\7\4\2\2\u01bf\u01c0\7v\2\2\u01c0\u0209\3\2\2\2\u01c1"+
		"\u01c2\7<\2\2\u01c2\u01c3\7\62\2\2\u01c3\u01c4\7u\2\2\u01c4\u01c5\7\4"+
		"\2\2\u01c5\u01c6\5\34\17\2\u01c6\u01c7\7\4\2\2\u01c7\u01c8\7v\2\2\u01c8"+
		"\u0209\3\2\2\2\u01c9\u01ca\7<\2\2\u01ca\u01cb\7\60\2\2\u01cb\u01cc\7u"+
		"\2\2\u01cc\u01cd\7;\2\2\u01cd\u01ce\7~\2\2\u01ce\u01cf\7\4\2\2\u01cf\u01d0"+
		"\5&\24\2\u01d0\u01d1\7\4\2\2\u01d1\u01d2\7|\2\2\u01d2\u01d3\78\2\2\u01d3"+
		"\u01d4\7~\2\2\u01d4\u01d5\7w\2\2\u01d5\u01d6\5\20\t\2\u01d6\u01d7\7x\2"+
		"\2\u01d7\u01d8\7v\2\2\u01d8\u0209\3\2\2\2\u01d9\u01da\7<\2\2\u01da\u01db"+
		"\7/\2\2\u01db\u01dc\7u\2\2\u01dc\u01dd\7\4\2\2\u01dd\u01de\5&\24\2\u01de"+
		"\u01df\7\4\2\2\u01df\u01e0\7v\2\2\u01e0\u0209\3\2\2\2\u01e1\u01e2\7<\2"+
		"\2\u01e2\u01e3\7/\2\2\u01e3\u01e4\7u\2\2\u01e4\u01e5\7;\2\2\u01e5\u01e6"+
		"\7~\2\2\u01e6\u01e7\7\4\2\2\u01e7\u01e8\5&\24\2\u01e8\u01e9\7\4\2\2\u01e9"+
		"\u01ea\7|\2\2\u01ea\u01eb\7\66\2\2\u01eb\u01ec\7~\2\2\u01ec\u01ed\5\16"+
		"\b\2\u01ed\u01ee\7v\2\2\u01ee\u0209\3\2\2\2\u01ef\u01f0\7<\2\2\u01f0\u01f1"+
		"\7\63\2\2\u01f1\u01f2\7u\2\2\u01f2\u01f3\7\4\2\2\u01f3\u01f4\5F$\2\u01f4"+
		"\u01f5\7\4\2\2\u01f5\u01f6\7v\2\2\u01f6\u0209\3\2\2\2\u01f7\u01f8\7<\2"+
		"\2\u01f8\u01f9\7\61\2\2\u01f9\u01fa\7u\2\2\u01fa\u01fb\7\4\2\2\u01fb\u01fc"+
		"\5\34\17\2\u01fc\u01fd\7\4\2\2\u01fd\u01fe\7v\2\2\u01fe\u0209\3\2\2\2"+
		"\u01ff\u0200\7<\2\2\u0200\u0201\7\64\2\2\u0201\u0202\7u\2\2\u0202\u0204"+
		"\7\4\2\2\u0203\u0205\5\34\17\2\u0204\u0203\3\2\2\2\u0204\u0205\3\2\2\2"+
		"\u0205\u0206\3\2\2\2\u0206\u0207\7\4\2\2\u0207\u0209\7v\2\2\u0208\u0175"+
		"\3\2\2\2\u0208\u017f\3\2\2\2\u0208\u0187\3\2\2\2\u0208\u018f\3\2\2\2\u0208"+
		"\u019f\3\2\2\2\u0208\u01a7\3\2\2\2\u0208\u01af\3\2\2\2\u0208\u01b9\3\2"+
		"\2\2\u0208\u01c1\3\2\2\2\u0208\u01c9\3\2\2\2\u0208\u01d9\3\2\2\2\u0208"+
		"\u01e1\3\2\2\2\u0208\u01ef\3\2\2\2\u0208\u01f7\3\2\2\2\u0208\u01ff\3\2"+
		"\2\2\u0209\r\3\2\2\2\u020a\u020f\5`\61\2\u020b\u020c\7}\2\2\u020c\u020e"+
		"\5`\61\2\u020d\u020b\3\2\2\2\u020e\u0211\3\2\2\2\u020f\u020d\3\2\2\2\u020f"+
		"\u0210\3\2\2\2\u0210\u0212\3\2\2\2\u0211\u020f\3\2\2\2\u0212\u0213\7}"+
		"\2\2\u0213\u0214\7E\2\2\u0214\17\3\2\2\2\u0215\u021a\5\16\b\2\u0216\u0217"+
		"\7|\2\2\u0217\u0219\5\16\b\2\u0218\u0216\3\2\2\2\u0219\u021c\3\2\2\2\u021a"+
		"\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b\21\3\2\2\2\u021c\u021a\3\2\2"+
		"\2\u021d\u021f\7\37\2\2\u021e\u021d\3\2\2\2\u021e\u021f\3\2\2\2\u021f"+
		"\u0223\3\2\2\2\u0220\u0222\5\u0098M\2\u0221\u0220\3\2\2\2\u0222\u0225"+
		"\3\2\2\2\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0226\3\2\2\2\u0225"+
		"\u0223\3\2\2\2\u0226\u0227\7\n\2\2\u0227\u0229\5`\61\2\u0228\u022a\5\u009e"+
		"P\2\u0229\u0228\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022d\3\2\2\2\u022b"+
		"\u022c\7M\2\2\u022c\u022e\5\u00c2b\2\u022d\u022b\3\2\2\2\u022d\u022e\3"+
		"\2\2\2\u022e\u0231\3\2\2\2\u022f\u0230\7T\2\2\u0230\u0232\5\u00a6T\2\u0231"+
		"\u022f\3\2\2\2\u0231\u0232\3\2\2\2\u0232\u0234\3\2\2\2\u0233\u0235\5\30"+
		"\r\2\u0234\u0233\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0236\3\2\2\2\u0236"+
		"\u0237\5\4\3\2\u0237\23\3\2\2\2\u0238\u023a\5\f\7\2\u0239\u0238\3\2\2"+
		"\2\u023a\u023d\3\2\2\2\u023b\u0239\3\2\2\2\u023b\u023c\3\2\2\2\u023c\u023f"+
		"\3\2\2\2\u023d\u023b\3\2\2\2\u023e\u0240\7c\2\2\u023f\u023e\3\2\2\2\u023f"+
		"\u0240\3\2\2\2\u0240\u0241\3\2\2\2\u0241\u0244\5\26\f\2\u0242\u0243\7"+
		"i\2\2\u0243\u0245\5\u00a6T\2\u0244\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245"+
		"\u0246\3\2\2\2\u0246\u0247\7\u0084\2\2\u0247\u0248\5\34\17\2\u0248\u0249"+
		"\5\u00d4k\2\u0249\25\3\2\2\2\u024a\u024b\7\13\2\2\u024b\u0269\5\u00cc"+
		"g\2\u024c\u024d\7\b\2\2\u024d\u0269\5\u00ccg\2\u024e\u024f\7\b\2\2\u024f"+
		"\u0250\5\u00ccg\2\u0250\u0256\7 \2\2\u0251\u0253\7u\2\2\u0252\u0254\5"+
		"\u00d0i\2\u0253\u0252\3\2\2\2\u0253\u0254\3\2\2\2\u0254\u0255\3\2\2\2"+
		"\u0255\u0257\7v\2\2\u0256\u0251\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0269"+
		"\3\2\2\2\u0258\u0259\7\b\2\2\u0259\u025a\5\u00ccg\2\u025a\u0260\7%\2\2"+
		"\u025b\u025d\7u\2\2\u025c\u025e\5\u00d0i\2\u025d\u025c\3\2\2\2\u025d\u025e"+
		"\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0261\7v\2\2\u0260\u025b\3\2\2\2\u0260"+
		"\u0261\3\2\2\2\u0261\u0269\3\2\2\2\u0262\u0265\5\u00c2b\2\u0263\u0265"+
		"\7l\2\2\u0264\u0262\3\2\2\2\u0264\u0263\3\2\2\2\u0265\u0266\3\2\2\2\u0266"+
		"\u0267\7\t\2\2\u0267\u0269\5\u00ccg\2\u0268\u024a\3\2\2\2\u0268\u024c"+
		"\3\2\2\2\u0268\u024e\3\2\2\2\u0268\u0258\3\2\2\2\u0268\u0264\3\2\2\2\u0269"+
		"\27\3\2\2\2\u026a\u026b\7\31\2\2\u026b\u026c\7u\2\2\u026c\u026d\5\34\17"+
		"\2\u026d\u026e\7v\2\2\u026e\u0287\3\2\2\2\u026f\u0270\7\32\2\2\u0270\u0271"+
		"\7u\2\2\u0271\u0272\5\34\17\2\u0272\u0273\7v\2\2\u0273\u0287\3\2\2\2\u0274"+
		"\u0275\7\27\2\2\u0275\u0276\7u\2\2\u0276\u0277\5\34\17\2\u0277\u0278\7"+
		"v\2\2\u0278\u0287\3\2\2\2\u0279\u027a\7\30\2\2\u027a\u027b\7u\2\2\u027b"+
		"\u027c\5\34\17\2\u027c\u027d\7v\2\2\u027d\u0287\3\2\2\2\u027e\u027f\7"+
		"\33\2\2\u027f\u0280\7u\2\2\u0280\u0281\5&\24\2\u0281\u0282\7v\2\2\u0282"+
		"\u0287\3\2\2\2\u0283\u0284\7\25\2\2\u0284\u0285\7u\2\2\u0285\u0287\7v"+
		"\2\2\u0286\u026a\3\2\2\2\u0286\u026f\3\2\2\2\u0286\u0274\3\2\2\2\u0286"+
		"\u0279\3\2\2\2\u0286\u027e\3\2\2\2\u0286\u0283\3\2\2\2\u0287\31\3\2\2"+
		"\2\u0288\u028c\7=\2\2\u0289\u028b\5\u0098M\2\u028a\u0289\3\2\2\2\u028b"+
		"\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d\u028f\3\2"+
		"\2\2\u028e\u028c\3\2\2\2\u028f\u0290\7\34\2\2\u0290\u0291\5`\61\2\u0291"+
		"\u0292\5\u00ccg\2\u0292\u0293\7{\2\2\u0293\u02a2\3\2\2\2\u0294\u0296\5"+
		"\u0098M\2\u0295\u0294\3\2\2\2\u0296\u0299\3\2\2\2\u0297\u0295\3\2\2\2"+
		"\u0297\u0298\3\2\2\2\u0298\u029a\3\2\2\2\u0299\u0297\3\2\2\2\u029a\u029b"+
		"\7\34\2\2\u029b\u029c\5`\61\2\u029c\u029d\5\u00ccg\2\u029d\u029e\7\u0084"+
		"\2\2\u029e\u029f\5\34\17\2\u029f\u02a0\7{\2\2\u02a0\u02a2\3\2\2\2\u02a1"+
		"\u0288\3\2\2\2\u02a1\u0297\3\2\2\2\u02a2\33\3\2\2\2\u02a3\u02a6\b\17\1"+
		"\2\u02a4\u02a7\5\36\20\2\u02a5\u02a7\5 \21\2\u02a6\u02a4\3\2\2\2\u02a6"+
		"\u02a5\3\2\2\2\u02a7\u02af\3\2\2\2\u02a8\u02a9\7\u0081\2\2\u02a9\u02af"+
		"\5\34\17\6\u02aa\u02ab\7u\2\2\u02ab\u02ac\5\34\17\2\u02ac\u02ad\7v\2\2"+
		"\u02ad\u02af\3\2\2\2\u02ae\u02a3\3\2\2\2\u02ae\u02a8\3\2\2\2\u02ae\u02aa"+
		"\3\2\2\2\u02af\u02b8\3\2\2\2\u02b0\u02b1\f\4\2\2\u02b1\u02b2\7\u0089\2"+
		"\2\u02b2\u02b7\5\34\17\5\u02b3\u02b4\f\3\2\2\u02b4\u02b5\7\u008a\2\2\u02b5"+
		"\u02b7\5\34\17\4\u02b6\u02b0\3\2\2\2\u02b6\u02b3\3\2\2\2\u02b7\u02ba\3"+
		"\2\2\2\u02b8\u02b6\3\2\2\2\u02b8\u02b9\3\2\2\2\u02b9\35\3\2\2\2\u02ba"+
		"\u02b8\3\2\2\2\u02bb\u02bc\7\f\2\2\u02bc\u02bd\7u\2\2\u02bd\u02be\58\35"+
		"\2\u02be\u02bf\7v\2\2\u02bf\u0334\3\2\2\2\u02c0\u02c1\7\21\2\2\u02c1\u02c2"+
		"\7u\2\2\u02c2\u02c3\58\35\2\u02c3\u02c4\7v\2\2\u02c4\u0334\3\2\2\2\u02c5"+
		"\u02c6\7\24\2\2\u02c6\u02c7\7u\2\2\u02c7\u02c8\5H%\2\u02c8\u02c9\7v\2"+
		"\2\u02c9\u0334\3\2\2\2\u02ca\u02cb\7\36\2\2\u02cb\u02cc\7u\2\2\u02cc\u02cd"+
		"\5H%\2\u02cd\u02ce\7v\2\2\u02ce\u0334\3\2\2\2\u02cf\u02d0\7#\2\2\u02d0"+
		"\u02d1\7u\2\2\u02d1\u02d2\5,\27\2\u02d2\u02d3\7v\2\2\u02d3\u0334\3\2\2"+
		"\2\u02d4\u02d5\7\22\2\2\u02d5\u02d6\7u\2\2\u02d6\u02d7\5.\30\2\u02d7\u02d8"+
		"\7v\2\2\u02d8\u0334\3\2\2\2\u02d9\u02da\7!\2\2\u02da\u02db\7u\2\2\u02db"+
		"\u02dc\5.\30\2\u02dc\u02dd\7v\2\2\u02dd\u0334\3\2\2\2\u02de\u02df\7\23"+
		"\2\2\u02df\u02e0\7u\2\2\u02e0\u02e1\5,\27\2\u02e1\u02e2\7v\2\2\u02e2\u0334"+
		"\3\2\2\2\u02e3\u02e4\7\5\2\2\u02e4\u02e5\7u\2\2\u02e5\u0334\7v\2\2\u02e6"+
		"\u02e7\7\'\2\2\u02e7\u02e8\7u\2\2\u02e8\u02e9\5,\27\2\u02e9\u02ea\7v\2"+
		"\2\u02ea\u0334\3\2\2\2\u02eb\u02ec\7(\2\2\u02ec\u02ed\7u\2\2\u02ed\u02ee"+
		"\58\35\2\u02ee\u02ef\7v\2\2\u02ef\u0334\3\2\2\2\u02f0\u02f1\7\r\2\2\u02f1"+
		"\u02f2\7u\2\2\u02f2\u02f3\5\34\17\2\u02f3\u02f4\7v\2\2\u02f4\u0334\3\2"+
		"\2\2\u02f5\u02f6\7\16\2\2\u02f6\u02f7\7u\2\2\u02f7\u02f8\5\34\17\2\u02f8"+
		"\u02f9\7v\2\2\u02f9\u0334\3\2\2\2\u02fa\u02fb\7R\2\2\u02fb\u02fd\7u\2"+
		"\2\u02fc\u02fe\5\u0086D\2\u02fd\u02fc\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe"+
		"\u02ff\3\2\2\2\u02ff\u0334\7v\2\2\u0300\u0301\7g\2\2\u0301\u0302\7u\2"+
		"\2\u0302\u0303\5T+\2\u0303\u0304\7v\2\2\u0304\u0334\3\2\2\2\u0305\u0306"+
		"\7$\2\2\u0306\u0307\7u\2\2\u0307\u0308\5T+\2\u0308\u0309\7v\2\2\u0309"+
		"\u0334\3\2\2\2\u030a\u030b\7\7\2\2\u030b\u030c\7u\2\2\u030c\u030d\5^\60"+
		"\2\u030d\u030e\7v\2\2\u030e\u0334\3\2\2\2\u030f\u0310\7<\2\2\u0310\u0311"+
		"\7g\2\2\u0311\u0312\7u\2\2\u0312\u0313\5V,\2\u0313\u0314\7v\2\2\u0314"+
		"\u0334\3\2\2\2\u0315\u0316\7<\2\2\u0316\u0317\7$\2\2\u0317\u0318\7u\2"+
		"\2\u0318\u0319\5V,\2\u0319\u031a\7v\2\2\u031a\u0334\3\2\2\2\u031b\u031c"+
		"\7<\2\2\u031c\u031d\7\7\2\2\u031d\u031e\7u\2\2\u031e\u031f\5X-\2\u031f"+
		"\u0320\7v\2\2\u0320\u0334\3\2\2\2\u0321\u0322\7<\2\2\u0322\u0323\7\'\2"+
		"\2\u0323\u0324\7u\2\2\u0324\u0325\5V,\2\u0325\u0326\7v\2\2\u0326\u0334"+
		"\3\2\2\2\u0327\u0328\7<\2\2\u0328\u0329\7(\2\2\u0329\u032a\7u\2\2\u032a"+
		"\u032b\5V,\2\u032b\u032c\7v\2\2\u032c\u0334\3\2\2\2\u032d\u032e\7<\2\2"+
		"\u032e\u032f\7\6\2\2\u032f\u0330\7u\2\2\u0330\u0331\5V,\2\u0331\u0332"+
		"\7v\2\2\u0332\u0334\3\2\2\2\u0333\u02bb\3\2\2\2\u0333\u02c0\3\2\2\2\u0333"+
		"\u02c5\3\2\2\2\u0333\u02ca\3\2\2\2\u0333\u02cf\3\2\2\2\u0333\u02d4\3\2"+
		"\2\2\u0333\u02d9\3\2\2\2\u0333\u02de\3\2\2\2\u0333\u02e3\3\2\2\2\u0333"+
		"\u02e6\3\2\2\2\u0333\u02eb\3\2\2\2\u0333\u02f0\3\2\2\2\u0333\u02f5\3\2"+
		"\2\2\u0333\u02fa\3\2\2\2\u0333\u0300\3\2\2\2\u0333\u0305\3\2\2\2\u0333"+
		"\u030a\3\2\2\2\u0333\u030f\3\2\2\2\u0333\u0315\3\2\2\2\u0333\u031b\3\2"+
		"\2\2\u0333\u0321\3\2\2\2\u0333\u0327\3\2\2\2\u0333\u032d\3\2\2\2\u0334"+
		"\37\3\2\2\2\u0335\u0336\5&\24\2\u0336\u0337\7}\2\2\u0337\u0339\3\2\2\2"+
		"\u0338\u0335\3\2\2\2\u0338\u0339\3\2\2\2\u0339\u033a\3\2\2\2\u033a\u033b"+
		"\5`\61\2\u033b\u033c\5R*\2\u033c!\3\2\2\2\u033d\u033f\5\u0098M\2\u033e"+
		"\u033d\3\2\2\2\u033f\u0342\3\2\2\2\u0340\u033e\3\2\2\2\u0340\u0341\3\2"+
		"\2\2\u0341\u0345\3\2\2\2\u0342\u0340\3\2\2\2\u0343\u0346\5\u00c2b\2\u0344"+
		"\u0346\7l\2\2\u0345\u0343\3\2\2\2\u0345\u0344\3\2\2\2\u0346\u0347\3\2"+
		"\2\2\u0347\u0348\5\u00c2b\2\u0348\u0349\7}\2\2\u0349\u034a\5`\61\2\u034a"+
		"\u034d\5\u00ccg\2\u034b\u034c\7i\2\2\u034c\u034e\5\u00a6T\2\u034d\u034b"+
		"\3\2\2\2\u034d\u034e\3\2\2\2\u034e\u034f\3\2\2\2\u034f\u0350\5\u00d4k"+
		"\2\u0350\u0390\3\2\2\2\u0351\u0353\5\u0098M\2\u0352\u0351\3\2\2\2\u0353"+
		"\u0356\3\2\2\2\u0354\u0352\3\2\2\2\u0354\u0355\3\2\2\2\u0355\u0357\3\2"+
		"\2\2\u0356\u0354\3\2\2\2\u0357\u035b\7=\2\2\u0358\u035a\5\u0098M\2\u0359"+
		"\u0358\3\2\2\2\u035a\u035d\3\2\2\2\u035b\u0359\3\2\2\2\u035b\u035c\3\2"+
		"\2\2\u035c\u0360\3\2\2\2\u035d\u035b\3\2\2\2\u035e\u0361\5\u00c2b\2\u035f"+
		"\u0361\7l\2\2\u0360\u035e\3\2\2\2\u0360\u035f\3\2\2\2\u0361\u0362\3\2"+
		"\2\2\u0362\u0363\5\u00c2b\2\u0363\u0364\7}\2\2\u0364\u0365\5`\61\2\u0365"+
		"\u0368\5\u00ccg\2\u0366\u0367\7i\2\2\u0367\u0369\5\u00a6T\2\u0368\u0366"+
		"\3\2\2\2\u0368\u0369\3\2\2\2\u0369\u036a\3\2\2\2\u036a\u036b\7{\2\2\u036b"+
		"\u0390\3\2\2\2\u036c\u036e\5\u0098M\2\u036d\u036c\3\2\2\2\u036e\u0371"+
		"\3\2\2\2\u036f\u036d\3\2\2\2\u036f\u0370\3\2\2\2\u0370\u0372\3\2\2\2\u0371"+
		"\u036f\3\2\2\2\u0372\u0373\5\u00c2b\2\u0373\u0374\7}\2\2\u0374\u0375\7"+
		"[\2\2\u0375\u0378\5\u00ccg\2\u0376\u0377\7i\2\2\u0377\u0379\5\u00a6T\2"+
		"\u0378\u0376\3\2\2\2\u0378\u0379\3\2\2\2\u0379\u037a\3\2\2\2\u037a\u037b"+
		"\5\u00d4k\2\u037b\u0390\3\2\2\2\u037c\u037e\5\u0098M\2\u037d\u037c\3\2"+
		"\2\2\u037e\u0381\3\2\2\2\u037f\u037d\3\2\2\2\u037f\u0380\3\2\2\2\u0380"+
		"\u0384\3\2\2\2\u0381\u037f\3\2\2\2\u0382\u0385\5\u00c2b\2\u0383\u0385"+
		"\7l\2\2\u0384\u0382\3\2\2\2\u0384\u0383\3\2\2\2\u0385\u0386\3\2\2\2\u0386"+
		"\u0387\5\u00c2b\2\u0387\u0388\7}\2\2\u0388\u038b\5`\61\2\u0389\u038a\7"+
		"~\2\2\u038a\u038c\5\u0086D\2\u038b\u0389\3\2\2\2\u038b\u038c\3\2\2\2\u038c"+
		"\u038d\3\2\2\2\u038d\u038e\7{\2\2\u038e\u0390\3\2\2\2\u038f\u0340\3\2"+
		"\2\2\u038f\u0354\3\2\2\2\u038f\u036f\3\2\2\2\u038f\u037f\3\2\2\2\u0390"+
		"#\3\2\2\2\u0391\u0392\7\17\2\2\u0392\u0393\7\26\2\2\u0393\u0395\7\u0084"+
		"\2\2\u0394\u0396\5\f\7\2\u0395\u0394\3\2\2\2\u0395\u0396\3\2\2\2\u0396"+
		"\u0397\3\2\2\2\u0397\u0398\5&\24\2\u0398\u0399\7M\2\2\u0399\u039a\5\u00c2"+
		"b\2\u039a\u039b\7{\2\2\u039b\u03f6\3\2\2\2\u039c\u039d\7\17\2\2\u039d"+
		"\u039e\7\26\2\2\u039e\u03a0\7\u0084\2\2\u039f\u03a1\5\f\7\2\u03a0\u039f"+
		"\3\2\2\2\u03a0\u03a1\3\2\2\2\u03a1\u03a2\3\2\2\2\u03a2\u03a3\5&\24\2\u03a3"+
		"\u03a4\7T\2\2\u03a4\u03a5\5\u00a6T\2\u03a5\u03a6\7{\2\2\u03a6\u03f6\3"+
		"\2\2\2\u03a7\u03a8\7\17\2\2\u03a8\u03a9\7&\2\2\u03a9\u03ab\7\u0084\2\2"+
		"\u03aa\u03ac\5\f\7\2\u03ab\u03aa\3\2\2\2\u03ab\u03ac\3\2\2\2\u03ac\u03ad"+
		"\3\2\2\2\u03ad\u03ae\5\34\17\2\u03ae\u03af\7\u0084\2\2\u03af\u03b0\7s"+
		"\2\2\u03b0\u03b1\7{\2\2\u03b1\u03f6\3\2\2\2\u03b2\u03b3\7\17\2\2\u03b3"+
		"\u03b4\7\20\2\2\u03b4\u03b6\7\u0084\2\2\u03b5\u03b7\5\f\7\2\u03b6\u03b5"+
		"\3\2\2\2\u03b6\u03b7\3\2\2\2\u03b7\u03b8\3\2\2\2\u03b8\u03b9\5\34\17\2"+
		"\u03b9\u03ba\7\u0084\2\2\u03ba\u03bb\7s\2\2\u03bb\u03bc\7{\2\2\u03bc\u03f6"+
		"\3\2\2\2\u03bd\u03be\7\17\2\2\u03be\u03bf\7\"\2\2\u03bf\u03c1\7\u0084"+
		"\2\2\u03c0\u03c2\5\f\7\2\u03c1\u03c0\3\2\2\2\u03c1\u03c2\3\2\2\2\u03c2"+
		"\u03c3\3\2\2\2\u03c3\u03c4\5\u00c2b\2\u03c4\u03c5\7\u0084\2\2\u03c5\u03c6"+
		"\5\34\17\2\u03c6\u03c7\7{\2\2\u03c7\u03f6\3\2\2\2\u03c8\u03c9\7\17\2\2"+
		"\u03c9\u03ca\7\35\2\2\u03ca\u03cc\7\u0084\2\2\u03cb\u03cd\5\f\7\2\u03cc"+
		"\u03cb\3\2\2\2\u03cc\u03cd\3\2\2\2\u03cd\u03ce\3\2\2\2\u03ce\u03cf\5F"+
		"$\2\u03cf\u03d0\7{\2\2\u03d0\u03f6\3\2\2\2\u03d1\u03d2\7\17\2\2\u03d2"+
		"\u03d3\7<\2\2\u03d3\u03d4\79\2\2\u03d4\u03d5\7\u0084\2\2\u03d5\u03d6\5"+
		"&\24\2\u03d6\u03d7\7\u0084\2\2\u03d7\u03d8\5\f\7\2\u03d8\u03d9\7{\2\2"+
		"\u03d9\u03f6\3\2\2\2\u03da\u03db\7\17\2\2\u03db\u03dc\7<\2\2\u03dc\u03dd"+
		"\7:\2\2\u03dd\u03de\7\u0084\2\2\u03de\u03df\5:\36\2\u03df\u03e0\7\u0084"+
		"\2\2\u03e0\u03e1\5\f\7\2\u03e1\u03e2\7{\2\2\u03e2\u03f6\3\2\2\2\u03e3"+
		"\u03e4\7\17\2\2\u03e4\u03e5\7<\2\2\u03e5\u03e6\7\65\2\2\u03e6\u03e7\7"+
		"\u0084\2\2\u03e7\u03e8\5H%\2\u03e8\u03e9\7\u0084\2\2\u03e9\u03ea\5\f\7"+
		"\2\u03ea\u03eb\7{\2\2\u03eb\u03f6\3\2\2\2\u03ec\u03ed\7\17\2\2\u03ed\u03ee"+
		"\7<\2\2\u03ee\u03ef\7\67\2\2\u03ef\u03f0\7\u0084\2\2\u03f0\u03f1\5.\30"+
		"\2\u03f1\u03f2\7\u0084\2\2\u03f2\u03f3\5\f\7\2\u03f3\u03f4\7{\2\2\u03f4"+
		"\u03f6\3\2\2\2\u03f5\u0391\3\2\2\2\u03f5\u039c\3\2\2\2\u03f5\u03a7\3\2"+
		"\2\2\u03f5\u03b2\3\2\2\2\u03f5\u03bd\3\2\2\2\u03f5\u03c8\3\2\2\2\u03f5"+
		"\u03d1\3\2\2\2\u03f5\u03da\3\2\2\2\u03f5\u03e3\3\2\2\2\u03f5\u03ec\3\2"+
		"\2\2\u03f6%\3\2\2\2\u03f7\u03f8\b\24\1\2\u03f8\u0403\5(\25\2\u03f9\u03fa"+
		"\7\u0081\2\2\u03fa\u0403\5&\24\6\u03fb\u03fd\7u\2\2\u03fc\u03fe\5N(\2"+
		"\u03fd\u03fc\3\2\2\2\u03fd\u03fe\3\2\2\2\u03fe\u03ff\3\2\2\2\u03ff\u0400"+
		"\5&\24\2\u0400\u0401\7v\2\2\u0401\u0403\3\2\2\2\u0402\u03f7\3\2\2\2\u0402"+
		"\u03f9\3\2\2\2\u0402\u03fb\3\2\2\2\u0403\u040c\3\2\2\2\u0404\u0405\f\4"+
		"\2\2\u0405\u0406\7\u0089\2\2\u0406\u040b\5&\24\5\u0407\u0408\f\3\2\2\u0408"+
		"\u0409\7\u008a\2\2\u0409\u040b\5&\24\4\u040a\u0404\3\2\2\2\u040a\u0407"+
		"\3\2\2\2\u040b\u040e\3\2\2\2\u040c\u040a\3\2\2\2\u040c\u040d\3\2\2\2\u040d"+
		"\'\3\2\2\2\u040e\u040c\3\2\2\2\u040f\u0411\5*\26\2\u0410\u0412\7\u008d"+
		"\2\2\u0411\u0410\3\2\2\2\u0411\u0412\3\2\2\2\u0412\u0417\3\2\2\2\u0413"+
		"\u0414\7y\2\2\u0414\u0416\7z\2\2\u0415\u0413\3\2\2\2\u0416\u0419\3\2\2"+
		"\2\u0417\u0415\3\2\2\2\u0417\u0418\3\2\2\2\u0418)\3\2\2\2\u0419\u0417"+
		"\3\2\2\2\u041a\u0420\5\u00c2b\2\u041b\u0420\5`\61\2\u041c\u0420\7\u008f"+
		"\2\2\u041d\u0420\7}\2\2\u041e\u0420\7\3\2\2\u041f\u041a\3\2\2\2\u041f"+
		"\u041b\3\2\2\2\u041f\u041c\3\2\2\2\u041f\u041d\3\2\2\2\u041f\u041e\3\2"+
		"\2\2\u0420\u0421\3\2\2\2\u0421\u041f\3\2\2\2\u0421\u0422\3\2\2\2\u0422"+
		"\u0425\3\2\2\2\u0423\u0425\7l\2\2\u0424\u041f\3\2\2\2\u0424\u0423\3\2"+
		"\2\2\u0425+\3\2\2\2\u0426\u0428\7u\2\2\u0427\u0429\5N(\2\u0428\u0427\3"+
		"\2\2\2\u0428\u0429\3\2\2\2\u0429\u042a\3\2\2\2\u042a\u042b\5&\24\2\u042b"+
		"\u042c\7v\2\2\u042c\u0432\3\2\2\2\u042d\u042f\5N(\2\u042e\u042d\3\2\2"+
		"\2\u042e\u042f\3\2\2\2\u042f\u0430\3\2\2\2\u0430\u0432\5&\24\2\u0431\u0426"+
		"\3\2\2\2\u0431\u042e\3\2\2\2\u0432-\3\2\2\2\u0433\u0435\5N(\2\u0434\u0433"+
		"\3\2\2\2\u0434\u0435\3\2\2\2\u0435\u0437\3\2\2\2\u0436\u0438\5\60\31\2"+
		"\u0437\u0436\3\2\2\2\u0437\u0438\3\2\2\2\u0438\u0439\3\2\2\2\u0439\u043d"+
		"\5&\24\2\u043a\u043b\5&\24\2\u043b\u043c\5\64\33\2\u043c\u043e\3\2\2\2"+
		"\u043d\u043a\3\2\2\2\u043d\u043e\3\2\2\2\u043e\u043f\3\2\2\2\u043f\u0440"+
		"\5\66\34\2\u0440/\3\2\2\2\u0441\u0443\7\u0081\2\2\u0442\u0441\3\2\2\2"+
		"\u0442\u0443\3\2\2\2\u0443\u0444\3\2\2\2\u0444\u0448\5\62\32\2\u0445\u0447"+
		"\5\60\31\2\u0446\u0445\3\2\2\2\u0447\u044a\3\2\2\2\u0448\u0446\3\2\2\2"+
		"\u0448\u0449\3\2\2\2\u0449\61\3\2\2\2\u044a\u0448\3\2\2\2\u044b\u044c"+
		"\t\2\2\2\u044c\63\3\2\2\2\u044d\u044e\t\3\2\2\u044e\65\3\2\2\2\u044f\u0454"+
		"\5`\61\2\u0450\u0451\7\u008f\2\2\u0451\u0453\5`\61\2\u0452\u0450\3\2\2"+
		"\2\u0453\u0456\3\2\2\2\u0454\u0452\3\2\2\2\u0454\u0455\3\2\2\2\u0455\u0458"+
		"\3\2\2\2\u0456\u0454\3\2\2\2\u0457\u0459\7\u008f\2\2\u0458\u0457\3\2\2"+
		"\2\u0458\u0459\3\2\2\2\u0459\u0467\3\2\2\2\u045a\u0460\7\u008f\2\2\u045b"+
		"\u045c\5`\61\2\u045c\u045d\7\u008f\2\2\u045d\u045f\3\2\2\2\u045e\u045b"+
		"\3\2\2\2\u045f\u0462\3\2\2\2\u0460\u045e\3\2\2\2\u0460\u0461\3\2\2\2\u0461"+
		"\u0464\3\2\2\2\u0462\u0460\3\2\2\2\u0463\u0465\5`\61\2\u0464\u0463\3\2"+
		"\2\2\u0464\u0465\3\2\2\2\u0465\u0467\3\2\2\2\u0466\u044f\3\2\2\2\u0466"+
		"\u045a\3\2\2\2\u0467\67\3\2\2\2\u0468\u046b\5:\36\2\u0469\u046b\5H%\2"+
		"\u046a\u0468\3\2\2\2\u046a\u0469\3\2\2\2\u046b9\3\2\2\2\u046c\u046e\5"+
		"N(\2\u046d\u046c\3\2\2\2\u046d\u046e\3\2\2\2\u046e\u0470\3\2\2\2\u046f"+
		"\u0471\5<\37\2\u0470\u046f\3\2\2\2\u0470\u0471\3\2\2\2\u0471\u0472\3\2"+
		"\2\2\u0472\u0476\5&\24\2\u0473\u0474\5&\24\2\u0474\u0475\5\64\33\2\u0475"+
		"\u0477\3\2\2\2\u0476\u0473\3\2\2\2\u0476\u0477\3\2\2\2\u0477\u0478\3\2"+
		"\2\2\u0478\u0479\5\66\34\2\u0479\u047b\5R*\2\u047a\u047c\5D#\2\u047b\u047a"+
		"\3\2\2\2\u047b\u047c\3\2\2\2\u047c;\3\2\2\2\u047d\u047f\7\u0081\2\2\u047e"+
		"\u047d\3\2\2\2\u047e\u047f\3\2\2\2\u047f\u0480\3\2\2\2\u0480\u0484\5>"+
		" \2\u0481\u0483\5<\37\2\u0482\u0481\3\2\2\2\u0483\u0486\3\2\2\2\u0484"+
		"\u0482\3\2\2\2\u0484\u0485\3\2\2\2\u0485=\3\2\2\2\u0486\u0484\3\2\2\2"+
		"\u0487\u0488\t\4\2\2\u0488?\3\2\2\2\u0489\u048e\7\3\2\2\u048a\u048b\7"+
		"|\2\2\u048b\u048d\5B\"\2\u048c\u048a\3\2\2\2\u048d\u0490\3\2\2\2\u048e"+
		"\u048c\3\2\2\2\u048e\u048f\3\2\2\2\u048f\u049d\3\2\2\2\u0490\u048e\3\2"+
		"\2\2\u0491\u0496\5,\27\2\u0492\u0493\7|\2\2\u0493\u0495\5@!\2\u0494\u0492"+
		"\3\2\2\2\u0495\u0498\3\2\2\2\u0496\u0494\3\2\2\2\u0496\u0497\3\2\2\2\u0497"+
		"\u049d\3\2\2\2\u0498\u0496\3\2\2\2\u0499\u049a\5&\24\2\u049a\u049b\7\u00a1"+
		"\2\2\u049b\u049d\3\2\2\2\u049c\u0489\3\2\2\2\u049c\u0491\3\2\2\2\u049c"+
		"\u0499\3\2\2\2\u049dA\3\2\2\2\u049e\u04a3\5,\27\2\u049f\u04a0\7|\2\2\u04a0"+
		"\u04a2\5B\"\2\u04a1\u049f\3\2\2\2\u04a2\u04a5\3\2\2\2\u04a3\u04a1\3\2"+
		"\2\2\u04a3\u04a4\3\2\2\2\u04a4\u04aa\3\2\2\2\u04a5\u04a3\3\2\2\2\u04a6"+
		"\u04a7\5&\24\2\u04a7\u04a8\7\u00a1\2\2\u04a8\u04aa\3\2\2\2\u04a9\u049e"+
		"\3\2\2\2\u04a9\u04a6\3\2\2\2\u04aaC\3\2\2\2\u04ab\u04ac\7i\2\2\u04ac\u04ad"+
		"\5F$\2\u04adE\3\2\2\2\u04ae\u04b3\5&\24\2\u04af\u04b0\7|\2\2\u04b0\u04b2"+
		"\5&\24\2\u04b1\u04af\3\2\2\2\u04b2\u04b5\3\2\2\2\u04b3\u04b1\3\2\2\2\u04b3"+
		"\u04b4\3\2\2\2\u04b4G\3\2\2\2\u04b5\u04b3\3\2\2\2\u04b6\u04b8\5N(\2\u04b7"+
		"\u04b6\3\2\2\2\u04b7\u04b8\3\2\2\2\u04b8\u04ba\3\2\2\2\u04b9\u04bb\5J"+
		"&\2\u04ba\u04b9\3\2\2\2\u04ba\u04bb\3\2\2\2\u04bb\u04bf\3\2\2\2\u04bc"+
		"\u04bd\5&\24\2\u04bd\u04be\5\64\33\2\u04be\u04c0\3\2\2\2\u04bf\u04bc\3"+
		"\2\2\2\u04bf\u04c0\3\2\2\2\u04c0\u04c1\3\2\2\2\u04c1\u04c2\7[\2\2\u04c2"+
		"\u04c4\5R*\2\u04c3\u04c5\5D#\2\u04c4\u04c3\3\2\2\2\u04c4\u04c5\3\2\2\2"+
		"\u04c5I\3\2\2\2\u04c6\u04c8\7\u0081\2\2\u04c7\u04c6\3\2\2\2\u04c7\u04c8"+
		"\3\2\2\2\u04c8\u04c9\3\2\2\2\u04c9\u04cd\5L\'\2\u04ca\u04cc\5J&\2\u04cb"+
		"\u04ca\3\2\2\2\u04cc\u04cf\3\2\2\2\u04cd\u04cb\3\2\2\2\u04cd\u04ce\3\2"+
		"\2\2\u04ceK\3\2\2\2\u04cf\u04cd\3\2\2\2\u04d0\u04d1\t\5\2\2\u04d1M\3\2"+
		"\2\2\u04d2\u04d4\7\u0081\2\2\u04d3\u04d2\3\2\2\2\u04d3\u04d4\3\2\2\2\u04d4"+
		"\u04d5\3\2\2\2\u04d5\u04d6\7<\2\2\u04d6\u04da\5P)\2\u04d7\u04d9\5N(\2"+
		"\u04d8\u04d7\3\2\2\2\u04d9\u04dc\3\2\2\2\u04da\u04d8\3\2\2\2\u04da\u04db"+
		"\3\2\2\2\u04dbO\3\2\2\2\u04dc\u04da\3\2\2\2\u04dd\u04e3\5z>\2\u04de\u04df"+
		"\7u\2\2\u04df\u04e0\5&\24\2\u04e0\u04e1\7v\2\2\u04e1\u04e3\3\2\2\2\u04e2"+
		"\u04dd\3\2\2\2\u04e2\u04de\3\2\2\2\u04e3Q\3\2\2\2\u04e4\u04e6\7u\2\2\u04e5"+
		"\u04e7\5@!\2\u04e6\u04e5\3\2\2\2\u04e6\u04e7\3\2\2\2\u04e7\u04e8\3\2\2"+
		"\2\u04e8\u04e9\7v\2\2\u04e9S\3\2\2\2\u04ea\u04ed\5\u00c2b\2\u04eb\u04ed"+
		"\5t;\2\u04ec\u04ea\3\2\2\2\u04ec\u04eb\3\2\2\2\u04edU\3\2\2\2\u04ee\u04f1"+
		"\5z>\2\u04ef\u04f1\5`\61\2\u04f0\u04ee\3\2\2\2\u04f0\u04ef\3\2\2\2\u04f1"+
		"W\3\2\2\2\u04f2\u04f5\7\3\2\2\u04f3\u04f4\7|\2\2\u04f4\u04f6\5Z.\2\u04f5"+
		"\u04f3\3\2\2\2\u04f5\u04f6\3\2\2\2\u04f6\u0508\3\2\2\2\u04f7\u04fc\5V"+
		",\2\u04f8\u04f9\7|\2\2\u04f9\u04fb\5X-\2\u04fa\u04f8\3\2\2\2\u04fb\u04fe"+
		"\3\2\2\2\u04fc\u04fa\3\2\2\2\u04fc\u04fd\3\2\2\2\u04fd\u0508\3\2\2\2\u04fe"+
		"\u04fc\3\2\2\2\u04ff\u0504\7\u008f\2\2\u0500\u0501\7|\2\2\u0501\u0503"+
		"\5X-\2\u0502\u0500\3\2\2\2\u0503\u0506\3\2\2\2\u0504\u0502\3\2\2\2\u0504"+
		"\u0505\3\2\2\2\u0505\u0508\3\2\2\2\u0506\u0504\3\2\2\2\u0507\u04f2\3\2"+
		"\2\2\u0507\u04f7\3\2\2\2\u0507\u04ff\3\2\2\2\u0508Y\3\2\2\2\u0509\u050e"+
		"\5V,\2\u050a\u050b\7|\2\2\u050b\u050d\5Z.\2\u050c\u050a\3\2\2\2\u050d"+
		"\u0510\3\2\2\2\u050e\u050c\3\2\2\2\u050e\u050f\3\2\2\2\u050f\u051a\3\2"+
		"\2\2\u0510\u050e\3\2\2\2\u0511\u0516\7\u008f\2\2\u0512\u0513\7|\2\2\u0513"+
		"\u0515\5Z.\2\u0514\u0512\3\2\2\2\u0515\u0518\3\2\2\2\u0516\u0514\3\2\2"+
		"\2\u0516\u0517\3\2\2\2\u0517\u051a\3\2\2\2\u0518\u0516\3\2\2\2\u0519\u0509"+
		"\3\2\2\2\u0519\u0511\3\2\2\2\u051a[\3\2\2\2\u051b\u051e\5T+\2\u051c\u051e"+
		"\t\6\2\2\u051d\u051b\3\2\2\2\u051d\u051c\3\2\2\2\u051e]\3\2\2\2\u051f"+
		"\u0524\5\\/\2\u0520\u0521\7|\2\2\u0521\u0523\5\\/\2\u0522\u0520\3\2\2"+
		"\2\u0523\u0526\3\2\2\2\u0524\u0522\3\2\2\2\u0524\u0525\3\2\2\2\u0525_"+
		"\3\2\2\2\u0526\u0524\3\2\2\2\u0527\u052a\t\7\2\2\u0528\u052a\7\u00a0\2"+
		"\2\u0529\u0527\3\2\2\2\u0529\u0528\3\2\2\2\u052aa\3\2\2\2\u052b\u052c"+
		"\7E\2\2\u052c\u052e\5`\61\2\u052d\u052f\5\u009eP\2\u052e\u052d\3\2\2\2"+
		"\u052e\u052f\3\2\2\2\u052f\u0532\3\2\2\2\u0530\u0531\7M\2\2\u0531\u0533"+
		"\5\u00c2b\2\u0532\u0530\3\2\2\2\u0532\u0533\3\2\2\2\u0533\u0536\3\2\2"+
		"\2\u0534\u0535\7T\2\2\u0535\u0537\5\u00a6T\2\u0536\u0534\3\2\2\2\u0536"+
		"\u0537\3\2\2\2\u0537\u0538\3\2\2\2\u0538\u0539\5\u00a8U\2\u0539c\3\2\2"+
		"\2\u053a\u053d\5`\61\2\u053b\u053c\7M\2\2\u053c\u053e\5\u00a0Q\2\u053d"+
		"\u053b\3\2\2\2\u053d\u053e\3\2\2\2\u053ee\3\2\2\2\u053f\u0540\7L\2\2\u0540"+
		"\u0543\5`\61\2\u0541\u0542\7T\2\2\u0542\u0544\5\u00a6T\2\u0543\u0541\3"+
		"\2\2\2\u0543\u0544\3\2\2\2\u0544\u0545\3\2\2\2\u0545\u0547\7w\2\2\u0546"+
		"\u0548\5\u00a2R\2\u0547\u0546\3\2\2\2\u0547\u0548\3\2\2\2\u0548\u054a"+
		"\3\2\2\2\u0549\u054b\7|\2\2\u054a\u0549\3\2\2\2\u054a\u054b\3\2\2\2\u054b"+
		"\u054d\3\2\2\2\u054c\u054e\5\u00a4S\2\u054d\u054c\3\2\2\2\u054d\u054e"+
		"\3\2\2\2\u054e\u054f\3\2\2\2\u054f\u0550\7x\2\2\u0550g\3\2\2\2\u0551\u0553"+
		"\5\f\7\2\u0552\u0551\3\2\2\2\u0553\u0556\3\2\2\2\u0554\u0552\3\2\2\2\u0554"+
		"\u0555\3\2\2\2\u0555\u0557\3\2\2\2\u0556\u0554\3\2\2\2\u0557\u0559\5`"+
		"\61\2\u0558\u055a\5\u0122\u0092\2\u0559\u0558\3\2\2\2\u0559\u055a\3\2"+
		"\2\2\u055a\u055c\3\2\2\2\u055b\u055d\5\u00a8U\2\u055c\u055b\3\2\2\2\u055c"+
		"\u055d\3\2\2\2\u055di\3\2\2\2\u055e\u055f\7X\2\2\u055f\u0561\5`\61\2\u0560"+
		"\u0562\5\u009eP\2\u0561\u0560\3\2\2\2\u0561\u0562\3\2\2\2\u0562\u0565"+
		"\3\2\2\2\u0563\u0564\7M\2\2\u0564\u0566\5\u00a6T\2\u0565\u0563\3\2\2\2"+
		"\u0565\u0566\3\2\2\2\u0566\u0567\3\2\2\2\u0567\u0568\5\u00aaV\2\u0568"+
		"k\3\2\2\2\u0569\u056c\5\u00c2b\2\u056a\u056c\7l\2\2\u056b\u0569\3\2\2"+
		"\2\u056b\u056a\3\2\2\2\u056c\u056d\3\2\2\2\u056d\u056e\5`\61\2\u056e\u0573"+
		"\5\u00ccg\2\u056f\u0570\7y\2\2\u0570\u0572\7z\2\2\u0571\u056f\3\2\2\2"+
		"\u0572\u0575\3\2\2\2\u0573\u0571\3\2\2\2\u0573\u0574\3\2\2\2\u0574\u0578"+
		"\3\2\2\2\u0575\u0573\3\2\2\2\u0576\u0577\7i\2\2\u0577\u0579\5\u00caf\2"+
		"\u0578\u0576\3\2\2\2\u0578\u0579\3\2\2\2\u0579\u057c\3\2\2\2\u057a\u057d"+
		"\5\u00d4k\2\u057b\u057d\7{\2\2\u057c\u057a\3\2\2\2\u057c\u057b\3\2\2\2"+
		"\u057dm\3\2\2\2\u057e\u057f\5`\61\2\u057f\u0582\5\u00ccg\2\u0580\u0581"+
		"\7i\2\2\u0581\u0583\5\u00caf\2\u0582\u0580\3\2\2\2\u0582\u0583\3\2\2\2"+
		"\u0583\u0584\3\2\2\2\u0584\u0585\5\u00d6l\2\u0585o\3\2\2\2\u0586\u058b"+
		"\5`\61\2\u0587\u0588\7y\2\2\u0588\u058a\7z\2\2\u0589\u0587\3\2\2\2\u058a"+
		"\u058d\3\2\2\2\u058b\u0589\3\2\2\2\u058b\u058c\3\2\2\2\u058c\u058e\3\2"+
		"\2\2\u058d\u058b\3\2\2\2\u058e\u058f\7~\2\2\u058f\u0590\5\u00be`\2\u0590"+
		"q\3\2\2\2\u0591\u0594\5\u00c2b\2\u0592\u0594\7l\2\2\u0593\u0591\3\2\2"+
		"\2\u0593\u0592\3\2\2\2\u0594\u0595\3\2\2\2\u0595\u0596\5`\61\2\u0596\u059b"+
		"\5\u00ccg\2\u0597\u0598\7y\2\2\u0598\u059a\7z\2\2\u0599\u0597\3\2\2\2"+
		"\u059a\u059d\3\2\2\2\u059b\u0599\3\2\2\2\u059b\u059c\3\2\2\2\u059c\u05a0"+
		"\3\2\2\2\u059d\u059b\3\2\2\2\u059e\u059f\7i\2\2\u059f\u05a1\5\u00caf\2"+
		"\u05a0\u059e\3\2\2\2\u05a0\u05a1\3\2\2\2\u05a1\u05a2\3\2\2\2\u05a2\u05a3"+
		"\7{\2\2\u05a3s\3\2\2\2\u05a4\u05a9\5`\61\2\u05a5\u05a6\7y\2\2\u05a6\u05a8"+
		"\7z\2\2\u05a7\u05a5\3\2\2\2\u05a8\u05ab\3\2\2\2\u05a9\u05a7\3\2\2\2\u05a9"+
		"\u05aa\3\2\2\2\u05aau\3\2\2\2\u05ab\u05a9\3\2\2\2\u05ac\u05ad\5`\61\2"+
		"\u05adw\3\2\2\2\u05ae\u05b0\5`\61\2\u05af\u05b1\5\u00c6d\2\u05b0\u05af"+
		"\3\2\2\2\u05b0\u05b1\3\2\2\2\u05b1\u05b9\3\2\2\2\u05b2\u05b3\7}\2\2\u05b3"+
		"\u05b5\5`\61\2\u05b4\u05b6\5\u00c6d\2\u05b5\u05b4\3\2\2\2\u05b5\u05b6"+
		"\3\2\2\2\u05b6\u05b8\3\2\2\2\u05b7\u05b2\3\2\2\2\u05b8\u05bb\3\2\2\2\u05b9"+
		"\u05b7\3\2\2\2\u05b9\u05ba\3\2\2\2\u05bay\3\2\2\2\u05bb\u05b9\3\2\2\2"+
		"\u05bc\u05c1\5`\61\2\u05bd\u05be\7}\2\2\u05be\u05c0\5`\61\2\u05bf\u05bd"+
		"\3\2\2\2\u05c0\u05c3\3\2\2\2\u05c1\u05bf\3\2\2\2\u05c1\u05c2\3\2\2\2\u05c2"+
		"{\3\2\2\2\u05c3\u05c1\3\2\2\2\u05c4\u05c5\5`\61\2\u05c5\u05c6\7~\2\2\u05c6"+
		"\u05c7\5\u00dep\2\u05c7}\3\2\2\2\u05c8\u05c9\7<\2\2\u05c9\u05ca\7X\2\2"+
		"\u05ca\u05cb\5`\61\2\u05cb\u05cc\5\u00e2r\2\u05cc\177\3\2\2\2\u05cd\u05ce"+
		"\5`\61\2\u05ce\u05cf\7u\2\2\u05cf\u05d1\7v\2\2\u05d0\u05d2\5\u00ecw\2"+
		"\u05d1\u05d0\3\2\2\2\u05d1\u05d2\3\2\2\2\u05d2\u0081\3\2\2\2\u05d3\u063d"+
		"\5\u00eex\2\u05d4\u05d5\7>\2\2\u05d5\u05d8\5\u0086D\2\u05d6\u05d7\7\u0084"+
		"\2\2\u05d7\u05d9\5\u0086D\2\u05d8\u05d6\3\2\2\2\u05d8\u05d9\3\2\2\2\u05d9"+
		"\u05da\3\2\2\2\u05da\u05db\7{\2\2\u05db\u063d\3\2\2\2\u05dc\u05dd\7R\2"+
		"\2\u05dd\u05de\5\u010c\u0087\2\u05de\u05e1\5\u0082B\2\u05df\u05e0\7K\2"+
		"\2\u05e0\u05e2\5\u0082B\2\u05e1\u05df\3\2\2\2\u05e1\u05e2\3\2\2\2\u05e2"+
		"\u063d\3\2\2\2\u05e3\u05e4\7Q\2\2\u05e4\u05e5\7u\2\2\u05e5\u05e6\5\u0104"+
		"\u0083\2\u05e6\u05e7\7v\2\2\u05e7\u05e8\5\u0082B\2\u05e8\u063d\3\2\2\2"+
		"\u05e9\u05ea\7n\2\2\u05ea\u05eb\5\u010c\u0087\2\u05eb\u05ec\5\u0082B\2"+
		"\u05ec\u063d\3\2\2\2\u05ed\u05ee\7I\2\2\u05ee\u05ef\5\u0082B\2\u05ef\u05f0"+
		"\7n\2\2\u05f0\u05f1\5\u010c\u0087\2\u05f1\u05f2\7{\2\2\u05f2\u063d\3\2"+
		"\2\2\u05f3\u05f4\7k\2\2\u05f4\u05fe\5\u00eex\2\u05f5\u05f7\5\u0084C\2"+
		"\u05f6\u05f5\3\2\2\2\u05f7\u05f8\3\2\2\2\u05f8\u05f6\3\2\2\2\u05f8\u05f9"+
		"\3\2\2\2\u05f9\u05fb\3\2\2\2\u05fa\u05fc\5\u00f8}\2\u05fb\u05fa\3\2\2"+
		"\2\u05fb\u05fc\3\2\2\2\u05fc\u05ff\3\2\2\2\u05fd\u05ff\5\u00f8}\2\u05fe"+
		"\u05f6\3\2\2\2\u05fe\u05fd\3\2\2\2\u05ff\u063d\3\2\2\2\u0600\u0601\7k"+
		"\2\2\u0601\u0602\5\u00fa~\2\u0602\u0606\5\u00eex\2\u0603\u0605\5\u0084"+
		"C\2\u0604\u0603\3\2\2\2\u0605\u0608\3\2\2\2\u0606\u0604\3\2\2\2\u0606"+
		"\u0607\3\2\2\2\u0607\u060a\3\2\2\2\u0608\u0606\3\2\2\2\u0609\u060b\5\u00f8"+
		"}\2\u060a\u0609\3\2\2\2\u060a\u060b\3\2\2\2\u060b\u063d\3\2\2\2\u060c"+
		"\u060d\7e\2\2\u060d\u060e\5\u010c\u0087\2\u060e\u0612\7w\2\2\u060f\u0611"+
		"\5\u0100\u0081\2\u0610\u060f\3\2\2\2\u0611\u0614\3\2\2\2\u0612\u0610\3"+
		"\2\2\2\u0612\u0613\3\2\2\2\u0613\u0618\3\2\2\2\u0614\u0612\3\2\2\2\u0615"+
		"\u0617\5\u0102\u0082\2\u0616\u0615\3\2\2\2\u0617\u061a\3\2\2\2\u0618\u0616"+
		"\3\2\2\2\u0618\u0619\3\2\2\2\u0619\u061b\3\2\2\2\u061a\u0618\3\2\2\2\u061b"+
		"\u061c\7x\2\2\u061c\u063d\3\2\2\2\u061d\u061e\7f\2\2\u061e\u061f\5\u010c"+
		"\u0087\2\u061f\u0620\5\u00eex\2\u0620\u063d\3\2\2\2\u0621\u0623\7`\2\2"+
		"\u0622\u0624\5\u0086D\2\u0623\u0622\3\2\2\2\u0623\u0624\3\2\2\2\u0624"+
		"\u0625\3\2\2\2\u0625\u063d\7{\2\2\u0626\u0627\7h\2\2\u0627\u0628\5\u0086"+
		"D\2\u0628\u0629\7{\2\2\u0629\u063d\3\2\2\2\u062a\u062c\7@\2\2\u062b\u062d"+
		"\5`\61\2\u062c\u062b\3\2\2\2\u062c\u062d\3\2\2\2\u062d\u062e\3\2\2\2\u062e"+
		"\u063d\7{\2\2\u062f\u0631\7G\2\2\u0630\u0632\5`\61\2\u0631\u0630\3\2\2"+
		"\2\u0631\u0632\3\2\2\2\u0632\u0633\3\2\2\2\u0633\u063d\7{\2\2\u0634\u063d"+
		"\7{\2\2\u0635\u0636\5\u0110\u0089\2\u0636\u0637\7{\2\2\u0637\u063d\3\2"+
		"\2\2\u0638\u0639\5`\61\2\u0639\u063a\7\u0084\2\2\u063a\u063b\5\u0082B"+
		"\2\u063b\u063d\3\2\2\2\u063c\u05d3\3\2\2\2\u063c\u05d4\3\2\2\2\u063c\u05dc"+
		"\3\2\2\2\u063c\u05e3\3\2\2\2\u063c\u05e9\3\2\2\2\u063c\u05ed\3\2\2\2\u063c"+
		"\u05f3\3\2\2\2\u063c\u0600\3\2\2\2\u063c\u060c\3\2\2\2\u063c\u061d\3\2"+
		"\2\2\u063c\u0621\3\2\2\2\u063c\u0626\3\2\2\2\u063c\u062a\3\2\2\2\u063c"+
		"\u062f\3\2\2\2\u063c\u0634\3\2\2\2\u063c\u0635\3\2\2\2\u063c\u0638\3\2"+
		"\2\2\u063d\u0083\3\2\2\2\u063e\u063f\7C\2\2\u063f\u0643\7u\2\2\u0640\u0642"+
		"\5\u009cO\2\u0641\u0640\3\2\2\2\u0642\u0645\3\2\2\2\u0643\u0641\3\2\2"+
		"\2\u0643\u0644\3\2\2\2\u0644\u0646\3\2\2\2\u0645\u0643\3\2\2\2\u0646\u0647"+
		"\5\u00f6|\2\u0647\u0648\5`\61\2\u0648\u0649\7v\2\2\u0649\u064a\5\u00ee"+
		"x\2\u064a\u0085\3\2\2\2\u064b\u064c\bD\1\2\u064c\u0659\5\u0088E\2\u064d"+
		"\u064e\7[\2\2\u064e\u0659\5\u0114\u008b\2\u064f\u0650\7u\2\2\u0650\u0651"+
		"\5\u00c2b\2\u0651\u0652\7v\2\2\u0652\u0653\5\u0086D\23\u0653\u0659\3\2"+
		"\2\2\u0654\u0655\t\b\2\2\u0655\u0659\5\u0086D\21\u0656\u0657\t\t\2\2\u0657"+
		"\u0659\5\u0086D\20\u0658\u064b\3\2\2\2\u0658\u064d\3\2\2\2\u0658\u064f"+
		"\3\2\2\2\u0658\u0654\3\2\2\2\u0658\u0656\3\2\2\2\u0659\u06af\3\2\2\2\u065a"+
		"\u065b\f\17\2\2\u065b\u065c\t\n\2\2\u065c\u06ae\5\u0086D\20\u065d\u065e"+
		"\f\16\2\2\u065e\u065f\t\13\2\2\u065f\u06ae\5\u0086D\17\u0660\u0668\f\r"+
		"\2\2\u0661\u0662\7\u0080\2\2\u0662\u0669\7\u0080\2\2\u0663\u0664\7\177"+
		"\2\2\u0664\u0665\7\177\2\2\u0665\u0669\7\177\2\2\u0666\u0667\7\177\2\2"+
		"\u0667\u0669\7\177\2\2\u0668\u0661\3\2\2\2\u0668\u0663\3\2\2\2\u0668\u0666"+
		"\3\2\2\2\u0669\u066a\3\2\2\2\u066a\u06ae\5\u0086D\16\u066b\u066c\f\f\2"+
		"\2\u066c\u066d\t\f\2\2\u066d\u06ae\5\u0086D\r\u066e\u066f\f\n\2\2\u066f"+
		"\u0670\t\r\2\2\u0670\u06ae\5\u0086D\13\u0671\u0672\f\t\2\2\u0672\u0673"+
		"\7\u0091\2\2\u0673\u06ae\5\u0086D\n\u0674\u0675\f\b\2\2\u0675\u0676\7"+
		"\u0093\2\2\u0676\u06ae\5\u0086D\t\u0677\u0678\f\7\2\2\u0678\u0679\7\u0092"+
		"\2\2\u0679\u06ae\5\u0086D\b\u067a\u067b\f\6\2\2\u067b\u067c\7\u0089\2"+
		"\2\u067c\u06ae\5\u0086D\7\u067d\u067e\f\5\2\2\u067e\u067f\7\u008a\2\2"+
		"\u067f\u06ae\5\u0086D\6\u0680\u0681\f\4\2\2\u0681\u0682\7\u0083\2\2\u0682"+
		"\u0683\5\u0086D\2\u0683\u0684\7\u0084\2\2\u0684\u0685\5\u0086D\5\u0685"+
		"\u06ae\3\2\2\2\u0686\u0687\f\3\2\2\u0687\u0688\t\16\2\2\u0688\u06ae\5"+
		"\u0086D\4\u0689\u068a\f\33\2\2\u068a\u068b\7}\2\2\u068b\u06ae\5`\61\2"+
		"\u068c\u068d\f\32\2\2\u068d\u068e\7}\2\2\u068e\u06ae\7g\2\2\u068f\u0690"+
		"\f\31\2\2\u0690\u0691\7}\2\2\u0691\u0693\7[\2\2\u0692\u0694\5\u011c\u008f"+
		"\2\u0693\u0692\3\2\2\2\u0693\u0694\3\2\2\2\u0694\u0695\3\2\2\2\u0695\u06ae"+
		"\5\u008cG\2\u0696\u0697\f\30\2\2\u0697\u0698\7}\2\2\u0698\u0699\7d\2\2"+
		"\u0699\u06ae\5\u008eH\2\u069a\u069b\f\27\2\2\u069b\u069c\7}\2\2\u069c"+
		"\u06ae\5\u011a\u008e\2\u069d\u069e\f\26\2\2\u069e\u069f\7y\2\2\u069f\u06a0"+
		"\5\u0086D\2\u06a0\u06a1\7z\2\2\u06a1\u06ae\3\2\2\2\u06a2\u06a3\f\25\2"+
		"\2\u06a3\u06a5\7u\2\2\u06a4\u06a6\5\u010e\u0088\2\u06a5\u06a4\3\2\2\2"+
		"\u06a5\u06a6\3\2\2\2\u06a6\u06a7\3\2\2\2\u06a7\u06ae\7v\2\2\u06a8\u06a9"+
		"\f\22\2\2\u06a9\u06ae\t\17\2\2\u06aa\u06ab\f\13\2\2\u06ab\u06ac\7V\2\2"+
		"\u06ac\u06ae\5\u00c2b\2\u06ad\u065a\3\2\2\2\u06ad\u065d\3\2\2\2\u06ad"+
		"\u0660\3\2\2\2\u06ad\u066b\3\2\2\2\u06ad\u066e\3\2\2\2\u06ad\u0671\3\2"+
		"\2\2\u06ad\u0674\3\2\2\2\u06ad\u0677\3\2\2\2\u06ad\u067a\3\2\2\2\u06ad"+
		"\u067d\3\2\2\2\u06ad\u0680\3\2\2\2\u06ad\u0686\3\2\2\2\u06ad\u0689\3\2"+
		"\2\2\u06ad\u068c\3\2\2\2\u06ad\u068f\3\2\2\2\u06ad\u0696\3\2\2\2\u06ad"+
		"\u069a\3\2\2\2\u06ad\u069d\3\2\2\2\u06ad\u06a2\3\2\2\2\u06ad\u06a8\3\2"+
		"\2\2\u06ad\u06aa\3\2\2\2\u06ae\u06b1\3\2\2\2\u06af\u06ad\3\2\2\2\u06af"+
		"\u06b0\3\2\2\2\u06b0\u0087\3\2\2\2\u06b1\u06af\3\2\2\2\u06b2\u06b3\7u"+
		"\2\2\u06b3\u06b4\5\u0086D\2\u06b4\u06b5\7v\2\2\u06b5\u06c8\3\2\2\2\u06b6"+
		"\u06c8\7g\2\2\u06b7\u06c8\7d\2\2\u06b8\u06c8\5\u00d8m\2\u06b9\u06c8\5"+
		"`\61\2\u06ba\u06bb\5\u00c2b\2\u06bb\u06bc\7}\2\2\u06bc\u06bd\7E\2\2\u06bd"+
		"\u06c8\3\2\2\2\u06be\u06bf\7l\2\2\u06bf\u06c0\7}\2\2\u06c0\u06c8\7E\2"+
		"\2\u06c1\u06c5\5\u011c\u008f\2\u06c2\u06c6\5\u0090I\2\u06c3\u06c4\7g\2"+
		"\2\u06c4\u06c6\5\u0122\u0092\2\u06c5\u06c2\3\2\2\2\u06c5\u06c3\3\2\2\2"+
		"\u06c6\u06c8\3\2\2\2\u06c7\u06b2\3\2\2\2\u06c7\u06b6\3\2\2\2\u06c7\u06b7"+
		"\3\2\2\2\u06c7\u06b8\3\2\2\2\u06c7\u06b9\3\2\2\2\u06c7\u06ba\3\2\2\2\u06c7"+
		"\u06be\3\2\2\2\u06c7\u06c1\3\2\2\2\u06c8\u0089\3\2\2\2\u06c9\u06cb\5`"+
		"\61\2\u06ca\u06cc\5\u011e\u0090\2\u06cb\u06ca\3\2\2\2\u06cb\u06cc\3\2"+
		"\2\2\u06cc\u06d4\3\2\2\2\u06cd\u06ce\7}\2\2\u06ce\u06d0\5`\61\2\u06cf"+
		"\u06d1\5\u011e\u0090\2\u06d0\u06cf\3\2\2\2\u06d0\u06d1\3\2\2\2\u06d1\u06d3"+
		"\3\2\2\2\u06d2\u06cd\3\2\2\2\u06d3\u06d6\3\2\2\2\u06d4\u06d2\3\2\2\2\u06d4"+
		"\u06d5\3\2\2\2\u06d5\u06d9\3\2\2\2\u06d6\u06d4\3\2\2\2\u06d7\u06d9\5\u00c4"+
		"c\2\u06d8\u06c9\3\2\2\2\u06d8\u06d7\3\2\2\2\u06d9\u008b\3\2\2\2\u06da"+
		"\u06dc\5`\61\2\u06db\u06dd\5\u0120\u0091\2\u06dc\u06db\3\2\2\2\u06dc\u06dd"+
		"\3\2\2\2\u06dd\u06de\3\2\2\2\u06de\u06df\5\u0118\u008d\2\u06df\u008d\3"+
		"\2\2\2\u06e0\u06e7\5\u0122\u0092\2\u06e1\u06e2\7}\2\2\u06e2\u06e4\5`\61"+
		"\2\u06e3\u06e5\5\u0122\u0092\2\u06e4\u06e3\3\2\2\2\u06e4\u06e5\3\2\2\2"+
		"\u06e5\u06e7\3\2\2\2\u06e6\u06e0\3\2\2\2\u06e6\u06e1\3\2\2\2\u06e7\u008f"+
		"\3\2\2\2\u06e8\u06e9\7d\2\2\u06e9\u06ee\5\u008eH\2\u06ea\u06eb\5`\61\2"+
		"\u06eb\u06ec\5\u0122\u0092\2\u06ec\u06ee\3\2\2\2\u06ed\u06e8\3\2\2\2\u06ed"+
		"\u06ea\3\2\2\2\u06ee\u0091\3\2\2\2\u06ef\u06f1\5\u0094K\2\u06f0\u06ef"+
		"\3\2\2\2\u06f0\u06f1\3\2\2\2\u06f1\u06f5\3\2\2\2\u06f2\u06f4\5\u0096L"+
		"\2\u06f3\u06f2\3\2\2\2\u06f4\u06f7\3\2\2\2\u06f5\u06f3\3\2\2\2\u06f5\u06f6"+
		"\3\2\2\2\u06f6\u06fb\3\2\2\2\u06f7\u06f5\3\2\2\2\u06f8\u06fa\5\2\2\2\u06f9"+
		"\u06f8\3\2\2\2\u06fa\u06fd\3\2\2\2\u06fb\u06f9\3\2\2\2\u06fb\u06fc\3\2"+
		"\2\2\u06fc\u06fe\3\2\2\2\u06fd\u06fb\3\2\2\2\u06fe\u06ff\7\2\2\3\u06ff"+
		"\u0093\3\2\2\2\u0700\u0702\5\f\7\2\u0701\u0700\3\2\2\2\u0702\u0705\3\2"+
		"\2\2\u0703\u0701\3\2\2\2\u0703\u0704\3\2\2\2\u0704\u0706\3\2\2\2\u0705"+
		"\u0703\3\2\2\2\u0706\u0707\7\\\2\2\u0707\u0708\5z>\2\u0708\u0709\7{\2"+
		"\2\u0709\u0095\3\2\2\2\u070a\u070c\7U\2\2\u070b\u070d\7b\2\2\u070c\u070b"+
		"\3\2\2\2\u070c\u070d\3\2\2\2\u070d\u070e\3\2\2\2\u070e\u0711\5z>\2\u070f"+
		"\u0710\7}\2\2\u0710\u0712\7\u008f\2\2\u0711\u070f\3\2\2\2\u0711\u0712"+
		"\3\2\2\2\u0712\u0713\3\2\2\2\u0713\u0714\7{\2\2\u0714\u0097\3\2\2\2\u0715"+
		"\u0718\5\u009aN\2\u0716\u0718\t\20\2\2\u0717\u0715\3\2\2\2\u0717\u0716"+
		"\3\2\2\2\u0718\u0099\3\2\2\2\u0719\u071c\5\f\7\2\u071a\u071c\t\21\2\2"+
		"\u071b\u0719\3\2\2\2\u071b\u071a\3\2\2\2\u071c\u009b\3\2\2\2\u071d\u0720"+
		"\7N\2\2\u071e\u0720\5\f\7\2\u071f\u071d\3\2\2\2\u071f\u071e\3\2\2\2\u0720"+
		"\u009d\3\2\2\2\u0721\u0722\7\u0080\2\2\u0722\u0727\5d\63\2\u0723\u0724"+
		"\7|\2\2\u0724\u0726\5d\63\2\u0725\u0723\3\2\2\2\u0726\u0729\3\2\2\2\u0727"+
		"\u0725\3\2\2\2\u0727\u0728\3\2\2\2\u0728\u072a\3\2\2\2\u0729\u0727\3\2"+
		"\2\2\u072a\u072b\7\177\2\2\u072b\u009f\3\2\2\2\u072c\u0731\5\u00c2b\2"+
		"\u072d\u072e\7\u0091\2\2\u072e\u0730\5\u00c2b\2\u072f\u072d\3\2\2\2\u0730"+
		"\u0733\3\2\2\2\u0731\u072f\3\2\2\2\u0731\u0732\3\2\2\2\u0732\u00a1\3\2"+
		"\2\2\u0733\u0731\3\2\2\2\u0734\u0739\5h\65\2\u0735\u0736\7|\2\2\u0736"+
		"\u0738\5h\65\2\u0737\u0735\3\2\2\2\u0738\u073b\3\2\2\2\u0739\u0737\3\2"+
		"\2\2\u0739\u073a\3\2\2\2\u073a\u00a3\3\2\2\2\u073b\u0739\3\2\2\2\u073c"+
		"\u0740\7{\2\2\u073d\u073f\5\6\4\2\u073e\u073d\3\2\2\2\u073f\u0742\3\2"+
		"\2\2\u0740\u073e\3\2\2\2\u0740\u0741\3\2\2\2\u0741\u00a5\3\2\2\2\u0742"+
		"\u0740\3\2\2\2\u0743\u0748\5\u00c2b\2\u0744\u0745\7|\2\2\u0745\u0747\5"+
		"\u00c2b\2\u0746\u0744\3\2\2\2\u0747\u074a\3\2\2\2\u0748\u0746\3\2\2\2"+
		"\u0748\u0749\3\2\2\2\u0749\u00a7\3\2\2\2\u074a\u0748\3\2\2\2\u074b\u074f"+
		"\7w\2\2\u074c\u074e\5\6\4\2\u074d\u074c\3\2\2\2\u074e\u0751\3\2\2\2\u074f"+
		"\u074d\3\2\2\2\u074f\u0750\3\2\2\2\u0750\u0752\3\2\2\2\u0751\u074f\3\2"+
		"\2\2\u0752\u0753\7x\2\2\u0753\u00a9\3\2\2\2\u0754\u0758\7w\2\2\u0755\u0757"+
		"\5\u00b2Z\2\u0756\u0755\3\2\2\2\u0757\u075a\3\2\2\2\u0758\u0756\3\2\2"+
		"\2\u0758\u0759\3\2\2\2\u0759\u075b\3\2\2\2\u075a\u0758\3\2\2\2\u075b\u075c"+
		"\7x\2\2\u075c\u00ab\3\2\2\2\u075d\u075e\5\u009eP\2\u075e\u075f\5l\67\2"+
		"\u075f\u00ad\3\2\2\2\u0760\u0761\5\u009eP\2\u0761\u0762\5n8\2\u0762\u00af"+
		"\3\2\2\2\u0763\u0764\5\u00c2b\2\u0764\u0765\5\u00ba^\2\u0765\u0766\7{"+
		"\2\2\u0766\u00b1\3\2\2\2\u0767\u0769\5\u0098M\2\u0768\u0767\3\2\2\2\u0769"+
		"\u076c\3\2\2\2\u076a\u0768\3\2\2\2\u076a\u076b\3\2\2\2\u076b\u076d\3\2"+
		"\2\2\u076c\u076a\3\2\2\2\u076d\u0770\5\u00b4[\2\u076e\u0770\7{\2\2\u076f"+
		"\u076a\3\2\2\2\u076f\u076e\3\2\2\2\u0770\u00b3\3\2\2\2\u0771\u0779\5\u00b6"+
		"\\\2\u0772\u0779\5r:\2\u0773\u0779\5\u00b8]\2\u0774\u0779\5j\66\2\u0775"+
		"\u0779\5~@\2\u0776\u0779\5b\62\2\u0777\u0779\5f\64\2\u0778\u0771\3\2\2"+
		"\2\u0778\u0772\3\2\2\2\u0778\u0773\3\2\2\2\u0778\u0774\3\2\2\2\u0778\u0775"+
		"\3\2\2\2\u0778\u0776\3\2\2\2\u0778\u0777\3\2\2\2\u0779\u00b5\3\2\2\2\u077a"+
		"\u077b\5\u00c2b\2\u077b\u0780\5p9\2\u077c\u077d\7|\2\2\u077d\u077f\5p"+
		"9\2\u077e\u077c\3\2\2\2\u077f\u0782\3\2\2\2\u0780\u077e\3\2\2\2\u0780"+
		"\u0781\3\2\2\2\u0781\u0783\3\2\2\2\u0782\u0780\3\2\2\2\u0783\u0784\7{"+
		"\2\2\u0784\u00b7\3\2\2\2\u0785\u0786\5\u009eP\2\u0786\u0787\5r:\2\u0787"+
		"\u00b9\3\2\2\2\u0788\u078d\5\u00bc_\2\u0789\u078a\7|\2\2\u078a\u078c\5"+
		"\u00bc_\2\u078b\u0789\3\2\2\2\u078c\u078f\3\2\2\2\u078d\u078b\3\2\2\2"+
		"\u078d\u078e\3\2\2\2\u078e\u00bb\3\2\2\2\u078f\u078d\3\2\2\2\u0790\u0793"+
		"\5t;\2\u0791\u0792\7~\2\2\u0792\u0794\5\u00be`\2\u0793\u0791\3\2\2\2\u0793"+
		"\u0794\3\2\2\2\u0794\u00bd\3\2\2\2\u0795\u0798\5\u00c0a\2\u0796\u0798"+
		"\5\u0086D\2\u0797\u0795\3\2\2\2\u0797\u0796\3\2\2\2\u0798\u00bf\3\2\2"+
		"\2\u0799\u07a5\7w\2\2\u079a\u079f\5\u00be`\2\u079b\u079c\7|\2\2\u079c"+
		"\u079e\5\u00be`\2\u079d\u079b\3\2\2\2\u079e\u07a1\3\2\2\2\u079f\u079d"+
		"\3\2\2\2\u079f\u07a0\3\2\2\2\u07a0\u07a3\3\2\2\2\u07a1\u079f\3\2\2\2\u07a2"+
		"\u07a4\7|\2\2\u07a3\u07a2\3\2\2\2\u07a3\u07a4\3\2\2\2\u07a4\u07a6\3\2"+
		"\2\2\u07a5\u079a\3\2\2\2\u07a5\u07a6\3\2\2\2\u07a6\u07a7\3\2\2\2\u07a7"+
		"\u07a8\7x\2\2\u07a8\u00c1\3\2\2\2\u07a9\u07ae\5x=\2\u07aa\u07ab\7y\2\2"+
		"\u07ab\u07ad\7z\2\2\u07ac\u07aa\3\2\2\2\u07ad\u07b0\3\2\2\2\u07ae\u07ac"+
		"\3\2\2\2\u07ae\u07af\3\2\2\2\u07af\u07ba\3\2\2\2\u07b0\u07ae\3\2\2\2\u07b1"+
		"\u07b6\5\u00c4c\2\u07b2\u07b3\7y\2\2\u07b3\u07b5\7z\2\2\u07b4\u07b2\3"+
		"\2\2\2\u07b5\u07b8\3\2\2\2\u07b6\u07b4\3\2\2\2\u07b6\u07b7\3\2\2\2\u07b7"+
		"\u07ba\3\2\2\2\u07b8\u07b6\3\2\2\2\u07b9\u07a9\3\2\2\2\u07b9\u07b1\3\2"+
		"\2\2\u07ba\u00c3\3\2\2\2\u07bb\u07bc\t\22\2\2\u07bc\u00c5\3\2\2\2\u07bd"+
		"\u07be\7\u0080\2\2\u07be\u07c3\5\u00c8e\2\u07bf\u07c0\7|\2\2\u07c0\u07c2"+
		"\5\u00c8e\2\u07c1\u07bf\3\2\2\2\u07c2\u07c5\3\2\2\2\u07c3\u07c1\3\2\2"+
		"\2\u07c3\u07c4\3\2\2\2\u07c4\u07c6\3\2\2\2\u07c5\u07c3\3\2\2\2\u07c6\u07c7"+
		"\7\177\2\2\u07c7\u00c7\3\2\2\2\u07c8\u07cf\5\u00c2b\2\u07c9\u07cc\7\u0083"+
		"\2\2\u07ca\u07cb\t\23\2\2\u07cb\u07cd\5\u00c2b\2\u07cc\u07ca\3\2\2\2\u07cc"+
		"\u07cd\3\2\2\2\u07cd\u07cf\3\2\2\2\u07ce\u07c8\3\2\2\2\u07ce\u07c9\3\2"+
		"\2\2\u07cf\u00c9\3\2\2\2\u07d0\u07d5\5z>\2\u07d1\u07d2\7|\2\2\u07d2\u07d4"+
		"\5z>\2\u07d3\u07d1\3\2\2\2\u07d4\u07d7\3\2\2\2\u07d5\u07d3\3\2\2\2\u07d5"+
		"\u07d6\3\2\2\2\u07d6\u00cb\3\2\2\2\u07d7\u07d5\3\2\2\2\u07d8\u07da\7u"+
		"\2\2\u07d9\u07db\5\u00ceh\2\u07da\u07d9\3\2\2\2\u07da\u07db\3\2\2\2\u07db"+
		"\u07dc\3\2\2\2\u07dc\u07dd\7v\2\2\u07dd\u00cd\3\2\2\2\u07de\u07e3\5\u00d0"+
		"i\2\u07df\u07e0\7|\2\2\u07e0\u07e2\5\u00d0i\2\u07e1\u07df\3\2\2\2\u07e2"+
		"\u07e5\3\2\2\2\u07e3\u07e1\3\2\2\2\u07e3\u07e4\3\2\2\2\u07e4\u07e8\3\2"+
		"\2\2\u07e5\u07e3\3\2\2\2\u07e6\u07e7\7|\2\2\u07e7\u07e9\5\u00d2j\2\u07e8"+
		"\u07e6\3\2\2\2\u07e8\u07e9\3\2\2\2\u07e9\u07ec\3\2\2\2\u07ea\u07ec\5\u00d2"+
		"j\2\u07eb\u07de\3\2\2\2\u07eb\u07ea\3\2\2\2\u07ec\u00cf\3\2\2\2\u07ed"+
		"\u07ef\5\u009cO\2\u07ee\u07ed\3\2\2\2\u07ef\u07f2\3\2\2\2\u07f0\u07ee"+
		"\3\2\2\2\u07f0\u07f1\3\2\2\2\u07f1\u07f3\3\2\2\2\u07f2\u07f0\3\2\2\2\u07f3"+
		"\u07f4\5\u00c2b\2\u07f4\u07f5\5t;\2\u07f5\u00d1\3\2\2\2\u07f6\u07f8\5"+
		"\u009cO\2\u07f7\u07f6\3\2\2\2\u07f8\u07fb\3\2\2\2\u07f9\u07f7\3\2\2\2"+
		"\u07f9\u07fa\3\2\2\2\u07fa\u07fc\3\2\2\2\u07fb\u07f9\3\2\2\2\u07fc\u07fd"+
		"\5\u00c2b\2\u07fd\u07fe\7\u00a1\2\2\u07fe\u07ff\5t;\2\u07ff\u00d3\3\2"+
		"\2\2\u0800\u0801\5\u00eex\2\u0801\u00d5\3\2\2\2\u0802\u0803\5\u00eex\2"+
		"\u0803\u00d7\3\2\2\2\u0804\u0805\t\24\2\2\u0805\u00d9\3\2\2\2\u0806\u0807"+
		"\5z>\2\u0807\u00db\3\2\2\2\u0808\u080d\5|?\2\u0809\u080a\7|\2\2\u080a"+
		"\u080c\5|?\2\u080b\u0809\3\2\2\2\u080c\u080f\3\2\2\2\u080d\u080b\3\2\2"+
		"\2\u080d\u080e\3\2\2\2\u080e\u00dd\3\2\2\2\u080f\u080d\3\2\2\2\u0810\u0814"+
		"\5\u0086D\2\u0811\u0814\5\f\7\2\u0812\u0814\5\u00e0q\2\u0813\u0810\3\2"+
		"\2\2\u0813\u0811\3\2\2\2\u0813\u0812\3\2\2\2\u0814\u00df\3\2\2\2\u0815"+
		"\u081e\7w\2\2\u0816\u081b\5\u00dep\2\u0817\u0818\7|\2\2\u0818\u081a\5"+
		"\u00dep\2\u0819\u0817\3\2\2\2\u081a\u081d\3\2\2\2\u081b\u0819\3\2\2\2"+
		"\u081b\u081c\3\2\2\2\u081c\u081f\3\2\2\2\u081d\u081b\3\2\2\2\u081e\u0816"+
		"\3\2\2\2\u081e\u081f\3\2\2\2\u081f\u0821\3\2\2\2\u0820\u0822\7|\2\2\u0821"+
		"\u0820\3\2\2\2\u0821\u0822\3\2\2\2\u0822\u0823\3\2\2\2\u0823\u0824\7x"+
		"\2\2\u0824\u00e1\3\2\2\2\u0825\u0829\7w\2\2\u0826\u0828\5\u00e4s\2\u0827"+
		"\u0826\3\2\2\2\u0828\u082b\3\2\2\2\u0829\u0827\3\2\2\2\u0829\u082a\3\2"+
		"\2\2\u082a\u082c\3\2\2\2\u082b\u0829\3\2\2\2\u082c\u082d\7x\2\2\u082d"+
		"\u00e3\3\2\2\2\u082e\u0830\5\u0098M\2\u082f\u082e\3\2\2\2\u0830\u0833"+
		"\3\2\2\2\u0831\u082f\3\2\2\2\u0831\u0832\3\2\2\2\u0832\u0834\3\2\2\2\u0833"+
		"\u0831\3\2\2\2\u0834\u0837\5\u00e6t\2\u0835\u0837\7{\2\2\u0836\u0831\3"+
		"\2\2\2\u0836\u0835\3\2\2\2\u0837\u00e5\3\2\2\2\u0838\u0839\5\u00c2b\2"+
		"\u0839\u083a\5\u00e8u\2\u083a\u083b\7{\2\2\u083b\u084d\3\2\2\2\u083c\u083e"+
		"\5b\62\2\u083d\u083f\7{\2\2\u083e\u083d\3\2\2\2\u083e\u083f\3\2\2\2\u083f"+
		"\u084d\3\2\2\2\u0840\u0842\5j\66\2\u0841\u0843\7{\2\2\u0842\u0841\3\2"+
		"\2\2\u0842\u0843\3\2\2\2\u0843\u084d\3\2\2\2\u0844\u0846\5f\64\2\u0845"+
		"\u0847\7{\2\2\u0846\u0845\3\2\2\2\u0846\u0847\3\2\2\2\u0847\u084d\3\2"+
		"\2\2\u0848\u084a\5~@\2\u0849\u084b\7{\2\2\u084a\u0849\3\2\2\2\u084a\u084b"+
		"\3\2\2\2\u084b\u084d\3\2\2\2\u084c\u0838\3\2\2\2\u084c\u083c\3\2\2\2\u084c"+
		"\u0840\3\2\2\2\u084c\u0844\3\2\2\2\u084c\u0848\3\2\2\2\u084d\u00e7\3\2"+
		"\2\2\u084e\u0851\5\u0080A\2\u084f\u0851\5\u00eav\2\u0850\u084e\3\2\2\2"+
		"\u0850\u084f\3\2\2\2\u0851\u00e9\3\2\2\2\u0852\u0853\5\u00ba^\2\u0853"+
		"\u00eb\3\2\2\2\u0854\u0855\7H\2\2\u0855\u0856\5\u00dep\2\u0856\u00ed\3"+
		"\2\2\2\u0857\u085b\7w\2\2\u0858\u085a\5\u00f0y\2\u0859\u0858\3\2\2\2\u085a"+
		"\u085d\3\2\2\2\u085b\u0859\3\2\2\2\u085b\u085c\3\2\2\2\u085c\u085e\3\2"+
		"\2\2\u085d\u085b\3\2\2\2\u085e\u085f\7x\2\2\u085f\u00ef\3\2\2\2\u0860"+
		"\u0864\5\u00f2z\2\u0861\u0864\5\u0082B\2\u0862\u0864\5\2\2\2\u0863\u0860"+
		"\3\2\2\2\u0863\u0861\3\2\2\2\u0863\u0862\3\2\2\2\u0864\u00f1\3\2\2\2\u0865"+
		"\u0866\5\u00f4{\2\u0866\u0867\7{\2\2\u0867\u00f3\3\2\2\2\u0868\u086a\5"+
		"\u009cO\2\u0869\u0868\3\2\2\2\u086a\u086d\3\2\2\2\u086b\u0869\3\2\2\2"+
		"\u086b\u086c\3\2\2\2\u086c\u086e\3\2\2\2\u086d\u086b\3\2\2\2\u086e\u086f"+
		"\5\u00c2b\2\u086f\u0870\5\u00ba^\2\u0870\u00f5\3\2\2\2\u0871\u0876\5z"+
		">\2\u0872\u0873\7\u0092\2\2\u0873\u0875\5z>\2\u0874\u0872\3\2\2\2\u0875"+
		"\u0878\3\2\2\2\u0876\u0874\3\2\2\2\u0876\u0877\3\2\2\2\u0877\u00f7\3\2"+
		"\2\2\u0878\u0876\3\2\2\2\u0879\u087a\7O\2\2\u087a\u087b\5\u00eex\2\u087b"+
		"\u00f9\3\2\2\2\u087c\u087d\7u\2\2\u087d\u087f\5\u00fc\177\2\u087e\u0880"+
		"\7{\2\2\u087f\u087e\3\2\2\2\u087f\u0880\3\2\2\2\u0880\u0881\3\2\2\2\u0881"+
		"\u0882\7v\2\2\u0882\u00fb\3\2\2\2\u0883\u0888\5\u00fe\u0080\2\u0884\u0885"+
		"\7{\2\2\u0885\u0887\5\u00fe\u0080\2\u0886\u0884\3\2\2\2\u0887\u088a\3"+
		"\2\2\2\u0888\u0886\3\2\2\2\u0888\u0889\3\2\2\2\u0889\u00fd\3\2\2\2\u088a"+
		"\u0888\3\2\2\2\u088b\u088d\5\u009cO\2\u088c\u088b\3\2\2\2\u088d\u0890"+
		"\3\2\2\2\u088e\u088c\3\2\2\2\u088e\u088f\3\2\2\2\u088f\u0891\3\2\2\2\u0890"+
		"\u088e\3\2\2\2\u0891\u0892\5x=\2\u0892\u0893\5t;\2\u0893\u0894\7~\2\2"+
		"\u0894\u0895\5\u0086D\2\u0895\u00ff\3\2\2\2\u0896\u0898\5\u0102\u0082"+
		"\2\u0897\u0896\3\2\2\2\u0898\u0899\3\2\2\2\u0899\u0897\3\2\2\2\u0899\u089a"+
		"\3\2\2\2\u089a\u089c\3\2\2\2\u089b\u089d\5\u00f0y\2\u089c\u089b\3\2\2"+
		"\2\u089d\u089e\3\2\2\2\u089e\u089c\3\2\2\2\u089e\u089f\3\2\2\2\u089f\u0101"+
		"\3\2\2\2\u08a0\u08a1\7B\2\2\u08a1\u08a2\5\u0112\u008a\2\u08a2\u08a3\7"+
		"\u0084\2\2\u08a3\u08ab\3\2\2\2\u08a4\u08a5\7B\2\2\u08a5\u08a6\5v<\2\u08a6"+
		"\u08a7\7\u0084\2\2\u08a7\u08ab\3\2\2\2\u08a8\u08a9\7H\2\2\u08a9\u08ab"+
		"\7\u0084\2\2\u08aa\u08a0\3\2\2\2\u08aa\u08a4\3\2\2\2\u08aa\u08a8\3\2\2"+
		"\2\u08ab\u0103\3\2\2\2\u08ac\u08b9\5\u0108\u0085\2\u08ad\u08af\5\u0106"+
		"\u0084\2\u08ae\u08ad\3\2\2\2\u08ae\u08af\3\2\2\2\u08af\u08b0\3\2\2\2\u08b0"+
		"\u08b2\7{\2\2\u08b1\u08b3\5\u0086D\2\u08b2\u08b1\3\2\2\2\u08b2\u08b3\3"+
		"\2\2\2\u08b3\u08b4\3\2\2\2\u08b4\u08b6\7{\2\2\u08b5\u08b7\5\u010a\u0086"+
		"\2\u08b6\u08b5\3\2\2\2\u08b6\u08b7\3\2\2\2\u08b7\u08b9\3\2\2\2\u08b8\u08ac"+
		"\3\2\2\2\u08b8\u08ae\3\2\2\2\u08b9\u0105\3\2\2\2\u08ba\u08bd\5\u00f4{"+
		"\2\u08bb\u08bd\5\u010e\u0088\2\u08bc\u08ba\3\2\2\2\u08bc\u08bb\3\2\2\2"+
		"\u08bd\u0107\3\2\2\2\u08be\u08c0\5\u009cO\2\u08bf\u08be\3\2\2\2\u08c0"+
		"\u08c3\3\2\2\2\u08c1\u08bf\3\2\2\2\u08c1\u08c2\3\2\2\2\u08c2\u08c4\3\2"+
		"\2\2\u08c3\u08c1\3\2\2\2\u08c4\u08c5\5\u00c2b\2\u08c5\u08c6\5t;\2\u08c6"+
		"\u08c7\7\u0084\2\2\u08c7\u08c8\5\u0086D\2\u08c8\u0109\3\2\2\2\u08c9\u08ca"+
		"\5\u010e\u0088\2\u08ca\u010b\3\2\2\2\u08cb\u08cc\7u\2\2\u08cc\u08cd\5"+
		"\u0086D\2\u08cd\u08ce\7v\2\2\u08ce\u010d\3\2\2\2\u08cf\u08d4\5\u0086D"+
		"\2\u08d0\u08d1\7|\2\2\u08d1\u08d3\5\u0086D\2\u08d2\u08d0\3\2\2\2\u08d3"+
		"\u08d6\3\2\2\2\u08d4\u08d2\3\2\2\2\u08d4\u08d5\3\2\2\2\u08d5\u010f\3\2"+
		"\2\2\u08d6\u08d4\3\2\2\2\u08d7\u08d8\5\u0086D\2\u08d8\u0111\3\2\2\2\u08d9"+
		"\u08da\5\u0086D\2\u08da\u0113\3\2\2\2\u08db\u08dc\5\u011c\u008f\2\u08dc"+
		"\u08dd\5\u008aF\2\u08dd\u08de\5\u0118\u008d\2\u08de\u08e5\3\2\2\2\u08df"+
		"\u08e2\5\u008aF\2\u08e0\u08e3\5\u0116\u008c\2\u08e1\u08e3\5\u0118\u008d"+
		"\2\u08e2\u08e0\3\2\2\2\u08e2\u08e1\3\2\2\2\u08e3\u08e5\3\2\2\2\u08e4\u08db"+
		"\3\2\2\2\u08e4\u08df\3\2\2\2\u08e5\u0115\3\2\2\2\u08e6\u0902\7y\2\2\u08e7"+
		"\u08ec\7z\2\2\u08e8\u08e9\7y\2\2\u08e9\u08eb\7z\2\2\u08ea\u08e8\3\2\2"+
		"\2\u08eb\u08ee\3\2\2\2\u08ec\u08ea\3\2\2\2\u08ec\u08ed\3\2\2\2\u08ed\u08ef"+
		"\3\2\2\2\u08ee\u08ec\3\2\2\2\u08ef\u0903\5\u00c0a\2\u08f0\u08f1\5\u0086"+
		"D\2\u08f1\u08f8\7z\2\2\u08f2\u08f3\7y\2\2\u08f3\u08f4\5\u0086D\2\u08f4"+
		"\u08f5\7z\2\2\u08f5\u08f7\3\2\2\2\u08f6\u08f2\3\2\2\2\u08f7\u08fa\3\2"+
		"\2\2\u08f8\u08f6\3\2\2\2\u08f8\u08f9\3\2\2\2\u08f9\u08ff\3\2\2\2\u08fa"+
		"\u08f8\3\2\2\2\u08fb\u08fc\7y\2\2\u08fc\u08fe\7z\2\2\u08fd\u08fb\3\2\2"+
		"\2\u08fe\u0901\3\2\2\2\u08ff\u08fd\3\2\2\2\u08ff\u0900\3\2\2\2\u0900\u0903"+
		"\3\2\2\2\u0901\u08ff\3\2\2\2\u0902\u08e7\3\2\2\2\u0902\u08f0\3\2\2\2\u0903"+
		"\u0117\3\2\2\2\u0904\u0906\5\u0122\u0092\2\u0905\u0907\5\u00a8U\2\u0906"+
		"\u0905\3\2\2\2\u0906\u0907\3\2\2\2\u0907\u0119\3\2\2\2\u0908\u0909\5\u011c"+
		"\u008f\2\u0909\u090a\5\u0090I\2\u090a\u011b\3\2\2\2\u090b\u090c\7\u0080"+
		"\2\2\u090c\u090d\5\u00a6T\2\u090d\u090e\7\177\2\2\u090e\u011d\3\2\2\2"+
		"\u090f\u0910\7\u0080\2\2\u0910\u0913\7\177\2\2\u0911\u0913\5\u00c6d\2"+
		"\u0912\u090f\3\2\2\2\u0912\u0911\3\2\2\2\u0913\u011f\3\2\2\2\u0914\u0915"+
		"\7\u0080\2\2\u0915\u0918\7\177\2\2\u0916\u0918\5\u011c\u008f\2\u0917\u0914"+
		"\3\2\2\2\u0917\u0916\3\2\2\2\u0918\u0121\3\2\2\2\u0919\u091b\7u\2\2\u091a"+
		"\u091c\5\u010e\u0088\2\u091b\u091a\3\2\2\2\u091b\u091c\3\2\2\2\u091c\u091d"+
		"\3\2\2\2\u091d\u091e\7v\2\2\u091e\u0123\3\2\2\2\u0106\u0127\u012e\u0135"+
		"\u013c\u0143\u0148\u014e\u0155\u015b\u0161\u0167\u0173\u017a\u017d\u01b7"+
		"\u0204\u0208\u020f\u021a\u021e\u0223\u0229\u022d\u0231\u0234\u023b\u023f"+
		"\u0244\u0253\u0256\u025d\u0260\u0264\u0268\u0286\u028c\u0297\u02a1\u02a6"+
		"\u02ae\u02b6\u02b8\u02fd\u0333\u0338\u0340\u0345\u034d\u0354\u035b\u0360"+
		"\u0368\u036f\u0378\u037f\u0384\u038b\u038f\u0395\u03a0\u03ab\u03b6\u03c1"+
		"\u03cc\u03f5\u03fd\u0402\u040a\u040c\u0411\u0417\u041f\u0421\u0424\u0428"+
		"\u042e\u0431\u0434\u0437\u043d\u0442\u0448\u0454\u0458\u0460\u0464\u0466"+
		"\u046a\u046d\u0470\u0476\u047b\u047e\u0484\u048e\u0496\u049c\u04a3\u04a9"+
		"\u04b3\u04b7\u04ba\u04bf\u04c4\u04c7\u04cd\u04d3\u04da\u04e2\u04e6\u04ec"+
		"\u04f0\u04f5\u04fc\u0504\u0507\u050e\u0516\u0519\u051d\u0524\u0529\u052e"+
		"\u0532\u0536\u053d\u0543\u0547\u054a\u054d\u0554\u0559\u055c\u0561\u0565"+
		"\u056b\u0573\u0578\u057c\u0582\u058b\u0593\u059b\u05a0\u05a9\u05b0\u05b5"+
		"\u05b9\u05c1\u05d1\u05d8\u05e1\u05f8\u05fb\u05fe\u0606\u060a\u0612\u0618"+
		"\u0623\u062c\u0631\u063c\u0643\u0658\u0668\u0693\u06a5\u06ad\u06af\u06c5"+
		"\u06c7\u06cb\u06d0\u06d4\u06d8\u06dc\u06e4\u06e6\u06ed\u06f0\u06f5\u06fb"+
		"\u0703\u070c\u0711\u0717\u071b\u071f\u0727\u0731\u0739\u0740\u0748\u074f"+
		"\u0758\u076a\u076f\u0778\u0780\u078d\u0793\u0797\u079f\u07a3\u07a5\u07ae"+
		"\u07b6\u07b9\u07c3\u07cc\u07ce\u07d5\u07da\u07e3\u07e8\u07eb\u07f0\u07f9"+
		"\u080d\u0813\u081b\u081e\u0821\u0829\u0831\u0836\u083e\u0842\u0846\u084a"+
		"\u084c\u0850\u085b\u0863\u086b\u0876\u087f\u0888\u088e\u0899\u089e\u08aa"+
		"\u08ae\u08b2\u08b6\u08b8\u08bc\u08c1\u08d4\u08e2\u08e4\u08ec\u08f8\u08ff"+
		"\u0902\u0906\u0912\u0917\u091b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}