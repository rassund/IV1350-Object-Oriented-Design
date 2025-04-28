package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Represents an amount of money in SEK.
 */
public class Amount {
    private BigDecimal amount;
    private String currency;

    public Amount(BigDecimal amount) {
        this.amount = amount;
        this.currency = "SEK";
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Adds the amount <code>amountToAdd</code> onto the amount that calls this method.
     * Uses the <code>BigDecimal</code> "add" method.
     * @param amountToAdd Contains what to add to the amount that calls this method.
     */
    public void addToThis(Amount amountToAdd) { this.amount = this.amount.add(amountToAdd.getAmount()); }

    /**
     * Subtracts the amount <code>amountToSubtract</code> from the amount that calls this method.
     * @param amountToSubtract Contains what to subtract from the amount that calls this method.
     */
    public void subtractFromThis(Amount amountToSubtract) {this.amount = this.amount.subtract(amountToSubtract.getAmount());}

    /**
     * Subtracts one amount with another and returns the difference.
     * @param amountToSubtractFrom The <code>Amount</code> that gets reduced.
     * @param amountToSubtract The <code>Amount</code> that subtracts from <code>amountToSubtractFrom</code>.
     * @return What remains after subtraction: <code>amountToSubtractFrom</code> - <code>amountToSubtract</code>.
     */
    public static Amount subtractTwoAmounts(Amount amountToSubtractFrom, Amount amountToSubtract) {
        BigDecimal newAmount = amountToSubtractFrom.getAmount().subtract(amountToSubtract.getAmount());
        return new Amount(newAmount);
    }

    /**
     * Checks if a given object is the same object as the <code>Amount</code> object that calls this method.
     * @param obj The object which may or may not be equal to the <code>Amount</code> object that calls this method.
     * @return Returns "true" if the given object is the same as the <code>Amount</code> object that calls this method. Otherwise, returns "false".
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Amount otherAmount) {
            return this.amount.equals((otherAmount.getAmount()));
        }
        return false;
    }

    /**
     * Gets a string containing the amount/price of this Amount instance, together with the currency used for this Amount instance.
     * @return A string containing the <code>amount</code> value of this Amount instance, together with the <code>currency</code> value.
     */
    public String getAmountAsStringWithCurrency() {
        return this.amount + " " + this.currency;
    }
}
