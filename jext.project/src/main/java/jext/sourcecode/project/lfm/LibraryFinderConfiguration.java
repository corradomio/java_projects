package jext.sourcecode.project.lfm;

import jext.sourcecode.project.LibraryFinder;

public interface LibraryFinderConfiguration {

    String getLanguage();

    LibraryFinder createFinder();

}
