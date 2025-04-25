package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

import java.math.BigDecimal;

/**
 * Used for handling the database containing the information for all items in the store's inventory.
 */
public class InventoryHandler {
    private ItemDTO[] dummyItems = new ItemDTO[3];

    /**
     * Constructs an instance of the "InventoryHandler" class.
     * When a new InventoryHandler is created, it is populated with dummy data, with a VAT rate of 8% (VAT.LOW).
     */
    public InventoryHandler() {
        for (int i = 0; i < dummyItems.length; i++) {
            dummyItems[i] = new ItemDTO(new Amount(BigDecimal.valueOf(40+i)), VAT.LOW, "Dummy item nr " + i, i);
        }
    }

    /**
     * Retreives an item with a given <code>ItemID</code> from the <code>ItemDTO</code> array of this InventoryHandler instance.
     * Returns null if no item in the array has the given <code>itemID</code>.
     * @param itemID The itemID of the item to search for in the <code>ItemDTO</code> array.
     * @return The <code>ItemDTO</code> with corresponding <code>itemID</code> value if found. Otherwise, returns null.
     */
    public ItemDTO fetchItemDTO(int itemID) {
        for (ItemDTO item : dummyItems) {
            if (item.getID() == itemID) {
                return item;
            }
        }
        return null;
    }

    /**
     * Updates the corresponding table in the database handled by the InventoryHandler class.
     * Contains no code since there is no such database right now.
     * @param saleDTO Contains all the info about the items to be removed/updated inside the database.
     */
    public void updateInventory(SaleDTO saleDTO) {
        return;
    }
}