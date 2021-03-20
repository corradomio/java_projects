package jext.math.linalg;

public interface Matrix {

    Dim dim();
    int dim(int idim);

    float  at(int i, int j);
    Matrix at(int i, int j, float v);
    Matrix set(float v);

    Vector dot(Vector v);
    Matrix dot(Matrix m);

    Matrix hprod(Matrix m);

    Matrix linear(float s, float t, Matrix b);
}
