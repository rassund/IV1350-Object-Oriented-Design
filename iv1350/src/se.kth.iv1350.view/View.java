package se.kth.iv1350.view;

import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;

import java.math.BigDecimal;

/**
 * The <code>view</code> method, which is a hardcoded mockup of the layer that handles the cashiers interaction with the program.
 */

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void testRun() {
        contr.startSale();
        SaleSummaryDTO saleSummary;
        saleSummary = contr.enterItemID(0);
        printSaleSummary(saleSummary);
        saleSummary = contr.enterItemID(0);
        printSaleSummary(saleSummary);
        saleSummary = contr.enterItemID(1);
        printSaleSummary(saleSummary);
        Amount amountOfChange = contr.payForSale(new Amount(new BigDecimal(100)));
    }

    private void printSaleSummary(SaleSummaryDTO saleSummary) {
        System.out.println("Add 1 item with item id " + saleSummary.getLatestItemID() + ":");
        System.out.println("Item ID: " + saleSummary.getLatestItemID());
        System.out.println("Item name: " + saleSummary.getLatestItemName());
        System.out.println("Item cost: " + saleSummary.getLatestItemAddedPrice());
        System.out.println("VAT: " + saleSummary.getLatestItemVAT().vatToPercent());
        System.out.println("Item description: " + saleSummary.getLatestItemAddedDescription());
        System.out.println();
        System.out.println("Total cost (incl VAT): " + saleSummary.getRunningTotal());
        System.out.println("Total VAT: " + saleSummary.getTotalVAT());
        System.out.println();
    }
}