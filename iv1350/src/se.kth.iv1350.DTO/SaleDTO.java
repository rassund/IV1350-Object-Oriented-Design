package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SaleDTO {
    private LocalDateTime dateTime;
    private ArrayList<ItemDTO> items;
    private Amount totalPrice;
    private Amount totalVAT;
    private Amount amountPaid;
    private Amount change;

    public SaleDTO(LocalDateTime dateTime, ArrayList<ItemDTO> items, Amount totalPrice, Amount totalVAT, Amount amountPaid, Amount change) {
        this.dateTime = dateTime;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    public Amount getTotalPrice() {
        return totalPrice;
    }

    public Amount getChange() {
        return change;
    }
}
