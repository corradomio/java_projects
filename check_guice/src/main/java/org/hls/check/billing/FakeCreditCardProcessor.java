package org.hls.check.billing;

public class FakeCreditCardProcessor implements CreditCardProcessor {

    private CreditCard creditCard;
    private int amount;

    public ChargeResult charge(CreditCard creditCard, int amount) throws UnreachableException {
        this.creditCard = creditCard;
        this.amount = amount;
        return new ChargeResult();
    }

    @Override
    public CreditCard getCardOfOnlyCharge() {
        return creditCard;
    }

    @Override
    public int getAmountOfOnlyCharge() {
        return amount;
    }
}
