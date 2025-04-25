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
    private final ItemDTO[] dummyItems = new ItemDTO[2];

    public InventoryHandler() {
        addDummyItems();
    }

    private void addDummyItems() {
        dummyItems[0] = new ItemDTO(new Amount(
                new BigDecimal("29.90")), VAT.LOW,
                "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free",
                0, "BigWheel Oatmeal");
        dummyItems[1] = new ItemDTO(new Amount(
                new BigDecimal("14.90")), VAT.LOW,
                "YouGoGo Blueberry 240g, low sugar yogurt, blueberry flavour",
                1, "YouGoGo Blueberry");
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