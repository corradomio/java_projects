// Generated from regexParser.g4 by ANTLR 4.13.2
package jext.antlr.v4.regexp;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link regexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface regexParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link regexParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(regexParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#regExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegExp(regexParser.RegExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(regexParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#piece}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPiece(regexParser.PieceContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier(regexParser.QuantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#quantity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantity(regexParser.QuantityContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#quantRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantRange(regexParser.QuantRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#quantMin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantMin(regexParser.QuantMinContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(regexParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharClass(regexParser.CharClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charClassExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharClassExpr(regexParser.CharClassExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharGroup(regexParser.CharGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#posCharGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPosCharGroup(regexParser.PosCharGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharRange(regexParser.CharRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#seRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeRange(regexParser.SeRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charOrEsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharOrEsc(regexParser.CharOrEscContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charClassEsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharClassEsc(regexParser.CharClassEscContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#catEsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatEsc(regexParser.CatEscContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#complEsc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplEsc(regexParser.ComplEscContext ctx);
	/**
	 * Visit a parse tree produced by {@link regexParser#charProp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharProp(regexParser.CharPropContext ctx);
}