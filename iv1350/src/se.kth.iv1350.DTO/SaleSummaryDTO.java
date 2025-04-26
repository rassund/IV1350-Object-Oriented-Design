package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

public class SaleSummaryDTO {
    ItemInBasketDTO itemInBasketDTO;
    Amount runningTotal;
    Amount totalVAT;

    public SaleSummaryDTO(ItemInBasketDTO itemInBasketDTO, Amount runningTotal, Amount totalVAT) {
        this.itemInBasketDTO = itemInBasketDTO;
        this.runningTotal = runningTotal;
        this.totalVAT = totalVAT;
    }

    public String getLatestItemName() { return itemInBasketDTO.getName(); }

    public String getLatestItemAddedDescription() { return itemInBasketDTO.getDescription(); }

    public Amount getLatestItemAddedPrice() { return itemInBasketDTO.getPrice(); }

    public VAT getLatestItemVAT() { return itemInBasketDTO.getVATRate(); }

    public Amount getRunningTotal() { return runningTotal; }

    public Amount getTotalVAT() { return totalVAT; }

    public int getLatestItemID() { return itemInBasketDTO.getID(); }
}
