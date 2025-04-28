package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VATTest {
    VAT vatLow;
    VAT vatMedium;
    VAT vatHigh;

    @BeforeEach
    void setUp() {
        vatLow = VAT.LOW;
        vatMedium = VAT.MEDIUM;
        vatHigh = VAT.HIGH;
    }

    @AfterEach
    void tearDown() {
        vatLow = null;
        vatMedium = null;
        vatHigh = null;
    }

    @Test
    void correctLowPercentageIsShown(){
        String vatPercentageLow = vatLow.vatToPercent();
        String expectedResultLow = "6.00%";

        assertEquals(expectedResultLow, vatPercentageLow, "vatToPercent() does not give the expected percentage for VAT.LOW (6.00%)");
    }

    @Test
    void correctMediumPercentageIsShown(){
        String vatPercentageMedium = vatMedium.vatToPercent();
        String expectedResultMedium = "12.00%";

        assertEquals(expectedResultMedium, vatPercentageMedium, "vatToPercent() does not give the expected percentage for VAT.MEDIUM (12.00%)");
    }

    @Test
    void correctHighPercentageIsShown(){
        String vatPercentageHigh = vatHigh.vatToPercent();
        String expectedResultHigh = "25.00%";

        assertEquals(expectedResultHigh, vatPercentageHigh, "vatToPercent() does not give the expected percentage for VAT.HIGH (25.00%)");
    }
}