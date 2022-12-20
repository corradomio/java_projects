package jext.metrics;

public enum ValueType {
    NOT_A_NUMBER,
    BOOLEAN,    // {0,1}
    COUNT,      // 0..+inf      in N
    INTEGER,    // -inf..+inf   in Z
    FLOAT,      // -inf..+inf   in R
    PERCENT,    // 0..100       in R

    QUOTA,      // 0..1         in R
    POSITIVE,   // 0..+inf      in R
    ONE_RANGE   // -1..+1       in R
}
