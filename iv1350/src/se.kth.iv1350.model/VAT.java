package se.kth.iv1350.model;

public enum VAT {
    LOW(0.06f),
    MEDIUM(0.12f),
    HIGH(0.25f);

    private final float rate;

    VAT(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }
}