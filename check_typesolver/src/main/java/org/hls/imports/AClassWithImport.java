package org.hls.imports;

import org.hls.imports.pkg.AnotherImportedClass;
import java.util.List;
import org.hls.missing.Missing;

public class AClassWithImport {

    public static void main(String[] args) {
        List<String> l = org.hls.imports.pkg.AImportedClass.empty();
        String s = AnotherImportedClass.amethod(100);
    }
}
