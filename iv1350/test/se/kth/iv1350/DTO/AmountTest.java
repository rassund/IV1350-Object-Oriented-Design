package se.kth.iv1350.DTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Amount;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    Amount amount;

    @BeforeEach
    void setUp() {
        amount = new Amount(null);
    }

    @AfterEach
    void tearDown() {
        amount = null;
    }

    @Test
    void addPositiveAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToAdd = new Amount(new BigDecimal("5.5"));
        Amount expectedAmount = new Amount(new BigDecimal("10.5"));
        amount.addToThis(amountToAdd);

        assertEquals(amount, expectedAmount, "Amount after addition is not at expected value");
    }

    @Test
    void addNegativeAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToAdd = new Amount(new BigDecimal(-6));
        Amount expectedAmount = new Amount(new BigDecimal(-1));
        amount.addToThis(amountToAdd);

        assertEquals(amount, expectedAmount, "Amount after addition is not at expected value");
    }

    @Test
    void addZero() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToAdd = new Amount(new BigDecimal(0));
        Amount expectedAmount = new Amount(new BigDecimal(5));
        amount.addToThis(amountToAdd);

        assertEquals(amount, expectedAmount, "Amount after addition is not at expected value");
    }

    @Test
    void subtractPositiveAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToSubtract = new Amount(new BigDecimal("5.5"));
        Amount expectedAmount = new Amount(new BigDecimal("-0.5"));
        amount.subtractTwoAmounts(amountToSubtract);

        assertEquals(amount, expectedAmount, "Amount after substraction is not at expected value");
    }

    @Test
    void subtractNegativeAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToSubtract = new Amount(new BigDecimal(-6));
        Amount expectedAmount = new Amount(new BigDecimal(11));
        amount.subtractTwoAmounts(amountToSubtract);

        assertEquals(amount, expectedAmount, "Amount after substraction is not at expected value");
    }

    @Test
    void subtractZero() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToSubtract = new Amount(new BigDecimal(0));
        Amount expectedAmount = new Amount(new BigDecimal(5));
        amount.subtractTwoAmounts(amountToSubtract);

        assertEquals(amount, expectedAmount, "Amount after substraction is not at expected value");
    }

    @Test
    void equalsAmountSameValue() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToCompare = new Amount(new BigDecimal(5));
        boolean booleanToTest = amount.equals(amountToCompare);

        assertTrue(booleanToTest);
    }

    @Test
    void equalsAmountDifferentValue() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToCompare = new Amount(new BigDecimal(3));
        boolean booleanToTest = amount.equals(amountToCompare);

        assertFalse(booleanToTest);
    }

    @Test
    void equalsCompareDifferentType() {
        amount.setAmount(new BigDecimal(5));
        BigDecimal objectToCompare = new BigDecimal(5);
        boolean booleanToTest = amount.equals(objectToCompare);

        assertFalse(booleanToTest);
    }

}
