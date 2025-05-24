package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.view.View;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PrinterHandlerTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private PrinterHandler printerHandler;
    private LocalDateTime localDateTime;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        printerHandler = PrinterHandler.getInstance();
        localDateTime = LocalDateTime.now();
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);

        printerHandler = null;
        localDateTime = null;
    }

    private static String getItemsString(Receipt receipt) {
        StringBuilder itemsStringBuilder = new StringBuilder();
        for (ItemInBasketDTO item : receipt.getItemsBought()) {
            BigDecimal amountOfItemBought = new BigDecimal(item.amountInBasket());
            Amount totalPriceForItem = new Amount(item.price().getAmount().multiply(amountOfItemBought));
            itemsStringBuilder.append(String.format("%-20s %10s x %-5s  %s%n",
                    item.name(),
                    item.price().getAmountAsStringWithCurrency(),
                    amountOfItemBought,
                    totalPriceForItem.getAmountAsStringWithCurrency()));
        }
        return itemsStringBuilder.toString();
    }

    @Test
    void printReceiptNotNull() {
        ArrayList<ItemInBasketDTO> itemInBasketDTOS = new ArrayList<>();
        Amount zero = new Amount("0");
        SaleDTO saleDTO = new SaleDTO(localDateTime, itemInBasketDTOS, zero, zero, zero, zero, zero, zero);
        Receipt receipt = new Receipt(saleDTO);
        printerHandler.printReceipt(receipt);

        String result = outContent.toString();
        assertNotNull(result, "Receipt printout is null");
    }

    @Test
    void printReceiptEmptySale() {
        ArrayList<ItemInBasketDTO> itemInBasketDTOS = new ArrayList<>();
        Amount zero = new Amount("0");
        SaleDTO saleDTO = new SaleDTO(localDateTime, itemInBasketDTOS, zero, zero, zero, zero, zero, zero);
        Receipt receipt = new Receipt(saleDTO);
        printerHandler.printReceipt(receipt);

        String receiptString =
                "------------------ Begin receipt -------------------" +
                System.lineSeparator() +
                "Time of Sale: " + receipt.getDateOfSale() + " " + receipt.getTimeOfSale() +
                System.lineSeparator() +
                System.lineSeparator() +
                System.lineSeparator() +
                String.format("%-20s %29s%n", "Total: ", receipt.getTotalPrice().getAmountAsStringWithCurrency()) +
                String.format("%-20s %29s%n", "VAT: ", receipt.getTotalVAT().getAmountAsStringWithCurrency()) +
                System.lineSeparator() +
                String.format("%-20s %29s%n", "Rounded Total: ",
                        receipt.getRoundedTotalPrice().getAmountAsStringWithCurrency()) +
                System.lineSeparator() +
                String.format("%-20s %29s%n", "Cash: ", receipt.getAmountPaid().getAmountAsStringWithCurrency()) +
                String.format("%-20s %29s%n", "Change: ", receipt.getChange().getAmountAsStringWithCurrency()) +
                "------------------ End receipt -------------------" +
                System.lineSeparator() +
                System.lineSeparator();

        String result = outContent.toString();
        assertTrue(result.contains(receiptString), "Receipt printout for sale with no items not correct");
    }

    @Test
    void printReceiptNoDiscount() {
        Amount amountPayed = new Amount("500");
        Controller controller = new Controller();
        View view = new View(controller);
        controller.startSale();
        try{
            controller.enterItemID(0);
            controller.enterItemID(0);
            controller.enterItemID(2);
        }
        catch (Exception e){
            fail("A exception was thrown during the test \n" +
                    "Error message: " + e.getMessage() + "\n" +
                    "Error stack trace: " + Arrays.toString(e.getStackTrace()));
        }
        SaleDTO saleDTO = controller.payForSale(amountPayed);
        Receipt receipt = new Receipt(saleDTO);
        printerHandler.printReceipt(receipt);

        String itemsString = getItemsString(receipt);

        String receiptString =
                "------------------ Begin receipt -------------------" +
                        System.lineSeparator() +
                        "Time of Sale: " + receipt.getDateOfSale() + " " + receipt.getTimeOfSale() +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        itemsString +
                        System.lineSeparator() +
                        String.format("%-20s %29s%n", "Total: ", receipt.getTotalPrice().getAmountAsStringWithCurrency()) +
                        String.format("%-20s %29s%n", "VAT: ", receipt.getTotalVAT().getAmountAsStringWithCurrency()) +
                        System.lineSeparator() +
                        String.format("%-20s %29s%n", "Rounded Total: ",
                                receipt.getRoundedTotalPrice().getAmountAsStringWithCurrency()) +
                        System.lineSeparator() +
                        String.format("%-20s %29s%n", "Cash: ", receipt.getAmountPaid().getAmountAsStringWithCurrency()) +
                        String.format("%-20s %29s%n", "Change: ", receipt.getChange().getAmountAsStringWithCurrency()) +
                        "------------------ End receipt -------------------" +
                        System.lineSeparator() +
                        System.lineSeparator();

        String result = outContent.toString();
        assertTrue(result.contains(receiptString), "Receipt printout for sale with no items not correct");
    }
}
