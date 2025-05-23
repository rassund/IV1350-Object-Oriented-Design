package se.kth.iv1350.view;

import se.kth.iv1350.util.FileLogger;

import java.time.ZonedDateTime;

/**
 * Used to print the total income from all sales during one runtime of the program into a created text file with the name "RevenueOutput.txt".
 * Used when a <code>SaleObserver</code> has been notified.
 */
public class TotalRevenueFileOutput extends TotalRevenueOutput {
    private static final String FILE_NAME = "RevenueOutput.txt";
    private static final String ERROR_LOG = "log.txt";
    private final FileLogger logger;


    /**
     * When the total income earned from all sales starts to be recorded, the total income starts off as being "0".
     * Also creates a text file with the name "RevenueOutput.txt", used for logging all times the total income changes.
     */
    public TotalRevenueFileOutput() {
        logger = new FileLogger(FILE_NAME);
    }

    /**
     * Adds the income from a sale that has ended onto the current total income earned from all sales, and writes it into a log file.
     * @param amount The amount of total revenue, or the running total, for the sale that has ended.
     */
    @Override
    protected void doShowTotalIncome() throws Exception {
        logger.log(totalRevenue.getAmountAsStringWithCurrency(), ZonedDateTime.now());
    }

    // DOUBLE CHECK ERROR HANDLING LATER
    @Override
    protected void handleErrors(Exception e) {
        FileLogger errorLog = new FileLogger(ERROR_LOG);
        e.getStackTrace();
        errorLog.log("Error in TotalRevenueFileOutput", ZonedDateTime.now());
    }
}