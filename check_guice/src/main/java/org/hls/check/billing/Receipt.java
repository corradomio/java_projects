package org.hls.check.billing;

public class Receipt {
    protected String message;
    protected int amount;

    static Receipt forSuccessfulCharge(int amount) {
        return new SuccesfullReceipt(amount);
    }

    static Receipt forDeclinedCharge(String declineMessage) {
        return new DeclinedReceipt(declineMessage);
    }

    static Receipt forSystemFailure(String message) {
        return new FailureReceipt(message);
    }

    public boolean hasSuccessfulCharge() {
        return true;
    }

    public int getAmountOfCharge() {
        return amount;
    }
}
