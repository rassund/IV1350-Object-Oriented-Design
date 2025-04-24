package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
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
    void addItemDTONotNull() {
        ItemDTO exampleItemDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        Amount expectedPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            returnedSaleSummaryDTO = sale.addItem(exampleItemDTO);

            assertNotNull(returnedSaleSummaryDTO, "Returned SaleSummaryDTO is null");
        }
    }

    @Test
    void addItemCorrectItemDescription() {
        ItemDTO exampleItemDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            returnedSaleSummaryDTO = sale.addItem(exampleItemDTO);

            assertEquals(exampleItemDTO.getDescription(), returnedSaleSummaryDTO.getLatestItemAddedDescription(),
                    "SaleSummaryDTO does not contain the correct description for the last item");
        }
    }

    @Test
    void addItemCorrectLastItemPrice() {
        ItemDTO exampleItemDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            returnedSaleSummaryDTO = sale.addItem(exampleItemDTO);

            assertEquals(exampleItemDTO.getPrice(), returnedSaleSummaryDTO.getLatestItemAddedPrice(),
                    "SaleSummaryDTO does not contain the correct price for the last item");
        }
    }

    @Test
    void addItemCorrectRunningTotal() {
        ItemDTO exampleItemDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        Amount expectedPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            returnedSaleSummaryDTO = sale.addItem(exampleItemDTO);
            Amount amountToAdd = new Amount(priceOfItem.getAmount().multiply(VAT.MEDIUM.getRate().add(BigDecimal.ONE)));
            expectedPrice.add(amountToAdd);

            assertEquals(expectedPrice.getAmount(), returnedSaleSummaryDTO.getRunningTotal().getAmount(), "SaleSummaryDTO does not contain the correct running total");
        }
    }



    @Test
    void endSaleAmountPaidBiggerThanTotalPrice() {
        Amount amountPaid = new Amount(new BigDecimal("1000"));
        ItemDTO exampleItemDTO;
        Amount totalPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            sale.addItem(exampleItemDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = amountPaid.subtract(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.getChange(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidSmallerThanTotalPrice() {
        Amount amountPaid = new Amount(new BigDecimal("1"));
        ItemDTO exampleItemDTO;
        Amount totalPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            sale.addItem(exampleItemDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = amountPaid.subtract(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.getChange(), "SaleDTO doesn't contain the correct change");
    }
    
//FOR ALL TESTS BELOW THE ASSUMPTION IS THAT WE DON'T HAVE TO DEAL WITH THE CUSTOMER NOT GIVING ENOUGH MONEY, IF THAT IS NOT TRUE THEY NEED TO BE CHANGED
    @Test
    void endSaleAmountPaidIsZero() {
        Amount amountPaid = new Amount(new BigDecimal("0"));
        ItemDTO exampleItemDTO;
        Amount totalPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            sale.addItem(exampleItemDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = amountPaid.subtract(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.getChange(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidIsNegative() {
        Amount amountPaid = new Amount(new BigDecimal("-5"));
        ItemDTO exampleItemDTO;
        Amount totalPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            sale.addItem(exampleItemDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = amountPaid.subtract(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.getChange(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidEqualsTotalPrice() {
        ItemDTO exampleItemDTO;
        Amount totalPrice = new Amount(BigDecimal.ZERO);
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i);
            sale.addItem(exampleItemDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount amountPaid = new Amount(totalPrice.getAmount());
        Amount expectedChange = amountPaid.subtract(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.getChange(), "SaleDTO doesn't contain the correct change");
    }
}

