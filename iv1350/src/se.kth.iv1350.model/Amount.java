package se.kth.iv1350.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    public void add(Amount amountToAdd) { this.amount = this.amount.add(amountToAdd.getAmount()); }

    public void subtract(Amount amountToSubtract) {this.amount = this.amount.subtract(amountToSubtract.getAmount());}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Amount otherAmount) {
            return this.amount.equals((otherAmount.getAmount()));
        }
        return false;
    }
}
