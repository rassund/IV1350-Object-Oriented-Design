package se.kth.iv1350.model;

/**
 * Used for handling how much money the store receives and gives out as change.
 */
public class Register {
    private Amount balance;

    public Register(Amount balance) {
        this.balance = balance;
    }

    /**
     * Updates the "<code>balance</code>" of this Register instance by adding the amount "<code>totalPrice</code>".
     * @param totalPrice The amount that is added onto the <code>balance</code> for the register.
     */
    public void updateRegister(Amount totalPrice) {
        balance.addToThis(totalPrice);
    }
}