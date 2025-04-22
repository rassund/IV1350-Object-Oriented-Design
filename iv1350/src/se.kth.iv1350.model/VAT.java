package se.kth.iv1350.model;

import java.math.BigDecimal;

public enum VAT {
    LOW(new BigDecimal("0.06")),
    MEDIUM(new BigDecimal("0.12")),
    HIGH(new BigDecimal("0.25"));

    private final BigDecimal rate;

    VAT(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}