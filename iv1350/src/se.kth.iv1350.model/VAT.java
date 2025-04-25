package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Contains the three allowed VAT rates for the sale of items.
 * <code>VAT.LOW</code> is for a VAT rate of 6%. <code>VAT.MEDIUM</code> is for 12%. <code>VAT.HIGH</code> is for 25%.
 */
public enum VAT {
    LOW(new BigDecimal("0.06")),
    MEDIUM(new BigDecimal("0.12")),
    HIGH(new BigDecimal("0.25"));

    private final BigDecimal rate;

    VAT(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}