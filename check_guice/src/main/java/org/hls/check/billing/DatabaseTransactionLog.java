package org.hls.check.billing;

public class DatabaseTransactionLog implements TransactionLog {
    @Override
    public void logChargeResult(ChargeResult result) {

    }

    @Override
    public void logConnectException(UnreachableException e) {

    }

    @Override
    public boolean wasSuccessLogged() {
        return true;
    }
}
