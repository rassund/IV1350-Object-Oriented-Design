package se.kth.iv1350.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.VAT;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    AccountingHandler accHandler;
    InventoryHandler invHandler;
    DiscountHandler discHandler;
    PrinterHandler printHandler;
    Amount balance;
    Register register;
    Controller contr;

    @BeforeEach
    void setUp() {
        accHandler = new AccountingHandler();
        invHandler = new InventoryHandler();
        discHandler = new DiscountHandler();
        printHandler = new PrinterHandler();
        balance = new Amount(BigDecimal.ZERO);
        register = new Register(balance);
        contr = new Controller(accHandler, invHandler, discHandler, printHandler, register);
        contr.startSale();
    }

    @AfterEach
    void tearDown() {
        accHandler = null;
        invHandler = null;
        discHandler = null;
        printHandler = null;
        balance = null;
        register = null;
        contr = null;
    }

    @Test
    void enterItemIDNotAlreadyInSale() {
        SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
        Amount returnedPrice = returnedSaleSummaryDTO.getLatestItemAddedPrice();
        String returnedDescription = returnedSaleSummaryDTO.getLatestItemAddedDescription();
        Amount returnedTotal = returnedSaleSummaryDTO.getRunningTotal();

        ItemDTO expectedItemDTO = invHandler.fetchItemDTO(0);
        Amount expectedPrice = expectedItemDTO.getPrice();
        String expectedDescription = expectedItemDTO.getDescription();
        Amount expectedTotal = new Amount(expectedPrice.getAmount().multiply(VAT.LOW.getRateAsDecimal().add(BigDecimal.ONE)));

        assertNotNull(returnedSaleSummaryDTO, "Returned SaleSummaryDTO is null");
        assertEquals(expectedPrice, returnedPrice, "The price of the SaleSummaryDTO does not match the expected price");
        assertEquals(expectedDescription, returnedDescription, "The description of the SaleSummaryDTO does not match the expected description");
        assertEquals(expectedTotal, returnedTotal, "The running total of the SaleSummaryDTO does not match the expected running total");
    }

    @Test
    void enterItemIDAlreadyInSale() {
        SaleSummaryDTO firstSaleSummaryDTO = contr.enterItemID(0);
        Amount expectedPrice = firstSaleSummaryDTO.getLatestItemAddedPrice();
        String expectedDescription = firstSaleSummaryDTO.getLatestItemAddedDescription();
        BigDecimal expectedTotal = firstSaleSummaryDTO.getRunningTotal().getAmount().multiply(BigDecimal.TWO);

        SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
        Amount returnedPrice = returnedSaleSummaryDTO.getLatestItemAddedPrice();
        String returnedDescription = returnedSaleSummaryDTO.getLatestItemAddedDescription();
        BigDecimal returnedTotal = returnedSaleSummaryDTO.getRunningTotal().getAmount();

        assertNotNull(firstSaleSummaryDTO, "First SaleSummaryDTO is null");
        assertNotNull(returnedSaleSummaryDTO, "Returned SaleSummaryDTO is null");
        assertEquals(expectedPrice, returnedPrice, "The price of the SaleSummaryDTO does not match the expected price");
        assertEquals(expectedDescription, returnedDescription, "The description of the SaleSummaryDTO does not match the expected description");
        assertEquals(expectedTotal, returnedTotal, "The running total of the SaleSummaryDTO does not match the expected running total");
    }

    @Test
    void payForSaleChangeCalculation() {
        int testItemID = 0;
        Amount amountPaid = new Amount(new BigDecimal(1000));

        contr.enterItemID(testItemID);
        Amount returnedChange = contr.payForSale(amountPaid);

        Sale expectedSale = new Sale();
        expectedSale.addItem(invHandler.fetchItemDTO(testItemID));
        Amount expectedTotal = expectedSale.getRunningTotal();
        Amount expectedChange = Amount.subtract(amountPaid, expectedTotal);

        assertEquals(expectedChange, returnedChange, "The returned change does not match the expected change.");
    }

    @Test
    void payForSaleExactPayment() {
        Amount returnedChange = contr.payForSale(new Amount(BigDecimal.ZERO));
        Amount expectedChange = new Amount(BigDecimal.ZERO);

        assertEquals(expectedChange, returnedChange, "The returned change does not match the expected change.");
    }
}