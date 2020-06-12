package org.hls.check.billing;

public class ChargeResult {
    public boolean wasSuccessful() {
        return true;
    }

    public String  getDeclineMessage() {
        return "Not enough money";
    }
}
