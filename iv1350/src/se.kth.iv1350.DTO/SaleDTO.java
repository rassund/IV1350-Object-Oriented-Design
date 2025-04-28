package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;

import java.time.LocalDateTime;
import java.util.ArrayList;

public final class SaleDTO {
    private final LocalDateTime dateTime;
    private final ArrayList<ItemInBasketDTO> items;
    private final Amount totalPrice;
    private final Amount totalVAT;
    private final Amount amountPaid;
    private final Amount change;

    public SaleDTO(LocalDateTime dateTime, ArrayList<ItemInBasketDTO> items, Amount totalPrice, Amount totalVAT, Amount amountPaid, Amount change) {
        this.dateTime = dateTime;
        this.items = new ArrayList<ItemInBasketDTO>(items);
        this.totalPrice = new Amount(totalPrice);
        this.totalVAT = new Amount(totalVAT);
        this.amountPaid = new Amount(amountPaid);
        this.change = new Amount(change);
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
