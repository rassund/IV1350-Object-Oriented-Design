package se.kth.iv1350.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterTest {
    @Test
    void UpdateRegisterTest(){
        Amount balanceForTesting = new Amount(new BigDecimal("1000"));
        Amount amountToAdd = new Amount(new BigDecimal("500"));
        Amount expectedTotal = new Amount(new BigDecimal("1500"));

        Register registerForTesting = new Register(balanceForTesting);
        registerForTesting.addAmountToRegister(amountToAdd);

        assertEquals(expectedTotal, registerForTesting.getBalance(), "Balance after UpdateRegister() does not become expected result.");
    }
}