package se.kth.iv1350.view;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.DatabaseException;
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
        SaleSummaryDTO saleSummary = null;
        SaleDTO sale;
        contr.startSale();
        System.out.println();
        System.out.println();
        saleSummary = scanItem(saleSummary, 2);
        saleSummary = scanItem(saleSummary, 0);
        contr.applyDiscount(1);
        if (saleSummary != null) {
            sale = contr.payForSale(amountPaid);
            printEndOfSaleInfo(sale);
        }

        contr.startSale();
        saleSummary = null;
        System.out.println();
        System.out.println();
        saleSummary = scanItem(saleSummary, -1);
        saleSummary = scanItem(saleSummary, 1);
        saleSummary = scanItem(saleSummary, 0);
        saleSummary = scanItem(saleSummary, 100);
        saleSummary = scanItem(saleSummary, 1);
        contr.applyDiscount(0);
        if (saleSummary != null) {
            sale = contr.payForSale(amountPaid);
            printEndOfSaleInfo(sale);
        }
    }

    private SaleSummaryDTO scanItem(SaleSummaryDTO saleSummary, int itemID) {
        try {
            saleSummary = contr.enterItemID(itemID);
            printSaleSummary(saleSummary);
        }
        catch (InvalidIDException | DatabaseException ex) {
            System.out.println(ex.getMessage());
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

    private void printEndOfSaleInfo(SaleDTO saleDTO){
        System.out.println("End Sale:");
        System.out.println("Total cost ( incl VAT ): " + saleDTO.roundedTotalPrice().getAmountAsStringWithCurrency());
        System.out.println("Amount paid: " + saleDTO.amountPaid().getAmountAsStringWithCurrency());
        System.out.println("Change: " + saleDTO.change().getAmountAsStringWithCurrency());
    }
}
