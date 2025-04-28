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
        invHandler = new InventoryHandler();
    }

    @AfterEach
    void tearDown() {
        invHandler = null;
    }

    @Test
    void fetchItemDTOValidIDNotNull() {
        ItemDTO returnedItemDTO = invHandler.fetchItemDTO(0);

        assertNotNull(returnedItemDTO, "Returned item DTO is null");
    }

    @Test
    void fetchItemDTOValid() {
        ItemDTO expectedItemDTO = new ItemDTO(new Amount("14.90"), VAT.LOW,
                "YouGoGo Blueberry 240g, low sugar yogurt, blueberry flavour",
                1, "YouGoGo Blueberry");
        ItemDTO returnedItemDTO = invHandler.fetchItemDTO(1);

        assertEquals(expectedItemDTO, returnedItemDTO, "InventoryHandler did not fetch correct item");
    }

    @Test
    void fetchItemDTOInvalidID() {
        ItemDTO returnedItemDTO = invHandler.fetchItemDTO(10);

        assertNull(returnedItemDTO, "InventoryHandler returned an item with invalid ID");
    }
}