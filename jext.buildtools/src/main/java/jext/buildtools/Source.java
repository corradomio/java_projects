package jext.buildtools;

import jext.buildtools.Module;

public interface Source extends Named {

    Module getModule();

    Name getRoot();
}
