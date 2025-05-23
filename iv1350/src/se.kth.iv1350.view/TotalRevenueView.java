package se.kth.iv1350.view;

import se.kth.iv1350.util.FileLogger;

import java.time.ZonedDateTime;

/**
 * Used to display the total income from all sales during one runtime of the program to a user.
 */
public class TotalRevenueView extends TotalRevenueOutput {
    private static final String ERROR_LOG = "log.txt";

    /**
     * Adds the income from a sale that has ended onto the current total income earned from all sales, and shows the new total income to the user.
     * @param amount The amount of total revenue, or the running total, for the sale that has ended.
     */
    @Override
    protected void doShowTotalIncome() throws Exception {
        System.out.println("Current total revenue of all sales: " + totalRevenue.getAmountAsStringWithCurrency());
    }

    // DOUBLE CHECK ERROR HANDLING LATER
    @Override
    protected void handleErrors(Exception e) {
        FileLogger errorLog = new FileLogger(ERROR_LOG);
        e.getStackTrace();
        errorLog.log("Error in TotalRevenueView", ZonedDateTime.now());
    }
}
