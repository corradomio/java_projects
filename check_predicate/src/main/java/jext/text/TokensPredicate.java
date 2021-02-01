package jext.text;

import com.sun.org.apache.xpath.internal.operations.And;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/*
     T1|T2|...  T1;T2;...
     T1,T2,...
     !T
     (exp)
 */

public class TokensPredicate implements Predicate<List<String>> {

    public static Predicate<List<String>> parse(String expr) {
        return new TokensPredicate().parseExpr(expr);
    }

    private static class Lex {

        private static String SYNTAX = "!,;|()";

        private String text;
        private int at;
        private int len;
        private int last;

        Lex(String text) {
            this.text = text;
            this.at = 0;
            this.len = text.length();
        }

        String next() {
            spaces();
            if (isSyntax())
                return syntax();
            else
                return token();
        }

        boolean eoe() {
            return at >= len;
        }

        void back() {
            at = last;
        }

        private boolean isSyntax() {
            if (at >= len)
                return true;
            else
                return SYNTAX.indexOf(text.charAt(at)) != -1;
        }

        private String syntax() {
            last = at;
            if (at >= len)
                return "\0";
            else
                return text.substring(at++, at);
        }

        private String token() {
            int bgn = at;
            last = at;
            while(!isSyntax())
                at++;
            return text.substring(bgn, at);
        }

        private void spaces() {
            while (at < len && text.charAt(at) == ' ')
                at++;
        }
    }

    private static class AndPredicate implements Predicate<List<String>> {

        private List<Predicate<List<String>>> preds = new ArrayList<>();

        void add(Predicate<List<String>> pred) {
            preds.add(pred);
        }

        boolean isSingle() { return preds.size() == 1; }
        Predicate<List<String>> get() {
            return preds.get(0);
        }

        @Override
        public boolean test(List<String> s) {
            for(Predicate<List<String>> pred : preds)
                if (!pred.test(s))
                    return false;
            return true;
        }

        static boolean isAnd(String token) {
            return ",".equals(token);
        }
    }

    private static class OrPredicate implements Predicate<List<String>> {

        private List<Predicate<List<String>>> preds = new ArrayList<>();

        void add(Predicate<List<String>> pred) {
            preds.add(pred);
        }

        boolean isSingle() { return preds.size() == 1; }
        Predicate<List<String>> get() {
            return preds.get(0);
        }

        @Override
        public boolean test(List<String> s) {
            for(Predicate<List<String>> pred : preds)
                if (pred.test(s))
                    return true;
            return false;
        }

        static boolean isOr(String token) {
            return ";|".contains(token);
        }

    }

    private static class NotPredicate implements Predicate<List<String>> {

        private Predicate<List<String>> pred;

        NotPredicate(Predicate<List<String>> pred) {
            this.pred = pred;
        }

        @Override
        public boolean test(List<String> s) {
            return !this.pred.test(s);
        }

        static boolean isNot(String token) {
            return "~!".contains(token);
        }

    }

    private static class ExprPredicate implements Predicate<List<String>> {

        private Predicate<List<String>> pred;

        ExprPredicate(Predicate<List<String>> pred) {
            this.pred = pred;
        }

        @Override
        public boolean test(List<String> s) {
            return !this.pred.test(s);
        }

        static boolean isExpr(String token) {
            return "()".contains(token);
        }

    }

    private static class EqualsPredicate implements Predicate<List<String>> {
        private String text;

        EqualsPredicate(String text) {
            this.text = text;
        }

        @Override
        public boolean test(List<String> s) {
            for (String t : s)
                if (this.text.equals(t))
                    return true;
            return false;
        }
    }

    private static class RegexPredicate implements Predicate<List<String>> {
        private Pattern pattern;

        RegexPredicate(String regex) {
            regex = regex
                .replace("?", ".")
                .replace("*", ".*");
            this.pattern = Pattern.compile(regex);
        }

        @Override
        public boolean test(List<String> s) {
            for (String t : s)
                if (this.pattern.matcher(t).matches())
                    return true;
            return false;
        }

        static boolean isRegex(String s) {
            return s.contains("*") || s.contains("?");
        }
    }

    private Predicate<List<String>> pred;

    @Override
    public boolean test(List<String> s) {
        return this.pred.test(s);
    }

    private Lex lex;

    private Predicate<List<String>> parseExpr(String expr) {
        lex = new Lex(expr);
        this.pred = expr();
        return this.pred;
    }

    private Predicate<List<String>> expr() {

        OrPredicate or = new OrPredicate();

        while(!lex.eoe()) {
            Predicate<List<String>> pred = andExpr();
            or.add(pred);
            String token = lex.next();
            if (!OrPredicate.isOr(token)) {
                lex.back();
                break;
            }
        }

        if (or.isSingle())
            return or.get();
        else
            return or;
    }

    private Predicate<List<String>> andExpr() {
        AndPredicate and = new AndPredicate();

        while(!lex.eoe()) {
            Predicate<List<String>> pred = notExp();
            and.add(pred);
            String token = lex.next();
            if (!AndPredicate.isAnd(token)) {
                lex.back();
                break;
            }

        }

        if (and.isSingle())
            return and.get();
        else
            return and;
    }

    private Predicate<List<String>> notExp() {
        String token = lex.next();
        if (NotPredicate.isNot(token)) {
            Predicate<List<String>> pred = expr();
            return new NotPredicate(pred);
        }
        if (ExprPredicate.isExpr(token)) {
            Predicate<List<String>> pred = expr();
            token = lex.next();
            if(!ExprPredicate.isExpr(token))
                lex.back();
            return pred;
        }
        if (RegexPredicate.isRegex(token))
            return new RegexPredicate(token);
        else
            return new EqualsPredicate(token);
    }

}
