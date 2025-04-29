package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    private Receipt receipt;
    private LocalDateTime localDateTime;

    @BeforeEach
    void setUp() {
        Sale sale = new Sale();
        ItemDTO itemDTO= new ItemDTO(new Amount("25.50"), VAT.MEDIUM, "Example item description.", 5, "Example item" );
        ItemInBasketDTO itemInBasketDTO = new ItemInBasketDTO(itemDTO,3);
        sale.addItem(itemInBasketDTO);
        SaleDTO saleDTO = sale.endSale(new Amount("150"));
        receipt = new Receipt(saleDTO);
        localDateTime = LocalDateTime.now();

    }

    @AfterEach
    void tearDown() {
        receipt = null;
        localDateTime = null;
    }

    @Test
    void getDateOfSale() {
        LocalDate expectedLocalDate = localDateTime.toLocalDate();
        LocalDate returnedLocalDate = receipt.getDateOfSale();

        assertEquals(expectedLocalDate, returnedLocalDate, "The returned LocalDate didn't match the expected one.");
    }

    @Test
    void getTimeOfSale() {
        LocalTime expectedLocalTime = localDateTime.toLocalTime().withSecond(0).withNano(0);
        LocalTime returnedLocalTime = receipt.getTimeOfSale();

        assertEquals(expectedLocalTime, returnedLocalTime, "The returned LocalTime didn't match the expected one.");
    }
}