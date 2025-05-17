package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Records all relevant information at the end of a sale.
 * @param dateTime The date and time of the sale.
 * @param items A list of the purchased items.
 * @param totalPrice The total price of the sale (including VAT).
 * @param roundedTotalPrice The total price rounded to the nearest swedish crown, needed for cash payments.
 * @param totalVAT The total amount of VAT applied to the sale.
 * @param amountPaid The amount paid by the customer for the sale.
 * @param change The amount of change to return to the customer.
 */
public record SaleDTO(LocalDateTime dateTime, ArrayList<ItemInBasketDTO> items, Amount totalPrice, Amount roundedTotalPrice,
                      Amount totalVAT, Amount amountPaid, Amount change) {

    /**
     * Creates a new {@link SaleDTO} instance with defensive copying for mutable fields.
     * @param dateTime The date and time of the sale.
     * @param items A list of the purchased items.
     * @param totalPrice The total price of the sale (including VAT).
     * @param roundedTotalPrice The total price rounded to the nearest swedish crown, needed for cash payments.
     * @param totalVAT The total amount of VAT applied to the sale.
     * @param amountPaid The amount paid by the customer for the sale.
     * @param change The change to return to the customer.
     */
    public SaleDTO {
        items = new ArrayList<>(items);
        totalPrice = new Amount(totalPrice);
        roundedTotalPrice = new Amount(roundedTotalPrice);
        totalVAT = new Amount(totalVAT);
        amountPaid = new Amount(amountPaid);
        change = new Amount(change);
    }

    /**
     * Returns a defensive copy of the list containing all items purchased in the sale.
     * @return A list of items purchased in the sale.
     */
    public ArrayList<ItemInBasketDTO> items() {
        return new ArrayList<>(items);
    }

    /**
     * Returns a defensive copy of the total price of the sale.
     * @return The total price of the sale.
     */
    public Amount totalPrice() {
        return new Amount(totalPrice);
    }

    /**
     * Returns a defensive copy of the rounded total price of the sale.
     * @return The rounded total price of the sale.
     */
    public Amount roundedTotalPrice() {
        return new Amount(roundedTotalPrice);
    }

    /**
     * Returns a defensive copy of the total VAT of the sale.
     * @return The total amount of VAT applied to the sale.
     */
    public Amount totalVAT() {
        return new Amount(totalVAT);
    }

    /**
     * Returns a defensive copy of the amount paid by the customer.
     * @return The amount paid by the customer.
     */
    public Amount amountPaid() {
        return new Amount(amountPaid);
    }

    /**
     * Returns a defensive copy of the change from the sale.
     * @return The amount of change to be returned to the customer.
     */
    public Amount change() {
        return new Amount(change);
    }
}
