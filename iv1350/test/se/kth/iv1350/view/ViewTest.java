package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
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
    private View view;
    private Controller contr;

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

    private static String getDuringSaleSummary(SaleSummaryDTO saleSummary){
        return  System.lineSeparator() +
                "Add 1 item with item id " + saleSummary.latestItemID() + ":" +
                System.lineSeparator() +
                "Item ID: " + saleSummary.latestItemID() +
                System.lineSeparator() +
                "Item name: " + saleSummary.latestItemName() +
                System.lineSeparator() +
                "Item cost: " + saleSummary.latestItemPrice().getAmountAsStringWithCurrency() +
                System.lineSeparator() +
                "VAT: " + saleSummary.latestItemVAT().vatToPercent() +
                System.lineSeparator() +
                "Item description: " + saleSummary.latestItemDescription() +
                System.lineSeparator() +
                System.lineSeparator() +
                "Total cost (incl VAT): " + saleSummary.runningTotal().getAmountAsStringWithCurrency() +
                System.lineSeparator() +
                "Total VAT: " + saleSummary.totalVAT().getAmountAsStringWithCurrency() +
                System.lineSeparator();
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
        String result = outContent.toString();
        assertNotNull(result);
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

    @Test
    void testRunExceptionMessages(){
        view.testRun();
        contr.startSale();
        String databaseExceptionString = "Unmodified database exception string";
        String invalidIDExceptionString = "Unmodified invalid ID exception string";
        try {
            contr.enterItemID(-1);
        }
        catch (Exception e) {
            databaseExceptionString = e.getMessage();
        }
        try {
            contr.enterItemID(100);
        }
        catch (Exception e) {
            invalidIDExceptionString = e.getMessage();
        }

        String result = outContent.toString();
        assertFalse(result.contains("Unmodified database exception string"), "Database exception not thrown during test run.");
        assertTrue(result.contains(databaseExceptionString), "Correct database exception string not found in output.");
        assertFalse(result.contains("Unmodified invalid ID exception string"), "Invalid ID exception not thrown during test run.");
        assertTrue(result.contains(invalidIDExceptionString), "Correct invalid ID exception string not found in output.");
    }

    @Test
    void testRunItemSummaries(){
        view.testRun();
        SaleSummaryDTO sale1Item1DTO = null;
        SaleSummaryDTO sale1item2DTO = null;
        SaleSummaryDTO sale2Item1DTO = null;
        SaleSummaryDTO sale2item2DTO = null;
        SaleSummaryDTO sale2item3DTO = null;
        contr.startSale();
        try {
            sale1Item1DTO = contr.enterItemID(2);
            sale1item2DTO = contr.enterItemID(0);
        }
        catch (Exception e) {
            fail("An exception was thrown during the first sale \n" +
                    "Error message: " + e.getMessage() + "\n" +
                    "Error stack trace: " + Arrays.toString(e.getStackTrace()));
        }
        contr.payForSale(new Amount("500"));
        contr.startSale();
        try {
            sale2Item1DTO = contr.enterItemID(1);
            sale2item2DTO = contr.enterItemID(0);
            sale2item3DTO = contr.enterItemID(1);
        }
        catch (Exception e) {
            fail("An exception was thrown during the second sale \n" +
                    "Error message: " + e.getMessage() + "\n" +
                    "Error stack trace: " + Arrays.toString(e.getStackTrace()));
        }

        String sale1item1String = getDuringSaleSummary(sale1Item1DTO);
        String sale1item2String = getDuringSaleSummary(sale1item2DTO);
        String sale2item1String = getDuringSaleSummary(sale2Item1DTO);
        String sale2item2String = getDuringSaleSummary(sale2item2DTO);
        String sale2item3String = getDuringSaleSummary(sale2item3DTO);

        String result = outContent.toString();
        assertTrue(result.contains(sale1item1String), "Printout doesn't contain summary from sale 1 item 1.");
        assertTrue(result.contains(sale1item2String), "Printout doesn't contain summary from sale 1 item 2.");
        assertTrue(result.contains(sale2item1String), "Printout doesn't contain summary from sale 2 item 1.");
        assertTrue(result.contains(sale2item2String), "Printout doesn't contain summary from sale 2 item 2.");
        assertTrue(result.contains(sale2item3String), "Printout doesn't contain summary from sale 2 item 3.");
    }

    @Test
    void testRunCompleteDuringSalePrintouts(){
        view.testRun();
        SaleSummaryDTO sale1Item1DTO = null;
        SaleSummaryDTO sale1item2DTO = null;
        SaleSummaryDTO sale2Item1DTO = null;
        SaleSummaryDTO sale2item2DTO = null;
        SaleSummaryDTO sale2item3DTO = null;
        contr.startSale();
        try {
            sale1Item1DTO = contr.enterItemID(2);
            sale1item2DTO = contr.enterItemID(0);
        }
        catch (Exception e) {
            fail("An exception was thrown during the first sale \n" +
                    "Error message: " + e.getMessage() + "\n" +
                    "Error stack trace: " + Arrays.toString(e.getStackTrace()));
        }
        contr.payForSale(new Amount("500"));
        contr.startSale();
        try {
            sale2Item1DTO = contr.enterItemID(1);
            sale2item2DTO = contr.enterItemID(0);
            sale2item3DTO = contr.enterItemID(1);
        }
        catch (Exception e) {
            fail("An exception was thrown during the second sale \n" +
                    "Error message: " + e.getMessage() + "\n" +
                    "Error stack trace: " + Arrays.toString(e.getStackTrace()));
        }
        contr.payForSale(new Amount("500"));


        String databaseExceptionString = "Unmodified database exception string";
        String invalidIDExceptionString = "Unmodified invalid ID exception string";
        contr.startSale();
        try {
            contr.enterItemID(-1);
        }
        catch (Exception e) {
            databaseExceptionString = e.getMessage();
        }
        try {
            contr.enterItemID(100);
        }
        catch (Exception e) {
            invalidIDExceptionString = e.getMessage();
        }

        String sale1String =
                System.lineSeparator() + getDuringSaleSummary(sale1Item1DTO) + getDuringSaleSummary(sale1item2DTO);
        String sale2String =
                System.lineSeparator() + databaseExceptionString +
                getDuringSaleSummary(sale2Item1DTO) + getDuringSaleSummary(sale2item2DTO) +
                System.lineSeparator() + invalidIDExceptionString + getDuringSaleSummary(sale2item3DTO);

        String result = outContent.toString();
        assertTrue(result.contains(sale1String), "Printout during the first sale not correct.");
        assertTrue(result.contains(sale2String), "Printout during the second sale not correct.");
    }
}
