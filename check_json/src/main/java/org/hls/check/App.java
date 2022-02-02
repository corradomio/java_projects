package org.hls.check;

import jext.util.LongHash;

public class App {

    public static void main(String[] args) {
        for(int i=0; i<32; ++i)
            System.out.print(LongHash.toString(i));

    }

}
