package jext.batch;

public interface State {

    Status getStatus();

    void init();

    void start();

    void abort();

    // --------------------------------------


    void onCreate();

    void onStart();

    void onAbort();

    void onSuccess();

    void onFailed();

    void onDone();

}
