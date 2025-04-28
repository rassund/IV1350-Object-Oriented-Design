package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VATTest {
    VAT testVATLow;
    VAT testVATMedium;
    VAT testVATHigh;

    @BeforeEach
    void setUp() {
        testVATLow = VAT.LOW;
        testVATMedium = VAT.MEDIUM;
        testVATHigh = VAT.HIGH;
    }

    @AfterEach
    void tearDown() {
        testVATLow = null;
        testVATMedium = null;
        testVATHigh = null;
    }

    @Test
    void vatToPercent() {
    }
}