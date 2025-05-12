package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.integration.InventoryHandler;

import java.math.BigDecimal;

class SaleTest {
    private InventoryHandler invHandler;
    private Sale sale;

    @BeforeEach
    void setUp() {
        invHandler = InventoryHandler.getInstance();
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
        ItemInBasketDTO exampleItemInBasketDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);

            assertNotNull(returnedSaleSummaryDTO, "Returned SaleSummaryDTO is null");
        }
    }

    @Test
    void addItemCorrectItemName() {
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temp" + i);
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);

            assertEquals(exampleItemDTO.name(), returnedSaleSummaryDTO.latestItemName(),
                    "SaleSummaryDTO does not contain the correct name for the last item");
        }
    }

    @Test
    void addItemSpecialCharactersItemName() {
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;

        Amount priceOfItem = new Amount(BigDecimal.valueOf(5));
        exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item", 2, "ÄÖÅäöå _'*^¨<>#&€ ĆŃÓŻŹŁĘĄŚćńóżźłęą");
        exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
        returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);

        assertEquals("ÄÖÅäöå _'*^¨<>#&€ ĆŃÓŻŹŁĘĄŚćńóżźłęą", returnedSaleSummaryDTO.latestItemName(),
                "SaleSummaryDTO does not contain the correct name for the last item");
    }

    @Test
    void addItemCorrectItemDescription() {
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);

            assertEquals(exampleItemDTO.description(), returnedSaleSummaryDTO.latestItemDescription(),
                    "SaleSummaryDTO does not contain the correct description for the last item");
        }
    }

    @Test
    void addItemSpecialCharactersItemDescription() {
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;

            Amount priceOfItem = new Amount(BigDecimal.valueOf(5));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "ÄÖÅäöå _'*^¨<>#&€ ĆŃÓŻŹŁĘĄŚćńóżźłęą", 2, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);

            assertEquals("ÄÖÅäöå _'*^¨<>#&€ ĆŃÓŻŹŁĘĄŚćńóżźłęą", returnedSaleSummaryDTO.latestItemDescription(),
                    "SaleSummaryDTO does not contain the correct description for the last item");
    }

    @Test
    void addItemCorrectLastItemPrice() {
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);

            assertEquals(exampleItemDTO.price(), returnedSaleSummaryDTO.latestItemPrice(),
                    "SaleSummaryDTO does not contain the correct price for the last item");
        }
    }

    @Test
    void addItemCorrectRunningTotal() {
        ItemDTO exampleItemDTO;
        SaleSummaryDTO returnedSaleSummaryDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        Amount expectedPrice = new Amount("0");
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(new BigDecimal(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            returnedSaleSummaryDTO = sale.addItem(exampleItemInBasketDTO);
            expectedPrice.addToThis(priceOfItem);

            assertEquals(expectedPrice.getAmount(), returnedSaleSummaryDTO.runningTotal().getAmount(), "SaleSummaryDTO does not contain the correct running total");
        }
    }



    @Test
    void endSaleAmountPaidBiggerThanTotalPrice() {
        Amount amountPaid = new Amount(new BigDecimal("1000"));
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        Amount totalPrice;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            sale.addItem(exampleItemInBasketDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = new Amount(amountPaid.getAmount());
        expectedChange.subtractFromThis(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.change(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidSmallerThanTotalPrice() {
        Amount amountPaid = new Amount(new BigDecimal("1"));
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        Amount totalPrice;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            sale.addItem(exampleItemInBasketDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = new Amount(amountPaid.getAmount());
        expectedChange.subtractFromThis(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.change(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidIsZero() {
        Amount amountPaid = new Amount(new BigDecimal("0"));
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        Amount totalPrice;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            sale.addItem(exampleItemInBasketDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = new Amount(amountPaid.getAmount());
        expectedChange.subtractFromThis(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.change(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidIsNegative() {
        Amount amountPaid = new Amount(new BigDecimal("-5"));
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        Amount totalPrice;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            sale.addItem(exampleItemInBasketDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount expectedChange = new Amount(amountPaid.getAmount());
        expectedChange.subtractFromThis(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.change(), "SaleDTO doesn't contain the correct change");
    }

    @Test
    void endSaleAmountPaidEqualsTotalPrice() {
        ItemDTO exampleItemDTO;
        ItemInBasketDTO exampleItemInBasketDTO;
        Amount totalPrice;
        for (int i = 0; i < 3; i++) {
            Amount priceOfItem = new Amount(BigDecimal.valueOf(5 + i));
            exampleItemDTO = new ItemDTO(priceOfItem, VAT.MEDIUM, "Example item number " + i, i, "Temporary");
            exampleItemInBasketDTO = new ItemInBasketDTO(exampleItemDTO, 1);
            sale.addItem(exampleItemInBasketDTO);
        }
        totalPrice = sale.getRunningTotal();
        Amount amountPaid = new Amount(totalPrice.getAmount());
        Amount expectedChange = new Amount(amountPaid.getAmount());
        expectedChange.subtractFromThis(totalPrice);
        SaleDTO saleDTOToTest = sale.endSale(amountPaid);

        assertEquals(expectedChange, saleDTOToTest.change(), "SaleDTO doesn't contain the correct change");
    }
}

