package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.InvalidIDException;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.model.Amount;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueOutputTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private View view;
    private Controller contr;
    private InventoryHandler invHandler;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        contr = new Controller();
        view = new View(contr);
        invHandler = InventoryHandler.getInstance();
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        contr = null;
        view = null;
        invHandler = null;
    }

    @Test
    void totalRevenueViewGivesCorrectOutput() throws InvalidIDException {
        contr.startSale();
        SaleSummaryDTO saleSummary = contr.enterItemID(0);
        Amount amountPaid = new Amount(saleSummary.roundedRunningTotal().getAmount());
        contr.payForSale(amountPaid);

        String result = outContent.toString();
        assertTrue(result.contains("Current total revenue of all sales: " + amountPaid.getAmount()), "TotalRevenueView sale observer prints wrong total revenue: " + amountPaid.getAmount());
    }
}