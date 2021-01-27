package jext.dll.openblas;

public enum CBLAS_ORDER  {
    CblasRowMajor(101),
    CblasColMajor(102);

    private final int value;

    CBLAS_ORDER(final int value) {
        this.value = value;
    }
}
