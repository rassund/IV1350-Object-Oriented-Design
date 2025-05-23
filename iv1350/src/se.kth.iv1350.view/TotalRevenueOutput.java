package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

public abstract class TotalRevenueOutput implements SaleObserver {
    protected final Amount totalRevenue;

    public TotalRevenueOutput() {
        this.totalRevenue = new Amount("0");
    }

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

    protected abstract void doShowTotalIncome() throws Exception;

    protected abstract void handleErrors(Exception e);
}
