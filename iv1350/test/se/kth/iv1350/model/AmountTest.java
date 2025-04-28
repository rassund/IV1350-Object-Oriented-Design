package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertEquals(expectedAmount, amount, "Amount after addition is not at expected value");
    }

    @Test
    void addNegativeAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToAdd = new Amount(new BigDecimal(-6));
        Amount expectedAmount = new Amount(new BigDecimal(-1));
        amount.addToThis(amountToAdd);

        assertEquals(expectedAmount, amount, "Amount after addition is not at expected value");
    }

    @Test
    void addZero() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToAdd = new Amount(new BigDecimal(0));
        Amount expectedAmount = new Amount(new BigDecimal(5));
        amount.addToThis(amountToAdd);

        assertEquals(expectedAmount, amount, "Amount after addition is not at expected value");
    }

    @Test
    void subtractPositiveAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToSubtract = new Amount(new BigDecimal("5.5"));
        Amount expectedAmount = new Amount(new BigDecimal("-0.5"));
        amount.subtractFromThis(amountToSubtract);

        assertEquals(expectedAmount, amount, "Amount after subtraction is not at expected value");
    }

    @Test
    void subtractNegativeAmount() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToSubtract = new Amount(new BigDecimal(-6));
        Amount expectedAmount = new Amount(new BigDecimal(11));
        amount.subtractFromThis(amountToSubtract);

        assertEquals(expectedAmount, amount, "Amount after subtraction is not at expected value");
    }

    @Test
    void subtractZero() {
        amount.setAmount(new BigDecimal(5));
        Amount amountToSubtract = new Amount(new BigDecimal(0));
        Amount expectedAmount = new Amount(new BigDecimal(5));
        amount.subtractFromThis(amountToSubtract);

        assertEquals(expectedAmount, amount, "Amount after subtraction is not at expected value");
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

    @Test
    void subtractTwoAmountsTest() {
        Amount amountToSubtractFrom = new Amount(new BigDecimal("1000"));
        Amount amountToSubtractWith = new Amount(new BigDecimal("500"));
        Amount expectedAmount = new Amount(new BigDecimal("500"));

        Amount amountAfterSubtraction = Amount.subtractTwoAmounts(amountToSubtractFrom, amountToSubtractWith);

        assertEquals(amountAfterSubtraction, expectedAmount, "subtractTwoAmount() does not result in an expected Amount.");
    }

    @Test
    void correctCurrencyAndPriceIsPrinted(){
        amount.setAmount(new BigDecimal("1000"));
        String expectedResult = "1000 SEK";

        assertEquals(expectedResult, amount.getAmountAsStringWithCurrency(), "printCurrencyWithPrice() does not output expected string.");
    }

}
