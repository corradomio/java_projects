package jext.dll.openblas;

public enum CBLAS_UPLO {
    CblasUpper(121),
    CblasLower(122);

    private final int value;

    CBLAS_UPLO(final int value) {
        this.value = value;
    }
}
