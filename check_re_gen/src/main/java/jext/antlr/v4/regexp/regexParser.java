// Generated from regexParser.g4 by ANTLR 4.13.2
package jext.antlr.v4.regexp;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class regexParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, PIPE=3, PLUS=4, QUESTION=5, STAR=6, WildcardEsc=7, 
		Char=8, StartQuantity=9, SingleCharEsc=10, MultiCharEsc=11, CatEsc=12, 
		ComplEsc=13, NegCharGroup=14, PosCharGroup=15, EndQuantity=16, QuantExact=17, 
		COMMA=18, EndCategory=19, IsCategory=20, Letters=21, Marks=22, Numbers=23, 
		Punctuation=24, Separators=25, Symbols=26, Others=27, IsBlock=28, NestedSingleCharEsc=29, 
		NestedMultiCharEsc=30, NestedCatEsc=31, NestedComplEsc=32, NestedNegCharGroup=33, 
		NestedPosCharGroup=34, EndCharGroup=35, DASH=36, XmlChar=37;
	public static final int
		RULE_root = 0, RULE_regExp = 1, RULE_branch = 2, RULE_piece = 3, RULE_quantifier = 4, 
		RULE_quantity = 5, RULE_quantRange = 6, RULE_quantMin = 7, RULE_atom = 8, 
		RULE_charClass = 9, RULE_charClassExpr = 10, RULE_charGroup = 11, RULE_posCharGroup = 12, 
		RULE_charRange = 13, RULE_seRange = 14, RULE_charOrEsc = 15, RULE_charClassEsc = 16, 
		RULE_catEsc = 17, RULE_complEsc = 18, RULE_charProp = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "regExp", "branch", "piece", "quantifier", "quantity", "quantRange", 
			"quantMin", "atom", "charClass", "charClassExpr", "charGroup", "posCharGroup", 
			"charRange", "seRange", "charOrEsc", "charClassEsc", "catEsc", "complEsc", 
			"charProp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'|'", "'+'", "'?'", "'*'", "'.'", null, "'{'", null, 
			null, null, null, null, null, null, null, "','", null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"']'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LPAREN", "RPAREN", "PIPE", "PLUS", "QUESTION", "STAR", "WildcardEsc", 
			"Char", "StartQuantity", "SingleCharEsc", "MultiCharEsc", "CatEsc", "ComplEsc", 
			"NegCharGroup", "PosCharGroup", "EndQuantity", "QuantExact", "COMMA", 
			"EndCategory", "IsCategory", "Letters", "Marks", "Numbers", "Punctuation", 
			"Separators", "Symbols", "Others", "IsBlock", "NestedSingleCharEsc", 
			"NestedMultiCharEsc", "NestedCatEsc", "NestedComplEsc", "NestedNegCharGroup", 
			"NestedPosCharGroup", "EndCharGroup", "DASH", "XmlChar"
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
	public String getGrammarFileName() { return "regexParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public regexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RootContext extends ParserRuleContext {
		public RegExpContext regExp() {
			return getRuleContext(RegExpContext.class,0);
		}
		public TerminalNode EOF() { return getToken(regexParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			regExp();
			setState(41);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RegExpContext extends ParserRuleContext {
		public List<BranchContext> branch() {
			return getRuleContexts(BranchContext.class);
		}
		public BranchContext branch(int i) {
			return getRuleContext(BranchContext.class,i);
		}
		public List<TerminalNode> PIPE() { return getTokens(regexParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(regexParser.PIPE, i);
		}
		public RegExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterRegExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitRegExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitRegExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegExpContext regExp() throws RecognitionException {
		RegExpContext _localctx = new RegExpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_regExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			branch();
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PIPE) {
				{
				{
				setState(44);
				match(PIPE);
				setState(45);
				branch();
				}
				}
				setState(50);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BranchContext extends ParserRuleContext {
		public List<PieceContext> piece() {
			return getRuleContexts(PieceContext.class);
		}
		public PieceContext piece(int i) {
			return getRuleContext(PieceContext.class,i);
		}
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_branch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33822932354L) != 0)) {
				{
				{
				setState(51);
				piece();
				}
				}
				setState(56);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PieceContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public QuantifierContext quantifier() {
			return getRuleContext(QuantifierContext.class,0);
		}
		public PieceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_piece; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterPiece(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitPiece(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitPiece(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PieceContext piece() throws RecognitionException {
		PieceContext _localctx = new PieceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_piece);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			atom();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 624L) != 0)) {
				{
				setState(58);
				quantifier();
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

	@SuppressWarnings("CheckReturnValue")
	public static class QuantifierContext extends ParserRuleContext {
		public TerminalNode QUESTION() { return getToken(regexParser.QUESTION, 0); }
		public TerminalNode STAR() { return getToken(regexParser.STAR, 0); }
		public TerminalNode PLUS() { return getToken(regexParser.PLUS, 0); }
		public TerminalNode StartQuantity() { return getToken(regexParser.StartQuantity, 0); }
		public QuantityContext quantity() {
			return getRuleContext(QuantityContext.class,0);
		}
		public TerminalNode EndQuantity() { return getToken(regexParser.EndQuantity, 0); }
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterQuantifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitQuantifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quantifier);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUESTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(QUESTION);
				}
				break;
			case STAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(STAR);
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				match(PLUS);
				}
				break;
			case StartQuantity:
				enterOuterAlt(_localctx, 4);
				{
				setState(64);
				match(StartQuantity);
				setState(65);
				quantity();
				setState(66);
				match(EndQuantity);
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

	@SuppressWarnings("CheckReturnValue")
	public static class QuantityContext extends ParserRuleContext {
		public QuantRangeContext quantRange() {
			return getRuleContext(QuantRangeContext.class,0);
		}
		public QuantMinContext quantMin() {
			return getRuleContext(QuantMinContext.class,0);
		}
		public TerminalNode QuantExact() { return getToken(regexParser.QuantExact, 0); }
		public QuantityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterQuantity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitQuantity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitQuantity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantityContext quantity() throws RecognitionException {
		QuantityContext _localctx = new QuantityContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quantity);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				quantRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				quantMin();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				match(QuantExact);
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

	@SuppressWarnings("CheckReturnValue")
	public static class QuantRangeContext extends ParserRuleContext {
		public List<TerminalNode> QuantExact() { return getTokens(regexParser.QuantExact); }
		public TerminalNode QuantExact(int i) {
			return getToken(regexParser.QuantExact, i);
		}
		public TerminalNode COMMA() { return getToken(regexParser.COMMA, 0); }
		public QuantRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterQuantRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitQuantRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitQuantRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantRangeContext quantRange() throws RecognitionException {
		QuantRangeContext _localctx = new QuantRangeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_quantRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(QuantExact);
			setState(76);
			match(COMMA);
			setState(77);
			match(QuantExact);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuantMinContext extends ParserRuleContext {
		public TerminalNode QuantExact() { return getToken(regexParser.QuantExact, 0); }
		public TerminalNode COMMA() { return getToken(regexParser.COMMA, 0); }
		public QuantMinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantMin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterQuantMin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitQuantMin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitQuantMin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantMinContext quantMin() throws RecognitionException {
		QuantMinContext _localctx = new QuantMinContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_quantMin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(QuantExact);
			setState(80);
			match(COMMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public TerminalNode Char() { return getToken(regexParser.Char, 0); }
		public CharClassContext charClass() {
			return getRuleContext(CharClassContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(regexParser.LPAREN, 0); }
		public RegExpContext regExp() {
			return getRuleContext(RegExpContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(regexParser.RPAREN, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_atom);
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Char:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				match(Char);
				}
				break;
			case WildcardEsc:
			case SingleCharEsc:
			case MultiCharEsc:
			case CatEsc:
			case ComplEsc:
			case NegCharGroup:
			case PosCharGroup:
			case NestedSingleCharEsc:
			case NestedMultiCharEsc:
			case NestedCatEsc:
			case NestedComplEsc:
			case NestedNegCharGroup:
			case NestedPosCharGroup:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				charClass();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(84);
				match(LPAREN);
				setState(85);
				regExp();
				setState(86);
				match(RPAREN);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CharClassContext extends ParserRuleContext {
		public CharClassEscContext charClassEsc() {
			return getRuleContext(CharClassEscContext.class,0);
		}
		public CharClassExprContext charClassExpr() {
			return getRuleContext(CharClassExprContext.class,0);
		}
		public TerminalNode WildcardEsc() { return getToken(regexParser.WildcardEsc, 0); }
		public CharClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharClassContext charClass() throws RecognitionException {
		CharClassContext _localctx = new CharClassContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_charClass);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SingleCharEsc:
			case MultiCharEsc:
			case CatEsc:
			case ComplEsc:
			case NestedSingleCharEsc:
			case NestedMultiCharEsc:
			case NestedCatEsc:
			case NestedComplEsc:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				charClassEsc();
				}
				break;
			case NegCharGroup:
			case PosCharGroup:
			case NestedNegCharGroup:
			case NestedPosCharGroup:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				charClassExpr();
				}
				break;
			case WildcardEsc:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				match(WildcardEsc);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CharClassExprContext extends ParserRuleContext {
		public CharGroupContext charGroup() {
			return getRuleContext(CharGroupContext.class,0);
		}
		public TerminalNode EndCharGroup() { return getToken(regexParser.EndCharGroup, 0); }
		public TerminalNode NegCharGroup() { return getToken(regexParser.NegCharGroup, 0); }
		public TerminalNode NestedNegCharGroup() { return getToken(regexParser.NestedNegCharGroup, 0); }
		public TerminalNode PosCharGroup() { return getToken(regexParser.PosCharGroup, 0); }
		public TerminalNode NestedPosCharGroup() { return getToken(regexParser.NestedPosCharGroup, 0); }
		public CharClassExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charClassExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharClassExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharClassExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharClassExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharClassExprContext charClassExpr() throws RecognitionException {
		CharClassExprContext _localctx = new CharClassExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_charClassExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 25769852928L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(96);
			charGroup();
			setState(97);
			match(EndCharGroup);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharGroupContext extends ParserRuleContext {
		public List<TerminalNode> DASH() { return getTokens(regexParser.DASH); }
		public TerminalNode DASH(int i) {
			return getToken(regexParser.DASH, i);
		}
		public CharClassExprContext charClassExpr() {
			return getRuleContext(CharClassExprContext.class,0);
		}
		public PosCharGroupContext posCharGroup() {
			return getRuleContext(PosCharGroupContext.class,0);
		}
		public CharGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharGroupContext charGroup() throws RecognitionException {
		CharGroupContext _localctx = new CharGroupContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_charGroup);
		int _la;
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(99);
					posCharGroup();
					}
					break;
				}
				setState(102);
				match(DASH);
				setState(103);
				match(DASH);
				setState(104);
				charClassExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				posCharGroup();
				setState(106);
				match(DASH);
				setState(107);
				charClassExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				posCharGroup();
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DASH) {
					{
					setState(110);
					match(DASH);
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				match(DASH);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PosCharGroupContext extends ParserRuleContext {
		public TerminalNode DASH() { return getToken(regexParser.DASH, 0); }
		public List<CharRangeContext> charRange() {
			return getRuleContexts(CharRangeContext.class);
		}
		public CharRangeContext charRange(int i) {
			return getRuleContext(CharRangeContext.class,i);
		}
		public List<CharClassEscContext> charClassEsc() {
			return getRuleContexts(CharClassEscContext.class);
		}
		public CharClassEscContext charClassEsc(int i) {
			return getRuleContext(CharClassEscContext.class,i);
		}
		public PosCharGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_posCharGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterPosCharGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitPosCharGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitPosCharGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PosCharGroupContext posCharGroup() throws RecognitionException {
		PosCharGroupContext _localctx = new PosCharGroupContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_posCharGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DASH) {
				{
				setState(116);
				match(DASH);
				}
			}

			setState(121); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(121);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(119);
					charRange();
					}
					break;
				case 2:
					{
					setState(120);
					charClassEsc();
					}
					break;
				}
				}
				setState(123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 145492032512L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharRangeContext extends ParserRuleContext {
		public SeRangeContext seRange() {
			return getRuleContext(SeRangeContext.class,0);
		}
		public TerminalNode XmlChar() { return getToken(regexParser.XmlChar, 0); }
		public CharRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharRangeContext charRange() throws RecognitionException {
		CharRangeContext _localctx = new CharRangeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_charRange);
		try {
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				seRange();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(XmlChar);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SeRangeContext extends ParserRuleContext {
		public List<CharOrEscContext> charOrEsc() {
			return getRuleContexts(CharOrEscContext.class);
		}
		public CharOrEscContext charOrEsc(int i) {
			return getRuleContext(CharOrEscContext.class,i);
		}
		public TerminalNode DASH() { return getToken(regexParser.DASH, 0); }
		public SeRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterSeRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitSeRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitSeRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeRangeContext seRange() throws RecognitionException {
		SeRangeContext _localctx = new SeRangeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_seRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			charOrEsc();
			setState(130);
			match(DASH);
			setState(131);
			charOrEsc();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharOrEscContext extends ParserRuleContext {
		public TerminalNode XmlChar() { return getToken(regexParser.XmlChar, 0); }
		public TerminalNode SingleCharEsc() { return getToken(regexParser.SingleCharEsc, 0); }
		public CharOrEscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charOrEsc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharOrEsc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharOrEsc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharOrEsc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharOrEscContext charOrEsc() throws RecognitionException {
		CharOrEscContext _localctx = new CharOrEscContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_charOrEsc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_la = _input.LA(1);
			if ( !(_la==SingleCharEsc || _la==XmlChar) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class CharClassEscContext extends ParserRuleContext {
		public TerminalNode SingleCharEsc() { return getToken(regexParser.SingleCharEsc, 0); }
		public TerminalNode NestedSingleCharEsc() { return getToken(regexParser.NestedSingleCharEsc, 0); }
		public TerminalNode MultiCharEsc() { return getToken(regexParser.MultiCharEsc, 0); }
		public TerminalNode NestedMultiCharEsc() { return getToken(regexParser.NestedMultiCharEsc, 0); }
		public CatEscContext catEsc() {
			return getRuleContext(CatEscContext.class,0);
		}
		public ComplEscContext complEsc() {
			return getRuleContext(ComplEscContext.class,0);
		}
		public CharClassEscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charClassEsc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharClassEsc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharClassEsc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharClassEsc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharClassEscContext charClassEsc() throws RecognitionException {
		CharClassEscContext _localctx = new CharClassEscContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_charClassEsc);
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SingleCharEsc:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				match(SingleCharEsc);
				}
				break;
			case NestedSingleCharEsc:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(NestedSingleCharEsc);
				}
				break;
			case MultiCharEsc:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(MultiCharEsc);
				}
				break;
			case NestedMultiCharEsc:
				enterOuterAlt(_localctx, 4);
				{
				setState(138);
				match(NestedMultiCharEsc);
				}
				break;
			case CatEsc:
			case NestedCatEsc:
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				catEsc();
				}
				break;
			case ComplEsc:
			case NestedComplEsc:
				enterOuterAlt(_localctx, 6);
				{
				setState(140);
				complEsc();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CatEscContext extends ParserRuleContext {
		public CharPropContext charProp() {
			return getRuleContext(CharPropContext.class,0);
		}
		public TerminalNode EndCategory() { return getToken(regexParser.EndCategory, 0); }
		public TerminalNode CatEsc() { return getToken(regexParser.CatEsc, 0); }
		public TerminalNode NestedCatEsc() { return getToken(regexParser.NestedCatEsc, 0); }
		public CatEscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catEsc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCatEsc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCatEsc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCatEsc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatEscContext catEsc() throws RecognitionException {
		CatEscContext _localctx = new CatEscContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_catEsc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !(_la==CatEsc || _la==NestedCatEsc) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(144);
			charProp();
			setState(145);
			match(EndCategory);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComplEscContext extends ParserRuleContext {
		public CharPropContext charProp() {
			return getRuleContext(CharPropContext.class,0);
		}
		public TerminalNode EndCategory() { return getToken(regexParser.EndCategory, 0); }
		public TerminalNode ComplEsc() { return getToken(regexParser.ComplEsc, 0); }
		public TerminalNode NestedComplEsc() { return getToken(regexParser.NestedComplEsc, 0); }
		public ComplEscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complEsc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterComplEsc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitComplEsc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitComplEsc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplEscContext complEsc() throws RecognitionException {
		ComplEscContext _localctx = new ComplEscContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_complEsc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_la = _input.LA(1);
			if ( !(_la==ComplEsc || _la==NestedComplEsc) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(148);
			charProp();
			setState(149);
			match(EndCategory);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharPropContext extends ParserRuleContext {
		public TerminalNode IsCategory() { return getToken(regexParser.IsCategory, 0); }
		public TerminalNode IsBlock() { return getToken(regexParser.IsBlock, 0); }
		public CharPropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charProp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).enterCharProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regexParserListener ) ((regexParserListener)listener).exitCharProp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof regexParserVisitor ) return ((regexParserVisitor<? extends T>)visitor).visitCharProp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharPropContext charProp() throws RecognitionException {
		CharPropContext _localctx = new CharPropContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_charProp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_la = _input.LA(1);
			if ( !(_la==IsCategory || _la==IsBlock) ) {
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

	public static final String _serializedATN =
		"\u0004\u0001%\u009a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001/\b\u0001\n\u0001\f\u00012\t\u0001"+
		"\u0001\u0002\u0005\u00025\b\u0002\n\u0002\f\u00028\t\u0002\u0001\u0003"+
		"\u0001\u0003\u0003\u0003<\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004E\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005J\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bY\b\b\u0001\t"+
		"\u0001\t\u0001\t\u0003\t^\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0003\u000be\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"p\b\u000b\u0001\u000b\u0003\u000bs\b\u000b\u0001\f\u0003\fv\b\f\u0001"+
		"\f\u0001\f\u0004\fz\b\f\u000b\f\f\f{\u0001\r\u0001\r\u0003\r\u0080\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u008e\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0000\u0000\u0014\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&\u0000\u0005\u0002\u0000"+
		"\u000e\u000f!\"\u0002\u0000\n\n%%\u0002\u0000\f\f\u001f\u001f\u0002\u0000"+
		"\r\r  \u0002\u0000\u0014\u0014\u001c\u001c\u009f\u0000(\u0001\u0000\u0000"+
		"\u0000\u0002+\u0001\u0000\u0000\u0000\u00046\u0001\u0000\u0000\u0000\u0006"+
		"9\u0001\u0000\u0000\u0000\bD\u0001\u0000\u0000\u0000\nI\u0001\u0000\u0000"+
		"\u0000\fK\u0001\u0000\u0000\u0000\u000eO\u0001\u0000\u0000\u0000\u0010"+
		"X\u0001\u0000\u0000\u0000\u0012]\u0001\u0000\u0000\u0000\u0014_\u0001"+
		"\u0000\u0000\u0000\u0016r\u0001\u0000\u0000\u0000\u0018u\u0001\u0000\u0000"+
		"\u0000\u001a\u007f\u0001\u0000\u0000\u0000\u001c\u0081\u0001\u0000\u0000"+
		"\u0000\u001e\u0085\u0001\u0000\u0000\u0000 \u008d\u0001\u0000\u0000\u0000"+
		"\"\u008f\u0001\u0000\u0000\u0000$\u0093\u0001\u0000\u0000\u0000&\u0097"+
		"\u0001\u0000\u0000\u0000()\u0003\u0002\u0001\u0000)*\u0005\u0000\u0000"+
		"\u0001*\u0001\u0001\u0000\u0000\u0000+0\u0003\u0004\u0002\u0000,-\u0005"+
		"\u0003\u0000\u0000-/\u0003\u0004\u0002\u0000.,\u0001\u0000\u0000\u0000"+
		"/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001\u0000\u0000"+
		"\u00001\u0003\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000035\u0003"+
		"\u0006\u0003\u000043\u0001\u0000\u0000\u000058\u0001\u0000\u0000\u0000"+
		"64\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u00007\u0005\u0001\u0000"+
		"\u0000\u000086\u0001\u0000\u0000\u00009;\u0003\u0010\b\u0000:<\u0003\b"+
		"\u0004\u0000;:\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<\u0007"+
		"\u0001\u0000\u0000\u0000=E\u0005\u0005\u0000\u0000>E\u0005\u0006\u0000"+
		"\u0000?E\u0005\u0004\u0000\u0000@A\u0005\t\u0000\u0000AB\u0003\n\u0005"+
		"\u0000BC\u0005\u0010\u0000\u0000CE\u0001\u0000\u0000\u0000D=\u0001\u0000"+
		"\u0000\u0000D>\u0001\u0000\u0000\u0000D?\u0001\u0000\u0000\u0000D@\u0001"+
		"\u0000\u0000\u0000E\t\u0001\u0000\u0000\u0000FJ\u0003\f\u0006\u0000GJ"+
		"\u0003\u000e\u0007\u0000HJ\u0005\u0011\u0000\u0000IF\u0001\u0000\u0000"+
		"\u0000IG\u0001\u0000\u0000\u0000IH\u0001\u0000\u0000\u0000J\u000b\u0001"+
		"\u0000\u0000\u0000KL\u0005\u0011\u0000\u0000LM\u0005\u0012\u0000\u0000"+
		"MN\u0005\u0011\u0000\u0000N\r\u0001\u0000\u0000\u0000OP\u0005\u0011\u0000"+
		"\u0000PQ\u0005\u0012\u0000\u0000Q\u000f\u0001\u0000\u0000\u0000RY\u0005"+
		"\b\u0000\u0000SY\u0003\u0012\t\u0000TU\u0005\u0001\u0000\u0000UV\u0003"+
		"\u0002\u0001\u0000VW\u0005\u0002\u0000\u0000WY\u0001\u0000\u0000\u0000"+
		"XR\u0001\u0000\u0000\u0000XS\u0001\u0000\u0000\u0000XT\u0001\u0000\u0000"+
		"\u0000Y\u0011\u0001\u0000\u0000\u0000Z^\u0003 \u0010\u0000[^\u0003\u0014"+
		"\n\u0000\\^\u0005\u0007\u0000\u0000]Z\u0001\u0000\u0000\u0000][\u0001"+
		"\u0000\u0000\u0000]\\\u0001\u0000\u0000\u0000^\u0013\u0001\u0000\u0000"+
		"\u0000_`\u0007\u0000\u0000\u0000`a\u0003\u0016\u000b\u0000ab\u0005#\u0000"+
		"\u0000b\u0015\u0001\u0000\u0000\u0000ce\u0003\u0018\f\u0000dc\u0001\u0000"+
		"\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0005"+
		"$\u0000\u0000gh\u0005$\u0000\u0000hs\u0003\u0014\n\u0000ij\u0003\u0018"+
		"\f\u0000jk\u0005$\u0000\u0000kl\u0003\u0014\n\u0000ls\u0001\u0000\u0000"+
		"\u0000mo\u0003\u0018\f\u0000np\u0005$\u0000\u0000on\u0001\u0000\u0000"+
		"\u0000op\u0001\u0000\u0000\u0000ps\u0001\u0000\u0000\u0000qs\u0005$\u0000"+
		"\u0000rd\u0001\u0000\u0000\u0000ri\u0001\u0000\u0000\u0000rm\u0001\u0000"+
		"\u0000\u0000rq\u0001\u0000\u0000\u0000s\u0017\u0001\u0000\u0000\u0000"+
		"tv\u0005$\u0000\u0000ut\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"vy\u0001\u0000\u0000\u0000wz\u0003\u001a\r\u0000xz\u0003 \u0010\u0000"+
		"yw\u0001\u0000\u0000\u0000yx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000"+
		"\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|\u0019\u0001"+
		"\u0000\u0000\u0000}\u0080\u0003\u001c\u000e\u0000~\u0080\u0005%\u0000"+
		"\u0000\u007f}\u0001\u0000\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080"+
		"\u001b\u0001\u0000\u0000\u0000\u0081\u0082\u0003\u001e\u000f\u0000\u0082"+
		"\u0083\u0005$\u0000\u0000\u0083\u0084\u0003\u001e\u000f\u0000\u0084\u001d"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0007\u0001\u0000\u0000\u0086\u001f"+
		"\u0001\u0000\u0000\u0000\u0087\u008e\u0005\n\u0000\u0000\u0088\u008e\u0005"+
		"\u001d\u0000\u0000\u0089\u008e\u0005\u000b\u0000\u0000\u008a\u008e\u0005"+
		"\u001e\u0000\u0000\u008b\u008e\u0003\"\u0011\u0000\u008c\u008e\u0003$"+
		"\u0012\u0000\u008d\u0087\u0001\u0000\u0000\u0000\u008d\u0088\u0001\u0000"+
		"\u0000\u0000\u008d\u0089\u0001\u0000\u0000\u0000\u008d\u008a\u0001\u0000"+
		"\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000"+
		"\u0000\u0000\u008e!\u0001\u0000\u0000\u0000\u008f\u0090\u0007\u0002\u0000"+
		"\u0000\u0090\u0091\u0003&\u0013\u0000\u0091\u0092\u0005\u0013\u0000\u0000"+
		"\u0092#\u0001\u0000\u0000\u0000\u0093\u0094\u0007\u0003\u0000\u0000\u0094"+
		"\u0095\u0003&\u0013\u0000\u0095\u0096\u0005\u0013\u0000\u0000\u0096%\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0007\u0004\u0000\u0000\u0098\'\u0001\u0000"+
		"\u0000\u0000\u000f06;DIX]doruy{\u007f\u008d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}