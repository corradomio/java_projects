// Generated from AspectJParser.g4 by ANTLR 4.10.1
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
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
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
				_la = _input.LA(1);
				if (_la==LPAREN) {
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
				_la = _input.LA(1);
				if (_la==LPAREN) {
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
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << ADVICEEXECUTION) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (IF - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (LPAREN - 66)) | (1L << (DOT - 66)) | (1L << (BANG - 66)))) != 0) || _la==MUL || _la==Identifier) {
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
			setState(552);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(550);
				match(EXTENDS);
				setState(551);
				type();
				}
			}

			setState(556);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(554);
				match(IMPLEMENTS);
				setState(555);
				typeList();
				}
			}

			setState(559);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ISSINGLETON) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN))) != 0)) {
				{
				setState(558);
				perClause();
				}
			}

			setState(561);
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
			setState(564);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRICTFP) {
				{
				setState(563);
				match(STRICTFP);
				}
			}

			setState(566);
			adviceSpec();
			setState(569);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(567);
				match(THROWS);
				setState(568);
				typeList();
				}
			}

			setState(571);
			match(COLON);
			setState(572);
			pointcutExpression(0);
			setState(573);
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
			setState(605);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(575);
				match(BEFORE);
				setState(576);
				formalParameters();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(577);
				match(AFTER);
				setState(578);
				formalParameters();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(579);
				match(AFTER);
				setState(580);
				formalParameters();
				setState(581);
				match(RETURNING);
				setState(587);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(582);
					match(LPAREN);
					setState(584);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)))) != 0) || _la==Identifier) {
						{
						setState(583);
						formalParameter();
						}
					}

					setState(586);
					match(RPAREN);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(589);
				match(AFTER);
				setState(590);
				formalParameters();
				setState(591);
				match(THROWING);
				setState(597);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(592);
					match(LPAREN);
					setState(594);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)))) != 0) || _la==Identifier) {
						{
						setState(593);
						formalParameter();
						}
					}

					setState(596);
					match(RPAREN);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(601);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
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
					setState(599);
					type();
					}
					break;
				case VOID:
					{
					setState(600);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(603);
				match(AROUND);
				setState(604);
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
			setState(635);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PERTARGET:
				enterOuterAlt(_localctx, 1);
				{
				setState(607);
				match(PERTARGET);
				setState(608);
				match(LPAREN);
				setState(609);
				pointcutExpression(0);
				setState(610);
				match(RPAREN);
				}
				break;
			case PERTHIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(612);
				match(PERTHIS);
				setState(613);
				match(LPAREN);
				setState(614);
				pointcutExpression(0);
				setState(615);
				match(RPAREN);
				}
				break;
			case PERCFLOW:
				enterOuterAlt(_localctx, 3);
				{
				setState(617);
				match(PERCFLOW);
				setState(618);
				match(LPAREN);
				setState(619);
				pointcutExpression(0);
				setState(620);
				match(RPAREN);
				}
				break;
			case PERCFLOWBELOW:
				enterOuterAlt(_localctx, 4);
				{
				setState(622);
				match(PERCFLOWBELOW);
				setState(623);
				match(LPAREN);
				setState(624);
				pointcutExpression(0);
				setState(625);
				match(RPAREN);
				}
				break;
			case PERTYPEWITHIN:
				enterOuterAlt(_localctx, 5);
				{
				setState(627);
				match(PERTYPEWITHIN);
				setState(628);
				match(LPAREN);
				setState(629);
				typePattern(0);
				setState(630);
				match(RPAREN);
				}
				break;
			case ISSINGLETON:
				enterOuterAlt(_localctx, 6);
				{
				setState(632);
				match(ISSINGLETON);
				setState(633);
				match(LPAREN);
				setState(634);
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
			setState(662);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(637);
				match(ABSTRACT);
				setState(641);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(638);
					modifier();
					}
					}
					setState(643);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(644);
				match(POINTCUT);
				setState(645);
				id();
				setState(646);
				formalParameters();
				setState(647);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(649);
					modifier();
					}
					}
					setState(654);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(655);
				match(POINTCUT);
				setState(656);
				id();
				setState(657);
				formalParameters();
				setState(658);
				match(COLON);
				setState(659);
				pointcutExpression(0);
				setState(660);
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
			setState(675);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(667);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(665);
					pointcutPrimitive();
					}
					break;
				case 2:
					{
					setState(666);
					referencePointcut();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(669);
				match(BANG);
				setState(670);
				pointcutExpression(4);
				}
				break;
			case 3:
				{
				setState(671);
				match(LPAREN);
				setState(672);
				pointcutExpression(0);
				setState(673);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(685);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(683);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						_localctx = new PointcutExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_pointcutExpression);
						setState(677);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(678);
						match(AND);
						setState(679);
						pointcutExpression(3);
						}
						break;
					case 2:
						{
						_localctx = new PointcutExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_pointcutExpression);
						setState(680);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(681);
						match(OR);
						setState(682);
						pointcutExpression(2);
						}
						break;
					}
					} 
				}
				setState(687);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
			setState(808);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				_localctx = new CallPointcutContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(688);
				match(CALL);
				setState(689);
				match(LPAREN);
				setState(690);
				methodOrConstructorPattern();
				setState(691);
				match(RPAREN);
				}
				break;
			case 2:
				_localctx = new ExecutionPointcutContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(693);
				match(EXECUTION);
				setState(694);
				match(LPAREN);
				setState(695);
				methodOrConstructorPattern();
				setState(696);
				match(RPAREN);
				}
				break;
			case 3:
				_localctx = new InitializationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(698);
				match(INITIALIZATION);
				setState(699);
				match(LPAREN);
				setState(700);
				constructorPattern();
				setState(701);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new PreInitializationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(703);
				match(PREINITIALIZATION);
				setState(704);
				match(LPAREN);
				setState(705);
				constructorPattern();
				setState(706);
				match(RPAREN);
				}
				break;
			case 5:
				_localctx = new StaticInitializationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(708);
				match(STATICINITIALIZATION);
				setState(709);
				match(LPAREN);
				setState(710);
				optionalParensTypePattern();
				setState(711);
				match(RPAREN);
				}
				break;
			case 6:
				_localctx = new GetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(713);
				match(GET);
				setState(714);
				match(LPAREN);
				setState(715);
				fieldPattern();
				setState(716);
				match(RPAREN);
				}
				break;
			case 7:
				_localctx = new SetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(718);
				match(SET);
				setState(719);
				match(LPAREN);
				setState(720);
				fieldPattern();
				setState(721);
				match(RPAREN);
				}
				break;
			case 8:
				_localctx = new HandlerPointcutContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(723);
				match(HANDLER);
				setState(724);
				match(LPAREN);
				setState(725);
				optionalParensTypePattern();
				setState(726);
				match(RPAREN);
				}
				break;
			case 9:
				_localctx = new AdviceExecutionPointcutContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(728);
				match(ADVICEEXECUTION);
				setState(729);
				match(LPAREN);
				setState(730);
				match(RPAREN);
				}
				break;
			case 10:
				_localctx = new WithinPointcutContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(731);
				match(WITHIN);
				setState(732);
				match(LPAREN);
				setState(733);
				optionalParensTypePattern();
				setState(734);
				match(RPAREN);
				}
				break;
			case 11:
				_localctx = new WithinCodePointcutContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(736);
				match(WITHINCODE);
				setState(737);
				match(LPAREN);
				setState(738);
				methodOrConstructorPattern();
				setState(739);
				match(RPAREN);
				}
				break;
			case 12:
				_localctx = new CFlowPointcutContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(741);
				match(CFLOW);
				setState(742);
				match(LPAREN);
				setState(743);
				pointcutExpression(0);
				setState(744);
				match(RPAREN);
				}
				break;
			case 13:
				_localctx = new CFlowBelowPointcutContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(746);
				match(CFLOWBELOW);
				setState(747);
				match(LPAREN);
				setState(748);
				pointcutExpression(0);
				setState(749);
				match(RPAREN);
				}
				break;
			case 14:
				_localctx = new IfPointcutContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(751);
				match(IF);
				setState(752);
				match(LPAREN);
				setState(754);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(753);
					expression(0);
					}
				}

				setState(756);
				match(RPAREN);
				}
				break;
			case 15:
				_localctx = new ThisPointcutPointcutContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(757);
				match(THIS);
				setState(758);
				match(LPAREN);
				setState(759);
				typeOrIdentifier();
				setState(760);
				match(RPAREN);
				}
				break;
			case 16:
				_localctx = new TargetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(762);
				match(TARGET);
				setState(763);
				match(LPAREN);
				setState(764);
				typeOrIdentifier();
				setState(765);
				match(RPAREN);
				}
				break;
			case 17:
				_localctx = new ArgsPointcutContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(767);
				match(ARGS);
				setState(768);
				match(LPAREN);
				setState(769);
				argsPatternList();
				setState(770);
				match(RPAREN);
				}
				break;
			case 18:
				_localctx = new AnnotationThisPointcutContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(772);
				match(AT);
				setState(773);
				match(THIS);
				setState(774);
				match(LPAREN);
				setState(775);
				annotationOrIdentifer();
				setState(776);
				match(RPAREN);
				}
				break;
			case 19:
				_localctx = new AnnotationTargetPointcutContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(778);
				match(AT);
				setState(779);
				match(TARGET);
				setState(780);
				match(LPAREN);
				setState(781);
				annotationOrIdentifer();
				setState(782);
				match(RPAREN);
				}
				break;
			case 20:
				_localctx = new AnnotationArgsPointcutContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(784);
				match(AT);
				setState(785);
				match(ARGS);
				setState(786);
				match(LPAREN);
				setState(787);
				annotationsOrIdentifiersPattern();
				setState(788);
				match(RPAREN);
				}
				break;
			case 21:
				_localctx = new AnnotationWithinPointcutContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(790);
				match(AT);
				setState(791);
				match(WITHIN);
				setState(792);
				match(LPAREN);
				setState(793);
				annotationOrIdentifer();
				setState(794);
				match(RPAREN);
				}
				break;
			case 22:
				_localctx = new AnnotationWithinCodePointcutContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(796);
				match(AT);
				setState(797);
				match(WITHINCODE);
				setState(798);
				match(LPAREN);
				setState(799);
				annotationOrIdentifer();
				setState(800);
				match(RPAREN);
				}
				break;
			case 23:
				_localctx = new AnnotationPointcutContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(802);
				match(AT);
				setState(803);
				match(ANNOTATION);
				setState(804);
				match(LPAREN);
				setState(805);
				annotationOrIdentifer();
				setState(806);
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
			setState(813);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(810);
				typePattern(0);
				setState(811);
				match(DOT);
				}
				break;
			}
			setState(815);
			id();
			setState(816);
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
			setState(900);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(821);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(818);
					modifier();
					}
					}
					setState(823);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(826);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
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
					setState(824);
					type();
					}
					break;
				case VOID:
					{
					setState(825);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(828);
				type();
				setState(829);
				match(DOT);
				setState(830);
				id();
				setState(831);
				formalParameters();
				setState(834);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(832);
					match(THROWS);
					setState(833);
					typeList();
					}
				}

				setState(836);
				methodBody();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(841);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(838);
						modifier();
						}
						} 
					}
					setState(843);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				setState(844);
				match(ABSTRACT);
				setState(848);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(845);
					modifier();
					}
					}
					setState(850);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(853);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
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
					setState(851);
					type();
					}
					break;
				case VOID:
					{
					setState(852);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(855);
				type();
				setState(856);
				match(DOT);
				setState(857);
				id();
				setState(858);
				formalParameters();
				setState(861);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(859);
					match(THROWS);
					setState(860);
					typeList();
					}
				}

				setState(863);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(868);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(865);
					modifier();
					}
					}
					setState(870);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(871);
				type();
				setState(872);
				match(DOT);
				setState(873);
				match(NEW);
				setState(874);
				formalParameters();
				setState(877);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THROWS) {
					{
					setState(875);
					match(THROWS);
					setState(876);
					typeList();
					}
				}

				setState(879);
				methodBody();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(884);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (AT - 58)) | (1L << (ABSTRACT - 58)) | (1L << (FINAL - 58)) | (1L << (NATIVE - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)) | (1L << (PUBLIC - 58)) | (1L << (STATIC - 58)) | (1L << (STRICTFP - 58)) | (1L << (SYNCHRONIZED - 58)) | (1L << (TRANSIENT - 58)) | (1L << (VOLATILE - 58)))) != 0)) {
					{
					{
					setState(881);
					modifier();
					}
					}
					setState(886);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(889);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
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
					setState(887);
					type();
					}
					break;
				case VOID:
					{
					setState(888);
					match(VOID);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(891);
				type();
				setState(892);
				match(DOT);
				setState(893);
				id();
				setState(896);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(894);
					match(ASSIGN);
					setState(895);
					expression(0);
					}
				}

				setState(898);
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
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
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
		try {
			setState(984);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(902);
				match(DECLARE);
				setState(903);
				match(PARENTS);
				setState(904);
				match(COLON);
				setState(905);
				typePattern(0);
				setState(906);
				match(EXTENDS);
				setState(907);
				type();
				setState(908);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(910);
				match(DECLARE);
				setState(911);
				match(PARENTS);
				setState(912);
				match(COLON);
				setState(913);
				typePattern(0);
				setState(914);
				match(IMPLEMENTS);
				setState(915);
				typeList();
				setState(916);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(918);
				match(DECLARE);
				setState(919);
				match(WARNING);
				setState(920);
				match(COLON);
				setState(921);
				pointcutExpression(0);
				setState(922);
				match(COLON);
				setState(923);
				match(StringLiteral);
				setState(924);
				match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(926);
				match(DECLARE);
				setState(927);
				match(ERROR);
				setState(928);
				match(COLON);
				setState(929);
				pointcutExpression(0);
				setState(930);
				match(COLON);
				setState(931);
				match(StringLiteral);
				setState(932);
				match(SEMI);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(934);
				match(DECLARE);
				setState(935);
				match(SOFT);
				setState(936);
				match(COLON);
				setState(937);
				type();
				setState(938);
				match(COLON);
				setState(939);
				pointcutExpression(0);
				setState(940);
				match(SEMI);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(942);
				match(DECLARE);
				setState(943);
				match(PRECEDENCE);
				setState(944);
				match(COLON);
				setState(945);
				typePatternList();
				setState(946);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(948);
				match(DECLARE);
				setState(949);
				match(AT);
				setState(950);
				match(ANNOTATION_TYPE);
				setState(951);
				match(COLON);
				setState(952);
				typePattern(0);
				setState(953);
				match(COLON);
				setState(954);
				annotation();
				setState(955);
				match(SEMI);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(957);
				match(DECLARE);
				setState(958);
				match(AT);
				setState(959);
				match(ANNOTATION_METHOD);
				setState(960);
				match(COLON);
				setState(961);
				methodPattern();
				setState(962);
				match(COLON);
				setState(963);
				annotation();
				setState(964);
				match(SEMI);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(966);
				match(DECLARE);
				setState(967);
				match(AT);
				setState(968);
				match(ANNOTATION_CONSTRUCTOR);
				setState(969);
				match(COLON);
				setState(970);
				constructorPattern();
				setState(971);
				match(COLON);
				setState(972);
				annotation();
				setState(973);
				match(SEMI);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(975);
				match(DECLARE);
				setState(976);
				match(AT);
				setState(977);
				match(ANNOTATION_FIELD);
				setState(978);
				match(COLON);
				setState(979);
				fieldPattern();
				setState(980);
				match(COLON);
				setState(981);
				annotation();
				setState(982);
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
			setState(997);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOTDOT:
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
				setState(987);
				simpleTypePattern();
				}
				break;
			case BANG:
				{
				setState(988);
				match(BANG);
				setState(989);
				typePattern(4);
				}
				break;
			case LPAREN:
				{
				setState(990);
				match(LPAREN);
				setState(992);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(991);
					annotationPattern();
					}
					break;
				}
				setState(994);
				typePattern(0);
				setState(995);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(1007);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1005);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						_localctx = new TypePatternContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_typePattern);
						setState(999);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1000);
						match(AND);
						setState(1001);
						typePattern(3);
						}
						break;
					case 2:
						{
						_localctx = new TypePatternContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_typePattern);
						setState(1002);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1003);
						match(OR);
						setState(1004);
						typePattern(2);
						}
						break;
					}
					} 
				}
				setState(1009);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
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
			setState(1010);
			dottedNamePattern();
			setState(1012);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(1011);
				match(ADD);
				}
				break;
			}
			setState(1018);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1014);
					match(LBRACK);
					setState(1015);
					match(RBRACK);
					}
					} 
				}
				setState(1020);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
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
			setState(1031);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOTDOT:
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
				setState(1026); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						setState(1026);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
						case 1:
							{
							setState(1021);
							type();
							}
							break;
						case 2:
							{
							setState(1022);
							id();
							}
							break;
						case 3:
							{
							setState(1023);
							match(MUL);
							}
							break;
						case 4:
							{
							setState(1024);
							match(DOT);
							}
							break;
						case 5:
							{
							setState(1025);
							match(DOTDOT);
							}
							break;
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1028); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(1030);
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
			setState(1044);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1033);
				match(LPAREN);
				setState(1035);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(1034);
					annotationPattern();
					}
					break;
				}
				setState(1037);
				typePattern(0);
				setState(1038);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1041);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(1040);
					annotationPattern();
					}
					break;
				}
				setState(1043);
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
			setState(1047);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(1046);
				annotationPattern();
				}
				break;
			}
			setState(1050);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(1049);
				fieldModifiersPattern();
				}
				break;
			}
			setState(1052);
			typePattern(0);
			setState(1056);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				{
				setState(1053);
				typePattern(0);
				setState(1054);
				dotOrDotDot();
				}
				break;
			}
			setState(1058);
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
			setState(1061);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1060);
				match(BANG);
				}
			}

			setState(1063);
			fieldModifier();
			setState(1067);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1064);
					fieldModifiersPattern();
					}
					} 
				}
				setState(1069);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
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
			setState(1070);
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
			setState(1072);
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
			setState(1097);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1074);
				id();
				setState(1079);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1075);
						match(MUL);
						setState(1076);
						id();
						}
						} 
					}
					setState(1081);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				}
				setState(1083);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MUL) {
					{
					setState(1082);
					match(MUL);
					}
				}

				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1085);
				match(MUL);
				setState(1091);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1086);
						id();
						setState(1087);
						match(MUL);
						}
						} 
					}
					setState(1093);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				}
				setState(1095);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE))) != 0) || _la==Identifier) {
					{
					setState(1094);
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
			setState(1101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1099);
				methodPattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1100);
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
			setState(1104);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(1103);
				annotationPattern();
				}
				break;
			}
			setState(1107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(1106);
				methodModifiersPattern();
				}
				break;
			}
			setState(1109);
			typePattern(0);
			setState(1113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(1110);
				typePattern(0);
				setState(1111);
				dotOrDotDot();
				}
				break;
			}
			setState(1115);
			simpleNamePattern();
			setState(1116);
			formalParametersPattern();
			setState(1118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1117);
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
			setState(1121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1120);
				match(BANG);
				}
			}

			setState(1123);
			methodModifier();
			setState(1127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1124);
					methodModifiersPattern();
					}
					} 
				}
				setState(1129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
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
			setState(1130);
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
			setState(1151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1132);
				match(DOTDOT);
				setState(1137);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1133);
						match(COMMA);
						setState(1134);
						formalsPatternAfterDotDot();
						}
						} 
					}
					setState(1139);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1140);
				optionalParensTypePattern();
				setState(1145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1141);
						match(COMMA);
						setState(1142);
						formalsPattern();
						}
						} 
					}
					setState(1147);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1148);
				typePattern(0);
				setState(1149);
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
			setState(1164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1153);
				optionalParensTypePattern();
				setState(1158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1154);
						match(COMMA);
						setState(1155);
						formalsPatternAfterDotDot();
						}
						} 
					}
					setState(1160);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1161);
				typePattern(0);
				setState(1162);
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
			setState(1166);
			match(THROWS);
			setState(1167);
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
			setState(1169);
			typePattern(0);
			setState(1174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1170);
				match(COMMA);
				setState(1171);
				typePattern(0);
				}
				}
				setState(1176);
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
			setState(1178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(1177);
				annotationPattern();
				}
				break;
			}
			setState(1181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
			case 1:
				{
				setState(1180);
				constructorModifiersPattern();
				}
				break;
			}
			setState(1186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)) | (1L << (VOID - 66)) | (1L << (LPAREN - 66)) | (1L << (DOT - 66)) | (1L << (BANG - 66)))) != 0) || _la==MUL || _la==Identifier) {
				{
				setState(1183);
				typePattern(0);
				setState(1184);
				dotOrDotDot();
				}
			}

			setState(1188);
			match(NEW);
			setState(1189);
			formalParametersPattern();
			setState(1191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1190);
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
			setState(1194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1193);
				match(BANG);
				}
			}

			setState(1196);
			constructorModifier();
			setState(1200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1197);
					constructorModifiersPattern();
					}
					} 
				}
				setState(1202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
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
			setState(1203);
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
			setState(1206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BANG) {
				{
				setState(1205);
				match(BANG);
				}
			}

			setState(1208);
			match(AT);
			setState(1209);
			annotationTypePattern();
			setState(1213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1210);
					annotationPattern();
					}
					} 
				}
				setState(1215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
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
			setState(1221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1216);
				qualifiedName();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(1217);
				match(LPAREN);
				setState(1218);
				typePattern(0);
				setState(1219);
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
			setState(1223);
			match(LPAREN);
			setState(1225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOTDOT) | (1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)) | (1L << (VOID - 66)) | (1L << (LPAREN - 66)) | (1L << (DOT - 66)) | (1L << (BANG - 66)))) != 0) || _la==MUL || _la==Identifier) {
				{
				setState(1224);
				formalsPattern();
				}
			}

			setState(1227);
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
			setState(1231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1229);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1230);
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
			setState(1235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1233);
				qualifiedName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1234);
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
			setState(1258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOTDOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1237);
				match(DOTDOT);
				setState(1240);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(1238);
					match(COMMA);
					setState(1239);
					annotationsOrIdentifiersPatternAfterDotDot();
					}
					break;
				}
				}
				break;
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
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1242);
				annotationOrIdentifer();
				setState(1247);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1243);
						match(COMMA);
						setState(1244);
						annotationsOrIdentifiersPattern();
						}
						} 
					}
					setState(1249);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
				}
				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 3);
				{
				setState(1250);
				match(MUL);
				setState(1255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1251);
						match(COMMA);
						setState(1252);
						annotationsOrIdentifiersPattern();
						}
						} 
					}
					setState(1257);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
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
			setState(1276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1260);
				annotationOrIdentifer();
				setState(1265);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,108,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1261);
						match(COMMA);
						setState(1262);
						annotationsOrIdentifiersPatternAfterDotDot();
						}
						} 
					}
					setState(1267);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,108,_ctx);
				}
				}
				break;
			case MUL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1268);
				match(MUL);
				setState(1273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,109,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1269);
						match(COMMA);
						setState(1270);
						annotationsOrIdentifiersPatternAfterDotDot();
						}
						} 
					}
					setState(1275);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,109,_ctx);
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
			setState(1280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(1278);
				typeOrIdentifier();
				}
				break;
			case DOTDOT:
			case MUL:
				enterOuterAlt(_localctx, 2);
				{
				setState(1279);
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
			setState(1282);
			argsPattern();
			setState(1287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1283);
				match(COMMA);
				setState(1284);
				argsPattern();
				}
				}
				setState(1289);
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
			setState(1292);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				enterOuterAlt(_localctx, 1);
				{
				setState(1290);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE))) != 0)) ) {
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
				setState(1291);
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
			setState(1294);
			match(CLASS);
			setState(1295);
			id();
			setState(1297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1296);
				typeParameters();
				}
			}

			setState(1301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1299);
				match(EXTENDS);
				setState(1300);
				type();
				}
			}

			setState(1305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1303);
				match(IMPLEMENTS);
				setState(1304);
				typeList();
				}
			}

			setState(1307);
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
			setState(1309);
			id();
			setState(1312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1310);
				match(EXTENDS);
				setState(1311);
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
			setState(1314);
			match(ENUM);
			setState(1315);
			id();
			setState(1318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(1316);
				match(IMPLEMENTS);
				setState(1317);
				typeList();
				}
			}

			setState(1320);
			match(LBRACE);
			setState(1322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT))) != 0) || _la==Identifier) {
				{
				setState(1321);
				enumConstants();
				}
			}

			setState(1325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1324);
				match(COMMA);
				}
			}

			setState(1328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1327);
				enumBodyDeclarations();
				}
			}

			setState(1330);
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
			setState(1335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1332);
				annotation();
				}
				}
				setState(1337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1338);
			id();
			setState(1340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(1339);
				arguments();
				}
			}

			setState(1343);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(1342);
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
			setState(1345);
			match(INTERFACE);
			setState(1346);
			id();
			setState(1348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1347);
				typeParameters();
				}
			}

			setState(1352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(1350);
				match(EXTENDS);
				setState(1351);
				typeList();
				}
			}

			setState(1354);
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
			setState(1358);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(1356);
				type();
				}
				break;
			case VOID:
				{
				setState(1357);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1360);
			id();
			setState(1361);
			formalParameters();
			setState(1366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1362);
				match(LBRACK);
				setState(1363);
				match(RBRACK);
				}
				}
				setState(1368);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1371);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1369);
				match(THROWS);
				setState(1370);
				qualifiedNameList();
				}
			}

			setState(1375);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(1373);
				methodBody();
				}
				break;
			case SEMI:
				{
				setState(1374);
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
			setState(1377);
			id();
			setState(1378);
			formalParameters();
			setState(1381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1379);
				match(THROWS);
				setState(1380);
				qualifiedNameList();
				}
			}

			setState(1383);
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
			setState(1385);
			id();
			setState(1390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1386);
				match(LBRACK);
				setState(1387);
				match(RBRACK);
				}
				}
				setState(1392);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1393);
			match(ASSIGN);
			setState(1394);
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
			setState(1398);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(1396);
				type();
				}
				break;
			case VOID:
				{
				setState(1397);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1400);
			id();
			setState(1401);
			formalParameters();
			setState(1406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1402);
				match(LBRACK);
				setState(1403);
				match(RBRACK);
				}
				}
				setState(1408);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(1409);
				match(THROWS);
				setState(1410);
				qualifiedNameList();
				}
			}

			setState(1413);
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
			setState(1415);
			id();
			setState(1420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(1416);
				match(LBRACK);
				setState(1417);
				match(RBRACK);
				}
				}
				setState(1422);
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
			setState(1423);
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
			setState(1425);
			id();
			setState(1427);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				{
				setState(1426);
				typeArguments();
				}
				break;
			}
			setState(1436);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,139,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1429);
					match(DOT);
					setState(1430);
					id();
					setState(1432);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
					case 1:
						{
						setState(1431);
						typeArguments();
						}
						break;
					}
					}
					} 
				}
				setState(1438);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,139,_ctx);
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
			setState(1439);
			id();
			setState(1444);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,140,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1440);
					match(DOT);
					setState(1441);
					id();
					}
					} 
				}
				setState(1446);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,140,_ctx);
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
			setState(1447);
			id();
			setState(1448);
			match(ASSIGN);
			setState(1449);
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
			setState(1451);
			match(AT);
			setState(1452);
			match(INTERFACE);
			setState(1453);
			id();
			setState(1454);
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
			setState(1456);
			id();
			setState(1457);
			match(LPAREN);
			setState(1458);
			match(RPAREN);
			setState(1460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(1459);
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
			setState(1567);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,154,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1462);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1463);
				match(ASSERT);
				setState(1464);
				expression(0);
				setState(1467);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(1465);
					match(COLON);
					setState(1466);
					expression(0);
					}
				}

				setState(1469);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1471);
				match(IF);
				setState(1472);
				parExpression();
				setState(1473);
				statement();
				setState(1476);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,143,_ctx) ) {
				case 1:
					{
					setState(1474);
					match(ELSE);
					setState(1475);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1478);
				match(FOR);
				setState(1479);
				match(LPAREN);
				setState(1480);
				forControl();
				setState(1481);
				match(RPAREN);
				setState(1482);
				statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1484);
				match(WHILE);
				setState(1485);
				parExpression();
				setState(1486);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1488);
				match(DO);
				setState(1489);
				statement();
				setState(1490);
				match(WHILE);
				setState(1491);
				parExpression();
				setState(1492);
				match(SEMI);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1494);
				match(TRY);
				setState(1495);
				block();
				setState(1505);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(1497); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(1496);
						catchClause();
						}
						}
						setState(1499); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==CATCH );
					setState(1502);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(1501);
						finallyBlock();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(1504);
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
				setState(1507);
				match(TRY);
				setState(1508);
				resourceSpecification();
				setState(1509);
				block();
				setState(1513);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(1510);
					catchClause();
					}
					}
					setState(1515);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1517);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(1516);
					finallyBlock();
					}
				}

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1519);
				match(SWITCH);
				setState(1520);
				parExpression();
				setState(1521);
				match(LBRACE);
				setState(1525);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,149,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1522);
						switchBlockStatementGroup();
						}
						} 
					}
					setState(1527);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,149,_ctx);
				}
				setState(1531);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(1528);
					switchLabel();
					}
					}
					setState(1533);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1534);
				match(RBRACE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1536);
				match(SYNCHRONIZED);
				setState(1537);
				parExpression();
				setState(1538);
				block();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1540);
				match(RETURN);
				setState(1542);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(1541);
					expression(0);
					}
				}

				setState(1544);
				match(SEMI);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1545);
				match(THROW);
				setState(1546);
				expression(0);
				setState(1547);
				match(SEMI);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1549);
				match(BREAK);
				setState(1551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE))) != 0) || _la==Identifier) {
					{
					setState(1550);
					id();
					}
				}

				setState(1553);
				match(SEMI);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1554);
				match(CONTINUE);
				setState(1556);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE))) != 0) || _la==Identifier) {
					{
					setState(1555);
					id();
					}
				}

				setState(1558);
				match(SEMI);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1559);
				match(SEMI);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1560);
				statementExpression();
				setState(1561);
				match(SEMI);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1563);
				id();
				setState(1564);
				match(COLON);
				setState(1565);
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
			setState(1569);
			match(CATCH);
			setState(1570);
			match(LPAREN);
			setState(1574);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(1571);
				variableModifier();
				}
				}
				setState(1576);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1577);
			catchType();
			setState(1578);
			id();
			setState(1579);
			match(RPAREN);
			setState(1580);
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
			setState(1595);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,156,_ctx) ) {
			case 1:
				{
				setState(1583);
				primary();
				}
				break;
			case 2:
				{
				setState(1584);
				match(NEW);
				setState(1585);
				creator();
				}
				break;
			case 3:
				{
				setState(1586);
				match(LPAREN);
				setState(1587);
				type();
				setState(1588);
				match(RPAREN);
				setState(1589);
				expression(17);
				}
				break;
			case 4:
				{
				setState(1591);
				_la = _input.LA(1);
				if ( !(((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1592);
				expression(15);
				}
				break;
			case 5:
				{
				setState(1593);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==TILDE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1594);
				expression(14);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1682);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,161,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1680);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,160,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1597);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1598);
						_la = _input.LA(1);
						if ( !(((((_la - 141)) & ~0x3f) == 0 && ((1L << (_la - 141)) & ((1L << (MUL - 141)) | (1L << (DIV - 141)) | (1L << (MOD - 141)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1599);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1600);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1601);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1602);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1603);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1611);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,157,_ctx) ) {
						case 1:
							{
							setState(1604);
							match(LT);
							setState(1605);
							match(LT);
							}
							break;
						case 2:
							{
							setState(1606);
							match(GT);
							setState(1607);
							match(GT);
							setState(1608);
							match(GT);
							}
							break;
						case 3:
							{
							setState(1609);
							match(GT);
							setState(1610);
							match(GT);
							}
							break;
						}
						setState(1613);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1614);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1615);
						_la = _input.LA(1);
						if ( !(((((_la - 125)) & ~0x3f) == 0 && ((1L << (_la - 125)) & ((1L << (GT - 125)) | (1L << (LT - 125)) | (1L << (LE - 125)) | (1L << (GE - 125)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1616);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1617);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1618);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1619);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1620);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1621);
						match(BITAND);
						setState(1622);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1623);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1624);
						match(CARET);
						setState(1625);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1626);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1627);
						match(BITOR);
						setState(1628);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1629);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1630);
						match(AND);
						setState(1631);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1632);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1633);
						match(OR);
						setState(1634);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1635);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1636);
						match(QUESTION);
						setState(1637);
						expression(0);
						setState(1638);
						match(COLON);
						setState(1639);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1641);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1642);
						_la = _input.LA(1);
						if ( !(((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & ((1L << (ASSIGN - 124)) | (1L << (ADD_ASSIGN - 124)) | (1L << (SUB_ASSIGN - 124)) | (1L << (MUL_ASSIGN - 124)) | (1L << (DIV_ASSIGN - 124)) | (1L << (AND_ASSIGN - 124)) | (1L << (OR_ASSIGN - 124)) | (1L << (XOR_ASSIGN - 124)) | (1L << (MOD_ASSIGN - 124)) | (1L << (LSHIFT_ASSIGN - 124)) | (1L << (RSHIFT_ASSIGN - 124)) | (1L << (URSHIFT_ASSIGN - 124)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1643);
						expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1644);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1645);
						match(DOT);
						setState(1646);
						id();
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1647);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(1648);
						match(DOT);
						setState(1649);
						match(THIS);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1650);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1651);
						match(DOT);
						setState(1652);
						match(NEW);
						setState(1654);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==LT) {
							{
							setState(1653);
							nonWildcardTypeArguments();
							}
						}

						setState(1656);
						innerCreator();
						}
						break;
					case 16:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1657);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(1658);
						match(DOT);
						setState(1659);
						match(SUPER);
						setState(1660);
						superSuffix();
						}
						break;
					case 17:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1661);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(1662);
						match(DOT);
						setState(1663);
						explicitGenericInvocation();
						}
						break;
					case 18:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1664);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1665);
						match(LBRACK);
						setState(1666);
						expression(0);
						setState(1667);
						match(RBRACK);
						}
						break;
					case 19:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1669);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1670);
						match(LPAREN);
						setState(1672);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
							{
							setState(1671);
							expressionList();
							}
						}

						setState(1674);
						match(RPAREN);
						}
						break;
					case 20:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1675);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1676);
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
						setState(1677);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1678);
						match(INSTANCEOF);
						setState(1679);
						type();
						}
						break;
					}
					} 
				}
				setState(1684);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,161,_ctx);
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
			setState(1706);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,163,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1685);
				match(LPAREN);
				setState(1686);
				expression(0);
				setState(1687);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1689);
				match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1690);
				match(SUPER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1691);
				literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1692);
				id();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1693);
				type();
				setState(1694);
				match(DOT);
				setState(1695);
				match(CLASS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1697);
				match(VOID);
				setState(1698);
				match(DOT);
				setState(1699);
				match(CLASS);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1700);
				nonWildcardTypeArguments();
				setState(1704);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
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
				case SUPER:
				case Identifier:
					{
					setState(1701);
					explicitGenericInvocationSuffix();
					}
					break;
				case THIS:
					{
					setState(1702);
					match(THIS);
					setState(1703);
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
			setState(1723);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1708);
				id();
				setState(1710);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1709);
					typeArgumentsOrDiamond();
					}
				}

				setState(1719);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(1712);
					match(DOT);
					setState(1713);
					id();
					setState(1715);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1714);
						typeArgumentsOrDiamond();
						}
					}

					}
					}
					setState(1721);
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
				setState(1722);
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
			setState(1725);
			id();
			setState(1727);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1726);
				nonWildcardTypeArgumentsOrDiamond();
				}
			}

			setState(1729);
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
			setState(1737);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1731);
				arguments();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1732);
				match(DOT);
				setState(1733);
				id();
				setState(1735);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,169,_ctx) ) {
				case 1:
					{
					setState(1734);
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
			setState(1744);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1739);
				match(SUPER);
				setState(1740);
				superSuffix();
				}
				break;
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
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1741);
				id();
				setState(1742);
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
			setState(1747);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,172,_ctx) ) {
			case 1:
				{
				setState(1746);
				packageDeclaration();
				}
				break;
			}
			setState(1752);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(1749);
				importDeclaration();
				}
				}
				setState(1754);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1758);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASPECT) | (1L << PRIVILEGED) | (1L << AT) | (1L << ABSTRACT))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (CLASS - 67)) | (1L << (ENUM - 67)) | (1L << (FINAL - 67)) | (1L << (INTERFACE - 67)) | (1L << (NATIVE - 67)) | (1L << (PRIVATE - 67)) | (1L << (PROTECTED - 67)) | (1L << (PUBLIC - 67)) | (1L << (STATIC - 67)) | (1L << (STRICTFP - 67)) | (1L << (SYNCHRONIZED - 67)) | (1L << (TRANSIENT - 67)) | (1L << (VOLATILE - 67)) | (1L << (SEMI - 67)))) != 0)) {
				{
				{
				setState(1755);
				typeDeclaration();
				}
				}
				setState(1760);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1761);
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
			setState(1766);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1763);
				annotation();
				}
				}
				setState(1768);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1769);
			match(PACKAGE);
			setState(1770);
			qualifiedName();
			setState(1771);
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
			setState(1773);
			match(IMPORT);
			setState(1775);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(1774);
				match(STATIC);
				}
			}

			setState(1777);
			qualifiedName();
			setState(1780);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(1778);
				match(DOT);
				setState(1779);
				match(MUL);
				}
			}

			setState(1782);
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
			setState(1786);
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
				setState(1784);
				classOrInterfaceModifier();
				}
				break;
			case NATIVE:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1785);
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
			setState(1790);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1788);
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
				setState(1789);
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
			setState(1794);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(1792);
				match(FINAL);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1793);
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
			setState(1796);
			match(LT);
			setState(1797);
			typeParameter();
			setState(1802);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1798);
				match(COMMA);
				setState(1799);
				typeParameter();
				}
				}
				setState(1804);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1805);
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
			setState(1807);
			type();
			setState(1812);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(1808);
				match(BITAND);
				setState(1809);
				type();
				}
				}
				setState(1814);
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
			setState(1815);
			enumConstant();
			setState(1820);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1816);
					match(COMMA);
					setState(1817);
					enumConstant();
					}
					} 
				}
				setState(1822);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,183,_ctx);
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
			setState(1823);
			match(SEMI);
			setState(1827);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(1824);
				classBodyDeclaration();
				}
				}
				setState(1829);
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
			setState(1830);
			type();
			setState(1835);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1831);
				match(COMMA);
				setState(1832);
				type();
				}
				}
				setState(1837);
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
			setState(1838);
			match(LBRACE);
			setState(1842);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(1839);
				classBodyDeclaration();
				}
				}
				setState(1844);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1845);
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
			setState(1847);
			match(LBRACE);
			setState(1851);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(1848);
				interfaceBodyDeclaration();
				}
				}
				setState(1853);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1854);
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
			setState(1856);
			typeParameters();
			setState(1857);
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
			setState(1859);
			typeParameters();
			setState(1860);
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
			setState(1862);
			type();
			setState(1863);
			variableDeclarators();
			setState(1864);
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
			setState(1874);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(1869);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1866);
						modifier();
						}
						} 
					}
					setState(1871);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
				}
				setState(1872);
				interfaceMemberDeclaration();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(1873);
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
			setState(1883);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,190,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1876);
				constDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1877);
				interfaceMethodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1878);
				genericInterfaceMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1879);
				interfaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1880);
				annotationTypeDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1881);
				classDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1882);
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
			setState(1885);
			type();
			setState(1886);
			constantDeclarator();
			setState(1891);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1887);
				match(COMMA);
				setState(1888);
				constantDeclarator();
				}
				}
				setState(1893);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1894);
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
			setState(1896);
			typeParameters();
			setState(1897);
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
			setState(1899);
			variableDeclarator();
			setState(1904);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1900);
				match(COMMA);
				setState(1901);
				variableDeclarator();
				}
				}
				setState(1906);
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
			setState(1907);
			variableDeclaratorId();
			setState(1910);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(1908);
				match(ASSIGN);
				setState(1909);
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
			setState(1914);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1912);
				arrayInitializer();
				}
				break;
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
				setState(1913);
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
			setState(1916);
			match(LBRACE);
			setState(1928);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				setState(1917);
				variableInitializer();
				setState(1922);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,195,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1918);
						match(COMMA);
						setState(1919);
						variableInitializer();
						}
						} 
					}
					setState(1924);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,195,_ctx);
				}
				setState(1926);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1925);
					match(COMMA);
					}
				}

				}
			}

			setState(1930);
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
			setState(1948);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1932);
				classOrInterfaceType();
				setState(1937);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1933);
						match(LBRACK);
						setState(1934);
						match(RBRACK);
						}
						} 
					}
					setState(1939);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
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
				setState(1940);
				primitiveType();
				setState(1945);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,199,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1941);
						match(LBRACK);
						setState(1942);
						match(RBRACK);
						}
						} 
					}
					setState(1947);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,199,_ctx);
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
			setState(1950);
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
			setState(1952);
			match(LT);
			setState(1953);
			typeArgument();
			setState(1958);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1954);
				match(COMMA);
				setState(1955);
				typeArgument();
				}
				}
				setState(1960);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1961);
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
			setState(1969);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(1963);
				type();
				}
				break;
			case QUESTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(1964);
				match(QUESTION);
				setState(1967);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS || _la==SUPER) {
					{
					setState(1965);
					_la = _input.LA(1);
					if ( !(_la==EXTENDS || _la==SUPER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1966);
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
			setState(1971);
			qualifiedName();
			setState(1976);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1972);
				match(COMMA);
				setState(1973);
				qualifiedName();
				}
				}
				setState(1978);
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
			setState(1979);
			match(LPAREN);
			setState(1981);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (SHORT - 66)))) != 0) || _la==Identifier) {
				{
				setState(1980);
				formalParameterList();
				}
			}

			setState(1983);
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
			setState(1998);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,208,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1985);
				formalParameter();
				setState(1990);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,206,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1986);
						match(COMMA);
						setState(1987);
						formalParameter();
						}
						} 
					}
					setState(1992);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,206,_ctx);
				}
				setState(1995);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1993);
					match(COMMA);
					setState(1994);
					lastFormalParameter();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1997);
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
			setState(2003);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2000);
				variableModifier();
				}
				}
				setState(2005);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2006);
			type();
			setState(2007);
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
			setState(2012);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2009);
				variableModifier();
				}
				}
				setState(2014);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2015);
			type();
			setState(2016);
			match(ELLIPSIS);
			setState(2017);
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
			setState(2019);
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
			setState(2021);
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
			setState(2023);
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
			setState(2025);
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
			setState(2027);
			elementValuePair();
			setState(2032);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2028);
				match(COMMA);
				setState(2029);
				elementValuePair();
				}
				}
				setState(2034);
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
			setState(2038);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(2035);
				expression(0);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(2036);
				annotation();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2037);
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
			setState(2040);
			match(LBRACE);
			setState(2049);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				setState(2041);
				elementValue();
				setState(2046);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,213,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2042);
						match(COMMA);
						setState(2043);
						elementValue();
						}
						} 
					}
					setState(2048);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,213,_ctx);
				}
				}
			}

			setState(2052);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(2051);
				match(COMMA);
				}
			}

			setState(2054);
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
			setState(2056);
			match(LBRACE);
			setState(2060);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (TRANSIENT - 66)) | (1L << (VOLATILE - 66)) | (1L << (SEMI - 66)))) != 0) || _la==Identifier) {
				{
				{
				setState(2057);
				annotationTypeElementDeclaration();
				}
				}
				setState(2062);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2063);
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
			setState(2073);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(2068);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,217,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2065);
						modifier();
						}
						} 
					}
					setState(2070);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,217,_ctx);
				}
				setState(2071);
				annotationTypeElementRest();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(2072);
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
			setState(2095);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				setState(2075);
				type();
				setState(2076);
				annotationMethodOrConstantRest();
				setState(2077);
				match(SEMI);
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 2);
				{
				setState(2079);
				classDeclaration();
				setState(2081);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,219,_ctx) ) {
				case 1:
					{
					setState(2080);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case INTERFACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2083);
				interfaceDeclaration();
				setState(2085);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,220,_ctx) ) {
				case 1:
					{
					setState(2084);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case ENUM:
				enterOuterAlt(_localctx, 4);
				{
				setState(2087);
				enumDeclaration();
				setState(2089);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,221,_ctx) ) {
				case 1:
					{
					setState(2088);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 5);
				{
				setState(2091);
				annotationTypeDeclaration();
				setState(2093);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,222,_ctx) ) {
				case 1:
					{
					setState(2092);
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
			setState(2099);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,224,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2097);
				annotationMethodRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2098);
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
			setState(2101);
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
			setState(2103);
			match(DEFAULT);
			setState(2104);
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
			setState(2106);
			match(LBRACE);
			setState(2110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (CONTINUE - 66)) | (1L << (DO - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (FOR - 66)) | (1L << (IF - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (NEW - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (RETURN - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SUPER - 66)) | (1L << (SWITCH - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (THIS - 66)) | (1L << (THROW - 66)) | (1L << (TRANSIENT - 66)) | (1L << (TRY - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (WHILE - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				{
				setState(2107);
				blockStatement();
				}
				}
				setState(2112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2113);
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
			setState(2118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,226,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2115);
				localVariableDeclarationStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2116);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2117);
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
			setState(2120);
			localVariableDeclaration();
			setState(2121);
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
			setState(2126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2123);
				variableModifier();
				}
				}
				setState(2128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2129);
			type();
			setState(2130);
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
			setState(2132);
			qualifiedName();
			setState(2137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(2133);
				match(BITOR);
				setState(2134);
				qualifiedName();
				}
				}
				setState(2139);
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
			setState(2140);
			match(FINALLY);
			setState(2141);
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
			setState(2143);
			match(LPAREN);
			setState(2144);
			resources();
			setState(2146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(2145);
				match(SEMI);
				}
			}

			setState(2148);
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
			setState(2150);
			resource();
			setState(2155);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2151);
					match(SEMI);
					setState(2152);
					resource();
					}
					} 
				}
				setState(2157);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,230,_ctx);
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
			setState(2161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2158);
				variableModifier();
				}
				}
				setState(2163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2164);
			classOrInterfaceType();
			setState(2165);
			variableDeclaratorId();
			setState(2166);
			match(ASSIGN);
			setState(2167);
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
			setState(2170); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2169);
				switchLabel();
				}
				}
				setState(2172); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE || _la==DEFAULT );
			setState(2175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2174);
				blockStatement();
				}
				}
				setState(2177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (CLASS - 66)) | (1L << (CONTINUE - 66)) | (1L << (DO - 66)) | (1L << (DOUBLE - 66)) | (1L << (ENUM - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (FOR - 66)) | (1L << (IF - 66)) | (1L << (INT - 66)) | (1L << (INTERFACE - 66)) | (1L << (LONG - 66)) | (1L << (NATIVE - 66)) | (1L << (NEW - 66)) | (1L << (PRIVATE - 66)) | (1L << (PROTECTED - 66)) | (1L << (PUBLIC - 66)) | (1L << (RETURN - 66)) | (1L << (SHORT - 66)) | (1L << (STATIC - 66)) | (1L << (STRICTFP - 66)) | (1L << (SUPER - 66)) | (1L << (SWITCH - 66)) | (1L << (SYNCHRONIZED - 66)) | (1L << (THIS - 66)) | (1L << (THROW - 66)) | (1L << (TRANSIENT - 66)) | (1L << (TRY - 66)) | (1L << (VOID - 66)) | (1L << (VOLATILE - 66)) | (1L << (WHILE - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LBRACE - 66)) | (1L << (SEMI - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0) );
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
			setState(2189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,234,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2179);
				match(CASE);
				setState(2180);
				constantExpression();
				setState(2181);
				match(COLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2183);
				match(CASE);
				setState(2184);
				enumConstantName();
				setState(2185);
				match(COLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2187);
				match(DEFAULT);
				setState(2188);
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
			setState(2203);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,238,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2191);
				enhancedForControl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << AT) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FINAL - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(2192);
					forInit();
					}
				}

				setState(2195);
				match(SEMI);
				setState(2197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(2196);
					expression(0);
					}
				}

				setState(2199);
				match(SEMI);
				setState(2201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
					{
					setState(2200);
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
			setState(2207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,239,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2205);
				localVariableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2206);
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
			setState(2212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT || _la==FINAL) {
				{
				{
				setState(2209);
				variableModifier();
				}
				}
				setState(2214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2215);
			type();
			setState(2216);
			variableDeclaratorId();
			setState(2217);
			match(COLON);
			setState(2218);
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
			setState(2220);
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
			setState(2222);
			match(LPAREN);
			setState(2223);
			expression(0);
			setState(2224);
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
			setState(2226);
			expression(0);
			setState(2231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2227);
				match(COMMA);
				setState(2228);
				expression(0);
				}
				}
				setState(2233);
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
			setState(2234);
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
			setState(2236);
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
			setState(2247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(2238);
				nonWildcardTypeArguments();
				setState(2239);
				createdName();
				setState(2240);
				classCreatorRest();
				}
				break;
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
				setState(2242);
				createdName();
				setState(2245);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LBRACK:
					{
					setState(2243);
					arrayCreatorRest();
					}
					break;
				case LPAREN:
					{
					setState(2244);
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
			setState(2249);
			match(LBRACK);
			setState(2277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RBRACK:
				{
				setState(2250);
				match(RBRACK);
				setState(2255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACK) {
					{
					{
					setState(2251);
					match(LBRACK);
					setState(2252);
					match(RBRACK);
					}
					}
					setState(2257);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2258);
				arrayInitializer();
				}
				break;
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
				setState(2259);
				expression(0);
				setState(2260);
				match(RBRACK);
				setState(2267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,245,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2261);
						match(LBRACK);
						setState(2262);
						expression(0);
						setState(2263);
						match(RBRACK);
						}
						} 
					}
					setState(2269);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,245,_ctx);
				}
				setState(2274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,246,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2270);
						match(LBRACK);
						setState(2271);
						match(RBRACK);
						}
						} 
					}
					setState(2276);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,246,_ctx);
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
			setState(2279);
			arguments();
			setState(2281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,248,_ctx) ) {
			case 1:
				{
				setState(2280);
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
			setState(2283);
			nonWildcardTypeArguments();
			setState(2284);
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
			setState(2286);
			match(LT);
			setState(2287);
			typeList();
			setState(2288);
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
			setState(2293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,249,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2290);
				match(LT);
				setState(2291);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2292);
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
			setState(2298);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,250,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2295);
				match(LT);
				setState(2296);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2297);
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
			setState(2300);
			match(LPAREN);
			setState(2302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARGS) | (1L << AFTER) | (1L << AROUND) | (1L << ASPECT) | (1L << BEFORE) | (1L << CALL) | (1L << CFLOW) | (1L << CFLOWBELOW) | (1L << DECLARE) | (1L << ERROR) | (1L << EXECUTION) | (1L << GET) | (1L << HANDLER) | (1L << INITIALIZATION) | (1L << ISSINGLETON) | (1L << PARENTS) | (1L << PERCFLOW) | (1L << PERCFLOWBELOW) | (1L << PERTARGET) | (1L << PERTHIS) | (1L << PERTYPEWITHIN) | (1L << POINTCUT) | (1L << PRECEDENCE) | (1L << PREINITIALIZATION) | (1L << PRIVILEGED) | (1L << RETURNING) | (1L << SET) | (1L << SOFT) | (1L << STATICINITIALIZATION) | (1L << TARGET) | (1L << THROWING) | (1L << WARNING) | (1L << WITHIN) | (1L << WITHINCODE) | (1L << BOOLEAN) | (1L << BYTE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (CHAR - 66)) | (1L << (DOUBLE - 66)) | (1L << (FLOAT - 66)) | (1L << (INT - 66)) | (1L << (LONG - 66)) | (1L << (NEW - 66)) | (1L << (SHORT - 66)) | (1L << (SUPER - 66)) | (1L << (THIS - 66)) | (1L << (VOID - 66)) | (1L << (IntegerLiteral - 66)) | (1L << (FloatingPointLiteral - 66)) | (1L << (BooleanLiteral - 66)) | (1L << (CharacterLiteral - 66)) | (1L << (StringLiteral - 66)) | (1L << (NullLiteral - 66)) | (1L << (LPAREN - 66)) | (1L << (LT - 66)) | (1L << (BANG - 66)) | (1L << (TILDE - 66)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (INC - 137)) | (1L << (DEC - 137)) | (1L << (ADD - 137)) | (1L << (SUB - 137)) | (1L << (Identifier - 137)))) != 0)) {
				{
				setState(2301);
				expressionList();
				}
			}

			setState(2304);
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
		"\u0004\u0001\u00b1\u0903\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007"+
		"O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007"+
		"T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007"+
		"Y\u0002Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007"+
		"^\u0002_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007"+
		"c\u0002d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007"+
		"h\u0002i\u0007i\u0002j\u0007j\u0002k\u0007k\u0002l\u0007l\u0002m\u0007"+
		"m\u0002n\u0007n\u0002o\u0007o\u0002p\u0007p\u0002q\u0007q\u0002r\u0007"+
		"r\u0002s\u0007s\u0002t\u0007t\u0002u\u0007u\u0002v\u0007v\u0002w\u0007"+
		"w\u0002x\u0007x\u0002y\u0007y\u0002z\u0007z\u0002{\u0007{\u0002|\u0007"+
		"|\u0002}\u0007}\u0002~\u0007~\u0002\u007f\u0007\u007f\u0002\u0080\u0007"+
		"\u0080\u0002\u0081\u0007\u0081\u0002\u0082\u0007\u0082\u0002\u0083\u0007"+
		"\u0083\u0002\u0084\u0007\u0084\u0002\u0085\u0007\u0085\u0002\u0086\u0007"+
		"\u0086\u0002\u0087\u0007\u0087\u0002\u0088\u0007\u0088\u0002\u0089\u0007"+
		"\u0089\u0002\u008a\u0007\u008a\u0002\u008b\u0007\u008b\u0002\u008c\u0007"+
		"\u008c\u0002\u008d\u0007\u008d\u0002\u008e\u0007\u008e\u0002\u008f\u0007"+
		"\u008f\u0002\u0090\u0007\u0090\u0001\u0000\u0005\u0000\u0124\b\u0000\n"+
		"\u0000\f\u0000\u0127\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000\u012b"+
		"\b\u0000\n\u0000\f\u0000\u012e\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"\u0132\b\u0000\n\u0000\f\u0000\u0135\t\u0000\u0001\u0000\u0001\u0000\u0005"+
		"\u0000\u0139\b\u0000\n\u0000\f\u0000\u013c\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000\u0140\b\u0000\n\u0000\f\u0000\u0143\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0003\u0000\u0147\b\u0000\u0001\u0001\u0001\u0001\u0005\u0001\u014b"+
		"\b\u0001\n\u0001\f\u0001\u014e\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0003\u0002\u0154\b\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"\u0158\b\u0002\n\u0002\f\u0002\u015b\t\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002\u0160\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u0166\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u0172\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0179\b\u0005\u0001\u0005\u0003\u0005\u017c"+
		"\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u01b6\b\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0203\b\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u0207\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u020c"+
		"\b\u0006\n\u0006\f\u0006\u020f\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0217\b\u0007\n\u0007"+
		"\f\u0007\u021a\t\u0007\u0001\b\u0003\b\u021d\b\b\u0001\b\u0005\b\u0220"+
		"\b\b\n\b\f\b\u0223\t\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0229\b"+
		"\b\u0001\b\u0001\b\u0003\b\u022d\b\b\u0001\b\u0003\b\u0230\b\b\u0001\b"+
		"\u0001\b\u0001\t\u0003\t\u0235\b\t\u0001\t\u0001\t\u0001\t\u0003\t\u023a"+
		"\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0249\b\n\u0001\n\u0003\n\u024c"+
		"\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0253\b\n\u0001\n"+
		"\u0003\n\u0256\b\n\u0001\n\u0001\n\u0003\n\u025a\b\n\u0001\n\u0001\n\u0003"+
		"\n\u025e\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u027c\b\u000b\u0001\f\u0001\f\u0005\f\u0280\b\f\n\f\f\f\u0283\t\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u028b\b\f\n\f\f\f\u028e"+
		"\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0297"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0003\r\u029c\b\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0003\r\u02a4\b\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u02ac\b\r\n\r\f\r\u02af\t\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u02f3"+
		"\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0329"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u032e\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0005\u0010\u0334\b\u0010"+
		"\n\u0010\f\u0010\u0337\t\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u033b"+
		"\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u0343\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005"+
		"\u0010\u0348\b\u0010\n\u0010\f\u0010\u034b\t\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u034f\b\u0010\n\u0010\f\u0010\u0352\t\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u0356\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u035e\b\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u0363\b\u0010\n\u0010\f\u0010\u0366\t\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u036e\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u0373\b\u0010\n\u0010\f\u0010\u0376\t\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u037a\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u0381\b\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0385"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u03d9"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u03e1\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003"+
		"\u0012\u03e6\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0005\u0012\u03ee\b\u0012\n\u0012\f\u0012\u03f1\t\u0012"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u03f5\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u03f9\b\u0013\n\u0013\f\u0013\u03fc\t\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0004\u0014\u0403\b\u0014\u000b"+
		"\u0014\f\u0014\u0404\u0001\u0014\u0003\u0014\u0408\b\u0014\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u040c\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u0412\b\u0015\u0001\u0015\u0003\u0015\u0415\b"+
		"\u0015\u0001\u0016\u0003\u0016\u0418\b\u0016\u0001\u0016\u0003\u0016\u041b"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0421"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0003\u0017\u0426\b\u0017"+
		"\u0001\u0017\u0001\u0017\u0005\u0017\u042a\b\u0017\n\u0017\f\u0017\u042d"+
		"\t\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0005\u001a\u0436\b\u001a\n\u001a\f\u001a\u0439\t\u001a"+
		"\u0001\u001a\u0003\u001a\u043c\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u0442\b\u001a\n\u001a\f\u001a\u0445\t\u001a\u0001"+
		"\u001a\u0003\u001a\u0448\b\u001a\u0003\u001a\u044a\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u044e\b\u001b\u0001\u001c\u0003\u001c\u0451\b"+
		"\u001c\u0001\u001c\u0003\u001c\u0454\b\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u045a\b\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u045f\b\u001c\u0001\u001d\u0003\u001d\u0462\b\u001d"+
		"\u0001\u001d\u0001\u001d\u0005\u001d\u0466\b\u001d\n\u001d\f\u001d\u0469"+
		"\t\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0005"+
		"\u001f\u0470\b\u001f\n\u001f\f\u001f\u0473\t\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0005\u001f\u0478\b\u001f\n\u001f\f\u001f\u047b\t\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0480\b\u001f\u0001 \u0001"+
		" \u0001 \u0005 \u0485\b \n \f \u0488\t \u0001 \u0001 \u0001 \u0003 \u048d"+
		"\b \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0005\"\u0495\b\"\n\""+
		"\f\"\u0498\t\"\u0001#\u0003#\u049b\b#\u0001#\u0003#\u049e\b#\u0001#\u0001"+
		"#\u0001#\u0003#\u04a3\b#\u0001#\u0001#\u0001#\u0003#\u04a8\b#\u0001$\u0003"+
		"$\u04ab\b$\u0001$\u0001$\u0005$\u04af\b$\n$\f$\u04b2\t$\u0001%\u0001%"+
		"\u0001&\u0003&\u04b7\b&\u0001&\u0001&\u0001&\u0005&\u04bc\b&\n&\f&\u04bf"+
		"\t&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u04c6\b\'\u0001(\u0001"+
		"(\u0003(\u04ca\b(\u0001(\u0001(\u0001)\u0001)\u0003)\u04d0\b)\u0001*\u0001"+
		"*\u0003*\u04d4\b*\u0001+\u0001+\u0001+\u0003+\u04d9\b+\u0001+\u0001+\u0001"+
		"+\u0005+\u04de\b+\n+\f+\u04e1\t+\u0001+\u0001+\u0001+\u0005+\u04e6\b+"+
		"\n+\f+\u04e9\t+\u0003+\u04eb\b+\u0001,\u0001,\u0001,\u0005,\u04f0\b,\n"+
		",\f,\u04f3\t,\u0001,\u0001,\u0001,\u0005,\u04f8\b,\n,\f,\u04fb\t,\u0003"+
		",\u04fd\b,\u0001-\u0001-\u0003-\u0501\b-\u0001.\u0001.\u0001.\u0005.\u0506"+
		"\b.\n.\f.\u0509\t.\u0001/\u0001/\u0003/\u050d\b/\u00010\u00010\u00010"+
		"\u00030\u0512\b0\u00010\u00010\u00030\u0516\b0\u00010\u00010\u00030\u051a"+
		"\b0\u00010\u00010\u00011\u00011\u00011\u00031\u0521\b1\u00012\u00012\u0001"+
		"2\u00012\u00032\u0527\b2\u00012\u00012\u00032\u052b\b2\u00012\u00032\u052e"+
		"\b2\u00012\u00032\u0531\b2\u00012\u00012\u00013\u00053\u0536\b3\n3\f3"+
		"\u0539\t3\u00013\u00013\u00033\u053d\b3\u00013\u00033\u0540\b3\u00014"+
		"\u00014\u00014\u00034\u0545\b4\u00014\u00014\u00034\u0549\b4\u00014\u0001"+
		"4\u00015\u00015\u00035\u054f\b5\u00015\u00015\u00015\u00015\u00055\u0555"+
		"\b5\n5\f5\u0558\t5\u00015\u00015\u00035\u055c\b5\u00015\u00015\u00035"+
		"\u0560\b5\u00016\u00016\u00016\u00016\u00036\u0566\b6\u00016\u00016\u0001"+
		"7\u00017\u00017\u00057\u056d\b7\n7\f7\u0570\t7\u00017\u00017\u00017\u0001"+
		"8\u00018\u00038\u0577\b8\u00018\u00018\u00018\u00018\u00058\u057d\b8\n"+
		"8\f8\u0580\t8\u00018\u00018\u00038\u0584\b8\u00018\u00018\u00019\u0001"+
		"9\u00019\u00059\u058b\b9\n9\f9\u058e\t9\u0001:\u0001:\u0001;\u0001;\u0003"+
		";\u0594\b;\u0001;\u0001;\u0001;\u0003;\u0599\b;\u0005;\u059b\b;\n;\f;"+
		"\u059e\t;\u0001<\u0001<\u0001<\u0005<\u05a3\b<\n<\f<\u05a6\t<\u0001=\u0001"+
		"=\u0001=\u0001=\u0001>\u0001>\u0001>\u0001>\u0001>\u0001?\u0001?\u0001"+
		"?\u0001?\u0003?\u05b5\b?\u0001@\u0001@\u0001@\u0001@\u0001@\u0003@\u05bc"+
		"\b@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0003@\u05c5\b@\u0001"+
		"@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001"+
		"@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0004@\u05da"+
		"\b@\u000b@\f@\u05db\u0001@\u0003@\u05df\b@\u0001@\u0003@\u05e2\b@\u0001"+
		"@\u0001@\u0001@\u0001@\u0005@\u05e8\b@\n@\f@\u05eb\t@\u0001@\u0003@\u05ee"+
		"\b@\u0001@\u0001@\u0001@\u0001@\u0005@\u05f4\b@\n@\f@\u05f7\t@\u0001@"+
		"\u0005@\u05fa\b@\n@\f@\u05fd\t@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001"+
		"@\u0001@\u0001@\u0003@\u0607\b@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001"+
		"@\u0001@\u0003@\u0610\b@\u0001@\u0001@\u0001@\u0003@\u0615\b@\u0001@\u0001"+
		"@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0001@\u0003@\u0620\b@\u0001"+
		"A\u0001A\u0001A\u0005A\u0625\bA\nA\fA\u0628\tA\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0003B\u063c\bB\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0003B\u064c\bB\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0003B\u0677\bB\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0003B\u0689\bB\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0005"+
		"B\u0691\bB\nB\fB\u0694\tB\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001"+
		"C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001"+
		"C\u0001C\u0001C\u0003C\u06a9\bC\u0003C\u06ab\bC\u0001D\u0001D\u0003D\u06af"+
		"\bD\u0001D\u0001D\u0001D\u0003D\u06b4\bD\u0005D\u06b6\bD\nD\fD\u06b9\t"+
		"D\u0001D\u0003D\u06bc\bD\u0001E\u0001E\u0003E\u06c0\bE\u0001E\u0001E\u0001"+
		"F\u0001F\u0001F\u0001F\u0003F\u06c8\bF\u0003F\u06ca\bF\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0003G\u06d1\bG\u0001H\u0003H\u06d4\bH\u0001H\u0005H\u06d7"+
		"\bH\nH\fH\u06da\tH\u0001H\u0005H\u06dd\bH\nH\fH\u06e0\tH\u0001H\u0001"+
		"H\u0001I\u0005I\u06e5\bI\nI\fI\u06e8\tI\u0001I\u0001I\u0001I\u0001I\u0001"+
		"J\u0001J\u0003J\u06f0\bJ\u0001J\u0001J\u0001J\u0003J\u06f5\bJ\u0001J\u0001"+
		"J\u0001K\u0001K\u0003K\u06fb\bK\u0001L\u0001L\u0003L\u06ff\bL\u0001M\u0001"+
		"M\u0003M\u0703\bM\u0001N\u0001N\u0001N\u0001N\u0005N\u0709\bN\nN\fN\u070c"+
		"\tN\u0001N\u0001N\u0001O\u0001O\u0001O\u0005O\u0713\bO\nO\fO\u0716\tO"+
		"\u0001P\u0001P\u0001P\u0005P\u071b\bP\nP\fP\u071e\tP\u0001Q\u0001Q\u0005"+
		"Q\u0722\bQ\nQ\fQ\u0725\tQ\u0001R\u0001R\u0001R\u0005R\u072a\bR\nR\fR\u072d"+
		"\tR\u0001S\u0001S\u0005S\u0731\bS\nS\fS\u0734\tS\u0001S\u0001S\u0001T"+
		"\u0001T\u0005T\u073a\bT\nT\fT\u073d\tT\u0001T\u0001T\u0001U\u0001U\u0001"+
		"U\u0001V\u0001V\u0001V\u0001W\u0001W\u0001W\u0001W\u0001X\u0005X\u074c"+
		"\bX\nX\fX\u074f\tX\u0001X\u0001X\u0003X\u0753\bX\u0001Y\u0001Y\u0001Y"+
		"\u0001Y\u0001Y\u0001Y\u0001Y\u0003Y\u075c\bY\u0001Z\u0001Z\u0001Z\u0001"+
		"Z\u0005Z\u0762\bZ\nZ\fZ\u0765\tZ\u0001Z\u0001Z\u0001[\u0001[\u0001[\u0001"+
		"\\\u0001\\\u0001\\\u0005\\\u076f\b\\\n\\\f\\\u0772\t\\\u0001]\u0001]\u0001"+
		"]\u0003]\u0777\b]\u0001^\u0001^\u0003^\u077b\b^\u0001_\u0001_\u0001_\u0001"+
		"_\u0005_\u0781\b_\n_\f_\u0784\t_\u0001_\u0003_\u0787\b_\u0003_\u0789\b"+
		"_\u0001_\u0001_\u0001`\u0001`\u0001`\u0005`\u0790\b`\n`\f`\u0793\t`\u0001"+
		"`\u0001`\u0001`\u0005`\u0798\b`\n`\f`\u079b\t`\u0003`\u079d\b`\u0001a"+
		"\u0001a\u0001b\u0001b\u0001b\u0001b\u0005b\u07a5\bb\nb\fb\u07a8\tb\u0001"+
		"b\u0001b\u0001c\u0001c\u0001c\u0001c\u0003c\u07b0\bc\u0003c\u07b2\bc\u0001"+
		"d\u0001d\u0001d\u0005d\u07b7\bd\nd\fd\u07ba\td\u0001e\u0001e\u0003e\u07be"+
		"\be\u0001e\u0001e\u0001f\u0001f\u0001f\u0005f\u07c5\bf\nf\ff\u07c8\tf"+
		"\u0001f\u0001f\u0003f\u07cc\bf\u0001f\u0003f\u07cf\bf\u0001g\u0005g\u07d2"+
		"\bg\ng\fg\u07d5\tg\u0001g\u0001g\u0001g\u0001h\u0005h\u07db\bh\nh\fh\u07de"+
		"\th\u0001h\u0001h\u0001h\u0001h\u0001i\u0001i\u0001j\u0001j\u0001k\u0001"+
		"k\u0001l\u0001l\u0001m\u0001m\u0001m\u0005m\u07ef\bm\nm\fm\u07f2\tm\u0001"+
		"n\u0001n\u0001n\u0003n\u07f7\bn\u0001o\u0001o\u0001o\u0001o\u0005o\u07fd"+
		"\bo\no\fo\u0800\to\u0003o\u0802\bo\u0001o\u0003o\u0805\bo\u0001o\u0001"+
		"o\u0001p\u0001p\u0005p\u080b\bp\np\fp\u080e\tp\u0001p\u0001p\u0001q\u0005"+
		"q\u0813\bq\nq\fq\u0816\tq\u0001q\u0001q\u0003q\u081a\bq\u0001r\u0001r"+
		"\u0001r\u0001r\u0001r\u0001r\u0003r\u0822\br\u0001r\u0001r\u0003r\u0826"+
		"\br\u0001r\u0001r\u0003r\u082a\br\u0001r\u0001r\u0003r\u082e\br\u0003"+
		"r\u0830\br\u0001s\u0001s\u0003s\u0834\bs\u0001t\u0001t\u0001u\u0001u\u0001"+
		"u\u0001v\u0001v\u0005v\u083d\bv\nv\fv\u0840\tv\u0001v\u0001v\u0001w\u0001"+
		"w\u0001w\u0003w\u0847\bw\u0001x\u0001x\u0001x\u0001y\u0005y\u084d\by\n"+
		"y\fy\u0850\ty\u0001y\u0001y\u0001y\u0001z\u0001z\u0001z\u0005z\u0858\b"+
		"z\nz\fz\u085b\tz\u0001{\u0001{\u0001{\u0001|\u0001|\u0001|\u0003|\u0863"+
		"\b|\u0001|\u0001|\u0001}\u0001}\u0001}\u0005}\u086a\b}\n}\f}\u086d\t}"+
		"\u0001~\u0005~\u0870\b~\n~\f~\u0873\t~\u0001~\u0001~\u0001~\u0001~\u0001"+
		"~\u0001\u007f\u0004\u007f\u087b\b\u007f\u000b\u007f\f\u007f\u087c\u0001"+
		"\u007f\u0004\u007f\u0880\b\u007f\u000b\u007f\f\u007f\u0881\u0001\u0080"+
		"\u0001\u0080\u0001\u0080\u0001\u0080\u0001\u0080\u0001\u0080\u0001\u0080"+
		"\u0001\u0080\u0001\u0080\u0001\u0080\u0003\u0080\u088e\b\u0080\u0001\u0081"+
		"\u0001\u0081\u0003\u0081\u0892\b\u0081\u0001\u0081\u0001\u0081\u0003\u0081"+
		"\u0896\b\u0081\u0001\u0081\u0001\u0081\u0003\u0081\u089a\b\u0081\u0003"+
		"\u0081\u089c\b\u0081\u0001\u0082\u0001\u0082\u0003\u0082\u08a0\b\u0082"+
		"\u0001\u0083\u0005\u0083\u08a3\b\u0083\n\u0083\f\u0083\u08a6\t\u0083\u0001"+
		"\u0083\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0084\u0001"+
		"\u0084\u0001\u0085\u0001\u0085\u0001\u0085\u0001\u0085\u0001\u0086\u0001"+
		"\u0086\u0001\u0086\u0005\u0086\u08b6\b\u0086\n\u0086\f\u0086\u08b9\t\u0086"+
		"\u0001\u0087\u0001\u0087\u0001\u0088\u0001\u0088\u0001\u0089\u0001\u0089"+
		"\u0001\u0089\u0001\u0089\u0001\u0089\u0001\u0089\u0001\u0089\u0003\u0089"+
		"\u08c6\b\u0089\u0003\u0089\u08c8\b\u0089\u0001\u008a\u0001\u008a\u0001"+
		"\u008a\u0001\u008a\u0005\u008a\u08ce\b\u008a\n\u008a\f\u008a\u08d1\t\u008a"+
		"\u0001\u008a\u0001\u008a\u0001\u008a\u0001\u008a\u0001\u008a\u0001\u008a"+
		"\u0001\u008a\u0005\u008a\u08da\b\u008a\n\u008a\f\u008a\u08dd\t\u008a\u0001"+
		"\u008a\u0001\u008a\u0005\u008a\u08e1\b\u008a\n\u008a\f\u008a\u08e4\t\u008a"+
		"\u0003\u008a\u08e6\b\u008a\u0001\u008b\u0001\u008b\u0003\u008b\u08ea\b"+
		"\u008b\u0001\u008c\u0001\u008c\u0001\u008c\u0001\u008d\u0001\u008d\u0001"+
		"\u008d\u0001\u008d\u0001\u008e\u0001\u008e\u0001\u008e\u0003\u008e\u08f6"+
		"\b\u008e\u0001\u008f\u0001\u008f\u0001\u008f\u0003\u008f\u08fb\b\u008f"+
		"\u0001\u0090\u0001\u0090\u0003\u0090\u08ff\b\u0090\u0001\u0090\u0001\u0090"+
		"\u0001\u0090\u0000\u0003\u001a$\u0084\u0091\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0"+
		"\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8"+
		"\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0"+
		"\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8"+
		"\u00ea\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100"+
		"\u0102\u0104\u0106\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118"+
		"\u011a\u011c\u011e\u0120\u0000\u0013\u0004\u0000LL[]``hh\u0002\u0000\u0001"+
		"\u0001{{\u0004\u0000LL[]``dd\u0001\u0000[]\u0002\u0000\u0001\u0001\u008d"+
		"\u008d\u0001\u0000\u0005&\u0001\u0000\u0089\u008c\u0001\u0000\u007f\u0080"+
		"\u0002\u0000\u008d\u008e\u0092\u0092\u0001\u0000\u008b\u008c\u0002\u0000"+
		"}~\u0084\u0085\u0002\u0000\u0083\u0083\u0086\u0086\u0002\u0000||\u0093"+
		"\u009d\u0001\u0000\u0089\u008a\u0004\u0000XXddhhkk\u0004\u0000;;LL[]`"+
		"a\b\u0000==??BBHHNNUUWW__\u0002\u0000KKbb\u0001\u0000mr\u09ef\u0000\u0146"+
		"\u0001\u0000\u0000\u0000\u0002\u0148\u0001\u0000\u0000\u0000\u0004\u015f"+
		"\u0001\u0000\u0000\u0000\u0006\u0165\u0001\u0000\u0000\u0000\b\u0171\u0001"+
		"\u0000\u0000\u0000\n\u0206\u0001\u0000\u0000\u0000\f\u0208\u0001\u0000"+
		"\u0000\u0000\u000e\u0213\u0001\u0000\u0000\u0000\u0010\u021c\u0001\u0000"+
		"\u0000\u0000\u0012\u0234\u0001\u0000\u0000\u0000\u0014\u025d\u0001\u0000"+
		"\u0000\u0000\u0016\u027b\u0001\u0000\u0000\u0000\u0018\u0296\u0001\u0000"+
		"\u0000\u0000\u001a\u02a3\u0001\u0000\u0000\u0000\u001c\u0328\u0001\u0000"+
		"\u0000\u0000\u001e\u032d\u0001\u0000\u0000\u0000 \u0384\u0001\u0000\u0000"+
		"\u0000\"\u03d8\u0001\u0000\u0000\u0000$\u03e5\u0001\u0000\u0000\u0000"+
		"&\u03f2\u0001\u0000\u0000\u0000(\u0407\u0001\u0000\u0000\u0000*\u0414"+
		"\u0001\u0000\u0000\u0000,\u0417\u0001\u0000\u0000\u0000.\u0425\u0001\u0000"+
		"\u0000\u00000\u042e\u0001\u0000\u0000\u00002\u0430\u0001\u0000\u0000\u0000"+
		"4\u0449\u0001\u0000\u0000\u00006\u044d\u0001\u0000\u0000\u00008\u0450"+
		"\u0001\u0000\u0000\u0000:\u0461\u0001\u0000\u0000\u0000<\u046a\u0001\u0000"+
		"\u0000\u0000>\u047f\u0001\u0000\u0000\u0000@\u048c\u0001\u0000\u0000\u0000"+
		"B\u048e\u0001\u0000\u0000\u0000D\u0491\u0001\u0000\u0000\u0000F\u049a"+
		"\u0001\u0000\u0000\u0000H\u04aa\u0001\u0000\u0000\u0000J\u04b3\u0001\u0000"+
		"\u0000\u0000L\u04b6\u0001\u0000\u0000\u0000N\u04c5\u0001\u0000\u0000\u0000"+
		"P\u04c7\u0001\u0000\u0000\u0000R\u04cf\u0001\u0000\u0000\u0000T\u04d3"+
		"\u0001\u0000\u0000\u0000V\u04ea\u0001\u0000\u0000\u0000X\u04fc\u0001\u0000"+
		"\u0000\u0000Z\u0500\u0001\u0000\u0000\u0000\\\u0502\u0001\u0000\u0000"+
		"\u0000^\u050c\u0001\u0000\u0000\u0000`\u050e\u0001\u0000\u0000\u0000b"+
		"\u051d\u0001\u0000\u0000\u0000d\u0522\u0001\u0000\u0000\u0000f\u0537\u0001"+
		"\u0000\u0000\u0000h\u0541\u0001\u0000\u0000\u0000j\u054e\u0001\u0000\u0000"+
		"\u0000l\u0561\u0001\u0000\u0000\u0000n\u0569\u0001\u0000\u0000\u0000p"+
		"\u0576\u0001\u0000\u0000\u0000r\u0587\u0001\u0000\u0000\u0000t\u058f\u0001"+
		"\u0000\u0000\u0000v\u0591\u0001\u0000\u0000\u0000x\u059f\u0001\u0000\u0000"+
		"\u0000z\u05a7\u0001\u0000\u0000\u0000|\u05ab\u0001\u0000\u0000\u0000~"+
		"\u05b0\u0001\u0000\u0000\u0000\u0080\u061f\u0001\u0000\u0000\u0000\u0082"+
		"\u0621\u0001\u0000\u0000\u0000\u0084\u063b\u0001\u0000\u0000\u0000\u0086"+
		"\u06aa\u0001\u0000\u0000\u0000\u0088\u06bb\u0001\u0000\u0000\u0000\u008a"+
		"\u06bd\u0001\u0000\u0000\u0000\u008c\u06c9\u0001\u0000\u0000\u0000\u008e"+
		"\u06d0\u0001\u0000\u0000\u0000\u0090\u06d3\u0001\u0000\u0000\u0000\u0092"+
		"\u06e6\u0001\u0000\u0000\u0000\u0094\u06ed\u0001\u0000\u0000\u0000\u0096"+
		"\u06fa\u0001\u0000\u0000\u0000\u0098\u06fe\u0001\u0000\u0000\u0000\u009a"+
		"\u0702\u0001\u0000\u0000\u0000\u009c\u0704\u0001\u0000\u0000\u0000\u009e"+
		"\u070f\u0001\u0000\u0000\u0000\u00a0\u0717\u0001\u0000\u0000\u0000\u00a2"+
		"\u071f\u0001\u0000\u0000\u0000\u00a4\u0726\u0001\u0000\u0000\u0000\u00a6"+
		"\u072e\u0001\u0000\u0000\u0000\u00a8\u0737\u0001\u0000\u0000\u0000\u00aa"+
		"\u0740\u0001\u0000\u0000\u0000\u00ac\u0743\u0001\u0000\u0000\u0000\u00ae"+
		"\u0746\u0001\u0000\u0000\u0000\u00b0\u0752\u0001\u0000\u0000\u0000\u00b2"+
		"\u075b\u0001\u0000\u0000\u0000\u00b4\u075d\u0001\u0000\u0000\u0000\u00b6"+
		"\u0768\u0001\u0000\u0000\u0000\u00b8\u076b\u0001\u0000\u0000\u0000\u00ba"+
		"\u0773\u0001\u0000\u0000\u0000\u00bc\u077a\u0001\u0000\u0000\u0000\u00be"+
		"\u077c\u0001\u0000\u0000\u0000\u00c0\u079c\u0001\u0000\u0000\u0000\u00c2"+
		"\u079e\u0001\u0000\u0000\u0000\u00c4\u07a0\u0001\u0000\u0000\u0000\u00c6"+
		"\u07b1\u0001\u0000\u0000\u0000\u00c8\u07b3\u0001\u0000\u0000\u0000\u00ca"+
		"\u07bb\u0001\u0000\u0000\u0000\u00cc\u07ce\u0001\u0000\u0000\u0000\u00ce"+
		"\u07d3\u0001\u0000\u0000\u0000\u00d0\u07dc\u0001\u0000\u0000\u0000\u00d2"+
		"\u07e3\u0001\u0000\u0000\u0000\u00d4\u07e5\u0001\u0000\u0000\u0000\u00d6"+
		"\u07e7\u0001\u0000\u0000\u0000\u00d8\u07e9\u0001\u0000\u0000\u0000\u00da"+
		"\u07eb\u0001\u0000\u0000\u0000\u00dc\u07f6\u0001\u0000\u0000\u0000\u00de"+
		"\u07f8\u0001\u0000\u0000\u0000\u00e0\u0808\u0001\u0000\u0000\u0000\u00e2"+
		"\u0819\u0001\u0000\u0000\u0000\u00e4\u082f\u0001\u0000\u0000\u0000\u00e6"+
		"\u0833\u0001\u0000\u0000\u0000\u00e8\u0835\u0001\u0000\u0000\u0000\u00ea"+
		"\u0837\u0001\u0000\u0000\u0000\u00ec\u083a\u0001\u0000\u0000\u0000\u00ee"+
		"\u0846\u0001\u0000\u0000\u0000\u00f0\u0848\u0001\u0000\u0000\u0000\u00f2"+
		"\u084e\u0001\u0000\u0000\u0000\u00f4\u0854\u0001\u0000\u0000\u0000\u00f6"+
		"\u085c\u0001\u0000\u0000\u0000\u00f8\u085f\u0001\u0000\u0000\u0000\u00fa"+
		"\u0866\u0001\u0000\u0000\u0000\u00fc\u0871\u0001\u0000\u0000\u0000\u00fe"+
		"\u087a\u0001\u0000\u0000\u0000\u0100\u088d\u0001\u0000\u0000\u0000\u0102"+
		"\u089b\u0001\u0000\u0000\u0000\u0104\u089f\u0001\u0000\u0000\u0000\u0106"+
		"\u08a4\u0001\u0000\u0000\u0000\u0108\u08ac\u0001\u0000\u0000\u0000\u010a"+
		"\u08ae\u0001\u0000\u0000\u0000\u010c\u08b2\u0001\u0000\u0000\u0000\u010e"+
		"\u08ba\u0001\u0000\u0000\u0000\u0110\u08bc\u0001\u0000\u0000\u0000\u0112"+
		"\u08c7\u0001\u0000\u0000\u0000\u0114\u08c9\u0001\u0000\u0000\u0000\u0116"+
		"\u08e7\u0001\u0000\u0000\u0000\u0118\u08eb\u0001\u0000\u0000\u0000\u011a"+
		"\u08ee\u0001\u0000\u0000\u0000\u011c\u08f5\u0001\u0000\u0000\u0000\u011e"+
		"\u08fa\u0001\u0000\u0000\u0000\u0120\u08fc\u0001\u0000\u0000\u0000\u0122"+
		"\u0124\u0003\u0098L\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0124\u0127"+
		"\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0125\u0126"+
		"\u0001\u0000\u0000\u0000\u0126\u0128\u0001\u0000\u0000\u0000\u0127\u0125"+
		"\u0001\u0000\u0000\u0000\u0128\u0147\u0003`0\u0000\u0129\u012b\u0003\u0098"+
		"L\u0000\u012a\u0129\u0001\u0000\u0000\u0000\u012b\u012e\u0001\u0000\u0000"+
		"\u0000\u012c\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000"+
		"\u0000\u012d\u012f\u0001\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000"+
		"\u0000\u012f\u0147\u0003d2\u0000\u0130\u0132\u0003\u0098L\u0000\u0131"+
		"\u0130\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000\u0000\u0133"+
		"\u0131\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000\u0000\u0134"+
		"\u0136\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0136"+
		"\u0147\u0003h4\u0000\u0137\u0139\u0003\u0098L\u0000\u0138\u0137\u0001"+
		"\u0000\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000\u013a\u0138\u0001"+
		"\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u013d\u0001"+
		"\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013d\u0147\u0003"+
		"|>\u0000\u013e\u0140\u0003\u0098L\u0000\u013f\u013e\u0001\u0000\u0000"+
		"\u0000\u0140\u0143\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000"+
		"\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0144\u0001\u0000\u0000"+
		"\u0000\u0143\u0141\u0001\u0000\u0000\u0000\u0144\u0147\u0003\u0010\b\u0000"+
		"\u0145\u0147\u0005y\u0000\u0000\u0146\u0125\u0001\u0000\u0000\u0000\u0146"+
		"\u012c\u0001\u0000\u0000\u0000\u0146\u0133\u0001\u0000\u0000\u0000\u0146"+
		"\u013a\u0001\u0000\u0000\u0000\u0146\u0141\u0001\u0000\u0000\u0000\u0146"+
		"\u0145\u0001\u0000\u0000\u0000\u0147\u0001\u0001\u0000\u0000\u0000\u0148"+
		"\u014c\u0005u\u0000\u0000\u0149\u014b\u0003\u0006\u0003\u0000\u014a\u0149"+
		"\u0001\u0000\u0000\u0000\u014b\u014e\u0001\u0000\u0000\u0000\u014c\u014a"+
		"\u0001\u0000\u0000\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u014f"+
		"\u0001\u0000\u0000\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014f\u0150"+
		"\u0005v\u0000\u0000\u0150\u0003\u0001\u0000\u0000\u0000\u0151\u0160\u0005"+
		"y\u0000\u0000\u0152\u0154\u0005`\u0000\u0000\u0153\u0152\u0001\u0000\u0000"+
		"\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000"+
		"\u0000\u0155\u0160\u0003\u00ecv\u0000\u0156\u0158\u0003\u0096K\u0000\u0157"+
		"\u0156\u0001\u0000\u0000\u0000\u0158\u015b\u0001\u0000\u0000\u0000\u0159"+
		"\u0157\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a"+
		"\u015c\u0001\u0000\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015c"+
		"\u0160\u0003\b\u0004\u0000\u015d\u015e\u0005`\u0000\u0000\u015e\u0160"+
		"\u0003\u0010\b\u0000\u015f\u0151\u0001\u0000\u0000\u0000\u015f\u0153\u0001"+
		"\u0000\u0000\u0000\u015f\u0159\u0001\u0000\u0000\u0000\u015f\u015d\u0001"+
		"\u0000\u0000\u0000\u0160\u0005\u0001\u0000\u0000\u0000\u0161\u0166\u0003"+
		"\u0004\u0002\u0000\u0162\u0166\u0003\u0012\t\u0000\u0163\u0166\u0003 "+
		"\u0010\u0000\u0164\u0166\u0003\"\u0011\u0000\u0165\u0161\u0001\u0000\u0000"+
		"\u0000\u0165\u0162\u0001\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000"+
		"\u0000\u0165\u0164\u0001\u0000\u0000\u0000\u0166\u0007\u0001\u0000\u0000"+
		"\u0000\u0167\u0172\u0003j5\u0000\u0168\u0172\u0003\u00aaU\u0000\u0169"+
		"\u0172\u0003\u00aeW\u0000\u016a\u0172\u0003l6\u0000\u016b\u0172\u0003"+
		"\u00acV\u0000\u016c\u0172\u0003h4\u0000\u016d\u0172\u0003|>\u0000\u016e"+
		"\u0172\u0003`0\u0000\u016f\u0172\u0003d2\u0000\u0170\u0172\u0003\u0018"+
		"\f\u0000\u0171\u0167\u0001\u0000\u0000\u0000\u0171\u0168\u0001\u0000\u0000"+
		"\u0000\u0171\u0169\u0001\u0000\u0000\u0000\u0171\u016a\u0001\u0000\u0000"+
		"\u0000\u0171\u016b\u0001\u0000\u0000\u0000\u0171\u016c\u0001\u0000\u0000"+
		"\u0000\u0171\u016d\u0001\u0000\u0000\u0000\u0171\u016e\u0001\u0000\u0000"+
		"\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0171\u0170\u0001\u0000\u0000"+
		"\u0000\u0172\t\u0001\u0000\u0000\u0000\u0173\u0174\u0005:\u0000\u0000"+
		"\u0174\u017b\u0003\u00d8l\u0000\u0175\u0178\u0005s\u0000\u0000\u0176\u0179"+
		"\u0003\u00dam\u0000\u0177\u0179\u0003\u00dcn\u0000\u0178\u0176\u0001\u0000"+
		"\u0000\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000"+
		"\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u017c\u0005t\u0000"+
		"\u0000\u017b\u0175\u0001\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000"+
		"\u0000\u017c\u0207\u0001\u0000\u0000\u0000\u017d\u017e\u0005:\u0000\u0000"+
		"\u017e\u017f\u0005\'\u0000\u0000\u017f\u0180\u0005s\u0000\u0000\u0180"+
		"\u0181\u0005\u0002\u0000\u0000\u0181\u0182\u0003\u001a\r\u0000\u0182\u0183"+
		"\u0005\u0002\u0000\u0000\u0183\u0184\u0005t\u0000\u0000\u0184\u0207\u0001"+
		"\u0000\u0000\u0000\u0185\u0186\u0005:\u0000\u0000\u0186\u0187\u0005(\u0000"+
		"\u0000\u0187\u0188\u0005s\u0000\u0000\u0188\u0189\u0005\u0002\u0000\u0000"+
		"\u0189\u018a\u0003\u001a\r\u0000\u018a\u018b\u0005\u0002\u0000\u0000\u018b"+
		"\u018c\u0005t\u0000\u0000\u018c\u0207\u0001\u0000\u0000\u0000\u018d\u018e"+
		"\u0005:\u0000\u0000\u018e\u018f\u0005(\u0000\u0000\u018f\u0190\u0005s"+
		"\u0000\u0000\u0190\u0191\u0005\u001a\u0000\u0000\u0191\u0192\u0005|\u0000"+
		"\u0000\u0192\u0193\u0005\u0002\u0000\u0000\u0193\u0194\u0003\u001a\r\u0000"+
		"\u0194\u0195\u0005\u0002\u0000\u0000\u0195\u0196\u0005z\u0000\u0000\u0196"+
		"\u0197\u0005\u001e\u0000\u0000\u0197\u0198\u0005|\u0000\u0000\u0198\u0199"+
		"\u0005\u0002\u0000\u0000\u0199\u019a\u0003^/\u0000\u019a\u019b\u0005\u0002"+
		"\u0000\u0000\u019b\u019c\u0005t\u0000\u0000\u019c\u0207\u0001\u0000\u0000"+
		"\u0000\u019d\u019e\u0005:\u0000\u0000\u019e\u019f\u0005)\u0000\u0000\u019f"+
		"\u01a0\u0005s\u0000\u0000\u01a0\u01a1\u0005\u0002\u0000\u0000\u01a1\u01a2"+
		"\u0003\u001a\r\u0000\u01a2\u01a3\u0005\u0002\u0000\u0000\u01a3\u01a4\u0005"+
		"t\u0000\u0000\u01a4\u0207\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005:\u0000"+
		"\u0000\u01a6\u01a7\u0005*\u0000\u0000\u01a7\u01a8\u0005s\u0000\u0000\u01a8"+
		"\u01a9\u0005\u0002\u0000\u0000\u01a9\u01aa\u0003\u001a\r\u0000\u01aa\u01ab"+
		"\u0005\u0002\u0000\u0000\u01ab\u01ac\u0005t\u0000\u0000\u01ac\u0207\u0001"+
		"\u0000\u0000\u0000\u01ad\u01ae\u0005:\u0000\u0000\u01ae\u01b5\u0005+\u0000"+
		"\u0000\u01af\u01b0\u0005s\u0000\u0000\u01b0\u01b1\u0005\u0002\u0000\u0000"+
		"\u01b1\u01b2\u0003\u0016\u000b\u0000\u01b2\u01b3\u0005\u0002\u0000\u0000"+
		"\u01b3\u01b4\u0005t\u0000\u0000\u01b4\u01b6\u0001\u0000\u0000\u0000\u01b5"+
		"\u01af\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6"+
		"\u0207\u0001\u0000\u0000\u0000\u01b7\u01b8\u0005:\u0000\u0000\u01b8\u01b9"+
		"\u0005,\u0000\u0000\u01b9\u01ba\u0005s\u0000\u0000\u01ba\u01bb\u0005\u0002"+
		"\u0000\u0000\u01bb\u01bc\u0003\u001a\r\u0000\u01bc\u01bd\u0005\u0002\u0000"+
		"\u0000\u01bd\u01be\u0005t\u0000\u0000\u01be\u0207\u0001\u0000\u0000\u0000"+
		"\u01bf\u01c0\u0005:\u0000\u0000\u01c0\u01c1\u00050\u0000\u0000\u01c1\u01c2"+
		"\u0005s\u0000\u0000\u01c2\u01c3\u0005\u0002\u0000\u0000\u01c3\u01c4\u0003"+
		"\u001a\r\u0000\u01c4\u01c5\u0005\u0002\u0000\u0000\u01c5\u01c6\u0005t"+
		"\u0000\u0000\u01c6\u0207\u0001\u0000\u0000\u0000\u01c7\u01c8\u0005:\u0000"+
		"\u0000\u01c8\u01c9\u0005.\u0000\u0000\u01c9\u01ca\u0005s\u0000\u0000\u01ca"+
		"\u01cb\u00059\u0000\u0000\u01cb\u01cc\u0005|\u0000\u0000\u01cc\u01cd\u0005"+
		"\u0002\u0000\u0000\u01cd\u01ce\u0003$\u0012\u0000\u01ce\u01cf\u0005\u0002"+
		"\u0000\u0000\u01cf\u01d0\u0005z\u0000\u0000\u01d0\u01d1\u00056\u0000\u0000"+
		"\u01d1\u01d2\u0005|\u0000\u0000\u01d2\u01d3\u0005u\u0000\u0000\u01d3\u01d4"+
		"\u0003\u000e\u0007\u0000\u01d4\u01d5\u0005v\u0000\u0000\u01d5\u01d6\u0005"+
		"t\u0000\u0000\u01d6\u0207\u0001\u0000\u0000\u0000\u01d7\u01d8\u0005:\u0000"+
		"\u0000\u01d8\u01d9\u0005-\u0000\u0000\u01d9\u01da\u0005s\u0000\u0000\u01da"+
		"\u01db\u0005\u0002\u0000\u0000\u01db\u01dc\u0003$\u0012\u0000\u01dc\u01dd"+
		"\u0005\u0002\u0000\u0000\u01dd\u01de\u0005t\u0000\u0000\u01de\u0207\u0001"+
		"\u0000\u0000\u0000\u01df\u01e0\u0005:\u0000\u0000\u01e0\u01e1\u0005-\u0000"+
		"\u0000\u01e1\u01e2\u0005s\u0000\u0000\u01e2\u01e3\u00059\u0000\u0000\u01e3"+
		"\u01e4\u0005|\u0000\u0000\u01e4\u01e5\u0005\u0002\u0000\u0000\u01e5\u01e6"+
		"\u0003$\u0012\u0000\u01e6\u01e7\u0005\u0002\u0000\u0000\u01e7\u01e8\u0005"+
		"z\u0000\u0000\u01e8\u01e9\u00054\u0000\u0000\u01e9\u01ea\u0005|\u0000"+
		"\u0000\u01ea\u01eb\u0003\f\u0006\u0000\u01eb\u01ec\u0005t\u0000\u0000"+
		"\u01ec\u0207\u0001\u0000\u0000\u0000\u01ed\u01ee\u0005:\u0000\u0000\u01ee"+
		"\u01ef\u00051\u0000\u0000\u01ef\u01f0\u0005s\u0000\u0000\u01f0\u01f1\u0005"+
		"\u0002\u0000\u0000\u01f1\u01f2\u0003D\"\u0000\u01f2\u01f3\u0005\u0002"+
		"\u0000\u0000\u01f3\u01f4\u0005t\u0000\u0000\u01f4\u0207\u0001\u0000\u0000"+
		"\u0000\u01f5\u01f6\u0005:\u0000\u0000\u01f6\u01f7\u0005/\u0000\u0000\u01f7"+
		"\u01f8\u0005s\u0000\u0000\u01f8\u01f9\u0005\u0002\u0000\u0000\u01f9\u01fa"+
		"\u0003\u001a\r\u0000\u01fa\u01fb\u0005\u0002\u0000\u0000\u01fb\u01fc\u0005"+
		"t\u0000\u0000\u01fc\u0207\u0001\u0000\u0000\u0000\u01fd\u01fe\u0005:\u0000"+
		"\u0000\u01fe\u01ff\u00052\u0000\u0000\u01ff\u0200\u0005s\u0000\u0000\u0200"+
		"\u0202\u0005\u0002\u0000\u0000\u0201\u0203\u0003\u001a\r\u0000\u0202\u0201"+
		"\u0001\u0000\u0000\u0000\u0202\u0203\u0001\u0000\u0000\u0000\u0203\u0204"+
		"\u0001\u0000\u0000\u0000\u0204\u0205\u0005\u0002\u0000\u0000\u0205\u0207"+
		"\u0005t\u0000\u0000\u0206\u0173\u0001\u0000\u0000\u0000\u0206\u017d\u0001"+
		"\u0000\u0000\u0000\u0206\u0185\u0001\u0000\u0000\u0000\u0206\u018d\u0001"+
		"\u0000\u0000\u0000\u0206\u019d\u0001\u0000\u0000\u0000\u0206\u01a5\u0001"+
		"\u0000\u0000\u0000\u0206\u01ad\u0001\u0000\u0000\u0000\u0206\u01b7\u0001"+
		"\u0000\u0000\u0000\u0206\u01bf\u0001\u0000\u0000\u0000\u0206\u01c7\u0001"+
		"\u0000\u0000\u0000\u0206\u01d7\u0001\u0000\u0000\u0000\u0206\u01df\u0001"+
		"\u0000\u0000\u0000\u0206\u01ed\u0001\u0000\u0000\u0000\u0206\u01f5\u0001"+
		"\u0000\u0000\u0000\u0206\u01fd\u0001\u0000\u0000\u0000\u0207\u000b\u0001"+
		"\u0000\u0000\u0000\u0208\u020d\u0003^/\u0000\u0209\u020a\u0005{\u0000"+
		"\u0000\u020a\u020c\u0003^/\u0000\u020b\u0209\u0001\u0000\u0000\u0000\u020c"+
		"\u020f\u0001\u0000\u0000\u0000\u020d\u020b\u0001\u0000\u0000\u0000\u020d"+
		"\u020e\u0001\u0000\u0000\u0000\u020e\u0210\u0001\u0000\u0000\u0000\u020f"+
		"\u020d\u0001\u0000\u0000\u0000\u0210\u0211\u0005{\u0000\u0000\u0211\u0212"+
		"\u0005C\u0000\u0000\u0212\r\u0001\u0000\u0000\u0000\u0213\u0218\u0003"+
		"\f\u0006\u0000\u0214\u0215\u0005z\u0000\u0000\u0215\u0217\u0003\f\u0006"+
		"\u0000\u0216\u0214\u0001\u0000\u0000\u0000\u0217\u021a\u0001\u0000\u0000"+
		"\u0000\u0218\u0216\u0001\u0000\u0000\u0000\u0218\u0219\u0001\u0000\u0000"+
		"\u0000\u0219\u000f\u0001\u0000\u0000\u0000\u021a\u0218\u0001\u0000\u0000"+
		"\u0000\u021b\u021d\u0005\u001d\u0000\u0000\u021c\u021b\u0001\u0000\u0000"+
		"\u0000\u021c\u021d\u0001\u0000\u0000\u0000\u021d\u0221\u0001\u0000\u0000"+
		"\u0000\u021e\u0220\u0003\u0096K\u0000\u021f\u021e\u0001\u0000\u0000\u0000"+
		"\u0220\u0223\u0001\u0000\u0000\u0000\u0221\u021f\u0001\u0000\u0000\u0000"+
		"\u0221\u0222\u0001\u0000\u0000\u0000\u0222\u0224\u0001\u0000\u0000\u0000"+
		"\u0223\u0221\u0001\u0000\u0000\u0000\u0224\u0225\u0005\b\u0000\u0000\u0225"+
		"\u0228\u0003^/\u0000\u0226\u0227\u0005K\u0000\u0000\u0227\u0229\u0003"+
		"\u00c0`\u0000\u0228\u0226\u0001\u0000\u0000\u0000\u0228\u0229\u0001\u0000"+
		"\u0000\u0000\u0229\u022c\u0001\u0000\u0000\u0000\u022a\u022b\u0005R\u0000"+
		"\u0000\u022b\u022d\u0003\u00a4R\u0000\u022c\u022a\u0001\u0000\u0000\u0000"+
		"\u022c\u022d\u0001\u0000\u0000\u0000\u022d\u022f\u0001\u0000\u0000\u0000"+
		"\u022e\u0230\u0003\u0016\u000b\u0000\u022f\u022e\u0001\u0000\u0000\u0000"+
		"\u022f\u0230\u0001\u0000\u0000\u0000\u0230\u0231\u0001\u0000\u0000\u0000"+
		"\u0231\u0232\u0003\u0002\u0001\u0000\u0232\u0011\u0001\u0000\u0000\u0000"+
		"\u0233\u0235\u0005a\u0000\u0000\u0234\u0233\u0001\u0000\u0000\u0000\u0234"+
		"\u0235\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000\u0000\u0000\u0236"+
		"\u0239\u0003\u0014\n\u0000\u0237\u0238\u0005g\u0000\u0000\u0238\u023a"+
		"\u0003\u00a4R\u0000\u0239\u0237\u0001\u0000\u0000\u0000\u0239\u023a\u0001"+
		"\u0000\u0000\u0000\u023a\u023b\u0001\u0000\u0000\u0000\u023b\u023c\u0005"+
		"\u0082\u0000\u0000\u023c\u023d\u0003\u001a\r\u0000\u023d\u023e\u0003\u00d2"+
		"i\u0000\u023e\u0013\u0001\u0000\u0000\u0000\u023f\u0240\u0005\t\u0000"+
		"\u0000\u0240\u025e\u0003\u00cae\u0000\u0241\u0242\u0005\u0006\u0000\u0000"+
		"\u0242\u025e\u0003\u00cae\u0000\u0243\u0244\u0005\u0006\u0000\u0000\u0244"+
		"\u0245\u0003\u00cae\u0000\u0245\u024b\u0005\u001e\u0000\u0000\u0246\u0248"+
		"\u0005s\u0000\u0000\u0247\u0249\u0003\u00ceg\u0000\u0248\u0247\u0001\u0000"+
		"\u0000\u0000\u0248\u0249\u0001\u0000\u0000\u0000\u0249\u024a\u0001\u0000"+
		"\u0000\u0000\u024a\u024c\u0005t\u0000\u0000\u024b\u0246\u0001\u0000\u0000"+
		"\u0000\u024b\u024c\u0001\u0000\u0000\u0000\u024c\u025e\u0001\u0000\u0000"+
		"\u0000\u024d\u024e\u0005\u0006\u0000\u0000\u024e\u024f\u0003\u00cae\u0000"+
		"\u024f\u0255\u0005#\u0000\u0000\u0250\u0252\u0005s\u0000\u0000\u0251\u0253"+
		"\u0003\u00ceg\u0000\u0252\u0251\u0001\u0000\u0000\u0000\u0252\u0253\u0001"+
		"\u0000\u0000\u0000\u0253\u0254\u0001\u0000\u0000\u0000\u0254\u0256\u0005"+
		"t\u0000\u0000\u0255\u0250\u0001\u0000\u0000\u0000\u0255\u0256\u0001\u0000"+
		"\u0000\u0000\u0256\u025e\u0001\u0000\u0000\u0000\u0257\u025a\u0003\u00c0"+
		"`\u0000\u0258\u025a\u0005j\u0000\u0000\u0259\u0257\u0001\u0000\u0000\u0000"+
		"\u0259\u0258\u0001\u0000\u0000\u0000\u025a\u025b\u0001\u0000\u0000\u0000"+
		"\u025b\u025c\u0005\u0007\u0000\u0000\u025c\u025e\u0003\u00cae\u0000\u025d"+
		"\u023f\u0001\u0000\u0000\u0000\u025d\u0241\u0001\u0000\u0000\u0000\u025d"+
		"\u0243\u0001\u0000\u0000\u0000\u025d\u024d\u0001\u0000\u0000\u0000\u025d"+
		"\u0259\u0001\u0000\u0000\u0000\u025e\u0015\u0001\u0000\u0000\u0000\u025f"+
		"\u0260\u0005\u0017\u0000\u0000\u0260\u0261\u0005s\u0000\u0000\u0261\u0262"+
		"\u0003\u001a\r\u0000\u0262\u0263\u0005t\u0000\u0000\u0263\u027c\u0001"+
		"\u0000\u0000\u0000\u0264\u0265\u0005\u0018\u0000\u0000\u0265\u0266\u0005"+
		"s\u0000\u0000\u0266\u0267\u0003\u001a\r\u0000\u0267\u0268\u0005t\u0000"+
		"\u0000\u0268\u027c\u0001\u0000\u0000\u0000\u0269\u026a\u0005\u0015\u0000"+
		"\u0000\u026a\u026b\u0005s\u0000\u0000\u026b\u026c\u0003\u001a\r\u0000"+
		"\u026c\u026d\u0005t\u0000\u0000\u026d\u027c\u0001\u0000\u0000\u0000\u026e"+
		"\u026f\u0005\u0016\u0000\u0000\u026f\u0270\u0005s\u0000\u0000\u0270\u0271"+
		"\u0003\u001a\r\u0000\u0271\u0272\u0005t\u0000\u0000\u0272\u027c\u0001"+
		"\u0000\u0000\u0000\u0273\u0274\u0005\u0019\u0000\u0000\u0274\u0275\u0005"+
		"s\u0000\u0000\u0275\u0276\u0003$\u0012\u0000\u0276\u0277\u0005t\u0000"+
		"\u0000\u0277\u027c\u0001\u0000\u0000\u0000\u0278\u0279\u0005\u0013\u0000"+
		"\u0000\u0279\u027a\u0005s\u0000\u0000\u027a\u027c\u0005t\u0000\u0000\u027b"+
		"\u025f\u0001\u0000\u0000\u0000\u027b\u0264\u0001\u0000\u0000\u0000\u027b"+
		"\u0269\u0001\u0000\u0000\u0000\u027b\u026e\u0001\u0000\u0000\u0000\u027b"+
		"\u0273\u0001\u0000\u0000\u0000\u027b\u0278\u0001\u0000\u0000\u0000\u027c"+
		"\u0017\u0001\u0000\u0000\u0000\u027d\u0281\u0005;\u0000\u0000\u027e\u0280"+
		"\u0003\u0096K\u0000\u027f\u027e\u0001\u0000\u0000\u0000\u0280\u0283\u0001"+
		"\u0000\u0000\u0000\u0281\u027f\u0001\u0000\u0000\u0000\u0281\u0282\u0001"+
		"\u0000\u0000\u0000\u0282\u0284\u0001\u0000\u0000\u0000\u0283\u0281\u0001"+
		"\u0000\u0000\u0000\u0284\u0285\u0005\u001a\u0000\u0000\u0285\u0286\u0003"+
		"^/\u0000\u0286\u0287\u0003\u00cae\u0000\u0287\u0288\u0005y\u0000\u0000"+
		"\u0288\u0297\u0001\u0000\u0000\u0000\u0289\u028b\u0003\u0096K\u0000\u028a"+
		"\u0289\u0001\u0000\u0000\u0000\u028b\u028e\u0001\u0000\u0000\u0000\u028c"+
		"\u028a\u0001\u0000\u0000\u0000\u028c\u028d\u0001\u0000\u0000\u0000\u028d"+
		"\u028f\u0001\u0000\u0000\u0000\u028e\u028c\u0001\u0000\u0000\u0000\u028f"+
		"\u0290\u0005\u001a\u0000\u0000\u0290\u0291\u0003^/\u0000\u0291\u0292\u0003"+
		"\u00cae\u0000\u0292\u0293\u0005\u0082\u0000\u0000\u0293\u0294\u0003\u001a"+
		"\r\u0000\u0294\u0295\u0005y\u0000\u0000\u0295\u0297\u0001\u0000\u0000"+
		"\u0000\u0296\u027d\u0001\u0000\u0000\u0000\u0296\u028c\u0001\u0000\u0000"+
		"\u0000\u0297\u0019\u0001\u0000\u0000\u0000\u0298\u029b\u0006\r\uffff\uffff"+
		"\u0000\u0299\u029c\u0003\u001c\u000e\u0000\u029a\u029c\u0003\u001e\u000f"+
		"\u0000\u029b\u0299\u0001\u0000\u0000\u0000\u029b\u029a\u0001\u0000\u0000"+
		"\u0000\u029c\u02a4\u0001\u0000\u0000\u0000\u029d\u029e\u0005\u007f\u0000"+
		"\u0000\u029e\u02a4\u0003\u001a\r\u0004\u029f\u02a0\u0005s\u0000\u0000"+
		"\u02a0\u02a1\u0003\u001a\r\u0000\u02a1\u02a2\u0005t\u0000\u0000\u02a2"+
		"\u02a4\u0001\u0000\u0000\u0000\u02a3\u0298\u0001\u0000\u0000\u0000\u02a3"+
		"\u029d\u0001\u0000\u0000\u0000\u02a3\u029f\u0001\u0000\u0000\u0000\u02a4"+
		"\u02ad\u0001\u0000\u0000\u0000\u02a5\u02a6\n\u0002\u0000\u0000\u02a6\u02a7"+
		"\u0005\u0087\u0000\u0000\u02a7\u02ac\u0003\u001a\r\u0003\u02a8\u02a9\n"+
		"\u0001\u0000\u0000\u02a9\u02aa\u0005\u0088\u0000\u0000\u02aa\u02ac\u0003"+
		"\u001a\r\u0002\u02ab\u02a5\u0001\u0000\u0000\u0000\u02ab\u02a8\u0001\u0000"+
		"\u0000\u0000\u02ac\u02af\u0001\u0000\u0000\u0000\u02ad\u02ab\u0001\u0000"+
		"\u0000\u0000\u02ad\u02ae\u0001\u0000\u0000\u0000\u02ae\u001b\u0001\u0000"+
		"\u0000\u0000\u02af\u02ad\u0001\u0000\u0000\u0000\u02b0\u02b1\u0005\n\u0000"+
		"\u0000\u02b1\u02b2\u0005s\u0000\u0000\u02b2\u02b3\u00036\u001b\u0000\u02b3"+
		"\u02b4\u0005t\u0000\u0000\u02b4\u0329\u0001\u0000\u0000\u0000\u02b5\u02b6"+
		"\u0005\u000f\u0000\u0000\u02b6\u02b7\u0005s\u0000\u0000\u02b7\u02b8\u0003"+
		"6\u001b\u0000\u02b8\u02b9\u0005t\u0000\u0000\u02b9\u0329\u0001\u0000\u0000"+
		"\u0000\u02ba\u02bb\u0005\u0012\u0000\u0000\u02bb\u02bc\u0005s\u0000\u0000"+
		"\u02bc\u02bd\u0003F#\u0000\u02bd\u02be\u0005t\u0000\u0000\u02be\u0329"+
		"\u0001\u0000\u0000\u0000\u02bf\u02c0\u0005\u001c\u0000\u0000\u02c0\u02c1"+
		"\u0005s\u0000\u0000\u02c1\u02c2\u0003F#\u0000\u02c2\u02c3\u0005t\u0000"+
		"\u0000\u02c3\u0329\u0001\u0000\u0000\u0000\u02c4\u02c5\u0005!\u0000\u0000"+
		"\u02c5\u02c6\u0005s\u0000\u0000\u02c6\u02c7\u0003*\u0015\u0000\u02c7\u02c8"+
		"\u0005t\u0000\u0000\u02c8\u0329\u0001\u0000\u0000\u0000\u02c9\u02ca\u0005"+
		"\u0010\u0000\u0000\u02ca\u02cb\u0005s\u0000\u0000\u02cb\u02cc\u0003,\u0016"+
		"\u0000\u02cc\u02cd\u0005t\u0000\u0000\u02cd\u0329\u0001\u0000\u0000\u0000"+
		"\u02ce\u02cf\u0005\u001f\u0000\u0000\u02cf\u02d0\u0005s\u0000\u0000\u02d0"+
		"\u02d1\u0003,\u0016\u0000\u02d1\u02d2\u0005t\u0000\u0000\u02d2\u0329\u0001"+
		"\u0000\u0000\u0000\u02d3\u02d4\u0005\u0011\u0000\u0000\u02d4\u02d5\u0005"+
		"s\u0000\u0000\u02d5\u02d6\u0003*\u0015\u0000\u02d6\u02d7\u0005t\u0000"+
		"\u0000\u02d7\u0329\u0001\u0000\u0000\u0000\u02d8\u02d9\u0005\u0003\u0000"+
		"\u0000\u02d9\u02da\u0005s\u0000\u0000\u02da\u0329\u0005t\u0000\u0000\u02db"+
		"\u02dc\u0005%\u0000\u0000\u02dc\u02dd\u0005s\u0000\u0000\u02dd\u02de\u0003"+
		"*\u0015\u0000\u02de\u02df\u0005t\u0000\u0000\u02df\u0329\u0001\u0000\u0000"+
		"\u0000\u02e0\u02e1\u0005&\u0000\u0000\u02e1\u02e2\u0005s\u0000\u0000\u02e2"+
		"\u02e3\u00036\u001b\u0000\u02e3\u02e4\u0005t\u0000\u0000\u02e4\u0329\u0001"+
		"\u0000\u0000\u0000\u02e5\u02e6\u0005\u000b\u0000\u0000\u02e6\u02e7\u0005"+
		"s\u0000\u0000\u02e7\u02e8\u0003\u001a\r\u0000\u02e8\u02e9\u0005t\u0000"+
		"\u0000\u02e9\u0329\u0001\u0000\u0000\u0000\u02ea\u02eb\u0005\f\u0000\u0000"+
		"\u02eb\u02ec\u0005s\u0000\u0000\u02ec\u02ed\u0003\u001a\r\u0000\u02ed"+
		"\u02ee\u0005t\u0000\u0000\u02ee\u0329\u0001\u0000\u0000\u0000\u02ef\u02f0"+
		"\u0005P\u0000\u0000\u02f0\u02f2\u0005s\u0000\u0000\u02f1\u02f3\u0003\u0084"+
		"B\u0000\u02f2\u02f1\u0001\u0000\u0000\u0000\u02f2\u02f3\u0001\u0000\u0000"+
		"\u0000\u02f3\u02f4\u0001\u0000\u0000\u0000\u02f4\u0329\u0005t\u0000\u0000"+
		"\u02f5\u02f6\u0005e\u0000\u0000\u02f6\u02f7\u0005s\u0000\u0000\u02f7\u02f8"+
		"\u0003R)\u0000\u02f8\u02f9\u0005t\u0000\u0000\u02f9\u0329\u0001\u0000"+
		"\u0000\u0000\u02fa\u02fb\u0005\"\u0000\u0000\u02fb\u02fc\u0005s\u0000"+
		"\u0000\u02fc\u02fd\u0003R)\u0000\u02fd\u02fe\u0005t\u0000\u0000\u02fe"+
		"\u0329\u0001\u0000\u0000\u0000\u02ff\u0300\u0005\u0005\u0000\u0000\u0300"+
		"\u0301\u0005s\u0000\u0000\u0301\u0302\u0003\\.\u0000\u0302\u0303\u0005"+
		"t\u0000\u0000\u0303\u0329\u0001\u0000\u0000\u0000\u0304\u0305\u0005:\u0000"+
		"\u0000\u0305\u0306\u0005e\u0000\u0000\u0306\u0307\u0005s\u0000\u0000\u0307"+
		"\u0308\u0003T*\u0000\u0308\u0309\u0005t\u0000\u0000\u0309\u0329\u0001"+
		"\u0000\u0000\u0000\u030a\u030b\u0005:\u0000\u0000\u030b\u030c\u0005\""+
		"\u0000\u0000\u030c\u030d\u0005s\u0000\u0000\u030d\u030e\u0003T*\u0000"+
		"\u030e\u030f\u0005t\u0000\u0000\u030f\u0329\u0001\u0000\u0000\u0000\u0310"+
		"\u0311\u0005:\u0000\u0000\u0311\u0312\u0005\u0005\u0000\u0000\u0312\u0313"+
		"\u0005s\u0000\u0000\u0313\u0314\u0003V+\u0000\u0314\u0315\u0005t\u0000"+
		"\u0000\u0315\u0329\u0001\u0000\u0000\u0000\u0316\u0317\u0005:\u0000\u0000"+
		"\u0317\u0318\u0005%\u0000\u0000\u0318\u0319\u0005s\u0000\u0000\u0319\u031a"+
		"\u0003T*\u0000\u031a\u031b\u0005t\u0000\u0000\u031b\u0329\u0001\u0000"+
		"\u0000\u0000\u031c\u031d\u0005:\u0000\u0000\u031d\u031e\u0005&\u0000\u0000"+
		"\u031e\u031f\u0005s\u0000\u0000\u031f\u0320\u0003T*\u0000\u0320\u0321"+
		"\u0005t\u0000\u0000\u0321\u0329\u0001\u0000\u0000\u0000\u0322\u0323\u0005"+
		":\u0000\u0000\u0323\u0324\u0005\u0004\u0000\u0000\u0324\u0325\u0005s\u0000"+
		"\u0000\u0325\u0326\u0003T*\u0000\u0326\u0327\u0005t\u0000\u0000\u0327"+
		"\u0329\u0001\u0000\u0000\u0000\u0328\u02b0\u0001\u0000\u0000\u0000\u0328"+
		"\u02b5\u0001\u0000\u0000\u0000\u0328\u02ba\u0001\u0000\u0000\u0000\u0328"+
		"\u02bf\u0001\u0000\u0000\u0000\u0328\u02c4\u0001\u0000\u0000\u0000\u0328"+
		"\u02c9\u0001\u0000\u0000\u0000\u0328\u02ce\u0001\u0000\u0000\u0000\u0328"+
		"\u02d3\u0001\u0000\u0000\u0000\u0328\u02d8\u0001\u0000\u0000\u0000\u0328"+
		"\u02db\u0001\u0000\u0000\u0000\u0328\u02e0\u0001\u0000\u0000\u0000\u0328"+
		"\u02e5\u0001\u0000\u0000\u0000\u0328\u02ea\u0001\u0000\u0000\u0000\u0328"+
		"\u02ef\u0001\u0000\u0000\u0000\u0328\u02f5\u0001\u0000\u0000\u0000\u0328"+
		"\u02fa\u0001\u0000\u0000\u0000\u0328\u02ff\u0001\u0000\u0000\u0000\u0328"+
		"\u0304\u0001\u0000\u0000\u0000\u0328\u030a\u0001\u0000\u0000\u0000\u0328"+
		"\u0310\u0001\u0000\u0000\u0000\u0328\u0316\u0001\u0000\u0000\u0000\u0328"+
		"\u031c\u0001\u0000\u0000\u0000\u0328\u0322\u0001\u0000\u0000\u0000\u0329"+
		"\u001d\u0001\u0000\u0000\u0000\u032a\u032b\u0003$\u0012\u0000\u032b\u032c"+
		"\u0005{\u0000\u0000\u032c\u032e\u0001\u0000\u0000\u0000\u032d\u032a\u0001"+
		"\u0000\u0000\u0000\u032d\u032e\u0001\u0000\u0000\u0000\u032e\u032f\u0001"+
		"\u0000\u0000\u0000\u032f\u0330\u0003^/\u0000\u0330\u0331\u0003P(\u0000"+
		"\u0331\u001f\u0001\u0000\u0000\u0000\u0332\u0334\u0003\u0096K\u0000\u0333"+
		"\u0332\u0001\u0000\u0000\u0000\u0334\u0337\u0001\u0000\u0000\u0000\u0335"+
		"\u0333\u0001\u0000\u0000\u0000\u0335\u0336\u0001\u0000\u0000\u0000\u0336"+
		"\u033a\u0001\u0000\u0000\u0000\u0337\u0335\u0001\u0000\u0000\u0000\u0338"+
		"\u033b\u0003\u00c0`\u0000\u0339\u033b\u0005j\u0000\u0000\u033a\u0338\u0001"+
		"\u0000\u0000\u0000\u033a\u0339\u0001\u0000\u0000\u0000\u033b\u033c\u0001"+
		"\u0000\u0000\u0000\u033c\u033d\u0003\u00c0`\u0000\u033d\u033e\u0005{\u0000"+
		"\u0000\u033e\u033f\u0003^/\u0000\u033f\u0342\u0003\u00cae\u0000\u0340"+
		"\u0341\u0005g\u0000\u0000\u0341\u0343\u0003\u00a4R\u0000\u0342\u0340\u0001"+
		"\u0000\u0000\u0000\u0342\u0343\u0001\u0000\u0000\u0000\u0343\u0344\u0001"+
		"\u0000\u0000\u0000\u0344\u0345\u0003\u00d2i\u0000\u0345\u0385\u0001\u0000"+
		"\u0000\u0000\u0346\u0348\u0003\u0096K\u0000\u0347\u0346\u0001\u0000\u0000"+
		"\u0000\u0348\u034b\u0001\u0000\u0000\u0000\u0349\u0347\u0001\u0000\u0000"+
		"\u0000\u0349\u034a\u0001\u0000\u0000\u0000\u034a\u034c\u0001\u0000\u0000"+
		"\u0000\u034b\u0349\u0001\u0000\u0000\u0000\u034c\u0350\u0005;\u0000\u0000"+
		"\u034d\u034f\u0003\u0096K\u0000\u034e\u034d\u0001\u0000\u0000\u0000\u034f"+
		"\u0352\u0001\u0000\u0000\u0000\u0350\u034e\u0001\u0000\u0000\u0000\u0350"+
		"\u0351\u0001\u0000\u0000\u0000\u0351\u0355\u0001\u0000\u0000\u0000\u0352"+
		"\u0350\u0001\u0000\u0000\u0000\u0353\u0356\u0003\u00c0`\u0000\u0354\u0356"+
		"\u0005j\u0000\u0000\u0355\u0353\u0001\u0000\u0000\u0000\u0355\u0354\u0001"+
		"\u0000\u0000\u0000\u0356\u0357\u0001\u0000\u0000\u0000\u0357\u0358\u0003"+
		"\u00c0`\u0000\u0358\u0359\u0005{\u0000\u0000\u0359\u035a\u0003^/\u0000"+
		"\u035a\u035d\u0003\u00cae\u0000\u035b\u035c\u0005g\u0000\u0000\u035c\u035e"+
		"\u0003\u00a4R\u0000\u035d\u035b\u0001\u0000\u0000\u0000\u035d\u035e\u0001"+
		"\u0000\u0000\u0000\u035e\u035f\u0001\u0000\u0000\u0000\u035f\u0360\u0005"+
		"y\u0000\u0000\u0360\u0385\u0001\u0000\u0000\u0000\u0361\u0363\u0003\u0096"+
		"K\u0000\u0362\u0361\u0001\u0000\u0000\u0000\u0363\u0366\u0001\u0000\u0000"+
		"\u0000\u0364\u0362\u0001\u0000\u0000\u0000\u0364\u0365\u0001\u0000\u0000"+
		"\u0000\u0365\u0367\u0001\u0000\u0000\u0000\u0366\u0364\u0001\u0000\u0000"+
		"\u0000\u0367\u0368\u0003\u00c0`\u0000\u0368\u0369\u0005{\u0000\u0000\u0369"+
		"\u036a\u0005Y\u0000\u0000\u036a\u036d\u0003\u00cae\u0000\u036b\u036c\u0005"+
		"g\u0000\u0000\u036c\u036e\u0003\u00a4R\u0000\u036d\u036b\u0001\u0000\u0000"+
		"\u0000\u036d\u036e\u0001\u0000\u0000\u0000\u036e\u036f\u0001\u0000\u0000"+
		"\u0000\u036f\u0370\u0003\u00d2i\u0000\u0370\u0385\u0001\u0000\u0000\u0000"+
		"\u0371\u0373\u0003\u0096K\u0000\u0372\u0371\u0001\u0000\u0000\u0000\u0373"+
		"\u0376\u0001\u0000\u0000\u0000\u0374\u0372\u0001\u0000\u0000\u0000\u0374"+
		"\u0375\u0001\u0000\u0000\u0000\u0375\u0379\u0001\u0000\u0000\u0000\u0376"+
		"\u0374\u0001\u0000\u0000\u0000\u0377\u037a\u0003\u00c0`\u0000\u0378\u037a"+
		"\u0005j\u0000\u0000\u0379\u0377\u0001\u0000\u0000\u0000\u0379\u0378\u0001"+
		"\u0000\u0000\u0000\u037a\u037b\u0001\u0000\u0000\u0000\u037b\u037c\u0003"+
		"\u00c0`\u0000\u037c\u037d\u0005{\u0000\u0000\u037d\u0380\u0003^/\u0000"+
		"\u037e\u037f\u0005|\u0000\u0000\u037f\u0381\u0003\u0084B\u0000\u0380\u037e"+
		"\u0001\u0000\u0000\u0000\u0380\u0381\u0001\u0000\u0000\u0000\u0381\u0382"+
		"\u0001\u0000\u0000\u0000\u0382\u0383\u0005y\u0000\u0000\u0383\u0385\u0001"+
		"\u0000\u0000\u0000\u0384\u0335\u0001\u0000\u0000\u0000\u0384\u0349\u0001"+
		"\u0000\u0000\u0000\u0384\u0364\u0001\u0000\u0000\u0000\u0384\u0374\u0001"+
		"\u0000\u0000\u0000\u0385!\u0001\u0000\u0000\u0000\u0386\u0387\u0005\r"+
		"\u0000\u0000\u0387\u0388\u0005\u0014\u0000\u0000\u0388\u0389\u0005\u0082"+
		"\u0000\u0000\u0389\u038a\u0003$\u0012\u0000\u038a\u038b\u0005K\u0000\u0000"+
		"\u038b\u038c\u0003\u00c0`\u0000\u038c\u038d\u0005y\u0000\u0000\u038d\u03d9"+
		"\u0001\u0000\u0000\u0000\u038e\u038f\u0005\r\u0000\u0000\u038f\u0390\u0005"+
		"\u0014\u0000\u0000\u0390\u0391\u0005\u0082\u0000\u0000\u0391\u0392\u0003"+
		"$\u0012\u0000\u0392\u0393\u0005R\u0000\u0000\u0393\u0394\u0003\u00a4R"+
		"\u0000\u0394\u0395\u0005y\u0000\u0000\u0395\u03d9\u0001\u0000\u0000\u0000"+
		"\u0396\u0397\u0005\r\u0000\u0000\u0397\u0398\u0005$\u0000\u0000\u0398"+
		"\u0399\u0005\u0082\u0000\u0000\u0399\u039a\u0003\u001a\r\u0000\u039a\u039b"+
		"\u0005\u0082\u0000\u0000\u039b\u039c\u0005q\u0000\u0000\u039c\u039d\u0005"+
		"y\u0000\u0000\u039d\u03d9\u0001\u0000\u0000\u0000\u039e\u039f\u0005\r"+
		"\u0000\u0000\u039f\u03a0\u0005\u000e\u0000\u0000\u03a0\u03a1\u0005\u0082"+
		"\u0000\u0000\u03a1\u03a2\u0003\u001a\r\u0000\u03a2\u03a3\u0005\u0082\u0000"+
		"\u0000\u03a3\u03a4\u0005q\u0000\u0000\u03a4\u03a5\u0005y\u0000\u0000\u03a5"+
		"\u03d9\u0001\u0000\u0000\u0000\u03a6\u03a7\u0005\r\u0000\u0000\u03a7\u03a8"+
		"\u0005 \u0000\u0000\u03a8\u03a9\u0005\u0082\u0000\u0000\u03a9\u03aa\u0003"+
		"\u00c0`\u0000\u03aa\u03ab\u0005\u0082\u0000\u0000\u03ab\u03ac\u0003\u001a"+
		"\r\u0000\u03ac\u03ad\u0005y\u0000\u0000\u03ad\u03d9\u0001\u0000\u0000"+
		"\u0000\u03ae\u03af\u0005\r\u0000\u0000\u03af\u03b0\u0005\u001b\u0000\u0000"+
		"\u03b0\u03b1\u0005\u0082\u0000\u0000\u03b1\u03b2\u0003D\"\u0000\u03b2"+
		"\u03b3\u0005y\u0000\u0000\u03b3\u03d9\u0001\u0000\u0000\u0000\u03b4\u03b5"+
		"\u0005\r\u0000\u0000\u03b5\u03b6\u0005:\u0000\u0000\u03b6\u03b7\u0005"+
		"7\u0000\u0000\u03b7\u03b8\u0005\u0082\u0000\u0000\u03b8\u03b9\u0003$\u0012"+
		"\u0000\u03b9\u03ba\u0005\u0082\u0000\u0000\u03ba\u03bb\u0003\n\u0005\u0000"+
		"\u03bb\u03bc\u0005y\u0000\u0000\u03bc\u03d9\u0001\u0000\u0000\u0000\u03bd"+
		"\u03be\u0005\r\u0000\u0000\u03be\u03bf\u0005:\u0000\u0000\u03bf\u03c0"+
		"\u00058\u0000\u0000\u03c0\u03c1\u0005\u0082\u0000\u0000\u03c1\u03c2\u0003"+
		"8\u001c\u0000\u03c2\u03c3\u0005\u0082\u0000\u0000\u03c3\u03c4\u0003\n"+
		"\u0005\u0000\u03c4\u03c5\u0005y\u0000\u0000\u03c5\u03d9\u0001\u0000\u0000"+
		"\u0000\u03c6\u03c7\u0005\r\u0000\u0000\u03c7\u03c8\u0005:\u0000\u0000"+
		"\u03c8\u03c9\u00053\u0000\u0000\u03c9\u03ca\u0005\u0082\u0000\u0000\u03ca"+
		"\u03cb\u0003F#\u0000\u03cb\u03cc\u0005\u0082\u0000\u0000\u03cc\u03cd\u0003"+
		"\n\u0005\u0000\u03cd\u03ce\u0005y\u0000\u0000\u03ce\u03d9\u0001\u0000"+
		"\u0000\u0000\u03cf\u03d0\u0005\r\u0000\u0000\u03d0\u03d1\u0005:\u0000"+
		"\u0000\u03d1\u03d2\u00055\u0000\u0000\u03d2\u03d3\u0005\u0082\u0000\u0000"+
		"\u03d3\u03d4\u0003,\u0016\u0000\u03d4\u03d5\u0005\u0082\u0000\u0000\u03d5"+
		"\u03d6\u0003\n\u0005\u0000\u03d6\u03d7\u0005y\u0000\u0000\u03d7\u03d9"+
		"\u0001\u0000\u0000\u0000\u03d8\u0386\u0001\u0000\u0000\u0000\u03d8\u038e"+
		"\u0001\u0000\u0000\u0000\u03d8\u0396\u0001\u0000\u0000\u0000\u03d8\u039e"+
		"\u0001\u0000\u0000\u0000\u03d8\u03a6\u0001\u0000\u0000\u0000\u03d8\u03ae"+
		"\u0001\u0000\u0000\u0000\u03d8\u03b4\u0001\u0000\u0000\u0000\u03d8\u03bd"+
		"\u0001\u0000\u0000\u0000\u03d8\u03c6\u0001\u0000\u0000\u0000\u03d8\u03cf"+
		"\u0001\u0000\u0000\u0000\u03d9#\u0001\u0000\u0000\u0000\u03da\u03db\u0006"+
		"\u0012\uffff\uffff\u0000\u03db\u03e6\u0003&\u0013\u0000\u03dc\u03dd\u0005"+
		"\u007f\u0000\u0000\u03dd\u03e6\u0003$\u0012\u0004\u03de\u03e0\u0005s\u0000"+
		"\u0000\u03df\u03e1\u0003L&\u0000\u03e0\u03df\u0001\u0000\u0000\u0000\u03e0"+
		"\u03e1\u0001\u0000\u0000\u0000\u03e1\u03e2\u0001\u0000\u0000\u0000\u03e2"+
		"\u03e3\u0003$\u0012\u0000\u03e3\u03e4\u0005t\u0000\u0000\u03e4\u03e6\u0001"+
		"\u0000\u0000\u0000\u03e5\u03da\u0001\u0000\u0000\u0000\u03e5\u03dc\u0001"+
		"\u0000\u0000\u0000\u03e5\u03de\u0001\u0000\u0000\u0000\u03e6\u03ef\u0001"+
		"\u0000\u0000\u0000\u03e7\u03e8\n\u0002\u0000\u0000\u03e8\u03e9\u0005\u0087"+
		"\u0000\u0000\u03e9\u03ee\u0003$\u0012\u0003\u03ea\u03eb\n\u0001\u0000"+
		"\u0000\u03eb\u03ec\u0005\u0088\u0000\u0000\u03ec\u03ee\u0003$\u0012\u0002"+
		"\u03ed\u03e7\u0001\u0000\u0000\u0000\u03ed\u03ea\u0001\u0000\u0000\u0000"+
		"\u03ee\u03f1\u0001\u0000\u0000\u0000\u03ef\u03ed\u0001\u0000\u0000\u0000"+
		"\u03ef\u03f0\u0001\u0000\u0000\u0000\u03f0%\u0001\u0000\u0000\u0000\u03f1"+
		"\u03ef\u0001\u0000\u0000\u0000\u03f2\u03f4\u0003(\u0014\u0000\u03f3\u03f5"+
		"\u0005\u008b\u0000\u0000\u03f4\u03f3\u0001\u0000\u0000\u0000\u03f4\u03f5"+
		"\u0001\u0000\u0000\u0000\u03f5\u03fa\u0001\u0000\u0000\u0000\u03f6\u03f7"+
		"\u0005w\u0000\u0000\u03f7\u03f9\u0005x\u0000\u0000\u03f8\u03f6\u0001\u0000"+
		"\u0000\u0000\u03f9\u03fc\u0001\u0000\u0000\u0000\u03fa\u03f8\u0001\u0000"+
		"\u0000\u0000\u03fa\u03fb\u0001\u0000\u0000\u0000\u03fb\'\u0001\u0000\u0000"+
		"\u0000\u03fc\u03fa\u0001\u0000\u0000\u0000\u03fd\u0403\u0003\u00c0`\u0000"+
		"\u03fe\u0403\u0003^/\u0000\u03ff\u0403\u0005\u008d\u0000\u0000\u0400\u0403"+
		"\u0005{\u0000\u0000\u0401\u0403\u0005\u0001\u0000\u0000\u0402\u03fd\u0001"+
		"\u0000\u0000\u0000\u0402\u03fe\u0001\u0000\u0000\u0000\u0402\u03ff\u0001"+
		"\u0000\u0000\u0000\u0402\u0400\u0001\u0000\u0000\u0000\u0402\u0401\u0001"+
		"\u0000\u0000\u0000\u0403\u0404\u0001\u0000\u0000\u0000\u0404\u0402\u0001"+
		"\u0000\u0000\u0000\u0404\u0405\u0001\u0000\u0000\u0000\u0405\u0408\u0001"+
		"\u0000\u0000\u0000\u0406\u0408\u0005j\u0000\u0000\u0407\u0402\u0001\u0000"+
		"\u0000\u0000\u0407\u0406\u0001\u0000\u0000\u0000\u0408)\u0001\u0000\u0000"+
		"\u0000\u0409\u040b\u0005s\u0000\u0000\u040a\u040c\u0003L&\u0000\u040b"+
		"\u040a\u0001\u0000\u0000\u0000\u040b\u040c\u0001\u0000\u0000\u0000\u040c"+
		"\u040d\u0001\u0000\u0000\u0000\u040d\u040e\u0003$\u0012\u0000\u040e\u040f"+
		"\u0005t\u0000\u0000\u040f\u0415\u0001\u0000\u0000\u0000\u0410\u0412\u0003"+
		"L&\u0000\u0411\u0410\u0001\u0000\u0000\u0000\u0411\u0412\u0001\u0000\u0000"+
		"\u0000\u0412\u0413\u0001\u0000\u0000\u0000\u0413\u0415\u0003$\u0012\u0000"+
		"\u0414\u0409\u0001\u0000\u0000\u0000\u0414\u0411\u0001\u0000\u0000\u0000"+
		"\u0415+\u0001\u0000\u0000\u0000\u0416\u0418\u0003L&\u0000\u0417\u0416"+
		"\u0001\u0000\u0000\u0000\u0417\u0418\u0001\u0000\u0000\u0000\u0418\u041a"+
		"\u0001\u0000\u0000\u0000\u0419\u041b\u0003.\u0017\u0000\u041a\u0419\u0001"+
		"\u0000\u0000\u0000\u041a\u041b\u0001\u0000\u0000\u0000\u041b\u041c\u0001"+
		"\u0000\u0000\u0000\u041c\u0420\u0003$\u0012\u0000\u041d\u041e\u0003$\u0012"+
		"\u0000\u041e\u041f\u00032\u0019\u0000\u041f\u0421\u0001\u0000\u0000\u0000"+
		"\u0420\u041d\u0001\u0000\u0000\u0000\u0420\u0421\u0001\u0000\u0000\u0000"+
		"\u0421\u0422\u0001\u0000\u0000\u0000\u0422\u0423\u00034\u001a\u0000\u0423"+
		"-\u0001\u0000\u0000\u0000\u0424\u0426\u0005\u007f\u0000\u0000\u0425\u0424"+
		"\u0001\u0000\u0000\u0000\u0425\u0426\u0001\u0000\u0000\u0000\u0426\u0427"+
		"\u0001\u0000\u0000\u0000\u0427\u042b\u00030\u0018\u0000\u0428\u042a\u0003"+
		".\u0017\u0000\u0429\u0428\u0001\u0000\u0000\u0000\u042a\u042d\u0001\u0000"+
		"\u0000\u0000\u042b\u0429\u0001\u0000\u0000\u0000\u042b\u042c\u0001\u0000"+
		"\u0000\u0000\u042c/\u0001\u0000\u0000\u0000\u042d\u042b\u0001\u0000\u0000"+
		"\u0000\u042e\u042f\u0007\u0000\u0000\u0000\u042f1\u0001\u0000\u0000\u0000"+
		"\u0430\u0431\u0007\u0001\u0000\u0000\u04313\u0001\u0000\u0000\u0000\u0432"+
		"\u0437\u0003^/\u0000\u0433\u0434\u0005\u008d\u0000\u0000\u0434\u0436\u0003"+
		"^/\u0000\u0435\u0433\u0001\u0000\u0000\u0000\u0436\u0439\u0001\u0000\u0000"+
		"\u0000\u0437\u0435\u0001\u0000\u0000\u0000\u0437\u0438\u0001\u0000\u0000"+
		"\u0000\u0438\u043b\u0001\u0000\u0000\u0000\u0439\u0437\u0001\u0000\u0000"+
		"\u0000\u043a\u043c\u0005\u008d\u0000\u0000\u043b\u043a\u0001\u0000\u0000"+
		"\u0000\u043b\u043c\u0001\u0000\u0000\u0000\u043c\u044a\u0001\u0000\u0000"+
		"\u0000\u043d\u0443\u0005\u008d\u0000\u0000\u043e\u043f\u0003^/\u0000\u043f"+
		"\u0440\u0005\u008d\u0000\u0000\u0440\u0442\u0001\u0000\u0000\u0000\u0441"+
		"\u043e\u0001\u0000\u0000\u0000\u0442\u0445\u0001\u0000\u0000\u0000\u0443"+
		"\u0441\u0001\u0000\u0000\u0000\u0443\u0444\u0001\u0000\u0000\u0000\u0444"+
		"\u0447\u0001\u0000\u0000\u0000\u0445\u0443\u0001\u0000\u0000\u0000\u0446"+
		"\u0448\u0003^/\u0000\u0447\u0446\u0001\u0000\u0000\u0000\u0447\u0448\u0001"+
		"\u0000\u0000\u0000\u0448\u044a\u0001\u0000\u0000\u0000\u0449\u0432\u0001"+
		"\u0000\u0000\u0000\u0449\u043d\u0001\u0000\u0000\u0000\u044a5\u0001\u0000"+
		"\u0000\u0000\u044b\u044e\u00038\u001c\u0000\u044c\u044e\u0003F#\u0000"+
		"\u044d\u044b\u0001\u0000\u0000\u0000\u044d\u044c\u0001\u0000\u0000\u0000"+
		"\u044e7\u0001\u0000\u0000\u0000\u044f\u0451\u0003L&\u0000\u0450\u044f"+
		"\u0001\u0000\u0000\u0000\u0450\u0451\u0001\u0000\u0000\u0000\u0451\u0453"+
		"\u0001\u0000\u0000\u0000\u0452\u0454\u0003:\u001d\u0000\u0453\u0452\u0001"+
		"\u0000\u0000\u0000\u0453\u0454\u0001\u0000\u0000\u0000\u0454\u0455\u0001"+
		"\u0000\u0000\u0000\u0455\u0459\u0003$\u0012\u0000\u0456\u0457\u0003$\u0012"+
		"\u0000\u0457\u0458\u00032\u0019\u0000\u0458\u045a\u0001\u0000\u0000\u0000"+
		"\u0459\u0456\u0001\u0000\u0000\u0000\u0459\u045a\u0001\u0000\u0000\u0000"+
		"\u045a\u045b\u0001\u0000\u0000\u0000\u045b\u045c\u00034\u001a\u0000\u045c"+
		"\u045e\u0003P(\u0000\u045d\u045f\u0003B!\u0000\u045e\u045d\u0001\u0000"+
		"\u0000\u0000\u045e\u045f\u0001\u0000\u0000\u0000\u045f9\u0001\u0000\u0000"+
		"\u0000\u0460\u0462\u0005\u007f\u0000\u0000\u0461\u0460\u0001\u0000\u0000"+
		"\u0000\u0461\u0462\u0001\u0000\u0000\u0000\u0462\u0463\u0001\u0000\u0000"+
		"\u0000\u0463\u0467\u0003<\u001e\u0000\u0464\u0466\u0003:\u001d\u0000\u0465"+
		"\u0464\u0001\u0000\u0000\u0000\u0466\u0469\u0001\u0000\u0000\u0000\u0467"+
		"\u0465\u0001\u0000\u0000\u0000\u0467\u0468\u0001\u0000\u0000\u0000\u0468"+
		";\u0001\u0000\u0000\u0000\u0469\u0467\u0001\u0000\u0000\u0000\u046a\u046b"+
		"\u0007\u0002\u0000\u0000\u046b=\u0001\u0000\u0000\u0000\u046c\u0471\u0005"+
		"\u0001\u0000\u0000\u046d\u046e\u0005z\u0000\u0000\u046e\u0470\u0003@ "+
		"\u0000\u046f\u046d\u0001\u0000\u0000\u0000\u0470\u0473\u0001\u0000\u0000"+
		"\u0000\u0471\u046f\u0001\u0000\u0000\u0000\u0471\u0472\u0001\u0000\u0000"+
		"\u0000\u0472\u0480\u0001\u0000\u0000\u0000\u0473\u0471\u0001\u0000\u0000"+
		"\u0000\u0474\u0479\u0003*\u0015\u0000\u0475\u0476\u0005z\u0000\u0000\u0476"+
		"\u0478\u0003>\u001f\u0000\u0477\u0475\u0001\u0000\u0000\u0000\u0478\u047b"+
		"\u0001\u0000\u0000\u0000\u0479\u0477\u0001\u0000\u0000\u0000\u0479\u047a"+
		"\u0001\u0000\u0000\u0000\u047a\u0480\u0001\u0000\u0000\u0000\u047b\u0479"+
		"\u0001\u0000\u0000\u0000\u047c\u047d\u0003$\u0012\u0000\u047d\u047e\u0005"+
		"\u009f\u0000\u0000\u047e\u0480\u0001\u0000\u0000\u0000\u047f\u046c\u0001"+
		"\u0000\u0000\u0000\u047f\u0474\u0001\u0000\u0000\u0000\u047f\u047c\u0001"+
		"\u0000\u0000\u0000\u0480?\u0001\u0000\u0000\u0000\u0481\u0486\u0003*\u0015"+
		"\u0000\u0482\u0483\u0005z\u0000\u0000\u0483\u0485\u0003@ \u0000\u0484"+
		"\u0482\u0001\u0000\u0000\u0000\u0485\u0488\u0001\u0000\u0000\u0000\u0486"+
		"\u0484\u0001\u0000\u0000\u0000\u0486\u0487\u0001\u0000\u0000\u0000\u0487"+
		"\u048d\u0001\u0000\u0000\u0000\u0488\u0486\u0001\u0000\u0000\u0000\u0489"+
		"\u048a\u0003$\u0012\u0000\u048a\u048b\u0005\u009f\u0000\u0000\u048b\u048d"+
		"\u0001\u0000\u0000\u0000\u048c\u0481\u0001\u0000\u0000\u0000\u048c\u0489"+
		"\u0001\u0000\u0000\u0000\u048dA\u0001\u0000\u0000\u0000\u048e\u048f\u0005"+
		"g\u0000\u0000\u048f\u0490\u0003D\"\u0000\u0490C\u0001\u0000\u0000\u0000"+
		"\u0491\u0496\u0003$\u0012\u0000\u0492\u0493\u0005z\u0000\u0000\u0493\u0495"+
		"\u0003$\u0012\u0000\u0494\u0492\u0001\u0000\u0000\u0000\u0495\u0498\u0001"+
		"\u0000\u0000\u0000\u0496\u0494\u0001\u0000\u0000\u0000\u0496\u0497\u0001"+
		"\u0000\u0000\u0000\u0497E\u0001\u0000\u0000\u0000\u0498\u0496\u0001\u0000"+
		"\u0000\u0000\u0499\u049b\u0003L&\u0000\u049a\u0499\u0001\u0000\u0000\u0000"+
		"\u049a\u049b\u0001\u0000\u0000\u0000\u049b\u049d\u0001\u0000\u0000\u0000"+
		"\u049c\u049e\u0003H$\u0000\u049d\u049c\u0001\u0000\u0000\u0000\u049d\u049e"+
		"\u0001\u0000\u0000\u0000\u049e\u04a2\u0001\u0000\u0000\u0000\u049f\u04a0"+
		"\u0003$\u0012\u0000\u04a0\u04a1\u00032\u0019\u0000\u04a1\u04a3\u0001\u0000"+
		"\u0000\u0000\u04a2\u049f\u0001\u0000\u0000\u0000\u04a2\u04a3\u0001\u0000"+
		"\u0000\u0000\u04a3\u04a4\u0001\u0000\u0000\u0000\u04a4\u04a5\u0005Y\u0000"+
		"\u0000\u04a5\u04a7\u0003P(\u0000\u04a6\u04a8\u0003B!\u0000\u04a7\u04a6"+
		"\u0001\u0000\u0000\u0000\u04a7\u04a8\u0001\u0000\u0000\u0000\u04a8G\u0001"+
		"\u0000\u0000\u0000\u04a9\u04ab\u0005\u007f\u0000\u0000\u04aa\u04a9\u0001"+
		"\u0000\u0000\u0000\u04aa\u04ab\u0001\u0000\u0000\u0000\u04ab\u04ac\u0001"+
		"\u0000\u0000\u0000\u04ac\u04b0\u0003J%\u0000\u04ad\u04af\u0003H$\u0000"+
		"\u04ae\u04ad\u0001\u0000\u0000\u0000\u04af\u04b2\u0001\u0000\u0000\u0000"+
		"\u04b0\u04ae\u0001\u0000\u0000\u0000\u04b0\u04b1\u0001\u0000\u0000\u0000"+
		"\u04b1I\u0001\u0000\u0000\u0000\u04b2\u04b0\u0001\u0000\u0000\u0000\u04b3"+
		"\u04b4\u0007\u0003\u0000\u0000\u04b4K\u0001\u0000\u0000\u0000\u04b5\u04b7"+
		"\u0005\u007f\u0000\u0000\u04b6\u04b5\u0001\u0000\u0000\u0000\u04b6\u04b7"+
		"\u0001\u0000\u0000\u0000\u04b7\u04b8\u0001\u0000\u0000\u0000\u04b8\u04b9"+
		"\u0005:\u0000\u0000\u04b9\u04bd\u0003N\'\u0000\u04ba\u04bc\u0003L&\u0000"+
		"\u04bb\u04ba\u0001\u0000\u0000\u0000\u04bc\u04bf\u0001\u0000\u0000\u0000"+
		"\u04bd\u04bb\u0001\u0000\u0000\u0000\u04bd\u04be\u0001\u0000\u0000\u0000"+
		"\u04beM\u0001\u0000\u0000\u0000\u04bf\u04bd\u0001\u0000\u0000\u0000\u04c0"+
		"\u04c6\u0003x<\u0000\u04c1\u04c2\u0005s\u0000\u0000\u04c2\u04c3\u0003"+
		"$\u0012\u0000\u04c3\u04c4\u0005t\u0000\u0000\u04c4\u04c6\u0001\u0000\u0000"+
		"\u0000\u04c5\u04c0\u0001\u0000\u0000\u0000\u04c5\u04c1\u0001\u0000\u0000"+
		"\u0000\u04c6O\u0001\u0000\u0000\u0000\u04c7\u04c9\u0005s\u0000\u0000\u04c8"+
		"\u04ca\u0003>\u001f\u0000\u04c9\u04c8\u0001\u0000\u0000\u0000\u04c9\u04ca"+
		"\u0001\u0000\u0000\u0000\u04ca\u04cb\u0001\u0000\u0000\u0000\u04cb\u04cc"+
		"\u0005t\u0000\u0000\u04ccQ\u0001\u0000\u0000\u0000\u04cd\u04d0\u0003\u00c0"+
		"`\u0000\u04ce\u04d0\u0003r9\u0000\u04cf\u04cd\u0001\u0000\u0000\u0000"+
		"\u04cf\u04ce\u0001\u0000\u0000\u0000\u04d0S\u0001\u0000\u0000\u0000\u04d1"+
		"\u04d4\u0003x<\u0000\u04d2\u04d4\u0003^/\u0000\u04d3\u04d1\u0001\u0000"+
		"\u0000\u0000\u04d3\u04d2\u0001\u0000\u0000\u0000\u04d4U\u0001\u0000\u0000"+
		"\u0000\u04d5\u04d8\u0005\u0001\u0000\u0000\u04d6\u04d7\u0005z\u0000\u0000"+
		"\u04d7\u04d9\u0003X,\u0000\u04d8\u04d6\u0001\u0000\u0000\u0000\u04d8\u04d9"+
		"\u0001\u0000\u0000\u0000\u04d9\u04eb\u0001\u0000\u0000\u0000\u04da\u04df"+
		"\u0003T*\u0000\u04db\u04dc\u0005z\u0000\u0000\u04dc\u04de\u0003V+\u0000"+
		"\u04dd\u04db\u0001\u0000\u0000\u0000\u04de\u04e1\u0001\u0000\u0000\u0000"+
		"\u04df\u04dd\u0001\u0000\u0000\u0000\u04df\u04e0\u0001\u0000\u0000\u0000"+
		"\u04e0\u04eb\u0001\u0000\u0000\u0000\u04e1\u04df\u0001\u0000\u0000\u0000"+
		"\u04e2\u04e7\u0005\u008d\u0000\u0000\u04e3\u04e4\u0005z\u0000\u0000\u04e4"+
		"\u04e6\u0003V+\u0000\u04e5\u04e3\u0001\u0000\u0000\u0000\u04e6\u04e9\u0001"+
		"\u0000\u0000\u0000\u04e7\u04e5\u0001\u0000\u0000\u0000\u04e7\u04e8\u0001"+
		"\u0000\u0000\u0000\u04e8\u04eb\u0001\u0000\u0000\u0000\u04e9\u04e7\u0001"+
		"\u0000\u0000\u0000\u04ea\u04d5\u0001\u0000\u0000\u0000\u04ea\u04da\u0001"+
		"\u0000\u0000\u0000\u04ea\u04e2\u0001\u0000\u0000\u0000\u04ebW\u0001\u0000"+
		"\u0000\u0000\u04ec\u04f1\u0003T*\u0000\u04ed\u04ee\u0005z\u0000\u0000"+
		"\u04ee\u04f0\u0003X,\u0000\u04ef\u04ed\u0001\u0000\u0000\u0000\u04f0\u04f3"+
		"\u0001\u0000\u0000\u0000\u04f1\u04ef\u0001\u0000\u0000\u0000\u04f1\u04f2"+
		"\u0001\u0000\u0000\u0000\u04f2\u04fd\u0001\u0000\u0000\u0000\u04f3\u04f1"+
		"\u0001\u0000\u0000\u0000\u04f4\u04f9\u0005\u008d\u0000\u0000\u04f5\u04f6"+
		"\u0005z\u0000\u0000\u04f6\u04f8\u0003X,\u0000\u04f7\u04f5\u0001\u0000"+
		"\u0000\u0000\u04f8\u04fb\u0001\u0000\u0000\u0000\u04f9\u04f7\u0001\u0000"+
		"\u0000\u0000\u04f9\u04fa\u0001\u0000\u0000\u0000\u04fa\u04fd\u0001\u0000"+
		"\u0000\u0000\u04fb\u04f9\u0001\u0000\u0000\u0000\u04fc\u04ec\u0001\u0000"+
		"\u0000\u0000\u04fc\u04f4\u0001\u0000\u0000\u0000\u04fdY\u0001\u0000\u0000"+
		"\u0000\u04fe\u0501\u0003R)\u0000\u04ff\u0501\u0007\u0004\u0000\u0000\u0500"+
		"\u04fe\u0001\u0000\u0000\u0000\u0500\u04ff\u0001\u0000\u0000\u0000\u0501"+
		"[\u0001\u0000\u0000\u0000\u0502\u0507\u0003Z-\u0000\u0503\u0504\u0005"+
		"z\u0000\u0000\u0504\u0506\u0003Z-\u0000\u0505\u0503\u0001\u0000\u0000"+
		"\u0000\u0506\u0509\u0001\u0000\u0000\u0000\u0507\u0505\u0001\u0000\u0000"+
		"\u0000\u0507\u0508\u0001\u0000\u0000\u0000\u0508]\u0001\u0000\u0000\u0000"+
		"\u0509\u0507\u0001\u0000\u0000\u0000\u050a\u050d\u0007\u0005\u0000\u0000"+
		"\u050b\u050d\u0005\u009e\u0000\u0000\u050c\u050a\u0001\u0000\u0000\u0000"+
		"\u050c\u050b\u0001\u0000\u0000\u0000\u050d_\u0001\u0000\u0000\u0000\u050e"+
		"\u050f\u0005C\u0000\u0000\u050f\u0511\u0003^/\u0000\u0510\u0512\u0003"+
		"\u009cN\u0000\u0511\u0510\u0001\u0000\u0000\u0000\u0511\u0512\u0001\u0000"+
		"\u0000\u0000\u0512\u0515\u0001\u0000\u0000\u0000\u0513\u0514\u0005K\u0000"+
		"\u0000\u0514\u0516\u0003\u00c0`\u0000\u0515\u0513\u0001\u0000\u0000\u0000"+
		"\u0515\u0516\u0001\u0000\u0000\u0000\u0516\u0519\u0001\u0000\u0000\u0000"+
		"\u0517\u0518\u0005R\u0000\u0000\u0518\u051a\u0003\u00a4R\u0000\u0519\u0517"+
		"\u0001\u0000\u0000\u0000\u0519\u051a\u0001\u0000\u0000\u0000\u051a\u051b"+
		"\u0001\u0000\u0000\u0000\u051b\u051c\u0003\u00a6S\u0000\u051ca\u0001\u0000"+
		"\u0000\u0000\u051d\u0520\u0003^/\u0000\u051e\u051f\u0005K\u0000\u0000"+
		"\u051f\u0521\u0003\u009eO\u0000\u0520\u051e\u0001\u0000\u0000\u0000\u0520"+
		"\u0521\u0001\u0000\u0000\u0000\u0521c\u0001\u0000\u0000\u0000\u0522\u0523"+
		"\u0005J\u0000\u0000\u0523\u0526\u0003^/\u0000\u0524\u0525\u0005R\u0000"+
		"\u0000\u0525\u0527\u0003\u00a4R\u0000\u0526\u0524\u0001\u0000\u0000\u0000"+
		"\u0526\u0527\u0001\u0000\u0000\u0000\u0527\u0528\u0001\u0000\u0000\u0000"+
		"\u0528\u052a\u0005u\u0000\u0000\u0529\u052b\u0003\u00a0P\u0000\u052a\u0529"+
		"\u0001\u0000\u0000\u0000\u052a\u052b\u0001\u0000\u0000\u0000\u052b\u052d"+
		"\u0001\u0000\u0000\u0000\u052c\u052e\u0005z\u0000\u0000\u052d\u052c\u0001"+
		"\u0000\u0000\u0000\u052d\u052e\u0001\u0000\u0000\u0000\u052e\u0530\u0001"+
		"\u0000\u0000\u0000\u052f\u0531\u0003\u00a2Q\u0000\u0530\u052f\u0001\u0000"+
		"\u0000\u0000\u0530\u0531\u0001\u0000\u0000\u0000\u0531\u0532\u0001\u0000"+
		"\u0000\u0000\u0532\u0533\u0005v\u0000\u0000\u0533e\u0001\u0000\u0000\u0000"+
		"\u0534\u0536\u0003\n\u0005\u0000\u0535\u0534\u0001\u0000\u0000\u0000\u0536"+
		"\u0539\u0001\u0000\u0000\u0000\u0537\u0535\u0001\u0000\u0000\u0000\u0537"+
		"\u0538\u0001\u0000\u0000\u0000\u0538\u053a\u0001\u0000\u0000\u0000\u0539"+
		"\u0537\u0001\u0000\u0000\u0000\u053a\u053c\u0003^/\u0000\u053b\u053d\u0003"+
		"\u0120\u0090\u0000\u053c\u053b\u0001\u0000\u0000\u0000\u053c\u053d\u0001"+
		"\u0000\u0000\u0000\u053d\u053f\u0001\u0000\u0000\u0000\u053e\u0540\u0003"+
		"\u00a6S\u0000\u053f\u053e\u0001\u0000\u0000\u0000\u053f\u0540\u0001\u0000"+
		"\u0000\u0000\u0540g\u0001\u0000\u0000\u0000\u0541\u0542\u0005V\u0000\u0000"+
		"\u0542\u0544\u0003^/\u0000\u0543\u0545\u0003\u009cN\u0000\u0544\u0543"+
		"\u0001\u0000\u0000\u0000\u0544\u0545\u0001\u0000\u0000\u0000\u0545\u0548"+
		"\u0001\u0000\u0000\u0000\u0546\u0547\u0005K\u0000\u0000\u0547\u0549\u0003"+
		"\u00a4R\u0000\u0548\u0546\u0001\u0000\u0000\u0000\u0548\u0549\u0001\u0000"+
		"\u0000\u0000\u0549\u054a\u0001\u0000\u0000\u0000\u054a\u054b\u0003\u00a8"+
		"T\u0000\u054bi\u0001\u0000\u0000\u0000\u054c\u054f\u0003\u00c0`\u0000"+
		"\u054d\u054f\u0005j\u0000\u0000\u054e\u054c\u0001\u0000\u0000\u0000\u054e"+
		"\u054d\u0001\u0000\u0000\u0000\u054f\u0550\u0001\u0000\u0000\u0000\u0550"+
		"\u0551\u0003^/\u0000\u0551\u0556\u0003\u00cae\u0000\u0552\u0553\u0005"+
		"w\u0000\u0000\u0553\u0555\u0005x\u0000\u0000\u0554\u0552\u0001\u0000\u0000"+
		"\u0000\u0555\u0558\u0001\u0000\u0000\u0000\u0556\u0554\u0001\u0000\u0000"+
		"\u0000\u0556\u0557\u0001\u0000\u0000\u0000\u0557\u055b\u0001\u0000\u0000"+
		"\u0000\u0558\u0556\u0001\u0000\u0000\u0000\u0559\u055a\u0005g\u0000\u0000"+
		"\u055a\u055c\u0003\u00c8d\u0000\u055b\u0559\u0001\u0000\u0000\u0000\u055b"+
		"\u055c\u0001\u0000\u0000\u0000\u055c\u055f\u0001\u0000\u0000\u0000\u055d"+
		"\u0560\u0003\u00d2i\u0000\u055e\u0560\u0005y\u0000\u0000\u055f\u055d\u0001"+
		"\u0000\u0000\u0000\u055f\u055e\u0001\u0000\u0000\u0000\u0560k\u0001\u0000"+
		"\u0000\u0000\u0561\u0562\u0003^/\u0000\u0562\u0565\u0003\u00cae\u0000"+
		"\u0563\u0564\u0005g\u0000\u0000\u0564\u0566\u0003\u00c8d\u0000\u0565\u0563"+
		"\u0001\u0000\u0000\u0000\u0565\u0566\u0001\u0000\u0000\u0000\u0566\u0567"+
		"\u0001\u0000\u0000\u0000\u0567\u0568\u0003\u00d4j\u0000\u0568m\u0001\u0000"+
		"\u0000\u0000\u0569\u056e\u0003^/\u0000\u056a\u056b\u0005w\u0000\u0000"+
		"\u056b\u056d\u0005x\u0000\u0000\u056c\u056a\u0001\u0000\u0000\u0000\u056d"+
		"\u0570\u0001\u0000\u0000\u0000\u056e\u056c\u0001\u0000\u0000\u0000\u056e"+
		"\u056f\u0001\u0000\u0000\u0000\u056f\u0571\u0001\u0000\u0000\u0000\u0570"+
		"\u056e\u0001\u0000\u0000\u0000\u0571\u0572\u0005|\u0000\u0000\u0572\u0573"+
		"\u0003\u00bc^\u0000\u0573o\u0001\u0000\u0000\u0000\u0574\u0577\u0003\u00c0"+
		"`\u0000\u0575\u0577\u0005j\u0000\u0000\u0576\u0574\u0001\u0000\u0000\u0000"+
		"\u0576\u0575\u0001\u0000\u0000\u0000\u0577\u0578\u0001\u0000\u0000\u0000"+
		"\u0578\u0579\u0003^/\u0000\u0579\u057e\u0003\u00cae\u0000\u057a\u057b"+
		"\u0005w\u0000\u0000\u057b\u057d\u0005x\u0000\u0000\u057c\u057a\u0001\u0000"+
		"\u0000\u0000\u057d\u0580\u0001\u0000\u0000\u0000\u057e\u057c\u0001\u0000"+
		"\u0000\u0000\u057e\u057f\u0001\u0000\u0000\u0000\u057f\u0583\u0001\u0000"+
		"\u0000\u0000\u0580\u057e\u0001\u0000\u0000\u0000\u0581\u0582\u0005g\u0000"+
		"\u0000\u0582\u0584\u0003\u00c8d\u0000\u0583\u0581\u0001\u0000\u0000\u0000"+
		"\u0583\u0584\u0001\u0000\u0000\u0000\u0584\u0585\u0001\u0000\u0000\u0000"+
		"\u0585\u0586\u0005y\u0000\u0000\u0586q\u0001\u0000\u0000\u0000\u0587\u058c"+
		"\u0003^/\u0000\u0588\u0589\u0005w\u0000\u0000\u0589\u058b\u0005x\u0000"+
		"\u0000\u058a\u0588\u0001\u0000\u0000\u0000\u058b\u058e\u0001\u0000\u0000"+
		"\u0000\u058c\u058a\u0001\u0000\u0000\u0000\u058c\u058d\u0001\u0000\u0000"+
		"\u0000\u058ds\u0001\u0000\u0000\u0000\u058e\u058c\u0001\u0000\u0000\u0000"+
		"\u058f\u0590\u0003^/\u0000\u0590u\u0001\u0000\u0000\u0000\u0591\u0593"+
		"\u0003^/\u0000\u0592\u0594\u0003\u00c4b\u0000\u0593\u0592\u0001\u0000"+
		"\u0000\u0000\u0593\u0594\u0001\u0000\u0000\u0000\u0594\u059c\u0001\u0000"+
		"\u0000\u0000\u0595\u0596\u0005{\u0000\u0000\u0596\u0598\u0003^/\u0000"+
		"\u0597\u0599\u0003\u00c4b\u0000\u0598\u0597\u0001\u0000\u0000\u0000\u0598"+
		"\u0599\u0001\u0000\u0000\u0000\u0599\u059b\u0001\u0000\u0000\u0000\u059a"+
		"\u0595\u0001\u0000\u0000\u0000\u059b\u059e\u0001\u0000\u0000\u0000\u059c"+
		"\u059a\u0001\u0000\u0000\u0000\u059c\u059d\u0001\u0000\u0000\u0000\u059d"+
		"w\u0001\u0000\u0000\u0000\u059e\u059c\u0001\u0000\u0000\u0000\u059f\u05a4"+
		"\u0003^/\u0000\u05a0\u05a1\u0005{\u0000\u0000\u05a1\u05a3\u0003^/\u0000"+
		"\u05a2\u05a0\u0001\u0000\u0000\u0000\u05a3\u05a6\u0001\u0000\u0000\u0000"+
		"\u05a4\u05a2\u0001\u0000\u0000\u0000\u05a4\u05a5\u0001\u0000\u0000\u0000"+
		"\u05a5y\u0001\u0000\u0000\u0000\u05a6\u05a4\u0001\u0000\u0000\u0000\u05a7"+
		"\u05a8\u0003^/\u0000\u05a8\u05a9\u0005|\u0000\u0000\u05a9\u05aa\u0003"+
		"\u00dcn\u0000\u05aa{\u0001\u0000\u0000\u0000\u05ab\u05ac\u0005:\u0000"+
		"\u0000\u05ac\u05ad\u0005V\u0000\u0000\u05ad\u05ae\u0003^/\u0000\u05ae"+
		"\u05af\u0003\u00e0p\u0000\u05af}\u0001\u0000\u0000\u0000\u05b0\u05b1\u0003"+
		"^/\u0000\u05b1\u05b2\u0005s\u0000\u0000\u05b2\u05b4\u0005t\u0000\u0000"+
		"\u05b3\u05b5\u0003\u00eau\u0000\u05b4\u05b3\u0001\u0000\u0000\u0000\u05b4"+
		"\u05b5\u0001\u0000\u0000\u0000\u05b5\u007f\u0001\u0000\u0000\u0000\u05b6"+
		"\u0620\u0003\u00ecv\u0000\u05b7\u05b8\u0005<\u0000\u0000\u05b8\u05bb\u0003"+
		"\u0084B\u0000\u05b9\u05ba\u0005\u0082\u0000\u0000\u05ba\u05bc\u0003\u0084"+
		"B\u0000\u05bb\u05b9\u0001\u0000\u0000\u0000\u05bb\u05bc\u0001\u0000\u0000"+
		"\u0000\u05bc\u05bd\u0001\u0000\u0000\u0000\u05bd\u05be\u0005y\u0000\u0000"+
		"\u05be\u0620\u0001\u0000\u0000\u0000\u05bf\u05c0\u0005P\u0000\u0000\u05c0"+
		"\u05c1\u0003\u010a\u0085\u0000\u05c1\u05c4\u0003\u0080@\u0000\u05c2\u05c3"+
		"\u0005I\u0000\u0000\u05c3\u05c5\u0003\u0080@\u0000\u05c4\u05c2\u0001\u0000"+
		"\u0000\u0000\u05c4\u05c5\u0001\u0000\u0000\u0000\u05c5\u0620\u0001\u0000"+
		"\u0000\u0000\u05c6\u05c7\u0005O\u0000\u0000\u05c7\u05c8\u0005s\u0000\u0000"+
		"\u05c8\u05c9\u0003\u0102\u0081\u0000\u05c9\u05ca\u0005t\u0000\u0000\u05ca"+
		"\u05cb\u0003\u0080@\u0000\u05cb\u0620\u0001\u0000\u0000\u0000\u05cc\u05cd"+
		"\u0005l\u0000\u0000\u05cd\u05ce\u0003\u010a\u0085\u0000\u05ce\u05cf\u0003"+
		"\u0080@\u0000\u05cf\u0620\u0001\u0000\u0000\u0000\u05d0\u05d1\u0005G\u0000"+
		"\u0000\u05d1\u05d2\u0003\u0080@\u0000\u05d2\u05d3\u0005l\u0000\u0000\u05d3"+
		"\u05d4\u0003\u010a\u0085\u0000\u05d4\u05d5\u0005y\u0000\u0000\u05d5\u0620"+
		"\u0001\u0000\u0000\u0000\u05d6\u05d7\u0005i\u0000\u0000\u05d7\u05e1\u0003"+
		"\u00ecv\u0000\u05d8\u05da\u0003\u0082A\u0000\u05d9\u05d8\u0001\u0000\u0000"+
		"\u0000\u05da\u05db\u0001\u0000\u0000\u0000\u05db\u05d9\u0001\u0000\u0000"+
		"\u0000\u05db\u05dc\u0001\u0000\u0000\u0000\u05dc\u05de\u0001\u0000\u0000"+
		"\u0000\u05dd\u05df\u0003\u00f6{\u0000\u05de\u05dd\u0001\u0000\u0000\u0000"+
		"\u05de\u05df\u0001\u0000\u0000\u0000\u05df\u05e2\u0001\u0000\u0000\u0000"+
		"\u05e0\u05e2\u0003\u00f6{\u0000\u05e1\u05d9\u0001\u0000\u0000\u0000\u05e1"+
		"\u05e0\u0001\u0000\u0000\u0000\u05e2\u0620\u0001\u0000\u0000\u0000\u05e3"+
		"\u05e4\u0005i\u0000\u0000\u05e4\u05e5\u0003\u00f8|\u0000\u05e5\u05e9\u0003"+
		"\u00ecv\u0000\u05e6\u05e8\u0003\u0082A\u0000\u05e7\u05e6\u0001\u0000\u0000"+
		"\u0000\u05e8\u05eb\u0001\u0000\u0000\u0000\u05e9\u05e7\u0001\u0000\u0000"+
		"\u0000\u05e9\u05ea\u0001\u0000\u0000\u0000\u05ea\u05ed\u0001\u0000\u0000"+
		"\u0000\u05eb\u05e9\u0001\u0000\u0000\u0000\u05ec\u05ee\u0003\u00f6{\u0000"+
		"\u05ed\u05ec\u0001\u0000\u0000\u0000\u05ed\u05ee\u0001\u0000\u0000\u0000"+
		"\u05ee\u0620\u0001\u0000\u0000\u0000\u05ef\u05f0\u0005c\u0000\u0000\u05f0"+
		"\u05f1\u0003\u010a\u0085\u0000\u05f1\u05f5\u0005u\u0000\u0000\u05f2\u05f4"+
		"\u0003\u00fe\u007f\u0000\u05f3\u05f2\u0001\u0000\u0000\u0000\u05f4\u05f7"+
		"\u0001\u0000\u0000\u0000\u05f5\u05f3\u0001\u0000\u0000\u0000\u05f5\u05f6"+
		"\u0001\u0000\u0000\u0000\u05f6\u05fb\u0001\u0000\u0000\u0000\u05f7\u05f5"+
		"\u0001\u0000\u0000\u0000\u05f8\u05fa\u0003\u0100\u0080\u0000\u05f9\u05f8"+
		"\u0001\u0000\u0000\u0000\u05fa\u05fd\u0001\u0000\u0000\u0000\u05fb\u05f9"+
		"\u0001\u0000\u0000\u0000\u05fb\u05fc\u0001\u0000\u0000\u0000\u05fc\u05fe"+
		"\u0001\u0000\u0000\u0000\u05fd\u05fb\u0001\u0000\u0000\u0000\u05fe\u05ff"+
		"\u0005v\u0000\u0000\u05ff\u0620\u0001\u0000\u0000\u0000\u0600\u0601\u0005"+
		"d\u0000\u0000\u0601\u0602\u0003\u010a\u0085\u0000\u0602\u0603\u0003\u00ec"+
		"v\u0000\u0603\u0620\u0001\u0000\u0000\u0000\u0604\u0606\u0005^\u0000\u0000"+
		"\u0605\u0607\u0003\u0084B\u0000\u0606\u0605\u0001\u0000\u0000\u0000\u0606"+
		"\u0607\u0001\u0000\u0000\u0000\u0607\u0608\u0001\u0000\u0000\u0000\u0608"+
		"\u0620\u0005y\u0000\u0000\u0609\u060a\u0005f\u0000\u0000\u060a\u060b\u0003"+
		"\u0084B\u0000\u060b\u060c\u0005y\u0000\u0000\u060c\u0620\u0001\u0000\u0000"+
		"\u0000\u060d\u060f\u0005>\u0000\u0000\u060e\u0610\u0003^/\u0000\u060f"+
		"\u060e\u0001\u0000\u0000\u0000\u060f\u0610\u0001\u0000\u0000\u0000\u0610"+
		"\u0611\u0001\u0000\u0000\u0000\u0611\u0620\u0005y\u0000\u0000\u0612\u0614"+
		"\u0005E\u0000\u0000\u0613\u0615\u0003^/\u0000\u0614\u0613\u0001\u0000"+
		"\u0000\u0000\u0614\u0615\u0001\u0000\u0000\u0000\u0615\u0616\u0001\u0000"+
		"\u0000\u0000\u0616\u0620\u0005y\u0000\u0000\u0617\u0620\u0005y\u0000\u0000"+
		"\u0618\u0619\u0003\u010e\u0087\u0000\u0619\u061a\u0005y\u0000\u0000\u061a"+
		"\u0620\u0001\u0000\u0000\u0000\u061b\u061c\u0003^/\u0000\u061c\u061d\u0005"+
		"\u0082\u0000\u0000\u061d\u061e\u0003\u0080@\u0000\u061e\u0620\u0001\u0000"+
		"\u0000\u0000\u061f\u05b6\u0001\u0000\u0000\u0000\u061f\u05b7\u0001\u0000"+
		"\u0000\u0000\u061f\u05bf\u0001\u0000\u0000\u0000\u061f\u05c6\u0001\u0000"+
		"\u0000\u0000\u061f\u05cc\u0001\u0000\u0000\u0000\u061f\u05d0\u0001\u0000"+
		"\u0000\u0000\u061f\u05d6\u0001\u0000\u0000\u0000\u061f\u05e3\u0001\u0000"+
		"\u0000\u0000\u061f\u05ef\u0001\u0000\u0000\u0000\u061f\u0600\u0001\u0000"+
		"\u0000\u0000\u061f\u0604\u0001\u0000\u0000\u0000\u061f\u0609\u0001\u0000"+
		"\u0000\u0000\u061f\u060d\u0001\u0000\u0000\u0000\u061f\u0612\u0001\u0000"+
		"\u0000\u0000\u061f\u0617\u0001\u0000\u0000\u0000\u061f\u0618\u0001\u0000"+
		"\u0000\u0000\u061f\u061b\u0001\u0000\u0000\u0000\u0620\u0081\u0001\u0000"+
		"\u0000\u0000\u0621\u0622\u0005A\u0000\u0000\u0622\u0626\u0005s\u0000\u0000"+
		"\u0623\u0625\u0003\u009aM\u0000\u0624\u0623\u0001\u0000\u0000\u0000\u0625"+
		"\u0628\u0001\u0000\u0000\u0000\u0626\u0624\u0001\u0000\u0000\u0000\u0626"+
		"\u0627\u0001\u0000\u0000\u0000\u0627\u0629\u0001\u0000\u0000\u0000\u0628"+
		"\u0626\u0001\u0000\u0000\u0000\u0629\u062a\u0003\u00f4z\u0000\u062a\u062b"+
		"\u0003^/\u0000\u062b\u062c\u0005t\u0000\u0000\u062c\u062d\u0003\u00ec"+
		"v\u0000\u062d\u0083\u0001\u0000\u0000\u0000\u062e\u062f\u0006B\uffff\uffff"+
		"\u0000\u062f\u063c\u0003\u0086C\u0000\u0630\u0631\u0005Y\u0000\u0000\u0631"+
		"\u063c\u0003\u0112\u0089\u0000\u0632\u0633\u0005s\u0000\u0000\u0633\u0634"+
		"\u0003\u00c0`\u0000\u0634\u0635\u0005t\u0000\u0000\u0635\u0636\u0003\u0084"+
		"B\u0011\u0636\u063c\u0001\u0000\u0000\u0000\u0637\u0638\u0007\u0006\u0000"+
		"\u0000\u0638\u063c\u0003\u0084B\u000f\u0639\u063a\u0007\u0007\u0000\u0000"+
		"\u063a\u063c\u0003\u0084B\u000e\u063b\u062e\u0001\u0000\u0000\u0000\u063b"+
		"\u0630\u0001\u0000\u0000\u0000\u063b\u0632\u0001\u0000\u0000\u0000\u063b"+
		"\u0637\u0001\u0000\u0000\u0000\u063b\u0639\u0001\u0000\u0000\u0000\u063c"+
		"\u0692\u0001\u0000\u0000\u0000\u063d\u063e\n\r\u0000\u0000\u063e\u063f"+
		"\u0007\b\u0000\u0000\u063f\u0691\u0003\u0084B\u000e\u0640\u0641\n\f\u0000"+
		"\u0000\u0641\u0642\u0007\t\u0000\u0000\u0642\u0691\u0003\u0084B\r\u0643"+
		"\u064b\n\u000b\u0000\u0000\u0644\u0645\u0005~\u0000\u0000\u0645\u064c"+
		"\u0005~\u0000\u0000\u0646\u0647\u0005}\u0000\u0000\u0647\u0648\u0005}"+
		"\u0000\u0000\u0648\u064c\u0005}\u0000\u0000\u0649\u064a\u0005}\u0000\u0000"+
		"\u064a\u064c\u0005}\u0000\u0000\u064b\u0644\u0001\u0000\u0000\u0000\u064b"+
		"\u0646\u0001\u0000\u0000\u0000\u064b\u0649\u0001\u0000\u0000\u0000\u064c"+
		"\u064d\u0001\u0000\u0000\u0000\u064d\u0691\u0003\u0084B\f\u064e\u064f"+
		"\n\n\u0000\u0000\u064f\u0650\u0007\n\u0000\u0000\u0650\u0691\u0003\u0084"+
		"B\u000b\u0651\u0652\n\b\u0000\u0000\u0652\u0653\u0007\u000b\u0000\u0000"+
		"\u0653\u0691\u0003\u0084B\t\u0654\u0655\n\u0007\u0000\u0000\u0655\u0656"+
		"\u0005\u008f\u0000\u0000\u0656\u0691\u0003\u0084B\b\u0657\u0658\n\u0006"+
		"\u0000\u0000\u0658\u0659\u0005\u0091\u0000\u0000\u0659\u0691\u0003\u0084"+
		"B\u0007\u065a\u065b\n\u0005\u0000\u0000\u065b\u065c\u0005\u0090\u0000"+
		"\u0000\u065c\u0691\u0003\u0084B\u0006\u065d\u065e\n\u0004\u0000\u0000"+
		"\u065e\u065f\u0005\u0087\u0000\u0000\u065f\u0691\u0003\u0084B\u0005\u0660"+
		"\u0661\n\u0003\u0000\u0000\u0661\u0662\u0005\u0088\u0000\u0000\u0662\u0691"+
		"\u0003\u0084B\u0004\u0663\u0664\n\u0002\u0000\u0000\u0664\u0665\u0005"+
		"\u0081\u0000\u0000\u0665\u0666\u0003\u0084B\u0000\u0666\u0667\u0005\u0082"+
		"\u0000\u0000\u0667\u0668\u0003\u0084B\u0003\u0668\u0691\u0001\u0000\u0000"+
		"\u0000\u0669\u066a\n\u0001\u0000\u0000\u066a\u066b\u0007\f\u0000\u0000"+
		"\u066b\u0691\u0003\u0084B\u0002\u066c\u066d\n\u0019\u0000\u0000\u066d"+
		"\u066e\u0005{\u0000\u0000\u066e\u0691\u0003^/\u0000\u066f\u0670\n\u0018"+
		"\u0000\u0000\u0670\u0671\u0005{\u0000\u0000\u0671\u0691\u0005e\u0000\u0000"+
		"\u0672\u0673\n\u0017\u0000\u0000\u0673\u0674\u0005{\u0000\u0000\u0674"+
		"\u0676\u0005Y\u0000\u0000\u0675\u0677\u0003\u011a\u008d\u0000\u0676\u0675"+
		"\u0001\u0000\u0000\u0000\u0676\u0677\u0001\u0000\u0000\u0000\u0677\u0678"+
		"\u0001\u0000\u0000\u0000\u0678\u0691\u0003\u008aE\u0000\u0679\u067a\n"+
		"\u0016\u0000\u0000\u067a\u067b\u0005{\u0000\u0000\u067b\u067c\u0005b\u0000"+
		"\u0000\u067c\u0691\u0003\u008cF\u0000\u067d\u067e\n\u0015\u0000\u0000"+
		"\u067e\u067f\u0005{\u0000\u0000\u067f\u0691\u0003\u0118\u008c\u0000\u0680"+
		"\u0681\n\u0014\u0000\u0000\u0681\u0682\u0005w\u0000\u0000\u0682\u0683"+
		"\u0003\u0084B\u0000\u0683\u0684\u0005x\u0000\u0000\u0684\u0691\u0001\u0000"+
		"\u0000\u0000\u0685\u0686\n\u0013\u0000\u0000\u0686\u0688\u0005s\u0000"+
		"\u0000\u0687\u0689\u0003\u010c\u0086\u0000\u0688\u0687\u0001\u0000\u0000"+
		"\u0000\u0688\u0689\u0001\u0000\u0000\u0000\u0689\u068a\u0001\u0000\u0000"+
		"\u0000\u068a\u0691\u0005t\u0000\u0000\u068b\u068c\n\u0010\u0000\u0000"+
		"\u068c\u0691\u0007\r\u0000\u0000\u068d\u068e\n\t\u0000\u0000\u068e\u068f"+
		"\u0005T\u0000\u0000\u068f\u0691\u0003\u00c0`\u0000\u0690\u063d\u0001\u0000"+
		"\u0000\u0000\u0690\u0640\u0001\u0000\u0000\u0000\u0690\u0643\u0001\u0000"+
		"\u0000\u0000\u0690\u064e\u0001\u0000\u0000\u0000\u0690\u0651\u0001\u0000"+
		"\u0000\u0000\u0690\u0654\u0001\u0000\u0000\u0000\u0690\u0657\u0001\u0000"+
		"\u0000\u0000\u0690\u065a\u0001\u0000\u0000\u0000\u0690\u065d\u0001\u0000"+
		"\u0000\u0000\u0690\u0660\u0001\u0000\u0000\u0000\u0690\u0663\u0001\u0000"+
		"\u0000\u0000\u0690\u0669\u0001\u0000\u0000\u0000\u0690\u066c\u0001\u0000"+
		"\u0000\u0000\u0690\u066f\u0001\u0000\u0000\u0000\u0690\u0672\u0001\u0000"+
		"\u0000\u0000\u0690\u0679\u0001\u0000\u0000\u0000\u0690\u067d\u0001\u0000"+
		"\u0000\u0000\u0690\u0680\u0001\u0000\u0000\u0000\u0690\u0685\u0001\u0000"+
		"\u0000\u0000\u0690\u068b\u0001\u0000\u0000\u0000\u0690\u068d\u0001\u0000"+
		"\u0000\u0000\u0691\u0694\u0001\u0000\u0000\u0000\u0692\u0690\u0001\u0000"+
		"\u0000\u0000\u0692\u0693\u0001\u0000\u0000\u0000\u0693\u0085\u0001\u0000"+
		"\u0000\u0000\u0694\u0692\u0001\u0000\u0000\u0000\u0695\u0696\u0005s\u0000"+
		"\u0000\u0696\u0697\u0003\u0084B\u0000\u0697\u0698\u0005t\u0000\u0000\u0698"+
		"\u06ab\u0001\u0000\u0000\u0000\u0699\u06ab\u0005e\u0000\u0000\u069a\u06ab"+
		"\u0005b\u0000\u0000\u069b\u06ab\u0003\u00d6k\u0000\u069c\u06ab\u0003^"+
		"/\u0000\u069d\u069e\u0003\u00c0`\u0000\u069e\u069f\u0005{\u0000\u0000"+
		"\u069f\u06a0\u0005C\u0000\u0000\u06a0\u06ab\u0001\u0000\u0000\u0000\u06a1"+
		"\u06a2\u0005j\u0000\u0000\u06a2\u06a3\u0005{\u0000\u0000\u06a3\u06ab\u0005"+
		"C\u0000\u0000\u06a4\u06a8\u0003\u011a\u008d\u0000\u06a5\u06a9\u0003\u008e"+
		"G\u0000\u06a6\u06a7\u0005e\u0000\u0000\u06a7\u06a9\u0003\u0120\u0090\u0000"+
		"\u06a8\u06a5\u0001\u0000\u0000\u0000\u06a8\u06a6\u0001\u0000\u0000\u0000"+
		"\u06a9\u06ab\u0001\u0000\u0000\u0000\u06aa\u0695\u0001\u0000\u0000\u0000"+
		"\u06aa\u0699\u0001\u0000\u0000\u0000\u06aa\u069a\u0001\u0000\u0000\u0000"+
		"\u06aa\u069b\u0001\u0000\u0000\u0000\u06aa\u069c\u0001\u0000\u0000\u0000"+
		"\u06aa\u069d\u0001\u0000\u0000\u0000\u06aa\u06a1\u0001\u0000\u0000\u0000"+
		"\u06aa\u06a4\u0001\u0000\u0000\u0000\u06ab\u0087\u0001\u0000\u0000\u0000"+
		"\u06ac\u06ae\u0003^/\u0000\u06ad\u06af\u0003\u011c\u008e\u0000\u06ae\u06ad"+
		"\u0001\u0000\u0000\u0000\u06ae\u06af\u0001\u0000\u0000\u0000\u06af\u06b7"+
		"\u0001\u0000\u0000\u0000\u06b0\u06b1\u0005{\u0000\u0000\u06b1\u06b3\u0003"+
		"^/\u0000\u06b2\u06b4\u0003\u011c\u008e\u0000\u06b3\u06b2\u0001\u0000\u0000"+
		"\u0000\u06b3\u06b4\u0001\u0000\u0000\u0000\u06b4\u06b6\u0001\u0000\u0000"+
		"\u0000\u06b5\u06b0\u0001\u0000\u0000\u0000\u06b6\u06b9\u0001\u0000\u0000"+
		"\u0000\u06b7\u06b5\u0001\u0000\u0000\u0000\u06b7\u06b8\u0001\u0000\u0000"+
		"\u0000\u06b8\u06bc\u0001\u0000\u0000\u0000\u06b9\u06b7\u0001\u0000\u0000"+
		"\u0000\u06ba\u06bc\u0003\u00c2a\u0000\u06bb\u06ac\u0001\u0000\u0000\u0000"+
		"\u06bb\u06ba\u0001\u0000\u0000\u0000\u06bc\u0089\u0001\u0000\u0000\u0000"+
		"\u06bd\u06bf\u0003^/\u0000\u06be\u06c0\u0003\u011e\u008f\u0000\u06bf\u06be"+
		"\u0001\u0000\u0000\u0000\u06bf\u06c0\u0001\u0000\u0000\u0000\u06c0\u06c1"+
		"\u0001\u0000\u0000\u0000\u06c1\u06c2\u0003\u0116\u008b\u0000\u06c2\u008b"+
		"\u0001\u0000\u0000\u0000\u06c3\u06ca\u0003\u0120\u0090\u0000\u06c4\u06c5"+
		"\u0005{\u0000\u0000\u06c5\u06c7\u0003^/\u0000\u06c6\u06c8\u0003\u0120"+
		"\u0090\u0000\u06c7\u06c6\u0001\u0000\u0000\u0000\u06c7\u06c8\u0001\u0000"+
		"\u0000\u0000\u06c8\u06ca\u0001\u0000\u0000\u0000\u06c9\u06c3\u0001\u0000"+
		"\u0000\u0000\u06c9\u06c4\u0001\u0000\u0000\u0000\u06ca\u008d\u0001\u0000"+
		"\u0000\u0000\u06cb\u06cc\u0005b\u0000\u0000\u06cc\u06d1\u0003\u008cF\u0000"+
		"\u06cd\u06ce\u0003^/\u0000\u06ce\u06cf\u0003\u0120\u0090\u0000\u06cf\u06d1"+
		"\u0001\u0000\u0000\u0000\u06d0\u06cb\u0001\u0000\u0000\u0000\u06d0\u06cd"+
		"\u0001\u0000\u0000\u0000\u06d1\u008f\u0001\u0000\u0000\u0000\u06d2\u06d4"+
		"\u0003\u0092I\u0000\u06d3\u06d2\u0001\u0000\u0000\u0000\u06d3\u06d4\u0001"+
		"\u0000\u0000\u0000\u06d4\u06d8\u0001\u0000\u0000\u0000\u06d5\u06d7\u0003"+
		"\u0094J\u0000\u06d6\u06d5\u0001\u0000\u0000\u0000\u06d7\u06da\u0001\u0000"+
		"\u0000\u0000\u06d8\u06d6\u0001\u0000\u0000\u0000\u06d8\u06d9\u0001\u0000"+
		"\u0000\u0000\u06d9\u06de\u0001\u0000\u0000\u0000\u06da\u06d8\u0001\u0000"+
		"\u0000\u0000\u06db\u06dd\u0003\u0000\u0000\u0000\u06dc\u06db\u0001\u0000"+
		"\u0000\u0000\u06dd\u06e0\u0001\u0000\u0000\u0000\u06de\u06dc\u0001\u0000"+
		"\u0000\u0000\u06de\u06df\u0001\u0000\u0000\u0000\u06df\u06e1\u0001\u0000"+
		"\u0000\u0000\u06e0\u06de\u0001\u0000\u0000\u0000\u06e1\u06e2\u0005\u0000"+
		"\u0000\u0001\u06e2\u0091\u0001\u0000\u0000\u0000\u06e3\u06e5\u0003\n\u0005"+
		"\u0000\u06e4\u06e3\u0001\u0000\u0000\u0000\u06e5\u06e8\u0001\u0000\u0000"+
		"\u0000\u06e6\u06e4\u0001\u0000\u0000\u0000\u06e6\u06e7\u0001\u0000\u0000"+
		"\u0000\u06e7\u06e9\u0001\u0000\u0000\u0000\u06e8\u06e6\u0001\u0000\u0000"+
		"\u0000\u06e9\u06ea\u0005Z\u0000\u0000\u06ea\u06eb\u0003x<\u0000\u06eb"+
		"\u06ec\u0005y\u0000\u0000\u06ec\u0093\u0001\u0000\u0000\u0000\u06ed\u06ef"+
		"\u0005S\u0000\u0000\u06ee\u06f0\u0005`\u0000\u0000\u06ef\u06ee\u0001\u0000"+
		"\u0000\u0000\u06ef\u06f0\u0001\u0000\u0000\u0000\u06f0\u06f1\u0001\u0000"+
		"\u0000\u0000\u06f1\u06f4\u0003x<\u0000\u06f2\u06f3\u0005{\u0000\u0000"+
		"\u06f3\u06f5\u0005\u008d\u0000\u0000\u06f4\u06f2\u0001\u0000\u0000\u0000"+
		"\u06f4\u06f5\u0001\u0000\u0000\u0000\u06f5\u06f6\u0001\u0000\u0000\u0000"+
		"\u06f6\u06f7\u0005y\u0000\u0000\u06f7\u0095\u0001\u0000\u0000\u0000\u06f8"+
		"\u06fb\u0003\u0098L\u0000\u06f9\u06fb\u0007\u000e\u0000\u0000\u06fa\u06f8"+
		"\u0001\u0000\u0000\u0000\u06fa\u06f9\u0001\u0000\u0000\u0000\u06fb\u0097"+
		"\u0001\u0000\u0000\u0000\u06fc\u06ff\u0003\n\u0005\u0000\u06fd\u06ff\u0007"+
		"\u000f\u0000\u0000\u06fe\u06fc\u0001\u0000\u0000\u0000\u06fe\u06fd\u0001"+
		"\u0000\u0000\u0000\u06ff\u0099\u0001\u0000\u0000\u0000\u0700\u0703\u0005"+
		"L\u0000\u0000\u0701\u0703\u0003\n\u0005\u0000\u0702\u0700\u0001\u0000"+
		"\u0000\u0000\u0702\u0701\u0001\u0000\u0000\u0000\u0703\u009b\u0001\u0000"+
		"\u0000\u0000\u0704\u0705\u0005~\u0000\u0000\u0705\u070a\u0003b1\u0000"+
		"\u0706\u0707\u0005z\u0000\u0000\u0707\u0709\u0003b1\u0000\u0708\u0706"+
		"\u0001\u0000\u0000\u0000\u0709\u070c\u0001\u0000\u0000\u0000\u070a\u0708"+
		"\u0001\u0000\u0000\u0000\u070a\u070b\u0001\u0000\u0000\u0000\u070b\u070d"+
		"\u0001\u0000\u0000\u0000\u070c\u070a\u0001\u0000\u0000\u0000\u070d\u070e"+
		"\u0005}\u0000\u0000\u070e\u009d\u0001\u0000\u0000\u0000\u070f\u0714\u0003"+
		"\u00c0`\u0000\u0710\u0711\u0005\u008f\u0000\u0000\u0711\u0713\u0003\u00c0"+
		"`\u0000\u0712\u0710\u0001\u0000\u0000\u0000\u0713\u0716\u0001\u0000\u0000"+
		"\u0000\u0714\u0712\u0001\u0000\u0000\u0000\u0714\u0715\u0001\u0000\u0000"+
		"\u0000\u0715\u009f\u0001\u0000\u0000\u0000\u0716\u0714\u0001\u0000\u0000"+
		"\u0000\u0717\u071c\u0003f3\u0000\u0718\u0719\u0005z\u0000\u0000\u0719"+
		"\u071b\u0003f3\u0000\u071a\u0718\u0001\u0000\u0000\u0000\u071b\u071e\u0001"+
		"\u0000\u0000\u0000\u071c\u071a\u0001\u0000\u0000\u0000\u071c\u071d\u0001"+
		"\u0000\u0000\u0000\u071d\u00a1\u0001\u0000\u0000\u0000\u071e\u071c\u0001"+
		"\u0000\u0000\u0000\u071f\u0723\u0005y\u0000\u0000\u0720\u0722\u0003\u0004"+
		"\u0002\u0000\u0721\u0720\u0001\u0000\u0000\u0000\u0722\u0725\u0001\u0000"+
		"\u0000\u0000\u0723\u0721\u0001\u0000\u0000\u0000\u0723\u0724\u0001\u0000"+
		"\u0000\u0000\u0724\u00a3\u0001\u0000\u0000\u0000\u0725\u0723\u0001\u0000"+
		"\u0000\u0000\u0726\u072b\u0003\u00c0`\u0000\u0727\u0728\u0005z\u0000\u0000"+
		"\u0728\u072a\u0003\u00c0`\u0000\u0729\u0727\u0001\u0000\u0000\u0000\u072a"+
		"\u072d\u0001\u0000\u0000\u0000\u072b\u0729\u0001\u0000\u0000\u0000\u072b"+
		"\u072c\u0001\u0000\u0000\u0000\u072c\u00a5\u0001\u0000\u0000\u0000\u072d"+
		"\u072b\u0001\u0000\u0000\u0000\u072e\u0732\u0005u\u0000\u0000\u072f\u0731"+
		"\u0003\u0004\u0002\u0000\u0730\u072f\u0001\u0000\u0000\u0000\u0731\u0734"+
		"\u0001\u0000\u0000\u0000\u0732\u0730\u0001\u0000\u0000\u0000\u0732\u0733"+
		"\u0001\u0000\u0000\u0000\u0733\u0735\u0001\u0000\u0000\u0000\u0734\u0732"+
		"\u0001\u0000\u0000\u0000\u0735\u0736\u0005v\u0000\u0000\u0736\u00a7\u0001"+
		"\u0000\u0000\u0000\u0737\u073b\u0005u\u0000\u0000\u0738\u073a\u0003\u00b0"+
		"X\u0000\u0739\u0738\u0001\u0000\u0000\u0000\u073a\u073d\u0001\u0000\u0000"+
		"\u0000\u073b\u0739\u0001\u0000\u0000\u0000\u073b\u073c\u0001\u0000\u0000"+
		"\u0000\u073c\u073e\u0001\u0000\u0000\u0000\u073d\u073b\u0001\u0000\u0000"+
		"\u0000\u073e\u073f\u0005v\u0000\u0000\u073f\u00a9\u0001\u0000\u0000\u0000"+
		"\u0740\u0741\u0003\u009cN\u0000\u0741\u0742\u0003j5\u0000\u0742\u00ab"+
		"\u0001\u0000\u0000\u0000\u0743\u0744\u0003\u009cN\u0000\u0744\u0745\u0003"+
		"l6\u0000\u0745\u00ad\u0001\u0000\u0000\u0000\u0746\u0747\u0003\u00c0`"+
		"\u0000\u0747\u0748\u0003\u00b8\\\u0000\u0748\u0749\u0005y\u0000\u0000"+
		"\u0749\u00af\u0001\u0000\u0000\u0000\u074a\u074c\u0003\u0096K\u0000\u074b"+
		"\u074a\u0001\u0000\u0000\u0000\u074c\u074f\u0001\u0000\u0000\u0000\u074d"+
		"\u074b\u0001\u0000\u0000\u0000\u074d\u074e\u0001\u0000\u0000\u0000\u074e"+
		"\u0750\u0001\u0000\u0000\u0000\u074f\u074d\u0001\u0000\u0000\u0000\u0750"+
		"\u0753\u0003\u00b2Y\u0000\u0751\u0753\u0005y\u0000\u0000\u0752\u074d\u0001"+
		"\u0000\u0000\u0000\u0752\u0751\u0001\u0000\u0000\u0000\u0753\u00b1\u0001"+
		"\u0000\u0000\u0000\u0754\u075c\u0003\u00b4Z\u0000\u0755\u075c\u0003p8"+
		"\u0000\u0756\u075c\u0003\u00b6[\u0000\u0757\u075c\u0003h4\u0000\u0758"+
		"\u075c\u0003|>\u0000\u0759\u075c\u0003`0\u0000\u075a\u075c\u0003d2\u0000"+
		"\u075b\u0754\u0001\u0000\u0000\u0000\u075b\u0755\u0001\u0000\u0000\u0000"+
		"\u075b\u0756\u0001\u0000\u0000\u0000\u075b\u0757\u0001\u0000\u0000\u0000"+
		"\u075b\u0758\u0001\u0000\u0000\u0000\u075b\u0759\u0001\u0000\u0000\u0000"+
		"\u075b\u075a\u0001\u0000\u0000\u0000\u075c\u00b3\u0001\u0000\u0000\u0000"+
		"\u075d\u075e\u0003\u00c0`\u0000\u075e\u0763\u0003n7\u0000\u075f\u0760"+
		"\u0005z\u0000\u0000\u0760\u0762\u0003n7\u0000\u0761\u075f\u0001\u0000"+
		"\u0000\u0000\u0762\u0765\u0001\u0000\u0000\u0000\u0763\u0761\u0001\u0000"+
		"\u0000\u0000\u0763\u0764\u0001\u0000\u0000\u0000\u0764\u0766\u0001\u0000"+
		"\u0000\u0000\u0765\u0763\u0001\u0000\u0000\u0000\u0766\u0767\u0005y\u0000"+
		"\u0000\u0767\u00b5\u0001\u0000\u0000\u0000\u0768\u0769\u0003\u009cN\u0000"+
		"\u0769\u076a\u0003p8\u0000\u076a\u00b7\u0001\u0000\u0000\u0000\u076b\u0770"+
		"\u0003\u00ba]\u0000\u076c\u076d\u0005z\u0000\u0000\u076d\u076f\u0003\u00ba"+
		"]\u0000\u076e\u076c\u0001\u0000\u0000\u0000\u076f\u0772\u0001\u0000\u0000"+
		"\u0000\u0770\u076e\u0001\u0000\u0000\u0000\u0770\u0771\u0001\u0000\u0000"+
		"\u0000\u0771\u00b9\u0001\u0000\u0000\u0000\u0772\u0770\u0001\u0000\u0000"+
		"\u0000\u0773\u0776\u0003r9\u0000\u0774\u0775\u0005|\u0000\u0000\u0775"+
		"\u0777\u0003\u00bc^\u0000\u0776\u0774\u0001\u0000\u0000\u0000\u0776\u0777"+
		"\u0001\u0000\u0000\u0000\u0777\u00bb\u0001\u0000\u0000\u0000\u0778\u077b"+
		"\u0003\u00be_\u0000\u0779\u077b\u0003\u0084B\u0000\u077a\u0778\u0001\u0000"+
		"\u0000\u0000\u077a\u0779\u0001\u0000\u0000\u0000\u077b\u00bd\u0001\u0000"+
		"\u0000\u0000\u077c\u0788\u0005u\u0000\u0000\u077d\u0782\u0003\u00bc^\u0000"+
		"\u077e\u077f\u0005z\u0000\u0000\u077f\u0781\u0003\u00bc^\u0000\u0780\u077e"+
		"\u0001\u0000\u0000\u0000\u0781\u0784\u0001\u0000\u0000\u0000\u0782\u0780"+
		"\u0001\u0000\u0000\u0000\u0782\u0783\u0001\u0000\u0000\u0000\u0783\u0786"+
		"\u0001\u0000\u0000\u0000\u0784\u0782\u0001\u0000\u0000\u0000\u0785\u0787"+
		"\u0005z\u0000\u0000\u0786\u0785\u0001\u0000\u0000\u0000\u0786\u0787\u0001"+
		"\u0000\u0000\u0000\u0787\u0789\u0001\u0000\u0000\u0000\u0788\u077d\u0001"+
		"\u0000\u0000\u0000\u0788\u0789\u0001\u0000\u0000\u0000\u0789\u078a\u0001"+
		"\u0000\u0000\u0000\u078a\u078b\u0005v\u0000\u0000\u078b\u00bf\u0001\u0000"+
		"\u0000\u0000\u078c\u0791\u0003v;\u0000\u078d\u078e\u0005w\u0000\u0000"+
		"\u078e\u0790\u0005x\u0000\u0000\u078f\u078d\u0001\u0000\u0000\u0000\u0790"+
		"\u0793\u0001\u0000\u0000\u0000\u0791\u078f\u0001\u0000\u0000\u0000\u0791"+
		"\u0792\u0001\u0000\u0000\u0000\u0792\u079d\u0001\u0000\u0000\u0000\u0793"+
		"\u0791\u0001\u0000\u0000\u0000\u0794\u0799\u0003\u00c2a\u0000\u0795\u0796"+
		"\u0005w\u0000\u0000\u0796\u0798\u0005x\u0000\u0000\u0797\u0795\u0001\u0000"+
		"\u0000\u0000\u0798\u079b\u0001\u0000\u0000\u0000\u0799\u0797\u0001\u0000"+
		"\u0000\u0000\u0799\u079a\u0001\u0000\u0000\u0000\u079a\u079d\u0001\u0000"+
		"\u0000\u0000\u079b\u0799\u0001\u0000\u0000\u0000\u079c\u078c\u0001\u0000"+
		"\u0000\u0000\u079c\u0794\u0001\u0000\u0000\u0000\u079d\u00c1\u0001\u0000"+
		"\u0000\u0000\u079e\u079f\u0007\u0010\u0000\u0000\u079f\u00c3\u0001\u0000"+
		"\u0000\u0000\u07a0\u07a1\u0005~\u0000\u0000\u07a1\u07a6\u0003\u00c6c\u0000"+
		"\u07a2\u07a3\u0005z\u0000\u0000\u07a3\u07a5\u0003\u00c6c\u0000\u07a4\u07a2"+
		"\u0001\u0000\u0000\u0000\u07a5\u07a8\u0001\u0000\u0000\u0000\u07a6\u07a4"+
		"\u0001\u0000\u0000\u0000\u07a6\u07a7\u0001\u0000\u0000\u0000\u07a7\u07a9"+
		"\u0001\u0000\u0000\u0000\u07a8\u07a6\u0001\u0000\u0000\u0000\u07a9\u07aa"+
		"\u0005}\u0000\u0000\u07aa\u00c5\u0001\u0000\u0000\u0000\u07ab\u07b2\u0003"+
		"\u00c0`\u0000\u07ac\u07af\u0005\u0081\u0000\u0000\u07ad\u07ae\u0007\u0011"+
		"\u0000\u0000\u07ae\u07b0\u0003\u00c0`\u0000\u07af\u07ad\u0001\u0000\u0000"+
		"\u0000\u07af\u07b0\u0001\u0000\u0000\u0000\u07b0\u07b2\u0001\u0000\u0000"+
		"\u0000\u07b1\u07ab\u0001\u0000\u0000\u0000\u07b1\u07ac\u0001\u0000\u0000"+
		"\u0000\u07b2\u00c7\u0001\u0000\u0000\u0000\u07b3\u07b8\u0003x<\u0000\u07b4"+
		"\u07b5\u0005z\u0000\u0000\u07b5\u07b7\u0003x<\u0000\u07b6\u07b4\u0001"+
		"\u0000\u0000\u0000\u07b7\u07ba\u0001\u0000\u0000\u0000\u07b8\u07b6\u0001"+
		"\u0000\u0000\u0000\u07b8\u07b9\u0001\u0000\u0000\u0000\u07b9\u00c9\u0001"+
		"\u0000\u0000\u0000\u07ba\u07b8\u0001\u0000\u0000\u0000\u07bb\u07bd\u0005"+
		"s\u0000\u0000\u07bc\u07be\u0003\u00ccf\u0000\u07bd\u07bc\u0001\u0000\u0000"+
		"\u0000\u07bd\u07be\u0001\u0000\u0000\u0000\u07be\u07bf\u0001\u0000\u0000"+
		"\u0000\u07bf\u07c0\u0005t\u0000\u0000\u07c0\u00cb\u0001\u0000\u0000\u0000"+
		"\u07c1\u07c6\u0003\u00ceg\u0000\u07c2\u07c3\u0005z\u0000\u0000\u07c3\u07c5"+
		"\u0003\u00ceg\u0000\u07c4\u07c2\u0001\u0000\u0000\u0000\u07c5\u07c8\u0001"+
		"\u0000\u0000\u0000\u07c6\u07c4\u0001\u0000\u0000\u0000\u07c6\u07c7\u0001"+
		"\u0000\u0000\u0000\u07c7\u07cb\u0001\u0000\u0000\u0000\u07c8\u07c6\u0001"+
		"\u0000\u0000\u0000\u07c9\u07ca\u0005z\u0000\u0000\u07ca\u07cc\u0003\u00d0"+
		"h\u0000\u07cb\u07c9\u0001\u0000\u0000\u0000\u07cb\u07cc\u0001\u0000\u0000"+
		"\u0000\u07cc\u07cf\u0001\u0000\u0000\u0000\u07cd\u07cf\u0003\u00d0h\u0000"+
		"\u07ce\u07c1\u0001\u0000\u0000\u0000\u07ce\u07cd\u0001\u0000\u0000\u0000"+
		"\u07cf\u00cd\u0001\u0000\u0000\u0000\u07d0\u07d2\u0003\u009aM\u0000\u07d1"+
		"\u07d0\u0001\u0000\u0000\u0000\u07d2\u07d5\u0001\u0000\u0000\u0000\u07d3"+
		"\u07d1\u0001\u0000\u0000\u0000\u07d3\u07d4\u0001\u0000\u0000\u0000\u07d4"+
		"\u07d6\u0001\u0000\u0000\u0000\u07d5\u07d3\u0001\u0000\u0000\u0000\u07d6"+
		"\u07d7\u0003\u00c0`\u0000\u07d7\u07d8\u0003r9\u0000\u07d8\u00cf\u0001"+
		"\u0000\u0000\u0000\u07d9\u07db\u0003\u009aM\u0000\u07da\u07d9\u0001\u0000"+
		"\u0000\u0000\u07db\u07de\u0001\u0000\u0000\u0000\u07dc\u07da\u0001\u0000"+
		"\u0000\u0000\u07dc\u07dd\u0001\u0000\u0000\u0000\u07dd\u07df\u0001\u0000"+
		"\u0000\u0000\u07de\u07dc\u0001\u0000\u0000\u0000\u07df\u07e0\u0003\u00c0"+
		"`\u0000\u07e0\u07e1\u0005\u009f\u0000\u0000\u07e1\u07e2\u0003r9\u0000"+
		"\u07e2\u00d1\u0001\u0000\u0000\u0000\u07e3\u07e4\u0003\u00ecv\u0000\u07e4"+
		"\u00d3\u0001\u0000\u0000\u0000\u07e5\u07e6\u0003\u00ecv\u0000\u07e6\u00d5"+
		"\u0001\u0000\u0000\u0000\u07e7\u07e8\u0007\u0012\u0000\u0000\u07e8\u00d7"+
		"\u0001\u0000\u0000\u0000\u07e9\u07ea\u0003x<\u0000\u07ea\u00d9\u0001\u0000"+
		"\u0000\u0000\u07eb\u07f0\u0003z=\u0000\u07ec\u07ed\u0005z\u0000\u0000"+
		"\u07ed\u07ef\u0003z=\u0000\u07ee\u07ec\u0001\u0000\u0000\u0000\u07ef\u07f2"+
		"\u0001\u0000\u0000\u0000\u07f0\u07ee\u0001\u0000\u0000\u0000\u07f0\u07f1"+
		"\u0001\u0000\u0000\u0000\u07f1\u00db\u0001\u0000\u0000\u0000\u07f2\u07f0"+
		"\u0001\u0000\u0000\u0000\u07f3\u07f7\u0003\u0084B\u0000\u07f4\u07f7\u0003"+
		"\n\u0005\u0000\u07f5\u07f7\u0003\u00deo\u0000\u07f6\u07f3\u0001\u0000"+
		"\u0000\u0000\u07f6\u07f4\u0001\u0000\u0000\u0000\u07f6\u07f5\u0001\u0000"+
		"\u0000\u0000\u07f7\u00dd\u0001\u0000\u0000\u0000\u07f8\u0801\u0005u\u0000"+
		"\u0000\u07f9\u07fe\u0003\u00dcn\u0000\u07fa\u07fb\u0005z\u0000\u0000\u07fb"+
		"\u07fd\u0003\u00dcn\u0000\u07fc\u07fa\u0001\u0000\u0000\u0000\u07fd\u0800"+
		"\u0001\u0000\u0000\u0000\u07fe\u07fc\u0001\u0000\u0000\u0000\u07fe\u07ff"+
		"\u0001\u0000\u0000\u0000\u07ff\u0802\u0001\u0000\u0000\u0000\u0800\u07fe"+
		"\u0001\u0000\u0000\u0000\u0801\u07f9\u0001\u0000\u0000\u0000\u0801\u0802"+
		"\u0001\u0000\u0000\u0000\u0802\u0804\u0001\u0000\u0000\u0000\u0803\u0805"+
		"\u0005z\u0000\u0000\u0804\u0803\u0001\u0000\u0000\u0000\u0804\u0805\u0001"+
		"\u0000\u0000\u0000\u0805\u0806\u0001\u0000\u0000\u0000\u0806\u0807\u0005"+
		"v\u0000\u0000\u0807\u00df\u0001\u0000\u0000\u0000\u0808\u080c\u0005u\u0000"+
		"\u0000\u0809\u080b\u0003\u00e2q\u0000\u080a\u0809\u0001\u0000\u0000\u0000"+
		"\u080b\u080e\u0001\u0000\u0000\u0000\u080c\u080a\u0001\u0000\u0000\u0000"+
		"\u080c\u080d\u0001\u0000\u0000\u0000\u080d\u080f\u0001\u0000\u0000\u0000"+
		"\u080e\u080c\u0001\u0000\u0000\u0000\u080f\u0810\u0005v\u0000\u0000\u0810"+
		"\u00e1\u0001\u0000\u0000\u0000\u0811\u0813\u0003\u0096K\u0000\u0812\u0811"+
		"\u0001\u0000\u0000\u0000\u0813\u0816\u0001\u0000\u0000\u0000\u0814\u0812"+
		"\u0001\u0000\u0000\u0000\u0814\u0815\u0001\u0000\u0000\u0000\u0815\u0817"+
		"\u0001\u0000\u0000\u0000\u0816\u0814\u0001\u0000\u0000\u0000\u0817\u081a"+
		"\u0003\u00e4r\u0000\u0818\u081a\u0005y\u0000\u0000\u0819\u0814\u0001\u0000"+
		"\u0000\u0000\u0819\u0818\u0001\u0000\u0000\u0000\u081a\u00e3\u0001\u0000"+
		"\u0000\u0000\u081b\u081c\u0003\u00c0`\u0000\u081c\u081d\u0003\u00e6s\u0000"+
		"\u081d\u081e\u0005y\u0000\u0000\u081e\u0830\u0001\u0000\u0000\u0000\u081f"+
		"\u0821\u0003`0\u0000\u0820\u0822\u0005y\u0000\u0000\u0821\u0820\u0001"+
		"\u0000\u0000\u0000\u0821\u0822\u0001\u0000\u0000\u0000\u0822\u0830\u0001"+
		"\u0000\u0000\u0000\u0823\u0825\u0003h4\u0000\u0824\u0826\u0005y\u0000"+
		"\u0000\u0825\u0824\u0001\u0000\u0000\u0000\u0825\u0826\u0001\u0000\u0000"+
		"\u0000\u0826\u0830\u0001\u0000\u0000\u0000\u0827\u0829\u0003d2\u0000\u0828"+
		"\u082a\u0005y\u0000\u0000\u0829\u0828\u0001\u0000\u0000\u0000\u0829\u082a"+
		"\u0001\u0000\u0000\u0000\u082a\u0830\u0001\u0000\u0000\u0000\u082b\u082d"+
		"\u0003|>\u0000\u082c\u082e\u0005y\u0000\u0000\u082d\u082c\u0001\u0000"+
		"\u0000\u0000\u082d\u082e\u0001\u0000\u0000\u0000\u082e\u0830\u0001\u0000"+
		"\u0000\u0000\u082f\u081b\u0001\u0000\u0000\u0000\u082f\u081f\u0001\u0000"+
		"\u0000\u0000\u082f\u0823\u0001\u0000\u0000\u0000\u082f\u0827\u0001\u0000"+
		"\u0000\u0000\u082f\u082b\u0001\u0000\u0000\u0000\u0830\u00e5\u0001\u0000"+
		"\u0000\u0000\u0831\u0834\u0003~?\u0000\u0832\u0834\u0003\u00e8t\u0000"+
		"\u0833\u0831\u0001\u0000\u0000\u0000\u0833\u0832\u0001\u0000\u0000\u0000"+
		"\u0834\u00e7\u0001\u0000\u0000\u0000\u0835\u0836\u0003\u00b8\\\u0000\u0836"+
		"\u00e9\u0001\u0000\u0000\u0000\u0837\u0838\u0005F\u0000\u0000\u0838\u0839"+
		"\u0003\u00dcn\u0000\u0839\u00eb\u0001\u0000\u0000\u0000\u083a\u083e\u0005"+
		"u\u0000\u0000\u083b\u083d\u0003\u00eew\u0000\u083c\u083b\u0001\u0000\u0000"+
		"\u0000\u083d\u0840\u0001\u0000\u0000\u0000\u083e\u083c\u0001\u0000\u0000"+
		"\u0000\u083e\u083f\u0001\u0000\u0000\u0000\u083f\u0841\u0001\u0000\u0000"+
		"\u0000\u0840\u083e\u0001\u0000\u0000\u0000\u0841\u0842\u0005v\u0000\u0000"+
		"\u0842\u00ed\u0001\u0000\u0000\u0000\u0843\u0847\u0003\u00f0x\u0000\u0844"+
		"\u0847\u0003\u0080@\u0000\u0845\u0847\u0003\u0000\u0000\u0000\u0846\u0843"+
		"\u0001\u0000\u0000\u0000\u0846\u0844\u0001\u0000\u0000\u0000\u0846\u0845"+
		"\u0001\u0000\u0000\u0000\u0847\u00ef\u0001\u0000\u0000\u0000\u0848\u0849"+
		"\u0003\u00f2y\u0000\u0849\u084a\u0005y\u0000\u0000\u084a\u00f1\u0001\u0000"+
		"\u0000\u0000\u084b\u084d\u0003\u009aM\u0000\u084c\u084b\u0001\u0000\u0000"+
		"\u0000\u084d\u0850\u0001\u0000\u0000\u0000\u084e\u084c\u0001\u0000\u0000"+
		"\u0000\u084e\u084f\u0001\u0000\u0000\u0000\u084f\u0851\u0001\u0000\u0000"+
		"\u0000\u0850\u084e\u0001\u0000\u0000\u0000\u0851\u0852\u0003\u00c0`\u0000"+
		"\u0852\u0853\u0003\u00b8\\\u0000\u0853\u00f3\u0001\u0000\u0000\u0000\u0854"+
		"\u0859\u0003x<\u0000\u0855\u0856\u0005\u0090\u0000\u0000\u0856\u0858\u0003"+
		"x<\u0000\u0857\u0855\u0001\u0000\u0000\u0000\u0858\u085b\u0001\u0000\u0000"+
		"\u0000\u0859\u0857\u0001\u0000\u0000\u0000\u0859\u085a\u0001\u0000\u0000"+
		"\u0000\u085a\u00f5\u0001\u0000\u0000\u0000\u085b\u0859\u0001\u0000\u0000"+
		"\u0000\u085c\u085d\u0005M\u0000\u0000\u085d\u085e\u0003\u00ecv\u0000\u085e"+
		"\u00f7\u0001\u0000\u0000\u0000\u085f\u0860\u0005s\u0000\u0000\u0860\u0862"+
		"\u0003\u00fa}\u0000\u0861\u0863\u0005y\u0000\u0000\u0862\u0861\u0001\u0000"+
		"\u0000\u0000\u0862\u0863\u0001\u0000\u0000\u0000\u0863\u0864\u0001\u0000"+
		"\u0000\u0000\u0864\u0865\u0005t\u0000\u0000\u0865\u00f9\u0001\u0000\u0000"+
		"\u0000\u0866\u086b\u0003\u00fc~\u0000\u0867\u0868\u0005y\u0000\u0000\u0868"+
		"\u086a\u0003\u00fc~\u0000\u0869\u0867\u0001\u0000\u0000\u0000\u086a\u086d"+
		"\u0001\u0000\u0000\u0000\u086b\u0869\u0001\u0000\u0000\u0000\u086b\u086c"+
		"\u0001\u0000\u0000\u0000\u086c\u00fb\u0001\u0000\u0000\u0000\u086d\u086b"+
		"\u0001\u0000\u0000\u0000\u086e\u0870\u0003\u009aM\u0000\u086f\u086e\u0001"+
		"\u0000\u0000\u0000\u0870\u0873\u0001\u0000\u0000\u0000\u0871\u086f\u0001"+
		"\u0000\u0000\u0000\u0871\u0872\u0001\u0000\u0000\u0000\u0872\u0874\u0001"+
		"\u0000\u0000\u0000\u0873\u0871\u0001\u0000\u0000\u0000\u0874\u0875\u0003"+
		"v;\u0000\u0875\u0876\u0003r9\u0000\u0876\u0877\u0005|\u0000\u0000\u0877"+
		"\u0878\u0003\u0084B\u0000\u0878\u00fd\u0001\u0000\u0000\u0000\u0879\u087b"+
		"\u0003\u0100\u0080\u0000\u087a\u0879\u0001\u0000\u0000\u0000\u087b\u087c"+
		"\u0001\u0000\u0000\u0000\u087c\u087a\u0001\u0000\u0000\u0000\u087c\u087d"+
		"\u0001\u0000\u0000\u0000\u087d\u087f\u0001\u0000\u0000\u0000\u087e\u0880"+
		"\u0003\u00eew\u0000\u087f\u087e\u0001\u0000\u0000\u0000\u0880\u0881\u0001"+
		"\u0000\u0000\u0000\u0881\u087f\u0001\u0000\u0000\u0000\u0881\u0882\u0001"+
		"\u0000\u0000\u0000\u0882\u00ff\u0001\u0000\u0000\u0000\u0883\u0884\u0005"+
		"@\u0000\u0000\u0884\u0885\u0003\u0110\u0088\u0000\u0885\u0886\u0005\u0082"+
		"\u0000\u0000\u0886\u088e\u0001\u0000\u0000\u0000\u0887\u0888\u0005@\u0000"+
		"\u0000\u0888\u0889\u0003t:\u0000\u0889\u088a\u0005\u0082\u0000\u0000\u088a"+
		"\u088e\u0001\u0000\u0000\u0000\u088b\u088c\u0005F\u0000\u0000\u088c\u088e"+
		"\u0005\u0082\u0000\u0000\u088d\u0883\u0001\u0000\u0000\u0000\u088d\u0887"+
		"\u0001\u0000\u0000\u0000\u088d\u088b\u0001\u0000\u0000\u0000\u088e\u0101"+
		"\u0001\u0000\u0000\u0000\u088f\u089c\u0003\u0106\u0083\u0000\u0890\u0892"+
		"\u0003\u0104\u0082\u0000\u0891\u0890\u0001\u0000\u0000\u0000\u0891\u0892"+
		"\u0001\u0000\u0000\u0000\u0892\u0893\u0001\u0000\u0000\u0000\u0893\u0895"+
		"\u0005y\u0000\u0000\u0894\u0896\u0003\u0084B\u0000\u0895\u0894\u0001\u0000"+
		"\u0000\u0000\u0895\u0896\u0001\u0000\u0000\u0000\u0896\u0897\u0001\u0000"+
		"\u0000\u0000\u0897\u0899\u0005y\u0000\u0000\u0898\u089a\u0003\u0108\u0084"+
		"\u0000\u0899\u0898\u0001\u0000\u0000\u0000\u0899\u089a\u0001\u0000\u0000"+
		"\u0000\u089a\u089c\u0001\u0000\u0000\u0000\u089b\u088f\u0001\u0000\u0000"+
		"\u0000\u089b\u0891\u0001\u0000\u0000\u0000\u089c\u0103\u0001\u0000\u0000"+
		"\u0000\u089d\u08a0\u0003\u00f2y\u0000\u089e\u08a0\u0003\u010c\u0086\u0000"+
		"\u089f\u089d\u0001\u0000\u0000\u0000\u089f\u089e\u0001\u0000\u0000\u0000"+
		"\u08a0\u0105\u0001\u0000\u0000\u0000\u08a1\u08a3\u0003\u009aM\u0000\u08a2"+
		"\u08a1\u0001\u0000\u0000\u0000\u08a3\u08a6\u0001\u0000\u0000\u0000\u08a4"+
		"\u08a2\u0001\u0000\u0000\u0000\u08a4\u08a5\u0001\u0000\u0000\u0000\u08a5"+
		"\u08a7\u0001\u0000\u0000\u0000\u08a6\u08a4\u0001\u0000\u0000\u0000\u08a7"+
		"\u08a8\u0003\u00c0`\u0000\u08a8\u08a9\u0003r9\u0000\u08a9\u08aa\u0005"+
		"\u0082\u0000\u0000\u08aa\u08ab\u0003\u0084B\u0000\u08ab\u0107\u0001\u0000"+
		"\u0000\u0000\u08ac\u08ad\u0003\u010c\u0086\u0000\u08ad\u0109\u0001\u0000"+
		"\u0000\u0000\u08ae\u08af\u0005s\u0000\u0000\u08af\u08b0\u0003\u0084B\u0000"+
		"\u08b0\u08b1\u0005t\u0000\u0000\u08b1\u010b\u0001\u0000\u0000\u0000\u08b2"+
		"\u08b7\u0003\u0084B\u0000\u08b3\u08b4\u0005z\u0000\u0000\u08b4\u08b6\u0003"+
		"\u0084B\u0000\u08b5\u08b3\u0001\u0000\u0000\u0000\u08b6\u08b9\u0001\u0000"+
		"\u0000\u0000\u08b7\u08b5\u0001\u0000\u0000\u0000\u08b7\u08b8\u0001\u0000"+
		"\u0000\u0000\u08b8\u010d\u0001\u0000\u0000\u0000\u08b9\u08b7\u0001\u0000"+
		"\u0000\u0000\u08ba\u08bb\u0003\u0084B\u0000\u08bb\u010f\u0001\u0000\u0000"+
		"\u0000\u08bc\u08bd\u0003\u0084B\u0000\u08bd\u0111\u0001\u0000\u0000\u0000"+
		"\u08be\u08bf\u0003\u011a\u008d\u0000\u08bf\u08c0\u0003\u0088D\u0000\u08c0"+
		"\u08c1\u0003\u0116\u008b\u0000\u08c1\u08c8\u0001\u0000\u0000\u0000\u08c2"+
		"\u08c5\u0003\u0088D\u0000\u08c3\u08c6\u0003\u0114\u008a\u0000\u08c4\u08c6"+
		"\u0003\u0116\u008b\u0000\u08c5\u08c3\u0001\u0000\u0000\u0000\u08c5\u08c4"+
		"\u0001\u0000\u0000\u0000\u08c6\u08c8\u0001\u0000\u0000\u0000\u08c7\u08be"+
		"\u0001\u0000\u0000\u0000\u08c7\u08c2\u0001\u0000\u0000\u0000\u08c8\u0113"+
		"\u0001\u0000\u0000\u0000\u08c9\u08e5\u0005w\u0000\u0000\u08ca\u08cf\u0005"+
		"x\u0000\u0000\u08cb\u08cc\u0005w\u0000\u0000\u08cc\u08ce\u0005x\u0000"+
		"\u0000\u08cd\u08cb\u0001\u0000\u0000\u0000\u08ce\u08d1\u0001\u0000\u0000"+
		"\u0000\u08cf\u08cd\u0001\u0000\u0000\u0000\u08cf\u08d0\u0001\u0000\u0000"+
		"\u0000\u08d0\u08d2\u0001\u0000\u0000\u0000\u08d1\u08cf\u0001\u0000\u0000"+
		"\u0000\u08d2\u08e6\u0003\u00be_\u0000\u08d3\u08d4\u0003\u0084B\u0000\u08d4"+
		"\u08db\u0005x\u0000\u0000\u08d5\u08d6\u0005w\u0000\u0000\u08d6\u08d7\u0003"+
		"\u0084B\u0000\u08d7\u08d8\u0005x\u0000\u0000\u08d8\u08da\u0001\u0000\u0000"+
		"\u0000\u08d9\u08d5\u0001\u0000\u0000\u0000\u08da\u08dd\u0001\u0000\u0000"+
		"\u0000\u08db\u08d9\u0001\u0000\u0000\u0000\u08db\u08dc\u0001\u0000\u0000"+
		"\u0000\u08dc\u08e2\u0001\u0000\u0000\u0000\u08dd\u08db\u0001\u0000\u0000"+
		"\u0000\u08de\u08df\u0005w\u0000\u0000\u08df\u08e1\u0005x\u0000\u0000\u08e0"+
		"\u08de\u0001\u0000\u0000\u0000\u08e1\u08e4\u0001\u0000\u0000\u0000\u08e2"+
		"\u08e0\u0001\u0000\u0000\u0000\u08e2\u08e3\u0001\u0000\u0000\u0000\u08e3"+
		"\u08e6\u0001\u0000\u0000\u0000\u08e4\u08e2\u0001\u0000\u0000\u0000\u08e5"+
		"\u08ca\u0001\u0000\u0000\u0000\u08e5\u08d3\u0001\u0000\u0000\u0000\u08e6"+
		"\u0115\u0001\u0000\u0000\u0000\u08e7\u08e9\u0003\u0120\u0090\u0000\u08e8"+
		"\u08ea\u0003\u00a6S\u0000\u08e9\u08e8\u0001\u0000\u0000\u0000\u08e9\u08ea"+
		"\u0001\u0000\u0000\u0000\u08ea\u0117\u0001\u0000\u0000\u0000\u08eb\u08ec"+
		"\u0003\u011a\u008d\u0000\u08ec\u08ed\u0003\u008eG\u0000\u08ed\u0119\u0001"+
		"\u0000\u0000\u0000\u08ee\u08ef\u0005~\u0000\u0000\u08ef\u08f0\u0003\u00a4"+
		"R\u0000\u08f0\u08f1\u0005}\u0000\u0000\u08f1\u011b\u0001\u0000\u0000\u0000"+
		"\u08f2\u08f3\u0005~\u0000\u0000\u08f3\u08f6\u0005}\u0000\u0000\u08f4\u08f6"+
		"\u0003\u00c4b\u0000\u08f5\u08f2\u0001\u0000\u0000\u0000\u08f5\u08f4\u0001"+
		"\u0000\u0000\u0000\u08f6\u011d\u0001\u0000\u0000\u0000\u08f7\u08f8\u0005"+
		"~\u0000\u0000\u08f8\u08fb\u0005}\u0000\u0000\u08f9\u08fb\u0003\u011a\u008d"+
		"\u0000\u08fa\u08f7\u0001\u0000\u0000\u0000\u08fa\u08f9\u0001\u0000\u0000"+
		"\u0000\u08fb\u011f\u0001\u0000\u0000\u0000\u08fc\u08fe\u0005s\u0000\u0000"+
		"\u08fd\u08ff\u0003\u010c\u0086\u0000\u08fe\u08fd\u0001\u0000\u0000\u0000"+
		"\u08fe\u08ff\u0001\u0000\u0000\u0000\u08ff\u0900\u0001\u0000\u0000\u0000"+
		"\u0900\u0901\u0005t\u0000\u0000\u0901\u0121\u0001\u0000\u0000\u0000\u00fc"+
		"\u0125\u012c\u0133\u013a\u0141\u0146\u014c\u0153\u0159\u015f\u0165\u0171"+
		"\u0178\u017b\u01b5\u0202\u0206\u020d\u0218\u021c\u0221\u0228\u022c\u022f"+
		"\u0234\u0239\u0248\u024b\u0252\u0255\u0259\u025d\u027b\u0281\u028c\u0296"+
		"\u029b\u02a3\u02ab\u02ad\u02f2\u0328\u032d\u0335\u033a\u0342\u0349\u0350"+
		"\u0355\u035d\u0364\u036d\u0374\u0379\u0380\u0384\u03d8\u03e0\u03e5\u03ed"+
		"\u03ef\u03f4\u03fa\u0402\u0404\u0407\u040b\u0411\u0414\u0417\u041a\u0420"+
		"\u0425\u042b\u0437\u043b\u0443\u0447\u0449\u044d\u0450\u0453\u0459\u045e"+
		"\u0461\u0467\u0471\u0479\u047f\u0486\u048c\u0496\u049a\u049d\u04a2\u04a7"+
		"\u04aa\u04b0\u04b6\u04bd\u04c5\u04c9\u04cf\u04d3\u04d8\u04df\u04e7\u04ea"+
		"\u04f1\u04f9\u04fc\u0500\u0507\u050c\u0511\u0515\u0519\u0520\u0526\u052a"+
		"\u052d\u0530\u0537\u053c\u053f\u0544\u0548\u054e\u0556\u055b\u055f\u0565"+
		"\u056e\u0576\u057e\u0583\u058c\u0593\u0598\u059c\u05a4\u05b4\u05bb\u05c4"+
		"\u05db\u05de\u05e1\u05e9\u05ed\u05f5\u05fb\u0606\u060f\u0614\u061f\u0626"+
		"\u063b\u064b\u0676\u0688\u0690\u0692\u06a8\u06aa\u06ae\u06b3\u06b7\u06bb"+
		"\u06bf\u06c7\u06c9\u06d0\u06d3\u06d8\u06de\u06e6\u06ef\u06f4\u06fa\u06fe"+
		"\u0702\u070a\u0714\u071c\u0723\u072b\u0732\u073b\u074d\u0752\u075b\u0763"+
		"\u0770\u0776\u077a\u0782\u0786\u0788\u0791\u0799\u079c\u07a6\u07af\u07b1"+
		"\u07b8\u07bd\u07c6\u07cb\u07ce\u07d3\u07dc\u07f0\u07f6\u07fe\u0801\u0804"+
		"\u080c\u0814\u0819\u0821\u0825\u0829\u082d\u082f\u0833\u083e\u0846\u084e"+
		"\u0859\u0862\u086b\u0871\u087c\u0881\u088d\u0891\u0895\u0899\u089b\u089f"+
		"\u08a4\u08b7\u08c5\u08c7\u08cf\u08db\u08e2\u08e5\u08e9\u08f5\u08fa\u08fe";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}