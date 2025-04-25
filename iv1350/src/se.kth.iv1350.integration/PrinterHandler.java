package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.Receipt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Used for handling the software for the printer (which prints the receipt).
 */
public class PrinterHandler {
    public void printReceipt(Receipt receipt) {
        fakePrintReceipt(receipt);
    }

    private void fakePrintReceipt(Receipt receipt) {
        SaleDTO saleDTO = receipt.getSaleDTO();
        System.out.println("------------------ Begin receipt -------------------");
        System.out.println("Time of Sale: " + getDate(saleDTO.getDateTime()) + " " + getTime(saleDTO.getDateTime()));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Total: " + saleDTO.getTotalPrice());
        System.out.println("VAT: " + saleDTO.getTotalVAT());
        System.out.println();
        System.out.println("Cash: " + saleDTO.getAmountPaid());
        System.out.println("Change: " + saleDTO.getChange());
        System.out.println("------------------ End receipt -------------------");
        System.out.println();
    }

    private LocalDate getDate(LocalDateTime dateTime) {
        return dateTime.toLocalDate();
    }

    private LocalTime getTime(LocalDateTime dateTime) {
        return dateTime.toLocalTime().withSecond(0).withNano(0);
    }
}