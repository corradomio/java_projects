package jext.sourcecode.project.csharp.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DotNetAssemblyUtils {

    private static final byte[] ASSEMBLY_HEADER = {
        0x50, 0x45, 0x00, 0x00,0x4C, 0x01
    };

    public static boolean isAssembly(File file) {
        byte[] data = new byte[15];
        try (InputStream stream = Files.newInputStream(file.toPath())) {
            stream.skip(128);
            stream.read(data);

            for (int i=0; i<ASSEMBLY_HEADER.length; ++i)
                if (ASSEMBLY_HEADER[i] != data[i])
                    return false;

            return true;
        }
        catch (IOException e) {
            // not important
            return false;
        }
    }
}
