package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

public abstract class TotalRevenueOutput implements SaleObserver {
    protected final Amount totalRevenue;

    /**
     * Creates a new TotalRevenueOutput instance.
     * When the total income earned from all sales starts to be recorded, the total income starts off as being "0".
     */
    public TotalRevenueOutput() {
        this.totalRevenue = new Amount("0");
    }

    /**
     * Adds the income from a sale that has ended onto the current total income earned from all sales and displays it.
     * @param amount The amount of total revenue, or the running total, for the sale that has ended.
     */
    @Override
    public void saleHasEnded(Amount amount) {
        totalRevenue.addToThis(amount);
        showTotalIncome();
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Displays the total income of all sales.
     * @throws Exception Any exception that might be thrown.
     */
    protected abstract void doShowTotalIncome() throws Exception;

    /**
     * Handles any exceptions thrown when trying to display the total income.
     * @param e The exception thrown.
     */
    protected abstract void handleErrors(Exception e);
}
