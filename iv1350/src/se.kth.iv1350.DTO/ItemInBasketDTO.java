package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

public class ItemInBasketDTO {
    private Amount price;
    private VAT VATRate;
    private String description;
    private int itemID;
    private String name;
    private int amountInBasket;

    public ItemInBasketDTO(ItemDTO itemDTO, int amountInBasket) {
        this.price = itemDTO.getPrice();
        this.VATRate = itemDTO.getVATRate();
        this.description = itemDTO.getDescription();
        this.itemID = itemDTO.getID();
        this.name = itemDTO.getName();
        this.amountInBasket = amountInBasket;

    }

    public ItemInBasketDTO(ItemInBasketDTO itemInBasketDTO, int amountInBasket) {
        this.price = itemInBasketDTO.getPrice();
        this.VATRate = itemInBasketDTO.getVATRate();
        this.description = itemInBasketDTO.getDescription();
        this.itemID = itemInBasketDTO.getID();
        this.name = itemInBasketDTO.getName();
        this.amountInBasket = amountInBasket;

    }

    public int getID() {
        return itemID;
    }

    public Amount getPrice() {
        return price;
    }

    public VAT getVATRate() { return VATRate; }

    public String getDescription() { return description; }

    public String getName() { return name; }

    public int getAmountInBasket() { return amountInBasket; }
}
