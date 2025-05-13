package se.kth.iv1350.model;

import java.math.BigDecimal;

public class SumDiscount implements Discount {
    BigDecimal discountAmount;

    public SumDiscount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public void applyDiscount(Sale sale) {
        sale.getRunningTotal().subtractFromThis(new Amount(discountAmount));
    }
}