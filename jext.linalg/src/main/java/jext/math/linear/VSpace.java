package jext.math.linear;

public interface VSpace {

    /// Object type: DENSE, SPARSE, DIAGONAL
    Type type();

    /// Dimensions
    Dim dim();
    /// Dimension on the selected axis
    int dim(int idim);
    /// number of elements in the object
    int length();
}
