package jext.sourcecode.project.util;

import jext.sourcecode.project.Library;
import jext.sourcecode.project.LibraryStatus;

public class LibraryInfo {

    public int count;
    public int upgradeable;
    public int obsolete;
    public int valid;
    public int invalid;

    public LibraryInfo() {

    }

    public void add(Library library) {
        count += 1;

        LibraryStatus status = library.getLibraryStatus();
        switch (status) {
            case OBSOLETE:
                obsolete += 1;
                break;
            case VALID:
                valid += 1;
                break;
            case UPGRADEABLE:
                upgradeable += 1;
                break;
            case NOTEXISTENT:
                invalid += 1;
                break;
            default:
                valid += 1;
        }
    }

}
