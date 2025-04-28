package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

/**
 * Records all relevant information about an item fetched from the inventory.
 * @param price The price of the item (including VAT).
 * @param vatRate The VAT rate of the item.
 * @param description A description of the item.
 * @param itemID The ID of the item.
 * @param name The name of the item.
 */
public record ItemDTO(Amount price, VAT vatRate, String description, int itemID, String name) {
    /**
     * Creates a new {@link ItemDTO} instance with defensive copying for mutable fields.
     * @param price The price of the item (including VAT).
     * @param vatRate The VAT rate of the item.
     * @param description A description of the item.
     * @param itemID The ID of the item.
     * @param name The name of the item.
     */
    public ItemDTO {
        price = new Amount(price);
    }
}
