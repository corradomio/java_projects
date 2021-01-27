package jext.dll.openblas;

public enum OPENBLAS_THREAD {
    SEQUENTIAL(0),
    THREAD(1),
    OPEN_MP(2);

    private final int value;

    OPENBLAS_THREAD(final int value) {
        this.value = value;
    }
}
