package se.kth.iv1350.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Sale;

import java.lang.reflect.Field;
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
        invHandler = InventoryHandler.getInstance();
        discHandler = new DiscountHandler();
        printHandler = new PrinterHandler();
        balance = new Amount(BigDecimal.ZERO);
        register = new Register(balance);
        contr = new Controller(accHandler, discHandler, printHandler, register);
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
    void enterItemIDNotAlreadyInSaleNotNull() {
        try {
            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);

            assertNotNull(returnedSaleSummaryDTO, "Returned SaleSummaryDTO is null");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDNotAlreadyInSaleItemPrice() {
        try {
            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
            Amount returnedPrice = returnedSaleSummaryDTO.latestItemPrice();

            ItemDTO expectedItemDTO = invHandler.fetchItemDTO(0);
            Amount expectedPrice = expectedItemDTO.price();

            assertEquals(expectedPrice, returnedPrice, "The price of the SaleSummaryDTO does not match the expected price");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDNotAlreadyInSaleItemDescription() {
        try {
            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
            String returnedDescription = returnedSaleSummaryDTO.latestItemDescription();

            ItemDTO expectedItemDTO = invHandler.fetchItemDTO(0);
            String expectedDescription = expectedItemDTO.description();

            assertEquals(expectedDescription, returnedDescription, "The description of the SaleSummaryDTO does not match the expected description");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDNotAlreadyInSaleTotalPrice() {
        try {
            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
            Amount returnedTotal = returnedSaleSummaryDTO.runningTotal();

            ItemDTO expectedItemDTO = invHandler.fetchItemDTO(0);
            Amount expectedPrice = expectedItemDTO.price();
            Amount expectedTotal = new Amount(expectedPrice.getAmount());

            assertEquals(expectedTotal, returnedTotal, "The running total of the SaleSummaryDTO does not match the expected running total");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDAlreadyInSaleNotNull() {
        try {
            SaleSummaryDTO firstSaleSummaryDTO = contr.enterItemID(0);

            assertNotNull(firstSaleSummaryDTO, "First SaleSummaryDTO is null");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDAlreadyInSaleItemPrice() {
        try {
            SaleSummaryDTO firstSaleSummaryDTO = contr.enterItemID(0);
            Amount expectedPrice = firstSaleSummaryDTO.latestItemPrice();

            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
            Amount returnedPrice = returnedSaleSummaryDTO.latestItemPrice();

            assertEquals(expectedPrice, returnedPrice, "The price of the SaleSummaryDTO does not match the expected price");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDAlreadyInSaleItemDescription() {
        try {
            SaleSummaryDTO firstSaleSummaryDTO = contr.enterItemID(0);
            String expectedDescription = firstSaleSummaryDTO.latestItemDescription();

            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
            String returnedDescription = returnedSaleSummaryDTO.latestItemDescription();

            assertEquals(expectedDescription, returnedDescription, "The description of the SaleSummaryDTO does not match the expected description");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void enterItemIDAlreadyInSaleTotalPrice() {
        try {
            SaleSummaryDTO firstSaleSummaryDTO = contr.enterItemID(0);
            BigDecimal expectedTotal = firstSaleSummaryDTO.runningTotal().getAmount().multiply(BigDecimal.TWO);

            SaleSummaryDTO returnedSaleSummaryDTO = contr.enterItemID(0);
            BigDecimal returnedTotal = returnedSaleSummaryDTO.runningTotal().getAmount();

            assertEquals(expectedTotal, returnedTotal, "The running total of the SaleSummaryDTO does not match the expected running total");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }




    @Test
    void payForSaleChangeCalculation() {
        try {
            int testItemID = 0;
            Amount amountPaid = new Amount("1000");

            contr.enterItemID(testItemID);
            Amount returnedChange = contr.payForSale(amountPaid);

            Sale expectedSale = new Sale();
            ItemInBasketDTO itemToAdd = new ItemInBasketDTO(invHandler.fetchItemDTO(testItemID), 1);
            expectedSale.addItem(itemToAdd);
            Amount expectedTotal = expectedSale.getRunningTotal();
            Amount expectedChange = Amount.subtractTwoAmounts(amountPaid, expectedTotal);

            assertEquals(expectedChange, returnedChange, "The returned change does not match the expected change.");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void payForSaleExactPayment() {
        try {
            int testItemID = 0;
            ItemDTO testItemDTO = invHandler.fetchItemDTO(testItemID);
            Amount itemPrice = testItemDTO.price();
            Amount expectedChange = new Amount("0");

            contr.enterItemID(testItemID);
            Amount returnedChange = contr.payForSale(itemPrice);

            assertEquals(expectedChange, returnedChange, "The returned change should be 0 but isn't.");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void payForSaleNoItemsInSale() {
        Amount amountPaid = new Amount("1000");
        Amount returnedChange = contr.payForSale(amountPaid);

        assertEquals(amountPaid, returnedChange, "The returned change does not match the expected change.");
    }

    @Test
    void payForSaleSaleEqualsNull() {
        try {
            int testItemID = 0;
            ItemDTO testItemDTO = invHandler.fetchItemDTO(testItemID);
            Amount itemPrice = testItemDTO.price();

            contr.enterItemID(testItemID);
            Amount returnedChange = contr.payForSale(itemPrice);

            Field saleField = contr.getClass().getDeclaredField("sale");
            saleField.setAccessible(true);
            Object saleToTest = saleField.get(contr);

            assertNull(saleToTest, "The ended sale isn't null.");
        } catch (Exception e) {
            fail("Unexpected exception was thrown: " + e.getMessage());
        }
    }
}