package jext.buildtools;

public interface Source extends Named {

    Module getModule();

    Name getRoot();

    String getLanguage();
}
