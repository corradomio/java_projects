package jext.math.linear;

public interface Vector extends VSpace {

    // a' = a/|a|
    Vector versor();

    // outer product
    Matrix outer(Vector v);

    // convert the vector in a diagonal matrix
    Matrix diag();
}
