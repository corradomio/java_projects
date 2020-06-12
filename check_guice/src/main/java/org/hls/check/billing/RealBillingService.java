package org.hls.check.billing;

import com.google.inject.Inject;

// public class RealBillingService implements BillingService {
//     public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
//         CreditCardProcessor processor = new PaypalCreditCardProcessor();
//         TransactionLog transactionLog = new DatabaseTransactionLog();
//
//         try {
//             ChargeResult result = processor.charge(creditCard, order.getAmount());
//             transactionLog.logChargeResult(result);
//
//             return result.wasSuccessful()
//                     ? Receipt.forSuccessfulCharge(order.getAmount())
//                     : Receipt.forDeclinedCharge(result.getDeclineMessage());
//         } catch (UnreachableException e) {
//             transactionLog.logConnectException(e);
//             return Receipt.forSystemFailure(e.getMessage());
//         }
//     }
// }
public class RealBillingService implements BillingService {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Inject
    public RealBillingService(CreditCardProcessor processor,
                              TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
        try {
            ChargeResult result = processor.charge(creditCard, order.getAmount());
            transactionLog.logChargeResult(result);

            return result.wasSuccessful()
                    ? Receipt.forSuccessfulCharge(order.getAmount())
                    : Receipt.forDeclinedCharge(result.getDeclineMessage());
        } catch (UnreachableException e) {
            transactionLog.logConnectException(e);
            return Receipt.forSystemFailure(e.getMessage());
        }
    }
}