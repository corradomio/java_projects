package org.hls.check.billing;

public class InMemoryTransactionLog implements TransactionLog {
    public void logChargeResult(ChargeResult result) {

    }

    public void logConnectException(UnreachableException e) {

    }

    @Override
    public boolean wasSuccessLogged() {
        return true;
    }
}
