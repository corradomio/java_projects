package org.hls.check;

import jext.sourcecode.project.csharp.util.DotNetNormalizer;

public class CheckDotNet {

    static void print(String s) {
        System.out.println(DotNetNormalizer.normalize(s));
    }

    public static void main(String[] args) {
        print(".NET 6.0");
        print(".NETFramework6.0");
        print(".NETFramework2.0");
        print(".NETFramework4.0-Client");
        print(".NET Framework 4.8.1");
        print(".NETFramework5.0");
        print(".NET Standard 2.0");
    }
}
