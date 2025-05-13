package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;
import se.kth.iv1350.util.FileLogger;

/**
 * Used to print the total income from all sales during one runtime of the program into a created text file with the name "RevenueOutput.txt".
 * Used when a <code>SaleObserver</code> has been notified.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private static final String FILE_NAME = "RevenueOutput.txt";
    private final FileLogger logger;
    private final Amount totalRevenue;

    /**
     * When the total income earned from all sales starts to be recorded, the total income starts off as being "0".
     * Also creates a text file with the name "RevenueOutput.txt", used for logging all times the total income changes.
     */
    public TotalRevenueFileOutput() {
        logger = new FileLogger(FILE_NAME);
        this.totalRevenue = new Amount("0");
    }

    /**
     * Adds the income from a sale that has ended onto the current total income earned from all sales, and writes it into a log file.
     * @param amount The amount of total revenue, or the running total, for the sale that has ended.
     */
    @Override
    public void saleHasEnded(Amount amount) {
        totalRevenue.addToThis(amount);
        logger.log(totalRevenue.getAmountAsStringWithCurrency());
    }
}