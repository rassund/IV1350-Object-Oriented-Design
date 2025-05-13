package se.kth.iv1350.model;

import java.math.BigDecimal;

public interface Discount {
    void applyDiscount(Sale sale);
}