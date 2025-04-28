package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

/**
 * Records both an item and the amount of that item contained in the basket.
 * @param price The price of the item (including VAT).
 * @param vatRate The VAT rate of the item.
 * @param description A description of the item.
 * @param itemID The ID of the item.
 * @param name The name of the item.
 * @param amountInBasket The amount of the item currently present in the basket.
 */
public record ItemInBasketDTO(Amount price, VAT vatRate, String description, int itemID, String name, int amountInBasket) {
    /**
     * Creates an {@link ItemInBasketDTO} based on an {@link ItemDTO} and an integer.
     *
     * <p>This is useful when adding a completely new item to the sale.</p>
     * @param itemDTO The DTO containing all information about the item.
     * @param amountInBasket The amount of the item present in the basket.
     */
    public ItemInBasketDTO(ItemDTO itemDTO, int amountInBasket) {
        this(new Amount(itemDTO.price()), itemDTO.vatRate(), itemDTO.description(), itemDTO.itemID(), itemDTO.name(), amountInBasket);
    }

    /**
     * Creates an {@link ItemInBasketDTO} based on an {@link ItemInBasketDTO} and an integer.
     *
     * <p>This is useful when adding or removing quantities of an item from the sale.</p>
     * @param itemInBasketDTO A previous instance of an {@link ItemInBasketDTO} instance.
     * @param amountInBasket The updated amount of the item present in the basket.
     */
    public ItemInBasketDTO(ItemInBasketDTO itemInBasketDTO, int amountInBasket) {
        this(new Amount(itemInBasketDTO.price()), itemInBasketDTO.vatRate(), itemInBasketDTO.description(),
                itemInBasketDTO.itemID(), itemInBasketDTO.name(), amountInBasket);
    }
}
