package org.hls.check;

import jext.compress.Archives;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Archives.uncompress(new File("D:\\SPLGroup\\repos\\.nuget\\xunit\\2.4.1\\xunit.2.4.1.nupkg"),
                    new File("D:\\SPLGroup\\repos\\.nuget\\xunit\\2.4.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
