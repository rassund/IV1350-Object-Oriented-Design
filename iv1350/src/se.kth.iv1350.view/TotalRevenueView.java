package se.kth.iv1350.view;

import se.kth.iv1350.util.FileLogger;

import java.time.ZonedDateTime;

/**
 * Used to display the total income from all sales during one runtime of the program to a user.
 */
public class TotalRevenueView extends TotalRevenueOutput {
    private static final String ERROR_LOG = "log.txt";

    /**
     * Prints the total income from all sales to System.out.
     * @throws Exception Any exception that might be thrown.
     */
    @Override
    protected void doShowTotalIncome() throws Exception {
        System.out.println("Current total revenue of all sales: " + totalRevenue.getAmountAsStringWithCurrency());
    }

    @Override
    protected void handleErrors(Exception e) {
        FileLogger errorLog = new FileLogger(ERROR_LOG);
        errorLog.log("Error in TotalRevenueView \n" + e.getMessage(), ZonedDateTime.now());
    }
}
