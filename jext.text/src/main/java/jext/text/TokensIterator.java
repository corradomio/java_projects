package jext.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class TokensIterator implements Iterator<String>, AutoCloseable {

    private boolean owner;
    private Reader reader;
    private String token;
    private int last = 0;

    public TokensIterator(File file) throws FileNotFoundException {
        this.reader = new FileReader(file);
        this.owner = true;
        findToken();
    }

    public TokensIterator(Reader reader) {
        this.reader = reader;
        findToken();
    }

    @Override
    public boolean hasNext() {
        return token != null;
    }

    @Override
    public String next() {
        String token = this.token;
        findToken();
        return token;
    }

    // 0: skip chars
    // 1: [lower]+
    // 2: [upper]+[lower]*
    // 3: make token
    // 4: end
    private void findToken() {
        StringBuilder sb = new StringBuilder();
        int state = 0, ch;
        try {
            if (isLower(last)) {
                state = 1;
                sb.append((char)last);
                last = 0;
            }
            if (isUpper(last)) {
                state = 2;
                sb.append((char)last);
                last = 0;
            }

            while(state != 3) {
                ch = reader.read();
                if (ch == -1) break;
                char chr = (char) ch;
                switch(state) {
                    case 0:
                        if (isLower(ch)) {
                            state = 1;
                            sb.append((char)ch);
                            break;
                        }
                        if (isUpper(ch)) {
                            state = 2;
                            sb.append((char)ch);
                            break;
                        }
                        break;
                    case 1:
                        if (isLower(ch)) {
                            sb.append((char)ch);
                            break;
                        }
                        if (isUpper(ch)) {
                            last = ch;
                            state = 3;
                            break;
                        }
                        else {
                            last = 0;
                            state = 3;
                            break;
                        }
                    case 2:
                        if (isUpper(ch)) {
                            sb.append((char)ch);
                            break;
                        }
                        if (isLower(ch)) {
                            sb.append((char)ch);
                            state = 1;
                            break;
                        }
                        else {
                            last = 0;
                            state = 3;
                            break;
                        }
                }
            }
        }
        catch(IOException e) {

        }
        this.token = sb.length() > 0 ? sb.toString() : null;
    }

    @Override
    public void close() throws Exception {
        if (owner && reader != null)
            reader.close();
    }

    private static boolean isLower(int ch) { return 'a' <= ch && ch <= 'z'; }
    private static boolean isUpper(int ch) { return 'A' <= ch && ch <= 'Z'; }

}
