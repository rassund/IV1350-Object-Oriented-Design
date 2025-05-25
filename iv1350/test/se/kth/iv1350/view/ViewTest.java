package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.InvalidIDException;
import se.kth.iv1350.model.Amount;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    View view;
    Controller contr;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        contr = new Controller();
        view = new View(contr);
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        contr = null;
        view = null;
    }

    private SaleDTO[] getCopyOfTestRunSaleDTOs() throws InvalidIDException {
        SaleDTO[] saleDTOs = new SaleDTO[2];

        Amount amountPaid = new Amount("100");
        contr.startSale();
        contr.enterItemID(2);
        contr.enterItemID(0);
        SaleDTO firstSale = contr.payForSale(amountPaid);
        contr.startSale();
        contr.enterItemID(1);
        contr.enterItemID(0);
        contr.enterItemID(1);
        contr.applyDiscount(0);
        SaleDTO secondSale = contr.payForSale(amountPaid);
        saleDTOs[0] = firstSale;
        saleDTOs[1] = secondSale;
        return saleDTOs;
    }

    private static String getEndOfSaleSummary(SaleDTO saleDTO){
        return "End Sale:" +
                System.lineSeparator() +
                "Total cost ( incl VAT ): " + saleDTO.roundedTotalPrice().getAmountAsStringWithCurrency() +
                System.lineSeparator() +
                "Amount paid: " + saleDTO.amountPaid().getAmountAsStringWithCurrency() +
                System.lineSeparator() +
                "Change: " + saleDTO.change().getAmountAsStringWithCurrency();
    }

    @Test
    void testRunNotNull() {
        view.testRun();
        assertNotNull(outContent);
    }

    @Test
    void testRunEndOfSaleSummary(){
        SaleDTO[] saleDTOs = null;
        view.testRun();

        try {
            saleDTOs = getCopyOfTestRunSaleDTOs();
        }
        catch (Exception ex) {
            fail("An exception was thrown during the test \n" +
                    "Error message: " + ex.getMessage() + "\n" +
                    "Error stack trace: " + Arrays.toString(ex.getStackTrace()));
        }

        String firstSaleSummary = getEndOfSaleSummary(saleDTOs[0]);
        String secondSaleSummary = getEndOfSaleSummary(saleDTOs[1]);

        String result = outContent.toString();
        assertTrue(result.contains(firstSaleSummary), "First end of sale summary not present or incorrect.");
        assertTrue(result.contains(secondSaleSummary), "Second end of sale summary not present or incorrect.");
    }
}
