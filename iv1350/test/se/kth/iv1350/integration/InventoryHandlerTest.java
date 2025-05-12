package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

class InventoryHandlerTest {
    private InventoryHandler invHandler;

    @BeforeEach
    void setUp() {
        invHandler = InventoryHandler.getInstance();
    }

    @AfterEach
    void tearDown() {
        invHandler = null;
    }

    @Test
     void fetchItemDTOValidIDNotNull() {
        int testID = 0;
        try {
            ItemDTO returnedItemDTO = invHandler.fetchItemDTO(testID);

            assertNotNull(returnedItemDTO, "Returned item DTO is null");
        } catch (InvalidIDException e) {
            fail("Valid ID " + testID + " was treated as invalid");
        }
     }

    @Test
    void fetchItemDTOValid() {
        int testID = 1;
        try {
            ItemDTO expectedItemDTO = new ItemDTO(new Amount("14.90"), VAT.LOW,
                    "YouGoGo Blueberry 240g, low sugar yogurt, blueberry flavour",
                    testID, "YouGoGo Blueberry");
            ItemDTO returnedItemDTO = invHandler.fetchItemDTO(testID);

            assertEquals(expectedItemDTO, returnedItemDTO, "InventoryHandler did not fetch correct item");
        } catch (InvalidIDException e) {
            fail("Valid ID " + testID + " was treated as invalid");
        }
    }

    @Test
    void fetchItemDTOInvalidID() {
        int testID = 10;
        try {
            invHandler.fetchItemDTO(testID);
            fail("InventoryHandler returned an item with invalid ID");
        } catch (InvalidIDException e) {
            assertTrue(e.getMessage().contains(Integer.toString(testID)), "InvalidIDException contains wrong itemID: " + e.getMessage());
        }
    }

    @Test
    void fetchItemDTODatabaseException() {
        try {
            invHandler.fetchItemDTO(-1);
            fail("InventoryHandler returned an item with invalid ID");
        } catch (InvalidIDException e) {
            fail("Wrong exception was thrown");
        }
    }
}