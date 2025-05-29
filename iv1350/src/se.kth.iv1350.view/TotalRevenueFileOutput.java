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
     * Creates a new instance of TotalRevenueFileOutput and a text file with the name "RevenueOutput.txt", used for logging all times the total income changes.
     */
    public TotalRevenueFileOutput() {
        logger = new FileLogger(FILE_NAME);
    }

    /**
     * Writes the current total revenue of all sales to the log.
     * @throws Exception Any exception that might be thrown.
     */
    @Override
    protected void doShowTotalIncome() throws Exception {
        logger.log(totalRevenue.getAmountAsStringWithCurrency(), ZonedDateTime.now());
    }

    @Override
    protected void handleErrors(Exception e) {
        FileLogger errorLog = new FileLogger(ERROR_LOG);
        e.getStackTrace();
        errorLog.log("Error in TotalRevenueFileOutput", ZonedDateTime.now());
    }
}