package se.kth.iv1350.model;

import java.util.ArrayList;

/**
 * Contains all discounts retrieved for a specific customer.
 * When a customer asks to include the discounts they are eligible for this sale,
 * those discounts are gathered together in the instance of this (composite) class and is then applied one after another to the sale being specified.
 */
public class CompositeDiscount implements Discount {
    private final ArrayList<Discount> discountAlgorithms = new ArrayList<>();

    /**
     * Applies all discounts gathered onto the running total of the given <code>Sale</code> object.
     * @param sale The sale that the discounts are to be applied to.
     */
    @Override
    public void applyDiscount(Sale sale) {
        for (Discount disc : discountAlgorithms) {
            disc.applyDiscount(sale);
        }
    }

    /**
     * Adds a discount onto the list of discounts contained for the current sale.
     * @param disc The <code>Discount</code> object, containing the discount to be applied to the sale.
     */
    public void addDiscount(Discount disc) {
        discountAlgorithms.add(disc);
    }
}