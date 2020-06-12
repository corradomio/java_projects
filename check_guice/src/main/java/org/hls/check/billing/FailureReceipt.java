package org.hls.check.billing;

public class FailureReceipt extends Receipt {
    public FailureReceipt(String message) {
        this.message = message;
    }
}
