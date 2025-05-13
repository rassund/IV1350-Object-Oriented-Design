package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

import java.util.ArrayList;

/**
 * Used for handling the database containing the information for all items in the store's inventory.
 */
public class InventoryHandler {
    private static final InventoryHandler INSTANCE = new InventoryHandler();
    private final ArrayList<ItemDTO> dummyItems;

    /**
     * Constructs an instance of the "InventoryHandler" class.
     * When a new InventoryHandler is created, it is populated with dummy data, with a VAT rate of 8% (VAT.LOW).
     */
    private InventoryHandler() {
        dummyItems = new ArrayList<ItemDTO>();
        addDummyItems();
    }

    /**
     * @return The only Singleton instance of this <code>InventoryHandler</code> class.
     */
    public static InventoryHandler getInstance() {
        return INSTANCE;
    }

    private void addDummyItems() {
        ItemDTO bigWheelOatmeal = new ItemDTO(new Amount("29.90"), VAT.LOW,
                "BigWheel Oatmeal 500g, whole grain oats, high fiber, gluten free",
                0, "BigWheel Oatmeal");
        dummyItems.add(bigWheelOatmeal);
        ItemDTO youGoGoBlueberry = new ItemDTO(new Amount("14.90"), VAT.LOW,
                "YouGoGo Blueberry 240g, low sugar yogurt, blueberry flavour",
                1, "YouGoGo Blueberry");
        dummyItems.add(youGoGoBlueberry);
    }

    /**
     * Retrieves an item with a given <code>ItemID</code> from the <code>ItemDTO</code> array of this InventoryHandler instance.
     * @param itemID The itemID of the item to search for in the <code>ItemDTO</code> array.
     * @return The <code>ItemDTO</code> with corresponding <code>itemID</code> value.
     * @throws DatabaseException If the inventory database can not be called. As there currently is no database, this is simulated when the itemID is "-1".
     * @throws InvalidIDException If the <code>itemID</code> given does not exist in the inventory catalog.
     */
    public ItemDTO fetchItemDTO(int itemID) throws DatabaseException, InvalidIDException {
        if (itemID == -1) {
            throw new DatabaseException("Inventory database could not be found.");
        }

        for (ItemDTO item : dummyItems) {
            if (item.itemID() == itemID) {
                return item;
            }
        }
        throw new InvalidIDException("There is no item in the inventory with ID " + itemID + ".");
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
