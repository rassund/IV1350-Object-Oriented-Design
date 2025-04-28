package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Contains the three allowed VAT rates for the sale of items.
 *
 * <p><code>VAT.LOW</code> is for a VAT rate of 6%.</p>
 * <p><code>VAT.MEDIUM</code> is for 12%.</p>
 * <p><code>VAT.HIGH</code> is for 25%.</p>
 */
public enum VAT {
    LOW(new BigDecimal("0.06")),
    MEDIUM(new BigDecimal("0.12")),
    HIGH(new BigDecimal("0.25"));

    private final BigDecimal rate;

    VAT(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * Gets VAT rate as a {@link BigDecimal} decimal value, useful calculations with VAT rates.
     * @return VAT rate as a {@link BigDecimal} decimal value.
     */
    public BigDecimal getRateAsDecimal() { return rate; }

    /**
     * Converts the VAT rate ("LOW", "MEDIUM" or "HIGH") into actual percentages.
     * @return A string showing a percentage value.
     */
    public String vatToPercent() {
        return rate.multiply(new BigDecimal(100)) + "%";
    }
}
