package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.CompositeDiscount;
import se.kth.iv1350.model.Discount;
import se.kth.iv1350.model.PercentageDiscount;
import se.kth.iv1350.model.SumDiscount;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Used for handling the database used for getting discounts to be applied to a sale.
 * Contains temporary test code since there is no discount database right now.
 */
public class DiscountHandler {
    private static final DiscountHandler INSTANCE = new DiscountHandler();

    private DiscountHandler() {}

    /**
     * @return The (only) Singleton instance of this <code>DiscountHandler</code> class.
     */
    public static DiscountHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Retrieves all discounts that a specified customer is eligible for.
     * Currently, ony placeholder/simulated discounts are retrieved, since no discount database is present.
     * @param customerID The identifier used to specify the customer that asks for any applicable discount(s).
     * @param saleDTO Containing information about the sale the discount(s) are to be applied to.
     * @return A <code>CompositeDiscount</code> instance, containing any discounts the customer may be eligible for.
     */
    public Discount fetchDiscount(int customerID, SaleDTO saleDTO) {
        return getFakeDiscounts(customerID, saleDTO);
    }

    private Discount getFakeDiscounts(int customerID, SaleDTO saleDTO) {
        CompositeDiscount disc = new CompositeDiscount();

        BigDecimal itemSumToDeduct = new BigDecimal("0");
        ArrayList<ItemInBasketDTO> itemsInSale = saleDTO.items();
        for(ItemInBasketDTO item : itemsInSale){
            if (item.itemID() == 1){
                itemSumToDeduct = itemSumToDeduct.add(BigDecimal.valueOf(2));
            }
        }
        disc.addDiscount(new SumDiscount(itemSumToDeduct));

        BigDecimal runningTotalSumToDeduct = new BigDecimal("5");
        BigDecimal minimumTotalSumToExceed = new BigDecimal("45");
        if (saleDTO.totalPrice().getAmount().compareTo(minimumTotalSumToExceed) >= 0){
            disc.addDiscount(new SumDiscount(runningTotalSumToDeduct));
        }

        BigDecimal percentageToReduce = new BigDecimal("0.2");
        if (customerID == 0){
            disc.addDiscount(new PercentageDiscount(percentageToReduce));
        }
        return disc;
    }
}