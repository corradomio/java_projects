package jext.dll.openblas;

public enum CBLAS_SIDE {
    CblasLeft(141),
    CblasRight(142);

    private final int value;

    CBLAS_SIDE(final int value) {
        this.value = value;
    }
}
