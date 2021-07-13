package jext.sourcecode.project;

public interface Revisions {

    String REV = "rev";
    int CURRENT_REVISION = -1;
    int ALL_REVISIONS = -2;

    int getCurrentRevision();

}
