package jext.metrics;

public enum ObjectType {
    UNKNOWN,
    PROJECT,
    // FILE,
    // DIRECTORY,
    MODULE, // alias for DIRECTORY
    SOURCE, // alias for FILE
    TYPE,
    METHOD,
    FIELD,
    NAMESPACE,
    ALL,
    VIRTUAL
}
