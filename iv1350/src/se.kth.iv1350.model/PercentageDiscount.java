package se.kth.iv1350.model;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;

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
        BigDecimal amountToSubtractNotRounded = runningTotal.getAmount().multiply(discountPercentage);
        Amount amountToSubtract = new Amount(amountToSubtractNotRounded.setScale(2, HALF_UP));
        sale.getTotalDiscount().subtractFromThis(amountToSubtract);
        runningTotal.subtractFromThis(amountToSubtract);
    }
}
