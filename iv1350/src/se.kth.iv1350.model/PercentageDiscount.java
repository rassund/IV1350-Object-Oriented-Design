package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Contains a percentage to be reduced from the total cost of the entire sale.
 */
public class PercentageDiscount implements Discount {
    BigDecimal discountPercentage;

    public PercentageDiscount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * Subtracts "<code>discountPercentage</code>" percent of the running total from the total cost of the given sale.
     * @param sale The <code>Sale</code> object containing the sale to apply the discount to.
     */
    @Override
    public void applyDiscount(Sale sale) {
        Amount runningTotal = sale.getRunningTotal();
        Amount amountToSubtract = new Amount(runningTotal.getAmount().multiply(discountPercentage));
        runningTotal.subtractFromThis(amountToSubtract);
    }
}