package jext.math.linear.sparse;

import jext.math.linear.BaseSpace;
import jext.math.linear.Dim;
import jext.math.linear.Type;

import java.util.Iterator;

public class BaseSparse extends BaseSpace implements Iterable<Loc> {

    public Data data;

    // @Override
    public int length() {
        return data.length();
    }

    public Iterator<Loc> iterator() {
        return data.iterator();
    }

}
