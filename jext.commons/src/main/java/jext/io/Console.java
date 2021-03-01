package jext.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

public class Console {

    private static PrintStream out = System.out;
    private static InputStream in = System.in;
    private static Console self = new Console();

    public static Console printfln(String format, Object... args) {
        out.printf(format, args);
        out.println();
        return self;
    }

    public static Console printfln(int depth, String format, Object... args) {
        spaces(depth);
        out.printf(format, args);
        out.println();
        return self;
    }

    public static Console spaces(int depth) {
        while(depth-- > 0)
            out.print("  ");
        return self;
    }

    public static Console flush() {
        out.flush();
        return self;
    }

    public static Console close() {
        out.close();
        return self;
    }

    public static Console write(int b) {
        out.write(b);
        return self;
    }

    public static Console write(byte[] buf, int off, int len) {
        out.write(buf, off, len);
        return self;
    }

    public static Console print(boolean b) {
        out.print(b);
        return self;
    }

    public static Console print(char c) {
        out.print(c);
        return self;
    }

    public static Console print(int i) {
        out.print(i);
        return self;
    }

    public static Console print(long l) {
        out.print(l);
        return self;
    }

    public static Console print(float f) {
        out.print(f);
        return self;
    }

    public static Console print(double d) {
        out.print(d);
        return self;
    }

    public static Console print(char[] s) {
        out.print(s);
        return self;
    }

    public static Console print(String s) {
        out.print(s);
        return self;
    }

    public static Console print(Object obj) {
        out.print(obj);
        return self;
    }

    public static Console println() {
        out.println();
        return self;
    }

    public static Console println(boolean x) {
        out.println(x);
        return self;
    }

    public static Console println(char x) {
        out.println(x);
        return self;
    }

    public static Console println(int x) {
        out.println(x);
        return self;
    }

    public static Console println(long x) {
        out.println(x);
        return self;
    }

    public static Console println(float x) {
        out.println(x);
        return self;
    }

    public static Console println(double x) {
        out.println(x);
        return self;
    }

    public static Console println(char[] x) {
        out.println(x);
        return self;
    }

    public static Console println(String x) {
        out.println(x);
        return self;
    }

    public static Console println(Object x) {
        out.println(x);
        return self;
    }

    public static Console printf(String format, Object... args) {
        out.printf(format, args);
        return self;
    }

    public static Console printf(Locale l, String format, Object... args) {
        out.printf(l, format, args);
        return self;
    }

    public static Console format(String format, Object... args) {
        out.format(format, args);
        return self;
    }

    public static Console format(Locale l, String format, Object... args) {
        out.format(l, format, args);
        return self;
    }

    public static Console append(CharSequence csq) {
        out.append(csq);
        return self;
    }

    public static Console append(CharSequence csq, int start, int end) {
        out.append(csq, start, end);
        return self;
    }

    public static Console append(char c) {
        out.append(c);
        return self;
    }

    public static Console write(byte[] b) throws IOException {
        out.write(b);
        return self;
    }

    public static void readKey(String message) throws IOException {
        if (message != null && message.length() > 0)
            System.out.println(message);
        in.read();
    }

}
