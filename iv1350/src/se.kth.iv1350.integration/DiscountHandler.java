package se.kth.iv1350.integration;

import se.kth.iv1350.model.CompositeDiscount;
import se.kth.iv1350.model.Discount;
import se.kth.iv1350.model.PercentageDiscount;
import se.kth.iv1350.model.SumDiscount;

import java.math.BigDecimal;

/**
 * Used for handling the database used for getting discounts for different users/customers.
 * Contains temporary test code since there is no discount database right now.
 */
public class DiscountHandler {
    private static final DiscountHandler INSTANCE = new DiscountHandler();

    private DiscountHandler() {}

    /**
     * @return The only Singleton instance of this <code>DiscountHandler</code> class.
     */
    public static DiscountHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Retreives all discounts that a specified customer is eligible for.
     * Currently, ony placeholder/simulated discounts are retreived, since no discount database is present.
     * @param customerID The identifier used to specify the customer that asks for (a) discount(s).
     * @return A <code>CompositeDiscount</code> instance, containing all discounts the customer is eligible for.
     */
    public Discount fetchDiscount(int customerID) {
        return getFakeDiscounts();
    }

    private Discount getFakeDiscounts() {
        CompositeDiscount disc = new CompositeDiscount();
        disc.addDiscount(new SumDiscount(new BigDecimal("5")));
        disc.addDiscount(new PercentageDiscount(new BigDecimal("0.2")));
        return disc;
    }
}