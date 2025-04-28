package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a receipt for a Sale and contains all information needed for its printing.
 */
public final class Receipt {
    private final LocalDateTime dateTimeOfSale;
    private final Amount totalPrice;
    private final Amount totalVAT;
    private final ArrayList<ItemInBasketDTO> itemsBought;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a <code>receipt</code> for a finished instance of a sale.
     * @param saleDTO contains all the needed information for the <code>receipt</code>.
     */

    public Receipt(SaleDTO saleDTO) {
        this.dateTimeOfSale = saleDTO.dateTime();
        this.totalPrice = saleDTO.totalPrice();
        this.totalVAT = saleDTO.totalVAT();
        this.itemsBought = new ArrayList<>(saleDTO.items());
        this.amountPaid = saleDTO.amountPaid();
        this.change = saleDTO.change();
    }

    /**
     * Gets the date for a sale.
     * @return A <code>LocalDate</code> representing the date in local time for the receipts purchase.
     */
    public LocalDate getDateOfSale() { return dateTimeOfSale.toLocalDate(); }

    /**
     * Gets the hour and minute (but not seconds or anything below) for a purchase.
     * @return A <code>LocalDate</code> showing the hour and minute in local time for the receipts purchase.
     */
    public LocalTime getTimeOfSale() { return dateTimeOfSale.toLocalTime().withSecond(0).withNano(0); }

    /**
     * Gets the total price.
     * @return An <code>Amount</code> representing the total price (Including VAT) for all items in the sale.
     */
    public Amount getTotalPrice() { return totalPrice; }

    /**
     * Gets the total VAT.
     * @return An <code>Amount</code> representing total VAT for all items in the sale.
     */
    public Amount getTotalVAT() { return totalVAT; }

    /**
     * Gets a list of all items in a sale.
     * @return A <code>ArrayList<ItemInBasketDTO></code> representing all the items bought in the sale.
     */
    public ArrayList<ItemInBasketDTO> getItemsBought() { return itemsBought; }

    /**
     * Gets the amount a customer has paid in a sale.
     * @return A <code>Amount</code> representing what the customer paid in a sale, this should be higher than or the same as the totalPrice.
     */
    public Amount getAmountPaid() { return amountPaid; }

    /**
     * Gets the change to give back to a customer in a sale.
     * @return An <code>Amount> representing the change to give back in a sale, It's the difference between amountPaid and totalPrice.
     */
    public Amount getChange() { return change; }
}
