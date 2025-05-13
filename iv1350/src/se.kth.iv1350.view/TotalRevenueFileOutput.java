package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;
import se.kth.iv1350.util.FileLogger;

public class TotalRevenueFileOutput implements SaleObserver {
    private static final String FILE_NAME = "RevenueOutput.txt";
    private final FileLogger logger;
    private final Amount totalRevenue;

    public TotalRevenueFileOutput() {
        logger = new FileLogger(FILE_NAME);
        this.totalRevenue = new Amount("0");
    }

    @Override
    public void saleHasEnded(Amount amount) {
        totalRevenue.addToThis(amount);
        logger.log(totalRevenue.getAmountAsStringWithCurrency());
    }
}