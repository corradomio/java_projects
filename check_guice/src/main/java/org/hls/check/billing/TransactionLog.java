package org.hls.check.billing;

public interface TransactionLog {
    void logChargeResult(ChargeResult result);

    void logConnectException(UnreachableException e);

    boolean wasSuccessLogged();
}
