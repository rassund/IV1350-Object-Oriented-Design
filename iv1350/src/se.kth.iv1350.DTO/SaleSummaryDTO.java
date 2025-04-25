package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.VAT;

public class SaleSummaryDTO {
    ItemDTO itemDTO;
    Amount runningTotal;
    Amount totalVAT;

    public SaleSummaryDTO(ItemDTO itemDTO, Amount runningTotal, Amount totalVAT) {
        this.itemDTO = itemDTO;
        this.runningTotal = runningTotal;
        this.totalVAT = totalVAT;
    }

    public String getLatestItemName() { return itemDTO.getName(); }

    public String getLatestItemAddedDescription() { return itemDTO.getDescription(); }

    public Amount getLatestItemAddedPrice() { return itemDTO.getPrice(); }

    public VAT getLatestItemVAT() { return itemDTO.getVATRate(); }

    public Amount getRunningTotal() { return runningTotal; }

    public Amount getTotalVAT() { return totalVAT; }

    public int getLatestItemID() { return itemDTO.getID(); }
}
