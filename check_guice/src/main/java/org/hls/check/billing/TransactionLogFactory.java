package org.hls.check.billing;

public class TransactionLogFactory {

    private static TransactionLog instance;

    public static void setInstance(TransactionLog transactionLog) {
        instance = transactionLog;
    }
}
