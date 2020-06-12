package org.hls.check.billing;

public class DeclinedReceipt extends Receipt {
    public DeclinedReceipt(String declineMessage) {
        this.message = declineMessage;
    }
}
