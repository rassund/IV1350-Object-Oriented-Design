package se.kth.iv1350.model;

/**
 * Used for handling how much money the store receives and gives out as change.
 */
public class Register {
    private Amount balance;

    public Register(Amount balance) {
        this.balance = balance;
    }

    public void updateRegister(Amount totalPrice) {
        balance.add(totalPrice.getAmount());
    }
}