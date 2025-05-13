package se.kth.iv1350.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CompositeDiscount implements Discount {
    private final ArrayList<Discount> discountAlgorithms = new ArrayList<>();

    @Override
    public void applyDiscount(Sale sale) {
        for (Discount disc : discountAlgorithms) {
            disc.applyDiscount(sale);
        }
    }

    public void addDiscount(Discount disc) {
        discountAlgorithms.add(disc);
    }
}