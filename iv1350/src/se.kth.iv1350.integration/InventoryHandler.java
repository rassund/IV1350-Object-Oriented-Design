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

    public InventoryHandler() {
        for (int i = 0; i < dummyItems.length; i++) {
            dummyItems[i] = new ItemDTO(new Amount(BigDecimal.valueOf(40+i)), VAT.LOW, "Dummy item nr " + i, i);
        }
    }

    public ItemDTO fetchItemDTO(int itemID) {
        for (ItemDTO item : dummyItems) {
            if (item.getID() == itemID) {
                return item;
            }
        }
        return null;
    }

    public void updateInventory(SaleDTO saleDTO) {
        return;
    }
}