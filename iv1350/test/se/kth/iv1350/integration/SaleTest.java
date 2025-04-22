package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.VAT;

import java.math.BigDecimal;

class SaleTest {
    private InventoryHandler invHandler;
    private Sale sale;

    @BeforeEach
    void setUp() {
        invHandler = new InventoryHandler();
        sale = new Sale();
    }

    @AfterEach
    void tearDown() {
        invHandler = null;
        sale = null;
    }

    @Test
    void addItemValidID() {
        ItemDTO exampleItemDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        Amount expectedPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            returnedSaleSummaryDTO = sale.addItem(exampleItemDTO);
            expectedPrice.add(priceOfItem.getAmount().multiply(VAT.MEDIUM.getRate().add(BigDecimal.ONE)));

            assertNotNull(returnedSaleSummaryDTO, "Returned SaleSummaryDTO is null");
            assertEquals(
                    exampleItemDTO.getDescription(), returnedSaleSummaryDTO.getLatestItemAddedDescription(),
                    "SaleSummaryDTO does not contain the correct description for the last item"
            );
            assertEquals(
                    exampleItemDTO.getPrice(), returnedSaleSummaryDTO.getLatestItemAddedPrice(),
                    "SaleSummaryDTO does not contain the correct price for the last item"
            );
            assertEquals(expectedPrice.getAmount(), returnedSaleSummaryDTO.getRunningTotal().getAmount(), "SaleSummaryDTO does not contain the correct running total");
        }
    }
}
