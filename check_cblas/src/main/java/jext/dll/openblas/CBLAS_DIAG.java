package jext.dll.openblas;

public enum CBLAS_DIAG {
    CblasNonUnit(131),
    CblasUnit(132);

    private final int value;

    CBLAS_DIAG(final int value) {
        this.value = value;
    }
}
