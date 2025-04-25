package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

import java.math.BigDecimal;

public class ItemDTO {
    private Amount price;
    private VAT vatRate;
    private String description;
    private int itemID;
    private String name;

    public ItemDTO(Amount price, VAT vatRate, String description, int itemID, String name) {
        this.price = price;
        this.vatRate = vatRate;
        this.description = description;
        this.itemID = itemID;
        this.name = name;
    }

    public int getID() {
        return itemID;
    }

    public Amount getPrice() {
        return price;
    }

    public VAT getVATRate() { return vatRate; }

    public String getDescription() { return description; }

    public String getName() { return name; }
}
