package se.kth.iv1350.model;

import java.math.BigDecimal;

/**
 * Represents an amount of money in SEK.
 */
public class Amount {
    private BigDecimal amount;
    private final String currency;

    /**
     * Creates an {@link Amount} based on a {@link String}.
     * @param amount A {@link String} representing the {@link Amount} to be created.
     */
    public Amount(String amount) {
        this.amount = new BigDecimal(amount);
        this.currency = "SEK";
    }

    /**
     * Creates an {@link Amount} based on a {@link BigDecimal}.
     * @param amount A {@link BigDecimal} representing the {@link Amount} to be created.
     */
    public Amount(BigDecimal amount) {
        this.amount = amount;
        this.currency = "SEK";
    }

    /**
     * Creates an {@link Amount} based on a {@link Amount}.
     *
     * <p>This is useful when coping an amount.</p>
     * @param amount An {@link Amount} representing the {@link Amount} to be created.
     */
    public Amount(Amount amount) {
        this.amount = amount.getAmount();
        this.currency = amount.getCurrency();
    }

    /**
     * Gets the amount from an {@link Amount}.
     * @return The amount from an {@link Amount} as a {@link BigDecimal}.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Changes the value of the amount in an {@link Amount} object.
     * @param amount The new value of the amount in an {@link Amount} object as a {@link BigDecimal}.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the currency for an {@link Amount}.
     * @return A {@link String} representing a currency.
     */
    public String getCurrency() { return currency; }

    /**
     * Adds the amount <code>amountToAdd</code> onto the amount that calls this method.
     * @param amountToAdd Contains what to add to the amount that calls this method.
     */
    public void addToThis(Amount amountToAdd) { this.amount = this.amount.add(amountToAdd.getAmount()); }

    /**
     * Subtracts the amount <code>amountToSubtract</code> from the amount that calls this method.
     * @param amountToSubtract What to subtract from the amount that calls this method.
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
            return this.amount.compareTo((otherAmount.getAmount())) == 0;
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
