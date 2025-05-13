package se.kth.iv1350.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterTest {
    @Test
    void UpdateRegisterTest(){
        Amount amountToAdd = new Amount(new BigDecimal("500"));
        Amount expectedTotal = new Amount(new BigDecimal("500"));

        Register registerForTesting = Register.getInstance();
        registerForTesting.addAmountToRegister(amountToAdd);

        assertEquals(expectedTotal, registerForTesting.getBalance(), "Balance after UpdateRegister() does not become expected result.");
    }
}