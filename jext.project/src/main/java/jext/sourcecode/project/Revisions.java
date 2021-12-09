package jext.sourcecode.project;

/**
 * Way to specify a revision:
 *
 *  - null|[]       the last revision
 *  - int | [int]   the specified revision
 *  - [int,int]     the specified 2 revisions
 *
 *  The 'int' value can be:
 *
 *  - -2:   an alternative way to specify all revisions
 *  - -1:   the latest revision
 *  - 0,1,...: the specified revision
 */
public interface Revisions {

    int CURRENT_REVISION  = -1;     // as input value
    // int PREVIOUS_REVISION = -2;  // as input value
    int ALL_REVISIONS     = -3;     // as input values
    int NO_REVISION = -1;           // as return value

}
