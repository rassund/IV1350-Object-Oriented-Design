package se.kth.iv1350.model;

import java.math.BigDecimal;

public class PercentageDiscount implements Discount {
    BigDecimal discountPercentage;

    public PercentageDiscount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void applyDiscount(Sale sale) {
        Amount runningTotal = sale.getRunningTotal();
        Amount amountToSubtract = new Amount(runningTotal.getAmount().multiply(discountPercentage));
        runningTotal.subtractFromThis(amountToSubtract);
    }
}