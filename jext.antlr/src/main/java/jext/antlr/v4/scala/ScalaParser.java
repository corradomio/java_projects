// Generated from Scala.g4 by ANTLR 4.10.1
package jext.antlr.v4.scala;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ScalaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, Id=62, BooleanLiteral=63, CharacterLiteral=64, SymbolLiteral=65, 
		IntegerLiteral=66, StringLiteral=67, FloatingPointLiteral=68, Varid=69, 
		BoundVarid=70, Paren=71, Delim=72, Semi=73, NL=74, NEWLINE=75, WS=76, 
		COMMENT=77, LINE_COMMENT=78;
	public static final int
		RULE_literal = 0, RULE_qualId = 1, RULE_ids = 2, RULE_stableId = 3, RULE_classQualifier = 4, 
		RULE_type_ = 5, RULE_functionArgTypes = 6, RULE_existentialClause = 7, 
		RULE_existentialDcl = 8, RULE_infixType = 9, RULE_compoundType = 10, RULE_annotType = 11, 
		RULE_simpleType = 12, RULE_typeArgs = 13, RULE_types = 14, RULE_refinement = 15, 
		RULE_refineStat = 16, RULE_typePat = 17, RULE_ascription = 18, RULE_expr = 19, 
		RULE_expr1 = 20, RULE_prefixDef = 21, RULE_postfixExpr = 22, RULE_infixExpr = 23, 
		RULE_prefixExpr = 24, RULE_simpleExpr = 25, RULE_simpleExpr1 = 26, RULE_exprs = 27, 
		RULE_argumentExprs = 28, RULE_args = 29, RULE_blockExpr = 30, RULE_block = 31, 
		RULE_blockStat = 32, RULE_resultExpr = 33, RULE_enumerators = 34, RULE_generator = 35, 
		RULE_caseClauses = 36, RULE_caseClause = 37, RULE_guard_ = 38, RULE_pattern = 39, 
		RULE_pattern1 = 40, RULE_pattern2 = 41, RULE_pattern3 = 42, RULE_simplePattern = 43, 
		RULE_patterns = 44, RULE_typeParamClause = 45, RULE_funTypeParamClause = 46, 
		RULE_variantTypeParam = 47, RULE_typeParam = 48, RULE_paramClauses = 49, 
		RULE_paramClause = 50, RULE_params = 51, RULE_param = 52, RULE_paramType = 53, 
		RULE_classParamClauses = 54, RULE_classParamClause = 55, RULE_classParams = 56, 
		RULE_classParam = 57, RULE_bindings = 58, RULE_binding = 59, RULE_modifier = 60, 
		RULE_localModifier = 61, RULE_accessModifier = 62, RULE_accessQualifier = 63, 
		RULE_annotation = 64, RULE_constrAnnotation = 65, RULE_templateBody = 66, 
		RULE_templateStat = 67, RULE_selfType = 68, RULE_import_ = 69, RULE_importExpr = 70, 
		RULE_importSelectors = 71, RULE_importSelector = 72, RULE_dcl = 73, RULE_valDcl = 74, 
		RULE_varDcl = 75, RULE_funDcl = 76, RULE_funSig = 77, RULE_typeDcl = 78, 
		RULE_patVarDef = 79, RULE_def_ = 80, RULE_patDef = 81, RULE_varDef = 82, 
		RULE_funDef = 83, RULE_typeDef = 84, RULE_tmplDef = 85, RULE_classDef = 86, 
		RULE_traitDef = 87, RULE_objectDef = 88, RULE_classTemplateOpt = 89, RULE_traitTemplateOpt = 90, 
		RULE_classTemplate = 91, RULE_traitTemplate = 92, RULE_classParents = 93, 
		RULE_traitParents = 94, RULE_constr = 95, RULE_earlyDefs = 96, RULE_earlyDef = 97, 
		RULE_constrExpr = 98, RULE_constrBlock = 99, RULE_selfInvocation = 100, 
		RULE_topStatSeq = 101, RULE_topStat = 102, RULE_packaging = 103, RULE_packageObject = 104, 
		RULE_compilationUnit = 105;
	private static String[] makeRuleNames() {
		return new String[] {
			"literal", "qualId", "ids", "stableId", "classQualifier", "type_", "functionArgTypes", 
			"existentialClause", "existentialDcl", "infixType", "compoundType", "annotType", 
			"simpleType", "typeArgs", "types", "refinement", "refineStat", "typePat", 
			"ascription", "expr", "expr1", "prefixDef", "postfixExpr", "infixExpr", 
			"prefixExpr", "simpleExpr", "simpleExpr1", "exprs", "argumentExprs", 
			"args", "blockExpr", "block", "blockStat", "resultExpr", "enumerators", 
			"generator", "caseClauses", "caseClause", "guard_", "pattern", "pattern1", 
			"pattern2", "pattern3", "simplePattern", "patterns", "typeParamClause", 
			"funTypeParamClause", "variantTypeParam", "typeParam", "paramClauses", 
			"paramClause", "params", "param", "paramType", "classParamClauses", "classParamClause", 
			"classParams", "classParam", "bindings", "binding", "modifier", "localModifier", 
			"accessModifier", "accessQualifier", "annotation", "constrAnnotation", 
			"templateBody", "templateStat", "selfType", "import_", "importExpr", 
			"importSelectors", "importSelector", "dcl", "valDcl", "varDcl", "funDcl", 
			"funSig", "typeDcl", "patVarDef", "def_", "patDef", "varDef", "funDef", 
			"typeDef", "tmplDef", "classDef", "traitDef", "objectDef", "classTemplateOpt", 
			"traitTemplateOpt", "classTemplate", "traitTemplate", "classParents", 
			"traitParents", "constr", "earlyDefs", "earlyDef", "constrExpr", "constrBlock", 
			"selfInvocation", "topStatSeq", "topStat", "packaging", "packageObject", 
			"compilationUnit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'null'", "'.'", "','", "'this'", "'super'", "'['", "']'", 
			"'=>'", "'('", "')'", "'forSome'", "'{'", "'}'", "'type'", "'val'", "'with'", 
			"'#'", "':'", "'_'", "'*'", "'implicit'", "'if'", "'else'", "'while'", 
			"'try'", "'catch'", "'finally'", "'do'", "'for'", "'yield'", "'throw'", 
			"'return'", "'='", "'match'", "'+'", "'~'", "'!'", "'new'", "'lazy'", 
			"'<-'", "'case'", "'|'", "'@'", "'>:'", "'<:'", "'<%'", "'var'", "'override'", 
			"'abstract'", "'final'", "'sealed'", "'private'", "'protected'", "'import'", 
			"'def'", "'class'", "'object'", "'trait'", "'extends'", "'package'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "Id", "BooleanLiteral", "CharacterLiteral", "SymbolLiteral", 
			"IntegerLiteral", "StringLiteral", "FloatingPointLiteral", "Varid", "BoundVarid", 
			"Paren", "Delim", "Semi", "NL", "NEWLINE", "WS", "COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "Scala.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ScalaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(ScalaParser.IntegerLiteral, 0); }
		public TerminalNode FloatingPointLiteral() { return getToken(ScalaParser.FloatingPointLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(ScalaParser.BooleanLiteral, 0); }
		public TerminalNode CharacterLiteral() { return getToken(ScalaParser.CharacterLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(ScalaParser.StringLiteral, 0); }
		public TerminalNode SymbolLiteral() { return getToken(ScalaParser.SymbolLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_literal);
		int _la;
		try {
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(212);
					match(T__0);
					}
				}

				setState(215);
				match(IntegerLiteral);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(216);
					match(T__0);
					}
				}

				setState(219);
				match(FloatingPointLiteral);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				match(BooleanLiteral);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(221);
				match(CharacterLiteral);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				match(StringLiteral);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(223);
				match(SymbolLiteral);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(224);
				match(T__1);
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

	public static class QualIdContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public QualIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterQualId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitQualId(this);
		}
	}

	public final QualIdContext qualId() throws RecognitionException {
		QualIdContext _localctx = new QualIdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_qualId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(Id);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(228);
				match(T__2);
				setState(229);
				match(Id);
				}
				}
				setState(234);
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

	public static class IdsContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public IdsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ids; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterIds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitIds(this);
		}
	}

	public final IdsContext ids() throws RecognitionException {
		IdsContext _localctx = new IdsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ids);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(Id);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(236);
				match(T__3);
				setState(237);
				match(Id);
				}
				}
				setState(242);
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

	public static class StableIdContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public ClassQualifierContext classQualifier() {
			return getRuleContext(ClassQualifierContext.class,0);
		}
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public StableIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stableId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterStableId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitStableId(this);
		}
	}

	public final StableIdContext stableId() throws RecognitionException {
		return stableId(0);
	}

	private StableIdContext stableId(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StableIdContext _localctx = new StableIdContext(_ctx, _parentState);
		StableIdContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_stableId, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(244);
				match(Id);
				}
				break;
			case 2:
				{
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Id) {
					{
					setState(245);
					match(Id);
					setState(246);
					match(T__2);
					}
				}

				setState(256);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(249);
					match(T__4);
					}
					break;
				case T__5:
					{
					setState(250);
					match(T__5);
					setState(252);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__6) {
						{
						setState(251);
						classQualifier();
						}
					}

					setState(254);
					match(T__2);
					setState(255);
					match(Id);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StableIdContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stableId);
					setState(260);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(261);
					match(T__2);
					setState(262);
					match(Id);
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	public static class ClassQualifierContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ClassQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classQualifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassQualifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassQualifier(this);
		}
	}

	public final ClassQualifierContext classQualifier() throws RecognitionException {
		ClassQualifierContext _localctx = new ClassQualifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classQualifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__6);
			setState(269);
			match(Id);
			setState(270);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_Context extends ParserRuleContext {
		public FunctionArgTypesContext functionArgTypes() {
			return getRuleContext(FunctionArgTypesContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public InfixTypeContext infixType() {
			return getRuleContext(InfixTypeContext.class,0);
		}
		public ExistentialClauseContext existentialClause() {
			return getRuleContext(ExistentialClauseContext.class,0);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitType_(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_type_);
		int _la;
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				functionArgTypes();
				setState(273);
				match(T__8);
				setState(274);
				type_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				infixType();
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(277);
					existentialClause();
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

	public static class FunctionArgTypesContext extends ParserRuleContext {
		public InfixTypeContext infixType() {
			return getRuleContext(InfixTypeContext.class,0);
		}
		public List<ParamTypeContext> paramType() {
			return getRuleContexts(ParamTypeContext.class);
		}
		public ParamTypeContext paramType(int i) {
			return getRuleContext(ParamTypeContext.class,i);
		}
		public FunctionArgTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunctionArgTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunctionArgTypes(this);
		}
	}

	public final FunctionArgTypesContext functionArgTypes() throws RecognitionException {
		FunctionArgTypesContext _localctx = new FunctionArgTypesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionArgTypes);
		int _la;
		try {
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				infixType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				match(T__9);
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__8) | (1L << T__9) | (1L << T__12) | (1L << Id))) != 0) || _la==NL) {
					{
					setState(284);
					paramType();
					setState(289);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3) {
						{
						{
						setState(285);
						match(T__3);
						setState(286);
						paramType();
						}
						}
						setState(291);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(294);
				match(T__10);
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

	public static class ExistentialClauseContext extends ParserRuleContext {
		public List<ExistentialDclContext> existentialDcl() {
			return getRuleContexts(ExistentialDclContext.class);
		}
		public ExistentialDclContext existentialDcl(int i) {
			return getRuleContext(ExistentialDclContext.class,i);
		}
		public ExistentialClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existentialClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExistentialClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExistentialClause(this);
		}
	}

	public final ExistentialClauseContext existentialClause() throws RecognitionException {
		ExistentialClauseContext _localctx = new ExistentialClauseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_existentialClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(T__11);
			setState(298);
			match(T__12);
			setState(300); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(299);
				existentialDcl();
				}
				}
				setState(302); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__14 || _la==T__15 );
			setState(304);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistentialDclContext extends ParserRuleContext {
		public TypeDclContext typeDcl() {
			return getRuleContext(TypeDclContext.class,0);
		}
		public ValDclContext valDcl() {
			return getRuleContext(ValDclContext.class,0);
		}
		public ExistentialDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existentialDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExistentialDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExistentialDcl(this);
		}
	}

	public final ExistentialDclContext existentialDcl() throws RecognitionException {
		ExistentialDclContext _localctx = new ExistentialDclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_existentialDcl);
		try {
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				match(T__14);
				setState(307);
				typeDcl();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(308);
				match(T__15);
				setState(309);
				valDcl();
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

	public static class InfixTypeContext extends ParserRuleContext {
		public List<CompoundTypeContext> compoundType() {
			return getRuleContexts(CompoundTypeContext.class);
		}
		public CompoundTypeContext compoundType(int i) {
			return getRuleContext(CompoundTypeContext.class,i);
		}
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public InfixTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterInfixType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitInfixType(this);
		}
	}

	public final InfixTypeContext infixType() throws RecognitionException {
		InfixTypeContext _localctx = new InfixTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_infixType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			compoundType();
			setState(317);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(313);
					match(Id);
					setState(314);
					compoundType();
					}
					} 
				}
				setState(319);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class CompoundTypeContext extends ParserRuleContext {
		public List<AnnotTypeContext> annotType() {
			return getRuleContexts(AnnotTypeContext.class);
		}
		public AnnotTypeContext annotType(int i) {
			return getRuleContext(AnnotTypeContext.class,i);
		}
		public RefinementContext refinement() {
			return getRuleContext(RefinementContext.class,0);
		}
		public CompoundTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCompoundType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCompoundType(this);
		}
	}

	public final CompoundTypeContext compoundType() throws RecognitionException {
		CompoundTypeContext _localctx = new CompoundTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_compoundType);
		int _la;
		try {
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case T__9:
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				annotType();
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(321);
					match(T__16);
					setState(322);
					annotType();
					}
					}
					setState(327);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(329);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(328);
					refinement();
					}
					break;
				}
				}
				break;
			case T__12:
			case NL:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				refinement();
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

	public static class AnnotTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AnnotTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAnnotType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAnnotType(this);
		}
	}

	public final AnnotTypeContext annotType() throws RecognitionException {
		AnnotTypeContext _localctx = new AnnotTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_annotType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			simpleType(0);
			setState(338);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(335);
					annotation();
					}
					} 
				}
				setState(340);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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

	public static class SimpleTypeContext extends ParserRuleContext {
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public TypeArgsContext typeArgs() {
			return getRuleContext(TypeArgsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimpleType(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		return simpleType(0);
	}

	private SimpleTypeContext simpleType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, _parentState);
		SimpleTypeContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_simpleType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
			case T__5:
			case Id:
				{
				setState(342);
				stableId(0);
				setState(345);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(343);
					match(T__2);
					setState(344);
					match(T__14);
					}
					break;
				}
				}
				break;
			case T__9:
				{
				setState(347);
				match(T__9);
				setState(348);
				types();
				setState(349);
				match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(360);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(358);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new SimpleTypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleType);
						setState(353);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(354);
						typeArgs();
						}
						break;
					case 2:
						{
						_localctx = new SimpleTypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleType);
						setState(355);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(356);
						match(T__17);
						setState(357);
						match(Id);
						}
						break;
					}
					} 
				}
				setState(362);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class TypeArgsContext extends ParserRuleContext {
		public TypesContext types() {
			return getRuleContext(TypesContext.class,0);
		}
		public TypeArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeArgs(this);
		}
	}

	public final TypeArgsContext typeArgs() throws RecognitionException {
		TypeArgsContext _localctx = new TypeArgsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeArgs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(T__6);
			setState(364);
			types();
			setState(365);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypesContext extends ParserRuleContext {
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_types; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypes(this);
		}
	}

	public final TypesContext types() throws RecognitionException {
		TypesContext _localctx = new TypesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_types);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			type_();
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(368);
				match(T__3);
				setState(369);
				type_();
				}
				}
				setState(374);
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

	public static class RefinementContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public List<RefineStatContext> refineStat() {
			return getRuleContexts(RefineStatContext.class);
		}
		public RefineStatContext refineStat(int i) {
			return getRuleContext(RefineStatContext.class,i);
		}
		public RefinementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refinement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterRefinement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitRefinement(this);
		}
	}

	public final RefinementContext refinement() throws RecognitionException {
		RefinementContext _localctx = new RefinementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_refinement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(375);
				match(NL);
				}
			}

			setState(378);
			match(T__12);
			setState(380); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(379);
				refineStat();
				}
				}
				setState(382); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__47) | (1L << T__55))) != 0) );
			setState(384);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RefineStatContext extends ParserRuleContext {
		public DclContext dcl() {
			return getRuleContext(DclContext.class,0);
		}
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public RefineStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refineStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterRefineStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitRefineStat(this);
		}
	}

	public final RefineStatContext refineStat() throws RecognitionException {
		RefineStatContext _localctx = new RefineStatContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_refineStat);
		try {
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				dcl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				match(T__14);
				setState(388);
				typeDef();
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

	public static class TypePatContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypePatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typePat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypePat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypePat(this);
		}
	}

	public final TypePatContext typePat() throws RecognitionException {
		TypePatContext _localctx = new TypePatContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typePat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AscriptionContext extends ParserRuleContext {
		public InfixTypeContext infixType() {
			return getRuleContext(InfixTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AscriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ascription; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAscription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAscription(this);
		}
	}

	public final AscriptionContext ascription() throws RecognitionException {
		AscriptionContext _localctx = new AscriptionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ascription);
		try {
			int _alt;
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(393);
				match(T__18);
				setState(394);
				infixType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(395);
				match(T__18);
				setState(397); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(396);
						annotation();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(399); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(401);
				match(T__18);
				setState(402);
				match(T__19);
				setState(403);
				match(T__20);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BindingsContext bindings() {
			return getRuleContext(BindingsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr);
		int _la;
		try {
			setState(417);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(412);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(406);
					bindings();
					}
					break;
				case T__21:
				case Id:
					{
					setState(408);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__21) {
						{
						setState(407);
						match(T__21);
						}
					}

					setState(410);
					match(Id);
					}
					break;
				case T__19:
					{
					setState(411);
					match(T__19);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(414);
				match(T__8);
				setState(415);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				expr1();
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

	public static class Expr1Context extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public EnumeratorsContext enumerators() {
			return getRuleContext(EnumeratorsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public SimpleExprContext simpleExpr() {
			return getRuleContext(SimpleExprContext.class,0);
		}
		public SimpleExpr1Context simpleExpr1() {
			return getRuleContext(SimpleExpr1Context.class,0);
		}
		public ArgumentExprsContext argumentExprs() {
			return getRuleContext(ArgumentExprsContext.class,0);
		}
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public AscriptionContext ascription() {
			return getRuleContext(AscriptionContext.class,0);
		}
		public CaseClausesContext caseClauses() {
			return getRuleContext(CaseClausesContext.class,0);
		}
		public Expr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExpr1(this);
		}
	}

	public final Expr1Context expr1() throws RecognitionException {
		Expr1Context _localctx = new Expr1Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_expr1);
		int _la;
		try {
			setState(514);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(419);
				match(T__22);
				setState(420);
				match(T__9);
				setState(421);
				expr();
				setState(422);
				match(T__10);
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(423);
					match(NL);
					}
					}
					setState(428);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(429);
				expr();
				setState(432);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(430);
					match(T__23);
					setState(431);
					expr();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(434);
				match(T__24);
				setState(435);
				match(T__9);
				setState(436);
				expr();
				setState(437);
				match(T__10);
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(438);
					match(NL);
					}
					}
					setState(443);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(444);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(446);
				match(T__25);
				setState(447);
				expr();
				setState(450);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(448);
					match(T__26);
					setState(449);
					expr();
					}
					break;
				}
				setState(454);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(452);
					match(T__27);
					setState(453);
					expr();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(456);
				match(T__28);
				setState(457);
				expr();
				setState(458);
				match(T__24);
				setState(459);
				match(T__9);
				setState(460);
				expr();
				setState(461);
				match(T__10);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(463);
				match(T__29);
				setState(472);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(464);
					match(T__9);
					setState(465);
					enumerators();
					setState(466);
					match(T__10);
					}
					break;
				case T__12:
					{
					setState(468);
					match(T__12);
					setState(469);
					enumerators();
					setState(470);
					match(T__13);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(475);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__30) {
					{
					setState(474);
					match(T__30);
					}
				}

				setState(477);
				expr();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(479);
				match(T__31);
				setState(480);
				expr();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(481);
				match(T__32);
				setState(483);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(482);
					expr();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(494);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(490);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						setState(485);
						simpleExpr();
						}
						break;
					case 2:
						{
						setState(486);
						simpleExpr1(0);
						setState(488);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(487);
							match(T__19);
							}
						}

						}
						break;
					}
					setState(492);
					match(T__2);
					}
					break;
				}
				setState(496);
				match(Id);
				setState(497);
				match(T__33);
				setState(498);
				expr();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(499);
				simpleExpr1(0);
				setState(500);
				argumentExprs();
				setState(501);
				match(T__33);
				setState(502);
				expr();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(504);
				postfixExpr();
				setState(506);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(505);
					ascription();
					}
				}

				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(508);
				postfixExpr();
				setState(509);
				match(T__34);
				setState(510);
				match(T__12);
				setState(511);
				caseClauses();
				setState(512);
				match(T__13);
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

	public static class PrefixDefContext extends ParserRuleContext {
		public PrefixDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPrefixDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPrefixDef(this);
		}
	}

	public final PrefixDefContext prefixDef() throws RecognitionException {
		PrefixDefContext _localctx = new PrefixDefContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_prefixDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
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

	public static class PostfixExprContext extends ParserRuleContext {
		public InfixExprContext infixExpr() {
			return getRuleContext(InfixExprContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public List<PrefixDefContext> prefixDef() {
			return getRuleContexts(PrefixDefContext.class);
		}
		public PrefixDefContext prefixDef(int i) {
			return getRuleContext(PrefixDefContext.class,i);
		}
		public List<SimpleExpr1Context> simpleExpr1() {
			return getRuleContexts(SimpleExpr1Context.class);
		}
		public SimpleExpr1Context simpleExpr1(int i) {
			return getRuleContext(SimpleExpr1Context.class,i);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public PostfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPostfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPostfixExpr(this);
		}
	}

	public final PostfixExprContext postfixExpr() throws RecognitionException {
		PostfixExprContext _localctx = new PostfixExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_postfixExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			infixExpr(0);
			setState(520);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(519);
				match(Id);
				}
				break;
			}
			setState(527);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(522);
					prefixDef();
					setState(523);
					simpleExpr1(0);
					}
					} 
				}
				setState(529);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
			}
			setState(531);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(530);
				match(NL);
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

	public static class InfixExprContext extends ParserRuleContext {
		public PrefixExprContext prefixExpr() {
			return getRuleContext(PrefixExprContext.class,0);
		}
		public List<InfixExprContext> infixExpr() {
			return getRuleContexts(InfixExprContext.class);
		}
		public InfixExprContext infixExpr(int i) {
			return getRuleContext(InfixExprContext.class,i);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public InfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterInfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitInfixExpr(this);
		}
	}

	public final InfixExprContext infixExpr() throws RecognitionException {
		return infixExpr(0);
	}

	private InfixExprContext infixExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InfixExprContext _localctx = new InfixExprContext(_ctx, _parentState);
		InfixExprContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_infixExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(534);
			prefixExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(544);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InfixExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_infixExpr);
					setState(536);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(537);
					match(Id);
					setState(539);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(538);
						match(NL);
						}
					}

					setState(541);
					infixExpr(2);
					}
					} 
				}
				setState(546);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
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

	public static class PrefixExprContext extends ParserRuleContext {
		public SimpleExprContext simpleExpr() {
			return getRuleContext(SimpleExprContext.class,0);
		}
		public SimpleExpr1Context simpleExpr1() {
			return getRuleContext(SimpleExpr1Context.class,0);
		}
		public PrefixDefContext prefixDef() {
			return getRuleContext(PrefixDefContext.class,0);
		}
		public PrefixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPrefixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPrefixExpr(this);
		}
	}

	public final PrefixExprContext prefixExpr() throws RecognitionException {
		PrefixExprContext _localctx = new PrefixExprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_prefixExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(547);
				prefixDef();
				}
				break;
			}
			setState(555);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(550);
				simpleExpr();
				}
				break;
			case 2:
				{
				setState(551);
				simpleExpr1(0);
				setState(553);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(552);
					match(T__19);
					}
					break;
				}
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

	public static class SimpleExprContext extends ParserRuleContext {
		public ClassTemplateContext classTemplate() {
			return getRuleContext(ClassTemplateContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public BlockExprContext blockExpr() {
			return getRuleContext(BlockExprContext.class,0);
		}
		public SimpleExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimpleExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimpleExpr(this);
		}
	}

	public final SimpleExprContext simpleExpr() throws RecognitionException {
		SimpleExprContext _localctx = new SimpleExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_simpleExpr);
		try {
			setState(563);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(557);
				match(T__38);
				setState(560);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(558);
					classTemplate();
					}
					break;
				case 2:
					{
					setState(559);
					templateBody();
					}
					break;
				}
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(562);
				blockExpr();
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

	public static class SimpleExpr1Context extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public SimpleExprContext simpleExpr() {
			return getRuleContext(SimpleExprContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TypeArgsContext typeArgs() {
			return getRuleContext(TypeArgsContext.class,0);
		}
		public SimpleExpr1Context simpleExpr1() {
			return getRuleContext(SimpleExpr1Context.class,0);
		}
		public ArgumentExprsContext argumentExprs() {
			return getRuleContext(ArgumentExprsContext.class,0);
		}
		public SimpleExpr1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpr1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimpleExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimpleExpr1(this);
		}
	}

	public final SimpleExpr1Context simpleExpr1() throws RecognitionException {
		return simpleExpr1(0);
	}

	private SimpleExpr1Context simpleExpr1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SimpleExpr1Context _localctx = new SimpleExpr1Context(_ctx, _parentState);
		SimpleExpr1Context _prevctx = _localctx;
		int _startState = 52;
		enterRecursionRule(_localctx, 52, RULE_simpleExpr1, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(566);
				literal();
				}
				break;
			case 2:
				{
				setState(567);
				stableId(0);
				}
				break;
			case 3:
				{
				setState(568);
				match(T__19);
				}
				break;
			case 4:
				{
				setState(569);
				match(T__9);
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
					{
					setState(570);
					exprs();
					}
				}

				setState(573);
				match(T__10);
				}
				break;
			case 5:
				{
				setState(574);
				simpleExpr();
				setState(575);
				match(T__2);
				setState(576);
				match(Id);
				}
				break;
			case 6:
				{
				setState(578);
				simpleExpr();
				setState(579);
				typeArgs();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(598);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(596);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
					case 1:
						{
						_localctx = new SimpleExpr1Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleExpr1);
						setState(583);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(585);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(584);
							match(T__19);
							}
						}

						setState(587);
						match(T__2);
						setState(588);
						match(Id);
						}
						break;
					case 2:
						{
						_localctx = new SimpleExpr1Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleExpr1);
						setState(589);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(591);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(590);
							match(T__19);
							}
						}

						setState(593);
						typeArgs();
						}
						break;
					case 3:
						{
						_localctx = new SimpleExpr1Context(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_simpleExpr1);
						setState(594);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(595);
						argumentExprs();
						}
						break;
					}
					} 
				}
				setState(600);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
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

	public static class ExprsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitExprs(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_exprs);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(601);
			expr();
			setState(606);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(602);
					match(T__3);
					setState(603);
					expr();
					}
					} 
				}
				setState(608);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
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

	public static class ArgumentExprsContext extends ParserRuleContext {
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public BlockExprContext blockExpr() {
			return getRuleContext(BlockExprContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ArgumentExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentExprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterArgumentExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitArgumentExprs(this);
		}
	}

	public final ArgumentExprsContext argumentExprs() throws RecognitionException {
		ArgumentExprsContext _localctx = new ArgumentExprsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_argumentExprs);
		int _la;
		try {
			setState(621);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(609);
				match(T__9);
				setState(610);
				args();
				setState(611);
				match(T__10);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(613);
				match(T__12);
				setState(614);
				args();
				setState(615);
				match(T__13);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(618);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(617);
					match(NL);
					}
				}

				setState(620);
				blockExpr();
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

	public static class ArgsContext extends ParserRuleContext {
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_args);
		int _la;
		try {
			setState(635);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(624);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
					{
					setState(623);
					exprs();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(629);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					{
					setState(626);
					exprs();
					setState(627);
					match(T__3);
					}
					break;
				}
				setState(631);
				postfixExpr();
				setState(633);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) {
					{
					setState(632);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__19) | (1L << T__20))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
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

	public static class BlockExprContext extends ParserRuleContext {
		public CaseClausesContext caseClauses() {
			return getRuleContext(CaseClausesContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBlockExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBlockExpr(this);
		}
	}

	public final BlockExprContext blockExpr() throws RecognitionException {
		BlockExprContext _localctx = new BlockExprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_blockExpr);
		try {
			setState(645);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(637);
				match(T__12);
				setState(638);
				caseClauses();
				setState(639);
				match(T__13);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(641);
				match(T__12);
				setState(642);
				block();
				setState(643);
				match(T__13);
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

	public static class BlockContext extends ParserRuleContext {
		public List<BlockStatContext> blockStat() {
			return getRuleContexts(BlockStatContext.class);
		}
		public BlockStatContext blockStat(int i) {
			return getRuleContext(BlockStatContext.class,i);
		}
		public ResultExprContext resultExpr() {
			return getRuleContext(ResultExprContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(648); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(647);
					blockStat();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(650); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(653);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
				{
				setState(652);
				resultExpr();
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

	public static class BlockStatContext extends ParserRuleContext {
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public Def_Context def_() {
			return getRuleContext(Def_Context.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TmplDefContext tmplDef() {
			return getRuleContext(TmplDefContext.class,0);
		}
		public List<LocalModifierContext> localModifier() {
			return getRuleContexts(LocalModifierContext.class);
		}
		public LocalModifierContext localModifier(int i) {
			return getRuleContext(LocalModifierContext.class,i);
		}
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public BlockStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBlockStat(this);
		}
	}

	public final BlockStatContext blockStat() throws RecognitionException {
		BlockStatContext _localctx = new BlockStatContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_blockStat);
		int _la;
		try {
			setState(680);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(655);
				import_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(659);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(656);
					annotation();
					}
					}
					setState(661);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(663);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21 || _la==T__39) {
					{
					setState(662);
					_la = _input.LA(1);
					if ( !(_la==T__21 || _la==T__39) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(665);
				def_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(669);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(666);
					annotation();
					}
					}
					setState(671);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(675);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__49) | (1L << T__50) | (1L << T__51))) != 0)) {
					{
					{
					setState(672);
					localModifier();
					}
					}
					setState(677);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(678);
				tmplDef();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(679);
				expr1();
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

	public static class ResultExprContext extends ParserRuleContext {
		public Expr1Context expr1() {
			return getRuleContext(Expr1Context.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BindingsContext bindings() {
			return getRuleContext(BindingsContext.class,0);
		}
		public CompoundTypeContext compoundType() {
			return getRuleContext(CompoundTypeContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ResultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterResultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitResultExpr(this);
		}
	}

	public final ResultExprContext resultExpr() throws RecognitionException {
		ResultExprContext _localctx = new ResultExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_resultExpr);
		int _la;
		try {
			setState(698);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(682);
				expr1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(693);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__9:
					{
					setState(683);
					bindings();
					}
					break;
				case T__19:
				case T__21:
				case Id:
					{
					setState(689);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__21:
					case Id:
						{
						setState(685);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__21) {
							{
							setState(684);
							match(T__21);
							}
						}

						setState(687);
						match(Id);
						}
						break;
					case T__19:
						{
						setState(688);
						match(T__19);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(691);
					match(T__18);
					setState(692);
					compoundType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(695);
				match(T__8);
				setState(696);
				block();
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

	public static class EnumeratorsContext extends ParserRuleContext {
		public List<GeneratorContext> generator() {
			return getRuleContexts(GeneratorContext.class);
		}
		public GeneratorContext generator(int i) {
			return getRuleContext(GeneratorContext.class,i);
		}
		public EnumeratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumerators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterEnumerators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitEnumerators(this);
		}
	}

	public final EnumeratorsContext enumerators() throws RecognitionException {
		EnumeratorsContext _localctx = new EnumeratorsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_enumerators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(701); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(700);
				generator();
				}
				}
				setState(703); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__19) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (Varid - 64)) | (1L << (BoundVarid - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneratorContext extends ParserRuleContext {
		public List<Pattern1Context> pattern1() {
			return getRuleContexts(Pattern1Context.class);
		}
		public Pattern1Context pattern1(int i) {
			return getRuleContext(Pattern1Context.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Guard_Context> guard_() {
			return getRuleContexts(Guard_Context.class);
		}
		public Guard_Context guard_(int i) {
			return getRuleContext(Guard_Context.class,i);
		}
		public GeneratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterGenerator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitGenerator(this);
		}
	}

	public final GeneratorContext generator() throws RecognitionException {
		GeneratorContext _localctx = new GeneratorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_generator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(705);
			pattern1();
			setState(706);
			match(T__40);
			setState(707);
			expr();
			setState(715);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(713);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__22:
						{
						setState(708);
						guard_();
						}
						break;
					case T__0:
					case T__1:
					case T__4:
					case T__5:
					case T__9:
					case T__19:
					case Id:
					case BooleanLiteral:
					case CharacterLiteral:
					case SymbolLiteral:
					case IntegerLiteral:
					case StringLiteral:
					case FloatingPointLiteral:
					case Varid:
					case BoundVarid:
						{
						setState(709);
						pattern1();
						setState(710);
						match(T__33);
						setState(711);
						expr();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(717);
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

	public static class CaseClausesContext extends ParserRuleContext {
		public List<CaseClauseContext> caseClause() {
			return getRuleContexts(CaseClauseContext.class);
		}
		public CaseClauseContext caseClause(int i) {
			return getRuleContext(CaseClauseContext.class,i);
		}
		public CaseClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCaseClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCaseClauses(this);
		}
	}

	public final CaseClausesContext caseClauses() throws RecognitionException {
		CaseClausesContext _localctx = new CaseClausesContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_caseClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(718);
				caseClause();
				}
				}
				setState(721); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__41 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseClauseContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Guard_Context guard_() {
			return getRuleContext(Guard_Context.class,0);
		}
		public CaseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCaseClause(this);
		}
	}

	public final CaseClauseContext caseClause() throws RecognitionException {
		CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_caseClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(723);
			match(T__41);
			setState(724);
			pattern();
			setState(726);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(725);
				guard_();
				}
			}

			setState(728);
			match(T__8);
			setState(729);
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

	public static class Guard_Context extends ParserRuleContext {
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public Guard_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_guard_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterGuard_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitGuard_(this);
		}
	}

	public final Guard_Context guard_() throws RecognitionException {
		Guard_Context _localctx = new Guard_Context(_ctx, getState());
		enterRule(_localctx, 76, RULE_guard_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731);
			match(T__22);
			setState(732);
			postfixExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public List<Pattern1Context> pattern1() {
			return getRuleContexts(Pattern1Context.class);
		}
		public Pattern1Context pattern1(int i) {
			return getRuleContext(Pattern1Context.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			pattern1();
			setState(739);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(735);
				match(T__42);
				setState(736);
				pattern1();
				}
				}
				setState(741);
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

	public static class Pattern1Context extends ParserRuleContext {
		public TypePatContext typePat() {
			return getRuleContext(TypePatContext.class,0);
		}
		public TerminalNode BoundVarid() { return getToken(ScalaParser.BoundVarid, 0); }
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Pattern2Context pattern2() {
			return getRuleContext(Pattern2Context.class,0);
		}
		public Pattern1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern1(this);
		}
	}

	public final Pattern1Context pattern1() throws RecognitionException {
		Pattern1Context _localctx = new Pattern1Context(_ctx, getState());
		enterRule(_localctx, 80, RULE_pattern1);
		int _la;
		try {
			setState(746);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(742);
				_la = _input.LA(1);
				if ( !(((((_la - 20)) & ~0x3f) == 0 && ((1L << (_la - 20)) & ((1L << (T__19 - 20)) | (1L << (Id - 20)) | (1L << (BoundVarid - 20)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(743);
				match(T__18);
				setState(744);
				typePat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(745);
				pattern2();
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

	public static class Pattern2Context extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Pattern3Context pattern3() {
			return getRuleContext(Pattern3Context.class,0);
		}
		public Pattern2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern2(this);
		}
	}

	public final Pattern2Context pattern2() throws RecognitionException {
		Pattern2Context _localctx = new Pattern2Context(_ctx, getState());
		enterRule(_localctx, 82, RULE_pattern2);
		int _la;
		try {
			setState(754);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(748);
				match(Id);
				setState(751);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__43) {
					{
					setState(749);
					match(T__43);
					setState(750);
					pattern3();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(753);
				pattern3();
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

	public static class Pattern3Context extends ParserRuleContext {
		public List<SimplePatternContext> simplePattern() {
			return getRuleContexts(SimplePatternContext.class);
		}
		public SimplePatternContext simplePattern(int i) {
			return getRuleContext(SimplePatternContext.class,i);
		}
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public Pattern3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPattern3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPattern3(this);
		}
	}

	public final Pattern3Context pattern3() throws RecognitionException {
		Pattern3Context _localctx = new Pattern3Context(_ctx, getState());
		enterRule(_localctx, 84, RULE_pattern3);
		int _la;
		try {
			setState(768);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(756);
				simplePattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(757);
				simplePattern();
				setState(765);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Id) {
					{
					{
					setState(758);
					match(Id);
					setState(760);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(759);
						match(NL);
						}
					}

					setState(762);
					simplePattern();
					}
					}
					setState(767);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class SimplePatternContext extends ParserRuleContext {
		public TerminalNode Varid() { return getToken(ScalaParser.Varid, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public PatternsContext patterns() {
			return getRuleContext(PatternsContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public SimplePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simplePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSimplePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSimplePattern(this);
		}
	}

	public final SimplePatternContext simplePattern() throws RecognitionException {
		SimplePatternContext _localctx = new SimplePatternContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_simplePattern);
		int _la;
		try {
			setState(801);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(770);
				match(T__19);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(771);
				match(Varid);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(772);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(773);
				stableId(0);
				setState(779);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__9) {
					{
					setState(774);
					match(T__9);
					setState(776);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__19) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (Varid - 64)) | (1L << (BoundVarid - 64)))) != 0)) {
						{
						setState(775);
						patterns();
						}
					}

					setState(778);
					match(T__10);
					}
				}

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(781);
				stableId(0);
				setState(782);
				match(T__9);
				setState(786);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(783);
					patterns();
					setState(784);
					match(T__3);
					}
					break;
				}
				setState(790);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Id) {
					{
					setState(788);
					match(Id);
					setState(789);
					match(T__43);
					}
				}

				setState(792);
				match(T__19);
				setState(793);
				match(T__20);
				setState(794);
				match(T__10);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(796);
				match(T__9);
				setState(798);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__19) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)) | (1L << (Varid - 64)) | (1L << (BoundVarid - 64)))) != 0)) {
					{
					setState(797);
					patterns();
					}
				}

				setState(800);
				match(T__10);
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

	public static class PatternsContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public PatternsContext patterns() {
			return getRuleContext(PatternsContext.class,0);
		}
		public PatternsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patterns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPatterns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPatterns(this);
		}
	}

	public final PatternsContext patterns() throws RecognitionException {
		PatternsContext _localctx = new PatternsContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_patterns);
		try {
			setState(810);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(803);
				pattern();
				setState(806);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					{
					setState(804);
					match(T__3);
					setState(805);
					patterns();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(808);
				match(T__19);
				setState(809);
				match(T__20);
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

	public static class TypeParamClauseContext extends ParserRuleContext {
		public List<VariantTypeParamContext> variantTypeParam() {
			return getRuleContexts(VariantTypeParamContext.class);
		}
		public VariantTypeParamContext variantTypeParam(int i) {
			return getRuleContext(VariantTypeParamContext.class,i);
		}
		public TypeParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParamClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeParamClause(this);
		}
	}

	public final TypeParamClauseContext typeParamClause() throws RecognitionException {
		TypeParamClauseContext _localctx = new TypeParamClauseContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_typeParamClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(812);
			match(T__6);
			setState(813);
			variantTypeParam();
			setState(818);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(814);
				match(T__3);
				setState(815);
				variantTypeParam();
				}
				}
				setState(820);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(821);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunTypeParamClauseContext extends ParserRuleContext {
		public List<TypeParamContext> typeParam() {
			return getRuleContexts(TypeParamContext.class);
		}
		public TypeParamContext typeParam(int i) {
			return getRuleContext(TypeParamContext.class,i);
		}
		public FunTypeParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funTypeParamClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunTypeParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunTypeParamClause(this);
		}
	}

	public final FunTypeParamClauseContext funTypeParamClause() throws RecognitionException {
		FunTypeParamClauseContext _localctx = new FunTypeParamClauseContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_funTypeParamClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(823);
			match(T__6);
			setState(824);
			typeParam();
			setState(829);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(825);
				match(T__3);
				setState(826);
				typeParam();
				}
				}
				setState(831);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(832);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariantTypeParamContext extends ParserRuleContext {
		public TypeParamContext typeParam() {
			return getRuleContext(TypeParamContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public VariantTypeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variantTypeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterVariantTypeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitVariantTypeParam(this);
		}
	}

	public final VariantTypeParamContext variantTypeParam() throws RecognitionException {
		VariantTypeParamContext _localctx = new VariantTypeParamContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_variantTypeParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(834);
				annotation();
				}
				}
				setState(839);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(841);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==T__35) {
				{
				setState(840);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==T__35) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(843);
			typeParam();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParamContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TypeParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeParam(this);
		}
	}

	public final TypeParamContext typeParam() throws RecognitionException {
		TypeParamContext _localctx = new TypeParamContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_typeParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(845);
			_la = _input.LA(1);
			if ( !(_la==T__19 || _la==Id) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(847);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(846);
				typeParamClause();
				}
			}

			setState(851);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(849);
				match(T__44);
				setState(850);
				type_();
				}
			}

			setState(855);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__45) {
				{
				setState(853);
				match(T__45);
				setState(854);
				type_();
				}
			}

			setState(861);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__46) {
				{
				{
				setState(857);
				match(T__46);
				setState(858);
				type_();
				}
				}
				setState(863);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(868);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(864);
				match(T__18);
				setState(865);
				type_();
				}
				}
				setState(870);
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

	public static class ParamClausesContext extends ParserRuleContext {
		public List<ParamClauseContext> paramClause() {
			return getRuleContexts(ParamClauseContext.class);
		}
		public ParamClauseContext paramClause(int i) {
			return getRuleContext(ParamClauseContext.class,i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ParamClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParamClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParamClauses(this);
		}
	}

	public final ParamClausesContext paramClauses() throws RecognitionException {
		ParamClausesContext _localctx = new ParamClausesContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_paramClauses);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(874);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(871);
					paramClause();
					}
					} 
				}
				setState(876);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			}
			setState(885);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				{
				setState(878);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(877);
					match(NL);
					}
				}

				setState(880);
				match(T__9);
				setState(881);
				match(T__21);
				setState(882);
				params();
				setState(883);
				match(T__10);
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

	public static class ParamClauseContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParamClause(this);
		}
	}

	public final ParamClauseContext paramClause() throws RecognitionException {
		ParamClauseContext _localctx = new ParamClauseContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_paramClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(888);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(887);
				match(NL);
				}
			}

			setState(890);
			match(T__9);
			setState(892);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__43 || _la==Id) {
				{
				setState(891);
				params();
				}
			}

			setState(894);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(896);
			param();
			setState(901);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(897);
				match(T__3);
				setState(898);
				param();
				}
				}
				setState(903);
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

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ParamTypeContext paramType() {
			return getRuleContext(ParamTypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(907);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(904);
				annotation();
				}
				}
				setState(909);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(910);
			match(Id);
			setState(913);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(911);
				match(T__18);
				setState(912);
				paramType();
				}
			}

			setState(917);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(915);
				match(T__33);
				setState(916);
				expr();
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

	public static class ParamTypeContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public ParamTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterParamType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitParamType(this);
		}
	}

	public final ParamTypeContext paramType() throws RecognitionException {
		ParamTypeContext _localctx = new ParamTypeContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_paramType);
		try {
			setState(925);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(919);
				type_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(920);
				match(T__8);
				setState(921);
				type_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(922);
				type_();
				setState(923);
				match(T__20);
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

	public static class ClassParamClausesContext extends ParserRuleContext {
		public List<ClassParamClauseContext> classParamClause() {
			return getRuleContexts(ClassParamClauseContext.class);
		}
		public ClassParamClauseContext classParamClause(int i) {
			return getRuleContext(ClassParamClauseContext.class,i);
		}
		public ClassParamsContext classParams() {
			return getRuleContext(ClassParamsContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ClassParamClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParamClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParamClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParamClauses(this);
		}
	}

	public final ClassParamClausesContext classParamClauses() throws RecognitionException {
		ClassParamClausesContext _localctx = new ClassParamClausesContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_classParamClauses);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(930);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,122,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(927);
					classParamClause();
					}
					} 
				}
				setState(932);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,122,_ctx);
			}
			setState(941);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
			case 1:
				{
				setState(934);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(933);
					match(NL);
					}
				}

				setState(936);
				match(T__9);
				setState(937);
				match(T__21);
				setState(938);
				classParams();
				setState(939);
				match(T__10);
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

	public static class ClassParamClauseContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ClassParamsContext classParams() {
			return getRuleContext(ClassParamsContext.class,0);
		}
		public ClassParamClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParamClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParamClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParamClause(this);
		}
	}

	public final ClassParamClauseContext classParamClause() throws RecognitionException {
		ClassParamClauseContext _localctx = new ClassParamClauseContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_classParamClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(944);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(943);
				match(NL);
				}
			}

			setState(946);
			match(T__9);
			setState(948);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__21) | (1L << T__39) | (1L << T__43) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << Id))) != 0)) {
				{
				setState(947);
				classParams();
				}
			}

			setState(950);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassParamsContext extends ParserRuleContext {
		public List<ClassParamContext> classParam() {
			return getRuleContexts(ClassParamContext.class);
		}
		public ClassParamContext classParam(int i) {
			return getRuleContext(ClassParamContext.class,i);
		}
		public ClassParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParams(this);
		}
	}

	public final ClassParamsContext classParams() throws RecognitionException {
		ClassParamsContext _localctx = new ClassParamsContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_classParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(952);
			classParam();
			setState(957);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(953);
				match(T__3);
				setState(954);
				classParam();
				}
				}
				setState(959);
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

	public static class ClassParamContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ParamTypeContext paramType() {
			return getRuleContext(ParamTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ClassParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParam(this);
		}
	}

	public final ClassParamContext classParam() throws RecognitionException {
		ClassParamContext _localctx = new ClassParamContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_classParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(963);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(960);
				annotation();
				}
				}
				setState(965);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(969);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
				{
				{
				setState(966);
				modifier();
				}
				}
				setState(971);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(973);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15 || _la==T__47) {
				{
				setState(972);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__47) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(975);
			match(Id);
			setState(976);
			match(T__18);
			setState(977);
			paramType();
			setState(980);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(978);
				match(T__33);
				setState(979);
				expr();
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

	public static class BindingsContext extends ParserRuleContext {
		public List<BindingContext> binding() {
			return getRuleContexts(BindingContext.class);
		}
		public BindingContext binding(int i) {
			return getRuleContext(BindingContext.class,i);
		}
		public BindingsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindings; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBindings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBindings(this);
		}
	}

	public final BindingsContext bindings() throws RecognitionException {
		BindingsContext _localctx = new BindingsContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_bindings);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(982);
			match(T__9);
			setState(983);
			binding();
			setState(988);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(984);
				match(T__3);
				setState(985);
				binding();
				}
				}
				setState(990);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(991);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BindingContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public BindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitBinding(this);
		}
	}

	public final BindingContext binding() throws RecognitionException {
		BindingContext _localctx = new BindingContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_binding);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			_la = _input.LA(1);
			if ( !(_la==T__19 || _la==Id) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(996);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(994);
				match(T__18);
				setState(995);
				type_();
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

	public static class ModifierContext extends ParserRuleContext {
		public LocalModifierContext localModifier() {
			return getRuleContext(LocalModifierContext.class,0);
		}
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitModifier(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_modifier);
		try {
			setState(1001);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
			case T__39:
			case T__49:
			case T__50:
			case T__51:
				enterOuterAlt(_localctx, 1);
				{
				setState(998);
				localModifier();
				}
				break;
			case T__52:
			case T__53:
				enterOuterAlt(_localctx, 2);
				{
				setState(999);
				accessModifier();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 3);
				{
				setState(1000);
				match(T__48);
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

	public static class LocalModifierContext extends ParserRuleContext {
		public LocalModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterLocalModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitLocalModifier(this);
		}
	}

	public final LocalModifierContext localModifier() throws RecognitionException {
		LocalModifierContext _localctx = new LocalModifierContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_localModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__49) | (1L << T__50) | (1L << T__51))) != 0)) ) {
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

	public static class AccessModifierContext extends ParserRuleContext {
		public AccessQualifierContext accessQualifier() {
			return getRuleContext(AccessQualifierContext.class,0);
		}
		public AccessModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAccessModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAccessModifier(this);
		}
	}

	public final AccessModifierContext accessModifier() throws RecognitionException {
		AccessModifierContext _localctx = new AccessModifierContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_accessModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1005);
			_la = _input.LA(1);
			if ( !(_la==T__52 || _la==T__53) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1007);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1006);
				accessQualifier();
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

	public static class AccessQualifierContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public AccessQualifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessQualifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAccessQualifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAccessQualifier(this);
		}
	}

	public final AccessQualifierContext accessQualifier() throws RecognitionException {
		AccessQualifierContext _localctx = new AccessQualifierContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_accessQualifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1009);
			match(T__6);
			setState(1010);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==Id) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1011);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
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
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public List<ArgumentExprsContext> argumentExprs() {
			return getRuleContexts(ArgumentExprsContext.class);
		}
		public ArgumentExprsContext argumentExprs(int i) {
			return getRuleContext(ArgumentExprsContext.class,i);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_annotation);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1013);
			match(T__43);
			setState(1014);
			simpleType(0);
			setState(1018);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,136,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1015);
					argumentExprs();
					}
					} 
				}
				setState(1020);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,136,_ctx);
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

	public static class ConstrAnnotationContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ArgumentExprsContext argumentExprs() {
			return getRuleContext(ArgumentExprsContext.class,0);
		}
		public ConstrAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstrAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstrAnnotation(this);
		}
	}

	public final ConstrAnnotationContext constrAnnotation() throws RecognitionException {
		ConstrAnnotationContext _localctx = new ConstrAnnotationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_constrAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1021);
			match(T__43);
			setState(1022);
			simpleType(0);
			setState(1023);
			argumentExprs();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateBodyContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public SelfTypeContext selfType() {
			return getRuleContext(SelfTypeContext.class,0);
		}
		public List<TemplateStatContext> templateStat() {
			return getRuleContexts(TemplateStatContext.class);
		}
		public TemplateStatContext templateStat(int i) {
			return getRuleContext(TemplateStatContext.class,i);
		}
		public TemplateBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTemplateBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTemplateBody(this);
		}
	}

	public final TemplateBodyContext templateBody() throws RecognitionException {
		TemplateBodyContext _localctx = new TemplateBodyContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_templateBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1026);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(1025);
				match(NL);
				}
			}

			setState(1028);
			match(T__12);
			setState(1030);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
			case 1:
				{
				setState(1029);
				selfType();
				}
				break;
			}
			setState(1033); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1032);
				templateStat();
				}
				}
				setState(1035); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__41) | (1L << T__43) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0) );
			setState(1037);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TemplateStatContext extends ParserRuleContext {
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public Def_Context def_() {
			return getRuleContext(Def_Context.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public DclContext dcl() {
			return getRuleContext(DclContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TemplateStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTemplateStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTemplateStat(this);
		}
	}

	public final TemplateStatContext templateStat() throws RecognitionException {
		TemplateStatContext _localctx = new TemplateStatContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_templateStat);
		int _la;
		try {
			setState(1073);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,146,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1039);
				import_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1046);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(1040);
					annotation();
					setState(1042);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1041);
						match(NL);
						}
					}

					}
					}
					setState(1048);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1052);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
					{
					{
					setState(1049);
					modifier();
					}
					}
					setState(1054);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1055);
				def_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1062);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(1056);
					annotation();
					setState(1058);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1057);
						match(NL);
						}
					}

					}
					}
					setState(1064);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1068);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
					{
					{
					setState(1065);
					modifier();
					}
					}
					setState(1070);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1071);
				dcl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1072);
				expr();
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

	public static class SelfTypeContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public SelfTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSelfType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSelfType(this);
		}
	}

	public final SelfTypeContext selfType() throws RecognitionException {
		SelfTypeContext _localctx = new SelfTypeContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_selfType);
		int _la;
		try {
			setState(1086);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(1075);
				match(Id);
				setState(1078);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(1076);
					match(T__18);
					setState(1077);
					type_();
					}
				}

				setState(1080);
				match(T__8);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(1081);
				match(T__4);
				setState(1082);
				match(T__18);
				setState(1083);
				type_();
				setState(1084);
				match(T__8);
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

	public static class Import_Context extends ParserRuleContext {
		public List<ImportExprContext> importExpr() {
			return getRuleContexts(ImportExprContext.class);
		}
		public ImportExprContext importExpr(int i) {
			return getRuleContext(ImportExprContext.class,i);
		}
		public Import_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImport_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImport_(this);
		}
	}

	public final Import_Context import_() throws RecognitionException {
		Import_Context _localctx = new Import_Context(_ctx, getState());
		enterRule(_localctx, 138, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1088);
			match(T__54);
			setState(1089);
			importExpr();
			setState(1094);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(1090);
				match(T__3);
				setState(1091);
				importExpr();
				}
				}
				setState(1096);
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

	public static class ImportExprContext extends ParserRuleContext {
		public StableIdContext stableId() {
			return getRuleContext(StableIdContext.class,0);
		}
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ImportSelectorsContext importSelectors() {
			return getRuleContext(ImportSelectorsContext.class,0);
		}
		public ImportExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImportExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImportExpr(this);
		}
	}

	public final ImportExprContext importExpr() throws RecognitionException {
		ImportExprContext _localctx = new ImportExprContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_importExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1097);
			stableId(0);
			setState(1104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(1098);
				match(T__2);
				setState(1102);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Id:
					{
					setState(1099);
					match(Id);
					}
					break;
				case T__19:
					{
					setState(1100);
					match(T__19);
					}
					break;
				case T__12:
					{
					setState(1101);
					importSelectors();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	public static class ImportSelectorsContext extends ParserRuleContext {
		public List<ImportSelectorContext> importSelector() {
			return getRuleContexts(ImportSelectorContext.class);
		}
		public ImportSelectorContext importSelector(int i) {
			return getRuleContext(ImportSelectorContext.class,i);
		}
		public ImportSelectorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSelectors; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImportSelectors(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImportSelectors(this);
		}
	}

	public final ImportSelectorsContext importSelectors() throws RecognitionException {
		ImportSelectorsContext _localctx = new ImportSelectorsContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_importSelectors);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1106);
			match(T__12);
			setState(1112);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,152,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1107);
					importSelector();
					setState(1108);
					match(T__3);
					}
					} 
				}
				setState(1114);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,152,_ctx);
			}
			setState(1117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Id:
				{
				setState(1115);
				importSelector();
				}
				break;
			case T__19:
				{
				setState(1116);
				match(T__19);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1119);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportSelectorContext extends ParserRuleContext {
		public List<TerminalNode> Id() { return getTokens(ScalaParser.Id); }
		public TerminalNode Id(int i) {
			return getToken(ScalaParser.Id, i);
		}
		public ImportSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterImportSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitImportSelector(this);
		}
	}

	public final ImportSelectorContext importSelector() throws RecognitionException {
		ImportSelectorContext _localctx = new ImportSelectorContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_importSelector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1121);
			match(Id);
			setState(1124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(1122);
				match(T__8);
				setState(1123);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==Id) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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

	public static class DclContext extends ParserRuleContext {
		public ValDclContext valDcl() {
			return getRuleContext(ValDclContext.class,0);
		}
		public VarDclContext varDcl() {
			return getRuleContext(VarDclContext.class,0);
		}
		public FunDclContext funDcl() {
			return getRuleContext(FunDclContext.class,0);
		}
		public TypeDclContext typeDcl() {
			return getRuleContext(TypeDclContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public DclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitDcl(this);
		}
	}

	public final DclContext dcl() throws RecognitionException {
		DclContext _localctx = new DclContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_dcl);
		int _la;
		try {
			setState(1140);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(1126);
				match(T__15);
				setState(1127);
				valDcl();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(1128);
				match(T__47);
				setState(1129);
				varDcl();
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 3);
				{
				setState(1130);
				match(T__55);
				setState(1131);
				funDcl();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(1132);
				match(T__14);
				setState(1136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(1133);
					match(NL);
					}
					}
					setState(1138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1139);
				typeDcl();
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

	public static class ValDclContext extends ParserRuleContext {
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public ValDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterValDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitValDcl(this);
		}
	}

	public final ValDclContext valDcl() throws RecognitionException {
		ValDclContext _localctx = new ValDclContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_valDcl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1142);
			ids();
			setState(1143);
			match(T__18);
			setState(1144);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDclContext extends ParserRuleContext {
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public VarDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterVarDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitVarDcl(this);
		}
	}

	public final VarDclContext varDcl() throws RecognitionException {
		VarDclContext _localctx = new VarDclContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_varDcl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1146);
			ids();
			setState(1147);
			match(T__18);
			setState(1148);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunDclContext extends ParserRuleContext {
		public FunSigContext funSig() {
			return getRuleContext(FunSigContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public FunDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunDcl(this);
		}
	}

	public final FunDclContext funDcl() throws RecognitionException {
		FunDclContext _localctx = new FunDclContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_funDcl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1150);
			funSig();
			setState(1153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(1151);
				match(T__18);
				setState(1152);
				type_();
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

	public static class FunSigContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ParamClausesContext paramClauses() {
			return getRuleContext(ParamClausesContext.class,0);
		}
		public FunTypeParamClauseContext funTypeParamClause() {
			return getRuleContext(FunTypeParamClauseContext.class,0);
		}
		public FunSigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funSig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunSig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunSig(this);
		}
	}

	public final FunSigContext funSig() throws RecognitionException {
		FunSigContext _localctx = new FunSigContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_funSig);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1155);
			match(Id);
			setState(1157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1156);
				funTypeParamClause();
				}
			}

			setState(1159);
			paramClauses();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDclContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TypeDclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDcl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeDcl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeDcl(this);
		}
	}

	public final TypeDclContext typeDcl() throws RecognitionException {
		TypeDclContext _localctx = new TypeDclContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_typeDcl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1161);
			match(Id);
			setState(1163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1162);
				typeParamClause();
				}
			}

			setState(1167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(1165);
				match(T__44);
				setState(1166);
				type_();
				}
			}

			setState(1171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__45) {
				{
				setState(1169);
				match(T__45);
				setState(1170);
				type_();
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

	public static class PatVarDefContext extends ParserRuleContext {
		public PatDefContext patDef() {
			return getRuleContext(PatDefContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public PatVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPatVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPatVarDef(this);
		}
	}

	public final PatVarDefContext patVarDef() throws RecognitionException {
		PatVarDefContext _localctx = new PatVarDefContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_patVarDef);
		try {
			setState(1177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(1173);
				match(T__15);
				setState(1174);
				patDef();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(1175);
				match(T__47);
				setState(1176);
				varDef();
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

	public static class Def_Context extends ParserRuleContext {
		public PatVarDefContext patVarDef() {
			return getRuleContext(PatVarDefContext.class,0);
		}
		public FunDefContext funDef() {
			return getRuleContext(FunDefContext.class,0);
		}
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public TmplDefContext tmplDef() {
			return getRuleContext(TmplDefContext.class,0);
		}
		public Def_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterDef_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitDef_(this);
		}
	}

	public final Def_Context def_() throws RecognitionException {
		Def_Context _localctx = new Def_Context(_ctx, getState());
		enterRule(_localctx, 160, RULE_def_);
		int _la;
		try {
			setState(1191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
			case T__47:
				enterOuterAlt(_localctx, 1);
				{
				setState(1179);
				patVarDef();
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 2);
				{
				setState(1180);
				match(T__55);
				setState(1181);
				funDef();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(1182);
				match(T__14);
				setState(1186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(1183);
					match(NL);
					}
					}
					setState(1188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1189);
				typeDef();
				}
				break;
			case T__41:
			case T__56:
			case T__57:
			case T__58:
				enterOuterAlt(_localctx, 4);
				{
				setState(1190);
				tmplDef();
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

	public static class PatDefContext extends ParserRuleContext {
		public List<Pattern2Context> pattern2() {
			return getRuleContexts(Pattern2Context.class);
		}
		public Pattern2Context pattern2(int i) {
			return getRuleContext(Pattern2Context.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public PatDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPatDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPatDef(this);
		}
	}

	public final PatDefContext patDef() throws RecognitionException {
		PatDefContext _localctx = new PatDefContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_patDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1193);
			pattern2();
			setState(1198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(1194);
				match(T__3);
				setState(1195);
				pattern2();
				}
				}
				setState(1200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(1201);
				match(T__18);
				setState(1202);
				type_();
				}
			}

			setState(1205);
			match(T__33);
			setState(1206);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public PatDefContext patDef() {
			return getRuleContext(PatDefContext.class,0);
		}
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitVarDef(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_varDef);
		try {
			setState(1215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,167,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1208);
				patDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1209);
				ids();
				setState(1210);
				match(T__18);
				setState(1211);
				type_();
				setState(1212);
				match(T__33);
				setState(1213);
				match(T__19);
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

	public static class FunDefContext extends ParserRuleContext {
		public FunSigContext funSig() {
			return getRuleContext(FunSigContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public ParamClauseContext paramClause() {
			return getRuleContext(ParamClauseContext.class,0);
		}
		public ParamClausesContext paramClauses() {
			return getRuleContext(ParamClausesContext.class,0);
		}
		public ConstrExprContext constrExpr() {
			return getRuleContext(ConstrExprContext.class,0);
		}
		public ConstrBlockContext constrBlock() {
			return getRuleContext(ConstrBlockContext.class,0);
		}
		public FunDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterFunDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitFunDef(this);
		}
	}

	public final FunDefContext funDef() throws RecognitionException {
		FunDefContext _localctx = new FunDefContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_funDef);
		int _la;
		try {
			setState(1244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,172,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1217);
				funSig();
				setState(1220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(1218);
					match(T__18);
					setState(1219);
					type_();
					}
				}

				setState(1222);
				match(T__33);
				setState(1223);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1225);
				funSig();
				setState(1227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(1226);
					match(NL);
					}
				}

				setState(1229);
				match(T__12);
				setState(1230);
				block();
				setState(1231);
				match(T__13);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1233);
				match(T__4);
				setState(1234);
				paramClause();
				setState(1235);
				paramClauses();
				setState(1242);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__33:
					{
					setState(1236);
					match(T__33);
					setState(1237);
					constrExpr();
					}
					break;
				case T__12:
				case NL:
					{
					setState(1239);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1238);
						match(NL);
						}
					}

					setState(1241);
					constrBlock();
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

	public static class TypeDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTypeDef(this);
		}
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_typeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1246);
			match(Id);
			setState(1248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1247);
				typeParamClause();
				}
			}

			setState(1250);
			match(T__33);
			setState(1251);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TmplDefContext extends ParserRuleContext {
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public ObjectDefContext objectDef() {
			return getRuleContext(ObjectDefContext.class,0);
		}
		public TraitDefContext traitDef() {
			return getRuleContext(TraitDefContext.class,0);
		}
		public TmplDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tmplDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTmplDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTmplDef(this);
		}
	}

	public final TmplDefContext tmplDef() throws RecognitionException {
		TmplDefContext _localctx = new TmplDefContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_tmplDef);
		int _la;
		try {
			setState(1265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,176,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__41) {
					{
					setState(1253);
					match(T__41);
					}
				}

				setState(1256);
				match(T__56);
				setState(1257);
				classDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__41) {
					{
					setState(1258);
					match(T__41);
					}
				}

				setState(1261);
				match(T__57);
				setState(1262);
				objectDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1263);
				match(T__58);
				setState(1264);
				traitDef();
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

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ClassParamClausesContext classParamClauses() {
			return getRuleContext(ClassParamClausesContext.class,0);
		}
		public ClassTemplateOptContext classTemplateOpt() {
			return getRuleContext(ClassTemplateOptContext.class,0);
		}
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public List<ConstrAnnotationContext> constrAnnotation() {
			return getRuleContexts(ConstrAnnotationContext.class);
		}
		public ConstrAnnotationContext constrAnnotation(int i) {
			return getRuleContext(ConstrAnnotationContext.class,i);
		}
		public AccessModifierContext accessModifier() {
			return getRuleContext(AccessModifierContext.class,0);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassDef(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_classDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1267);
			match(Id);
			setState(1269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1268);
				typeParamClause();
				}
			}

			setState(1274);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,178,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1271);
					constrAnnotation();
					}
					} 
				}
				setState(1276);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,178,_ctx);
			}
			setState(1278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,179,_ctx) ) {
			case 1:
				{
				setState(1277);
				accessModifier();
				}
				break;
			}
			setState(1280);
			classParamClauses();
			setState(1281);
			classTemplateOpt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TraitDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public TraitTemplateOptContext traitTemplateOpt() {
			return getRuleContext(TraitTemplateOptContext.class,0);
		}
		public TypeParamClauseContext typeParamClause() {
			return getRuleContext(TypeParamClauseContext.class,0);
		}
		public TraitDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitDef(this);
		}
	}

	public final TraitDefContext traitDef() throws RecognitionException {
		TraitDefContext _localctx = new TraitDefContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_traitDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1283);
			match(Id);
			setState(1285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(1284);
				typeParamClause();
				}
			}

			setState(1287);
			traitTemplateOpt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectDefContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(ScalaParser.Id, 0); }
		public ClassTemplateOptContext classTemplateOpt() {
			return getRuleContext(ClassTemplateOptContext.class,0);
		}
		public ObjectDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterObjectDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitObjectDef(this);
		}
	}

	public final ObjectDefContext objectDef() throws RecognitionException {
		ObjectDefContext _localctx = new ObjectDefContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_objectDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1289);
			match(Id);
			setState(1290);
			classTemplateOpt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTemplateOptContext extends ParserRuleContext {
		public ClassTemplateContext classTemplate() {
			return getRuleContext(ClassTemplateContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public ClassTemplateOptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTemplateOpt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassTemplateOpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassTemplateOpt(this);
		}
	}

	public final ClassTemplateOptContext classTemplateOpt() throws RecognitionException {
		ClassTemplateOptContext _localctx = new ClassTemplateOptContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_classTemplateOpt);
		int _la;
		try {
			setState(1300);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1292);
				match(T__59);
				setState(1293);
				classTemplate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1298);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,182,_ctx) ) {
				case 1:
					{
					setState(1295);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__59) {
						{
						setState(1294);
						match(T__59);
						}
					}

					setState(1297);
					templateBody();
					}
					break;
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

	public static class TraitTemplateOptContext extends ParserRuleContext {
		public TraitTemplateContext traitTemplate() {
			return getRuleContext(TraitTemplateContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TraitTemplateOptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitTemplateOpt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitTemplateOpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitTemplateOpt(this);
		}
	}

	public final TraitTemplateOptContext traitTemplateOpt() throws RecognitionException {
		TraitTemplateOptContext _localctx = new TraitTemplateOptContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_traitTemplateOpt);
		int _la;
		try {
			setState(1310);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,186,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1302);
				match(T__59);
				setState(1303);
				traitTemplate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1308);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,185,_ctx) ) {
				case 1:
					{
					setState(1305);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__59) {
						{
						setState(1304);
						match(T__59);
						}
					}

					setState(1307);
					templateBody();
					}
					break;
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

	public static class ClassTemplateContext extends ParserRuleContext {
		public ClassParentsContext classParents() {
			return getRuleContext(ClassParentsContext.class,0);
		}
		public EarlyDefsContext earlyDefs() {
			return getRuleContext(EarlyDefsContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public ClassTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassTemplate(this);
		}
	}

	public final ClassTemplateContext classTemplate() throws RecognitionException {
		ClassTemplateContext _localctx = new ClassTemplateContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_classTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(1312);
				earlyDefs();
				}
			}

			setState(1315);
			classParents();
			setState(1317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,188,_ctx) ) {
			case 1:
				{
				setState(1316);
				templateBody();
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

	public static class TraitTemplateContext extends ParserRuleContext {
		public TraitParentsContext traitParents() {
			return getRuleContext(TraitParentsContext.class,0);
		}
		public EarlyDefsContext earlyDefs() {
			return getRuleContext(EarlyDefsContext.class,0);
		}
		public TemplateBodyContext templateBody() {
			return getRuleContext(TemplateBodyContext.class,0);
		}
		public TraitTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitTemplate(this);
		}
	}

	public final TraitTemplateContext traitTemplate() throws RecognitionException {
		TraitTemplateContext _localctx = new TraitTemplateContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_traitTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(1319);
				earlyDefs();
				}
			}

			setState(1322);
			traitParents();
			setState(1324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,190,_ctx) ) {
			case 1:
				{
				setState(1323);
				templateBody();
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

	public static class ClassParentsContext extends ParserRuleContext {
		public ConstrContext constr() {
			return getRuleContext(ConstrContext.class,0);
		}
		public List<AnnotTypeContext> annotType() {
			return getRuleContexts(AnnotTypeContext.class);
		}
		public AnnotTypeContext annotType(int i) {
			return getRuleContext(AnnotTypeContext.class,i);
		}
		public ClassParentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classParents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterClassParents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitClassParents(this);
		}
	}

	public final ClassParentsContext classParents() throws RecognitionException {
		ClassParentsContext _localctx = new ClassParentsContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_classParents);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1326);
			constr();
			setState(1331);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,191,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1327);
					match(T__16);
					setState(1328);
					annotType();
					}
					} 
				}
				setState(1333);
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

	public static class TraitParentsContext extends ParserRuleContext {
		public List<AnnotTypeContext> annotType() {
			return getRuleContexts(AnnotTypeContext.class);
		}
		public AnnotTypeContext annotType(int i) {
			return getRuleContext(AnnotTypeContext.class,i);
		}
		public TraitParentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitParents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTraitParents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTraitParents(this);
		}
	}

	public final TraitParentsContext traitParents() throws RecognitionException {
		TraitParentsContext _localctx = new TraitParentsContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_traitParents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1334);
			annotType();
			setState(1339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(1335);
				match(T__16);
				setState(1336);
				annotType();
				}
				}
				setState(1341);
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

	public static class ConstrContext extends ParserRuleContext {
		public AnnotTypeContext annotType() {
			return getRuleContext(AnnotTypeContext.class,0);
		}
		public List<ArgumentExprsContext> argumentExprs() {
			return getRuleContexts(ArgumentExprsContext.class);
		}
		public ArgumentExprsContext argumentExprs(int i) {
			return getRuleContext(ArgumentExprsContext.class,i);
		}
		public ConstrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstr(this);
		}
	}

	public final ConstrContext constr() throws RecognitionException {
		ConstrContext _localctx = new ConstrContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_constr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1342);
			annotType();
			setState(1346);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,193,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1343);
					argumentExprs();
					}
					} 
				}
				setState(1348);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,193,_ctx);
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

	public static class EarlyDefsContext extends ParserRuleContext {
		public List<EarlyDefContext> earlyDef() {
			return getRuleContexts(EarlyDefContext.class);
		}
		public EarlyDefContext earlyDef(int i) {
			return getRuleContext(EarlyDefContext.class,i);
		}
		public EarlyDefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_earlyDefs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterEarlyDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitEarlyDefs(this);
		}
	}

	public final EarlyDefsContext earlyDefs() throws RecognitionException {
		EarlyDefsContext _localctx = new EarlyDefsContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_earlyDefs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1349);
			match(T__12);
			setState(1351); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1350);
				earlyDef();
				}
				}
				setState(1353); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__21) | (1L << T__39) | (1L << T__43) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0) );
			setState(1355);
			match(T__13);
			setState(1356);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EarlyDefContext extends ParserRuleContext {
		public PatVarDefContext patVarDef() {
			return getRuleContext(PatVarDefContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public EarlyDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_earlyDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterEarlyDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitEarlyDef(this);
		}
	}

	public final EarlyDefContext earlyDef() throws RecognitionException {
		EarlyDefContext _localctx = new EarlyDefContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_earlyDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(1358);
				annotation();
				setState(1360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NL) {
					{
					setState(1359);
					match(NL);
					}
				}

				}
				}
				setState(1366);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
				{
				{
				setState(1367);
				modifier();
				}
				}
				setState(1372);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1373);
			patVarDef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstrExprContext extends ParserRuleContext {
		public SelfInvocationContext selfInvocation() {
			return getRuleContext(SelfInvocationContext.class,0);
		}
		public ConstrBlockContext constrBlock() {
			return getRuleContext(ConstrBlockContext.class,0);
		}
		public ConstrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstrExpr(this);
		}
	}

	public final ConstrExprContext constrExpr() throws RecognitionException {
		ConstrExprContext _localctx = new ConstrExprContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_constrExpr);
		try {
			setState(1377);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(1375);
				selfInvocation();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(1376);
				constrBlock();
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

	public static class ConstrBlockContext extends ParserRuleContext {
		public SelfInvocationContext selfInvocation() {
			return getRuleContext(SelfInvocationContext.class,0);
		}
		public List<BlockStatContext> blockStat() {
			return getRuleContexts(BlockStatContext.class);
		}
		public BlockStatContext blockStat(int i) {
			return getRuleContext(BlockStatContext.class,i);
		}
		public ConstrBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterConstrBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitConstrBlock(this);
		}
	}

	public final ConstrBlockContext constrBlock() throws RecognitionException {
		ConstrBlockContext _localctx = new ConstrBlockContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_constrBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1379);
			match(T__12);
			setState(1380);
			selfInvocation();
			setState(1384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__5) | (1L << T__9) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__28) | (1L << T__29) | (1L << T__31) | (1L << T__32) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__41) | (1L << T__43) | (1L << T__47) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << Id) | (1L << BooleanLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CharacterLiteral - 64)) | (1L << (SymbolLiteral - 64)) | (1L << (IntegerLiteral - 64)) | (1L << (StringLiteral - 64)) | (1L << (FloatingPointLiteral - 64)))) != 0)) {
				{
				{
				setState(1381);
				blockStat();
				}
				}
				setState(1386);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1387);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelfInvocationContext extends ParserRuleContext {
		public List<ArgumentExprsContext> argumentExprs() {
			return getRuleContexts(ArgumentExprsContext.class);
		}
		public ArgumentExprsContext argumentExprs(int i) {
			return getRuleContext(ArgumentExprsContext.class,i);
		}
		public SelfInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selfInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterSelfInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitSelfInvocation(this);
		}
	}

	public final SelfInvocationContext selfInvocation() throws RecognitionException {
		SelfInvocationContext _localctx = new SelfInvocationContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_selfInvocation);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1389);
			match(T__4);
			setState(1391); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1390);
					argumentExprs();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1393); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopStatSeqContext extends ParserRuleContext {
		public List<TopStatContext> topStat() {
			return getRuleContexts(TopStatContext.class);
		}
		public TopStatContext topStat(int i) {
			return getRuleContext(TopStatContext.class,i);
		}
		public TopStatSeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topStatSeq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTopStatSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTopStatSeq(this);
		}
	}

	public final TopStatSeqContext topStatSeq() throws RecognitionException {
		TopStatSeqContext _localctx = new TopStatSeqContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_topStatSeq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1396); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1395);
				topStat();
				}
				}
				setState(1398); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__41) | (1L << T__43) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__60))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopStatContext extends ParserRuleContext {
		public TmplDefContext tmplDef() {
			return getRuleContext(TmplDefContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(ScalaParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(ScalaParser.NL, i);
		}
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public PackagingContext packaging() {
			return getRuleContext(PackagingContext.class,0);
		}
		public PackageObjectContext packageObject() {
			return getRuleContext(PackageObjectContext.class,0);
		}
		public TopStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterTopStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitTopStat(this);
		}
	}

	public final TopStatContext topStat() throws RecognitionException {
		TopStatContext _localctx = new TopStatContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_topStat);
		int _la;
		try {
			setState(1419);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,205,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1406);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(1400);
					annotation();
					setState(1402);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(1401);
						match(NL);
						}
					}

					}
					}
					setState(1408);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1412);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__39) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53))) != 0)) {
					{
					{
					setState(1409);
					modifier();
					}
					}
					setState(1414);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1415);
				tmplDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1416);
				import_();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1417);
				packaging();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1418);
				packageObject();
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

	public static class PackagingContext extends ParserRuleContext {
		public QualIdContext qualId() {
			return getRuleContext(QualIdContext.class,0);
		}
		public TopStatSeqContext topStatSeq() {
			return getRuleContext(TopStatSeqContext.class,0);
		}
		public TerminalNode NL() { return getToken(ScalaParser.NL, 0); }
		public PackagingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packaging; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPackaging(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPackaging(this);
		}
	}

	public final PackagingContext packaging() throws RecognitionException {
		PackagingContext _localctx = new PackagingContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_packaging);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1421);
			match(T__60);
			setState(1422);
			qualId();
			setState(1424);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NL) {
				{
				setState(1423);
				match(NL);
				}
			}

			setState(1426);
			match(T__12);
			setState(1427);
			topStatSeq();
			setState(1428);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageObjectContext extends ParserRuleContext {
		public ObjectDefContext objectDef() {
			return getRuleContext(ObjectDefContext.class,0);
		}
		public PackageObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterPackageObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitPackageObject(this);
		}
	}

	public final PackageObjectContext packageObject() throws RecognitionException {
		PackageObjectContext _localctx = new PackageObjectContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_packageObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1430);
			match(T__60);
			setState(1431);
			match(T__57);
			setState(1432);
			objectDef();
			}
		}
		catch (RecognitionException re) {
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
		public TopStatSeqContext topStatSeq() {
			return getRuleContext(TopStatSeqContext.class,0);
		}
		public List<QualIdContext> qualId() {
			return getRuleContexts(QualIdContext.class);
		}
		public QualIdContext qualId(int i) {
			return getRuleContext(QualIdContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScalaListener ) ((ScalaListener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_compilationUnit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1438);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,207,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1434);
					match(T__60);
					setState(1435);
					qualId();
					}
					} 
				}
				setState(1440);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,207,_ctx);
			}
			setState(1441);
			topStatSeq();
			}
		}
		catch (RecognitionException re) {
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
		case 3:
			return stableId_sempred((StableIdContext)_localctx, predIndex);
		case 12:
			return simpleType_sempred((SimpleTypeContext)_localctx, predIndex);
		case 23:
			return infixExpr_sempred((InfixExprContext)_localctx, predIndex);
		case 26:
			return simpleExpr1_sempred((SimpleExpr1Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean stableId_sempred(StableIdContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean simpleType_sempred(SimpleTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean infixExpr_sempred(InfixExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simpleExpr1_sempred(SimpleExpr1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 2);
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001N\u05a4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007J\u0002"+
		"K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007O\u0002"+
		"P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007T\u0002"+
		"U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007Y\u0002"+
		"Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007^\u0002"+
		"_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007c\u0002"+
		"d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007h\u0002"+
		"i\u0007i\u0001\u0000\u0003\u0000\u00d6\b\u0000\u0001\u0000\u0001\u0000"+
		"\u0003\u0000\u00da\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0003\u0000\u00e2\b\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001\u00e7\b\u0001\n\u0001\f\u0001\u00ea\t\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u00ef\b\u0002\n\u0002\f\u0002"+
		"\u00f2\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"\u00f8\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00fd\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0101\b\u0003\u0003\u0003\u0103"+
		"\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u0108\b\u0003"+
		"\n\u0003\f\u0003\u010b\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u0117\b\u0005\u0003\u0005\u0119\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u0120\b\u0006"+
		"\n\u0006\f\u0006\u0123\t\u0006\u0003\u0006\u0125\b\u0006\u0001\u0006\u0003"+
		"\u0006\u0128\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007\u012d"+
		"\b\u0007\u000b\u0007\f\u0007\u012e\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u0137\b\b\u0001\t\u0001\t\u0001\t\u0005\t\u013c"+
		"\b\t\n\t\f\t\u013f\t\t\u0001\n\u0001\n\u0001\n\u0005\n\u0144\b\n\n\n\f"+
		"\n\u0147\t\n\u0001\n\u0003\n\u014a\b\n\u0001\n\u0003\n\u014d\b\n\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u0151\b\u000b\n\u000b\f\u000b\u0154\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u015a\b\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u0160\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u0167\b\f\n\f\f\f\u016a\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000e\u0173\b\u000e\n\u000e\f\u000e\u0176"+
		"\t\u000e\u0001\u000f\u0003\u000f\u0179\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0004\u000f\u017d\b\u000f\u000b\u000f\f\u000f\u017e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0186\b\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0004"+
		"\u0012\u018e\b\u0012\u000b\u0012\f\u0012\u018f\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0003\u0012\u0195\b\u0012\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u0199\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u019d\b\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u01a2\b\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u01a9\b\u0014\n"+
		"\u0014\f\u0014\u01ac\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u01b1\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u01b8\b\u0014\n\u0014\f\u0014\u01bb\t\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u01c3\b\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01c7\b\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01d9\b\u0014\u0001"+
		"\u0014\u0003\u0014\u01dc\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01e4\b\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u01e9\b\u0014\u0003\u0014\u01eb\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u01ef\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u01fb\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0203\b\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u0209\b\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u020e\b\u0016\n\u0016"+
		"\f\u0016\u0211\t\u0016\u0001\u0016\u0003\u0016\u0214\b\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u021c\b\u0017\u0001\u0017\u0005\u0017\u021f\b\u0017\n\u0017\f\u0017\u0222"+
		"\t\u0017\u0001\u0018\u0003\u0018\u0225\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u022a\b\u0018\u0003\u0018\u022c\b\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0231\b\u0019\u0001\u0019\u0003"+
		"\u0019\u0234\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0003\u001a\u023c\b\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003"+
		"\u001a\u0246\b\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u024a\b\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0250\b\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u0255\b\u001a\n\u001a"+
		"\f\u001a\u0258\t\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b"+
		"\u025d\b\u001b\n\u001b\f\u001b\u0260\t\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0003\u001c\u026b\b\u001c\u0001\u001c\u0003\u001c\u026e\b\u001c"+
		"\u0001\u001d\u0003\u001d\u0271\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0003\u001d\u0276\b\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u027a\b"+
		"\u001d\u0003\u001d\u027c\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0286"+
		"\b\u001e\u0001\u001f\u0004\u001f\u0289\b\u001f\u000b\u001f\f\u001f\u028a"+
		"\u0001\u001f\u0003\u001f\u028e\b\u001f\u0001 \u0001 \u0005 \u0292\b \n"+
		" \f \u0295\t \u0001 \u0003 \u0298\b \u0001 \u0001 \u0005 \u029c\b \n "+
		"\f \u029f\t \u0001 \u0005 \u02a2\b \n \f \u02a5\t \u0001 \u0001 \u0003"+
		" \u02a9\b \u0001!\u0001!\u0001!\u0003!\u02ae\b!\u0001!\u0001!\u0003!\u02b2"+
		"\b!\u0001!\u0001!\u0003!\u02b6\b!\u0001!\u0001!\u0001!\u0003!\u02bb\b"+
		"!\u0001\"\u0004\"\u02be\b\"\u000b\"\f\"\u02bf\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0005#\u02ca\b#\n#\f#\u02cd\t#\u0001$\u0004"+
		"$\u02d0\b$\u000b$\f$\u02d1\u0001%\u0001%\u0001%\u0003%\u02d7\b%\u0001"+
		"%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0005\'\u02e2"+
		"\b\'\n\'\f\'\u02e5\t\'\u0001(\u0001(\u0001(\u0001(\u0003(\u02eb\b(\u0001"+
		")\u0001)\u0001)\u0003)\u02f0\b)\u0001)\u0003)\u02f3\b)\u0001*\u0001*\u0001"+
		"*\u0001*\u0003*\u02f9\b*\u0001*\u0005*\u02fc\b*\n*\f*\u02ff\t*\u0003*"+
		"\u0301\b*\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u0309\b+\u0001"+
		"+\u0003+\u030c\b+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u0313\b+\u0001"+
		"+\u0001+\u0003+\u0317\b+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003"+
		"+\u031f\b+\u0001+\u0003+\u0322\b+\u0001,\u0001,\u0001,\u0003,\u0327\b"+
		",\u0001,\u0001,\u0003,\u032b\b,\u0001-\u0001-\u0001-\u0001-\u0005-\u0331"+
		"\b-\n-\f-\u0334\t-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0005.\u033c"+
		"\b.\n.\f.\u033f\t.\u0001.\u0001.\u0001/\u0005/\u0344\b/\n/\f/\u0347\t"+
		"/\u0001/\u0003/\u034a\b/\u0001/\u0001/\u00010\u00010\u00030\u0350\b0\u0001"+
		"0\u00010\u00030\u0354\b0\u00010\u00010\u00030\u0358\b0\u00010\u00010\u0005"+
		"0\u035c\b0\n0\f0\u035f\t0\u00010\u00010\u00050\u0363\b0\n0\f0\u0366\t"+
		"0\u00011\u00051\u0369\b1\n1\f1\u036c\t1\u00011\u00031\u036f\b1\u00011"+
		"\u00011\u00011\u00011\u00011\u00031\u0376\b1\u00012\u00032\u0379\b2\u0001"+
		"2\u00012\u00032\u037d\b2\u00012\u00012\u00013\u00013\u00013\u00053\u0384"+
		"\b3\n3\f3\u0387\t3\u00014\u00054\u038a\b4\n4\f4\u038d\t4\u00014\u0001"+
		"4\u00014\u00034\u0392\b4\u00014\u00014\u00034\u0396\b4\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00035\u039e\b5\u00016\u00056\u03a1\b6\n6\f6\u03a4"+
		"\t6\u00016\u00036\u03a7\b6\u00016\u00016\u00016\u00016\u00016\u00036\u03ae"+
		"\b6\u00017\u00037\u03b1\b7\u00017\u00017\u00037\u03b5\b7\u00017\u0001"+
		"7\u00018\u00018\u00018\u00058\u03bc\b8\n8\f8\u03bf\t8\u00019\u00059\u03c2"+
		"\b9\n9\f9\u03c5\t9\u00019\u00059\u03c8\b9\n9\f9\u03cb\t9\u00019\u0003"+
		"9\u03ce\b9\u00019\u00019\u00019\u00019\u00019\u00039\u03d5\b9\u0001:\u0001"+
		":\u0001:\u0001:\u0005:\u03db\b:\n:\f:\u03de\t:\u0001:\u0001:\u0001;\u0001"+
		";\u0001;\u0003;\u03e5\b;\u0001<\u0001<\u0001<\u0003<\u03ea\b<\u0001=\u0001"+
		"=\u0001>\u0001>\u0003>\u03f0\b>\u0001?\u0001?\u0001?\u0001?\u0001@\u0001"+
		"@\u0001@\u0005@\u03f9\b@\n@\f@\u03fc\t@\u0001A\u0001A\u0001A\u0001A\u0001"+
		"B\u0003B\u0403\bB\u0001B\u0001B\u0003B\u0407\bB\u0001B\u0004B\u040a\b"+
		"B\u000bB\fB\u040b\u0001B\u0001B\u0001C\u0001C\u0001C\u0003C\u0413\bC\u0005"+
		"C\u0415\bC\nC\fC\u0418\tC\u0001C\u0005C\u041b\bC\nC\fC\u041e\tC\u0001"+
		"C\u0001C\u0001C\u0003C\u0423\bC\u0005C\u0425\bC\nC\fC\u0428\tC\u0001C"+
		"\u0005C\u042b\bC\nC\fC\u042e\tC\u0001C\u0001C\u0003C\u0432\bC\u0001D\u0001"+
		"D\u0001D\u0003D\u0437\bD\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0003"+
		"D\u043f\bD\u0001E\u0001E\u0001E\u0001E\u0005E\u0445\bE\nE\fE\u0448\tE"+
		"\u0001F\u0001F\u0001F\u0001F\u0001F\u0003F\u044f\bF\u0003F\u0451\bF\u0001"+
		"G\u0001G\u0001G\u0001G\u0005G\u0457\bG\nG\fG\u045a\tG\u0001G\u0001G\u0003"+
		"G\u045e\bG\u0001G\u0001G\u0001H\u0001H\u0001H\u0003H\u0465\bH\u0001I\u0001"+
		"I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0005I\u046f\bI\nI\fI\u0472"+
		"\tI\u0001I\u0003I\u0475\bI\u0001J\u0001J\u0001J\u0001J\u0001K\u0001K\u0001"+
		"K\u0001K\u0001L\u0001L\u0001L\u0003L\u0482\bL\u0001M\u0001M\u0003M\u0486"+
		"\bM\u0001M\u0001M\u0001N\u0001N\u0003N\u048c\bN\u0001N\u0001N\u0003N\u0490"+
		"\bN\u0001N\u0001N\u0003N\u0494\bN\u0001O\u0001O\u0001O\u0001O\u0003O\u049a"+
		"\bO\u0001P\u0001P\u0001P\u0001P\u0001P\u0005P\u04a1\bP\nP\fP\u04a4\tP"+
		"\u0001P\u0001P\u0003P\u04a8\bP\u0001Q\u0001Q\u0001Q\u0005Q\u04ad\bQ\n"+
		"Q\fQ\u04b0\tQ\u0001Q\u0001Q\u0003Q\u04b4\bQ\u0001Q\u0001Q\u0001Q\u0001"+
		"R\u0001R\u0001R\u0001R\u0001R\u0001R\u0001R\u0003R\u04c0\bR\u0001S\u0001"+
		"S\u0001S\u0003S\u04c5\bS\u0001S\u0001S\u0001S\u0001S\u0001S\u0003S\u04cc"+
		"\bS\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001S\u0001"+
		"S\u0003S\u04d8\bS\u0001S\u0003S\u04db\bS\u0003S\u04dd\bS\u0001T\u0001"+
		"T\u0003T\u04e1\bT\u0001T\u0001T\u0001T\u0001U\u0003U\u04e7\bU\u0001U\u0001"+
		"U\u0001U\u0003U\u04ec\bU\u0001U\u0001U\u0001U\u0001U\u0003U\u04f2\bU\u0001"+
		"V\u0001V\u0003V\u04f6\bV\u0001V\u0005V\u04f9\bV\nV\fV\u04fc\tV\u0001V"+
		"\u0003V\u04ff\bV\u0001V\u0001V\u0001V\u0001W\u0001W\u0003W\u0506\bW\u0001"+
		"W\u0001W\u0001X\u0001X\u0001X\u0001Y\u0001Y\u0001Y\u0003Y\u0510\bY\u0001"+
		"Y\u0003Y\u0513\bY\u0003Y\u0515\bY\u0001Z\u0001Z\u0001Z\u0003Z\u051a\b"+
		"Z\u0001Z\u0003Z\u051d\bZ\u0003Z\u051f\bZ\u0001[\u0003[\u0522\b[\u0001"+
		"[\u0001[\u0003[\u0526\b[\u0001\\\u0003\\\u0529\b\\\u0001\\\u0001\\\u0003"+
		"\\\u052d\b\\\u0001]\u0001]\u0001]\u0005]\u0532\b]\n]\f]\u0535\t]\u0001"+
		"^\u0001^\u0001^\u0005^\u053a\b^\n^\f^\u053d\t^\u0001_\u0001_\u0005_\u0541"+
		"\b_\n_\f_\u0544\t_\u0001`\u0001`\u0004`\u0548\b`\u000b`\f`\u0549\u0001"+
		"`\u0001`\u0001`\u0001a\u0001a\u0003a\u0551\ba\u0005a\u0553\ba\na\fa\u0556"+
		"\ta\u0001a\u0005a\u0559\ba\na\fa\u055c\ta\u0001a\u0001a\u0001b\u0001b"+
		"\u0003b\u0562\bb\u0001c\u0001c\u0001c\u0005c\u0567\bc\nc\fc\u056a\tc\u0001"+
		"c\u0001c\u0001d\u0001d\u0004d\u0570\bd\u000bd\fd\u0571\u0001e\u0004e\u0575"+
		"\be\u000be\fe\u0576\u0001f\u0001f\u0003f\u057b\bf\u0005f\u057d\bf\nf\f"+
		"f\u0580\tf\u0001f\u0005f\u0583\bf\nf\ff\u0586\tf\u0001f\u0001f\u0001f"+
		"\u0001f\u0003f\u058c\bf\u0001g\u0001g\u0001g\u0003g\u0591\bg\u0001g\u0001"+
		"g\u0001g\u0001g\u0001h\u0001h\u0001h\u0001h\u0001i\u0001i\u0005i\u059d"+
		"\bi\ni\fi\u05a0\ti\u0001i\u0001i\u0001i\u0000\u0004\u0006\u0018.4j\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084"+
		"\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c"+
		"\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4"+
		"\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc"+
		"\u00ce\u00d0\u00d2\u0000\n\u0002\u0000\u0001\u0001$&\u0001\u0000\u0013"+
		"\u0015\u0002\u0000\u0016\u0016((\u0003\u0000\u0014\u0014>>FF\u0002\u0000"+
		"\u0001\u0001$$\u0002\u0000\u0014\u0014>>\u0002\u0000\u0010\u001000\u0003"+
		"\u0000\u0016\u0016((24\u0001\u000056\u0002\u0000\u0005\u0005>>\u0632\u0000"+
		"\u00e1\u0001\u0000\u0000\u0000\u0002\u00e3\u0001\u0000\u0000\u0000\u0004"+
		"\u00eb\u0001\u0000\u0000\u0000\u0006\u0102\u0001\u0000\u0000\u0000\b\u010c"+
		"\u0001\u0000\u0000\u0000\n\u0118\u0001\u0000\u0000\u0000\f\u0127\u0001"+
		"\u0000\u0000\u0000\u000e\u0129\u0001\u0000\u0000\u0000\u0010\u0136\u0001"+
		"\u0000\u0000\u0000\u0012\u0138\u0001\u0000\u0000\u0000\u0014\u014c\u0001"+
		"\u0000\u0000\u0000\u0016\u014e\u0001\u0000\u0000\u0000\u0018\u015f\u0001"+
		"\u0000\u0000\u0000\u001a\u016b\u0001\u0000\u0000\u0000\u001c\u016f\u0001"+
		"\u0000\u0000\u0000\u001e\u0178\u0001\u0000\u0000\u0000 \u0185\u0001\u0000"+
		"\u0000\u0000\"\u0187\u0001\u0000\u0000\u0000$\u0194\u0001\u0000\u0000"+
		"\u0000&\u01a1\u0001\u0000\u0000\u0000(\u0202\u0001\u0000\u0000\u0000*"+
		"\u0204\u0001\u0000\u0000\u0000,\u0206\u0001\u0000\u0000\u0000.\u0215\u0001"+
		"\u0000\u0000\u00000\u0224\u0001\u0000\u0000\u00002\u0233\u0001\u0000\u0000"+
		"\u00004\u0245\u0001\u0000\u0000\u00006\u0259\u0001\u0000\u0000\u00008"+
		"\u026d\u0001\u0000\u0000\u0000:\u027b\u0001\u0000\u0000\u0000<\u0285\u0001"+
		"\u0000\u0000\u0000>\u0288\u0001\u0000\u0000\u0000@\u02a8\u0001\u0000\u0000"+
		"\u0000B\u02ba\u0001\u0000\u0000\u0000D\u02bd\u0001\u0000\u0000\u0000F"+
		"\u02c1\u0001\u0000\u0000\u0000H\u02cf\u0001\u0000\u0000\u0000J\u02d3\u0001"+
		"\u0000\u0000\u0000L\u02db\u0001\u0000\u0000\u0000N\u02de\u0001\u0000\u0000"+
		"\u0000P\u02ea\u0001\u0000\u0000\u0000R\u02f2\u0001\u0000\u0000\u0000T"+
		"\u0300\u0001\u0000\u0000\u0000V\u0321\u0001\u0000\u0000\u0000X\u032a\u0001"+
		"\u0000\u0000\u0000Z\u032c\u0001\u0000\u0000\u0000\\\u0337\u0001\u0000"+
		"\u0000\u0000^\u0345\u0001\u0000\u0000\u0000`\u034d\u0001\u0000\u0000\u0000"+
		"b\u036a\u0001\u0000\u0000\u0000d\u0378\u0001\u0000\u0000\u0000f\u0380"+
		"\u0001\u0000\u0000\u0000h\u038b\u0001\u0000\u0000\u0000j\u039d\u0001\u0000"+
		"\u0000\u0000l\u03a2\u0001\u0000\u0000\u0000n\u03b0\u0001\u0000\u0000\u0000"+
		"p\u03b8\u0001\u0000\u0000\u0000r\u03c3\u0001\u0000\u0000\u0000t\u03d6"+
		"\u0001\u0000\u0000\u0000v\u03e1\u0001\u0000\u0000\u0000x\u03e9\u0001\u0000"+
		"\u0000\u0000z\u03eb\u0001\u0000\u0000\u0000|\u03ed\u0001\u0000\u0000\u0000"+
		"~\u03f1\u0001\u0000\u0000\u0000\u0080\u03f5\u0001\u0000\u0000\u0000\u0082"+
		"\u03fd\u0001\u0000\u0000\u0000\u0084\u0402\u0001\u0000\u0000\u0000\u0086"+
		"\u0431\u0001\u0000\u0000\u0000\u0088\u043e\u0001\u0000\u0000\u0000\u008a"+
		"\u0440\u0001\u0000\u0000\u0000\u008c\u0449\u0001\u0000\u0000\u0000\u008e"+
		"\u0452\u0001\u0000\u0000\u0000\u0090\u0461\u0001\u0000\u0000\u0000\u0092"+
		"\u0474\u0001\u0000\u0000\u0000\u0094\u0476\u0001\u0000\u0000\u0000\u0096"+
		"\u047a\u0001\u0000\u0000\u0000\u0098\u047e\u0001\u0000\u0000\u0000\u009a"+
		"\u0483\u0001\u0000\u0000\u0000\u009c\u0489\u0001\u0000\u0000\u0000\u009e"+
		"\u0499\u0001\u0000\u0000\u0000\u00a0\u04a7\u0001\u0000\u0000\u0000\u00a2"+
		"\u04a9\u0001\u0000\u0000\u0000\u00a4\u04bf\u0001\u0000\u0000\u0000\u00a6"+
		"\u04dc\u0001\u0000\u0000\u0000\u00a8\u04de\u0001\u0000\u0000\u0000\u00aa"+
		"\u04f1\u0001\u0000\u0000\u0000\u00ac\u04f3\u0001\u0000\u0000\u0000\u00ae"+
		"\u0503\u0001\u0000\u0000\u0000\u00b0\u0509\u0001\u0000\u0000\u0000\u00b2"+
		"\u0514\u0001\u0000\u0000\u0000\u00b4\u051e\u0001\u0000\u0000\u0000\u00b6"+
		"\u0521\u0001\u0000\u0000\u0000\u00b8\u0528\u0001\u0000\u0000\u0000\u00ba"+
		"\u052e\u0001\u0000\u0000\u0000\u00bc\u0536\u0001\u0000\u0000\u0000\u00be"+
		"\u053e\u0001\u0000\u0000\u0000\u00c0\u0545\u0001\u0000\u0000\u0000\u00c2"+
		"\u0554\u0001\u0000\u0000\u0000\u00c4\u0561\u0001\u0000\u0000\u0000\u00c6"+
		"\u0563\u0001\u0000\u0000\u0000\u00c8\u056d\u0001\u0000\u0000\u0000\u00ca"+
		"\u0574\u0001\u0000\u0000\u0000\u00cc\u058b\u0001\u0000\u0000\u0000\u00ce"+
		"\u058d\u0001\u0000\u0000\u0000\u00d0\u0596\u0001\u0000\u0000\u0000\u00d2"+
		"\u059e\u0001\u0000\u0000\u0000\u00d4\u00d6\u0005\u0001\u0000\u0000\u00d5"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d7\u00e2\u0005B\u0000\u0000\u00d8\u00da"+
		"\u0005\u0001\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00d9\u00da"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00e2"+
		"\u0005D\u0000\u0000\u00dc\u00e2\u0005?\u0000\u0000\u00dd\u00e2\u0005@"+
		"\u0000\u0000\u00de\u00e2\u0005C\u0000\u0000\u00df\u00e2\u0005A\u0000\u0000"+
		"\u00e0\u00e2\u0005\u0002\u0000\u0000\u00e1\u00d5\u0001\u0000\u0000\u0000"+
		"\u00e1\u00d9\u0001\u0000\u0000\u0000\u00e1\u00dc\u0001\u0000\u0000\u0000"+
		"\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e1\u00de\u0001\u0000\u0000\u0000"+
		"\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e2\u0001\u0001\u0000\u0000\u0000\u00e3\u00e8\u0005>\u0000\u0000\u00e4"+
		"\u00e5\u0005\u0003\u0000\u0000\u00e5\u00e7\u0005>\u0000\u0000\u00e6\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u0003"+
		"\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00eb\u00f0"+
		"\u0005>\u0000\u0000\u00ec\u00ed\u0005\u0004\u0000\u0000\u00ed\u00ef\u0005"+
		">\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f1\u0005\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f4\u0006\u0003\uffff\uffff\u0000\u00f4\u0103\u0005"+
		">\u0000\u0000\u00f5\u00f6\u0005>\u0000\u0000\u00f6\u00f8\u0005\u0003\u0000"+
		"\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000"+
		"\u0000\u00f8\u0100\u0001\u0000\u0000\u0000\u00f9\u0101\u0005\u0005\u0000"+
		"\u0000\u00fa\u00fc\u0005\u0006\u0000\u0000\u00fb\u00fd\u0003\b\u0004\u0000"+
		"\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000"+
		"\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\u0003\u0000\u0000"+
		"\u00ff\u0101\u0005>\u0000\u0000\u0100\u00f9\u0001\u0000\u0000\u0000\u0100"+
		"\u00fa\u0001\u0000\u0000\u0000\u0101\u0103\u0001\u0000\u0000\u0000\u0102"+
		"\u00f3\u0001\u0000\u0000\u0000\u0102\u00f7\u0001\u0000\u0000\u0000\u0103"+
		"\u0109\u0001\u0000\u0000\u0000\u0104\u0105\n\u0002\u0000\u0000\u0105\u0106"+
		"\u0005\u0003\u0000\u0000\u0106\u0108\u0005>\u0000\u0000\u0107\u0104\u0001"+
		"\u0000\u0000\u0000\u0108\u010b\u0001\u0000\u0000\u0000\u0109\u0107\u0001"+
		"\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000\u010a\u0007\u0001"+
		"\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010c\u010d\u0005"+
		"\u0007\u0000\u0000\u010d\u010e\u0005>\u0000\u0000\u010e\u010f\u0005\b"+
		"\u0000\u0000\u010f\t\u0001\u0000\u0000\u0000\u0110\u0111\u0003\f\u0006"+
		"\u0000\u0111\u0112\u0005\t\u0000\u0000\u0112\u0113\u0003\n\u0005\u0000"+
		"\u0113\u0119\u0001\u0000\u0000\u0000\u0114\u0116\u0003\u0012\t\u0000\u0115"+
		"\u0117\u0003\u000e\u0007\u0000\u0116\u0115\u0001\u0000\u0000\u0000\u0116"+
		"\u0117\u0001\u0000\u0000\u0000\u0117\u0119\u0001\u0000\u0000\u0000\u0118"+
		"\u0110\u0001\u0000\u0000\u0000\u0118\u0114\u0001\u0000\u0000\u0000\u0119"+
		"\u000b\u0001\u0000\u0000\u0000\u011a\u0128\u0003\u0012\t\u0000\u011b\u0124"+
		"\u0005\n\u0000\u0000\u011c\u0121\u0003j5\u0000\u011d\u011e\u0005\u0004"+
		"\u0000\u0000\u011e\u0120\u0003j5\u0000\u011f\u011d\u0001\u0000\u0000\u0000"+
		"\u0120\u0123\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000"+
		"\u0123\u0121\u0001\u0000\u0000\u0000\u0124\u011c\u0001\u0000\u0000\u0000"+
		"\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000"+
		"\u0126\u0128\u0005\u000b\u0000\u0000\u0127\u011a\u0001\u0000\u0000\u0000"+
		"\u0127\u011b\u0001\u0000\u0000\u0000\u0128\r\u0001\u0000\u0000\u0000\u0129"+
		"\u012a\u0005\f\u0000\u0000\u012a\u012c\u0005\r\u0000\u0000\u012b\u012d"+
		"\u0003\u0010\b\u0000\u012c\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001"+
		"\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012e\u012f\u0001"+
		"\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0005"+
		"\u000e\u0000\u0000\u0131\u000f\u0001\u0000\u0000\u0000\u0132\u0133\u0005"+
		"\u000f\u0000\u0000\u0133\u0137\u0003\u009cN\u0000\u0134\u0135\u0005\u0010"+
		"\u0000\u0000\u0135\u0137\u0003\u0094J\u0000\u0136\u0132\u0001\u0000\u0000"+
		"\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0137\u0011\u0001\u0000\u0000"+
		"\u0000\u0138\u013d\u0003\u0014\n\u0000\u0139\u013a\u0005>\u0000\u0000"+
		"\u013a\u013c\u0003\u0014\n\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013c"+
		"\u013f\u0001\u0000\u0000\u0000\u013d\u013b\u0001\u0000\u0000\u0000\u013d"+
		"\u013e\u0001\u0000\u0000\u0000\u013e\u0013\u0001\u0000\u0000\u0000\u013f"+
		"\u013d\u0001\u0000\u0000\u0000\u0140\u0145\u0003\u0016\u000b\u0000\u0141"+
		"\u0142\u0005\u0011\u0000\u0000\u0142\u0144\u0003\u0016\u000b\u0000\u0143"+
		"\u0141\u0001\u0000\u0000\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145"+
		"\u0143\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146"+
		"\u0149\u0001\u0000\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148"+
		"\u014a\u0003\u001e\u000f\u0000\u0149\u0148\u0001\u0000\u0000\u0000\u0149"+
		"\u014a\u0001\u0000\u0000\u0000\u014a\u014d\u0001\u0000\u0000\u0000\u014b"+
		"\u014d\u0003\u001e\u000f\u0000\u014c\u0140\u0001\u0000\u0000\u0000\u014c"+
		"\u014b\u0001\u0000\u0000\u0000\u014d\u0015\u0001\u0000\u0000\u0000\u014e"+
		"\u0152\u0003\u0018\f\u0000\u014f\u0151\u0003\u0080@\u0000\u0150\u014f"+
		"\u0001\u0000\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000\u0152\u0150"+
		"\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0017"+
		"\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0155\u0156"+
		"\u0006\f\uffff\uffff\u0000\u0156\u0159\u0003\u0006\u0003\u0000\u0157\u0158"+
		"\u0005\u0003\u0000\u0000\u0158\u015a\u0005\u000f\u0000\u0000\u0159\u0157"+
		"\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u0160"+
		"\u0001\u0000\u0000\u0000\u015b\u015c\u0005\n\u0000\u0000\u015c\u015d\u0003"+
		"\u001c\u000e\u0000\u015d\u015e\u0005\u000b\u0000\u0000\u015e\u0160\u0001"+
		"\u0000\u0000\u0000\u015f\u0155\u0001\u0000\u0000\u0000\u015f\u015b\u0001"+
		"\u0000\u0000\u0000\u0160\u0168\u0001\u0000\u0000\u0000\u0161\u0162\n\u0004"+
		"\u0000\u0000\u0162\u0167\u0003\u001a\r\u0000\u0163\u0164\n\u0003\u0000"+
		"\u0000\u0164\u0165\u0005\u0012\u0000\u0000\u0165\u0167\u0005>\u0000\u0000"+
		"\u0166\u0161\u0001\u0000\u0000\u0000\u0166\u0163\u0001\u0000\u0000\u0000"+
		"\u0167\u016a\u0001\u0000\u0000\u0000\u0168\u0166\u0001\u0000\u0000\u0000"+
		"\u0168\u0169\u0001\u0000\u0000\u0000\u0169\u0019\u0001\u0000\u0000\u0000"+
		"\u016a\u0168\u0001\u0000\u0000\u0000\u016b\u016c\u0005\u0007\u0000\u0000"+
		"\u016c\u016d\u0003\u001c\u000e\u0000\u016d\u016e\u0005\b\u0000\u0000\u016e"+
		"\u001b\u0001\u0000\u0000\u0000\u016f\u0174\u0003\n\u0005\u0000\u0170\u0171"+
		"\u0005\u0004\u0000\u0000\u0171\u0173\u0003\n\u0005\u0000\u0172\u0170\u0001"+
		"\u0000\u0000\u0000\u0173\u0176\u0001\u0000\u0000\u0000\u0174\u0172\u0001"+
		"\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u001d\u0001"+
		"\u0000\u0000\u0000\u0176\u0174\u0001\u0000\u0000\u0000\u0177\u0179\u0005"+
		"J\u0000\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000"+
		"\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u017c\u0005\r\u0000"+
		"\u0000\u017b\u017d\u0003 \u0010\u0000\u017c\u017b\u0001\u0000\u0000\u0000"+
		"\u017d\u017e\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000\u0000\u0000"+
		"\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0180\u0001\u0000\u0000\u0000"+
		"\u0180\u0181\u0005\u000e\u0000\u0000\u0181\u001f\u0001\u0000\u0000\u0000"+
		"\u0182\u0186\u0003\u0092I\u0000\u0183\u0184\u0005\u000f\u0000\u0000\u0184"+
		"\u0186\u0003\u00a8T\u0000\u0185\u0182\u0001\u0000\u0000\u0000\u0185\u0183"+
		"\u0001\u0000\u0000\u0000\u0186!\u0001\u0000\u0000\u0000\u0187\u0188\u0003"+
		"\n\u0005\u0000\u0188#\u0001\u0000\u0000\u0000\u0189\u018a\u0005\u0013"+
		"\u0000\u0000\u018a\u0195\u0003\u0012\t\u0000\u018b\u018d\u0005\u0013\u0000"+
		"\u0000\u018c\u018e\u0003\u0080@\u0000\u018d\u018c\u0001\u0000\u0000\u0000"+
		"\u018e\u018f\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000"+
		"\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0195\u0001\u0000\u0000\u0000"+
		"\u0191\u0192\u0005\u0013\u0000\u0000\u0192\u0193\u0005\u0014\u0000\u0000"+
		"\u0193\u0195\u0005\u0015\u0000\u0000\u0194\u0189\u0001\u0000\u0000\u0000"+
		"\u0194\u018b\u0001\u0000\u0000\u0000\u0194\u0191\u0001\u0000\u0000\u0000"+
		"\u0195%\u0001\u0000\u0000\u0000\u0196\u019d\u0003t:\u0000\u0197\u0199"+
		"\u0005\u0016\u0000\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0198\u0199"+
		"\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u019d"+
		"\u0005>\u0000\u0000\u019b\u019d\u0005\u0014\u0000\u0000\u019c\u0196\u0001"+
		"\u0000\u0000\u0000\u019c\u0198\u0001\u0000\u0000\u0000\u019c\u019b\u0001"+
		"\u0000\u0000\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e\u019f\u0005"+
		"\t\u0000\u0000\u019f\u01a2\u0003&\u0013\u0000\u01a0\u01a2\u0003(\u0014"+
		"\u0000\u01a1\u019c\u0001\u0000\u0000\u0000\u01a1\u01a0\u0001\u0000\u0000"+
		"\u0000\u01a2\'\u0001\u0000\u0000\u0000\u01a3\u01a4\u0005\u0017\u0000\u0000"+
		"\u01a4\u01a5\u0005\n\u0000\u0000\u01a5\u01a6\u0003&\u0013\u0000\u01a6"+
		"\u01aa\u0005\u000b\u0000\u0000\u01a7\u01a9\u0005J\u0000\u0000\u01a8\u01a7"+
		"\u0001\u0000\u0000\u0000\u01a9\u01ac\u0001\u0000\u0000\u0000\u01aa\u01a8"+
		"\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ad"+
		"\u0001\u0000\u0000\u0000\u01ac\u01aa\u0001\u0000\u0000\u0000\u01ad\u01b0"+
		"\u0003&\u0013\u0000\u01ae\u01af\u0005\u0018\u0000\u0000\u01af\u01b1\u0003"+
		"&\u0013\u0000\u01b0\u01ae\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000"+
		"\u0000\u0000\u01b1\u0203\u0001\u0000\u0000\u0000\u01b2\u01b3\u0005\u0019"+
		"\u0000\u0000\u01b3\u01b4\u0005\n\u0000\u0000\u01b4\u01b5\u0003&\u0013"+
		"\u0000\u01b5\u01b9\u0005\u000b\u0000\u0000\u01b6\u01b8\u0005J\u0000\u0000"+
		"\u01b7\u01b6\u0001\u0000\u0000\u0000\u01b8\u01bb\u0001\u0000\u0000\u0000"+
		"\u01b9\u01b7\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000"+
		"\u01ba\u01bc\u0001\u0000\u0000\u0000\u01bb\u01b9\u0001\u0000\u0000\u0000"+
		"\u01bc\u01bd\u0003&\u0013\u0000\u01bd\u0203\u0001\u0000\u0000\u0000\u01be"+
		"\u01bf\u0005\u001a\u0000\u0000\u01bf\u01c2\u0003&\u0013\u0000\u01c0\u01c1"+
		"\u0005\u001b\u0000\u0000\u01c1\u01c3\u0003&\u0013\u0000\u01c2\u01c0\u0001"+
		"\u0000\u0000\u0000\u01c2\u01c3\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001"+
		"\u0000\u0000\u0000\u01c4\u01c5\u0005\u001c\u0000\u0000\u01c5\u01c7\u0003"+
		"&\u0013\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000"+
		"\u0000\u0000\u01c7\u0203\u0001\u0000\u0000\u0000\u01c8\u01c9\u0005\u001d"+
		"\u0000\u0000\u01c9\u01ca\u0003&\u0013\u0000\u01ca\u01cb\u0005\u0019\u0000"+
		"\u0000\u01cb\u01cc\u0005\n\u0000\u0000\u01cc\u01cd\u0003&\u0013\u0000"+
		"\u01cd\u01ce\u0005\u000b\u0000\u0000\u01ce\u0203\u0001\u0000\u0000\u0000"+
		"\u01cf\u01d8\u0005\u001e\u0000\u0000\u01d0\u01d1\u0005\n\u0000\u0000\u01d1"+
		"\u01d2\u0003D\"\u0000\u01d2\u01d3\u0005\u000b\u0000\u0000\u01d3\u01d9"+
		"\u0001\u0000\u0000\u0000\u01d4\u01d5\u0005\r\u0000\u0000\u01d5\u01d6\u0003"+
		"D\"\u0000\u01d6\u01d7\u0005\u000e\u0000\u0000\u01d7\u01d9\u0001\u0000"+
		"\u0000\u0000\u01d8\u01d0\u0001\u0000\u0000\u0000\u01d8\u01d4\u0001\u0000"+
		"\u0000\u0000\u01d9\u01db\u0001\u0000\u0000\u0000\u01da\u01dc\u0005\u001f"+
		"\u0000\u0000\u01db\u01da\u0001\u0000\u0000\u0000\u01db\u01dc\u0001\u0000"+
		"\u0000\u0000\u01dc\u01dd\u0001\u0000\u0000\u0000\u01dd\u01de\u0003&\u0013"+
		"\u0000\u01de\u0203\u0001\u0000\u0000\u0000\u01df\u01e0\u0005 \u0000\u0000"+
		"\u01e0\u0203\u0003&\u0013\u0000\u01e1\u01e3\u0005!\u0000\u0000\u01e2\u01e4"+
		"\u0003&\u0013\u0000\u01e3\u01e2\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001"+
		"\u0000\u0000\u0000\u01e4\u0203\u0001\u0000\u0000\u0000\u01e5\u01eb\u0003"+
		"2\u0019\u0000\u01e6\u01e8\u00034\u001a\u0000\u01e7\u01e9\u0005\u0014\u0000"+
		"\u0000\u01e8\u01e7\u0001\u0000\u0000\u0000\u01e8\u01e9\u0001\u0000\u0000"+
		"\u0000\u01e9\u01eb\u0001\u0000\u0000\u0000\u01ea\u01e5\u0001\u0000\u0000"+
		"\u0000\u01ea\u01e6\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000\u0000"+
		"\u0000\u01ec\u01ed\u0005\u0003\u0000\u0000\u01ed\u01ef\u0001\u0000\u0000"+
		"\u0000\u01ee\u01ea\u0001\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000"+
		"\u0000\u01ef\u01f0\u0001\u0000\u0000\u0000\u01f0\u01f1\u0005>\u0000\u0000"+
		"\u01f1\u01f2\u0005\"\u0000\u0000\u01f2\u0203\u0003&\u0013\u0000\u01f3"+
		"\u01f4\u00034\u001a\u0000\u01f4\u01f5\u00038\u001c\u0000\u01f5\u01f6\u0005"+
		"\"\u0000\u0000\u01f6\u01f7\u0003&\u0013\u0000\u01f7\u0203\u0001\u0000"+
		"\u0000\u0000\u01f8\u01fa\u0003,\u0016\u0000\u01f9\u01fb\u0003$\u0012\u0000"+
		"\u01fa\u01f9\u0001\u0000\u0000\u0000\u01fa\u01fb\u0001\u0000\u0000\u0000"+
		"\u01fb\u0203\u0001\u0000\u0000\u0000\u01fc\u01fd\u0003,\u0016\u0000\u01fd"+
		"\u01fe\u0005#\u0000\u0000\u01fe\u01ff\u0005\r\u0000\u0000\u01ff\u0200"+
		"\u0003H$\u0000\u0200\u0201\u0005\u000e\u0000\u0000\u0201\u0203\u0001\u0000"+
		"\u0000\u0000\u0202\u01a3\u0001\u0000\u0000\u0000\u0202\u01b2\u0001\u0000"+
		"\u0000\u0000\u0202\u01be\u0001\u0000\u0000\u0000\u0202\u01c8\u0001\u0000"+
		"\u0000\u0000\u0202\u01cf\u0001\u0000\u0000\u0000\u0202\u01df\u0001\u0000"+
		"\u0000\u0000\u0202\u01e1\u0001\u0000\u0000\u0000\u0202\u01ee\u0001\u0000"+
		"\u0000\u0000\u0202\u01f3\u0001\u0000\u0000\u0000\u0202\u01f8\u0001\u0000"+
		"\u0000\u0000\u0202\u01fc\u0001\u0000\u0000\u0000\u0203)\u0001\u0000\u0000"+
		"\u0000\u0204\u0205\u0007\u0000\u0000\u0000\u0205+\u0001\u0000\u0000\u0000"+
		"\u0206\u0208\u0003.\u0017\u0000\u0207\u0209\u0005>\u0000\u0000\u0208\u0207"+
		"\u0001\u0000\u0000\u0000\u0208\u0209\u0001\u0000\u0000\u0000\u0209\u020f"+
		"\u0001\u0000\u0000\u0000\u020a\u020b\u0003*\u0015\u0000\u020b\u020c\u0003"+
		"4\u001a\u0000\u020c\u020e\u0001\u0000\u0000\u0000\u020d\u020a\u0001\u0000"+
		"\u0000\u0000\u020e\u0211\u0001\u0000\u0000\u0000\u020f\u020d\u0001\u0000"+
		"\u0000\u0000\u020f\u0210\u0001\u0000\u0000\u0000\u0210\u0213\u0001\u0000"+
		"\u0000\u0000\u0211\u020f\u0001\u0000\u0000\u0000\u0212\u0214\u0005J\u0000"+
		"\u0000\u0213\u0212\u0001\u0000\u0000\u0000\u0213\u0214\u0001\u0000\u0000"+
		"\u0000\u0214-\u0001\u0000\u0000\u0000\u0215\u0216\u0006\u0017\uffff\uffff"+
		"\u0000\u0216\u0217\u00030\u0018\u0000\u0217\u0220\u0001\u0000\u0000\u0000"+
		"\u0218\u0219\n\u0001\u0000\u0000\u0219\u021b\u0005>\u0000\u0000\u021a"+
		"\u021c\u0005J\u0000\u0000\u021b\u021a\u0001\u0000\u0000\u0000\u021b\u021c"+
		"\u0001\u0000\u0000\u0000\u021c\u021d\u0001\u0000\u0000\u0000\u021d\u021f"+
		"\u0003.\u0017\u0002\u021e\u0218\u0001\u0000\u0000\u0000\u021f\u0222\u0001"+
		"\u0000\u0000\u0000\u0220\u021e\u0001\u0000\u0000\u0000\u0220\u0221\u0001"+
		"\u0000\u0000\u0000\u0221/\u0001\u0000\u0000\u0000\u0222\u0220\u0001\u0000"+
		"\u0000\u0000\u0223\u0225\u0003*\u0015\u0000\u0224\u0223\u0001\u0000\u0000"+
		"\u0000\u0224\u0225\u0001\u0000\u0000\u0000\u0225\u022b\u0001\u0000\u0000"+
		"\u0000\u0226\u022c\u00032\u0019\u0000\u0227\u0229\u00034\u001a\u0000\u0228"+
		"\u022a\u0005\u0014\u0000\u0000\u0229\u0228\u0001\u0000\u0000\u0000\u0229"+
		"\u022a\u0001\u0000\u0000\u0000\u022a\u022c\u0001\u0000\u0000\u0000\u022b"+
		"\u0226\u0001\u0000\u0000\u0000\u022b\u0227\u0001\u0000\u0000\u0000\u022c"+
		"1\u0001\u0000\u0000\u0000\u022d\u0230\u0005\'\u0000\u0000\u022e\u0231"+
		"\u0003\u00b6[\u0000\u022f\u0231\u0003\u0084B\u0000\u0230\u022e\u0001\u0000"+
		"\u0000\u0000\u0230\u022f\u0001\u0000\u0000\u0000\u0231\u0234\u0001\u0000"+
		"\u0000\u0000\u0232\u0234\u0003<\u001e\u0000\u0233\u022d\u0001\u0000\u0000"+
		"\u0000\u0233\u0232\u0001\u0000\u0000\u0000\u02343\u0001\u0000\u0000\u0000"+
		"\u0235\u0236\u0006\u001a\uffff\uffff\u0000\u0236\u0246\u0003\u0000\u0000"+
		"\u0000\u0237\u0246\u0003\u0006\u0003\u0000\u0238\u0246\u0005\u0014\u0000"+
		"\u0000\u0239\u023b\u0005\n\u0000\u0000\u023a\u023c\u00036\u001b\u0000"+
		"\u023b\u023a\u0001\u0000\u0000\u0000\u023b\u023c\u0001\u0000\u0000\u0000"+
		"\u023c\u023d\u0001\u0000\u0000\u0000\u023d\u0246\u0005\u000b\u0000\u0000"+
		"\u023e\u023f\u00032\u0019\u0000\u023f\u0240\u0005\u0003\u0000\u0000\u0240"+
		"\u0241\u0005>\u0000\u0000\u0241\u0246\u0001\u0000\u0000\u0000\u0242\u0243"+
		"\u00032\u0019\u0000\u0243\u0244\u0003\u001a\r\u0000\u0244\u0246\u0001"+
		"\u0000\u0000\u0000\u0245\u0235\u0001\u0000\u0000\u0000\u0245\u0237\u0001"+
		"\u0000\u0000\u0000\u0245\u0238\u0001\u0000\u0000\u0000\u0245\u0239\u0001"+
		"\u0000\u0000\u0000\u0245\u023e\u0001\u0000\u0000\u0000\u0245\u0242\u0001"+
		"\u0000\u0000\u0000\u0246\u0256\u0001\u0000\u0000\u0000\u0247\u0249\n\u0004"+
		"\u0000\u0000\u0248\u024a\u0005\u0014\u0000\u0000\u0249\u0248\u0001\u0000"+
		"\u0000\u0000\u0249\u024a\u0001\u0000\u0000\u0000\u024a\u024b\u0001\u0000"+
		"\u0000\u0000\u024b\u024c\u0005\u0003\u0000\u0000\u024c\u0255\u0005>\u0000"+
		"\u0000\u024d\u024f\n\u0002\u0000\u0000\u024e\u0250\u0005\u0014\u0000\u0000"+
		"\u024f\u024e\u0001\u0000\u0000\u0000\u024f\u0250\u0001\u0000\u0000\u0000"+
		"\u0250\u0251\u0001\u0000\u0000\u0000\u0251\u0255\u0003\u001a\r\u0000\u0252"+
		"\u0253\n\u0001\u0000\u0000\u0253\u0255\u00038\u001c\u0000\u0254\u0247"+
		"\u0001\u0000\u0000\u0000\u0254\u024d\u0001\u0000\u0000\u0000\u0254\u0252"+
		"\u0001\u0000\u0000\u0000\u0255\u0258\u0001\u0000\u0000\u0000\u0256\u0254"+
		"\u0001\u0000\u0000\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u02575\u0001"+
		"\u0000\u0000\u0000\u0258\u0256\u0001\u0000\u0000\u0000\u0259\u025e\u0003"+
		"&\u0013\u0000\u025a\u025b\u0005\u0004\u0000\u0000\u025b\u025d\u0003&\u0013"+
		"\u0000\u025c\u025a\u0001\u0000\u0000\u0000\u025d\u0260\u0001\u0000\u0000"+
		"\u0000\u025e\u025c\u0001\u0000\u0000\u0000\u025e\u025f\u0001\u0000\u0000"+
		"\u0000\u025f7\u0001\u0000\u0000\u0000\u0260\u025e\u0001\u0000\u0000\u0000"+
		"\u0261\u0262\u0005\n\u0000\u0000\u0262\u0263\u0003:\u001d\u0000\u0263"+
		"\u0264\u0005\u000b\u0000\u0000\u0264\u026e\u0001\u0000\u0000\u0000\u0265"+
		"\u0266\u0005\r\u0000\u0000\u0266\u0267\u0003:\u001d\u0000\u0267\u0268"+
		"\u0005\u000e\u0000\u0000\u0268\u026e\u0001\u0000\u0000\u0000\u0269\u026b"+
		"\u0005J\u0000\u0000\u026a\u0269\u0001\u0000\u0000\u0000\u026a\u026b\u0001"+
		"\u0000\u0000\u0000\u026b\u026c\u0001\u0000\u0000\u0000\u026c\u026e\u0003"+
		"<\u001e\u0000\u026d\u0261\u0001\u0000\u0000\u0000\u026d\u0265\u0001\u0000"+
		"\u0000\u0000\u026d\u026a\u0001\u0000\u0000\u0000\u026e9\u0001\u0000\u0000"+
		"\u0000\u026f\u0271\u00036\u001b\u0000\u0270\u026f\u0001\u0000\u0000\u0000"+
		"\u0270\u0271\u0001\u0000\u0000\u0000\u0271\u027c\u0001\u0000\u0000\u0000"+
		"\u0272\u0273\u00036\u001b\u0000\u0273\u0274\u0005\u0004\u0000\u0000\u0274"+
		"\u0276\u0001\u0000\u0000\u0000\u0275\u0272\u0001\u0000\u0000\u0000\u0275"+
		"\u0276\u0001\u0000\u0000\u0000\u0276\u0277\u0001\u0000\u0000\u0000\u0277"+
		"\u0279\u0003,\u0016\u0000\u0278\u027a\u0007\u0001\u0000\u0000\u0279\u0278"+
		"\u0001\u0000\u0000\u0000\u0279\u027a\u0001\u0000\u0000\u0000\u027a\u027c"+
		"\u0001\u0000\u0000\u0000\u027b\u0270\u0001\u0000\u0000\u0000\u027b\u0275"+
		"\u0001\u0000\u0000\u0000\u027c;\u0001\u0000\u0000\u0000\u027d\u027e\u0005"+
		"\r\u0000\u0000\u027e\u027f\u0003H$\u0000\u027f\u0280\u0005\u000e\u0000"+
		"\u0000\u0280\u0286\u0001\u0000\u0000\u0000\u0281\u0282\u0005\r\u0000\u0000"+
		"\u0282\u0283\u0003>\u001f\u0000\u0283\u0284\u0005\u000e\u0000\u0000\u0284"+
		"\u0286\u0001\u0000\u0000\u0000\u0285\u027d\u0001\u0000\u0000\u0000\u0285"+
		"\u0281\u0001\u0000\u0000\u0000\u0286=\u0001\u0000\u0000\u0000\u0287\u0289"+
		"\u0003@ \u0000\u0288\u0287\u0001\u0000\u0000\u0000\u0289\u028a\u0001\u0000"+
		"\u0000\u0000\u028a\u0288\u0001\u0000\u0000\u0000\u028a\u028b\u0001\u0000"+
		"\u0000\u0000\u028b\u028d\u0001\u0000\u0000\u0000\u028c\u028e\u0003B!\u0000"+
		"\u028d\u028c\u0001\u0000\u0000\u0000\u028d\u028e\u0001\u0000\u0000\u0000"+
		"\u028e?\u0001\u0000\u0000\u0000\u028f\u02a9\u0003\u008aE\u0000\u0290\u0292"+
		"\u0003\u0080@\u0000\u0291\u0290\u0001\u0000\u0000\u0000\u0292\u0295\u0001"+
		"\u0000\u0000\u0000\u0293\u0291\u0001\u0000\u0000\u0000\u0293\u0294\u0001"+
		"\u0000\u0000\u0000\u0294\u0297\u0001\u0000\u0000\u0000\u0295\u0293\u0001"+
		"\u0000\u0000\u0000\u0296\u0298\u0007\u0002\u0000\u0000\u0297\u0296\u0001"+
		"\u0000\u0000\u0000\u0297\u0298\u0001\u0000\u0000\u0000\u0298\u0299\u0001"+
		"\u0000\u0000\u0000\u0299\u02a9\u0003\u00a0P\u0000\u029a\u029c\u0003\u0080"+
		"@\u0000\u029b\u029a\u0001\u0000\u0000\u0000\u029c\u029f\u0001\u0000\u0000"+
		"\u0000\u029d\u029b\u0001\u0000\u0000\u0000\u029d\u029e\u0001\u0000\u0000"+
		"\u0000\u029e\u02a3\u0001\u0000\u0000\u0000\u029f\u029d\u0001\u0000\u0000"+
		"\u0000\u02a0\u02a2\u0003z=\u0000\u02a1\u02a0\u0001\u0000\u0000\u0000\u02a2"+
		"\u02a5\u0001\u0000\u0000\u0000\u02a3\u02a1\u0001\u0000\u0000\u0000\u02a3"+
		"\u02a4\u0001\u0000\u0000\u0000\u02a4\u02a6\u0001\u0000\u0000\u0000\u02a5"+
		"\u02a3\u0001\u0000\u0000\u0000\u02a6\u02a9\u0003\u00aaU\u0000\u02a7\u02a9"+
		"\u0003(\u0014\u0000\u02a8\u028f\u0001\u0000\u0000\u0000\u02a8\u0293\u0001"+
		"\u0000\u0000\u0000\u02a8\u029d\u0001\u0000\u0000\u0000\u02a8\u02a7\u0001"+
		"\u0000\u0000\u0000\u02a9A\u0001\u0000\u0000\u0000\u02aa\u02bb\u0003(\u0014"+
		"\u0000\u02ab\u02b6\u0003t:\u0000\u02ac\u02ae\u0005\u0016\u0000\u0000\u02ad"+
		"\u02ac\u0001\u0000\u0000\u0000\u02ad\u02ae\u0001\u0000\u0000\u0000\u02ae"+
		"\u02af\u0001\u0000\u0000\u0000\u02af\u02b2\u0005>\u0000\u0000\u02b0\u02b2"+
		"\u0005\u0014\u0000\u0000\u02b1\u02ad\u0001\u0000\u0000\u0000\u02b1\u02b0"+
		"\u0001\u0000\u0000\u0000\u02b2\u02b3\u0001\u0000\u0000\u0000\u02b3\u02b4"+
		"\u0005\u0013\u0000\u0000\u02b4\u02b6\u0003\u0014\n\u0000\u02b5\u02ab\u0001"+
		"\u0000\u0000\u0000\u02b5\u02b1\u0001\u0000\u0000\u0000\u02b6\u02b7\u0001"+
		"\u0000\u0000\u0000\u02b7\u02b8\u0005\t\u0000\u0000\u02b8\u02b9\u0003>"+
		"\u001f\u0000\u02b9\u02bb\u0001\u0000\u0000\u0000\u02ba\u02aa\u0001\u0000"+
		"\u0000\u0000\u02ba\u02b5\u0001\u0000\u0000\u0000\u02bbC\u0001\u0000\u0000"+
		"\u0000\u02bc\u02be\u0003F#\u0000\u02bd\u02bc\u0001\u0000\u0000\u0000\u02be"+
		"\u02bf\u0001\u0000\u0000\u0000\u02bf\u02bd\u0001\u0000\u0000\u0000\u02bf"+
		"\u02c0\u0001\u0000\u0000\u0000\u02c0E\u0001\u0000\u0000\u0000\u02c1\u02c2"+
		"\u0003P(\u0000\u02c2\u02c3\u0005)\u0000\u0000\u02c3\u02cb\u0003&\u0013"+
		"\u0000\u02c4\u02ca\u0003L&\u0000\u02c5\u02c6\u0003P(\u0000\u02c6\u02c7"+
		"\u0005\"\u0000\u0000\u02c7\u02c8\u0003&\u0013\u0000\u02c8\u02ca\u0001"+
		"\u0000\u0000\u0000\u02c9\u02c4\u0001\u0000\u0000\u0000\u02c9\u02c5\u0001"+
		"\u0000\u0000\u0000\u02ca\u02cd\u0001\u0000\u0000\u0000\u02cb\u02c9\u0001"+
		"\u0000\u0000\u0000\u02cb\u02cc\u0001\u0000\u0000\u0000\u02ccG\u0001\u0000"+
		"\u0000\u0000\u02cd\u02cb\u0001\u0000\u0000\u0000\u02ce\u02d0\u0003J%\u0000"+
		"\u02cf\u02ce\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001\u0000\u0000\u0000"+
		"\u02d1\u02cf\u0001\u0000\u0000\u0000\u02d1\u02d2\u0001\u0000\u0000\u0000"+
		"\u02d2I\u0001\u0000\u0000\u0000\u02d3\u02d4\u0005*\u0000\u0000\u02d4\u02d6"+
		"\u0003N\'\u0000\u02d5\u02d7\u0003L&\u0000\u02d6\u02d5\u0001\u0000\u0000"+
		"\u0000\u02d6\u02d7\u0001\u0000\u0000\u0000\u02d7\u02d8\u0001\u0000\u0000"+
		"\u0000\u02d8\u02d9\u0005\t\u0000\u0000\u02d9\u02da\u0003>\u001f\u0000"+
		"\u02daK\u0001\u0000\u0000\u0000\u02db\u02dc\u0005\u0017\u0000\u0000\u02dc"+
		"\u02dd\u0003,\u0016\u0000\u02ddM\u0001\u0000\u0000\u0000\u02de\u02e3\u0003"+
		"P(\u0000\u02df\u02e0\u0005+\u0000\u0000\u02e0\u02e2\u0003P(\u0000\u02e1"+
		"\u02df\u0001\u0000\u0000\u0000\u02e2\u02e5\u0001\u0000\u0000\u0000\u02e3"+
		"\u02e1\u0001\u0000\u0000\u0000\u02e3\u02e4\u0001\u0000\u0000\u0000\u02e4"+
		"O\u0001\u0000\u0000\u0000\u02e5\u02e3\u0001\u0000\u0000\u0000\u02e6\u02e7"+
		"\u0007\u0003\u0000\u0000\u02e7\u02e8\u0005\u0013\u0000\u0000\u02e8\u02eb"+
		"\u0003\"\u0011\u0000\u02e9\u02eb\u0003R)\u0000\u02ea\u02e6\u0001\u0000"+
		"\u0000\u0000\u02ea\u02e9\u0001\u0000\u0000\u0000\u02ebQ\u0001\u0000\u0000"+
		"\u0000\u02ec\u02ef\u0005>\u0000\u0000\u02ed\u02ee\u0005,\u0000\u0000\u02ee"+
		"\u02f0\u0003T*\u0000\u02ef\u02ed\u0001\u0000\u0000\u0000\u02ef\u02f0\u0001"+
		"\u0000\u0000\u0000\u02f0\u02f3\u0001\u0000\u0000\u0000\u02f1\u02f3\u0003"+
		"T*\u0000\u02f2\u02ec\u0001\u0000\u0000\u0000\u02f2\u02f1\u0001\u0000\u0000"+
		"\u0000\u02f3S\u0001\u0000\u0000\u0000\u02f4\u0301\u0003V+\u0000\u02f5"+
		"\u02fd\u0003V+\u0000\u02f6\u02f8\u0005>\u0000\u0000\u02f7\u02f9\u0005"+
		"J\u0000\u0000\u02f8\u02f7\u0001\u0000\u0000\u0000\u02f8\u02f9\u0001\u0000"+
		"\u0000\u0000\u02f9\u02fa\u0001\u0000\u0000\u0000\u02fa\u02fc\u0003V+\u0000"+
		"\u02fb\u02f6\u0001\u0000\u0000\u0000\u02fc\u02ff\u0001\u0000\u0000\u0000"+
		"\u02fd\u02fb\u0001\u0000\u0000\u0000\u02fd\u02fe\u0001\u0000\u0000\u0000"+
		"\u02fe\u0301\u0001\u0000\u0000\u0000\u02ff\u02fd\u0001\u0000\u0000\u0000"+
		"\u0300\u02f4\u0001\u0000\u0000\u0000\u0300\u02f5\u0001\u0000\u0000\u0000"+
		"\u0301U\u0001\u0000\u0000\u0000\u0302\u0322\u0005\u0014\u0000\u0000\u0303"+
		"\u0322\u0005E\u0000\u0000\u0304\u0322\u0003\u0000\u0000\u0000\u0305\u030b"+
		"\u0003\u0006\u0003\u0000\u0306\u0308\u0005\n\u0000\u0000\u0307\u0309\u0003"+
		"X,\u0000\u0308\u0307\u0001\u0000\u0000\u0000\u0308\u0309\u0001\u0000\u0000"+
		"\u0000\u0309\u030a\u0001\u0000\u0000\u0000\u030a\u030c\u0005\u000b\u0000"+
		"\u0000\u030b\u0306\u0001\u0000\u0000\u0000\u030b\u030c\u0001\u0000\u0000"+
		"\u0000\u030c\u0322\u0001\u0000\u0000\u0000\u030d\u030e\u0003\u0006\u0003"+
		"\u0000\u030e\u0312\u0005\n\u0000\u0000\u030f\u0310\u0003X,\u0000\u0310"+
		"\u0311\u0005\u0004\u0000\u0000\u0311\u0313\u0001\u0000\u0000\u0000\u0312"+
		"\u030f\u0001\u0000\u0000\u0000\u0312\u0313\u0001\u0000\u0000\u0000\u0313"+
		"\u0316\u0001\u0000\u0000\u0000\u0314\u0315\u0005>\u0000\u0000\u0315\u0317"+
		"\u0005,\u0000\u0000\u0316\u0314\u0001\u0000\u0000\u0000\u0316\u0317\u0001"+
		"\u0000\u0000\u0000\u0317\u0318\u0001\u0000\u0000\u0000\u0318\u0319\u0005"+
		"\u0014\u0000\u0000\u0319\u031a\u0005\u0015\u0000\u0000\u031a\u031b\u0005"+
		"\u000b\u0000\u0000\u031b\u0322\u0001\u0000\u0000\u0000\u031c\u031e\u0005"+
		"\n\u0000\u0000\u031d\u031f\u0003X,\u0000\u031e\u031d\u0001\u0000\u0000"+
		"\u0000\u031e\u031f\u0001\u0000\u0000\u0000\u031f\u0320\u0001\u0000\u0000"+
		"\u0000\u0320\u0322\u0005\u000b\u0000\u0000\u0321\u0302\u0001\u0000\u0000"+
		"\u0000\u0321\u0303\u0001\u0000\u0000\u0000\u0321\u0304\u0001\u0000\u0000"+
		"\u0000\u0321\u0305\u0001\u0000\u0000\u0000\u0321\u030d\u0001\u0000\u0000"+
		"\u0000\u0321\u031c\u0001\u0000\u0000\u0000\u0322W\u0001\u0000\u0000\u0000"+
		"\u0323\u0326\u0003N\'\u0000\u0324\u0325\u0005\u0004\u0000\u0000\u0325"+
		"\u0327\u0003X,\u0000\u0326\u0324\u0001\u0000\u0000\u0000\u0326\u0327\u0001"+
		"\u0000\u0000\u0000\u0327\u032b\u0001\u0000\u0000\u0000\u0328\u0329\u0005"+
		"\u0014\u0000\u0000\u0329\u032b\u0005\u0015\u0000\u0000\u032a\u0323\u0001"+
		"\u0000\u0000\u0000\u032a\u0328\u0001\u0000\u0000\u0000\u032bY\u0001\u0000"+
		"\u0000\u0000\u032c\u032d\u0005\u0007\u0000\u0000\u032d\u0332\u0003^/\u0000"+
		"\u032e\u032f\u0005\u0004\u0000\u0000\u032f\u0331\u0003^/\u0000\u0330\u032e"+
		"\u0001\u0000\u0000\u0000\u0331\u0334\u0001\u0000\u0000\u0000\u0332\u0330"+
		"\u0001\u0000\u0000\u0000\u0332\u0333\u0001\u0000\u0000\u0000\u0333\u0335"+
		"\u0001\u0000\u0000\u0000\u0334\u0332\u0001\u0000\u0000\u0000\u0335\u0336"+
		"\u0005\b\u0000\u0000\u0336[\u0001\u0000\u0000\u0000\u0337\u0338\u0005"+
		"\u0007\u0000\u0000\u0338\u033d\u0003`0\u0000\u0339\u033a\u0005\u0004\u0000"+
		"\u0000\u033a\u033c\u0003`0\u0000\u033b\u0339\u0001\u0000\u0000\u0000\u033c"+
		"\u033f\u0001\u0000\u0000\u0000\u033d\u033b\u0001\u0000\u0000\u0000\u033d"+
		"\u033e\u0001\u0000\u0000\u0000\u033e\u0340\u0001\u0000\u0000\u0000\u033f"+
		"\u033d\u0001\u0000\u0000\u0000\u0340\u0341\u0005\b\u0000\u0000\u0341]"+
		"\u0001\u0000\u0000\u0000\u0342\u0344\u0003\u0080@\u0000\u0343\u0342\u0001"+
		"\u0000\u0000\u0000\u0344\u0347\u0001\u0000\u0000\u0000\u0345\u0343\u0001"+
		"\u0000\u0000\u0000\u0345\u0346\u0001\u0000\u0000\u0000\u0346\u0349\u0001"+
		"\u0000\u0000\u0000\u0347\u0345\u0001\u0000\u0000\u0000\u0348\u034a\u0007"+
		"\u0004\u0000\u0000\u0349\u0348\u0001\u0000\u0000\u0000\u0349\u034a\u0001"+
		"\u0000\u0000\u0000\u034a\u034b\u0001\u0000\u0000\u0000\u034b\u034c\u0003"+
		"`0\u0000\u034c_\u0001\u0000\u0000\u0000\u034d\u034f\u0007\u0005\u0000"+
		"\u0000\u034e\u0350\u0003Z-\u0000\u034f\u034e\u0001\u0000\u0000\u0000\u034f"+
		"\u0350\u0001\u0000\u0000\u0000\u0350\u0353\u0001\u0000\u0000\u0000\u0351"+
		"\u0352\u0005-\u0000\u0000\u0352\u0354\u0003\n\u0005\u0000\u0353\u0351"+
		"\u0001\u0000\u0000\u0000\u0353\u0354\u0001\u0000\u0000\u0000\u0354\u0357"+
		"\u0001\u0000\u0000\u0000\u0355\u0356\u0005.\u0000\u0000\u0356\u0358\u0003"+
		"\n\u0005\u0000\u0357\u0355\u0001\u0000\u0000\u0000\u0357\u0358\u0001\u0000"+
		"\u0000\u0000\u0358\u035d\u0001\u0000\u0000\u0000\u0359\u035a\u0005/\u0000"+
		"\u0000\u035a\u035c\u0003\n\u0005\u0000\u035b\u0359\u0001\u0000\u0000\u0000"+
		"\u035c\u035f\u0001\u0000\u0000\u0000\u035d\u035b\u0001\u0000\u0000\u0000"+
		"\u035d\u035e\u0001\u0000\u0000\u0000\u035e\u0364\u0001\u0000\u0000\u0000"+
		"\u035f\u035d\u0001\u0000\u0000\u0000\u0360\u0361\u0005\u0013\u0000\u0000"+
		"\u0361\u0363\u0003\n\u0005\u0000\u0362\u0360\u0001\u0000\u0000\u0000\u0363"+
		"\u0366\u0001\u0000\u0000\u0000\u0364\u0362\u0001\u0000\u0000\u0000\u0364"+
		"\u0365\u0001\u0000\u0000\u0000\u0365a\u0001\u0000\u0000\u0000\u0366\u0364"+
		"\u0001\u0000\u0000\u0000\u0367\u0369\u0003d2\u0000\u0368\u0367\u0001\u0000"+
		"\u0000\u0000\u0369\u036c\u0001\u0000\u0000\u0000\u036a\u0368\u0001\u0000"+
		"\u0000\u0000\u036a\u036b\u0001\u0000\u0000\u0000\u036b\u0375\u0001\u0000"+
		"\u0000\u0000\u036c\u036a\u0001\u0000\u0000\u0000\u036d\u036f\u0005J\u0000"+
		"\u0000\u036e\u036d\u0001\u0000\u0000\u0000\u036e\u036f\u0001\u0000\u0000"+
		"\u0000\u036f\u0370\u0001\u0000\u0000\u0000\u0370\u0371\u0005\n\u0000\u0000"+
		"\u0371\u0372\u0005\u0016\u0000\u0000\u0372\u0373\u0003f3\u0000\u0373\u0374"+
		"\u0005\u000b\u0000\u0000\u0374\u0376\u0001\u0000\u0000\u0000\u0375\u036e"+
		"\u0001\u0000\u0000\u0000\u0375\u0376\u0001\u0000\u0000\u0000\u0376c\u0001"+
		"\u0000\u0000\u0000\u0377\u0379\u0005J\u0000\u0000\u0378\u0377\u0001\u0000"+
		"\u0000\u0000\u0378\u0379\u0001\u0000\u0000\u0000\u0379\u037a\u0001\u0000"+
		"\u0000\u0000\u037a\u037c\u0005\n\u0000\u0000\u037b\u037d\u0003f3\u0000"+
		"\u037c\u037b\u0001\u0000\u0000\u0000\u037c\u037d\u0001\u0000\u0000\u0000"+
		"\u037d\u037e\u0001\u0000\u0000\u0000\u037e\u037f\u0005\u000b\u0000\u0000"+
		"\u037fe\u0001\u0000\u0000\u0000\u0380\u0385\u0003h4\u0000\u0381\u0382"+
		"\u0005\u0004\u0000\u0000\u0382\u0384\u0003h4\u0000\u0383\u0381\u0001\u0000"+
		"\u0000\u0000\u0384\u0387\u0001\u0000\u0000\u0000\u0385\u0383\u0001\u0000"+
		"\u0000\u0000\u0385\u0386\u0001\u0000\u0000\u0000\u0386g\u0001\u0000\u0000"+
		"\u0000\u0387\u0385\u0001\u0000\u0000\u0000\u0388\u038a\u0003\u0080@\u0000"+
		"\u0389\u0388\u0001\u0000\u0000\u0000\u038a\u038d\u0001\u0000\u0000\u0000"+
		"\u038b\u0389\u0001\u0000\u0000\u0000\u038b\u038c\u0001\u0000\u0000\u0000"+
		"\u038c\u038e\u0001\u0000\u0000\u0000\u038d\u038b\u0001\u0000\u0000\u0000"+
		"\u038e\u0391\u0005>\u0000\u0000\u038f\u0390\u0005\u0013\u0000\u0000\u0390"+
		"\u0392\u0003j5\u0000\u0391\u038f\u0001\u0000\u0000\u0000\u0391\u0392\u0001"+
		"\u0000\u0000\u0000\u0392\u0395\u0001\u0000\u0000\u0000\u0393\u0394\u0005"+
		"\"\u0000\u0000\u0394\u0396\u0003&\u0013\u0000\u0395\u0393\u0001\u0000"+
		"\u0000\u0000\u0395\u0396\u0001\u0000\u0000\u0000\u0396i\u0001\u0000\u0000"+
		"\u0000\u0397\u039e\u0003\n\u0005\u0000\u0398\u0399\u0005\t\u0000\u0000"+
		"\u0399\u039e\u0003\n\u0005\u0000\u039a\u039b\u0003\n\u0005\u0000\u039b"+
		"\u039c\u0005\u0015\u0000\u0000\u039c\u039e\u0001\u0000\u0000\u0000\u039d"+
		"\u0397\u0001\u0000\u0000\u0000\u039d\u0398\u0001\u0000\u0000\u0000\u039d"+
		"\u039a\u0001\u0000\u0000\u0000\u039ek\u0001\u0000\u0000\u0000\u039f\u03a1"+
		"\u0003n7\u0000\u03a0\u039f\u0001\u0000\u0000\u0000\u03a1\u03a4\u0001\u0000"+
		"\u0000\u0000\u03a2\u03a0\u0001\u0000\u0000\u0000\u03a2\u03a3\u0001\u0000"+
		"\u0000\u0000\u03a3\u03ad\u0001\u0000\u0000\u0000\u03a4\u03a2\u0001\u0000"+
		"\u0000\u0000\u03a5\u03a7\u0005J\u0000\u0000\u03a6\u03a5\u0001\u0000\u0000"+
		"\u0000\u03a6\u03a7\u0001\u0000\u0000\u0000\u03a7\u03a8\u0001\u0000\u0000"+
		"\u0000\u03a8\u03a9\u0005\n\u0000\u0000\u03a9\u03aa\u0005\u0016\u0000\u0000"+
		"\u03aa\u03ab\u0003p8\u0000\u03ab\u03ac\u0005\u000b\u0000\u0000\u03ac\u03ae"+
		"\u0001\u0000\u0000\u0000\u03ad\u03a6\u0001\u0000\u0000\u0000\u03ad\u03ae"+
		"\u0001\u0000\u0000\u0000\u03aem\u0001\u0000\u0000\u0000\u03af\u03b1\u0005"+
		"J\u0000\u0000\u03b0\u03af\u0001\u0000\u0000\u0000\u03b0\u03b1\u0001\u0000"+
		"\u0000\u0000\u03b1\u03b2\u0001\u0000\u0000\u0000\u03b2\u03b4\u0005\n\u0000"+
		"\u0000\u03b3\u03b5\u0003p8\u0000\u03b4\u03b3\u0001\u0000\u0000\u0000\u03b4"+
		"\u03b5\u0001\u0000\u0000\u0000\u03b5\u03b6\u0001\u0000\u0000\u0000\u03b6"+
		"\u03b7\u0005\u000b\u0000\u0000\u03b7o\u0001\u0000\u0000\u0000\u03b8\u03bd"+
		"\u0003r9\u0000\u03b9\u03ba\u0005\u0004\u0000\u0000\u03ba\u03bc\u0003r"+
		"9\u0000\u03bb\u03b9\u0001\u0000\u0000\u0000\u03bc\u03bf\u0001\u0000\u0000"+
		"\u0000\u03bd\u03bb\u0001\u0000\u0000\u0000\u03bd\u03be\u0001\u0000\u0000"+
		"\u0000\u03beq\u0001\u0000\u0000\u0000\u03bf\u03bd\u0001\u0000\u0000\u0000"+
		"\u03c0\u03c2\u0003\u0080@\u0000\u03c1\u03c0\u0001\u0000\u0000\u0000\u03c2"+
		"\u03c5\u0001\u0000\u0000\u0000\u03c3\u03c1\u0001\u0000\u0000\u0000\u03c3"+
		"\u03c4\u0001\u0000\u0000\u0000\u03c4\u03c9\u0001\u0000\u0000\u0000\u03c5"+
		"\u03c3\u0001\u0000\u0000\u0000\u03c6\u03c8\u0003x<\u0000\u03c7\u03c6\u0001"+
		"\u0000\u0000\u0000\u03c8\u03cb\u0001\u0000\u0000\u0000\u03c9\u03c7\u0001"+
		"\u0000\u0000\u0000\u03c9\u03ca\u0001\u0000\u0000\u0000\u03ca\u03cd\u0001"+
		"\u0000\u0000\u0000\u03cb\u03c9\u0001\u0000\u0000\u0000\u03cc\u03ce\u0007"+
		"\u0006\u0000\u0000\u03cd\u03cc\u0001\u0000\u0000\u0000\u03cd\u03ce\u0001"+
		"\u0000\u0000\u0000\u03ce\u03cf\u0001\u0000\u0000\u0000\u03cf\u03d0\u0005"+
		">\u0000\u0000\u03d0\u03d1\u0005\u0013\u0000\u0000\u03d1\u03d4\u0003j5"+
		"\u0000\u03d2\u03d3\u0005\"\u0000\u0000\u03d3\u03d5\u0003&\u0013\u0000"+
		"\u03d4\u03d2\u0001\u0000\u0000\u0000\u03d4\u03d5\u0001\u0000\u0000\u0000"+
		"\u03d5s\u0001\u0000\u0000\u0000\u03d6\u03d7\u0005\n\u0000\u0000\u03d7"+
		"\u03dc\u0003v;\u0000\u03d8\u03d9\u0005\u0004\u0000\u0000\u03d9\u03db\u0003"+
		"v;\u0000\u03da\u03d8\u0001\u0000\u0000\u0000\u03db\u03de\u0001\u0000\u0000"+
		"\u0000\u03dc\u03da\u0001\u0000\u0000\u0000\u03dc\u03dd\u0001\u0000\u0000"+
		"\u0000\u03dd\u03df\u0001\u0000\u0000\u0000\u03de\u03dc\u0001\u0000\u0000"+
		"\u0000\u03df\u03e0\u0005\u000b\u0000\u0000\u03e0u\u0001\u0000\u0000\u0000"+
		"\u03e1\u03e4\u0007\u0005\u0000\u0000\u03e2\u03e3\u0005\u0013\u0000\u0000"+
		"\u03e3\u03e5\u0003\n\u0005\u0000\u03e4\u03e2\u0001\u0000\u0000\u0000\u03e4"+
		"\u03e5\u0001\u0000\u0000\u0000\u03e5w\u0001\u0000\u0000\u0000\u03e6\u03ea"+
		"\u0003z=\u0000\u03e7\u03ea\u0003|>\u0000\u03e8\u03ea\u00051\u0000\u0000"+
		"\u03e9\u03e6\u0001\u0000\u0000\u0000\u03e9\u03e7\u0001\u0000\u0000\u0000"+
		"\u03e9\u03e8\u0001\u0000\u0000\u0000\u03eay\u0001\u0000\u0000\u0000\u03eb"+
		"\u03ec\u0007\u0007\u0000\u0000\u03ec{\u0001\u0000\u0000\u0000\u03ed\u03ef"+
		"\u0007\b\u0000\u0000\u03ee\u03f0\u0003~?\u0000\u03ef\u03ee\u0001\u0000"+
		"\u0000\u0000\u03ef\u03f0\u0001\u0000\u0000\u0000\u03f0}\u0001\u0000\u0000"+
		"\u0000\u03f1\u03f2\u0005\u0007\u0000\u0000\u03f2\u03f3\u0007\t\u0000\u0000"+
		"\u03f3\u03f4\u0005\b\u0000\u0000\u03f4\u007f\u0001\u0000\u0000\u0000\u03f5"+
		"\u03f6\u0005,\u0000\u0000\u03f6\u03fa\u0003\u0018\f\u0000\u03f7\u03f9"+
		"\u00038\u001c\u0000\u03f8\u03f7\u0001\u0000\u0000\u0000\u03f9\u03fc\u0001"+
		"\u0000\u0000\u0000\u03fa\u03f8\u0001\u0000\u0000\u0000\u03fa\u03fb\u0001"+
		"\u0000\u0000\u0000\u03fb\u0081\u0001\u0000\u0000\u0000\u03fc\u03fa\u0001"+
		"\u0000\u0000\u0000\u03fd\u03fe\u0005,\u0000\u0000\u03fe\u03ff\u0003\u0018"+
		"\f\u0000\u03ff\u0400\u00038\u001c\u0000\u0400\u0083\u0001\u0000\u0000"+
		"\u0000\u0401\u0403\u0005J\u0000\u0000\u0402\u0401\u0001\u0000\u0000\u0000"+
		"\u0402\u0403\u0001\u0000\u0000\u0000\u0403\u0404\u0001\u0000\u0000\u0000"+
		"\u0404\u0406\u0005\r\u0000\u0000\u0405\u0407\u0003\u0088D\u0000\u0406"+
		"\u0405\u0001\u0000\u0000\u0000\u0406\u0407\u0001\u0000\u0000\u0000\u0407"+
		"\u0409\u0001\u0000\u0000\u0000\u0408\u040a\u0003\u0086C\u0000\u0409\u0408"+
		"\u0001\u0000\u0000\u0000\u040a\u040b\u0001\u0000\u0000\u0000\u040b\u0409"+
		"\u0001\u0000\u0000\u0000\u040b\u040c\u0001\u0000\u0000\u0000\u040c\u040d"+
		"\u0001\u0000\u0000\u0000\u040d\u040e\u0005\u000e\u0000\u0000\u040e\u0085"+
		"\u0001\u0000\u0000\u0000\u040f\u0432\u0003\u008aE\u0000\u0410\u0412\u0003"+
		"\u0080@\u0000\u0411\u0413\u0005J\u0000\u0000\u0412\u0411\u0001\u0000\u0000"+
		"\u0000\u0412\u0413\u0001\u0000\u0000\u0000\u0413\u0415\u0001\u0000\u0000"+
		"\u0000\u0414\u0410\u0001\u0000\u0000\u0000\u0415\u0418\u0001\u0000\u0000"+
		"\u0000\u0416\u0414\u0001\u0000\u0000\u0000\u0416\u0417\u0001\u0000\u0000"+
		"\u0000\u0417\u041c\u0001\u0000\u0000\u0000\u0418\u0416\u0001\u0000\u0000"+
		"\u0000\u0419\u041b\u0003x<\u0000\u041a\u0419\u0001\u0000\u0000\u0000\u041b"+
		"\u041e\u0001\u0000\u0000\u0000\u041c\u041a\u0001\u0000\u0000\u0000\u041c"+
		"\u041d\u0001\u0000\u0000\u0000\u041d\u041f\u0001\u0000\u0000\u0000\u041e"+
		"\u041c\u0001\u0000\u0000\u0000\u041f\u0432\u0003\u00a0P\u0000\u0420\u0422"+
		"\u0003\u0080@\u0000\u0421\u0423\u0005J\u0000\u0000\u0422\u0421\u0001\u0000"+
		"\u0000\u0000\u0422\u0423\u0001\u0000\u0000\u0000\u0423\u0425\u0001\u0000"+
		"\u0000\u0000\u0424\u0420\u0001\u0000\u0000\u0000\u0425\u0428\u0001\u0000"+
		"\u0000\u0000\u0426\u0424\u0001\u0000\u0000\u0000\u0426\u0427\u0001\u0000"+
		"\u0000\u0000\u0427\u042c\u0001\u0000\u0000\u0000\u0428\u0426\u0001\u0000"+
		"\u0000\u0000\u0429\u042b\u0003x<\u0000\u042a\u0429\u0001\u0000\u0000\u0000"+
		"\u042b\u042e\u0001\u0000\u0000\u0000\u042c\u042a\u0001\u0000\u0000\u0000"+
		"\u042c\u042d\u0001\u0000\u0000\u0000\u042d\u042f\u0001\u0000\u0000\u0000"+
		"\u042e\u042c\u0001\u0000\u0000\u0000\u042f\u0432\u0003\u0092I\u0000\u0430"+
		"\u0432\u0003&\u0013\u0000\u0431\u040f\u0001\u0000\u0000\u0000\u0431\u0416"+
		"\u0001\u0000\u0000\u0000\u0431\u0426\u0001\u0000\u0000\u0000\u0431\u0430"+
		"\u0001\u0000\u0000\u0000\u0432\u0087\u0001\u0000\u0000\u0000\u0433\u0436"+
		"\u0005>\u0000\u0000\u0434\u0435\u0005\u0013\u0000\u0000\u0435\u0437\u0003"+
		"\n\u0005\u0000\u0436\u0434\u0001\u0000\u0000\u0000\u0436\u0437\u0001\u0000"+
		"\u0000\u0000\u0437\u0438\u0001\u0000\u0000\u0000\u0438\u043f\u0005\t\u0000"+
		"\u0000\u0439\u043a\u0005\u0005\u0000\u0000\u043a\u043b\u0005\u0013\u0000"+
		"\u0000\u043b\u043c\u0003\n\u0005\u0000\u043c\u043d\u0005\t\u0000\u0000"+
		"\u043d\u043f\u0001\u0000\u0000\u0000\u043e\u0433\u0001\u0000\u0000\u0000"+
		"\u043e\u0439\u0001\u0000\u0000\u0000\u043f\u0089\u0001\u0000\u0000\u0000"+
		"\u0440\u0441\u00057\u0000\u0000\u0441\u0446\u0003\u008cF\u0000\u0442\u0443"+
		"\u0005\u0004\u0000\u0000\u0443\u0445\u0003\u008cF\u0000\u0444\u0442\u0001"+
		"\u0000\u0000\u0000\u0445\u0448\u0001\u0000\u0000\u0000\u0446\u0444\u0001"+
		"\u0000\u0000\u0000\u0446\u0447\u0001\u0000\u0000\u0000\u0447\u008b\u0001"+
		"\u0000\u0000\u0000\u0448\u0446\u0001\u0000\u0000\u0000\u0449\u0450\u0003"+
		"\u0006\u0003\u0000\u044a\u044e\u0005\u0003\u0000\u0000\u044b\u044f\u0005"+
		">\u0000\u0000\u044c\u044f\u0005\u0014\u0000\u0000\u044d\u044f\u0003\u008e"+
		"G\u0000\u044e\u044b\u0001\u0000\u0000\u0000\u044e\u044c\u0001\u0000\u0000"+
		"\u0000\u044e\u044d\u0001\u0000\u0000\u0000\u044f\u0451\u0001\u0000\u0000"+
		"\u0000\u0450\u044a\u0001\u0000\u0000\u0000\u0450\u0451\u0001\u0000\u0000"+
		"\u0000\u0451\u008d\u0001\u0000\u0000\u0000\u0452\u0458\u0005\r\u0000\u0000"+
		"\u0453\u0454\u0003\u0090H\u0000\u0454\u0455\u0005\u0004\u0000\u0000\u0455"+
		"\u0457\u0001\u0000\u0000\u0000\u0456\u0453\u0001\u0000\u0000\u0000\u0457"+
		"\u045a\u0001\u0000\u0000\u0000\u0458\u0456\u0001\u0000\u0000\u0000\u0458"+
		"\u0459\u0001\u0000\u0000\u0000\u0459\u045d\u0001\u0000\u0000\u0000\u045a"+
		"\u0458\u0001\u0000\u0000\u0000\u045b\u045e\u0003\u0090H\u0000\u045c\u045e"+
		"\u0005\u0014\u0000\u0000\u045d\u045b\u0001\u0000\u0000\u0000\u045d\u045c"+
		"\u0001\u0000\u0000\u0000\u045e\u045f\u0001\u0000\u0000\u0000\u045f\u0460"+
		"\u0005\u000e\u0000\u0000\u0460\u008f\u0001\u0000\u0000\u0000\u0461\u0464"+
		"\u0005>\u0000\u0000\u0462\u0463\u0005\t\u0000\u0000\u0463\u0465\u0007"+
		"\u0005\u0000\u0000\u0464\u0462\u0001\u0000\u0000\u0000\u0464\u0465\u0001"+
		"\u0000\u0000\u0000\u0465\u0091\u0001\u0000\u0000\u0000\u0466\u0467\u0005"+
		"\u0010\u0000\u0000\u0467\u0475\u0003\u0094J\u0000\u0468\u0469\u00050\u0000"+
		"\u0000\u0469\u0475\u0003\u0096K\u0000\u046a\u046b\u00058\u0000\u0000\u046b"+
		"\u0475\u0003\u0098L\u0000\u046c\u0470\u0005\u000f\u0000\u0000\u046d\u046f"+
		"\u0005J\u0000\u0000\u046e\u046d\u0001\u0000\u0000\u0000\u046f\u0472\u0001"+
		"\u0000\u0000\u0000\u0470\u046e\u0001\u0000\u0000\u0000\u0470\u0471\u0001"+
		"\u0000\u0000\u0000\u0471\u0473\u0001\u0000\u0000\u0000\u0472\u0470\u0001"+
		"\u0000\u0000\u0000\u0473\u0475\u0003\u009cN\u0000\u0474\u0466\u0001\u0000"+
		"\u0000\u0000\u0474\u0468\u0001\u0000\u0000\u0000\u0474\u046a\u0001\u0000"+
		"\u0000\u0000\u0474\u046c\u0001\u0000\u0000\u0000\u0475\u0093\u0001\u0000"+
		"\u0000\u0000\u0476\u0477\u0003\u0004\u0002\u0000\u0477\u0478\u0005\u0013"+
		"\u0000\u0000\u0478\u0479\u0003\n\u0005\u0000\u0479\u0095\u0001\u0000\u0000"+
		"\u0000\u047a\u047b\u0003\u0004\u0002\u0000\u047b\u047c\u0005\u0013\u0000"+
		"\u0000\u047c\u047d\u0003\n\u0005\u0000\u047d\u0097\u0001\u0000\u0000\u0000"+
		"\u047e\u0481\u0003\u009aM\u0000\u047f\u0480\u0005\u0013\u0000\u0000\u0480"+
		"\u0482\u0003\n\u0005\u0000\u0481\u047f\u0001\u0000\u0000\u0000\u0481\u0482"+
		"\u0001\u0000\u0000\u0000\u0482\u0099\u0001\u0000\u0000\u0000\u0483\u0485"+
		"\u0005>\u0000\u0000\u0484\u0486\u0003\\.\u0000\u0485\u0484\u0001\u0000"+
		"\u0000\u0000\u0485\u0486\u0001\u0000\u0000\u0000\u0486\u0487\u0001\u0000"+
		"\u0000\u0000\u0487\u0488\u0003b1\u0000\u0488\u009b\u0001\u0000\u0000\u0000"+
		"\u0489\u048b\u0005>\u0000\u0000\u048a\u048c\u0003Z-\u0000\u048b\u048a"+
		"\u0001\u0000\u0000\u0000\u048b\u048c\u0001\u0000\u0000\u0000\u048c\u048f"+
		"\u0001\u0000\u0000\u0000\u048d\u048e\u0005-\u0000\u0000\u048e\u0490\u0003"+
		"\n\u0005\u0000\u048f\u048d\u0001\u0000\u0000\u0000\u048f\u0490\u0001\u0000"+
		"\u0000\u0000\u0490\u0493\u0001\u0000\u0000\u0000\u0491\u0492\u0005.\u0000"+
		"\u0000\u0492\u0494\u0003\n\u0005\u0000\u0493\u0491\u0001\u0000\u0000\u0000"+
		"\u0493\u0494\u0001\u0000\u0000\u0000\u0494\u009d\u0001\u0000\u0000\u0000"+
		"\u0495\u0496\u0005\u0010\u0000\u0000\u0496\u049a\u0003\u00a2Q\u0000\u0497"+
		"\u0498\u00050\u0000\u0000\u0498\u049a\u0003\u00a4R\u0000\u0499\u0495\u0001"+
		"\u0000\u0000\u0000\u0499\u0497\u0001\u0000\u0000\u0000\u049a\u009f\u0001"+
		"\u0000\u0000\u0000\u049b\u04a8\u0003\u009eO\u0000\u049c\u049d\u00058\u0000"+
		"\u0000\u049d\u04a8\u0003\u00a6S\u0000\u049e\u04a2\u0005\u000f\u0000\u0000"+
		"\u049f\u04a1\u0005J\u0000\u0000\u04a0\u049f\u0001\u0000\u0000\u0000\u04a1"+
		"\u04a4\u0001\u0000\u0000\u0000\u04a2\u04a0\u0001\u0000\u0000\u0000\u04a2"+
		"\u04a3\u0001\u0000\u0000\u0000\u04a3\u04a5\u0001\u0000\u0000\u0000\u04a4"+
		"\u04a2\u0001\u0000\u0000\u0000\u04a5\u04a8\u0003\u00a8T\u0000\u04a6\u04a8"+
		"\u0003\u00aaU\u0000\u04a7\u049b\u0001\u0000\u0000\u0000\u04a7\u049c\u0001"+
		"\u0000\u0000\u0000\u04a7\u049e\u0001\u0000\u0000\u0000\u04a7\u04a6\u0001"+
		"\u0000\u0000\u0000\u04a8\u00a1\u0001\u0000\u0000\u0000\u04a9\u04ae\u0003"+
		"R)\u0000\u04aa\u04ab\u0005\u0004\u0000\u0000\u04ab\u04ad\u0003R)\u0000"+
		"\u04ac\u04aa\u0001\u0000\u0000\u0000\u04ad\u04b0\u0001\u0000\u0000\u0000"+
		"\u04ae\u04ac\u0001\u0000\u0000\u0000\u04ae\u04af\u0001\u0000\u0000\u0000"+
		"\u04af\u04b3\u0001\u0000\u0000\u0000\u04b0\u04ae\u0001\u0000\u0000\u0000"+
		"\u04b1\u04b2\u0005\u0013\u0000\u0000\u04b2\u04b4\u0003\n\u0005\u0000\u04b3"+
		"\u04b1\u0001\u0000\u0000\u0000\u04b3\u04b4\u0001\u0000\u0000\u0000\u04b4"+
		"\u04b5\u0001\u0000\u0000\u0000\u04b5\u04b6\u0005\"\u0000\u0000\u04b6\u04b7"+
		"\u0003&\u0013\u0000\u04b7\u00a3\u0001\u0000\u0000\u0000\u04b8\u04c0\u0003"+
		"\u00a2Q\u0000\u04b9\u04ba\u0003\u0004\u0002\u0000\u04ba\u04bb\u0005\u0013"+
		"\u0000\u0000\u04bb\u04bc\u0003\n\u0005\u0000\u04bc\u04bd\u0005\"\u0000"+
		"\u0000\u04bd\u04be\u0005\u0014\u0000\u0000\u04be\u04c0\u0001\u0000\u0000"+
		"\u0000\u04bf\u04b8\u0001\u0000\u0000\u0000\u04bf\u04b9\u0001\u0000\u0000"+
		"\u0000\u04c0\u00a5\u0001\u0000\u0000\u0000\u04c1\u04c4\u0003\u009aM\u0000"+
		"\u04c2\u04c3\u0005\u0013\u0000\u0000\u04c3\u04c5\u0003\n\u0005\u0000\u04c4"+
		"\u04c2\u0001\u0000\u0000\u0000\u04c4\u04c5\u0001\u0000\u0000\u0000\u04c5"+
		"\u04c6\u0001\u0000\u0000\u0000\u04c6\u04c7\u0005\"\u0000\u0000\u04c7\u04c8"+
		"\u0003&\u0013\u0000\u04c8\u04dd\u0001\u0000\u0000\u0000\u04c9\u04cb\u0003"+
		"\u009aM\u0000\u04ca\u04cc\u0005J\u0000\u0000\u04cb\u04ca\u0001\u0000\u0000"+
		"\u0000\u04cb\u04cc\u0001\u0000\u0000\u0000\u04cc\u04cd\u0001\u0000\u0000"+
		"\u0000\u04cd\u04ce\u0005\r\u0000\u0000\u04ce\u04cf\u0003>\u001f\u0000"+
		"\u04cf\u04d0\u0005\u000e\u0000\u0000\u04d0\u04dd\u0001\u0000\u0000\u0000"+
		"\u04d1\u04d2\u0005\u0005\u0000\u0000\u04d2\u04d3\u0003d2\u0000\u04d3\u04da"+
		"\u0003b1\u0000\u04d4\u04d5\u0005\"\u0000\u0000\u04d5\u04db\u0003\u00c4"+
		"b\u0000\u04d6\u04d8\u0005J\u0000\u0000\u04d7\u04d6\u0001\u0000\u0000\u0000"+
		"\u04d7\u04d8\u0001\u0000\u0000\u0000\u04d8\u04d9\u0001\u0000\u0000\u0000"+
		"\u04d9\u04db\u0003\u00c6c\u0000\u04da\u04d4\u0001\u0000\u0000\u0000\u04da"+
		"\u04d7\u0001\u0000\u0000\u0000\u04db\u04dd\u0001\u0000\u0000\u0000\u04dc"+
		"\u04c1\u0001\u0000\u0000\u0000\u04dc\u04c9\u0001\u0000\u0000\u0000\u04dc"+
		"\u04d1\u0001\u0000\u0000\u0000\u04dd\u00a7\u0001\u0000\u0000\u0000\u04de"+
		"\u04e0\u0005>\u0000\u0000\u04df\u04e1\u0003Z-\u0000\u04e0\u04df\u0001"+
		"\u0000\u0000\u0000\u04e0\u04e1\u0001\u0000\u0000\u0000\u04e1\u04e2\u0001"+
		"\u0000\u0000\u0000\u04e2\u04e3\u0005\"\u0000\u0000\u04e3\u04e4\u0003\n"+
		"\u0005\u0000\u04e4\u00a9\u0001\u0000\u0000\u0000\u04e5\u04e7\u0005*\u0000"+
		"\u0000\u04e6\u04e5\u0001\u0000\u0000\u0000\u04e6\u04e7\u0001\u0000\u0000"+
		"\u0000\u04e7\u04e8\u0001\u0000\u0000\u0000\u04e8\u04e9\u00059\u0000\u0000"+
		"\u04e9\u04f2\u0003\u00acV\u0000\u04ea\u04ec\u0005*\u0000\u0000\u04eb\u04ea"+
		"\u0001\u0000\u0000\u0000\u04eb\u04ec\u0001\u0000\u0000\u0000\u04ec\u04ed"+
		"\u0001\u0000\u0000\u0000\u04ed\u04ee\u0005:\u0000\u0000\u04ee\u04f2\u0003"+
		"\u00b0X\u0000\u04ef\u04f0\u0005;\u0000\u0000\u04f0\u04f2\u0003\u00aeW"+
		"\u0000\u04f1\u04e6\u0001\u0000\u0000\u0000\u04f1\u04eb\u0001\u0000\u0000"+
		"\u0000\u04f1\u04ef\u0001\u0000\u0000\u0000\u04f2\u00ab\u0001\u0000\u0000"+
		"\u0000\u04f3\u04f5\u0005>\u0000\u0000\u04f4\u04f6\u0003Z-\u0000\u04f5"+
		"\u04f4\u0001\u0000\u0000\u0000\u04f5\u04f6\u0001\u0000\u0000\u0000\u04f6"+
		"\u04fa\u0001\u0000\u0000\u0000\u04f7\u04f9\u0003\u0082A\u0000\u04f8\u04f7"+
		"\u0001\u0000\u0000\u0000\u04f9\u04fc\u0001\u0000\u0000\u0000\u04fa\u04f8"+
		"\u0001\u0000\u0000\u0000\u04fa\u04fb\u0001\u0000\u0000\u0000\u04fb\u04fe"+
		"\u0001\u0000\u0000\u0000\u04fc\u04fa\u0001\u0000\u0000\u0000\u04fd\u04ff"+
		"\u0003|>\u0000\u04fe\u04fd\u0001\u0000\u0000\u0000\u04fe\u04ff\u0001\u0000"+
		"\u0000\u0000\u04ff\u0500\u0001\u0000\u0000\u0000\u0500\u0501\u0003l6\u0000"+
		"\u0501\u0502\u0003\u00b2Y\u0000\u0502\u00ad\u0001\u0000\u0000\u0000\u0503"+
		"\u0505\u0005>\u0000\u0000\u0504\u0506\u0003Z-\u0000\u0505\u0504\u0001"+
		"\u0000\u0000\u0000\u0505\u0506\u0001\u0000\u0000\u0000\u0506\u0507\u0001"+
		"\u0000\u0000\u0000\u0507\u0508\u0003\u00b4Z\u0000\u0508\u00af\u0001\u0000"+
		"\u0000\u0000\u0509\u050a\u0005>\u0000\u0000\u050a\u050b\u0003\u00b2Y\u0000"+
		"\u050b\u00b1\u0001\u0000\u0000\u0000\u050c\u050d\u0005<\u0000\u0000\u050d"+
		"\u0515\u0003\u00b6[\u0000\u050e\u0510\u0005<\u0000\u0000\u050f\u050e\u0001"+
		"\u0000\u0000\u0000\u050f\u0510\u0001\u0000\u0000\u0000\u0510\u0511\u0001"+
		"\u0000\u0000\u0000\u0511\u0513\u0003\u0084B\u0000\u0512\u050f\u0001\u0000"+
		"\u0000\u0000\u0512\u0513\u0001\u0000\u0000\u0000\u0513\u0515\u0001\u0000"+
		"\u0000\u0000\u0514\u050c\u0001\u0000\u0000\u0000\u0514\u0512\u0001\u0000"+
		"\u0000\u0000\u0515\u00b3\u0001\u0000\u0000\u0000\u0516\u0517\u0005<\u0000"+
		"\u0000\u0517\u051f\u0003\u00b8\\\u0000\u0518\u051a\u0005<\u0000\u0000"+
		"\u0519\u0518\u0001\u0000\u0000\u0000\u0519\u051a\u0001\u0000\u0000\u0000"+
		"\u051a\u051b\u0001\u0000\u0000\u0000\u051b\u051d\u0003\u0084B\u0000\u051c"+
		"\u0519\u0001\u0000\u0000\u0000\u051c\u051d\u0001\u0000\u0000\u0000\u051d"+
		"\u051f\u0001\u0000\u0000\u0000\u051e\u0516\u0001\u0000\u0000\u0000\u051e"+
		"\u051c\u0001\u0000\u0000\u0000\u051f\u00b5\u0001\u0000\u0000\u0000\u0520"+
		"\u0522\u0003\u00c0`\u0000\u0521\u0520\u0001\u0000\u0000\u0000\u0521\u0522"+
		"\u0001\u0000\u0000\u0000\u0522\u0523\u0001\u0000\u0000\u0000\u0523\u0525"+
		"\u0003\u00ba]\u0000\u0524\u0526\u0003\u0084B\u0000\u0525\u0524\u0001\u0000"+
		"\u0000\u0000\u0525\u0526\u0001\u0000\u0000\u0000\u0526\u00b7\u0001\u0000"+
		"\u0000\u0000\u0527\u0529\u0003\u00c0`\u0000\u0528\u0527\u0001\u0000\u0000"+
		"\u0000\u0528\u0529\u0001\u0000\u0000\u0000\u0529\u052a\u0001\u0000\u0000"+
		"\u0000\u052a\u052c\u0003\u00bc^\u0000\u052b\u052d\u0003\u0084B\u0000\u052c"+
		"\u052b\u0001\u0000\u0000\u0000\u052c\u052d\u0001\u0000\u0000\u0000\u052d"+
		"\u00b9\u0001\u0000\u0000\u0000\u052e\u0533\u0003\u00be_\u0000\u052f\u0530"+
		"\u0005\u0011\u0000\u0000\u0530\u0532\u0003\u0016\u000b\u0000\u0531\u052f"+
		"\u0001\u0000\u0000\u0000\u0532\u0535\u0001\u0000\u0000\u0000\u0533\u0531"+
		"\u0001\u0000\u0000\u0000\u0533\u0534\u0001\u0000\u0000\u0000\u0534\u00bb"+
		"\u0001\u0000\u0000\u0000\u0535\u0533\u0001\u0000\u0000\u0000\u0536\u053b"+
		"\u0003\u0016\u000b\u0000\u0537\u0538\u0005\u0011\u0000\u0000\u0538\u053a"+
		"\u0003\u0016\u000b\u0000\u0539\u0537\u0001\u0000\u0000\u0000\u053a\u053d"+
		"\u0001\u0000\u0000\u0000\u053b\u0539\u0001\u0000\u0000\u0000\u053b\u053c"+
		"\u0001\u0000\u0000\u0000\u053c\u00bd\u0001\u0000\u0000\u0000\u053d\u053b"+
		"\u0001\u0000\u0000\u0000\u053e\u0542\u0003\u0016\u000b\u0000\u053f\u0541"+
		"\u00038\u001c\u0000\u0540\u053f\u0001\u0000\u0000\u0000\u0541\u0544\u0001"+
		"\u0000\u0000\u0000\u0542\u0540\u0001\u0000\u0000\u0000\u0542\u0543\u0001"+
		"\u0000\u0000\u0000\u0543\u00bf\u0001\u0000\u0000\u0000\u0544\u0542\u0001"+
		"\u0000\u0000\u0000\u0545\u0547\u0005\r\u0000\u0000\u0546\u0548\u0003\u00c2"+
		"a\u0000\u0547\u0546\u0001\u0000\u0000\u0000\u0548\u0549\u0001\u0000\u0000"+
		"\u0000\u0549\u0547\u0001\u0000\u0000\u0000\u0549\u054a\u0001\u0000\u0000"+
		"\u0000\u054a\u054b\u0001\u0000\u0000\u0000\u054b\u054c\u0005\u000e\u0000"+
		"\u0000\u054c\u054d\u0005\u0011\u0000\u0000\u054d\u00c1\u0001\u0000\u0000"+
		"\u0000\u054e\u0550\u0003\u0080@\u0000\u054f\u0551\u0005J\u0000\u0000\u0550"+
		"\u054f\u0001\u0000\u0000\u0000\u0550\u0551\u0001\u0000\u0000\u0000\u0551"+
		"\u0553\u0001\u0000\u0000\u0000\u0552\u054e\u0001\u0000\u0000\u0000\u0553"+
		"\u0556\u0001\u0000\u0000\u0000\u0554\u0552\u0001\u0000\u0000\u0000\u0554"+
		"\u0555\u0001\u0000\u0000\u0000\u0555\u055a\u0001\u0000\u0000\u0000\u0556"+
		"\u0554\u0001\u0000\u0000\u0000\u0557\u0559\u0003x<\u0000\u0558\u0557\u0001"+
		"\u0000\u0000\u0000\u0559\u055c\u0001\u0000\u0000\u0000\u055a\u0558\u0001"+
		"\u0000\u0000\u0000\u055a\u055b\u0001\u0000\u0000\u0000\u055b\u055d\u0001"+
		"\u0000\u0000\u0000\u055c\u055a\u0001\u0000\u0000\u0000\u055d\u055e\u0003"+
		"\u009eO\u0000\u055e\u00c3\u0001\u0000\u0000\u0000\u055f\u0562\u0003\u00c8"+
		"d\u0000\u0560\u0562\u0003\u00c6c\u0000\u0561\u055f\u0001\u0000\u0000\u0000"+
		"\u0561\u0560\u0001\u0000\u0000\u0000\u0562\u00c5\u0001\u0000\u0000\u0000"+
		"\u0563\u0564\u0005\r\u0000\u0000\u0564\u0568\u0003\u00c8d\u0000\u0565"+
		"\u0567\u0003@ \u0000\u0566\u0565\u0001\u0000\u0000\u0000\u0567\u056a\u0001"+
		"\u0000\u0000\u0000\u0568\u0566\u0001\u0000\u0000\u0000\u0568\u0569\u0001"+
		"\u0000\u0000\u0000\u0569\u056b\u0001\u0000\u0000\u0000\u056a\u0568\u0001"+
		"\u0000\u0000\u0000\u056b\u056c\u0005\u000e\u0000\u0000\u056c\u00c7\u0001"+
		"\u0000\u0000\u0000\u056d\u056f\u0005\u0005\u0000\u0000\u056e\u0570\u0003"+
		"8\u001c\u0000\u056f\u056e\u0001\u0000\u0000\u0000\u0570\u0571\u0001\u0000"+
		"\u0000\u0000\u0571\u056f\u0001\u0000\u0000\u0000\u0571\u0572\u0001\u0000"+
		"\u0000\u0000\u0572\u00c9\u0001\u0000\u0000\u0000\u0573\u0575\u0003\u00cc"+
		"f\u0000\u0574\u0573\u0001\u0000\u0000\u0000\u0575\u0576\u0001\u0000\u0000"+
		"\u0000\u0576\u0574\u0001\u0000\u0000\u0000\u0576\u0577\u0001\u0000\u0000"+
		"\u0000\u0577\u00cb\u0001\u0000\u0000\u0000\u0578\u057a\u0003\u0080@\u0000"+
		"\u0579\u057b\u0005J\u0000\u0000\u057a\u0579\u0001\u0000\u0000\u0000\u057a"+
		"\u057b\u0001\u0000\u0000\u0000\u057b\u057d\u0001\u0000\u0000\u0000\u057c"+
		"\u0578\u0001\u0000\u0000\u0000\u057d\u0580\u0001\u0000\u0000\u0000\u057e"+
		"\u057c\u0001\u0000\u0000\u0000\u057e\u057f\u0001\u0000\u0000\u0000\u057f"+
		"\u0584\u0001\u0000\u0000\u0000\u0580\u057e\u0001\u0000\u0000\u0000\u0581"+
		"\u0583\u0003x<\u0000\u0582\u0581\u0001\u0000\u0000\u0000\u0583\u0586\u0001"+
		"\u0000\u0000\u0000\u0584\u0582\u0001\u0000\u0000\u0000\u0584\u0585\u0001"+
		"\u0000\u0000\u0000\u0585\u0587\u0001\u0000\u0000\u0000\u0586\u0584\u0001"+
		"\u0000\u0000\u0000\u0587\u058c\u0003\u00aaU\u0000\u0588\u058c\u0003\u008a"+
		"E\u0000\u0589\u058c\u0003\u00ceg\u0000\u058a\u058c\u0003\u00d0h\u0000"+
		"\u058b\u057e\u0001\u0000\u0000\u0000\u058b\u0588\u0001\u0000\u0000\u0000"+
		"\u058b\u0589\u0001\u0000\u0000\u0000\u058b\u058a\u0001\u0000\u0000\u0000"+
		"\u058c\u00cd\u0001\u0000\u0000\u0000\u058d\u058e\u0005=\u0000\u0000\u058e"+
		"\u0590\u0003\u0002\u0001\u0000\u058f\u0591\u0005J\u0000\u0000\u0590\u058f"+
		"\u0001\u0000\u0000\u0000\u0590\u0591\u0001\u0000\u0000\u0000\u0591\u0592"+
		"\u0001\u0000\u0000\u0000\u0592\u0593\u0005\r\u0000\u0000\u0593\u0594\u0003"+
		"\u00cae\u0000\u0594\u0595\u0005\u000e\u0000\u0000\u0595\u00cf\u0001\u0000"+
		"\u0000\u0000\u0596\u0597\u0005=\u0000\u0000\u0597\u0598\u0005:\u0000\u0000"+
		"\u0598\u0599\u0003\u00b0X\u0000\u0599\u00d1\u0001\u0000\u0000\u0000\u059a"+
		"\u059b\u0005=\u0000\u0000\u059b\u059d\u0003\u0002\u0001\u0000\u059c\u059a"+
		"\u0001\u0000\u0000\u0000\u059d\u05a0\u0001\u0000\u0000\u0000\u059e\u059c"+
		"\u0001\u0000\u0000\u0000\u059e\u059f\u0001\u0000\u0000\u0000\u059f\u05a1"+
		"\u0001\u0000\u0000\u0000\u05a0\u059e\u0001\u0000\u0000\u0000\u05a1\u05a2"+
		"\u0003\u00cae\u0000\u05a2\u00d3\u0001\u0000\u0000\u0000\u00d0\u00d5\u00d9"+
		"\u00e1\u00e8\u00f0\u00f7\u00fc\u0100\u0102\u0109\u0116\u0118\u0121\u0124"+
		"\u0127\u012e\u0136\u013d\u0145\u0149\u014c\u0152\u0159\u015f\u0166\u0168"+
		"\u0174\u0178\u017e\u0185\u018f\u0194\u0198\u019c\u01a1\u01aa\u01b0\u01b9"+
		"\u01c2\u01c6\u01d8\u01db\u01e3\u01e8\u01ea\u01ee\u01fa\u0202\u0208\u020f"+
		"\u0213\u021b\u0220\u0224\u0229\u022b\u0230\u0233\u023b\u0245\u0249\u024f"+
		"\u0254\u0256\u025e\u026a\u026d\u0270\u0275\u0279\u027b\u0285\u028a\u028d"+
		"\u0293\u0297\u029d\u02a3\u02a8\u02ad\u02b1\u02b5\u02ba\u02bf\u02c9\u02cb"+
		"\u02d1\u02d6\u02e3\u02ea\u02ef\u02f2\u02f8\u02fd\u0300\u0308\u030b\u0312"+
		"\u0316\u031e\u0321\u0326\u032a\u0332\u033d\u0345\u0349\u034f\u0353\u0357"+
		"\u035d\u0364\u036a\u036e\u0375\u0378\u037c\u0385\u038b\u0391\u0395\u039d"+
		"\u03a2\u03a6\u03ad\u03b0\u03b4\u03bd\u03c3\u03c9\u03cd\u03d4\u03dc\u03e4"+
		"\u03e9\u03ef\u03fa\u0402\u0406\u040b\u0412\u0416\u041c\u0422\u0426\u042c"+
		"\u0431\u0436\u043e\u0446\u044e\u0450\u0458\u045d\u0464\u0470\u0474\u0481"+
		"\u0485\u048b\u048f\u0493\u0499\u04a2\u04a7\u04ae\u04b3\u04bf\u04c4\u04cb"+
		"\u04d7\u04da\u04dc\u04e0\u04e6\u04eb\u04f1\u04f5\u04fa\u04fe\u0505\u050f"+
		"\u0512\u0514\u0519\u051c\u051e\u0521\u0525\u0528\u052c\u0533\u053b\u0542"+
		"\u0549\u0550\u0554\u055a\u0561\u0568\u0571\u0576\u057a\u057e\u0584\u058b"+
		"\u0590\u059e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}