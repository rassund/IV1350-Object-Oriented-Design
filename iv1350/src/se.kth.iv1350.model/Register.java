package se.kth.iv1350.model;

/**
 * Used for keeping track of the money in a register while sales are conducted.
 */
public class Register {
    private Amount balance;

    /**
     * Creates a new {@link Register} object representing a register in the store
     * @param balance The {@link Amount} of money in the register at initialization.
     */
    public Register(Amount balance) {
        this.balance = balance;
    }

    /**
     * Gets the {@link Amount} of money in the {@link Register} object.
     * @return {@link Amount} of money in the {@link Register} object.
     */
    public Amount getBalance() {return balance;}

    /**
     * Updates the "<code>balance</code>" of this Register instance by adding the amount "<code>amountToAdd</code>".
     * @param amountToAdd The amount that is added onto the <code>balance</code> for the register.
     */
    public void addAmountToRegister(Amount amountToAdd) {
        balance.addToThis(amountToAdd);
    }
}
