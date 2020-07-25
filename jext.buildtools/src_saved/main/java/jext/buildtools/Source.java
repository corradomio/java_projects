package jext.buildtools;

public interface Source {

    Name getName();

    String getPath();

    Module getModule();

    String getDigest();

    String getLanguage();

    boolean isValid();

}
