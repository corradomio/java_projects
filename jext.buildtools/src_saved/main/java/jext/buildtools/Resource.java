package jext.buildtools;

public interface Resource {

    Name getName();

    String getPath();

    Module getModule();

    String getDigest();

    String getMimeType();

}
