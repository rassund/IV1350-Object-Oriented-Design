package se.kth.iv1350.integration;

import se.kth.iv1350.model.Receipt;

/**
 * Used for handling the software for the printer (which prints the receipt).
 */
public class PrinterHandler {
    /**
     * Sends all text to print on the receipt to the printer software, and starts the printing.
     * (Currently, the printing software does not exist. This method therefore now prints a dummy version of the receipt onto the console.)
     * @param receipt Contains all info to be printed onto the receipt.
     */
    public void printReceipt(Receipt receipt) {
        fakePrintReceipt(receipt);
    }

    private void fakePrintReceipt(Receipt receipt) {
        System.out.println("------------------ Begin receipt -------------------");
        System.out.println("Time of Sale: " + receipt.getDateOfSale() + " " + receipt.getTimeOfSale());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Total: " + receipt.getTotalPrice());
        System.out.println("VAT: " + receipt.getTotalVAT());
        System.out.println();
        System.out.println("Cash: " + receipt.getAmountPaid());
        System.out.println("Change: " + receipt.getChange());
        System.out.println("------------------ End receipt -------------------");
        System.out.println();
    }

}