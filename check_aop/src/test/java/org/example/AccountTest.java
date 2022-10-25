package org.example;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AccountTest {
    private Account account;

    // @Before
    public void before() {
        account = new Account();
    }

    @Test
    public void given20AndMin10_whenWithdraw5_thenSuccess() {
        assertTrue(account.withdraw(5));
    }

    @Test
    public void given20AndMin10_whenWithdraw100_thenFail() {
        assertFalse(account.withdraw(100));
    }
}