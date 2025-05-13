package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Contains a sum to be reduced from the total cost of the entire sale.
 */
public class SumDiscount implements Discount {
    BigDecimal discountAmount;

    public SumDiscount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Subtracts "<code>discountAmount</code>", as a sum, from the total cost of the given sale.
     * @param sale The <code>Sale</code> object containing the sale to apply the discount to.
     */
    @Override
    public void applyDiscount(Sale sale) {
        sale.getRunningTotal().subtractFromThis(new Amount(discountAmount));
    }
}