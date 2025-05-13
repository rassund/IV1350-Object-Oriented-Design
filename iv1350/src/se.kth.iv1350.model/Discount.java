package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * An interface for containing either a <code>SumDiscount</code> or a <code>PercentageDiscount</code> object.
 * Used for handling discounts, using the so-called "Strategy" pattern.
 */
public interface Discount {
    /**
     * Used to apply a discount to the given sale.
     * @param sale The <code>Sale</code> object containing the sale to apply the discount to.
     */
    void applyDiscount(Sale sale);
}