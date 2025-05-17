package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

/**
 * Records a summary of information about the latest item added to the sale,
 * the running total, and the total amount of VAT applied to the sale.
 * @param latestItemName The name of the latest item added.
 * @param latestItemDescription The description of the latest item added.
 * @param latestItemPrice The price of the latest item added.
 * @param latestItemVAT The VAT rate of the latest item added.
 * @param latestItemID The ID of the latest item added.
 * @param runningTotal The running total of the sale.
 * @param roundedRunningTotal The running total rounded to the nearest swedish crown, needed for cash payments.
 * @param totalVAT The total amount of VAT applied to the sale.
 */
public record SaleSummaryDTO(String latestItemName, String latestItemDescription, Amount latestItemPrice,
              VAT latestItemVAT, int latestItemID, Amount runningTotal, Amount roundedRunningTotal, Amount totalVAT) {
    /**
     * Creates a {@link SaleSummaryDTO} based on an {@link ItemInBasketDTO}.
     * @param itemInBasketDTO The {@link ItemInBasketDTO} containing information about the latest item added to the sale.
     * @param runningTotal The running total of the sale.
     * @param totalVAT The total amount of VAT applied to the sale.
     */
    public SaleSummaryDTO(ItemInBasketDTO itemInBasketDTO, Amount runningTotal, Amount roundedRunningTotal, Amount totalVAT) {
        this(itemInBasketDTO.name(), itemInBasketDTO.description(), new Amount(itemInBasketDTO.price()),
                itemInBasketDTO.vatRate(), itemInBasketDTO.itemID(), new Amount(runningTotal), new Amount(roundedRunningTotal), new Amount(totalVAT));
    }
}
