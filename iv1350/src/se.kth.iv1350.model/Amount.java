package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Represents an amount of money in SEK.
 */
public class Amount {
    private BigDecimal amount;

    public Amount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void add(BigDecimal amountToAdd) { this.amount = this.amount.add(amountToAdd); }
}