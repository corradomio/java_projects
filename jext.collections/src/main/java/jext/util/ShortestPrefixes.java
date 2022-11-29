package jext.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ShortestPrefixes extends HashSet<String> {

    @Override
    public boolean addAll(Collection<? extends String> c) {
        boolean[] added = new boolean[1];
        c.forEach(e -> {
            added[0] |= this.add(e);
        });
        return added[0];
    }


    @Override
    public boolean add(String s) {
        boolean toadd = true;
        Iterator<String> it = this.iterator();
        while(it.hasNext()) {
            String t = it.next();
            if (s.startsWith(t)) {
                toadd = false;
                break;
            }
            if (t.startsWith(s)) {
                it.remove();
            }
        }
        if (toadd)
            return super.add(s);
        else
            return false;
    }

    // @Override
    // protected Set<String> getContent() {
    //     return new HashSet<>();
    // }
}
