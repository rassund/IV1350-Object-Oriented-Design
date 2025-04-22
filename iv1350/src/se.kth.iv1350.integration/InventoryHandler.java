package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

/**
 * Used for handling the database where all items in the store's inventory is written down.
 */
public class InventoryHandler {
    private ItemDTO[] dummyItems = new ItemDTO[3];

    public InventoryHandler() {
        for (int i = 0; i < dummyItems.length; i++) {
            dummyItems[i] = new ItemDTO(new Amount(40+i), VAT.LOW, "Dummy item nr " + i, i);
        }
    }

    public ItemDTO fetchInfo(int itemID) {
        for (ItemDTO item : dummyItems) {
            if (item.getID() == itemID) {
                return item;
            }
        }
        return null;
    }
}