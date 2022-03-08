package org.hls.check;


enum DebitResult {
    OK,
    INSUFFICIENT_BALANCE,
    INVALID_TRANSACTION_ID
}

public class CheckEnum {

    @ Deprecated ( )
    public static void main(String[] args) {
        // AnEnum.values() -> AnEnum[]
        // AnEnum.valueOf(String) -> AnEnum
        System.out.println(String.valueOf(DebitResult.values()));

    }
}
