// Generated from regexParser.g4 by ANTLR 4.13.2
package jext.antlr.v4.regexp;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link regexParser}.
 */
public interface regexParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link regexParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(regexParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(regexParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#regExp}.
	 * @param ctx the parse tree
	 */
	void enterRegExp(regexParser.RegExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#regExp}.
	 * @param ctx the parse tree
	 */
	void exitRegExp(regexParser.RegExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(regexParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(regexParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#piece}.
	 * @param ctx the parse tree
	 */
	void enterPiece(regexParser.PieceContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#piece}.
	 * @param ctx the parse tree
	 */
	void exitPiece(regexParser.PieceContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(regexParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(regexParser.QuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#quantity}.
	 * @param ctx the parse tree
	 */
	void enterQuantity(regexParser.QuantityContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#quantity}.
	 * @param ctx the parse tree
	 */
	void exitQuantity(regexParser.QuantityContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#quantRange}.
	 * @param ctx the parse tree
	 */
	void enterQuantRange(regexParser.QuantRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#quantRange}.
	 * @param ctx the parse tree
	 */
	void exitQuantRange(regexParser.QuantRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#quantMin}.
	 * @param ctx the parse tree
	 */
	void enterQuantMin(regexParser.QuantMinContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#quantMin}.
	 * @param ctx the parse tree
	 */
	void exitQuantMin(regexParser.QuantMinContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(regexParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(regexParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charClass}.
	 * @param ctx the parse tree
	 */
	void enterCharClass(regexParser.CharClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charClass}.
	 * @param ctx the parse tree
	 */
	void exitCharClass(regexParser.CharClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charClassExpr}.
	 * @param ctx the parse tree
	 */
	void enterCharClassExpr(regexParser.CharClassExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charClassExpr}.
	 * @param ctx the parse tree
	 */
	void exitCharClassExpr(regexParser.CharClassExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charGroup}.
	 * @param ctx the parse tree
	 */
	void enterCharGroup(regexParser.CharGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charGroup}.
	 * @param ctx the parse tree
	 */
	void exitCharGroup(regexParser.CharGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#posCharGroup}.
	 * @param ctx the parse tree
	 */
	void enterPosCharGroup(regexParser.PosCharGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#posCharGroup}.
	 * @param ctx the parse tree
	 */
	void exitPosCharGroup(regexParser.PosCharGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charRange}.
	 * @param ctx the parse tree
	 */
	void enterCharRange(regexParser.CharRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charRange}.
	 * @param ctx the parse tree
	 */
	void exitCharRange(regexParser.CharRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#seRange}.
	 * @param ctx the parse tree
	 */
	void enterSeRange(regexParser.SeRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#seRange}.
	 * @param ctx the parse tree
	 */
	void exitSeRange(regexParser.SeRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charOrEsc}.
	 * @param ctx the parse tree
	 */
	void enterCharOrEsc(regexParser.CharOrEscContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charOrEsc}.
	 * @param ctx the parse tree
	 */
	void exitCharOrEsc(regexParser.CharOrEscContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charClassEsc}.
	 * @param ctx the parse tree
	 */
	void enterCharClassEsc(regexParser.CharClassEscContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charClassEsc}.
	 * @param ctx the parse tree
	 */
	void exitCharClassEsc(regexParser.CharClassEscContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#catEsc}.
	 * @param ctx the parse tree
	 */
	void enterCatEsc(regexParser.CatEscContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#catEsc}.
	 * @param ctx the parse tree
	 */
	void exitCatEsc(regexParser.CatEscContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#complEsc}.
	 * @param ctx the parse tree
	 */
	void enterComplEsc(regexParser.ComplEscContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#complEsc}.
	 * @param ctx the parse tree
	 */
	void exitComplEsc(regexParser.ComplEscContext ctx);
	/**
	 * Enter a parse tree produced by {@link regexParser#charProp}.
	 * @param ctx the parse tree
	 */
	void enterCharProp(regexParser.CharPropContext ctx);
	/**
	 * Exit a parse tree produced by {@link regexParser#charProp}.
	 * @param ctx the parse tree
	 */
	void exitCharProp(regexParser.CharPropContext ctx);
}