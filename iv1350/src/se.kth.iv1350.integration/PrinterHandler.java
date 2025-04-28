package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;

import java.math.BigDecimal;

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

        for (ItemInBasketDTO item : receipt.getItemsBought()) {
            BigDecimal amountOfItemBought = new BigDecimal(item.amountInBasket());
            Amount totalPriceForItem = new Amount(item.price().getAmount().multiply(amountOfItemBought));
            System.out.printf("%-20s %10s x %-5s  %s%n",
                    item.name(),
                    item.price().getAmountAsStringWithCurrency(),
                    amountOfItemBought,
                    totalPriceForItem.getAmountAsStringWithCurrency());
        }
        System.out.println();

        System.out.printf("%-20s %29s%n", "Total:", receipt.getTotalPrice().getAmountAsStringWithCurrency());
        System.out.printf("%-20s %29s%n", "VAT: " , receipt.getTotalVAT().getAmountAsStringWithCurrency());
        System.out.println();

        System.out.printf("%-20s %29s%n", "Cash: ", receipt.getAmountPaid().getAmountAsStringWithCurrency());
        System.out.printf("%-20s %29s%n", "Change: ", receipt.getChange().getAmountAsStringWithCurrency());

        System.out.println("------------------ End receipt -------------------");
        System.out.println();
    }

}
