package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @Test
    void printReceiptEmptySale() {
        ArrayList<ItemInBasketDTO> items = new ArrayList<>();
        Amount zero = new Amount("0");
        SaleDTO saleDTO = new SaleDTO(localDateTime, items, zero, zero, zero, zero, zero, zero);
        Receipt receipt = new Receipt(saleDTO);
        printerHandler.printReceipt(receipt);

        StringBuilder receiptStringBuilder = new StringBuilder();
        receiptStringBuilder.append("------------------ Begin receipt -------------------");
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append("Time of Sale: ").append(receipt.getDateOfSale()).append(" ").append(receipt.getTimeOfSale());
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append(String.format("%-20s %29s%n", "Total: ", receipt.getTotalPrice().getAmountAsStringWithCurrency()));
        receiptStringBuilder.append(String.format("%-20s %29s%n", "VAT: " , receipt.getTotalVAT().getAmountAsStringWithCurrency()));
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append(String.format("%-20s %29s%n", "Rounded Total: ", receipt.getRoundedTotalPrice().getAmountAsStringWithCurrency()));
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append(String.format("%-20s %29s%n", "Cash: ", receipt.getAmountPaid().getAmountAsStringWithCurrency()));
        receiptStringBuilder.append(String.format("%-20s %29s%n", "Change: ", receipt.getChange().getAmountAsStringWithCurrency()));
        receiptStringBuilder.append("------------------ End receipt -------------------");
        receiptStringBuilder.append(System.lineSeparator());
        receiptStringBuilder.append(System.lineSeparator());

        String receiptString = receiptStringBuilder.toString();
        String result = outContent.toString();
        assertTrue(result.contains(receiptString), "Receipt printout not correct");
    }
}
