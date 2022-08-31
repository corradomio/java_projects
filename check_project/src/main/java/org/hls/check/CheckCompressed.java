package org.hls.check;

import jext.compress.Archives;

import java.io.File;
import java.io.IOException;

public class CheckCompressed {

    public static void main(String[] args) throws IOException {

        //Archives.uncompress(new File(".files/django-4.0.3.tar.gz"), new File(".data/django"));
        //Archives.uncompress(new File(".files/spark-3.2.1.tgz"), new File(".data/spark"));
        //Archives.uncompress(new File(".files/roslyn-4.2.0-4.22266.5.zip"), new File(".data/roslyn"));
        //Archives.uncompress(new File(".files/FSharp.Core.6.0.4.nupkg"), new File(".data/fsharp"));
        Archives.uncompress(new File(".files/networkx-1.9rc1-py2.py3-none-any.whl"), new File(".data/networkx"));

    }
}
