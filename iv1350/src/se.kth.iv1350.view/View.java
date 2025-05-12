package se.kth.iv1350.view;

import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.InvalidIDException;
import se.kth.iv1350.model.Amount;

/**
 * The <code>view</code> method, which is a hardcoded mockup of the layer that handles the cashiers interaction with the program.
 */

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
        this.contr.addSaleObserver(new TotalRevenueView());
        this.contr.addSaleObserver(new TotalRevenueFileOutput());
    }

    /**
     * Simulates a sale of items. Used for testing.
     */
    public void testRun() {
        Amount amountPaid = new Amount("100");
        contr.startSale();
        SaleSummaryDTO saleSummary = null;
        saleSummary = scanItem(saleSummary, 0);
        saleSummary = scanItem(saleSummary, 0);
        saleSummary = scanItem(saleSummary, 1);
        if (saleSummary != null) {
            Amount amountOfChange = contr.payForSale(amountPaid);
            System.out.println("End Sale:");
            System.out.println("Total cost ( incl VAT ): " + saleSummary.runningTotal().getAmountAsStringWithCurrency());
            System.out.println("Amount paid: " + amountPaid.getAmountAsStringWithCurrency());
            System.out.println("Change: " + amountOfChange.getAmountAsStringWithCurrency());
        }
    }

    private SaleSummaryDTO scanItem(SaleSummaryDTO saleSummary, int itemID) {
        try {
            saleSummary = contr.enterItemID(itemID);
            printSaleSummary(saleSummary);
        }
        catch(InvalidIDException ex) {
            System.out.println(ex.getMessage());
            // Write a new message
        }
        return saleSummary;
    }

    private void printSaleSummary(SaleSummaryDTO saleSummary) {
        System.out.println("Add 1 item with item id " + saleSummary.latestItemID() + ":");
        System.out.println("Item ID: " + saleSummary.latestItemID());
        System.out.println("Item name: " + saleSummary.latestItemName());
        System.out.println("Item cost: " + saleSummary.latestItemPrice().getAmountAsStringWithCurrency());
        System.out.println("VAT: " + saleSummary.latestItemVAT().vatToPercent());
        System.out.println("Item description: " + saleSummary.latestItemDescription());
        System.out.println();
        System.out.println("Total cost (incl VAT): " + saleSummary.runningTotal().getAmountAsStringWithCurrency());
        System.out.println("Total VAT: " + saleSummary.totalVAT().getAmountAsStringWithCurrency());
        System.out.println();
    }
}
