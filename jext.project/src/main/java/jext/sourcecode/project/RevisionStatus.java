package jext.sourcecode.project;

public enum RevisionStatus {
    ADDED,      // false/true
    REMOVED,    // true/false
                // missing in the specified revision OR in BTH revisions
    SAME,       // true/true and digest1 == digest2
                // exists  in the specified revision
    CHANGED     // true/true but digest1 != digest2
}
