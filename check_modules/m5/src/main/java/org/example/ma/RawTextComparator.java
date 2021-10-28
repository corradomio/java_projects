package org.example.ma;

import static org.example.mb.RawCharUtil.isWhitespace;
import static org.example.mb.RawCharUtil.trimLeadingWhitespace;


public class RawTextComparator {
    public static final RawTextComparator DEFAULT = new RawTextComparator() {

    };
    public static final RawTextComparator WS_IGNORE_ALL = new RawTextComparator() {
        @Override
        public boolean equals(Object obj) {
            isWhitespace();
            trimLeadingWhitespace();
            return false;
        }
    };
}
