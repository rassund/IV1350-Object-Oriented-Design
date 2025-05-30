package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Amount;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private TotalRevenueView revenueView;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        revenueView = new TotalRevenueView();
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        revenueView = null;
    }

    @Test
    void saleHasEndedCorrectOutput() {
        Amount testAmount = new Amount("5");
        revenueView.saleHasEnded(testAmount);

        String result = outContent.toString();
        assertTrue(result.contains(testAmount.getAmountAsStringWithCurrency()), "TotalRevenueView does not print the correct revenue: " + result);
    }
}