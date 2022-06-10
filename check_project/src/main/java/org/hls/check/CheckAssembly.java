package org.hls.check;

import jext.sourcecode.project.csharp.util.DotNetAssemblyUtils;

import java.io.File;

public class CheckAssembly {

    public static void main(String[] args) {
        System.out.println(DotNetAssemblyUtils.isAssembly(new File(
            "D:\\C#\\.NET Core\\6.0.300\\shared\\Microsoft.NETCore.App\\6.0.5\\Microsoft.DiaSymReader.Native.amd64.dll"
        )));

        System.out.println(DotNetAssemblyUtils.isAssembly(new File(
            "C:\\Program Files (x86)\\dotnet\\shared\\Microsoft.NETCore.App\\3.1.25\\System.dll"
        )));
    }
}
