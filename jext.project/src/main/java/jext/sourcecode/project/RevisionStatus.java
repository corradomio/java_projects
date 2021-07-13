package jext.sourcecode.project;

public enum RevisionStatus {
    MISSING,    // false/false
    ADDED,      // false/true
    DELETED,    // true/false
    SAME,       // true/true and digest1 == digest2
    CHANGED     // true/true but digest1 != digest2
}
