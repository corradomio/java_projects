package jext.buildtools;

import java.io.File;

public interface Resource extends Named {

    File getFile();
    String getDigest();
    String getMimeType();
}
