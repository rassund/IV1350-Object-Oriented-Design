package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.PrinterHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterTest {
    Register registerForTesting = Register.getInstance();

    @BeforeEach
    void setUp() {
        Amount zero = new Amount("0");
        registerForTesting.setAmountInRegister(zero);
    }

    @AfterEach
    void tearDown() {
        Amount zero = new Amount("0");
        registerForTesting.setAmountInRegister(zero);
    }

    @Test
    void updateRegisterTest(){
        Amount amountToAdd = new Amount(new BigDecimal("500"));
        Amount expectedTotal = new Amount(new BigDecimal("500"));

        registerForTesting.addAmountToRegister(amountToAdd);

        assertEquals(expectedTotal.getAmount(), registerForTesting.getBalance().getAmount(), "Balance after UpdateRegister() does not become expected result.");
    }
    @Test
    void setRegisterTest(){
        Amount amountToSet = new Amount(new BigDecimal("500"));
        Amount expectedTotal = new Amount(new BigDecimal("500"));

        registerForTesting.setAmountInRegister(amountToSet);

        assertEquals(expectedTotal.getAmount(), registerForTesting.getBalance().getAmount(), "Balance after UpdateRegister() does not become expected result.");
    }

}