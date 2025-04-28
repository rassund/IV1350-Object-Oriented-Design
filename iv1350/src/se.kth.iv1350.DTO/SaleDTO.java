package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SaleDTO {
    private LocalDateTime dateTime;
    private ArrayList<ItemInBasketDTO> items;
    private Amount totalPrice;
    private Amount totalVAT;
    private Amount amountPaid;
    private Amount change;

    public SaleDTO(LocalDateTime dateTime, ArrayList<ItemInBasketDTO> items, Amount totalPrice, Amount totalVAT, Amount amountPaid, Amount change) {
        this.dateTime = dateTime;
        this.items = new ArrayList<ItemInBasketDTO>(items);
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    public LocalDateTime getDateTime() { return dateTime; }

    public ArrayList<ItemInBasketDTO> getItems() { return items; }

    public Amount getTotalPrice() {
        return totalPrice;
    }

    public Amount getTotalVAT() { return totalVAT; }

    public Amount getAmountPaid() { return amountPaid; }

    public Amount getChange() {
        return change;
    }
}
