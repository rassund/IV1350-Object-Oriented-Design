package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

public final class SaleSummaryDTO {
    private final ItemInBasketDTO itemInBasketDTO;
    private final Amount runningTotal;
    private final Amount totalVAT;

    public SaleSummaryDTO(ItemInBasketDTO itemInBasketDTO, Amount runningTotal, Amount totalVAT) {
        this.itemInBasketDTO = itemInBasketDTO;
        this.runningTotal = new Amount(runningTotal);
        this.totalVAT = new Amount(totalVAT);
    }

    public String getLatestItemName() { return itemInBasketDTO.getName(); }

    public String getLatestItemAddedDescription() { return itemInBasketDTO.getDescription(); }

    public Amount getLatestItemAddedPrice() { return itemInBasketDTO.getPrice(); }

    public VAT getLatestItemVAT() { return itemInBasketDTO.getVATRate(); }

    public Amount getRunningTotal() { return runningTotal; }

    public Amount getTotalVAT() { return totalVAT; }

    public int getLatestItemID() { return itemInBasketDTO.getID(); }
}
