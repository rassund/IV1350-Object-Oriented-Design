package se.kth.iv1350.view;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.SaleObserver;

public class TotalRevenueView implements SaleObserver {
    private final Amount totalRevenue;

    public TotalRevenueView() {
        this.totalRevenue = new Amount("0");
    }

    @Override
    public void saleHasEnded(Amount amount) {
        totalRevenue.addToThis(amount);
        System.out.println("Current total revenue of all sales: " + totalRevenue.getAmountAsStringWithCurrency());
    }
}
