package se.kth.iv1350.view;

import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;

/**
 * The <code>view</code> method, which is a hardcoded mockup of the layer that handles the cashiers interaction with the program.
 */

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a sale of items. Used for testing.
     */
    public void testRun() {
        contr.startSale();
        SaleSummaryDTO saleSummary;
        saleSummary = contr.enterItemID(0);
        printSaleSummary(saleSummary);
        saleSummary = contr.enterItemID(0);
        printSaleSummary(saleSummary);
        saleSummary = contr.enterItemID(1);
        printSaleSummary(saleSummary);
        Amount amountOfChange = contr.payForSale(new Amount("100"));
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
