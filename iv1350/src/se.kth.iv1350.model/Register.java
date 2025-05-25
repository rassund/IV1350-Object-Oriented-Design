package se.kth.iv1350.model;

import se.kth.iv1350.integration.AccountingHandler;

/**
 * Used for keeping track of the money in a register while sales are conducted.
 */
public class Register {
    private static final String STARTING_BALANCE = "0";
    private static final Register INSTANCE = new Register(new Amount(STARTING_BALANCE));
    private final Amount balance;

    /**
     * Creates a new {@link Register} object representing a register in the store
     * @param balance The {@link Amount} of money in the register at initialization.
     */
    private Register(Amount balance) {
        this.balance = balance;
    }

    public static Register getInstance() {
        return INSTANCE;
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

    /**
     * Updates the "<code>balance</code>" of this Register instance by setting the amount "<code>amountToSet</code>" as the new balance.
     * @param amountToSet The amount that is the new <code>balance</code> for the register.
     */
    public void setAmountInRegister(Amount amountToSet)  { balance.setAmount(amountToSet.getAmount()); };
}