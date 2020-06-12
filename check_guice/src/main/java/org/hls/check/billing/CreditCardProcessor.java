package org.hls.check.billing;

interface CreditCardProcessor {

    ChargeResult charge(CreditCard creditCard, int amount) throws UnreachableException;

    CreditCard getCardOfOnlyCharge();

    int getAmountOfOnlyCharge();
}
