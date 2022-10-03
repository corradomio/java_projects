package jext.sourcecode.project.csharp.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/*
    https://www.ecma-international.org/publications-and-standards/standards/ecma-335/

    'MZ/0/0'    0x4D 0x5A 0x00 0x00
    'PE/0/0'    0x50 0x45 0x00 0x00
 */

public class DotNetAssemblyUtils {

    private static final byte[] ASSEMBLY_HEADER = {
        0x50, 0x45, 0x00, 0x00
    };

    private static final short[] TARGET_HEADER = new short[]{
        0x0000,
        0x0001,
        0x014c,
        0x014d,
        0x014e,
        0x0160,
        0x0162,
        0x0166,
        0x0168,
        0x0169,
        0x0184,
        0x01A2,
        0x01A3,
        0x01A4,
        0x01A6,
        0x01A8,
        0x01C0,
        0x01C2,
        0x01C4,
        0x01D3,
        0x01F0,
        0x01F1,
        0x01F2,
        0x0200,
        0x0266,
        0x0284,
        0x0366,
        0x0466,
        0x0520,
        0x0CEF,
        0x0EBC,
        0x2000,
        (short) 0x8664,
        (short) 0x9041,
        (short) 0xC0EE,
        (short) 0xAA64,
        (short) 0xFD1D,
    };

    private static boolean checkHeader(byte[] data) {
        // check for 'PE/0/0'
        for(int i=0; i<ASSEMBLY_HEADER.length; ++i)
            if (ASSEMBLY_HEADER[i] != data[i])
                return false;
        return true;
    }

    // private static boolean checkTarget(byte[] data) {
    //     // it seems not necessary
    //     // it is enough to check if it starts with 'PE/0/0'
    //     return true;
    //
    //     // short target = (short) (data[4] | (data[5]<<8));
    //     // for (int i=0; i<TARGET_HEADER.length; ++i)
    //     //     if (TARGET_HEADER[i] == target)
    //     //         return true;
    //     // return false;
    // }

    public static boolean isAssembly(File file) {
        byte[] data = new byte[32];
        try (InputStream stream = Files.newInputStream(file.toPath())) {
            // skip to PE header at byte 128
            stream.skip(128);
            stream.read(data);

            // PE header at byte 128: 'PE/0/0'
            if (!checkHeader(data))
                return false;

            // check for the assembly type
            // Note: it seems not necessary
            // if (!checkTarget(data))
            //     return false;

            return true;
        }
        catch (IOException e) {
            // not important
            return false;
        }
    }
}
