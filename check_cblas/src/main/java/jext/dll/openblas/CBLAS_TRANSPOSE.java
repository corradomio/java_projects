package jext.dll.openblas;

public enum CBLAS_TRANSPOSE {
    CblasNoTrans(111),
    CblasTrans(112),
    CblasConjTrans(113),
    CblasConjNoTrans(114);

    private final int value;

    CBLAS_TRANSPOSE(final int value) {
        this.value = value;
    }
}
