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
public class Receipt {
    private final LocalDateTime dateTimeOfSale;
    private final Amount totalPrice;
    private final Amount totalVAT;
    private final ArrayList<ItemInBasketDTO> itemsBought;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a <code>receipt</code> for a finished instance of a sale.
     * @param saleDTO contains all the needed information except for the <code>dateTimeOfSale</code> for the <code>receipt</code>.
     */

    public Receipt(SaleDTO saleDTO) {
        this.dateTimeOfSale = saleDTO.dateTime();
        this.totalPrice = saleDTO.totalPrice();
        this.totalVAT = saleDTO.totalVAT();
        this.itemsBought = saleDTO.items();
        this.amountPaid = saleDTO.amountPaid();
        this.change = saleDTO.change();
    }

    /**
     * Returns the date for a sale.
     * @return A <code>LocalDate</code> representing the date in local time for the receipts purchase.
     */
    public LocalDate getDateOfSale() { return dateTimeOfSale.toLocalDate(); }

    /**
     * Returns the hour and minute (but not seconds or anything below) for a purchase.
     * @return A <code>LocalDate</code> showing the hour and minute in local time for the receipts purchase.
     */
    public LocalTime getTimeOfSale() { return dateTimeOfSale.toLocalTime().withSecond(0).withNano(0); }

    public Amount getTotalPrice() { return totalPrice; }

    public Amount getTotalVAT() { return totalVAT; }

    public ArrayList<ItemInBasketDTO> getItemsBought() { return itemsBought; }

    public Amount getAmountPaid() { return amountPaid; }

    public Amount getChange() { return change; }
}
