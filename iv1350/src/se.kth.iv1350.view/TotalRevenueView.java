package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

/**
 * Used to the total income from all sales to a user.
 * Used when a <code>SaleObserver</code> has been notified.
 */
public class TotalRevenueView implements SaleObserver {
    private final Amount totalRevenue;

    /**
     * When the total income earned from all sales starts to be recorded, the total income starts off as being "0".
     */
    public TotalRevenueView() {
        this.totalRevenue = new Amount("0");
    }

    /**
     * Adds the income from a sale that has ended onto the current total income earned from all sales, and shows the new total income to the user.
     * This method is called when a <code>SaleObserver</code> has been notified that a sale has ended.
     * @param amount The amount of total revenue, or the running total, for the sale that has ended.
     */
    @Override
    public void saleHasEnded(Amount amount) {
        totalRevenue.addToThis(amount);
        System.out.println("Current total revenue of all sales: " + totalRevenue.getAmountAsStringWithCurrency());
    }
}
