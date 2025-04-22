package se.kth.iv1350.model;
/**
 * Represents an amount of money in SEK.
 */
public class Amount {
    private float amount;

    public Amount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}